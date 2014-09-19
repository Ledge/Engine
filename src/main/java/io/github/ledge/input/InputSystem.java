package io.github.ledge.input;

import io.github.ledge.common.math.Vector2i;
import io.github.ledge.engine.GameEngine;
import io.github.ledge.input.device.KeyboardDevice;
import io.github.ledge.input.device.MouseDevice;

public class InputSystem {

    private GameEngine engine;

    private MouseDevice mouseDevice;
    private KeyboardDevice keyboardDevice;

    public void init() {
    }

    public void shutdown() {

    }

    public void update(float delta) {
        processMouseInput(delta);
        processKeyboardInput(delta);
    }

    public MouseDevice getMouseDevice() {
        return this.mouseDevice;
    }

    public void setMouseDevice(MouseDevice mouseDevice) {
        this.mouseDevice = mouseDevice;
    }

    public KeyboardDevice getKeyboardDevice() {
        return this.keyboardDevice;
    }

    public void setKeyboardDevice(KeyboardDevice keyboardDevice) {
        this.keyboardDevice = keyboardDevice;
    }

    private void processMouseInput(float delta) {

        Vector2i mouseDelta = this.mouseDevice.getDelta();

        // Process mouse x-axis events
        if (mouseDelta.x != 0) {

        }

        // Process mouse y-axis events
        if (mouseDelta.y != 0) {

        }

        // Process click queue
    }

    private void processKeyboardInput(float delta) {

    }
}
