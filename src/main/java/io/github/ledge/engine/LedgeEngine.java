package io.github.ledge.engine;

import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.tick.Timing;

public class LedgeEngine implements GameEngine {

    public static final int UPDATE_INTERVAL = 1000 / 20;
    public static final int RENDER_INTERVAL = 1000 / 60;

    private GameState currentState = null;
    private GameState pendingState = null;

    private boolean isInitialized = false;
    private boolean isRunning = false;
    private boolean isDisposed = false;

    private Timing timing;

    @Override
    public void init() {
        if (isInitialized)
            return;

        this.isInitialized = true;
        this.isRunning = false;
        this.isDisposed = false;

        this.timing = new LedgeTiming(); // TODO: allow the Timing to be customised
    }

    @Override
    public void run(GameState state) {
        if (!this.isInitialized)
            this.init();

        this.setCurrentState(state);
        this.isRunning = true;
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        this.startGameLoop();
    }

    @Override
    public void shutdown() {
        this.isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public void dispose() {
        this.isDisposed = true;
    }

    @Override
    public boolean isDisposed() {
        return this.isDisposed;
    }

    @Override
    public GameState getCurrentState() {
        return this.currentState;
    }

    @Override
    public void setCurrentState(GameState state) {
        if (this.currentState != null) {
            this.pendingState = state;
        } else {
            this.switchState(state);
        }
    }

    private void startGameLoop() {
        this.timing.runTimeStep();

        long lastUpdate = this.timing.getMilliSeconds();
        long lastRender = this.timing.getMilliSeconds();

        while (this.isRunning) {
            long now = this.timing.getMilliSeconds();

            long sinceLastUpdate = now - lastUpdate;
            long sinceLastRender = now - lastRender;
            
            if (this.currentState == null)
                this.shutdown();

            if (sinceLastUpdate >= LedgeEngine.UPDATE_INTERVAL) {
                this.currentState.update(sinceLastUpdate);
                lastUpdate = now;
                sinceLastUpdate = 0;

                this.currentState.render(sinceLastUpdate);
                lastRender = now;
                sinceLastRender = 0;

                this.handleStateChange();
            } else if (sinceLastRender >= LedgeEngine.RENDER_INTERVAL) {
                this.currentState.render(sinceLastUpdate);
                lastRender = now;
                sinceLastRender = 0;
            } else {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        this.isRunning = false;
    }

    private void handleStateChange() {
        if (this.pendingState != null) {
            switchState(this.pendingState);
            this.pendingState = null;
        }
    }

    private void switchState(GameState state) {
        if (this.currentState != null) {
            this.currentState.dispose();
        }

        this.currentState = state;
        state.init(this);
    }
}
