package io.github.ledge.input.device;

import io.github.ledge.common.math.Vector2i;

public interface MouseDevice {

    public Vector2i getPosition();

    public Vector2i getDelta();

    public boolean isGrabbed();

    public boolean isButtonPressed(int code);

    public void handleInput(float delta);
}
