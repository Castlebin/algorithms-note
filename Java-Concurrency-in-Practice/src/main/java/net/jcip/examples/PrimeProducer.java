package net.jcip.examples;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * PrimeProducer
 * <p/>
 * Using interruption for cancellation
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* Allow thread to exit */
        }
    }

    public void cancel() {
        interrupt();
    }

    public static void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(5);
        PrimeProducer producer = new PrimeProducer(primes);
        producer.start();
        try {
            Thread.sleep(1000);
            while (needMorePrimes()) {
                consume(primes.take());
            }
        } finally {
            producer.cancel();
        }
    }

    private static boolean needMorePrimes() {
        return false;
    }

    private static void consume(BigInteger prime) {
        System.out.println(prime);
    }

    public static void main(String[] args) throws InterruptedException {
        consumePrimes();
    }
}
