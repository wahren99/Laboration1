package DIT953.polygons.polygon;

/*
 * SRP - Single Responsibility Principle,Alla ska bara ha 1 jobb att göra
 * OCP
 *Liskov substitution principle
 *ISP - Interface segregation principle
 *DIP
 */

public class PolygonFactory {
    public static Polygon createTriangle(int x, int y) {
        return new Triangle(x, y);
    }
    public static Polygon createSquare(int x, int y) {
        return new Square(x, y);
    }
    public static Polygon createRectangle(int x, int y) {
        return new Rectangle(x, y);
    }
}
