package test;

public class BitCount {
    public static int bitCount(int num) {
        int count = 0;
        while (num != 0) {
            num = num & num - 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (bitCount(i) != Integer.bitCount(i)) {
                throw new Exception(i+"xx");
            }
        }
    }
}
