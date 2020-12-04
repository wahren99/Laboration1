public class Application {
    public static void main(String[] args) {

        CarModel model = new CarModel();

        // Instance of this class
        CarController cc = new CarController(model);

        // Start a new view and send a reference of self
        CarView view = new CarView("CarSim 2.0 - 1337 edition", cc, model);


        cc.run();
    }
}
