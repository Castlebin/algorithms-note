package sword;

import org.junit.Assert;
import org.junit.Test;

/**
 剑指 Offer 63. 股票的最大利润
 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

 示例 1:

 输入: [7,1,5,3,6,4]
 输出: 5
 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

 转化为最大子序列和问题，简单
*/
public class S063 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxProfit = 0, sum = 0;
        int[] diffs = new int[prices.length - 1];
        for (int i = 0; i < diffs.length; i++) {
            diffs[i] = prices[i + 1] - prices[i];
            sum = sum + diffs[i];
            if (sum > maxProfit) {
                maxProfit = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }
        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(5, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }

}
