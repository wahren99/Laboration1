package vehicles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* This class represents the Controller part in the MVC pattern.
 *
 * It's responsibilities is to wait for time to pass and responds in an appropriate manner by
 * notifying the Model.
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
}
