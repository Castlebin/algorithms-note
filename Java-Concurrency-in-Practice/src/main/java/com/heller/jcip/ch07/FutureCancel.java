package com.heller.jcip.ch07;

import com.heller.jcip.PrimeNumberGenerator;

import java.util.concurrent.*;

import static net.jcip.examples.LaunderThrowable.launderThrowable;

public class FutureCancel {
    private static final ExecutorService taskExec =
            Executors.newFixedThreadPool(5);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {

        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        } finally {
            task.cancel(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 不会被取消的任务
        FutureCancel.timedRun(() -> PrimeNumberGenerator.runALongTimeJob(1000, 10000), 2, TimeUnit.SECONDS);

        // 可以被取消的任务
        FutureCancel.timedRun(() -> {for (int i = 100; i<10000; i++) {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("cancelable task: " + PrimeNumberGenerator.findPrimeNumber(i++));
                    }
                }},
                2, TimeUnit.SECONDS);


    }

}
