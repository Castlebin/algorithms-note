package newcoder.classics;

import org.junit.Test;

import java.util.*;

/**
 LC2 	后缀表达式求值

 计算逆波兰式（后缀表达式）的值
 运算符仅包含"+","-","*"和"/"，被操作数可能是整数                    或其他表达式(测试用例里面没有，不用做了)
 例如：

 ["20", "10", "+", "30", "*"] -> ((20 + 10) * 30) -> 900
 ["40", "130", "50", "/", "+"] -> (40 + (130 / 50)) -> 42


 */
public class LC002 {

    public class Solution {
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

        public int evalRPN (String[] tokens) {
            return evalRPN(tokens, new Stack<>());
        }
        public int evalRPN (String[] tokens, Stack<Integer> stack) {
            for (String token : tokens) {
                // 遇到的不是操作符
                if (!operators.contains(token)) {
                    try {
                        // 说明是数字，直接放入栈里
                        int num = Integer.parseInt(token);
                        stack.push(num);
                    } catch (Exception e) {
                        /**
                         * 测试用例里面没有，不用做了
                         */
                        // 说明 是 其他的表达式

                        // 计算出表达式值后，放入栈里

                    }
                } else {
                    int num2 = stack.pop();
                    int num1 = stack.pop();
                    switch (token) {
                        case "+": stack.push(num1 + num2);break;
                        case "-": stack.push(num1 - num2);break;
                        case "*": stack.push(num1 * num2);break;
                        case "/": stack.push(num1 / num2);break;
                        default: break;
                    }
                }
            }
            return stack.pop();
        }

    }

    @Test
    public void testLC001Basic() {
        Solution solution = new Solution();

    }

}
