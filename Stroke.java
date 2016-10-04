/**
 * Created by Manuel on 2016-02-29.
 */

import java.awt.*;
import java.util.ArrayList;

public class Stroke {
    public class Point {
        int x, y;

        public Point(int x1, int y1) {
            x = x1;
            y = y1;
        }
    }

    int thick;
    Color col;
    ArrayList<Point> points = new ArrayList<>();

    public Stroke(int t, Color C1 ){
        thick = t;
        col = C1;
    }

    public void addPoint(int x, int y){
        Point P = new Point(x,y);
        points.add(P);
    }

}
