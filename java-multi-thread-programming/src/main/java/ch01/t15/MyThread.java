package ch01.t15;

public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000000; i++) {
                String x = "i = " + (i + 1);
                System.out.println(x);
            }
            System.out.println("run mythread begin");
            Thread.sleep(200000);
            System.out.println("run mythread end.");
        } catch (InterruptedException e) {
            System.out.println(" InterruptedException catch");
            e.printStackTrace();
        }

    }
}
