package io.github.ledge.engine;

import io.github.ledge.engine.state.GameState;

public interface GameEngine {

    public void init();

    public void run(GameState state);

    public boolean isRunning();

    public boolean isDisposed();

    public void dispose();

    public void shutdown();

    public GameState getCurrentState();

    public void switchState(GameState state);
}
