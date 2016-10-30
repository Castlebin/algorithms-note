package ch08;

// 使用join来等待其他线程完成
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 finish.");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 finish.");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("all thread finish.");
    }
}
