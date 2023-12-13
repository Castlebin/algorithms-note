package codility.lesson.L05;

import org.junit.Assert;
import org.junit.Test;

/**
 GenomicRangeQuery
 Find the minimal nucleotide from a range of sequence DNA

 https://app.codility.com/programmers/lessons/5-prefix_sums/genomic_range_query/

 巧妙的使用 前缀和 思想。时间复杂度 O(N)
 灵活使用前缀和思想，多个前缀和。分别求出 A、C、G 个数的前缀和。然后依次分别看区间内是否有 A、C、G
 */
public class T3 {

    class Solution {
        public int[] solution(String S, int[] P, int[] Q) {
            // 分别求出 A、C、G 个数的前缀和
            int[][] counter = new int[3][S.length() + 1];
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                counter[0][i + 1] = counter[0][i] + (c == 'A' ? 1 : 0);
                counter[1][i + 1] = counter[1][i] + (c == 'C' ? 1 : 0);
                counter[2][i + 1] = counter[2][i] + (c == 'G' ? 1 : 0);
            }
            int[] result = new int[P.length];
            for (int i = 0; i < P.length; i++) {
                // 如果子串中有 A 则是 1，依次类推
                if (counter[0][Q[i] + 1] - counter[0][P[i]] > 0) {
                    result[i] = 1;
                } else if (counter[1][Q[i] + 1] - counter[1][P[i]] > 0) {
                    result[i] = 2;
                } else if (counter[2][Q[i] + 1] - counter[2][P[i]] > 0) {
                    result[i] = 3;
                } else {
                    result[i] = 4;
                }
            }
            return result;
        }
    }

    @Test
    public void test() {
        Solution s = new Solution();
        int[] result = s.solution("CAGCCTA", new int[] {2, 5, 0}, new int[] {4, 5, 6});
        Assert.assertArrayEquals(new int[] {2, 4, 1}, result);
    }

}
