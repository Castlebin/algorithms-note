package ch01.se03;

import ch00.TimeCountExecutor;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class MaxSubSeqTest {
    private static final Logger log = Logger.getGlobal();

    private final TimeCountExecutor<Long, int[]> executor = new TimeCountExecutor<>();
    int maxSize;
    private Integer[] arr;

    private MaxSubSeq maxSubSeq = new MaxSubSeq();

    public class MyRandom {
        private int bound;
        private Random r = new Random();
        public MyRandom(int bound) {
            this.bound = bound;
        }
        int nextInt() {
            return r.nextInt(20)/2 - 5;
        }
    }

    @Before
    public void setUp() throws Exception {
        maxSize = 10;
        MyRandom r = new MyRandom(20);
        arr = Stream.generate(r::nextInt).limit(maxSize).toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void onlineMaxSubSeq() throws Exception {
        long maxSubSeqSum = maxSubSeq.onlineMaxSubSeq(arr);
        System.out.println(maxSubSeqSum);
    }

}