import java.awt.image.BufferedImage;
import java.awt.Point;

/** An image with a position. */
public class CarSprite {
    /** The 2D position of the sprite. */
    private final Point pos;
    /** What image gets drawn for this sprite. */
    private final BufferedImage image;

    /**
     * Constructs a new car sprite.
     *
     * @param pos position of the sprite
     * @param image the image used to present this sprite
     */
    public CarSprite(Point pos, BufferedImage image) {
        this.pos = pos;
        this.image = image;
    }

    /**
     * Returns the image.
     *
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Returns x-position
     * @return x-position
     */
    public int getX() {
        return (int) pos.getX();
    }

    /**
     * Returns y-position
      * @return y-position
     */
    public int getY() {
        return (int) pos.getY();
    }

}
