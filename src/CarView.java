import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/
public class CarView extends JFrame implements UpdateListener {
    /** the width of the frame */
    public static final int X = 800;
    /** the height of the frame */
    public static final int Y = 800;

    private final SpeedometerView speedometerView;

    /** The controller member */
    CarController carC;

    /** The Draw panel used to draw*/
    private final DrawPanel drawPanel;

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
     * to change Scania bed status and to start/stop all cars
     */
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    /**
     *  Constructs a carview
     * @param framename
     * @param cc
     * @param model
     */
    public CarView(String framename, CarController cc, CarModel model){
        this.carC = cc;
        model.addListener(this);
        drawPanel = new DrawPanel(model, X, Y-240);
        speedometerView = new SpeedometerView(model);

        initComponents(framename);
    }

    public Size getDrawPanelSize() {
        return new Size() {
            @Override
            public int getWidth() {
                return drawPanel.getWidth();
            }

            @Override
            public int getHeight() {
                return drawPanel.getHeight();
            }
        };
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        this.add(speedometerView);

        /**
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

        /**
         * edits layout of the gas panel.
         */
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        /**
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
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        /**
         * sets color of the start button.
         */
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        startButton.addActionListener(e -> carC.startEngine());
        this.add(startButton);

        /**
         * sets color of the stop button.
         */

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        stopButton.addActionListener(e -> carC.stopEngine());
        this.add(stopButton);

        /** edits turbo status when the turbo buttons are pressed.*/
        turboOnButton.addActionListener(e -> carC.turnTurboOn());
        turboOffButton.addActionListener(e -> carC.turnTurboOff());

        /** edits turbo status when the turbo buttons are pressed.*/
        liftBedButton.addActionListener(e -> carC.liftTruckBed());
        lowerBedButton.addActionListener(e -> carC.lowerTruckBed());

        // This actionListener is for the gas button only
        gasButton.addActionListener(e -> carC.gas(gasAmount));

        brakeButton.addActionListener(e -> carC.brake(gasAmount));

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Repaints when called upon
     */
    @Override
    public void onUpdate() {
        // repaint() calls the paintComponent method of the panel
        drawPanel.repaint();
    }
}