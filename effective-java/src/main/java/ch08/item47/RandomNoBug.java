package ch08.item47;

import java.util.Random;

public class RandomNoBug {
    public static final Random rnd = new Random();

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 100000; i++) {
            // 直接使用Random.nextInt(int) 方法产生的随机数是均匀的
            if (rnd.nextInt(n) < n / 2) {
                low++;
            }
        }

        System.out.println(low);
    }
}
