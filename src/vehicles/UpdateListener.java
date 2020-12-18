package vehicles;

/**
 * A listener that is wants to be notified when something changes.
 */
public interface UpdateListener {
    /**
     * Called when the thing has been updated.
     */
    void onUpdate();
}
