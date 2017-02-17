package ch04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecated {
    public static void main(String[] args) throws InterruptedException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        printThread.suspend();// 暂停打印线程
        System.out.println("PrintThread suspend @ " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.resume();// 恢复打印线程
        System.out.println("PrintThread resume @ " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        printThread.stop();// 终止打印线程
        System.out.println("PrintThread stop @ " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }

    static class Runner implements Runnable {
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + " Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
