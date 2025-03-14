package ds.ch09;

import static ds.ArrayUtil.generateNonNegativeArray;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class ShellSort {

    public void sort(int[] array) {
        int swapCount = 0;
        // 这里只是为了简单，采取了每次除2这种生成递减间隔的策略
        for (int dk = array.length / 2; dk >= 1; dk /= 2) {
            System.out.println(dk + "间隔排序");

            // 内层的循环就是一个dk间隔的插入排序
            for (int p = dk; p < array.length; p++) {
                // 新牌先拿到手里
                int tmp = array[p];
                // 与现有的排序好的牌进行比较，从右到左，给新牌找个合适的位置插进去
                int i = p;
                for (; i >= dk && array[i - dk] > tmp; i -= dk) {
                    // 将牌往后移，移出一个空位
                    array[i] = array[i - dk];
                    swapCount++;
                }
                array[i] = tmp;
            }
        }

        System.out.println("ShellSort 经过交换次数 swapCount = " + swapCount);
    }

    @Test
    public void testSort() {
        ShellSort shellSort = new ShellSort();
        InsertSort insertSort = new InsertSort();
        for (int i = 0; i < 10; i++) {
            int[] array = generateNonNegativeArray(100, 10000);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));
            System.out.println("copy  : " + Arrays.toString(array));

            shellSort.sort(array);
            insertSort.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals(array, copy);
            Assert.assertArrayEquals(array, copy2);
        }
    }

}
