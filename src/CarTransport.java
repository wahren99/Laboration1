import java.awt.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Nice car carrier without roof for the transport.
 */
public class CarTransport extends Truck<CarTransport.CarRamp> {
    /** The maximum length of the carriage. */
    private static final float MAX_LOADED_CARS_LENGTH = 10;

    private final Stack<Vehicle> loadedCars = new Stack<>();

    /**
     * Constructs an instance of a Car Transport
     */
    public CarTransport() {
        super(2, 200, Color.CYAN, "CarTransporter2000HyrbidMaximumPower360Transport", 2 + MAX_LOADED_CARS_LENGTH, new CarRamp());
    }

    /**
     * the speedfactor, how fast our transport is.
     * @return the speedfactor
     */
    @Override
    protected double speedFactor() {
        return getEnginePower() * /* this fella go wroom */ 10000;
    }

    /**
     * Loads car onto the car transport.
     * @throws IllegalStateException if one tries to load the car transport on to itself
     * @throws IllegalStateException if car ramp is up
     * @throws IllegalArgumentException if car is to far away
     * @param car
     */
    public void loadCar(Vehicle car) {
        if (car == this)
            throw new IllegalArgumentException("Loading itself would cause bad singularity.");
        if (getTruckBed().getStatus() != CarRamp.Status.DOWN)
            throw new IllegalStateException("Ramp is not lowered!");

        if (getLocation().distance(car.getLocation()) > 2)
            throw new IllegalArgumentException("Too far away!");

        if (loadedCars.stream().mapToDouble(Vehicle::getLength).sum() + car.getLength() > MAX_LOADED_CARS_LENGTH)
            throw new IllegalArgumentException("That car plus the others be a too long boy!");

        loadedCars.push(car);
    }

    /**
     * Unloads a Car. Checks that ramp is lowered.
     * @return the unloaded Car.
     */
    public Vehicle unloadCar() {
        if (getTruckBed().getStatus() != CarRamp.Status.DOWN)
            throw new IllegalStateException("Ramp is not lowered!");

        return loadedCars.pop();

    }
    /**
     * Moves the CarTransport.
     */
    @Override
    public void move() {
        super.move();
        // Loaded cars stick to our position
        loadedCars.forEach((car -> {
            car.setLocation(getLocation());
        }));
    }

    /**
     * Class CarRamp that implements Truckbed
     */
    public static final class CarRamp implements TruckBed {
        /**
         * Status of ramp can either be up or down
         */
        public enum Status {
            UP, DOWN
        }

        /** Status of ramp **/
        private final Status status;

        /** constructs car ramp **/
        public CarRamp(Status status) {
            this.status = status;
        }
        /** Sets up as default position of ramp **/
        public CarRamp() {
            this(Status.UP);
        }

        /**
         * @return status of ramp
         */
        public Status getStatus() {
            return status;
        }

        @Override
        public boolean allowedToDrive() {
            return switch (status) {
                case UP -> true;
                case DOWN -> false;
            };
        }

        /**
         * Implements Updater. Sets the status of the ramp.
         */
        public static final Updater<CarRamp, Status> setStatus = (truckBed, value) -> {
            if (value == null) throw new IllegalArgumentException("Ramp status cannot be null ya dumb dumb");
            return new CarRamp(value);
        };
    }
}
