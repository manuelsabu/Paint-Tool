import java.awt.*;
import java.util.ArrayList;

interface ViewInt{
    void update();
}

public class Model{
	// the data in the model, just a counter
    public int thickness = 1;
    public Image image;
    public int x, y, prevX, prevY;
    public Color bgColor = Color.WHITE;
    public Color strokeColor = Color.BLACK;
    public ArrayList<Stroke> strokes = new ArrayList<>();
    public ArrayList<ViewInt> views = new ArrayList<>();
    public Stroke curStroke;
    public int curpoint = 0;
    public boolean animate = false;

    public void startNewS(){
        curStroke = new Stroke(thickness, strokeColor);
        for (int i = curpoint; i < strokes.size();) {
            strokes.remove(i);
        }
        strokes.add(curStroke);
        curpoint = strokes.size();
    }

    public void endS(){
        //this.printPoints();
    }

    public void incrementCurPoint() {
        if (curpoint < strokes.size()) curpoint++;
        notifyViews();
    }

    public void addPoint(int x, int y){
        curStroke.addPoint(x,y);
        this.notifyViews();
    }

    public void registerView(ViewInt V1){
        views.add(V1);
    }

    public void notifyViews(){
        for(int i = 0; i < views.size(); ++i){
            views.get(i).update();
        }
    }

    public void setBrushThickness(int thick){
        if(thick >= 1 && thick <= 10){
            thickness = thick;
            System.out.println("Model: Brush thickness changed to " + thickness);
            notifyViews();
        }
    }
}
