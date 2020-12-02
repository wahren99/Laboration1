import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This panel represent the animated part of the view with the car images.
 */
public final class DrawPanel extends JPanel implements Size {
    /** The model for what to draw. */
    private final ViewModel viewModel;

    /** Initializes the panel and reads the images **/
    public DrawPanel(ViewModel viewModel, int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        this.viewModel = viewModel;
    }

    /** This method is called each time the panel updates/refreshes/repaints itself. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CarSprite sprite : viewModel.getSprites()) {
            g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), null); // see javadoc for more info on the parameters
        }
    }
}
