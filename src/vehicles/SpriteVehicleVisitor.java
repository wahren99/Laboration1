package vehicles;

import vehicles.models.CarTransport;
import vehicles.models.Saab95;
import vehicles.models.Scania;
import vehicles.models.Volvo240;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A vehicle visitor that adds images to vehicles. Nice!
 */
public class SpriteVehicleVisitor implements VehicleVisitor<BufferedImage> {
    /** An image of a Volvo. Beautiful. */
    private BufferedImage volvoImage,
            /** An image of a Saab. Gorgeous. */
            saabImage,
            /** An image of a vehicles.models.Scania truck. Powerful. */
            scaniaImage;

    /** Constructs a new sprite vehicle visitor and loads the images. */
    public SpriteVehicleVisitor() {
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            volvoImage = ImageIO.read(new File("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(new File("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(new File("pics/Scania.jpg"));

            // Remember to right click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            // volvoImage = ImageIO.read(vehicles.DrawPanel.class.getResourceAsStream("pics/vehicles.models.Volvo240.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public BufferedImage visit(Volvo240 volvo240) {
        return volvoImage;
    }

    @Override
    public BufferedImage visit(Saab95 saab95) {
        return saabImage;
    }

    @Override
    public BufferedImage visit(Scania scania) {
        return scaniaImage;
    }

    @Override
    public <T extends Vehicle> BufferedImage visit(CarTransport<T> carTransport) {
        throw new UnsupportedOperationException();
    }
}
