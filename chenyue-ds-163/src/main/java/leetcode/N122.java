package leetcode;

public class N122 {

    private Integer[] arr;

    public N122(Integer[] arr) {
        this.arr = arr;
    }

    public Integer process() {
        int maxBenefit = 0;
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff > 0) {
                maxBenefit += diff;
            }
        }

        return maxBenefit;
    }

}
