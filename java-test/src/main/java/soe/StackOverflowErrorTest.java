package soe;

/**
 * vm options:
 *      -Xms5m -Xmx10m -Xss300k -XX:MetaspaceSize=2m -XX:MaxMetaspaceSize=5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs/java.hprof
 */
public class StackOverflowErrorTest {
    private static int index = 1;

    public static void main(String[] args) {
        StackOverflowErrorTest test = new StackOverflowErrorTest();
        try {
            test.call();
        } catch (Throwable e) {
            System.out.println("stack depth: " + index);
            e.printStackTrace();
        }
    }

    public void call() {
        index++;
        call();
    }

}
