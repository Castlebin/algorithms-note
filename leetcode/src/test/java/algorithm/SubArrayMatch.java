package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SubArrayMatch {

    public List<List<Long>> matchSubArray(List<Long> a, List<Long> b) {
        a.sort(Long::compareTo);
        b.sort(Long::compareTo);
        List<List<Long>> result = new ArrayList<>();
        int ia = 0, ib = 0, beginA = 0, endA = 0;
        boolean match = false;
        while (ia < a.size() && ib < b.size()) {
            if (a.get(ia).equals(b.get(ib))) {
                if (!match) {
                    beginA = ia;
                }
                endA = ia;
                ia++;
                ib++;
                match = true;
            } else {
                if (match) {
                    result.add(a.subList(beginA, endA + 1));
                }
                match = false;
                long t = a.get(ia) > b.get(ib) ? ib++ : ia++;
            }
        }
        if (match) {
            result.add(a.subList(beginA, endA + 1));
        }
        return result;
    }

    @Test
    public void test() {
        // 对于没有重复元素的排序列表，结果是正确的，一旦有重复元素，就不行了，算法待改进
        List<Long> a = Arrays.asList(1L, 2L, 3L, 4L, 5L, 9L, 10L, 12L, 13L, 14L);
        List<Long> b = Arrays.asList(2L, 3L, 5L, 6L, 9L, 10L, 11L, 12L, 13L, 14L);
        List<List<Long>> result = matchSubArray(a, b);
        System.out.println(result);
    }

}
