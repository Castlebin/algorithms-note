package ds.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    protected void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v) && i != h);
            while (less(v, nums[--j]) && j != l);
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    @Test
    public void testSort() {
        Integer[] arr = new Integer[]{4, 981, 10, -17, 0, -20, 29, 50, 8, 43, -5};
        Sort<Integer> sort = new QuickSort<>();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

