package io.github.ledge.engine.tick;

public interface Timing {

    public float getDelta();

    public float getFps();

    public long getMilliSeconds();

    public float runTimeStep();
}
