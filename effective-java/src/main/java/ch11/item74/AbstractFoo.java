package ch11.item74;

import java.util.concurrent.atomic.AtomicReference;

public class AbstractFoo {
    private int x, y;

    private final AtomicReference<State> init = new AtomicReference<State>(State.NEW);

    protected AbstractFoo() {}

    public AbstractFoo(int x, int y) {
        initialize(x, y);
    }

    private enum State {
        NEW, INITIALIZING, INITIALIZED
    }

    protected final void initialize(int x, int y) {
        if (!init.compareAndSet(State.NEW, State.INITIALIZING)) {
            throw new IllegalStateException("Already initialized");
        }

        this.x = x;
        this.y = y;
        init.set(State.INITIALIZED);
    }

    private void checkInit() {
        if (init.get() != State.INITIALIZED) {
            throw new IllegalStateException("Uninitalized");
        }
    }

    protected final int getX() {
        checkInit();
        return x;
    }

    protected final int getY() {
        checkInit();
        return y;
    }

}
