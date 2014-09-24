package io.github.ledge.engine.subsystem.lwjgl;

import io.github.ledge.engine.GameEngine;
import io.github.ledge.engine.subsystem.SubSystem;
import io.github.ledge.utils.LwjglUtils;

public abstract class LwjglSubSystem implements SubSystem {

    private static boolean isInitialised;

    @Override
    public void init(GameEngine engine) {
        if (!isInitialised) {
            LwjglUtils.intializeLwjgl();
            isInitialised = true;
        }
    }
}
