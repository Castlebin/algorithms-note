package ch08;

/**

 -verbose:gc -Xmx100M -XX:+PrintGCDetails -XX:+PrintGCDateStamps

 */
public class SlotGCTest {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }

        // 对比一下加上或者注释掉这句赋值语句，GC日志的不同
        int a = 0;

        System.gc();
    }

}
