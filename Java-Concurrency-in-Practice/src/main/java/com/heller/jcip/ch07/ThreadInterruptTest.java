package com.heller.jcip.ch07;

import java.util.Date;

public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(1000);
                        System.out.println(new Date());
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                    // 检查线程中断标识，返回false，说明捕获InterruptedException时，会立即清除线程的中断标识
                    System.out.println(Thread.currentThread().getName()
                            + " isInterrupted: " + Thread.currentThread().isInterrupted());
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
