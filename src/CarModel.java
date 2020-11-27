import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Representation of the state of the car simulation.
 */
public class CarModel {
    /** A list of cars. */
    private List<Vehicle> cars = new ArrayList<>();
    /** Listener that gets notified when the model changes. */
    private final UpdateListener listener;

    /** Reference to the Saab. */
    private final Saab95 saab;
    /** Reference to the Scania. */
    private final Scania scania;

    private final double width, height;

    /**
     * Constructs the carModel
     *
     * @param listener Callback that fires when the model changes.
     * @param width
     * @param height
     */
    public CarModel(UpdateListener listener, double width, double height) {
        Volvo240 volvo = new Volvo240();
        volvo.setLocation(new Location(0, 0 * 100));
        cars.add(volvo);

        saab = new Saab95();
        saab.setLocation(new Location(0, 1 * 100));
        cars.add(saab);

        scania = new Scania();
        cars.add(scania);
        scania.setLocation(new Location(0, 2 * 100));

        this.listener = listener;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns an immutable view of the cars.
     *
     * @return The vehicles in this model.
     */
    public Collection<Vehicle> getVehicles() {
        return Collections.unmodifiableCollection(cars);
    }

    /**
     * Moves all cars and turns them 180 degrees right before they collide with the walls.
     */
    public void tick() {
        for (Vehicle car : cars) {
            car.move();

            Location oldLocation = car.getLocation();

            // FIXME
            if (car.getLocation().getY() < 0) {
                car.setLocation(new Location(oldLocation.getX(), 0));

                car.turnLeft();
                car.turnLeft();
            } else if (car.getLocation().getY() + 60 > height) {
                car.setLocation(new Location(oldLocation.getX(), height-60));
                car.turnLeft();
                car.turnLeft();
            }
        }
        listener.onUpdate();
    }

    /**
     * Starts engine of all vehicles
     */
    void startEngine() {
        cars.forEach(Vehicle::startEngine);
        listener.onUpdate();
    }

    /**
     * Stops vehicles of all engines
     */
    void stopEngine() {
        cars.forEach(Vehicle::stopEngine);
        listener.onUpdate();
    }

    /**
     * Calls the gas method for each car once.
     *
     * @param amount The amount by which to gas.
     */
    void gas(int amount) {
        double gas = amount / 100.0;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
        listener.onUpdate();
    }

    /**
     * Calls the brake method for each car once.
     *
     * @param amount The amount by which to brake.
     */
    void brake(int amount) {
        double brake = amount / 100.0;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
        listener.onUpdate();
    }

    /**
     * Turns turbo on
     */
    void turnTurboOn() {
        saab.setTurboOn();
        listener.onUpdate();
    }

    /**
     * Turns turbo off
     */
    void turnTurboOff() {
        saab.setTurboOff();
        listener.onUpdate();
    }

    /**
     * Lifts the truckbed
     */
    void liftTruckBed(){
        scania.addTruckBedAngle(70f);
        listener.onUpdate();
    }

    /**
     * Lower truckbred
     */
    void lowerTruckBed(){
        scania.addTruckBedAngle(-70f);
        listener.onUpdate();
    }
}
