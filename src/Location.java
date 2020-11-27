/** A location in a grid. */
public final class Location {
    /** The x-coordinate. */
    public final double x,
            /** The y-coordinate. */
            y;

    /**
     * Constructs a new location.
     *
     * @param x The initial x-coordinate.
     * @param y The initial y-coordinate.
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this location.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this location.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Checks if two locations are the same.
     *
     * @param o Another object.
     * @return True if the two objects are the same, otherwise false.
     */
    public boolean equals(Object o) {
        return o instanceof Location
                && ((Location) o).x == x && ((Location) o).y == y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    /**
     * Euclidean distance between this locations and some other.
     *
     * @param o The other location.
     * @return The distance between them.
     */
    public double distance(Location o) {
        return Math.sqrt(Math.pow(o.x - x, 2) + Math.pow(o.y - y, 2));
    }
}
