package ch00;

import java.util.function.Function;
import java.util.logging.Logger;

public class TimeCountExecutor<T, R> {
    private static final Logger log = Logger.getGlobal();

    public R timeLog(Function<T, R> function, T t) {
        long begin = System.currentTimeMillis();
        R r = function.apply(t);
        long end = System.currentTimeMillis();
        log.info(function.getClass().getName() + "execute time: " + (begin - end) + " ms");
        return r;
    }

}
