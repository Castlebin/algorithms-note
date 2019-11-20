package com.heller.jcip.ch15;

public class CasCounter {
    private SimulateCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (!value.compareAndSet(v,v + 1));
        return v + 1;
    }

}
