package thread;

public class ThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i=0; i<10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            throw new RuntimeException();
        });
        thread.start();

        for (int i=0; i<20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }

        System.out.println("main");

    }
}
