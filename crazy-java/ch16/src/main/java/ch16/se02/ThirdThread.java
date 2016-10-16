package ch16.se02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThirdThread implements Callable<Integer> {
    private int i;

    @Override
    public Integer call() throws Exception {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    public static void main(String[] args) {
        ThirdThread tt = new ThirdThread();
        FutureTask<Integer> ft = new FutureTask<>(tt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            System.out.println(Thread.activeCount());
            if (i == 20) {
                new Thread(ft).start();
            }
        }
        try {
            System.out.println("子线程返回值：" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
