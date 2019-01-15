package map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// JDK 1.8中ConcurrentHashMap的死锁bug（1.9修复）
// http://www.importnew.com/31278.html
// https://blog.csdn.net/lx1848/article/details/81256443
public class ConcurrentHashMapBugTest {
    private static Map<Integer, Integer> cache = new ConcurrentHashMap<>(15);

    public static void main(String[] args) {
        System.out.println(fibonaacci(80));
    }

    private static int fibonaacci(Integer i) {
        if (i == 0 || i == 1) {
            return i;
        }

        return cache.computeIfAbsent(i, (key) -> {
            System.out.println("fibonaacci : " + key);
            return fibonaacci(key - 1) + fibonaacci(key - 2);
        });
    }

}
