package ch03;

/**
 * 测试新生代的Minor GC
 * VM args:
 *      -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class YoungGenMinorGC {
    public static void main(String[] args) {
        testAllocation();
    }

    public static final int _1M = 1024 * 1024;

    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1M];
        a2 = new byte[2 * _1M];
        a3 = new byte[2 * _1M];
        a4 = new byte[3 * _1M];
    }
}
