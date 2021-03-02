package algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 1423. 可获得的最大点数

 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。

 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。

 你的点数就是你拿到手中的所有卡牌的点数之和。

 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。



 示例 1：

 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 输出：12
 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。

 */
public class CN1423 {

    /**
     * 所以算法实际等同于，求滑动窗口的最小和
     */
    public int maxScore(int[] cardPoints, int k) {
        // 因为剩余的牌必然是连续的，其余的牌是被拿掉的，所以等同于求剩余牌的最小和，用总和减去它即可
        int winLen = cardPoints.length - k;
        int curWinLen = 0;
        int curWinSum = 0, minWinSum = 0, sum = 0;
        for(int index = 0; index < cardPoints.length; index++) {
            sum += cardPoints[index];
            if(curWinLen < winLen) {
                curWinLen++;
                curWinSum += cardPoints[index];
                minWinSum = curWinSum;
            } else {
                curWinSum = curWinSum + cardPoints[index] - cardPoints[index - winLen];
                if(curWinSum < minWinSum) {
                    minWinSum = curWinSum;
                }
            }
        }
        return sum - minWinSum;
    }

    @Test
    public void testMaxScore() {
        Assert.assertEquals(12, maxScore(new int[]{1,2,3,4,5,6,1}, 3));
    }

}
