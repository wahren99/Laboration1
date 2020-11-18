/**
 * A nice platform for a truck or something that can be reclined.
 */
public interface AdjustablePlatform {
    /**
     * Returns whether driving is allowed with this platform arrangement.
     *
     * @return Whether driving in this state will not cause you to lose your driver's license.
     */
    boolean allowedToDrive();
}
