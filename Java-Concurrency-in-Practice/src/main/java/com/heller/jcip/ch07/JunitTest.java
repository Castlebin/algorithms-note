package com.heller.jcip.ch07;

import com.heller.jcip.PrimeNumberGenerator;
import org.junit.Test;

public class JunitTest {

    /**
     * 跑一秒钟后线程会被强制结束
     * @throws InterruptedException
     */
    @Test
    public void testRunThread() throws InterruptedException {
        new Thread(() -> {
            PrimeNumberGenerator.runALongTimeJob(1000, 50000);
        }).start();
        Thread.sleep(1000);
    }

}
