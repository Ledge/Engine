package io.github.ledge.engine.tick;

/**
 * Represents an Object that can be used to handle the timing and tick-loop of a game, as well as storing statistics
 * related to it.
 */
public interface Timing {

    /**
     * Gets the last recorded delta to be used by update methods, in the form of the number of milliseconds passed since
     * the last step.
     *
     * @return The last recorded delta in milliseconds
     */
    public int getDelta();

    /**
     * Gets the last recorded approximate amount of steps that have taken place in the past second.
     *
     * @return The last recorded approximate amount of steps
     */
    public float getSps();

    /**
     * Gets the system time in milliseconds.
     *
     * @return The system time in milliseconds
     */
    public long getMilliSeconds();

    /**
     * Runs a time step and returns the delta to be used for update methods.
     *
     * @return The delta to be used for the update methods
     */
    public int runTimeStep();
}
