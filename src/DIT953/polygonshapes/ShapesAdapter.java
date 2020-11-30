package DIT953.polygonshapes;

import DIT953.polygons.polygon.Polygon;
import DIT953.shapes.Shape;

import java.awt.*;

public class ShapesAdapter implements Polygon {
    private final Shape adaptee;

    public ShapesAdapter(Shape adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public Point getCenter() {
        return adaptee.getCenterPoint();
    }

    @Override
    public void setCenter(int x, int y) {
        Point oldCenter = getCenter();
        adaptee.translate(x - oldCenter.x, y - oldCenter.y);
    }

    @Override
    public void paint(Graphics g) {
        adaptee.paint(g);
    }
}
