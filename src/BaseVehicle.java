import java.awt.*;

/** Generic vehicle that other vehicles may be based on. */
public abstract class BaseVehicle implements Vehicle {
    /** The length of the vehicle. */
    private final double length;
    /** Engine power of the vehicle. */
    private final double enginePower;
    /** The current speed of the vehicle. */
    private double currentSpeed = 0;
    /** Color of the vehicle. */
    private Color color;
    /** The vehicle model name. */
    public final String modelName;
    /** Whether the engine is running. */
    private boolean engineOn = false;

    /** The current direction of the vehicle. */
    private Direction direction = Direction.NORTH;
    /** The current x-coordinate. */
    private double x,
        /** The current y-coordinate. */
        y;

    /**
     * Constructs a new stationary unpowered vehicle.
     *
     * @param enginePower The power of the engine.
     * @param color The initial color of the vehicle.
     * @param modelName The name of the model of the vehicle.
     */
    public BaseVehicle(double enginePower, Color color, String modelName, double length) {
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.length = length;

        stopEngine();
    }

    /**
     * Returns the engine power.
     *
     * @return the engine power.
     */
    protected double getEnginePower(){
        return enginePower;
    }

    @Override
    public Color getColor(){
        return color;
    }

    /**
     * Sets the color of the vehicle.
     *
     * @param clr The new color of the vehicle.
     */
    protected void setColor(Color clr){
        color = clr;
    }

    @Override
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Changes the speed, depending on speed factor and engine power of the vehicle.
     *
     * @param amount The amount of speed change.
     */
    private void changeSpeed(double amount) {
        if (Math.abs(amount) > 1)
            throw new IllegalArgumentException("Amount must be in interval (-1, 1)");
        currentSpeed = Math.max(Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower), 0);
    }

    @Override
    public void gas(double amount) {
        if (!engineOn)
            throw new IllegalStateException("Engine is not running!");

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > 1) throw new IllegalArgumentException("Amount must be max 1");
        changeSpeed(amount);
    }

    @Override
    public void brake(double amount){
        if(amount > 1) throw new IllegalArgumentException("Amount must be max 1");
        if(amount < 0) throw new IllegalArgumentException("Amount must be positive");
        changeSpeed(-amount);
    }

    @Override
    public void startEngine() {
        engineOn = true;
    }

    @Override
    public void stopEngine() {
        engineOn = false;
    }

    /**
     * Returns whether the engine is running hot.
     *
     * @return Whether the engine is powered on.
     */
    public boolean isEngineOn() {
        return engineOn;
    }

    /**
     * Returns the acceleration multiplier.
     *
     * @return The multiplier.
     */
    protected abstract double speedFactor();

    @Override
    public Location getLocation(){
        return new Location(x, y);
    }

    @Override
    public void setLocation(Location location){
        this.x = location.x;
        this.y = location.y;
    }

    @Override
    public Direction getDirection(){
        return direction;
    }

    @Override
    public void move() {
        Point delta = direction.getDelta();
        x += getCurrentSpeed() * delta.getX();
        y += getCurrentSpeed() * delta.getY();
        currentSpeed *= 0.999; // Deceleration due to air resistance
    }

    @Override
    public void turnLeft() {
        direction = direction.turnLeft();
    }

    @Override
    public void turnRight() {
        direction = direction.turnRight();
    }

    @Override
    public double getLength() {
        return length;
    }
}
