package ch01;

public class ConcurrencyTest {
    private static final long count = 10001;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    /**
     * 两个线程分别执行 a、b的计算
     */
    private static void concurrency() throws InterruptedException {
        int a = 0, b = 0;
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();

        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency : " + time + " ms, a=" + a + ", b=" + b);
    }

    /**
     * 单个线程先后执行a、b的计算
     */
    private static void serial() {
        int a = 0, b = 0;
        long start = System.currentTimeMillis();

        for (long i = 0; i < count; i++) {
            a += 5;
        }
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("serial : " + time + " ms, a=" + a + ", b=" + b);
    }
}
