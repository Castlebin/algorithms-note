package com.heller.jcip.ch07;

import com.heller.jcip.PrimeNumberGenerator;

public class DaemonThreadTest {
    public static void main(String[] args) throws InterruptedException {
        // 守护线程在JVM中正常线程都结束后，会被暴力结束
        Thread daemon = new Thread(() -> {
            PrimeNumberGenerator.runALongTimeJob(1000, 40000);
        });
        daemon.setDaemon(true);
        daemon.start();
        Thread.sleep(2000);
    }
}
