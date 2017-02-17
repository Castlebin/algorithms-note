package ch08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {
    static CyclicBarrier c = new CyclicBarrier(2, new Task());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
