package io.github.ledge.engine;

public class GameThread {

    private static volatile Thread gameThread;

    private GameThread() {
    }

    public static boolean isGameThread() {
        return Thread.currentThread() == gameThread;
    }

    public static void setGameThread() {
        gameThread = Thread.currentThread();
    }

    // TODO: add submitTask(Runnable) method(s)...
}
