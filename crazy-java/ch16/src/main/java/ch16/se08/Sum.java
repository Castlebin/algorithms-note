package ch16.se08;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

// 使用ForkJoinPool来做分治，太方便
class CalTask extends RecursiveTask<Integer> {
    // 每个小任务最多相加20个数
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;

    public CalTask(int arr[], int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }

            return sum;
        } else {
            // 分解任务
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);

            // 并行执行两个小任务
            left.fork();
            right.fork();

            // 将两个小任务的结果组合起来
            return left.join() + right.join();
        }
    }
}

public class Sum {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[1000];
        Random rand = new Random();
        int total = 0;
        for (int i = 0; i<arr.length; i++) {
            arr[i] = rand.nextInt(20);
            total += arr[i];
        }
        System.out.println("total: " + total);

        // 创建一个通用池
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
        System.out.println(future.get());

        // 关闭线程池
        pool.shutdown();
    }
}
