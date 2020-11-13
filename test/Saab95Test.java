import org.junit.jupiter.api.Test;

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

        saab2.move();
        assertEquals(new Location(0,0.1), saab2.getLocation());
    }
    @Test
    void testGasSpeed(){
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(1);
        assertEquals(1.35, saab.getCurrentSpeed());
    }
    @Test
    void testBrakeSpeed(){
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed());
    }

}