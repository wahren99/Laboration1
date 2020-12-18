package vehicles;

import java.util.*;

/**
 * Class vehicles.CarRamp that can load cars.
 */
public interface CarRamp<T extends Vehicle & Transportable> extends AdjustablePlatform, Transporter<T> {
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
    float MAX_LOADED_CARS_LENGTH = 10;

    /**
     * Returns an iterator of the currently loaded cars.
     * @return The currently loaded cars.
     */
    CarStorage<T> getLoadedCars();

    class BaseCarRamp<T extends Vehicle> implements CarRamp<T> {
        /** Collection of currently loaded cars. */
        private final CarStorage<T> loadedCars;

        /**
         * Constructs a new unspecialized car ramp.
         *
         * @param storage The car storage implementation to use for this car ramp.
         */
        BaseCarRamp(CarStorage<T> storage) {
            this.loadedCars = storage;
        }

        @Override
        public CarStorage<T> getLoadedCars() {
            return loadedCars;
        }

        @Override
        public boolean allowedToDrive() {
            return true;
        }

        @Override
        public void loadThing(T car) {
            if (loadedCars.stream().mapToDouble(Vehicle::getLength).sum() + car.getLength() > MAX_LOADED_CARS_LENGTH)
                throw new IllegalArgumentException("That car plus the others be a too long boy!");

            loadedCars.addCar(car);
        }

        @Override
        public T unloadThing() {
            return loadedCars.removeCar();
        }
    }

    final class UpCarRamp<T extends Vehicle> implements CarRamp<T> {
        private final BaseCarRamp<T> base;

        public UpCarRamp(CarStorage<T> storage) {
            base = new BaseCarRamp<T>(storage);
        }

        @Override
        public boolean allowedToDrive() {
            return true;
        }

        @Override
        public CarStorage<T> getLoadedCars() {
            return base.getLoadedCars();
        }

        @Override
        public void loadThing(T thing) {
            throw new IllegalStateException("Ramp is not lowered!");
        }

        @Override
        public T unloadThing() {
            throw new IllegalStateException("Ramp is not lowered!");
        }
    }

    final class DownCarRamp<T extends Vehicle> implements CarRamp<T> {
        private final BaseCarRamp<T> base;

        public DownCarRamp(CarStorage<T> storage) {
            base = new BaseCarRamp<T>(storage);
        }

        @Override
        public boolean allowedToDrive() {
            return false;
        }

        @Override
        public CarStorage<T> getLoadedCars() {
            return base.getLoadedCars();
        }

        @Override
        public void loadThing(T thing) {
            base.loadThing(thing);
        }

        @Override
        public T unloadThing() {
            return base.unloadThing();
        }
    }
}