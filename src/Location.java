public final class Location {
    public final double x, y;

    /**
     * Constructs a new location
     * @param x
     * @param y
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if two locations are the same
     * @param o
     * @return true or false
     */
    public boolean equals(Object o) {
        return o instanceof Location
                && ((Location) o).x == x && ((Location) o).y == y;
    }


    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    /**
     * Distance between two objects
     * @param o
     * @return distance
     */
    public double distance(Location o) {
        return Math.sqrt(Math.pow(o.x - x, 2) + Math.pow(o.y - y, 2));
    }
}
