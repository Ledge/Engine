package io.github.ledge.engine;

import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.tick.Timing;

import java.util.Iterator;

public class LedgeEngine implements GameEngine {

    private GameState currentState = null;

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

        this.switchState(state);
        this.isRunning = true;
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        this.startGameLoop();
    }

	@Override
	public void halt() {
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
