package leetcode;

import java.util.Arrays;

public class N004 {
    public static void main(String[] args) {
        N004 n004 = new N004();
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] nums2 = new int[]{1, 2, 3, 4};
        System.out.println(n004.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] all = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        for (; i < nums1.length && j < nums2.length; k++) {
            if (nums1[i] < nums2[j]) {
                all[k] = nums1[i++];
            } else {
                all[k] = nums2[j++];
            }
        }
        if (i == nums1.length) {//nums1 全部添加完毕
            for (;j<nums2.length;k++) {
                all[k] = nums2[j++];
            }
        } else if (j == nums2.length) {// nums2 全部添加完毕（其实只能是这两者之一）
            for (;i<nums1.length;k++) {
                all[k] = nums1[i++];
            }
        }

        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        System.out.println("all: " + Arrays.toString(all));

        if (all.length % 2 == 1) {// 奇数个元素，取中间
            return all[(all.length - 1) / 2];
        } else {
            return (all[all.length / 2] + all[all.length / 2 - 1]) / 2.0;
        }
    }
}
