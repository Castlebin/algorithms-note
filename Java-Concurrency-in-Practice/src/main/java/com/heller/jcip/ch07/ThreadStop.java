package com.heller.jcip.ch07;

import com.heller.jcip.PrimeNumberGenerator;

public class ThreadStop {
    public static void main(String[] args) {
        Thread myJob = new Thread(() -> {
            PrimeNumberGenerator.runALongTimeJob(500, 1000);
        });
        myJob.start();
        new Thread(() -> {
            PrimeNumberGenerator.runALongTimeJob(100, 300);

            // stop()方法确实可以暴力的停止线程，但是这是一个废弃的方法，有安全问题
            myJob.stop();
        }).start();
    }
}
