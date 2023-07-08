package codility.lesson.L10;

import org.junit.Assert;
import org.junit.Test;

/**
 Flags

 Find the maximum number of flags that can be set on mountain peaks.

 https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/
 */
public class T3 {

    /**
     * Correctness 100 % ，但 Performance 只有 71 % 。综合：Task Score：86 %
     */
    class Solution_1 {
        public int solution(int[] A) {
            int n = A.length;
            boolean[] isPeak = new boolean[n];
            int numPeaks = 0;
            for (int i = 1; i < n - 1; i++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    isPeak[i] = true;
                    numPeaks++;
                }
            }
            int maxFlags = 0;
            // 明显，最多只能带 numPeaks 面旗，带多了没用。从最多的可能的数量往下试。
            for (int flags = numPeaks; flags >= 1; flags--) {
                int pos = 0; // 下一个要尝试插旗的位置
                int count = 0; // 插了多少面旗
                while (pos < n && count < flags) {
                    if (isPeak[pos]) { // 是一个山峰。不存在说为了得到最多的旗子数量，当前可以插旗，但是我为了得到最优解，要往下选的情况（想想为什么?）
                        count++;
                        pos += flags;// 下一个可选的位置，至少要间隔所带旗子的数量
                    } else {
                        pos++;
                    }
                }
                maxFlags = Math.max(maxFlags, count);
            }
            return maxFlags;
        }
    }

    /**
     * 对上面的解法，做一点点优化 (这个很简单，所以必须掌握！):
     * 1. 简单的一个优化，发现，最多能带的旗子数量，也不可能超过 sqrt(n) + 1  (因为间隔要求)
     * 减少最大的可能的迭代次数，就达到双百了
     *
     * Correctness 100 % ，Performance 100 % 。综合：Task Score：100 %
     */
    class Solution_2 {
        public int solution(int[] A) {
            int n = A.length;
            boolean[] isPeak = new boolean[n];
            int numPeaks = 0;
            for (int i = 1; i < n - 1; i++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    isPeak[i] = true;
                    numPeaks++;
                }
            }
            int maxFlags = 0;
            // 明显，最多只能带 numPeaks 面旗，带多了没用。从最多的可能的数量往下试。
            // 优化点 1：简单的一个优化，发现，最多能带的旗子数量，也不可能超过 sqrt(n) + 1  (因为间隔要求)
            for (int flags = Math.min(numPeaks, (int) Math.sqrt(n) + 1); flags >= 1; flags--) {
                int pos = 0; // 下一个要尝试插旗的位置
                int count = 0; // 插了多少面旗
                while (pos < n && count < flags) {
                    if (isPeak[pos]) { // 是一个山峰。不存在说为了得到最多的旗子数量，当前可以插旗，但是我为了得到最优解，要往下选的情况（想想为什么?）
                        count++;
                        pos += flags;// 下一个可选的位置，至少要间隔所带旗子的数量
                    } else {
                        pos++;
                    }
                }
                maxFlags = Math.max(maxFlags, count);
            }
            return maxFlags;
        }
    }

    /**
     * 对上面的解法，还能优化:
     * （1. 简单的一个优化，发现，最多能带的旗子数量，也不可能超过 sqrt(n) + 1  (因为间隔要求)  ）
     *
     * 2. 山峰数量小于等于 2。最大的可能就是山峰的数量
     * 3. 当前得出的最大的可以携带的旗子数量，已经大于剩余要尝试的旗子数量，可以不用往下尝试了
     *
     * Correctness 100 % ，Performance 100 % 。综合：Task Score：100 %
     */
    class Solution_3 {
        public int solution(int[] A) {
            int n = A.length;
            boolean[] isPeak = new boolean[n];
            int numPeaks = 0;
            for (int i = 1; i < n - 1; i++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    isPeak[i] = true;
                    numPeaks++;
                }
            }
            // 优化点 2：发现山峰数量小于等于 2，那么每个山峰都可以插旗，直接返回答案
            if (numPeaks <= 2) {
                return numPeaks;
            }

            int maxFlags = 0;
            // 明显，最多只能带 numPeaks 面旗，带多了没用。从最多的可能的数量往下试。
            // 优化点 1：简单的一个优化，发现，最多能带的旗子数量，也不可能超过 sqrt(n) + 1  (因为间隔要求)
            for (int flags = Math.min(numPeaks, (int) Math.sqrt(n) + 1); flags > 2; flags--) {
                int pos = 0; // 下一个要尝试插旗的位置
                int count = 0; // 插了多少面旗
                while (pos < n && count < flags) {
                    if (isPeak[pos]) { // 是一个山峰。不存在说为了得到最多的旗子数量，当前可以插旗，但是我为了得到最优解，要往下选的情况（想想为什么?）
                        count++;
                        pos += flags;// 下一个可选的位置，至少要间隔所带旗子的数量
                    } else {
                        pos++;
                    }
                }
                maxFlags = Math.max(maxFlags, count);
                // 优化点 3：当前的最大值，已经大于下一个可以选择的值，没必要继续往下尝试了 （可以加在前面的 while 循环条件里，这里方便理解，就放在这里）
                if (maxFlags >= flags - 1) {
                    break;
                }
            }
            return maxFlags;
        }
    }

    /**
     * 对上面的解法，还能优化 (这个有些难想！):
     *
     * 4. 记住每个位置，当前可以选择的下一个插旗位置（便于快速找到下一个插旗位置，而不是每次都一个一个去试）
     *
     * Correctness 100 % ，Performance 100 % 。综合：Task Score：100 %
     */
    class Solution_4 {
        public int solution(int[] A) {
            int n = A.length;
            boolean[] isPeak = new boolean[n];
            int numPeaks = 0;
            for (int i = 1; i < n - 1; i++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    isPeak[i] = true;
                    numPeaks++;
                }
            }
            // 优化点 2：发现山峰数量小于等于 2，那么每个山峰都可以插旗，直接返回答案
            if (numPeaks <= 2) {
                return numPeaks;
            }

            // 优化点 4：预处理，记住每个位置的下一个山峰位置，便于快速定位到下一个可选的插旗位置
            int[] nextPeak = new int[n];
            nextPeak[n - 1] = -1; // 最后一座山，不可能是山峰，不可能插旗。记为 -1
            // 从后往前赋值
            for (int i = n - 2; i >= 0; i--) {
                if (isPeak[i]) {
                    nextPeak[i] = i;
                } else {
                    nextPeak[i] = nextPeak[i + 1]; // 和它下一座山的值一样
                }
            }

            int maxFlags = 0;
            // 明显，最多只能带 numPeaks 面旗，带多了没用。从最多的可能的数量往下试。
            // 优化点 1：简单的一个优化，发现，最多能带的旗子数量，也不可能超过 sqrt(n) + 1  (因为间隔要求)
            for (int flags = Math.min(numPeaks, (int) Math.sqrt(n) + 1); flags > 2; flags--) {
                int pos = nextPeak[0]; // 下一个要尝试插旗的位置。利用 nextPeak 快速定位
                int count = 0; // 插了多少面旗
                while (pos != -1 // -1 说明后面已经没得选了
                        && count < flags
                        && maxFlags < flags) { // 优化点 3 (&& maxFlags < flags)：当前的最大值，已经大于下一个可以选择的值，没必要继续往下尝试了
                    count++;
                    if (pos + flags >= n) {// 越界了
                        break;
                    }
                    pos = nextPeak[pos + flags]; // 有间隔要求
                }
                maxFlags = Math.max(maxFlags, count);
            }
            return maxFlags;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(3, new Solution_1().solution(new int[] {1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}));
    }

}
