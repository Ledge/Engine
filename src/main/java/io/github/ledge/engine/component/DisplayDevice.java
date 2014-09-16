package io.github.ledge.engine.component;

/**
 * Represents a device that can be used to display the game window.
 */
public interface DisplayDevice {

    /**
     * Checks if this DisplayDevice has requested the window to be closed.
     *
     * @return True if the DisplayDevice has requested the window to be closed, else false
     */
    public boolean isCloseRequested();

    /**
     * Checks if this DisplayDevice is active or not.
     *
     * @return True if the DisplayDevice is active, else false
     */
    public boolean isActive();

    /**
     * Sets whether or not this DisplayDevice should be in full screen mode or not.
     *
     * @param state If the DisplayDevice should be in full screen mode or not
     */
    public void setFullScreen(boolean state);

    /**
     * Prepares the screen to render something new
     */
    public void prepareToRender();
}
