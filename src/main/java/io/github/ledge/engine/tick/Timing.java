package io.github.ledge.engine.tick;

/**
 * Represents an Object that can be used to handle the timing and tick-loop of a game, as well as storing statistics
 * related to it.
 */
public interface Timing {
    /**
     * Gets the current time, represented in milliseconds.
     *
     * @return the current time in milliseconds
     */
    public long getCurrentTime();

    /**
     * Gets the time that the current tick began at.
     *
     * @return the time that the current tick began at
     */
    public long getCurrentTick();

    /**
     * Gets the difference between the current tick time and the last tick time.
     *
     * @return the difference between the current tick time and the last tick time.
     */
    public long getTickInterval();

    /**
     * Gets the difference between the current tick time and the last update time. This is more commonly known as the
     * delta for update methods and can be also used as interpolation for render methods.
     *
     * @return the difference between the current tick time and the last update time.
     */
    public long getUpdateInterval();

    /**
     * Gets the difference between the current tick time and the last render time.
     *
     * @return the difference between the current tick time and the last render time.
     */
    public long getRenderInterval();

    /**
     * Gets an approximation of the number of ticks that occurred in the last second.
     *
     * @return an approximation of the number of ticks that occurred in the last second.
     */
    public long getTicksPerSecond();

    /**
     * Gets an approximation of the number of updates that occurred in the last second.
     *
     * @return an approximation of the number of updates that occurred in the last second.
     */
    public long getUpdatesPerSecond();

    /**
     * Gets an approximation of the number of renders that occurred in the last second. This is more commonly known as
     * FPS (frames per second) but its name is chosen so as to ensure consistency is kept in naming within the
     * interface.
     *
     * @return an approximation of the number of renders that occurred in the last second.
     */
    public long getRendersPerSecond();

    /**
     * Gets whether or not update methods should be called to maintain the updates-per-second at the desired level.
     *
     * @return true if the update methods should be called, else false
     */
    public boolean shouldUpdate();

    /**
     * Gets whether or not render methods should be called to maintain the renders-per-second at the desired level.
     *
     * @return true if the render methods should be called, else false
     */
    public boolean shouldRender();

    /**
     * Update all values in the class based on the initialising of a new tick at the exact time the method is called.
     */
    public void stepTick();
}
