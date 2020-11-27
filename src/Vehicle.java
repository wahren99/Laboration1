import java.awt.*;

/**
 * Any kind of old tumbler allowed on the roads.
 */
public interface Vehicle extends Movable, Transportable {
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
     * Returns the color of the car
     * @return Color of the car
     */
    Color getColor();

    /**
     * Starts the engine.
     *
     * The vehicle cannot accelerate while the engine is off.
     */
    void startEngine();

    /**
     * Stops the engine.
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
     * Returns the current position of this vehicle.
     *
     * @return The current position.
     */
    Location getLocation();

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

    /**
     * Honks
     */
    default void honk() { System.out.println("HOONK HOONK!"); }
}
