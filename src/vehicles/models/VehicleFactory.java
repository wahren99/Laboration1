package vehicles.models;

import vehicles.NormalCar;
import vehicles.Vehicle;

/**
 * Class for creating concrete vehicles.
 */
public class VehicleFactory {
    public static NormalCar createVolvo() {
        return new Volvo240();
    }

    public static NormalCar createSaab95() {
        return new Saab95();
    }

    public static Vehicle createScania() {
        return new Scania();
    }
}
