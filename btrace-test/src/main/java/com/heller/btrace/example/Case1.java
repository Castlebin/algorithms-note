package com.heller.btrace.example;

import java.util.Random;

public class Case1 {
    public static Random random = new Random();
    public int size;

    public static void main(String[] args) throws Exception {
        new Case1().run();
    }

    public void run() throws Exception {
        while (true) {
            add(random.nextInt(10), random.nextInt(10));
        }
    }

    public int add(int a, int b) throws Exception {
        Thread.sleep(random.nextInt(10) * 100);
        return a + b;
    }
}
