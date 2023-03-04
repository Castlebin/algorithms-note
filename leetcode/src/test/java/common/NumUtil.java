package common;

public class NumUtil {

    /**
     * 生成一个长度为n的随机数组，数组中的每个元素的随机范围为[rangeL, rangeR]
     */
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return arr;
    }

    /**
     * 生成一个在 [rangeL, rangeR] 范围内的随机整数
     */
    public static int generateRandomInt(int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        return (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
    }




    private NumUtil() {}
}
