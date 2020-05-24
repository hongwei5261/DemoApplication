package com.example.common.algorithm;

import java.util.Stack;

public class A05 {
    static Stack<String> stack1 = new Stack<>();
    static Stack<String> stack2 = new Stack<>();

    public static void main(String[] args) {
        add("11");
        add("22");
        add("33");
        pop();
        pop();
        add("44");
        pop();
        pop();
        pop();
    }

    private static void add(String value) {
        stack1.add(value);
        System.out.println("add:" + value);
    }

    private static String pop() {
        String value = null;
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                System.out.println("stack1 and stack2 is empty");
            } else {
                while (!stack1.isEmpty()) {
                    stack2.add(stack1.pop());
                }
                value = stack2.pop();
            }
        } else {
            value = stack2.pop();
        }
        System.out.println("pop:" + value);
        return value;
    }
}
