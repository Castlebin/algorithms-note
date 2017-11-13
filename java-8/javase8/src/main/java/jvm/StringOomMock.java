package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * vm options: -Xms20m -Xmx30m -XX:PermSize=10m -XX:MaxPermSize=10m
 */
public class StringOomMock {

    static String base = "string";

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
            if (i % 2 == 0) {
                Thread.sleep(1000);
            }
        }
    }

}

/**
 *
     Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10m; support was removed in 8.0
     Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10m; support was removed in 8.0
     objc[3857]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/bin/java (0x10c34f4c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x10d37d4e0). One of the two will be used. Which one is undefined.
     Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         at java.util.Arrays.copyOf(Arrays.java:3332)
         at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
         at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
         at java.lang.StringBuilder.append(StringBuilder.java:136)
         at jvm.StringOomMock.main(StringOomMock.java:16)
 */
