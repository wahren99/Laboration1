package vehicles.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import vehicles.Direction;
import vehicles.Location;
import vehicles.NormalCar;
import vehicles.models.Volvo240;

import java.awt.Color;

class Volvo240Test {
    @Test
    void testMoveForward() {
        NormalCar car = new Volvo240();
        // Check that default direction matches what we expect
        assertEquals(Direction.NORTH, car.getDirection());

        // Try to move forward
        car.startEngine();
        car.gas(1);
        car.move();
        assertTrue(car.getLocation().getY() > 0);
    }

    @Test
    void testTurning() {
        NormalCar car = new Volvo240();
        assertEquals(Direction.NORTH, car.getDirection());
        car.turnLeft();
        assertEquals(Direction.WEST, car.getDirection());
        for (int i = 0; i < 3; ++i) car.turnRight();
        assertEquals(Direction.SOUTH, car.getDirection());
    }

    @Test
    void testGasing() {
        NormalCar car = new Volvo240();
        car.startEngine();

        // Momma's gonna gas this thing
        car.gas(1);
        car.move();
        assertEquals(new Location(0, (1 * 1.25)), car.getLocation());
    }

    @Test
    void testBraking() {
        NormalCar car = new Volvo240();
        car.startEngine();

        car.gas(1);
        car.brake(1);
        car.brake(1);
        car.brake(1);

        car.move();
        // Assert that we are back to baby speeds
        assertEquals(0, car.getLocation().y, 1e-3);
    }

    @Test
    void testDefaultValues() {
        NormalCar car = new Volvo240();
        assertEquals(4, car.getNrOfDoors());
        // assertEquals(100, car.getEnginePower());
        assertEquals(Color.BLACK, car.getColor());
        assertEquals(new Location(0, 0), car.getLocation());
        assertEquals(Direction.NORTH, car.getDirection());
    }

    @Test
    void testSetColor() {
        NormalCar car = new Volvo240();
        car.setColor(Color.YELLOW); // Turn her into a Lamborghini
        assertEquals(Color.YELLOW, car.getColor());
    }
}