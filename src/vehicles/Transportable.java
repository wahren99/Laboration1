package vehicles;

/**
 * Something that can be transported by a @c vehicles.Transporter.
 */
public interface Transportable {
    /**
     * Teleports this thing to the specified location.
     *
     * We have assigned Gandalf for this task.
     *
     * @param location The new location.
     */
    void setLocation(Location location);
}
