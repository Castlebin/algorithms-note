package ch01.t12;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            String x = "i = " + (i + 1);
        }
    }

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();// interrupt()并不能终止线程
    }
}
