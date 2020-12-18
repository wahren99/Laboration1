package vehicles.models;

import org.junit.jupiter.api.Test;
import vehicles.models.Saab95;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    @Test
    void testSetTurboOn() {
        Saab95 saab = new Saab95();
        saab.setTurboOn();
        assertEquals(true, saab.getTurboOn());
    }

    @Test
    void testSetTurboOff() {
        Saab95 saab = new Saab95();
        saab.setTurboOff();
        assertFalse(saab.getTurboOn());
    }

    @Test
    void testMoveForward() {
        Saab95 saab2 = new Saab95();
        saab2.startEngine();
        saab2.gas(1);
        saab2.move();
        assertTrue(saab2.getLocation().getY() > 0);
    }
    @Test
    void testGasSpeed(){
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(1);
        assertEquals(1.25, saab.getCurrentSpeed());
    }

    @Test
    void testBrakeSpeed(){
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed());
    }

}