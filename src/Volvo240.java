import java.awt.*;

/** A Volvo 240 - the Ferrari of the car world. */
public class Volvo240 extends NormalCar {
    /** The factor by which this car has been trimmed. */
    private final static double trimFactor = 1.25;

    /** Constructs a new black Volvo 240. */
    public Volvo240() {
        super(4, 100, Color.BLACK, "Volvo240",3.5);
    }

    /**
     * returns the car's speed factor
     * @return the Car's speedFactor
     * */

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}
