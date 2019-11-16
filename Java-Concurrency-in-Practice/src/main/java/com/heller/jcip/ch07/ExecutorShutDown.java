package com.heller.jcip.ch07;

import com.heller.jcip.PrimeNumberGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        exec.submit(() -> PrimeNumberGenerator.runALongTimeJob(1000, 5000));

        Thread.sleep(1000);
        exec.shutdownNow();
        exec.shutdown();
    }
}
