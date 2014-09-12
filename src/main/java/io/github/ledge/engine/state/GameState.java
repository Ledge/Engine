package io.github.ledge.engine.state;

public interface GameState {

    public void init(GameState state);

    public void update(float delta);

    public void render();

    public void handleInput(float delta);

    public void dispose();
}
