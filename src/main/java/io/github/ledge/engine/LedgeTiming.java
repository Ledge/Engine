package io.github.ledge.engine;

import io.github.ledge.engine.tick.Timing;
import org.lwjgl.Sys;

public class LedgeTiming implements Timing {

    /**
     * The value of one second in milliseconds.
     */
    public static final long ONE_SECOND = 1000;

    private final long idealUpdateInterval; // the amount of updates-per-second to aim for
    private final long idealRenderInterval; // the amount of renders-per-second to aim for

    private long currentTick = this.getCurrentTime(); // the cached time at which the current tick began

    private long lastTick = this.currentTick; // the time at which the previous tick began
    private long lastUpdate = this.currentTick; // the value of currentTick when the last update began
    private long lastRender = this.currentTick; // the value of currentTick when the last render began
    private long lastStatisticsAnalysis = this.currentTick; // the value of currentTick when the *PerSecond variables were updated.

    private long tickInterval; // the cached difference between the time this tick began and the time the previous tick began
    private long updateInterval; // the cached difference between the time this tick began and the time the last update's tick began
    private long renderInterval; // the cached difference between the time this tick began and the time the last render's tick began
    private long statisticsAnalysisInterval; // the cached difference between the time this tick began and the time the last statisticsAnalysis' tick began

    private long ticksThisSecond = 0; // the amount of ticks that have taken place so far this second
    private long updatesThisSecond = 0; // the amount of updates that have taken place so far this second
    private long rendersThisSecond = 0; // the amount of renders that have taken place so far this second

    private long ticksPerSecond = 0; // the estimated amount of ticks that took place last second
    private long updatesPerSecond = 0; // the estimated amount of updates that took place last second
    private long rendersPerSecond = 0; // the estimated amount of renders that took place last second

    private boolean shouldUpdate = false; // the cached order on whether one should update or not
    private boolean shouldRender = false; // the cached order on whether one should render or not

    /**
     * Instantiate LedgeTiming with specific update and render interval values.
     *
     * @param idealUpdateInterval the ideal amount of milliseconds between updates
     * @param idealRenderInterval the ideal amount of milliseconds between renders
     */
    public LedgeTiming(long idealUpdateInterval, long idealRenderInterval) {
        this.idealUpdateInterval = idealUpdateInterval;
        this.idealRenderInterval = idealRenderInterval;
    }

    /**
     * Instantiate LedgeTiming with the default update and render interval values.
     *
     * The default interval for update is 50 milliseconds and the default interval for render is 17 milliseconds.
     */
    public LedgeTiming() {
        this(50, 17); // sets the defaults to ~20 UPS, ~60 FPS (approximation, due to rounding)
    }

    @Override
    public long getCurrentTime() {
        return (Sys.getTime() * LedgeTiming.ONE_SECOND) / Sys.getTimerResolution();
    }

    @Override
    public long getCurrentTick() {
        return this.currentTick;
    }

    @Override
    public long getTickInterval() {
        return this.tickInterval;
    }

    @Override
    public long getUpdateInterval() {
        return this.updateInterval;
    }

    @Override
    public long getRenderInterval() {
        return this.renderInterval;
    }

    @Override
    public long getTicksPerSecond() {
        return this.ticksPerSecond;
    }

    @Override
    public long getUpdatesPerSecond() {
        return this.updatesPerSecond;
    }

    @Override
    public long getRendersPerSecond() {
        return this.rendersPerSecond;
    }

    @Override
    public boolean shouldUpdate() {
        return this.shouldUpdate;
    }

    @Override
    public boolean shouldRender() {
        return this.shouldRender;
    }

    @Override
    public void stepTick() {
        this.lastTick = this.currentTick;
        this.currentTick = this.getCurrentTime();
        this.ticksThisSecond++;

        if (this.shouldUpdate) {
            // assume updates have taken place
            this.lastUpdate = this.lastTick;
            this.updatesThisSecond++;
        }

        if (this.shouldRender) {
            // assume renders have taken place
            this.lastRender = this.lastTick;
            this.rendersThisSecond++;
        }

        this.tickInterval = this.currentTick - this.lastTick;
        this.updateInterval = this.currentTick - this.lastUpdate;
        this.renderInterval = this.currentTick - this.lastRender;
        this.statisticsAnalysisInterval = this.currentTick - this.lastStatisticsAnalysis;

        // one second has been exceeded since the last *PS readings were taken
        if (this.statisticsAnalysisInterval >= LedgeTiming.ONE_SECOND) {
            // update perSecond values

            this.ticksPerSecond = this.ticksThisSecond / this.statisticsAnalysisInterval;
            this.updatesPerSecond = this.updatesThisSecond / this.statisticsAnalysisInterval;
            this.rendersPerSecond = this.rendersThisSecond / this.statisticsAnalysisInterval;

            this.ticksThisSecond = 0;
            this.updatesThisSecond = 0;
            this.rendersThisSecond = 0;

            this.lastStatisticsAnalysis = this.currentTick;
        }

        if (this.updateInterval >= this.idealUpdateInterval) { // if an update should take place
            this.shouldUpdate = true;
            this.shouldRender = true; // a render should also take place to show new values
        } else if (this.renderInterval >= this.idealRenderInterval) {
            this.shouldUpdate = false;
            this.shouldRender = true;
        } else {
            this.shouldUpdate = false;
            this.shouldRender = false;
        }
    }
}

