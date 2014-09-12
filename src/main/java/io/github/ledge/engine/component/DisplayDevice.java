package io.github.ledge.engine.component;

public interface DisplayDevice {

    public boolean isCloseRequested();

    public boolean isActive();

    public void setFullScreen(boolean state);
}
