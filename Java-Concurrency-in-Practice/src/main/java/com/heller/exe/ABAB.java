package com.heller.exe;

/**
 * 两个线程，交替打印A、B
 */
public class ABAB {

    /**
     * 作为 标志位
     */
    private static volatile int state;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                while (state != 0) {
                    Thread.yield();
                }
                System.out.println("A");
                state = 1;
            }
        }).start();
        new Thread(() -> {
            while (true) {
                while (state != 1) {
                    Thread.yield();
                }
                System.out.println("B");
                state = 0;
            }
        }).start();
    }

}
