package io.github.ledge.engine;

import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.tick.Timing;

import java.util.Iterator;

public class LedgeEngine implements GameEngine {

    private GameState currentState;

    private boolean isInitialized;
    private boolean isRunning;
    private boolean isDisposed;

    private Timing timing;

    @Override
    public void init() {
        if (isInitialized)
            return;

        // TODO: initialize everything

       // this.timing = new LedgeTiming();

        this.isInitialized = true;
    }

    @Override
    public void run(GameState state) {
        if (!this.isInitialized)
            this.init();

        switchState(state);
        this.isRunning = true;
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        startGameLoop();
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public boolean isDisposed() {
        return this.isDisposed;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public GameState getCurrentState() {
        return null;
    }

    @Override
    public void switchState(GameState state) {
        this.currentState = state;
    }

    private void startGameLoop() {
        while (this.isRunning) {
            // TODO: check if we're actually playing
            float delta = this.timing.runTimeStep();
            this.currentState.update(delta);

            // GameThread.processPendingTasks();
        }

        this.isRunning = false;
    }
}
