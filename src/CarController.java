import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    List<Vehicle> cars = new ArrayList<>();

    Saab95 saab;
    Scania scania;

    public CarController() {
        Volvo240 volvo = new Volvo240();
        volvo.setLocation(new Location(0, 0 * 100));
        cars.add(volvo);

        saab = new Saab95();
        saab.setLocation(new Location(0, 1 * 100));
        cars.add(saab);

        scania = new Scania();
        cars.add(scania);
        scania.setLocation(new Location(0, 2 * 100));
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0 - 1337 edition", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();

                double width = frame.drawPanel.getWidth(),
                        height = frame.drawPanel.getHeight();

                Location oldLocation = car.getLocation();

                // FIXME
                if (car.getLocation().getY() < 0) {
                    car.setLocation(new Location(oldLocation.getX(), 0));

                    car.turnLeft();
                    car.turnLeft();
                } else if (car.getLocation().getY() + 60 > height) {
                    car.setLocation(new Location(oldLocation.getX(), height-60));
                    car.turnLeft();
                    car.turnLeft();
                }

                int x = (int) Math.round(car.getLocation().getX());
                int y = (int) Math.round(car.getLocation().getY());

                if (car instanceof Volvo240)
                frame.drawPanel.moveVolvo(x, y);
                else if (car instanceof Saab95)
                    frame.drawPanel.moveSaab(x, y);
                else if (car instanceof Scania)
                    frame.drawPanel.moveScania(x, y);

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    void startEngine() {
        cars.forEach(Vehicle::startEngine);
    }

    void stopEngine() {
        cars.forEach(Vehicle::stopEngine);
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = amount / 100.0;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    // Calls the gas method for each car once
    void brake(int amount) {
        double brake = amount / 100.0;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void turnTurboOn() {
        saab.setTurboOn();
    }

    void turnTurboOff() {
        saab.setTurboOff();
    }

    void liftTruckBed(){
        scania.addTruckBedAngle(70f);
    }
    void lowerTruckBed(){
        scania.addTruckBedAngle(-70f);
    }

}
