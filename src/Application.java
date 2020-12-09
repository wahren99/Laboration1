import java.util.ArrayList;
import java.util.Collection;

public class Application {
    public static void main(String[] args) {
        Collection<Vehicle> vehicles = new ArrayList<>() {{

            Vehicle volvo = VehicleFactory.createVolvo();
            volvo.setLocation(new Location(0, 0 * 100));
            add(volvo);

            Vehicle saab = VehicleFactory.createSaab95();
            saab.setLocation(new Location(0, 1 * 100));
            add(saab);

            Vehicle scania = VehicleFactory.createScania();
            scania.setLocation(new Location(0, 2 * 100));
            add(scania);
        }};


        CarModel model = new CarModel(vehicles);

        // Instance of this class
        CarController cc = new CarController(model);

        // Start a new view and send a reference of self
        CarView view = new CarView("CarSim 2.0 - 1337 edition", model);


        cc.run();
    }
}
