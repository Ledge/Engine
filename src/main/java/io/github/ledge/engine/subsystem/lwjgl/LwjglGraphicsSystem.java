package io.github.ledge.engine.subsystem.lwjgl;

import io.github.ledge.engine.GameEngine;
import io.github.ledge.engine.GameRegistry;
import io.github.ledge.engine.component.DisplayDevice;
import io.github.ledge.engine.component.internal.LwjglDisplayDevice;
import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.subsystem.SubSystem;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;

public class LwjglGraphicsSystem extends LwjglSubSystem {

    @Override
    public void init(GameEngine engine) {
        super.init(engine);

        LwjglDisplayDevice displayDevice = new LwjglDisplayDevice();

        GameRegistry.register(DisplayDevice.class, displayDevice);

        initDisplay();
        initOpenGl();
    }

    @Override
    public void preUpdate(GameState state, float delta) {
    }

    @Override
    public void postUpdate(GameState state, float delta) {
        Display.update();
        Display.sync(60);

        state.render(delta);

        if (Display.wasResized())
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
    }

    @Override
    public void dispose() {
        Display.destroy();
    }

    @Override
    public void shutdown() {
    }

    private void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(750, 500));
            Display.setTitle("Legde | Indev");
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            // TODO: handle error
            throw new RuntimeException(e);
        }
    }

    private void initOpenGl() {
        canRun();
        glViewport(0, 0, Display.getWidth(), Display.getHeight());
        enableOpenGl();
    }

    private void canRun() {
        boolean isCompatible = GLContext.getCapabilities().OpenGL11;

        if (!isCompatible)
            throw new RuntimeException("Your GPU is not compatible with Ledge!");
    }

    private void enableOpenGl() {
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_NORMALIZE);
        glDepthFunc(GL_LEQUAL);
    }
}
