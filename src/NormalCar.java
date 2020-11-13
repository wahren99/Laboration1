import java.awt.*;

/** A car of some sort. */
public abstract class NormalCar extends BaseVehicle {

    /**
     * Constructs a new stationary unpowered Car.
     * @param nrDoors The number of doors.
     * @param enginePower The power of the engine.
     * @param color The initial color of the car.
     * @param modelName The name of the model of the car.
     */
    public NormalCar(int nrDoors, double enginePower, Color color, String modelName, double length) {
        super(nrDoors, enginePower, color, modelName, length);

    }




}
