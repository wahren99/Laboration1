import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CarTransportTest {
    @Test
    void testLoadCar() {
        CarTransport<NormalCar> carTransport = new CarTransport<>("Trailer", new CarRamp.LifoCarStorage<NormalCar>());
        Volvo240 volvo240 = new Volvo240();
        volvo240.startEngine();
        volvo240.gas(1);
        volvo240.gas(1);
        volvo240.gas(1);
        for(int i=0; i<20; i++){
            volvo240.move();
        }

        assertTrue(volvo240.getLocation().distance(carTransport.getLocation()) > 2);
        carTransport.setPlatformStatus(CarRamp.Status.DOWN);
        assertThrows(IllegalArgumentException.class, () -> {
            carTransport.loadThing(volvo240);
        });
    }

    @Test
    void testMoveWithLoadedCar() {
        CarTransport<NormalCar> carTransport = new CarTransport<>("Trailer", new CarRamp.LifoCarStorage<NormalCar>());
        Saab95 saab = new Saab95();
        carTransport.setPlatformStatus(CarRamp.Status.DOWN);
        carTransport.loadThing(saab);
        carTransport.setPlatformStatus(CarRamp.Status.UP);
        carTransport.startEngine();
        carTransport.move();
        assertEquals(saab.getLocation(), new Location(0,0.1));
        assertEquals(saab.getLocation(),carTransport.getLocation());
    }

    @Test
    void testMaxNumberOfLoadedCarsThatCanPossiblyFitOnTheTruckAtTheSameTimeWithoutHangingOverTheEdge() {
        CarTransport<NormalCar> carTransport = new CarTransport<>("Trailer", new CarRamp.LifoCarStorage<NormalCar>());
        carTransport.setPlatformStatus(CarRamp.Status.DOWN);

        assertThrows(IllegalArgumentException.class, () -> {
            for (int i = 0; i < /* this many cars should NOT fit */ 200; ++i) {
                Volvo240 volvo240 = new Volvo240();
                carTransport.loadThing(volvo240);
            }
        });
    }

    @Test
    void testFerry(){
        Ferry ferry = new Ferry();
        ferry.setPlatformStatus(CarRamp.Status.DOWN);
        NormalCar volvo = new Volvo240();
        ferry.loadThing(volvo);
        ferry.loadThing(new Saab95());
        // First thing outta this thing here's belly should be the volvo
        assertEquals(volvo, ferry.unloadThing());
    }
}
