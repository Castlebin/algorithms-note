package leetcode;

public class N121 {

    private Integer[] arr;

    public N121(Integer[] arr) {
        this.arr = arr;
    }

    public Integer process() {
        int minBuyPrice = arr[0], maxBenefit = 0;// 注意初始化时，minBuyPrice应该为arr[0]
        for (int i = 1; i < arr.length; i++) {
            minBuyPrice = Math.min(minBuyPrice, arr[i]);
            int diff = arr[i] - minBuyPrice;
            maxBenefit = Math.max(diff, maxBenefit);
        }

        return maxBenefit;
    }

}
