package leetcode;

/**
    4. Median of Two Sorted Arrays
 There are two sorted arrays nums1 and nums2 of size m and n respectively.

 Find the median of the two sorted arrays. The overall run time complexity should be    O(log (m+n)).

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0
 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]

 The median is (2 + 3)/2 = 2.5
 */
public class N004 {
    public static void main(String[] args) {
        N004 n004 = new N004();
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] nums2 = new int[]{1, 2, 3, 4};
        System.out.println(n004.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }
}
