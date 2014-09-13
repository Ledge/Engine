package io.github.ledge.engine;

import io.github.ledge.engine.tick.Timing;
import org.lwjgl.Sys;

public class LedgeTiming implements Timing {

    private int currentDelta = 0;
    private long currentSps = 0;

    private long lastTimeStep = this.getMilliSeconds();
    private long lastSps = this.getMilliSeconds();

    private int stepsThisSecond = 0;

    public int getDelta() {
        return this.currentDelta;
    }

    @Override
    public float getSps() {
        return this.currentSps;
    }

    @Override
    public long getMilliSeconds() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    @Override
    public int runTimeStep() {
        long now = this.getMilliSeconds();
        this.currentDelta = (int) (now - this.lastTimeStep);
        this.lastTimeStep = now;

        long lastSpsDifference = now - lastSps;
        if (lastSpsDifference > 1000)
        {
            this.currentSps = (1000 / lastSpsDifference) * this.stepsThisSecond;
            this.stepsThisSecond = 0;
        }
        this.stepsThisSecond++;

        return this.getDelta();
    }
}

