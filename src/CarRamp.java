import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * Class CarRamp that can load cars.
 */
public final class CarRamp implements AdjustablePlatform, Transporter<NormalCar> {
    /**
     * Status of ramp can either be up or down.
     */
    public enum Status {
        /** The ramp is up. */
        UP,
        /** The ramp is down. */
        DOWN
    }

    public interface CarStorage extends Collection<NormalCar> {
        void addCar(NormalCar car);

        NormalCar removeCar();
    }

    /** The maximum length of the carriage. */
    public static final float MAX_LOADED_CARS_LENGTH = 10;

    /** Status of ramp **/
    private Status status;

    private final CarStorage loadedCars;

    /** constructs car ramp **/
    public CarRamp(Status status, CarStorage storage) {
        this.status = status;
        this.loadedCars = storage;
    }
    /** Sets up as default position of ramp **/
    public CarRamp(CarStorage storage) {
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

    public Iterable<NormalCar> getLoadedCars() {
        return loadedCars;
    }

    @Override
    public void loadThing(NormalCar car) {
        if (getStatus() != CarRamp.Status.DOWN)
            throw new IllegalStateException("Ramp is not lowered!");

        if (loadedCars.stream().mapToDouble(Vehicle::getLength).sum() + car.getLength() > MAX_LOADED_CARS_LENGTH)
            throw new IllegalArgumentException("That car plus the others be a too long boy!");

        loadedCars.addCar(car);
    }

    @Override
    public NormalCar unloadThing() {
        if (getStatus() != CarRamp.Status.DOWN)
            throw new IllegalStateException("Ramp is not lowered!");

        return loadedCars.removeCar();
    }

    /**
     * Implements Updater. Sets the status of the ramp.
     */
    public static final Updater<CarRamp, Status> setStatus = (truckBed, value) -> {
        if (value == null) throw new IllegalArgumentException("Ramp status cannot be null ya dumb dumb");
        return new CarRamp(value, truckBed.loadedCars);
    };
}