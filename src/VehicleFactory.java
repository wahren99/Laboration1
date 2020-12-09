/**
 * Class for creating concrete vehicles.
 */
public class VehicleFactory {
    public static Vehicle createVolvo() {
        return new Volvo240();
    }

    public static Vehicle createSaab95() {
        return new Saab95();
    }

    public static Vehicle createScania() {
        return new Scania();
    }
}
