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

    /**
     * Constructs the carModel
     *
     * @param listener Callback that fires when the model changes.
     */
    public CarModel(UpdateListener listener) {
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
     * Moves all cars a small step forward in time.
     */
    public void tick() {
        for (Vehicle vehicle : cars) {
            vehicle.move();
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
