package com.example.common.algorithm;

public class A12 {
    public static void main(String[] args) {

        ListNode head = ListNodeTest.create(new int[]{1, 2, 3, 8, 7, 6});
        ListNode node = func(head, 4);
        if (node != null) {
            System.out.print(node.value);
        }
    }

    private static ListNode func(ListNode head, int n) {
        if (n <= 0 || head == null) {
            return null;
        }
        ListNode xNode = head;
        ListNode yNode = head;

        while (n > 1 && xNode != null) {
            xNode = xNode.next;
            n--;
        }

        if (xNode == null) {
            return null;
        }

        while (xNode.next != null) {
            xNode = xNode.next;
            yNode = yNode.next;
        }


        return yNode;
    }
}
