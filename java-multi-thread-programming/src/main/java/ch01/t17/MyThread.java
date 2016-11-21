package ch01.t17;

public class MyThread extends Thread {
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        int count  = 0;
        for (int i = 0; i < 500000; i++) {
         //   Thread.yield();
            count++;
        }
        System.out.println("用时 " + (System.currentTimeMillis() - begin) + " ms.");
    }
}
