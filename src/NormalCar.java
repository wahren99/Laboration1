import java.awt.*;

/** A person car of some sort. */
public abstract class NormalCar extends BaseVehicle {
    /**
     * Constructs a new stationary unpowered Car.
     *
     * @param enginePower The power of the engine.
     * @param color The initial color of the car.
     * @param modelName The name of the model of the car.
     */
    public NormalCar(double enginePower, Color color, String modelName, double length) {
        super(enginePower, color, modelName, length);
    }

    /**
     * Returns the number of doors of this car.
     *
     * @return The number of doors.
     */
    public abstract int getNrOfDoors();
}
