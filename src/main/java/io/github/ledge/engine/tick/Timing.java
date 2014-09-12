package io.github.ledge.engine.tick;

import java.util.Iterator;

public interface Timing {

    public float getDelta();

    public float getFps();

    public long getMilliSeconds();

    public Iterator<Float> runTimeStep();
}
