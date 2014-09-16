package io.github.ledge.engine;

import io.github.ledge.engine.component.DisplayDevice;
import io.github.ledge.engine.component.internal.LwjglDisplayDevice;
import io.github.ledge.engine.state.GameState;
import io.github.ledge.engine.subsystem.SubSystem;
import io.github.ledge.engine.subsystem.lwjgl.LwjglGraphicsSystem;
import io.github.ledge.engine.tick.Timing;
import org.lwjgl.opengl.Display;

import java.util.ArrayDeque;
import java.util.Deque;

public class LedgeEngine implements GameEngine {

    public static final int UPDATE_INTERVAL = 1000 / 20;
    public static final int RENDER_INTERVAL = 1000 / 60;

    private GameState currentState = null;
    private GameState pendingState = null;

    private boolean isInitialized = false;
    private boolean isRunning = false;
    private boolean isDisposed = false;

    private Timing timing;

    private Deque<SubSystem> subSystems = new ArrayDeque<>();

    @Override
    public void init() {
        if (isInitialized)
            return;

        this.isInitialized = true;
        this.isRunning = false;
        this.isDisposed = false;

        this.initializeGameSystem();

        this.timing = GameRegistry.get(Timing.class);

        for (SubSystem subSystem : this.getSubSystems()) {
            subSystem.init(this);
        }
    }

    private void initializeGameSystem() {
        addSubSystem(new LwjglGraphicsSystem());
    }

    @Override
    public void run(GameState state) {
        if (!this.isInitialized)
            this.init();

        this.setCurrentState(state);
        this.isRunning = true;
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        this.startGameLoop();

        this.apocalypse();
    }

    private void apocalypse() {
        for (SubSystem subSystem : getSubSystems()) {
            subSystem.shutdown();
        }

        if (this.currentState != null) {
            this.currentState.dispose();
            this.currentState = null;
        }
    }

    @Override
    public void shutdown() {
        this.isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public void dispose() {
        if (!this.isRunning) {
            this.isDisposed = true;
            this.isInitialized = false;

            for (SubSystem subSystem : getSubSystems()) {
                subSystem.dispose();
            }
        }
    }

    @Override
    public boolean isDisposed() {
        return this.isDisposed;
    }

    @Override
    public GameState getCurrentState() {
        return this.currentState;
    }

    @Override
    public void setCurrentState(GameState state) {
        if (this.currentState != null) {
            this.pendingState = state;
        } else {
            this.switchState(state);
        }
    }

    public Deque<SubSystem> getSubSystems() {
        return this.subSystems;
    }

    public void addSubSystem(SubSystem subSystem) {
        this.subSystems.add(subSystem);
    }

    public void remove(SubSystem subSystem) {
        this.subSystems.remove(subSystem);
    }

    private void startGameLoop() {
        this.timing.runTimeStep();

        long lastUpdate = this.timing.getMilliSeconds();
        long lastRender = this.timing.getMilliSeconds();

        DisplayDevice displayDevice = GameRegistry.get(DisplayDevice.class);

        while (this.isRunning && !displayDevice.isCloseRequested()) {
            long now = this.timing.getMilliSeconds();

            long sinceLastUpdate = now - lastUpdate;
            long sinceLastRender = now - lastRender;

            if (this.currentState == null)
                this.shutdown();

            GameThread.setGameThread();

            if (sinceLastUpdate >= LedgeEngine.UPDATE_INTERVAL) {
                // this.currentState.update(sinceLastUpdate);
                lastUpdate = now;
                sinceLastUpdate = 0;

                // this.currentState.update(sinceLastUpdate);
                lastRender = now;
                sinceLastRender = 0;

                this.handleStateChange();
            } else if (sinceLastRender >= LedgeEngine.RENDER_INTERVAL) {
                // this.currentState.render(sinceLastUpdate);
                lastRender = now;
                sinceLastRender = 0;
            } else {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (SubSystem subSystem : getSubSystems()) {
                subSystem.preUpdate(this.currentState, now); // should be delta
            }

            // GameThread.flushAwaitingThreads();

            for (SubSystem subSystem : getSubSystems()) {
                subSystem.postUpdate(this.currentState, now); // should be delta
            }
        }

        this.isRunning = false;
    }

    private void handleStateChange() {
        if (this.pendingState != null) {
            switchState(this.pendingState);
            this.pendingState = null;
        }
    }

    private void switchState(GameState state) {
        if (this.currentState != null) {
            this.currentState.dispose();
        }

        this.currentState = state;
        state.init(this);
    }
}
