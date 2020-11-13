import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {
    @Test
    void testUpdateTruckBed() {
        Scania scania = new Scania();
        scania.changeTruckBed(Scania.ScaniaTruckBed.addAngle,30.0f);
        scania.changeTruckBed(Scania.ScaniaTruckBed.addAngle, -20f);
        assertEquals(10f, scania.getTruckBed().getAngle());

    }

    @Test
    void testCannotLowerTruckBedWhileDriving() {
        final Scania truck = new Scania();
        truck.startEngine();
        assertThrows(IllegalStateException.class, () -> {
            truck.changeTruckBed(Scania.ScaniaTruckBed.addAngle,30.0f);
        });
    }
}
