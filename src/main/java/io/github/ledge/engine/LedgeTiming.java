package io.github.ledge.engine;

import io.github.ledge.engine.tick.Timing;
import org.lwjgl.Sys;

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
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    @Override
    public float runTimeStep() {
        return 0;
    }
}

