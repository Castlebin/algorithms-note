package leetcode;

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
            if (a.get(ia) > b.get(ib)) {
                if (match) {
                    result.add(a.subList(beginA, endA + 1));
                }
                match = false;
                ib++;
            } else if (a.get(ia) < b.get(ib)) {
                if (match) {
                    result.add(a.subList(beginA, endA + 1));
                }
                match = false;
                ia++;
            } else {
                if (!match) {
                    beginA = ia;
                }
                endA = ia;
                ia++;
                ib++;
                match = true;
            }
        }
        if (match) {
            result.add(a.subList(beginA, endA + 1));
        }
        return result;
    }

    @Test
    public void test() {
        List<Long> a = Arrays.asList(1L, 2L, 3L, 4L, 5L, 9L, 10L, 12L, 13L, 14L);
        List<Long> b = Arrays.asList(2L, 3L, 5L, 6L, 9L, 10L, 11L, 12L, 13L, 14L);
        List<List<Long>> result = matchSubArray(a, b);
        System.out.println(result);
    }

}
