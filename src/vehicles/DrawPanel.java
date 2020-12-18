package vehicles;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * This panel represent the animated part of the view with the car images.
 */
public final class DrawPanel extends JPanel implements UpdateListener {
    /** The model that has the state of what we want to draw. */
    private final CarModel model;

    private final SpriteVehicleVisitor spriteVehicleVisitor = new SpriteVehicleVisitor();

    /** Initializes the panel and reads the images **/
    public DrawPanel(CarModel model, int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        this.model = model;
        model.addListener(this);
    }

    /** This method is called each time the panel updates/refreshes/repaints itself. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Rescales panel content if necessary
        g2d.scale(getWidth() / model.getWidth(),
                getHeight() / model.getHeight());

        for (Vehicle vehicle : model.getVehicles()) {
            BufferedImage image = vehicle.accept(spriteVehicleVisitor);
            Location location = vehicle.getLocation();

            g.drawImage(image,
                    (int) location.getX(), (int) location.getY(),
                    (int) model.getVehicleSize(), (int) model.getVehicleSize(),
                    null); // see javadoc for more info on the parameters
        }
    }

    /**
     * Repaints when called upon.
     */
    @Override
    public void onUpdate() {
        // repaint() calls the paintComponent method of the panel
        repaint();
    }
}
