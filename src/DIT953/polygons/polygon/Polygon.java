package DIT953.polygons.polygon;

import java.awt.*;

public interface Polygon {
    Point getCenter();

    void setCenter(int x, int y);

    void paint(Graphics g);
}
