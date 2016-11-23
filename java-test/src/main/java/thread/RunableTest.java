package thread;

public class RunableTest {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() +", Hello");
        };
        task.run();
        Thread thread = new Thread(task);
        thread.start();
    }
}
