package ch01.t12;

public class Run {
    public static void main(String[] args) {
        try {
            Thread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("是否停止：" + thread.isInterrupted());
            System.out.println("是否停止：" + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
