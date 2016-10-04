import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class View2 extends JPanel implements ViewInt{

	public JButton play = new JButton("Play");
	// the model that this view is showing
	private Model model;
	public JSlider slide;
	Rectangle r = Main.frame.getBounds();

	public ActionListener strokeFinder =  new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.incrementCurPoint();
			if (model.curpoint == model.strokes.size()) {
				play.setText("Play");
				play.removeActionListener(stopTimer);
				play.addActionListener(startTimer);
				timer.stop();
			}
		}
	};


	Timer timer = new Timer(1000, strokeFinder);

	public ActionListener stopTimer =  new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			play.setText("Play");
			play.removeActionListener(stopTimer);
			play.addActionListener(startTimer);
			timer.stop();
		}
	};

	public ActionListener startTimer =  new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			play.setText("Pause");
			play.removeActionListener(startTimer);
			play.addActionListener(stopTimer);
			timer.start();
		}
	};

	View2(Model model_) {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(r.width,60));
		setLayout(new BorderLayout());

		model = model_;

		JButton start = new JButton("Start");
		JButton end = new JButton("End");

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.curpoint = 0;
				model.notifyViews();
			}
		});

		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.curpoint = model.strokes.size();
				model.notifyViews();
			}
		});

		slide = new JSlider(0, model.strokes.size());
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slide.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				model.curpoint = slide.getValue();
				model.notifyViews();
			}
		});


		play.addActionListener(startTimer);
		add(play, BorderLayout.WEST);
		add(slide);
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(start);
		pan.add(end);
		add(pan, BorderLayout.EAST);

		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)		

	}

	public void paintComponent(Graphics g){
		g.drawImage(model.image, 0, 0, null);
	}

	// Observer interface 
	@Override
	public void update() {
		int curpoint = model.curpoint;
		slide.setMaximum(model.strokes.size());
		slide.setValue(curpoint);
		repaint();
	}
}
