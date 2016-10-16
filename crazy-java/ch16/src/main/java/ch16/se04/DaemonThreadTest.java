package ch16.se04;

public class DaemonThreadTest {
    public static void main(String[] args) {
        TestThread testThread = new TestThread("后台线程");
        testThread.setDaemon(true);
        testThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
