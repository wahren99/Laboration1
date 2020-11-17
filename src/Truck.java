import java.awt.*;

public abstract class Truck<T extends AdjustablePlatform> extends BaseVehicle {
    /** The truck bed. */
    private T platform;

    /**
     * Constructs a new stationary unpowered Car.
     * @param enginePower The power of the engine.
     * @param color The initial color of the car.
     * @param modelName The name of the model of the car.
     * @param platform The initial platform.
     */
    public Truck(double enginePower, Color color, String modelName, double length, T platform) {
        super(enginePower, color, modelName, length);
        this.platform = platform;
    }

    /**
     * Returns the truck bed of this truck.
     *
     * @return The truck bed.
     */
    public T getPlatform() {
        return platform;
    }

    /**
     * Updates the track according to the updater and the provided value.
     * @param updater The thing that will actually do the updating.
     * @param value The value to pass to the updater.
     * @param <U> The type parameter of the value given to the updater.
     */
    public final <U> void changePlatform(AdjustablePlatform.Updater<T, U> updater, U value) {
        if (!isStationary())
            throw new IllegalStateException("Cannot change truck bed when not stationary dum dum.");

        platform = updater.update(platform, value);
    }

    @Override
    public void startEngine(){
        if (!platform.allowedToDrive())
            throw new IllegalStateException("Cannot drive with your truck bed down silly");
        super.startEngine();
    }
}
