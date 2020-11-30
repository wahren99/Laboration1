package DIT953.polygonshapes;

import DIT953.polygons.polygon.Polygon;

public class PolygonFactory {
    public static Polygon createTriangle(int x, int y) {
        return new ShapesAdapter(new DIT953.shapes.Triangle(x, y));
    }
    public static Polygon createSquare(int x, int y) {
        return new ShapesAdapter(new DIT953.shapes.Rectangle(x, y, 2, 2));
    }
    public static Polygon createRectangle(int x, int y) {
        return new ShapesAdapter(new DIT953.shapes.Rectangle(x, y, 4, 2));
    }
}
