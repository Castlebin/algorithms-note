package ch16.se08;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

class PrintAction extends RecursiveAction {
    // 每个小任务最多只处理50个数
    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    public PrintAction(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } else {
            // 任务分解为小任务
            int middle = (start + end) / 2;
            PrintAction left = new PrintAction(start, middle);
            PrintAction right = new PrintAction(middle, end);

            // 并行执行两个小任务
            left.fork();
            right.fork();
        }
    }
}

public class ForkJoinPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的任务
        pool.submit(new PrintAction(0, 300));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
