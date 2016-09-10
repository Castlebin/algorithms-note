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

    private final TimeCountExecutor<Long> executor = new TimeCountExecutor<>();
    private static final int maxSize = 10;
    private MaxSubSeq maxSubSeq;
    private MyRandom r = new MyRandom(40);

    private class MyRandom {
        private int bound;
        private Random r = new Random();

        MyRandom(int bound) {
            this.bound = bound;
        }

        int nextInt() {
            return r.nextInt(bound) / 2 - bound/4;
        }
    }

    @Before
    public void setUp() throws Exception {
        Integer[] arr = Stream.generate(r::nextInt).limit(maxSize).toArray(Integer[]::new);
        maxSubSeq = new MaxSubSeq(arr);
        log.info("arr: " + Arrays.toString(arr));
    }

    @Test
    public void onlineMaxSubSeq() throws Exception {
        log.info("MaxSubSeqSum->onlineMaxSubSeq: " + executor.timeLog(maxSubSeq::onlineMaxSubSeq));
    }

}
