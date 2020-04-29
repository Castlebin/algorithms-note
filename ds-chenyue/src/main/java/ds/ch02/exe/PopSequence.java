package ds.ch02.exe;

import java.util.Scanner;
import java.util.Stack;

/**
    02-线性结构4 Pop Sequence (25分)
    Given a stack which can keep M numbers at most. Push N numbers in the order of 1, 2, 3, ..., N and pop randomly. You are supposed to tell if a given sequence of numbers is a possible pop sequence of the stack. For example, if M is 5 and N is 7, we can obtain 1, 2, 3, 4, 5, 6, 7 from the stack, but not 3, 2, 1, 7, 5, 6, 4.

    Input Specification:
    Each input file contains one test case. For each case, the first line contains 3 numbers (all no more than 1000): M (the maximum capacity of the stack), N (the length of push sequence), and K (the number of pop sequences to be checked). Then K lines follow, each contains a pop sequence of N numbers. All the numbers in a line are separated by a space.

    Output Specification:
    For each pop sequence, print in one line "YES" if it is indeed a possible pop sequence of the stack, or "NO" if not.

    Sample Input:
    5 7 5
    1 2 3 4 5 6 7
    3 2 1 7 5 6 4
    7 6 5 4 3 2 1
    5 6 4 3 7 2 1
    1 7 6 5 4 3 2

    Sample Output:
    YES
    NO
    NO
    YES
    NO
 */
public class PopSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String[] meta = firstLine.split("\\s+");
        int maxSize = Integer.parseInt(meta[0]);
        int nums = Integer.parseInt(meta[1]);
        int repeatTimes = Integer.parseInt(meta[2]);
        String[][] cases = new String[repeatTimes][];
        for (int i = 0; i < repeatTimes; i++) {
            cases[i] = sc.nextLine().split("\\s+");
        }
        for (String[] outputSeqCase : cases) {
            boolean possible = isPossibleOpSeq(maxSize, nums, outputSeqCase);
            System.out.println(possible?"YES":"NO");
        }
    }

    public static boolean isPossibleOpSeq(int stackSize, int nums, String[] outputSeq) {
        Stack<Integer> stack = new Stack<>();
        int numCur = Integer.parseInt(outputSeq[0]);
        if (numCur > stackSize) {
            return false;
        }
        for (int i = 1; i <= numCur; i++) {
            stack.push(i);
        }
        int i = 0;
        while (i < outputSeq.length) {
            int output = Integer.parseInt(outputSeq[i]);
            if (numCur >= output && !stack.isEmpty()) {
                if (stack.pop().compareTo(output) != 0) {
                    return false;
                }
                i++;
            } else {
                for (int num = numCur + 1; num <= output; num++) {
                    if (stack.size() >= stackSize) {
                        return false;
                    }
                    stack.push(num);
                }
                if (output > numCur) {
                    numCur = output;
                }
            }
        }
        if (i == outputSeq.length) {
            return true;
        }

        return false;
    }

}
