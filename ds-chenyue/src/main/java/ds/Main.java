package ds;

import java.util.Scanner;

public class Main {
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
        int begin = -1, end = 1, possibleBegin = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
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
        if (maxSubSeqSum == -1) {
            System.out.println(0 + " " + array[0] + " " + array[array.length - 1]);
        } else {
            System.out.println(maxSubSeqSum + " " + array[begin] + " " + array[end]);
        }
    }
}