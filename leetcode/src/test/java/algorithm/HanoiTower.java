package algorithm;

import org.junit.Test;

public class HanoiTower {

    public int hanoi(int n, String from, String buffer, String to) {
        int result = 0;
        if (n == 1) {
            System.out.println(from + " -> " + to);
            result += 1;
            return result;
        }
        result += hanoi(n - 1, from, to, buffer);
        System.out.println(from + " -> " + to);
        result += 1;
        result += hanoi(n - 1, buffer, from, to);
        return result;
    }

    @Test
    public void test1() {
        System.out.println(hanoi(1, "A", "B", "C"));
        System.out.println("------------------");

        System.out.println(hanoi(2, "A", "B", "C"));
        System.out.println("------------------");

        System.out.println(hanoi(3, "A", "B", "C"));
        System.out.println("------------------");

        System.out.println(hanoi(4, "A", "B", "C"));
        System.out.println("------------------");

        System.out.println(hanoi(8, "A", "B", "C"));
        System.out.println("------------------");
    }



}
