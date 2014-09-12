package io.github.ledge.engine.state;

import io.github.ledge.engine.GameEngine;

public interface GameState {

    public void init(GameEngine engine);

    public void update(float delta);

    public void render();

    public void handleInput(float delta);

    public void dispose();
}
