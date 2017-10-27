package ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * JDK 8 运行时常量池导致OOM
 * VM args:
 *      -XX:MetaspaceSize=2m -XX:MaxMetaspaceSize=5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs/java.hprof
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
