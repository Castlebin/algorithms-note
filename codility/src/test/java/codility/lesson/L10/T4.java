package codility.lesson.L10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 Peaks

 Divide an array into the maximum number of same-sized blocks, each of which should contain an index P such that A[P - 1] < A[P] > A[P + 1].

 https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
 */
public class T4 {

    /**
     * 划分一个数据成均匀的 m 段，使得每段里，至少有一个峰值。给定一个长度大于等于 1 的数组，求最大的 m。
     */
    class Solution_1 {// Detected time complexity: O(N * log(log(N)))
        public int solution(int[] A) {
            int n = A.length;
            // 先标记出所有的山峰，还有山峰的数量
            int numPeaks = 0;
            boolean[] isPeak = new boolean[n];
            for (int i = 1; i < n - 1; i++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    isPeak[i] = true;
                    numPeaks++;
                }
            }
            // 如果小于等于 1 ，直接返回
            if (numPeaks <= 1) {
                return numPeaks;
            }

            // 从最大的划分数量开始，发现满足条件，就可以直接返回 （numPeaks 是不可能大于 n / 2 的，所以不用在 numPeaks 和 n / 2 中取较小的值）（parts = 1 不用算，是最后的默认值）
            for (int parts = numPeaks; parts >= 2; parts--) {
                if (n % parts != 0) {// 不能整除
                    continue;
                }
                int partLen = n / parts; // 划分段的长度
                // 看看每个段，是否都有一个山峰
                boolean canDivided = true;
                for (int i = 0; i < n && canDivided; i += partLen) {
                    for (int j = i; j < i + partLen && canDivided; j++) {
                        // 看看 [i, i + partLen) 这一段
                        if (isPeak[j]) {// 这一段已经有一个山峰了，不用检查这个段之后的了，直接开始检查下一个段
                            break;
                        }
                        if (j == i + partLen - 1) {// 这一段试到最后一个元素，都没有一个峰值
                            canDivided = false; // 这一段没有峰值，不用再往下试了。不符合要求
                        }
                    }
                }
                if (canDivided) {// 因为是从最大的开始试的，所以发现满足条件，直接返回就行了
                    return parts;
                }
            }

            // 到了这里，numPeaks 是大于 1 的，所以，至少可以分成 1 段。而不是 0
            return 1;
        }
    }

    class Solution_2 {// Detected time complexity: O(N * log(log(N)))
        public int solution(int[] A) {
            int n = A.length;
            // 记下所有山峰的位置（还有总数量，即 size）
            Set<Integer> peaks = new HashSet<>();
            for (int i = 1; i < n - 1; i++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    peaks.add(i);
                }
            }
            // 如果山峰的数量小于等于 1，直接就是返回答案
            if (peaks.size() <= 1) {
                return peaks.size();
            }

            // 从最大的划分数量开始，发现满足条件，就可以直接返回 （numPeaks 是不可能大于 n / 2 的，所以不用在 numPeaks 和 n / 2 中取较小的值）（parts = 1 不用算，是最后的默认值）
            for (int parts = peaks.size(); parts >= 2; parts--) {
                if (n % parts != 0) {// 不能整除
                    continue;
                }
                int partLen = n / parts; // 划分段的长度
                boolean[] checked = new boolean[parts]; // 标记划分的每一段，是否已经判断过了
                int checkCount = 0; // 标记检查发现满足条件的段的数量
                // 直接用山峰的位置，来快速判断每个段是否满足条件
                for (int peak : peaks) {
                    // 用除法，快速判断这个山峰，落在哪个段里（记住这个技巧！！！）
                    int peakPart = peak / partLen; // 这个山峰落在的段
                    if (!checked[peakPart]) {
                        checked[peakPart] = true; // 这个段通过检查了，之后，不用检查，不能计数
                        checkCount++;
                    }
                    // 发现所有的段，都已经通过检查了，都满足条件。那么，返回答案
                    if (checkCount == parts) {
                        return parts;
                    }
                }
            }

            // 到这里，至少有一个山峰，所以至少是能划分为 1 段的。而不是返回 0
            return 1;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(3, new Solution_1().solution(new int[] {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}));
    }

}
