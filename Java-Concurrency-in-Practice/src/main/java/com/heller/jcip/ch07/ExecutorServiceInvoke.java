package com.heller.jcip.ch07;

import com.heller.jcip.PrimeNumberGenerator;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorServiceInvoke {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));

        exec.invokeAny(new ArrayList<Callable<Void>>() {{
            add(() -> {
                long i = 500;
                while (!Thread.currentThread().isInterrupted() && i < 600) {
                    System.out.println("Thread-1 ->" + i + " : " + PrimeNumberGenerator.findPrimeNumber(i++));
                }
                return null;
            });
            add(() -> {
                long i = 1000;
                while (!Thread.currentThread().isInterrupted() && i < 1600) {
                    System.out.println("Thread-2 ->" + i + " : " + PrimeNumberGenerator.findPrimeNumber(i++));
                }
                return null;
            });
            add(() -> {
                long i = 1500;
                while (!Thread.currentThread().isInterrupted() && i < 2600) {
                    System.out.println("Thread-3 ->" + i + " : " + PrimeNumberGenerator.findPrimeNumber(i++));
                }
                return null;
            });
        }});

        exec.shutdown();
    }
}
