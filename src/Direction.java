import java.awt.Point;

/**
 * The four cardinal directions.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Returns a new direction corresponding to turning 90 degrees left.
     * @return The direction left of this one.
     */
    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
    }

    /**
     * Returns a new direction corresponding to turning 90 degrees right.
     * @return The direction right of this one.
     */
    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    /**
     * Returns a unit vector in the given direction.
     * @return A point pointing in the direction.
     */
    public Point getDelta() {
        return switch (this) {
            case NORTH -> new Point(0, 1);
            case EAST -> new Point(1, 0);
            case SOUTH -> new Point(0, -1);
            case WEST -> new Point(-1, 0);
        };
    }
}
