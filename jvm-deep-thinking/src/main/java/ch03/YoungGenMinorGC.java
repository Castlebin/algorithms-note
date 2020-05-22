package ch03;

/**
 * 测试新生代的Minor GC  JDK 8
 * VM args:

       -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps

 -XX:+PrintGCTimeStamps 显示的是jvm启动时间，还是PrintGCDateStamps比较直观点
 */
public class YoungGenMinorGC {
    public static void main(String[] args) {
        testAllocation();
    }

    public static final int _1M = 1024 * 1024;

    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1M];
        System.out.println("allocate a1 success..");

        a2 = new byte[2 * _1M];
        System.out.println("allocate a2 success..");

        a3 = new byte[2 * _1M];
        System.out.println("allocate a3 success..");

        a4 = new byte[3 * _1M];     // 出现一次Minor GC
        System.out.println("allocate a4 success..");
    }
}
