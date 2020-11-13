import java.awt.*;

public abstract class BaseVehicle implements Vehicle {
    /** The length of the vehicle. */
    private final double length;
    /** Number of doors on the vehicle. */
    private final int nrDoors;
    /** Engine power of the vehicle. */
    private final double enginePower;
    /** The current speed of the vehicle. */
    private double currentSpeed = 0;
    /** Color of the car. */
    private Color color;
    /** The car model name. */
    public final String modelName;

    /** The current direction of the car. */
    private Direction direction = Direction.NORTH;
    /** The current x-coordinate. */
    private double x,
        /** The current y-coordinate. */
        y;

    /**
     * Constructs a new stationary unpowered Car.
     * @param nrDoors The number of doors.
     * @param enginePower The power of the engine.
     * @param color The initial color of the car.
     * @param modelName The name of the model of the car.
     */
    public BaseVehicle(int nrDoors, double enginePower, Color color, String modelName, double length) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.length=length;

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

    /**
     * Returns the color of the car
     * @return Color of the car
     */
    @Override
    public Color getColor(){
        return color;
    }

    /**
     * Sets the color of the car.
     *
     * @param clr The new color of the car.
     */
    protected void setColor(Color clr){
        color = clr;
    }

    @Override
    public int getNrDoors(){
        return nrDoors;
    }

    @Override
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Changes the speed, depending on speed factor and engine power of the car.
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
    /**
     * Sets the current speed of the car to 0.1
     */
    @Override
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Stops the car by setting current speed to 0
     */
    @Override
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Returns the acceleration multiplier.
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
    public double getLength() {return length;}

}
