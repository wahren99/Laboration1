import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Stack;

/**
 * Class CarRamp that can load cars.
 */
public final class CarRamp<T extends Vehicle & Transportable> implements AdjustablePlatform, Transporter<T> {
    /**
     * Status of ramp can either be up or down.
     */
    public enum Status {
        /** The ramp is up. */
        UP,
        /** The ramp is down. Cars can drive on up. */
        DOWN
    }

    /**
     * A way of storing cars.
     */
    public interface CarStorage<T extends Vehicle> extends Collection<T> {
        /**
         * Adds the car to this car storage. It is now added.
         *
         * @param car The car to add.
         */
        void addCar(T car);

        /**
         * Removes a car from this car storage.
         *
         * @return The removed car.
         */
        T removeCar();
    }


    /** A car storage where cars are removed in last-in first-out order. */
    public static final class LifoCarStorage<T extends Vehicle> extends Stack<T> implements CarStorage<T> {
        @Override
        public void addCar(T car) {
            push(car);
        }

        @Override
        public T removeCar() {
            return pop();
        }
    }

    /** A car storage where cars are removed in first-in first-out order. */
    public static final class FifoCarStorage<T extends Vehicle> extends ArrayDeque<T> implements CarStorage<T> {
        @Override
        public void addCar(T car) {
            add(car);
        }

        @Override
        public T removeCar() {
            return remove();
        }
    }

    /** The maximum length of the carriage. */
    public static final float MAX_LOADED_CARS_LENGTH = 10;

    /** Status of ramp **/
    private Status status;
    /** Collection of currently loaded cars. */
    private final CarStorage<T> loadedCars;

    /**
     * Constructs car ramp.
     *
     * @param status The initial status of the car ramp.
     * @param storage The car storage implementation to use for this car ramp.
     */
    public CarRamp(Status status, CarStorage<T> storage) {
        this.status = status;
        this.loadedCars = storage;
    }
    /** Sets up as default position of ramp. **/
    public CarRamp(CarStorage<T> storage) {
        this(Status.UP, storage);
    }

    /**
     * Returns the up-down status of the ramp.
     *
     * @return Whether the ramp is up or down.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets whether the ramp is up or down.
     *
     * @param status The new status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean allowedToDrive() {
        return switch (status) {
            case UP -> true;
            case DOWN -> false;
        };
    }

    /**
     * Returns an iterator of the currently loaded cars.
     * @return The currently loaded cars.
     */
    public Iterable<T> getLoadedCars() {
        return loadedCars;
    }

    @Override
    public void loadThing(T car) {
        if (getStatus() != CarRamp.Status.DOWN)
            throw new IllegalStateException("Ramp is not lowered!");

        if (loadedCars.stream().mapToDouble(Vehicle::getLength).sum() + car.getLength() > MAX_LOADED_CARS_LENGTH)
            throw new IllegalArgumentException("That car plus the others be a too long boy!");

        loadedCars.addCar(car);
    }

    @Override
    public T unloadThing() {
        if (getStatus() != CarRamp.Status.DOWN)
            throw new IllegalStateException("Ramp is not lowered!");

        return loadedCars.removeCar();
    }
}