package ch03;

/**
 * 动态对象年龄判定
 * VM args:

   -verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:MaxTenuringThreshold=8 -XX:+PrintTenuringDistribution

 */
public class TenuringThreshold2 {
    public static void main(String[] args) {
        testTenuringThreshold();
    }

    public static final int _1MB = 1024 * 1024;

    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4]; // allocation1 + allocation2 大于 survivor 空间一半
        allocation2 = new byte[_1MB / 4];
        System.out.println("allocation2");

        allocation3 = new byte[4 * _1MB];
        System.out.println("allocation3");

        allocation4 = new byte[4 * _1MB];
        System.out.println("allocation4");
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
        System.out.println("allocation4-2");
    }
}
