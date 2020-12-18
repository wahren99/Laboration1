package vehicles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import vehicles.RepairShop;
import vehicles.models.VehicleFactory;
import vehicles.models.Volvo240;

public class RepairShopTest {

    @Test
    void testMaxCapacity(){
        RepairShop<Volvo240> repairShop = new RepairShop<>(4);
        // Fill her up
        for (int i = 0; i < 4; ++i) {
            Volvo240 car = (Volvo240) VehicleFactory.createVolvo();
            repairShop.leaveCar(car);

        }

        assertThrows(IllegalStateException.class, () -> {
            Volvo240 theLastCar = (Volvo240) VehicleFactory.createVolvo();
            repairShop.leaveCar(theLastCar);
        });
    }
}
