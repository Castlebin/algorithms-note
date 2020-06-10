package sword;

/**
 * 单例模式
 */

/**
 * 利用类加载
 *
 * 线程安全 提前生成单例
 */
final class Singleton4 {
    private static final Singleton4 instance = new Singleton4();

    public static Singleton4 getInstance() {
        return instance;
    }
}

/**
 * 利用类加载
 *
 * 利用内部类，实现懒加载
 */
final class Singleton5 {
    public static Singleton5 getInstance() {
        return Nested.instance;
    }

    static class Nested {
        public static final Singleton5 instance = new Singleton5();
    }
}

/**
 * effective java 提到的利用枚举实现的单例
 * 挺神奇的一种方式
 */
enum Singleton6 {
    INSTANCE;
}
