package sword;

/**
 * 单例模式
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 利用类加载
 *
 * 线程安全 提前生成单例
 */
final class Singleton4 {
    private Singleton4() {}

    private static final Singleton4 INSTANCE = new Singleton4();

    public static Singleton4 getInstance() {
        return INSTANCE;
    }
}

/**
 * 利用类加载
 *
 * 利用内部类，实现懒加载
 */
final class Singleton5 {
    private Singleton5() {}

    public static Singleton5 getInstance() {
        return Nested.INSTANCE;
    }

    static class Nested {
        public static final Singleton5 INSTANCE = new Singleton5();
    }
}

/**
 * effective java 提到的利用枚举实现的单例
 * 挺神奇的一种方式
 */
enum Singleton6 {
    INSTANCE;
}

final class Singleton7 {
    private Singleton7() {}

    private static final AtomicReference<Singleton7> REFERENCE = new AtomicReference<>();

    public static Singleton7 getInstance() {
        for (;;) {
            Singleton7 current = REFERENCE.get();
            if (current != null) {
                return current;
            }
            if (REFERENCE.compareAndSet(null, new Singleton7())) {
                return REFERENCE.get();
            }
        }
    }
}
