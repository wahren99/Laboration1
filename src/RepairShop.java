import java.util.ArrayList;
import java.util.List;

public class RepairShop<T extends NormalCar> {

    private final int maxCars;
    private final List<T> servicedCars = new ArrayList<>();

    public RepairShop(int maxCars){
        this.maxCars = maxCars;
    }

    public T retrieveCar(T car) {
        if (!servicedCars.remove(car))
            throw new IllegalArgumentException("That car is serviced by another shop!");
        return car;
    }

    public void leaveCar(T car){
        if (servicedCars.size() + 1 > maxCars)
            throw new IllegalStateException("This repair shop is full!");
        servicedCars.add(car);
    }
}
