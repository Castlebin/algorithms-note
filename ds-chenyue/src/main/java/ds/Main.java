package ds;

import java.util.Scanner;
import java.util.Stack;

public class Main {
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
