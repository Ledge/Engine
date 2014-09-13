package io.github.ledge.engine.component.internal;

import io.github.ledge.engine.component.DisplayDevice;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class LWJGLDisplayDevice implements DisplayDevice {

    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    @Override
    public boolean isActive() {
        return Display.isActive();
    }

    @Override
    public void setFullScreen(boolean state) {
        try {
            Display.setDisplayMode(Display.getDesktopDisplayMode());
            Display.setFullscreen(state);
        } catch (LWJGLException e) {
            throw new RuntimeException("Failed to switch to fullscreen: " + e.getLocalizedMessage());
        }
    }
}
