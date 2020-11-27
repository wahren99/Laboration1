/**
 * A big boat with an appetite for vehicles.
 *
 *              _    _
 *           __|_|__|_|__
 *         _|____________|__
 *        |o o o o o o o o /
 *      ~'`~'`~'`~'`~'`~'`~'`~
 */
public class Ferry extends CarTransport<Vehicle> {
    /** Constructs a new Ferry. */
    public Ferry() {
        super("Stena Line Cinderella - Viking Line Grace Edition", new CarRamp.FifoCarStorage<>());
    }
}