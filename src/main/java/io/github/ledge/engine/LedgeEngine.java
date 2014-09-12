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
    }

    @Override
    public void run(GameState state) {

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

    }

    private void startGameLoop() {
        while (this.isRunning) {
            // TODO: check if we're actually playing

            Iterator<Float> timeStep = this.timing.runTimeStep();
            while (timeStep.hasNext()) {
                float delta = timeStep.next();
                this.currentState.update(delta);
            }

            // GameThread.processPendingTasks();
        }

        this.isRunning = false;
    }
}
