package ds.ch10;

import static ds.ArrayUtil.generateNonNegativeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ds.ch09.InsertSort;

/**
 * 基数排序
 *
 * 次位优先（Least Significant Digit）
 *
 * 简单实现，用于 **非负整数** 数组的排序
 */
public class RadixSortLSD {
    private static final int RADIX = 10;

    public void radixSort(int[] nums) {
        // 初始化一个 桶 数组
        BucketNode[] bucket = new BucketNode[RADIX];

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        int maxNumLength = getMaxNumLength(nums);
        for (int d = 1; d <= maxNumLength; d++) {
            initBucket(bucket);

            for (Integer num : list) {
                int digit = getDigit(num, d);
                Node node = new Node();
                node.key = num;
                if (bucket[digit].head == null) {
                    bucket[digit].head = node;
                    bucket[digit].tail = node;
                } else {
                    bucket[digit].tail.next = node;
                    bucket[digit].tail = node;
                }
            }

            // 现在可以将 bucket 中的元素，收集到 list 中了
            list.clear();
            for (int i = 0; i < bucket.length; i++) {
                Node cur = bucket[i].head;
                while (cur != null) {
                    list.add(cur.key);
                    cur = cur.next;
                }
            }
        }

        // list 中为已经排序好的数字了
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }

    private void initBucket(BucketNode[] bucket) {
        // 初始化桶里的元素
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new BucketNode();
        }
    }

    /* 桶元素结点 */
    class Node {
        public int key;
        public Node next;
    }

    class BucketNode {
        public Node head;
        public Node tail;
    }

    public int getMaxNumLength(int[] nums) {
        int maxNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
        }
        return getNumLength(maxNum);
    }

    public int getNumLength(int x) {
        int len = 0;
        while (x > 0) {
            len++;
            x /= RADIX;
        }
        return len;
    }

    public int getDigit(int x, int d) {
        // 获取 数字 x 的 第 d 位数字（个位为第1位）
        int r = x;
        for (int i = 1; i <= d; i++) {
            r = x % RADIX;
            x /= RADIX;
        }
        return r;
    }

    @Test
    public void test() {
        RadixSortLSD radixSort = new RadixSortLSD();
        InsertSort insertSort = new InsertSort();

        radixSort.radixSort(new int[]{64, 8, 216, 512, 27, 729, 0, 1, 343, 125});

        for (int i = 0; i < 10; i++) {
            int[] array = generateNonNegativeArray(10, 1000);
            int[] copy = Arrays.copyOfRange(array, 0, array.length);
            int[] copy2 = Arrays.copyOfRange(array, 0, array.length);
            System.out.println("origin: " + Arrays.toString(array));

            radixSort.radixSort(array);
            insertSort.sort(copy2);
            System.out.println("sorted: " + Arrays.toString(array));
            Arrays.sort(copy);

            Assert.assertArrayEquals(array, copy);
            Assert.assertArrayEquals(array, copy2);
        }
    }

}
