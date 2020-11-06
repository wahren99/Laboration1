/** A movable thing that can move in the four cardinal directions. */
public interface Movable {
    /** Moves forward in the current direction with the current speed. */
    void move();
    /** Turns 90 degrees left on the spot. */
    void turnLeft();
    /** Turns 90 degrees right on the spot. */
    void turnRight();
}
