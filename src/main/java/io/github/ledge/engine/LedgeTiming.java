package io.github.ledge.engine;

import io.github.ledge.engine.tick.Timing;

public class LedgeTiming implements Timing {

    @Override
    public float getDelta() {
        return 0;
    }

    @Override
    public float getFps() {
        return 0;
    }

    @Override
    public long getMilliSeconds() {
        return System.nanoTime(); // Will be overriden to use the LWJGL Sys class
    }

    @Override
    public float runTimeStep() {
        return 0;
    }
}

