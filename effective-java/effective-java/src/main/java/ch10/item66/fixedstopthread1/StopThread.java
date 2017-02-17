package ch10.item66.fixedstopthread1;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                // 方法使用了同步，保证后台线程能够及时看到stopRequested值的变化，所以backgroundThread能够正常停止
                while (!stopRequested()) {
                    i++;
                }
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }

}
