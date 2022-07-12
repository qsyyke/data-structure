package xyz.xcye.stack;

import java.util.Arrays;

/**
 * @author qsyyke
 * @date Created in 2022/7/9 08:20
 */


public class StackDemo {
    public static void main(String[] args) {
        StackArray stackArray = new StackArray(3);
        stackArray.push(2);
        stackArray.push(3);
        stackArray.push(4);

        stackArray.listStack();

        System.out.println("----------");
        stackArray.pop();
        stackArray.listStack();
        System.out.println("----------");
        stackArray.push(7);
        stackArray.listStack();
    }
}

class StackArray {
    private int maxSize;
    private int top;
    private int[] stackArray;

    public StackArray(int maxSize) {
        this.maxSize = maxSize;
        stackArray = new int[maxSize];
        top = -1;
    }

    private boolean isFull() {
        return top == stackArray.length - 1;
    }

    public void push(int stackNum) {
        if (isFull()) {
            throw new RuntimeException("栈满了");
        }
        stackArray[++top] = stackNum;
    }

    public void pop() {
        if (top == -1) {
            throw new RuntimeException("栈空");
        }
        stackArray[top--] = 0;
    }

    public void listStack() {
        Arrays.stream(stackArray).forEach(System.out::println);
    }
}
