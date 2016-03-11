package ch10.item66.fixedstopthread2;

import java.util.concurrent.TimeUnit;

public class StopThread {
    // 使用了volatile关键字保证了任何一个线程在 读取 该域 的时候都能看到最近刚刚被写入的值（然而volatile关键字并不能保证同步安全）
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                // 方法使用了volatile关键字，保证后台线程能够及时看到stopRequested值的变化，所以backgroundThread能够正常停止
                while (stopRequested) {
                    i++;
                }
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}
