package com.heller.jcip;

public class PrimeGenerator {
    private long nTh = 1;

    public long nextPrime() {
        return PrimeNumberGenerator.findPrimeNumber(nTh++);
    }
}
