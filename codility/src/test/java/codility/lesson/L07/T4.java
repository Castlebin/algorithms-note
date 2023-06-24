package codility.lesson.L07;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 StoneWall

 Cover "Manhattan skyline" using the minimum number of rectangles.

 https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
 */
public class T4 {

    /**
     * TODO 理解该算法
     */
    public int solution(int[] H) {
        int blocks = 0;
        Stack<Integer> stack = new Stack<>();
        // 遍历数组中所有的高度
        for (int i = 0; i < H.length; i++) {
            // 将所有高度大于当前高度的块弹出栈
            while (!stack.isEmpty() && stack.peek() > H[i]) {
                stack.pop();
            }
            // 如果栈为空或者栈顶的高度小于当前高度，则将当前高度推入栈中，并增加块数
            if (stack.isEmpty() || stack.peek() < H[i]) {
                stack.push(H[i]);
                blocks++;
            }
        }
        return blocks;
    }

    @Test
    public void test() {
        Assert.assertEquals(7, solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8}));
    }

}
