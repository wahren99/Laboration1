import java.awt.*;

/** A car of some sort. */
public abstract class Car implements Movable {
    /** Number of doors on the car. */
    private final int nrDoors;
    /** Engine power of the car. */
    private final double enginePower;
    /** The current speed of the car. */
    private double currentSpeed = 0;
    /** Color of the car. */
    private Color color;
    /** The car model name. */
    public final String modelName;

    /** The current direction of the car. */
    private Direction direction;
    /** The current x-coordinate. */
    private int x,
            /** The current y-coordinate. */
            y;

    /**
     * Constructs a new stationary unpowered Car.
     * @param nrDoors The number of doors.
     * @param enginePower The power of the engine.
     * @param color The initial color of the car.
     * @param modelName The name of the model of the car.
     */
    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;

        stopEngine();
    }

    /**
     * Returns number of doors
     * @return number of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Returns the engine power
     * @return the engine power
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * returns the current speed of the car
     * @return Current speed of the car
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Returns the color of the car
     * @return Color of the car
     */
    public Color getColor(){
        return color;
    }

    /**
     * Sets the color of the car
     * @param clr Color of the car
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Sets the current speed of the car to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Stops the car by setting current speed to 0
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Returns the acceleration multiplier.
     * @return The multiplier.
     */
    public abstract double speedFactor();

    /**
     * increases the speed, depending on speed factor and engine power of the car.
     * @param amount the starting amount of speed increase
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    /**
     * Decrements the speed, depending on the speed factor of the car, if speed decreases to less than 0, the speed is set to 0
     * @param amount the starting amount of speed decrease.
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }

    /**
     * returns the current point of position
     * @return The current position
     */
    public Point getLocation(){
        return new Point(x, y);
    }

    /** Returns the direction we are currently headed.
     *
     * @return The current direction.
     */
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
}
