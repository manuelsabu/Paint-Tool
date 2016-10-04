import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class View3 extends JPanel implements ViewInt {



    // the model that this view is showing
    private Model model;

    View3(Model model_) {


        // create the view UI
        //button = new JButton("?");

        //;
        //setBackground(model.bgColor);

        // set the model
        model = model_;

        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                model.startNewS();
                model.addPoint(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                model.addPoint(e.getX(),e.getY());
            }
        });

        addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                model.addPoint(e.getX(),e.getY());
                model.endS();
            }
        });
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < model.curpoint; ++i){
            Stroke stroke = model.strokes.get(i);
            Stroke.Point lastP = stroke.points.get(0);

            if (stroke.points.size() > 1) {
                for (int j = 1; j < stroke.points.size(); ++j) {
                    Stroke.Point p = stroke.points.get(j);
                    g2.setColor(stroke.col);
                    g2.setStroke(new BasicStroke(stroke.thick));


                    g2.drawLine(lastP.x, lastP.y, p.x, p.y);
                    lastP = p;
                }
            } else {
                Stroke.Point p = stroke.points.get(0);
                g2.drawLine(p.x, p.y, p.x, p.y);
            }
        }
    }

    public void update() {
        //button.setText(Integer.toString(model.getCounterValue()));
        repaint();
    }
}
