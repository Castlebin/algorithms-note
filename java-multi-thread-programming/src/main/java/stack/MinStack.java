package stack;

import java.util.Objects;
import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minItemStack = new Stack<>();

    public Integer push(Integer item) {
        if (minItemStack.empty() || item <= minItemStack.peek()) {
            minItemStack.push(item);
        }
        return stack.push(item);
    }

    public Integer pop() {
        Integer item = stack.pop();
        if (Objects.equals(item, minItemStack.peek())) {
            minItemStack.pop();
        }
        return item;
    }

    public Integer getMin() {
        return minItemStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(5);
        minStack.push(6);
        minStack.push(7);
        minStack.push(2);
        minStack.push(2);
        minStack.push(1);
        minStack.push(1);

        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
    }

}
