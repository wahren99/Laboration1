import java.awt.*;

/**
 * Any kind of old tumbler allowed on the roads.
 */
public interface Vehicle extends Movable {
    /**
     * Returns the current speed of the car.
     *
     * @return Current speed of the car.
     */
    double getCurrentSpeed();

    /**
     * Returns whether this vehicle is stationary.
     *
     * @return Whether the current speed is zero.
     */
    default boolean isStationary() {
        return getCurrentSpeed() == 0;
    }

    /**
     * Returns number of doors
     * @return number of doors
     */
    int getNrDoors();

    /**
     * Returns the color of the car
     * @return Color of the car
     */
    Color getColor();

    /**
     * Sets the current speed of the car to 0.1
     */
    void startEngine();

    /**
     * Stops the car by setting current speed to 0
     */
    void stopEngine();

    /**
     * Increases the speed of the car by the given amount.
     *
     * The argument @c amount must be in the interval [0, enginePower].
     *
     * @param amount The amount of speed increase.
     */
    void gas(double amount);

    /**
     * Decreases the speed of the car by the given amount.
     *
     * The argument @c amount must be in the interval [0, enginePower].
     *
     * @param amount The amount of speed decrease.
     */
    void brake(double amount);

    /**
     * returns the current point of position
     * @return The current position
     */
    Location getLocation();

    /**
     * Teleports the car to the specified location.
     *
     * We have assigned Gandalf for this task.
     *
     * @param location The new location.
     */
    void setLocation(Location location);

    /** Returns the direction we are currently headed.
     *
     * @return The current direction.
     */
    Direction getDirection();

    /**
     * Returns the length of this vehicle.
     *
     * @return Whether it is a big boy.
     */
    double getLength();
}
