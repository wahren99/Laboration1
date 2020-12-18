package vehicles;

import vehicles.models.Saab95;
import vehicles.models.Scania;
import vehicles.models.VehicleFactory;

import java.util.*;
import java.util.List;

/**
 * Representation of the state of the car simulation.
 */
public class CarModel {
    /** A list of cars. */
    private final List<Vehicle> cars;
    /** Listeners that gets notified when the model changes. */
    private final List<UpdateListener> listeners = new ArrayList<>();

    private static final double width = 600, height = 500,
        /** Width and height of each vehicle. */
        vehicleSize = 60;

    /** Maximum number of allowed cars in this simulation. */
    private static final int MAX_NR_VEHICLES = 10;

    /**
     * Constructs the carModel
     */
    public CarModel(Collection<Vehicle> vehicles) {
        if (vehicles.size() > MAX_NR_VEHICLES) throw new IllegalArgumentException("Too many cars dum dum");
        this.cars = new ArrayList<>(vehicles);
    }

    /**
     * @param listener Callback that fires when the model changes.
     */
    public void addListener(UpdateListener listener) {
        listeners.add(listener);
    }

    /**
     * notifies all listeners in our listener list.
     */
    private void notifyListeners() {
        listeners.forEach(UpdateListener::onUpdate);
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
     * Returns the width of the map that is the domain of the vehicles.
     *
     * @return The width of the playing area of the cars.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the heigth of the map that is the domain of the vehicles
     *
     * @return The width of the playing area of the cars.
     */
    public double getHeight() {
        return height;
    }

    public double getVehicleSize() {
        return vehicleSize;
    }

    /**
     * Moves all cars a small step forward in time.
     */
    public void tick() {
        for (Vehicle vehicle : cars) {
            vehicle.move();

            // Check if any vehicles are out of bounds. If so discipline them and move on,
            // i.e. turn them 180 degrees right before they collide with the walls.
            Location oldLocation = vehicle.getLocation();

            // Check the x-axis
            if (vehicle.getLocation().getX() < 0) {
                vehicle.setLocation(new Location(0, oldLocation.getY()));
                vehicle.turnAround();
            } else if (vehicle.getLocation().getX() + getVehicleSize() > getWidth()) {
                vehicle.setLocation(new Location(getWidth() - getVehicleSize(), oldLocation.getY()));
                vehicle.turnAround();
            }

            // Check the y-axis
            if (vehicle.getLocation().getY() < 0) {
                vehicle.setLocation(new Location(oldLocation.getX(), 0));
                vehicle.turnAround();
            } else if (vehicle.getLocation().getY() + getVehicleSize() > getHeight()) {
                vehicle.setLocation(new Location(oldLocation.getX(), getHeight() - getVehicleSize()));
                vehicle.turnAround();
            }
        }

        notifyListeners(); // Notify that model may have changed
    }

    /**
     * Starts engine of all vehicles
     */
    void startEngine() {
        cars.forEach(Vehicle::startEngine);
        notifyListeners();
    }

    /**
     * Stops vehicles of all engines
     */
    void stopEngine() {
        cars.forEach(Vehicle::stopEngine);
        notifyListeners();
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
        notifyListeners();
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
        notifyListeners();
    }

    /**
     * Turns turbo on
     */
    void turnTurboOn() {

        for (Vehicle vehicle : cars) {
            if (vehicle instanceof Saab95) {
                Saab95 saab = (Saab95) vehicle;
                saab.setTurboOn();
            }
        }
        notifyListeners();
    }

    /**
     * Turns turbo off
     */
    void turnTurboOff() {

        for (Vehicle vehicle : cars) {
            if (vehicle instanceof Saab95) {
                Saab95 saab = (Saab95) vehicle;
                saab.setTurboOff();
            }
        }
        notifyListeners();
    }

    /**
     * Lifts the truckbed
     */
    void liftTruckBed(){
        for (Vehicle vehicle : cars)
            if (vehicle instanceof Scania){
                Scania scania = (Scania) vehicle;
                scania.addTruckBedAngle(70f);
            }
        notifyListeners();
    }

    /**
     * Lower truckbred
     */
    void lowerTruckBed(){
        for (Vehicle vehicle : cars)
            if (vehicle instanceof Scania){
                Scania scania = (Scania) vehicle;
                scania.addTruckBedAngle(-70f);
            }
        notifyListeners();
    }

    public void removeCar() {
            if (cars.isEmpty()) return;

        Random random = new Random();
        cars.remove(random.nextInt(cars.size()));
    }

    public void addCar() {
        if (cars.size() >= MAX_NR_VEHICLES) {
            System.out.println("Too many cars!");
            return;
        }

        Random random = new Random();
        Vehicle choices[] = new Vehicle[] {
                VehicleFactory.createVolvo(),
                VehicleFactory.createSaab95(),
                VehicleFactory.createScania(),
        };
        Vehicle choice = choices[random.nextInt(choices.length)];
        choice.setLocation(new Location(random.nextDouble() * (getWidth() - getVehicleSize()),
                random.nextDouble() * (getHeight() - getVehicleSize())));
        for (int i = 0; i < random.nextInt(4); ++i) choice.turnRight();

        cars.add(choice);
    }
}
