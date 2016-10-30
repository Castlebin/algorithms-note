package ch12;

// 使用双锁检查（DCL）实现的单例模式
public class Singleton {
    private static volatile Singleton instance;

    public static void main(String[] args) {
        for (int i = 0; i<10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Singleton.getInstance());
            });
            thread.start();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private Singleton() {}
}
