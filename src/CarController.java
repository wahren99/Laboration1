import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.stream.Collectors;

/**
* This class represents the Controller part in the MVC pattern.
 *
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements UpdateListener {
    // member fields:

    /** The delay (ms) corresponds to 20 updates a sec (hz). */
    private final int delay = 50;
    /**
     * The timer is started with an listener (see below) that executes the statements
     * each step between delays.
     */
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    // CarView frame;

    private final CarModel model;
    final ViewModel viewModel;

    private final SpriteVehicleVisitor spriteVehicleVisitor = new SpriteVehicleVisitor();

    /**
     * Constructs a new car controller.
     */
    public CarController() {
        // TODO get these width/height values from somewhere else
        model = new CarModel(this, CarView.X, CarView.Y - 240);
        viewModel = new ViewModel(renderModel());
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();



        // Start a new view and send a reference of self
        CarView view = new CarView("CarSim 2.0 - 1337 edition", cc, cc.viewModel);

        // Start the timer
        cc.timer.start();
    }

    /**
     * Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.tick();
        }
    }

    /**
     * Returns a list of sprites that represents the model.
     *
     * @return The rendered model.
     */
    private Iterable<CarSprite> renderModel() {
        return model.getVehicles().stream().map(vehicle -> {
            Location location = vehicle.getLocation();
            Point pos = new Point((int) location.getX(), (int) location.getY());

            BufferedImage image = vehicle.accept(spriteVehicleVisitor);

            return new CarSprite(pos, image);
        }).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Updates the view model after the model changed.
     */
    @Override
    public void onUpdate() {
        // Update ViewModel from Model
        viewModel.update(renderModel());
    }

    void startEngine() {
        model.startEngine();
    }

    void stopEngine() {
        model.stopEngine();
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        model.gas(amount);
    }

    // Calls the gas method for each car once
    void brake(int amount) {
        model.brake(amount);
    }

    void turnTurboOn() {
        model.turnTurboOn();
    }

    void turnTurboOff() {
        model.turnTurboOff();
    }

    void liftTruckBed(){
        model.liftTruckBed();
    }
    void lowerTruckBed(){
        model.lowerTruckBed();
    }
}
