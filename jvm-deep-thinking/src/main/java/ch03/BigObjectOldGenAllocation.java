package ch03;

/**
 * 大对象直接在老年区分配
 * VM args:

  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:PretenureSizeThreshold=3145728

 */
public class BigObjectOldGenAllocation {
    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

    public static final int _1M = 1024 * 1024;

    public static void testPretenureSizeThreshold() {

        byte[] a4 = new byte[7 * _1M];     // 直接在老年代分配
        System.out.println("allocate a4 success..");
    }
}
