import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RepairShopTest {

    @Test
    void testMaxCapacity(){
        RepairShop<Volvo240> repairShop = new RepairShop<>(4);
        // Fill her up
        for (int i = 0; i < 4; ++i) {
            Volvo240 car = new Volvo240();
            repairShop.leaveCar(car);

        }

        assertThrows(IllegalStateException.class, () -> {
            Volvo240 theLastCar = new Volvo240();
            repairShop.leaveCar(theLastCar);
        });
    }
}
