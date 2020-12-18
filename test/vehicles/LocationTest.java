package vehicles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import vehicles.Location;

class LocationTest {
    @Test
    void testToString() {
        assertEquals("(1337.0, 420.0)", new Location(1337, 420).toString());
    }

    @Test
    void testEquals() {
        assertFalse(new Location(1, 2).equals(null));
        assertFalse(new Location(1, 2).equals(""));
        assertTrue(new Location(1, 2).equals(new Location(1, 2)));
        assertFalse(new Location(1, 2).equals(new Location(2, 2)));
        assertFalse(new Location(1, 2).equals(new Location(1, 1)));
    }
}
