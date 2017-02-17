package ch10.item66.brokenstopthread;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                // 没有使用同步，不能保证后台线程何时看到stopRequested值的变化，所以backgroundThread不会停止
                while (!stopRequested) {
                    i++;
                    // 但是使用下面代码却可以正常结束，烦啊！
                    // PS: 通过查看System.out.println()源码发现使用了synchronized关键字，猜测是受到了影响！暂时就这么解释吧...
                //  System.out.println(i++);
                }
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}
