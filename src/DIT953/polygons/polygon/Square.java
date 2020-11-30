package DIT953.polygons.polygon;

import java.awt.*;

/**
 * Created by Niklas on 2016-01-19.
 */
class Square extends BasePolygon {
    public Square(int x, int y) {
        super(x,y);
    }

   @Override
    public void paint(Graphics g){
        g.drawRect(centerPoint.x - 10, centerPoint.y - 10, 20, 20);
    }
}
