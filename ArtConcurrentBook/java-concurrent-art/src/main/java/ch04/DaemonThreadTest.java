package ch04;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Daemon Thread finally block running...");
                }
            }
        }, "DaemonThread");
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
