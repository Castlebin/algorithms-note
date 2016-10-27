package synchronize;

public class DeadLockDemo {
    public static String A = "A";
    public static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println(1);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println(2);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
