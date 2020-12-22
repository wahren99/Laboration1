package vehicles.models;

import vehicles.NormalCar;
import vehicles.VehicleVisitor;

import java.awt.*;

/** A Volvo 240 - the Ferrari of the car world. */
public class Volvo240 extends NormalCar {
    /** The factor by which this car has been trimmed. */
    private final static double trimFactor = 1.25;

    /** Constructs a new black Volvo 240. */
    Volvo240() {
        super(100, Color.BLACK, "Volvo240",3.5);
    }

    @Override
    public <R> R accept(VehicleVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    @Override
    public int getNrOfDoors() {
        return 4;
    }

    public Volvo240 gasMuch() {
        gas(1);
        return this;
    }
}
