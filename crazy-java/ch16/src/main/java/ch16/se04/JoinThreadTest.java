package ch16.se04;

public class JoinThreadTest {
    public static void main(String[] args) throws InterruptedException {
        new TestThread("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                TestThread tt = new TestThread("被join的线程");
                tt.start();
                // 调用线程必须等待被join的线程执行完成之后才能继续执行
                tt.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }

        // main 线程必须等待5个join进来的线程执行完毕才能继续执行
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                TestThread testThread = new TestThread("join的线程 - " + i);
                testThread.start();
                testThread.join();
            }

            System.out.println(Thread.currentThread() + " " + i);
        }
    }
}

class TestThread extends Thread {
    public TestThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

}
