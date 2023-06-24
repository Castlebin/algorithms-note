package codility.lesson.L07;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 Fish

 N voracious fish are moving along a river. Calculate how many fish are alive.

 https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
 */
public class T2 {

    public int solution(int[] A, int[] B) {
        // 把往下游的鱼放到栈里(相当于括号匹配里的左括号)，
        // 看看接下来的鱼，相向而行的(往上游的)(相当于右括号)，哪些能活下来
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            // 如果当前这条鱼是往上游的
            if (B[i] == 0) {
                // 如果它前面的鱼是往下游的，并且，体型比它小，那么，那些鱼都要被吃掉
                while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                    stack.pop();
                }
                // 如果它的前进方向，根本没有鱼，那么它是可以存活的
                if (stack.isEmpty()) {
                    count++;
                }
            } else {
                // 这条鱼是往下游的，入栈
                stack.push(i);
            }
        }
        return count + stack.size();
    }

    @Test
    public void test() {
        Assert.assertEquals(2, solution(new int[] {4, 3, 2, 1, 5}, new int[] {0, 1, 0, 0, 0}));
    }

}
