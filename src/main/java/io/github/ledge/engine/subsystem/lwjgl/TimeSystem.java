package io.github.ledge.engine.subsystem.lwjgl;

import io.github.ledge.engine.GameEngine;
import io.github.ledge.engine.GameRegistry;
import io.github.ledge.engine.LedgeTiming;
import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.subsystem.lwjgl.LwjglSubSystem;
import io.github.ledge.engine.tick.Timing;

public class TimeSystem extends LwjglSubSystem {

    @Override
    public void init(GameEngine engine) {
        super.init(engine);
        GameRegistry.registerPermanently(Timing.class, new LedgeTiming());
    }

    @Override
    public void preUpdate(GameState state, float delta) {

    }

    @Override
    public void postUpdate(GameState state, float delta) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void shutdown() {

    }
}
