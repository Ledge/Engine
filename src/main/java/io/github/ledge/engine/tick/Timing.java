package io.github.ledge.engine.tick;

/**
 * Represents an Object that can be used to handle the timing and tick-loop of a game, as well as storing statistics
 * related to it.
 */
public interface Timing {

    /**
     * Gets the delta to be used by update methods, in the form of the number of nanoseconds passed since the last
     * update.
     *
     * @return The delta in milliseconds
     */
    public float getDelta();

    /**
     * Gets the approximate amount of frames that have been rendered in the past second.
     *
     * @return The approximate amount of frames
     */
    public float getFps();

    /**
     * Gets the system time in milliseconds.
     *
     * @return The system time in milliseconds
     */
    public long getMilliSeconds();

    /**
     * Runs a time step and returns the delta to be used for the update methods.
     *
     * @return The delta to be used for the update methods
     */
    public float runTimeStep();
}
