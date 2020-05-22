package ch03;

/**
 * 长期存活的对象移动到老年代
 * VM args:

  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution

 */
public class TenuringThreshold {
    public static void main(String[] args) {
        testTenuringThreshold();
    }

    public static final int _1MB = 1024 * 1024;

    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4]; // 什么时候进入老年代决定于XX:MaxTenuring-Threshold设置

        allocation2 = new byte[4 * _1MB];
        System.out.println("allocation2");
        allocation3 = new byte[4 * _1MB];
        System.out.println("allocation3");
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
        System.out.println("allocation3");
    }
}
