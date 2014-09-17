package io.github.ledge.engine.subsystem.lwjgl;

import io.github.ledge.engine.GameEngine;
import io.github.ledge.engine.GameRegistry;
import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.subsystem.SubSystem;
import io.github.ledge.input.InputSystem;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class LwjglInputSystem extends LwjglSubSystem {

    @Override
    public void init(GameEngine engine) {
        super.init(engine);

        initDevices();
    }

    @Override
    public void preUpdate(GameState state, float delta) {

    }

    @Override
    public void postUpdate(GameState state, float delta) {
        state.handleInput(delta);
    }

    @Override
    public void dispose() {
        Mouse.destroy();
        Keyboard.destroy();
    }

    @Override
    public void shutdown() {

    }

    private void initDevices() {
        try {
            Keyboard.create();
            Keyboard.enableRepeatEvents(true);

            Mouse.create();

            InputSystem system = new InputSystem();
            system.setKeyboardDevice(new LwjglKeyboardDevice());
            system.setMouseDevice(new LwjglMouseDevice());

            GameRegistry.registerPermanently(InputSystem.class, system);
        } catch (LWJGLException e) {

        }
    }
}
