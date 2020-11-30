package DIT953.polygons.polygon;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Niklas on 2016-01-19.
 */
abstract class BasePolygon extends JComponent implements Polygon {
    public Point centerPoint;

    public BasePolygon(Point center){
        this.centerPoint = center;
    }
    public BasePolygon(int x, int y){
        this(new Point(x,y));
    }

    @Override
    public Point getCenter() {
        return centerPoint;
    }

    @Override
    public void setCenter(int x, int y){
        this.centerPoint = new Point(x,y);
    }

    @Override
    public abstract void paint(Graphics g)//paint
    ;
}
