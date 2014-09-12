package io.github.ledge.engine;

import io.github.ledge.engine.state.GameState;

public interface GameEngine {

    public void init();

    public void run(GameState state);

    public void halt();

    public boolean isRunning();

    public void dispose();

    public boolean isDisposed();

    public void switchState(GameState state);

    public GameState getCurrentState();
}
