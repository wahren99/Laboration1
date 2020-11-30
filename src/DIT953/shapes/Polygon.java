package DIT953.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 2016-02-17.
 */
public abstract class Polygon extends Shape {

    protected Polygon(int x, int y){ super(x,y); }

    public void paint(Graphics g){
        List<Point> corners = getCorners();
        // first and last point should be the same
        corners.add(corners.get(0));
        Point from = null;
        for (Point to : corners){
            if (from != null){
                g.drawLine(from.x, from.y, to.x, to.y);
            }
            from = to;
        }

    }

    protected abstract int[][] getOffsets();

    // Template method pattern?
    private List<Point> getCorners() {
        // Find the data for this particular polygon
        Point center = getCenterPoint();
        int[][] offsets = getOffsets();
        double alpha = Math.toRadians(getRotation());
        // Calculate points based on that data
        return calculateCornerPoints(center, offsets, alpha);
    }

    /* Everything below this point is completely independent of any
    *  particular polygon. You can tell by the fact that the code does not
    *  mention any members (fields or methods) from the object itself.
    *  Hence we have chosen during refactoring to make these parts static. */

    private static List<Point> calculateCornerPoints(Point center, int[][] offsets, double alpha) {
        List<Point> corners = new ArrayList<>(offsets.length) ;
        for (int[] pointOffsets : offsets) {
            Point newCorner = calculateCornerPoint(center, pointOffsets, alpha);
            corners.add(newCorner);
        }
        return corners;
    }
    private static Point calculateCornerPoint(Point center, int[] offset, double alpha) {
        return rotatePoint(center, alpha,
                    movePoint(center, offset));
    }
    private static Point movePoint(Point center, int[] offset) {
        return new Point(center.x+ offset[0], center.y+ offset[1]);
    }
    private static Point rotatePoint(Point center, double alpha, Point newCorner) {
        double newX = center.x + (newCorner.x - center.x) * Math.cos(alpha) - (newCorner.y - center.y) * Math.sin(alpha);
        double newY = center.y + (newCorner.x - center.x) * Math.sin(alpha) + (newCorner.y - center.y) * Math.cos(alpha);
        return new Point((int) newX, (int) newY);
    }

}
