package com.heller.jcip.ch07;

public class UncaughtExceptionHandlerTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                throw new RuntimeException();
            }
        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("UncaughtExceptionHandler caught an exception, " +
                        "Thread: " + t.getName() + ", e: " + e);
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run shutdown hook");
            }
        }));

        thread.start();
    }
}
