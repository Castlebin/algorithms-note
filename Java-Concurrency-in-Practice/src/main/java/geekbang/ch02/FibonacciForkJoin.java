package geekbang.ch02;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciForkJoin {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        for (int i = 1; i < 10; i++) {
            FibonacciTask task = new FibonacciTask(i);

            Integer result = pool.invoke(task);

            System.out.println(result);
        }
    }
}

class FibonacciTask extends RecursiveTask<Integer> {
    private int n;

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FibonacciTask n1 = new FibonacciTask(n - 1);
        n1.fork();
        FibonacciTask n2 = new FibonacciTask(n - 2);

        return n1.join() + n2.compute();
    }

}
