import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Representation of the model visuals.
 */
// TODO Rename to CarViewModel
public class ViewModel {
    /** The list of sprites that make up what should be currently displayed. */
    private Iterable<CarSprite> sprites;
    /**
     * The listener that gets notified when this view model changes.
     */
    private UpdateListener listener;

    /**
     * Constructs a viewmodel
     *
     * @param sprites The initial list of sprites.
     */
    public ViewModel(Iterable<CarSprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Constructs a new ViewModel that shows nothing.
     */
    public ViewModel() {
        this(Collections.emptyList());
    }

    /**
     * Sets the update listener to the specified value.
     *
     * @param listener The new listener.
     */
    public void setListener(UpdateListener listener) {
        this.listener = listener;
    }

    /**
     * Updates this view model to consist of the given list of sprites.
     *
     * @param sprites The new list.
     */
    public void update(Iterable<CarSprite> sprites) {
        this.sprites = sprites;
        listener.onUpdate();
    }

    /**
     * @return The list of sprites
     */
    public Iterable<CarSprite> getSprites() {
        return sprites;
    }
}
