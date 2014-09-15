package io.github.ledge.engine.subsystem;

import io.github.ledge.engine.GameEngine;
import io.github.ledge.engine.state.GameState;

public interface SubSystem {

    public void init(GameEngine engine);

    public void preUpdate(GameState state, float delta);

    public void postUpdate(GameState state, float delta);

    public void dispose();

    public void shutdown();
}
