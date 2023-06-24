package codility.lesson.L07;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 Nesting

 Determine whether a given string of parentheses (single type) is properly nested.

 https://app.codility.com/programmers/lessons/7-stacks_and_queues/nesting/

 还是一道括号匹配的问题，和第一题没啥区别，不过这里只包含一种类型的括号。还简单些
 */
public class T3 {

    public int solution(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return 0;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, solution("(()(())())"));
        Assert.assertEquals(0, solution("())"));
    }

}
