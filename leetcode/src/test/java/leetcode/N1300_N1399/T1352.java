package leetcode.N1300_N1399;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


/**
 * 1352. 最后 K 个数的乘积
 * ！！ 注意，数字可能为 0 ！！所以不能简单的直接用前缀乘法的思想
 * 采用前缀和 + 前缀乘积 的思想。并且对前缀乘法的 0 做特殊处理
 * 1. 如果有 0 。那么乘积一定为 0
 * 2. 如果没有 0。那么用前缀乘积做除法即可
 */
public class T1352 {

    class ProductOfNumbers {
        private final List<Integer> nums = new ArrayList<>();
        // 前缀和的思想，保留前缀 0 的个数
        private final List<Integer> preZeros = new ArrayList<>();
        // 保留前缀乘积。如果前面的前缀乘积为 0，设置为 1。这样不会影响后面的乘法和除法运算
        private final List<Integer> prods = new ArrayList<>();

        public ProductOfNumbers() {
            preZeros.add(0); // 都给 0 位置初始化一个合适的值，避免后面 add 时候对 0 位置 多做处理
            prods.add(1);
        }

        public void add(int num) {
            nums.add(num);
            if (num == 0) {
                preZeros.add(preZeros.get(preZeros.size() - 1) + 1);
                prods.add(1); // 0 的话，乘积后面都会为 0 ，无法计算了 。将它变成 1
            } else {
                preZeros.add(preZeros.get(preZeros.size() - 1));
                prods.add(prods.get(prods.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            if (k == 1) {
                return nums.get(nums.size() - 1);
            }
            int n = nums.size();
            if (preZeros.get(n) - preZeros.get(n - k) > 0) {
                return 0;
            }
            return prods.get(n) / prods.get(n - k);
        }
    }

    @Test
    public void test_1() {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);
        productOfNumbers.add(0);
        productOfNumbers.add(2);
        productOfNumbers.add(5);
        productOfNumbers.add(4);
        Assert.assertEquals(20, productOfNumbers.getProduct(2));
        Assert.assertEquals(40, productOfNumbers.getProduct(3));
        Assert.assertEquals(0, productOfNumbers.getProduct(4));
        productOfNumbers.add(8);
        Assert.assertEquals(32, productOfNumbers.getProduct(2));
    }

}
