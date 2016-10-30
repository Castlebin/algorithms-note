package ch03;

/**
 * 查看JVM 内存回收日志
 * VM args:
 *      -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1M = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1M];

    public static void main(String[] args) throws InterruptedException {
        ReferenceCountingGC objA= new ReferenceCountingGC();
        ReferenceCountingGC objB= new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();

        Thread.sleep(1000);
    }
}
