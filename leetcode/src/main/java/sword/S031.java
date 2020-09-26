package sword;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 剑指 Offer 31. 栈的压入、弹出序列
 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 假设压入栈的所有数字均不相等。

 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 */
public class S031 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if ( pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0, popIndex = 0;
        while (pushIndex < pushed.length && popIndex < popped.length) {
            while (pushIndex < pushed.length && popIndex < popped.length
                   && popped[popIndex] != pushed[pushIndex]) {
                stack.push(pushed[pushIndex++]);
            }
            pushIndex++;
            popIndex++;
            while (!stack.isEmpty() && popIndex < popped.length) {
                if (stack.peek() == popped[popIndex]) {
                    stack.pop();
                    popIndex++;
                } else {
                    break;
                }
            }
        }
        while (!stack.isEmpty() && popIndex < popped.length) {
            if (stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                break;
            }
        }
        if (stack.isEmpty() && popIndex == popped.length) {
            return true;
        }

        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
        Assert.assertFalse(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}));
        Assert.assertFalse(validateStackSequences(new int[]{8,9,2,3,7,0,5,4,6,1}, new int[]{6,8,2,1,3,9,0,7,4,5}));
    }

}
