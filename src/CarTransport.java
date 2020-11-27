import java.awt.*;

/**
 * Nice earthbound or aquatic car carrier without roof for the transport.
 *
 * @param <T> The type of things transportable by this car transport.
 */
public class CarTransport<T extends Vehicle & Transportable> implements Vehicle, Transporter<T>{
    /** The base vehicle for delegation. */
    private final BaseVehicle base;
    /** The car ramp where cars drive up and are stored. */
    private final CarRamp<T> platform;

    /**
     * Constructs an instance of a Car Transport.
     *
     * @param modelName The name of the model of car transport.
     * @param carStorage The way of storing cars loaded onto this transport.
     **/
    public CarTransport(String modelName, CarRamp.CarStorage<T> carStorage) {
        base = new BaseVehicle(200, Color.CYAN, modelName, 2 + CarRamp.MAX_LOADED_CARS_LENGTH) {
            @Override
            protected double speedFactor() {
                return getEnginePower() * /* this fella go wroom */ 10000;
            }
        };
        platform = new CarRamp<>(carStorage);
    }

    /**
     * Sets the up-or-down status of the car ramp.
     *
     * @throws IllegalStateException if the car ramp status is changed when the transport is moving
     * @param status The new status of the car ramp.
     */
    public void setPlatformStatus(CarRamp.Status status) {
        if (!isStationary() || base.isEngineOn())
            throw new IllegalStateException("Cannot change truck bed when not stationary dum dum.");
        platform.setStatus(status);
    }

    @Override
    public void startEngine() {
        if (!platform.allowedToDrive())
            throw new IllegalStateException("Cannot drive with your truck bed down silly");
        base.startEngine();
    }

    @Override
    public void move() {
        base.move();
        // Loaded cars stick to our position
        platform.getLoadedCars().forEach(car -> car.setLocation(getLocation()));
    }

    /**
     * Loads the specified vehicle onto this transport.
     *
     * @throws IllegalStateException if one tries to load the transporter on to itself
     * @throws IllegalArgumentException if the thing is to far away from this transport
     * @throws IllegalArgumentException if the car is too far from this transporter
     * @param vehicle The vehicle to load onto this transporter.
     */
    @Override
    public void loadThing(T vehicle) {
        if (vehicle == this)
            throw new IllegalArgumentException("Attempting to load this transporter onto itself would case very bad singularity indeed");
        if (getLocation().distance(vehicle.getLocation()) > 2)
            throw new IllegalArgumentException("Too far away!");
        platform.loadThing(vehicle);
    }

    @Override
    public T unloadThing() {
        return platform.unloadThing();
    }

    @Override
    public void turnLeft() {
        base.turnLeft();
    }

    @Override
    public void turnRight() {
        base.turnRight();
    }

    @Override
    public double getLength() {
        return base.getLength();
    }

    @Override
    public Color getColor() {
        return base.getColor();
    }

    @Override
    public double getCurrentSpeed() {
        return base.getCurrentSpeed();
    }

    @Override
    public void gas(double amount) {
        base.gas(amount);
    }

    @Override
    public void brake(double amount) {
        base.brake(amount);
    }

    @Override
    public void stopEngine() {
        base.stopEngine();
    }

    @Override
    public Location getLocation() {
        return base.getLocation();
    }

    @Override
    public void setLocation(Location location) {
        base.setLocation(location);
    }

    @Override
    public Direction getDirection() {
        return base.getDirection();
    }
}
