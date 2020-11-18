import java.awt.*;

/**
 * Nice car carrier without roof for the transport.
 */
public class CarTransport implements Vehicle, Transporter<NormalCar>, Transportable {
    private final CarRamp platform;
    private final BaseVehicle base;

    /**
     * Constructs an instance of a Car Transport.
     */
    public CarTransport(String modelName, CarRamp.CarStorage carStorage) {
        base = new BaseVehicle(200, Color.CYAN, modelName, 2 + CarRamp.MAX_LOADED_CARS_LENGTH) {
            @Override
            protected double speedFactor() {
                return getEnginePower() * /* this fella go wroom */ 10000;
            }
        };
        platform = new CarRamp(carStorage);
    }

    public void setPlatformStatus(CarRamp.Status status) {
        if (!isStationary())
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
        platform.getLoadedCars().forEach(car -> {
            car.setLocation(getLocation());
        });
    }

    /**
     * Loads the specified thing onto this transport.
     *
     * @throws IllegalStateException if one tries to load the transporter on to itself
     * @throws IllegalArgumentException if the thing is to far away from this transport
     * @throws IllegalArgumentException if the car is too far from this transporter
     * @param car The thing to load onto this transporter.
     */
    @Override
    public void loadThing(NormalCar car) {
        if (getLocation().distance(car.getLocation()) > 2)
            throw new IllegalArgumentException("Too far away!");
        platform.loadThing(car);
    }

    @Override
    public NormalCar unloadThing() {
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
