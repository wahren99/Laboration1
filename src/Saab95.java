import java.awt.*;

/** A Saab 95 - trusty but rusty. */
public class Saab95 extends NormalCar implements CarTransportLoadable {
    /** Whether the turbo is enabled. */
    private boolean turboOn;

    /** Constructs a new Saab 95. */
    public Saab95() {
        super(2, 125, Color.RED, "Saab95", 3);
        turboOn = false;
    }

    /** Turns on the turbo. */
    public void setTurboOn() {
        turboOn = true;
    }

    /** Turns off the turbo. */
    public void setTurboOff() {
        turboOn = false;
    }

    public boolean getTurboOn() {
        return turboOn;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
