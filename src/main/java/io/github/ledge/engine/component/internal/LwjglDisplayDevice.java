package io.github.ledge.engine.component.internal;

import io.github.ledge.engine.component.DisplayDevice;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class LwjglDisplayDevice implements DisplayDevice {

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

    @Override
    public void prepareToRender() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
    }
}
