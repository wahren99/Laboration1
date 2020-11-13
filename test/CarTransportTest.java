import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CarTransportTest {

    @Test
    void testLoadCar() {
        CarTransport carTransport = new CarTransport();
        Volvo240 volvo240 = new Volvo240();
        volvo240.startEngine();
        volvo240.gas(1);
        volvo240.gas(1);
        volvo240.gas(1);
        for(int i=0; i<20; i++){
            volvo240.move();
        }

        assertTrue(volvo240.getLocation().distance(carTransport.getLocation()) > 2);
        carTransport.changeTruckBed(CarTransport.CarRamp.setStatus, CarTransport.CarRamp.Status.DOWN);
        assertThrows(IllegalArgumentException.class, () -> {
                    carTransport.loadCar(volvo240);
                });


    }

    @Test
    void testMoveWithLoadedCar() {
        CarTransport carTransport = new CarTransport();
        Saab95 saab = new Saab95();
        carTransport.changeTruckBed(CarTransport.CarRamp.setStatus, CarTransport.CarRamp.Status.DOWN);
        carTransport.loadCar(saab);
        carTransport.changeTruckBed(CarTransport.CarRamp.setStatus, CarTransport.CarRamp.Status.UP);
        carTransport.startEngine();
        carTransport.move();
        assertEquals(saab.getLocation(), new Location(0,0.1));
        assertEquals(saab.getLocation(),carTransport.getLocation());
    }

    @Test
    void testMaxNumberOfLoadedCarsThatCanPossiblyFitOnTheTruckAtTheSameTimeWithoutHangingOverTheEdge() {
        CarTransport carTransport = new CarTransport();
        carTransport.changeTruckBed(CarTransport.CarRamp.setStatus, CarTransport.CarRamp.Status.DOWN);

        assertThrows(IllegalArgumentException.class, () -> {
            for (int i = 0; i < /* this many cars should NOT fit */ 200; ++i) {
                Volvo240 volvo240 = new Volvo240();
                carTransport.loadCar(volvo240);
            }
        });
    }
}
