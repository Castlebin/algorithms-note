package ch08;

// 使用join来等待其他线程完成
public class JoinTest2 {
    public static volatile int race = 0;

    public static void increase() {
        System.out.println(Thread.currentThread().getName() + " : " + (race++));
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i=0;i<threads.length;i++) {
            threads[i] = new Thread(() -> {
                for (int j=0;j<10;j++) {
                    increase();
                }
            });
            threads[i].start();
            threads[i].join();
        }
        System.out.println(race);
    }
}
