package vehicles;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ButtonsController extends JPanel {
    private final CarModel model;

    /** The control panel with our buttons*/
    JPanel controlPanel = new JPanel();

    /** Panel for gassing controls. */
    JPanel gasPanel = new JPanel();

    /** Constructs a spinner, used to vary the gas amount.
     * The initial amount is 0.
     */
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    /*
     * constructs buttons used to gas, brake, change Saab turbo status,
     * to change vehicles.models.Scania bed status and to start/stop all cars
     */
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    public ButtonsController(CarModel model) {
        this.model = model;

        initComponents();
    }

    private void initComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        /*
         * sets minimum, maximum, and stepsize for the gas spinner.
         */

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        10);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        /*
         * edits layout of the gas panel.
         */
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        /*
         * sets layot of the control panel.
         * adds all the buttons and sets position.
         */

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        // controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        /*
         * sets color of the start button.
         */
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        // startButton.setPreferredSize(new Dimension(X/5-15,200));
        startButton.addActionListener(e -> model.startEngine());
        this.add(startButton);

        /*
         * sets color of the stop button.
         */

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        // stopButton.setPreferredSize(new Dimension(X/5-15,200));
        stopButton.addActionListener(e -> model.stopEngine());
        this.add(stopButton);

        /* edits turbo status when the turbo buttons are pressed.*/
        turboOnButton.addActionListener(e -> model.turnTurboOn());
        turboOffButton.addActionListener(e -> model.turnTurboOff());

        /* edits turbo status when the turbo buttons are pressed.*/
        liftBedButton.addActionListener(e -> model.liftTruckBed());
        lowerBedButton.addActionListener(e -> model.lowerTruckBed());

        // This actionListener is for the gas button only
        gasButton.addActionListener(e -> model.gas(gasAmount));

        brakeButton.addActionListener(e -> model.brake(gasAmount));

    }
}
