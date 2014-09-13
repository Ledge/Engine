package io.github.ledge.engine;

import io.github.ledge.engine.tick.Timing;
import org.lwjgl.Sys;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LedgeTiming implements Timing {

    private AtomicInteger currentDelta = new AtomicInteger(0);
    private AtomicLong currentSps = new AtomicLong(0);

    private AtomicLong lastTimeStep = new AtomicLong(this.getMilliSeconds());
    private AtomicLong lastSps = new AtomicLong(this.getMilliSeconds());

    private AtomicInteger stepsThisSecond = new AtomicInteger(0);

    public int getDelta() {
        return this.currentDelta.get();
    }

    @Override
    public float getSps() {
        return this.currentSps.get();
    }

    @Override
    public long getMilliSeconds() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    @Override
    public int runTimeStep() {
        long now = this.getMilliSeconds();
        this.currentDelta.set((int) (now - this.lastTimeStep.get()));
        this.lastTimeStep.set(now);

        long lastSpsDifference = now - lastSps.get();
        if (lastSpsDifference > 1000)
        {
            this.currentSps.set((1000 / lastSpsDifference) * this.stepsThisSecond.get());
            this.stepsThisSecond.set(0);
        }
        this.stepsThisSecond.incrementAndGet();

        return this.getDelta();
    }
}

