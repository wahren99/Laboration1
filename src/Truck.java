import java.awt.*;

public abstract class Truck<T extends TruckBed> extends BaseVehicle {
    /** The truck bed. */
    private T truckBed;

    /**
     * Constructs a new stationary unpowered Car.
     * @param nrDoors The number of doors.
     * @param enginePower The power of the engine.
     * @param color The initial color of the car.
     * @param modelName The name of the model of the car.
     */
    public Truck(int nrDoors, double enginePower, Color color, String modelName, double length, T truckBed) {
        super(nrDoors, enginePower, color, modelName, length);
        this.truckBed = truckBed;
    }

    /**
     * Returns the truck bed of this truck.
     *
     * @return The truck bed.
     */
    public T getTruckBed() {
        return truckBed;
    }

    /**
     * Updates the track according to the updater and the provided value.
     * @param updater The thing that will actually do the updating.
     * @param value The value to pass to the updater.
     * @param <U> The type parameter of the value given to the updater.
     */
    public final <U> void changeTruckBed(TruckBed.Updater<T, U> updater, U value) {
        if (!isStationary())
            throw new IllegalStateException("Cannot change truck bed when not stationary dum dum.");

        truckBed = updater.update(truckBed, value);
    }

    @Override
    public void startEngine(){
        if (!truckBed.allowedToDrive())
            throw new IllegalStateException("Cannot drive with your truck bed down silly");
        super.startEngine();
    }
}
