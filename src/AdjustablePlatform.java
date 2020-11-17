/**
 * A nice truck bed for a truck that can be reclined.
 */
public interface AdjustablePlatform {
    /**
     * Returns whether driving is allowed with this platform arrangement.
     *
     * @return Whether driving in this state will not cause you to lose your driver's license.
     */
    boolean allowedToDrive();

    /**
     * An truck bed updater is able to update truck beds for some value.
     * @param <T> The truck bed type it is updating.
     * @param <U> The type of the value argument.
     */
    interface Updater<T, U> {
        /**
         * Returns the updated truck bed.
         * @param platform The old truck bed.
         * @param value The value to add by/set to.
         * @return The updated truck bed.
         */
        T update(T platform, U value);
    }
}
