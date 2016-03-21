package ch08.item47;

import java.util.Random;

public class RandomBug {
    public static final Random rnd = new Random();

    static int random(int n) {
        return Math.abs(rnd.nextInt() % n);
    }

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 100000; i++) {
            if (random(n) < n / 2) {
                low++;
            }
        }

        // random方法产生的数字有2/3落在前半部分，所以结果接近66666
        System.out.println(low);
    }
}
