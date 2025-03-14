package ds.sort;

/**
 * 基于三向切分的快速排序算法
 */
public class ThreeWayQuickSort<T extends Comparable<T>> extends QuickSort<T> {

    @Override
    protected void sort(T[] nums, int begin, int end) {
        if (end <= begin) {
            return;
        }
        int left = begin, index = begin + 1, right = end;
        T v = nums[begin];
        while (index <= right) {
            int cmp = nums[index].compareTo(v);
            if (cmp < 0) {
                swap(nums, left++, index++);
            } else if (cmp > 0) {
                swap(nums, index, right--);
            } else {
                index++;
            }
        }
        sort(nums, begin, left - 1);
        sort(nums, right + 1, end);
    }

}
