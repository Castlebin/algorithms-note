package redis;

import cn.hutool.core.util.StrUtil;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

public class BloomFilterTest {
    // 预计要插入的数量
    private static int expectedInsertions = 1000000;
    // 创建一个布隆过滤器，设置误判率为 0.01
    private static double fpp = 0.01;

    private static BloomFilter<Integer> bloomFilter
            = BloomFilter.create(Funnels.integerFunnel(), expectedInsertions, fpp);
    private static int testN = 100000;

    public static void main(String[] args) {
        List<Integer> error = new ArrayList<>();
        for (int i = 0; i < expectedInsertions; i++) {
            bloomFilter.put(i);
        }
        for (int num = expectedInsertions + 1; num < expectedInsertions + testN; num++) {
            if (bloomFilter.mightContain(num)) {
                error.add(num);
            }
        }
        System.out.println(StrUtil.format("error size={}, percent={}",
                error.size(), error.size()*1.0/testN));
    }

}
