/**
 * Created by Manuel on 2016-02-27.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{

    public static JFrame frame = new JFrame("Doodle");
    public static JPanel p = new JPanel(new BorderLayout());
    public static JPanel canP = new JPanel();

    public static JMenuBar mb = new JMenuBar();
    public static JMenu mnuFile = new JMenu("File");
    public static JMenuItem mnuItemQuit = new JMenuItem("Quit");
    public static JMenu mnuView = new JMenu("View");
    public static JMenuItem mnuItemFit = new JMenuItem("Fit");
    public static JMenuItem mnuItemFull = new JMenuItem("Full-Size");

    public static Model model = new Model();
    public static View tools = new View(model);
    public static View2 slider = new View2(model);
    public static View3 canvas = new View3(model);
    public static void main(String[] args){

        // create Model and initialize it
        // Set menubar
        frame.setJMenuBar(mb);

        // Build Menus
        mnuFile.add(mnuItemQuit);
        mnuView.add(mnuItemFit);
        mnuView.add(mnuItemFull);

        mb.add(mnuFile);
        mb.add(mnuView);

        // Setup Main Frame


        // create View, tell it about model (and controller)

        // tell Model about View.
        model.registerView(tools);

        model.registerView(slider);

        model.registerView(canvas);

        canvas.setBorder(BorderFactory.createLineBorder(Color.black));
        canvas.setPreferredSize(new Dimension(800,600));
        canP.add(canvas);

        p.add(tools, "West");
        p.add(slider, "South");
        p.add(new JScrollPane(canP), "Center");

        frame.getContentPane().add(p);


        frame.setMinimumSize(new Dimension(400,300));
        frame.setPreferredSize(new Dimension(1000,750));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Allows the Swing App to be closed
        frame.addWindowListener(new ListenCloseWdw());

        // Add Menu listener
        mnuItemQuit.addActionListener(new ListenMenuQuit());
        mnuItemFit.addActionListener(new ListenMenuFit());
        mnuItemFull.addActionListener(new ListenMenuFull());
    }

    public static class ListenMenuQuit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static class ListenMenuFit implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            canvas.setPreferredSize(new Dimension(canP.getWidth(), canP.getHeight()));
            canP.add(canvas);
        }
    }
    public static class ListenMenuFull implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            canvas.setPreferredSize(new Dimension(800,600));
            canP.add(canvas);
        }
    }

    public static class ListenCloseWdw extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

}

