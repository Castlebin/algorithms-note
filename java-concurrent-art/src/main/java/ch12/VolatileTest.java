package ch12;

// volatile关键字不能保证线程安全
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i=0;i<threads.length;i++) {
            threads[i] = new Thread(() -> {
                for (int j=0;j<100;j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        // 等待所有累加线程结束
        if (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
