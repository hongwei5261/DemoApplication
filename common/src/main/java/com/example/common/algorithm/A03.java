package com.example.common.algorithm;

import java.util.Stack;

public class A03 {
    public static void main(String[] args) {

        Node root = new Node(0);
        Node node1 = new Node(1);
        root.next = node1;
        Node node2 = new Node(2);
        node1.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;
        Node node4 = new Node(4);
        node3.next = node4;
        Node node5 = new Node(5);
        node4.next = node5;
        Node node6 = new Node(6);
        node5.next = node6;
        Node node7 = new Node(7);
        node6.next = node7;
        Node node8 = new Node(8);
        node7.next = node8;
        Node node9 = new Node(9);
        node8.next = node9;

        func(root);
    }

    /**
     * 反转打印内容
     *
     * @param root 头节点
     */
    private static void func(Node root) {
        Stack<Node> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.next;
        }

        Node node;
        while (!stack.empty() && (node = stack.pop()) != null) {
            System.out.println(node.item);
        }
    }

    private static class Node {
        int item;

        public Node(int item) {
            this.item = item;
        }

        Node next;
    }
}
