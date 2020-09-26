package sword;

import org.junit.Test;

/**
 面试题64. 求1+2+…+n

 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

 不能用 if 语句、也不能用 for 这些。。。。
 所以，可以使用递归来进行循环，但是，递归必须要有终止条件，也即是 if 语义

 所以，用 && 短路 特性 来实现 if 语义
*/
public class S064 {

    /**
     * 使用 && 的短路特性实现递归效果
     */
    public int sumNums(int n) {
        int sum = n;
        boolean flag = n > 0 && (sum += sumNums(n - 1)) > 0;
        return sum;
    }

    @Test
    public void test() {

    }

}
