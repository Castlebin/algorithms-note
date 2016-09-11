package ch00;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class TimeCountExecutor<T> {
    private static final Logger log = Logger.getGlobal();

    public T timeLog(Supplier<T> supplier) {
        long begin = System.currentTimeMillis();
        T t = supplier.get();
        long end = System.currentTimeMillis();
        log.info("execute time: " + (end - begin) + " ms");
        return t;
    }

}
