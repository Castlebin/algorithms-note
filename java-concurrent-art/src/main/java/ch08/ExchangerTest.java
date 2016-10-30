package ch08;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    private static final Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "这是流水A";
                    String B = exchanger.exchange(A);

                    System.out.println(Thread.currentThread().getName() + ", A、B数据是否一致：" + A.equals(B));
                    System.out.println(Thread.currentThread().getName() + ", A: " + A);
                    System.out.println(Thread.currentThread().getName() + ", B: " + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "这是流水B";
                    String A = exchanger.exchange(B);

                    System.out.println(Thread.currentThread().getName() + ", A、B数据是否一致：" + A.equals(B));
                    System.out.println(Thread.currentThread().getName() + ", A: " + A);
                    System.out.println(Thread.currentThread().getName() + ", B: " + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }
}
