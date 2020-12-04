import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* This class represents the Controller part in the MVC pattern.
 *
 * It's responsibilities is to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController {
    // member fields:

    /** The delay (ms) corresponds to 20 updates a sec (hz). */
    private final int delay = 50;
    /**
     * The timer is started with an listener (see below) that executes the statements
     * each step between delays.
     */
    private final Timer timer = new Timer(delay, new TimerListener());

    private final CarModel model;

    /**
     * Constructs a new car controller.
     */
    public CarController(CarModel model) {
        this.model = model;
    }

    //methods:

    /**
     * Starts the timer.
     */
    public void run() {
        timer.start();
    }

    /**
     * Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.tick();
        }
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
