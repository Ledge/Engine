package io.github.ledge.engine;

import io.github.ledge.engine.tick.Timing;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

public class LedgeTiming implements Timing {

    private AtomicLong last = new AtomicLong(0);
    private AtomicLong delta = new AtomicLong(0);

    @Override
    public float getDelta() {
        return this.delta.get();
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
    public Iterator<Float> runTimeStep() {
        return null;
    }
}
