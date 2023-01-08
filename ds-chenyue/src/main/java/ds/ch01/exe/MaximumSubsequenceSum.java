package ds.ch01.exe;

/**
 * 最大子列和问题，注意，除了最大子列和之外，还需要输出子列的第一个和最后一个元素的值
 *
 * 注意 前导0的情况、全是负数的情况
 *
 * 2004年浙江大学计算机专业考研复试真题
 */

import java.util.Scanner;

public class MaximumSubsequenceSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String secondLine = sc.nextLine();

        int[] array = new int[Integer.parseInt(firstLine)];
        String[] nums = secondLine.split(" ");
        int i = 0;
        for (String num : nums) {
            if (i < array.length) {
                array[i++] = Integer.parseInt(num);
            }
        }

        maxSubSeqSum_4(array);
    }

    public static void maxSubSeqSum_4(int[] array) {
        int maxSubSeqSum = -1;
        int subSeqSum = 0;
        boolean allNonPositive = true;
        int begin = -1, end = 1, possibleBegin = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
                allNonPositive = false;
                if (possibleBegin == -1) {
                    possibleBegin = i;
                }
            }
            subSeqSum += array[i];
            if (subSeqSum > maxSubSeqSum) {
                maxSubSeqSum = subSeqSum;
                end = i;
                begin = possibleBegin;
            } else if (subSeqSum < 0) {
                subSeqSum = 0;
                possibleBegin = -1;
            }
        }
        if (allNonPositive) {
            System.out.println(0 + " " + array[0] + " " + array[array.length - 1]);
        } else {
            System.out.println(maxSubSeqSum + " " + array[begin] + " " + array[end]);
        }
    }
}
