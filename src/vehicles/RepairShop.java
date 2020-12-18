package vehicles;

import java.util.ArrayList;
import java.util.List;

/**
 * A business where people can come and leave their cars to get them repaired.
 *
 * @param <T> The concrete type of cars that may be repaired by this shop.
 */
public class RepairShop<T extends NormalCar> {
    /** maximum cars that fit in the repair shop **/
    private final int maxCars;
    /** list that contains all cars being serviced. **/
    private final List<T> servicedCars = new ArrayList<>();

    /**
     * Constructs a repair shop
     * @param maxCars the maximum amount of cars
     */
    public RepairShop(int maxCars){
        this.maxCars = maxCars;
    }

    /**
     * Retrieve the car from the repairshop
     * @param car the car
     * @return The car that was retrieved.
     */
    public T retrieveCar(T car) {
        /** @throws IllegalStateException if the car is serviced by another shop **/
        if (!servicedCars.remove(car))
            throw new IllegalArgumentException("That car is serviced by another shop!");
        return car;
    }

    /**
     * leaves a car at the repair shop
     * @param car the car to leave
     */
    public void leaveCar(T car){
        if (servicedCars.size() + 1 > maxCars)
            throw new IllegalStateException("This repair shop is full!");
        servicedCars.add(car);
    }
}
