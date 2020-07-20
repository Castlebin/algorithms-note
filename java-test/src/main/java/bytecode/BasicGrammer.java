package bytecode;

import java.util.Arrays;

public class BasicGrammer {

    public static void main(String[] args) {
        BasicGrammer bg = new BasicGrammer();
        bg.test(10, 5);
    }

    public void test(int a, int b) {
        int c = a + b;
        int[] array = new int[c];
        for (int i = 0; i < array.length; i++) {
            array[i]++;
        }
        System.out.println(Arrays.toString(array));
    }

}
