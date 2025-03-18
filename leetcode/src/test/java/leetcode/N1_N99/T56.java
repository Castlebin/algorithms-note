package leetcode.N1_N99;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 56. 合并区间
 * https://leetcode.cn/problems/merge-intervals/
 */
public class T56 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a1, a2) -> {
            return a1[0] != a2[0] ? a1[0] - a2[0] : a1[1] - a2[1];
        });

        List<int[]> list = new ArrayList<>(intervals.length);
        list.add(intervals[0]);
        for (int index = 1; index < intervals.length; index++) {
            int[] cur = intervals[index];
            int[] prev = list.get(list.size() - 1);
            if (cur[0] <= prev[1]) { // 相交
                prev[1] = Math.max(prev[1], cur[1]);
            } else {
                list.add(cur);
            }
        }

        int[][] result = new int[list.size()][];
        for (int index = 0; index < result.length; index++) {
            result[index] = list.get(index);
        }
        return result;
    }

    @Test
    public void test() {
        int[][] result = merge(new int[][]{{1, 4}, {2, 3}});
        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("====");
        result = merge(new int[][]{{1, 2}, {3, 4}, {5, 6}});
        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

}
