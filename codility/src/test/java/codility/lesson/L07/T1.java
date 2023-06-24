package codility.lesson.L07;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 Brackets

 Determine whether a given string of parentheses (multiple types) is properly nested.

 https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
 */
public class T1 {

    public int solution(String S) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
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
        Assert.assertEquals(1, solution("{[()()]}"));
        Assert.assertEquals(0, solution("([)()]"));
    }

}
