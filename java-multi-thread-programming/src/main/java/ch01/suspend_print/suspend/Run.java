package ch01.suspend_print.suspend;

public class Run {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1);
            thread.suspend();
            System.out.println("main end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
