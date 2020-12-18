package vehicles.models;

import org.junit.jupiter.api.Test;
import vehicles.models.Scania;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {
    @Test
    void testUpdateTruckBed() {
        Scania scania = new Scania();
        scania.addTruckBedAngle(30f);
        scania.addTruckBedAngle(-20f);
        assertEquals(10f, scania.getTruckBedAngle());

    }

    @Test
    void testCannotLowerTruckBedWhileDriving() {
        final Scania truck = new Scania();
        truck.startEngine();
        assertThrows(IllegalStateException.class, () -> {
            truck.setTruckBedAngle(30.0f);
        });
    }
}
