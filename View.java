import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class View extends JPanel implements ViewInt {
	// the model that this view is showing
	private Model model;

	View(Model model_) {
        Rectangle r = Main.frame.getBounds();
        // create the view UI
        JPanel stroke = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(model.strokeColor);
                g2.setStroke(new BasicStroke(model.thickness));
                g2.drawLine(4, this.getHeight()/2, this.getWidth() - 6, this.getHeight()/2);
            }
        };

        JPanel buttonPan = new JPanel();
        JPanel button = new JPanel();
        button.setPreferredSize(new Dimension (70,70));
        button.setBackground(model_.strokeColor);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JFrame picker = new JFrame();
                picker.setSize(300,100);
                picker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel sampleText = new JLabel("Label");
                Color c = JColorChooser.showDialog(null, "Choose a Color", sampleText.getForeground());
                if (c != null)
                    sampleText.setForeground(c);
                model.strokeColor = c;
                button.setBackground(model_.strokeColor);
                stroke.repaint();

            }
        });

        buttonPan.setBorder(BorderFactory.createTitledBorder("Color"));
        buttonPan.setPreferredSize(new Dimension(100,120));
        buttonPan.add(button, BorderLayout.SOUTH);


        JPanel strokeStuff = new JPanel();



        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                model.thickness = (Integer)spinner.getValue();
                stroke.repaint();
                model.notifyViews();
            }
        });

        stroke.setBorder(BorderFactory.createLineBorder(Color.black));
        //stroke.setAlignmentX(Component.LEFT_ALIGNMENT);
        stroke.setPreferredSize(new Dimension(70,30));
        stroke.setBackground(Color.white);

        strokeStuff.add(stroke, BorderLayout.NORTH);
        strokeStuff.add(spinner, BorderLayout.SOUTH);
        strokeStuff.setBorder(BorderFactory.createTitledBorder("Thickness"));
        strokeStuff.setPreferredSize(new Dimension(100,100));

        setPreferredSize(new Dimension(100, r.height));
        setBackground(Color.GRAY);
        add(buttonPan, BorderLayout.NORTH);
        add(strokeStuff);


        model = model_;


        // set the mdel
        //model = model_;
        //new ColorChooser_01();

//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                model.setBrushThickness(4);
//            }
//        })
    }




	// Observer interface 
	@Override
	public void update() {
		//button.setText(Integer.toString(model.getCounterValue()));
	}
} 
