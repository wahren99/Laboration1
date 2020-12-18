package vehicles;

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
public class CarView extends JFrame {
    /** the width of the frame */
    public static final int X = 800;
    /** the height of the frame */
    public static final int Y = 800;

    private final SpeedometerView speedometerView;
    private final AddRemoveCarsView addRemoveCarsView;

    /** The simulation model. */
    CarModel model;

    /** The Draw panel used to draw*/
    private final DrawPanel drawPanel;

    private final ButtonsController buttons;

    /**
     *  Constructs a carview
     * @param framename
     * @param model
     */
    public CarView(String framename, CarModel model){
        this.model = model;
        drawPanel = new DrawPanel(model, X, Y-240);
        speedometerView = new SpeedometerView(model);
        addRemoveCarsView = new AddRemoveCarsView(model);
        buttons = new ButtonsController(model);

        initComponents(framename);
    }

    /**
     * Sets everything in place and fits everything
     */
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
        this.add(speedometerView);
        this.add(addRemoveCarsView);
        add(buttons);

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
}