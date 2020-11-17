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
public class Scania extends BaseVehicle<Scania.ScaniaTruckBed> {

    public Scania() {
        super(200, Color.YELLOW, "ScaniaV6", 12);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower();
    }

    /**
     * The truck bed of Scania trucks.
     *
     * Can be tilted.
     */
    public static final class ScaniaTruckBed implements TruckBed {
        /**
         * The angle of the truck bed.
         *
         * It is between [0, 70] degrees where 0 is down.
         */
        private final float angle;

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

        @Override
        public boolean allowedToDrive() {
            return angle == MIN_ANGLE;
        }

        public static final Updater<ScaniaTruckBed, Float> setAngle = (truckBed, value) -> new ScaniaTruckBed(Math.min(Math.max(value,
                ScaniaTruckBed.MIN_ANGLE), ScaniaTruckBed.MAX_ANGLE));

        public static final Updater<ScaniaTruckBed, Float> addAngle = (truckBed, value) -> setAngle.update(truckBed, truckBed.angle + value);
    }
}
