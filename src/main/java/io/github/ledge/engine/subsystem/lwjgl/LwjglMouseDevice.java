package io.github.ledge.engine.subsystem.lwjgl;

import io.github.ledge.common.math.Vector2i;
import io.github.ledge.input.device.MouseDevice;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class LwjglMouseDevice implements MouseDevice {

    @Override
    public Vector2i getPosition() {
        return new Vector2i(Mouse.getX(), Display.getHeight() - Mouse.getY());
    }

    @Override
    public Vector2i getDelta() {
        return new Vector2i(Mouse.getDX(), -Mouse.getDY());
    }

    @Override
    public boolean isGrabbed() {
        return Mouse.isGrabbed();  // TODO: implement in GameEngine (GameEngine.hasMouse() or so)
    }

    @Override
    public boolean isButtonPressed(int code) {
        return Mouse.isButtonDown(code);
    }

    @Override
    public void handleInput(float delta) {
        //
    }
}
