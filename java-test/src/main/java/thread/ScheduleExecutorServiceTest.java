package thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " start...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + new Date());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}
