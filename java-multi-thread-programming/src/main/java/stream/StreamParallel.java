package stream;

import java.util.stream.IntStream;

public class StreamParallel {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(IntStream.of(nums).parallel().map(i -> i * i).sum());
    }
}
