import java.awt.*;

/**
 * A Scania truck.
 *
 * <pre>
 *                                      /`-.
                                 \   `-._
                                 |`-._   `-._
                                 /    `-._   `-._
                                /    /    `-._   `-._
                               /    /    /    `-._   `-._
                               `-._/    /    /    `-._   `-._
                                 / `-._/    /    /    `-._   `-.
                                 `-._  `-._/    /    /    `-._ o)
                                     `-._  `-._/    /    /    /||
                                       //`-._  `-._/    /    / ||
                                      //     `-._  `-._/    /  ||
                                     (o.       /:`-._  `-._/   ||
                                      \:`.    /:/    `-./
                                       \::\  /:/
              ______                    \::\':/
           .'.-----.'.                .--(O)\'
          /.':    (| |               /:.-'\::\
         / | :`.  || |              /:/   .o):\
   ____.'. [-'-----' |             /:/.-'\.'\::\
 .'    |=| |     <=| |          _./:/ _.-'   `.:|
 |____.'=| [       | |   ____.-' /:/-'_________(o)
 (_.....---'-.__   | |\ |________ _______________|
 [_|   .------. '._| |'-'--------'---- .------. _|_
 [_|__/ .----. \ ___ |[=:=]_:::::::::_/ .----. \___]
[___|/ /  ..  \ \___||___.-----------/ /  ..  \ \--'
      |  (^v)  |                      |  (^v)  |
       \  ''  /                        \  ''  /
 * </pre>
 */
public class Scania extends BaseVehicle {
    private final ScaniaTruckBed platform = new ScaniaTruckBed();

    public Scania() {
        super(200, Color.YELLOW, "ScaniaV6", 12);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower();
    }

    @Override
    public void startEngine(){
        if (!platform.allowedToDrive())
            throw new IllegalStateException("Cannot drive with your truck bed down silly");
        super.startEngine();
    }

    public void setTruckBedAngle(float angle) {
        if (!isStationary())
            throw new IllegalStateException("Cannot change angle while driving");
        platform.setAngle(angle);
    }

    public void addTruckBedAngle(float angle) {
        setTruckBedAngle(getTruckBedAngle() + angle);
    }

    public float getTruckBedAngle() {
        return platform.getAngle();
    }

    /**
     * The truck bed of Scania trucks.
     *
     * Can be tilted.
     */
    public static final class ScaniaTruckBed implements AdjustablePlatform {
        /**
         * The angle of the truck bed.
         *
         * It is between [0, 70] degrees where 0 is down.
         */
        private float angle;

        public ScaniaTruckBed(float angle) {
            this.angle = angle;
        }

        public ScaniaTruckBed() {
            this(MIN_ANGLE);
        }


        public float getAngle(){
            return angle;
        }

        /** The truck bed is at a comfortable neutral position at this angle. */
        static final float MIN_ANGLE = 0,
        /** The truck bed is fully reclined at this angle. Unsafe to drive. */
                MAX_ANGLE = 70;

        public void setAngle(float angle) {
                    this.angle = Math.min(Math.max(angle, MIN_ANGLE), MAX_ANGLE);
                }

        @Override
        public boolean allowedToDrive() {
            return angle == MIN_ANGLE;
        }
    }
}
