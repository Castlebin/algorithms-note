package leetcode.N800_N899;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.Assert;

/**
 * 887. 鸡蛋掉落
 */
public class T887 {

    Map<String, Integer> cache = new HashMap<>();

    public int superEggDrop(int k, int n) {
        if (k == 1) return n;
        if (n <= 1) return n;

        String key = k + "_" + n;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int res = n;
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int broken = superEggDrop(k - 1, mid - 1);    // 鸡蛋碎了
            int notBroken = superEggDrop(k, n - mid);     // 鸡蛋没碎
            
            // 当前 mid 层的最坏情况
            res = Math.min(res, Math.max(broken, notBroken) + 1);
            
            if (broken == notBroken) {
                break;
            } else if (broken < notBroken) {
                // 需要让最大值更小，所以往上层找
                left = mid + 1;
            } else {
                // 需要让最大值更小，所以往下层找
                right = mid - 1;
            }
        }
        
        cache.put(key, res);
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, superEggDrop(1, 2));
        Assert.assertEquals(3, superEggDrop(2, 6));
        Assert.assertEquals(4, superEggDrop(3, 14));
    }

}
