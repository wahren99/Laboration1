package vehicles;

/**
 * A thing that can load and transport a many thing.
 *
 * @param <T> The type of thing that can be transported by this thing.
 */
public interface Transporter<T extends Transportable> {
    /**
     * Loads the specified thing onto this transport.
     *
     * @throws IllegalStateException if one tries to load the transporter on to itself
     * @throws IllegalArgumentException if the thing is to far away from this transport
     * @param thing The thing to load onto this transporter.
     */
    void loadThing(T thing);

    /**
     * Unloads a thing.
     *
     * @return The unloaded thing.
     */
    T unloadThing();
}
