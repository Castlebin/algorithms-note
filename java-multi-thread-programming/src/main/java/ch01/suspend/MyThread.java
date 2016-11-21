package ch01.suspend;

public class MyThread extends Thread {
    private long i = 0;
    @Override
    public void run() {
        while (true) {
            i++;
        }
    }
}
