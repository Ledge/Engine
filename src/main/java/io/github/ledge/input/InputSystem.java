package io.github.ledge.input;

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

    }

    private void processKeyboardInput(float delta) {

    }
}
