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
        return System.nanoTime(); // TODO: use LWJGL system class
    }

    @Override
    public float runTimeStep() {
        return 0;
    }
}

