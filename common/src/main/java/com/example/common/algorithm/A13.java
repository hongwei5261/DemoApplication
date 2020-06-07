package com.example.common.algorithm;

public class A13 {
    public static void main(String[] args) {

        ListNode head = ListNodeTest.create(new int[]{1, 2, 3, 8, 7, 6});

        ListNodeTest.printList(func2(head));
    }

    /**
     * 递归实现
     */
    private static ListNode func1(ListNode current) {
        if (current == null || current.next == null) {
            return current;
        }

        ListNode next = current.next;
        current.next = null;
        ListNode resetNode = func1(next);
        next.next = current;

        return resetNode;
    }

    private static ListNode func2(ListNode head) {
        if(head == null) {
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        head.next = null;
        head = pre;


        return head;
    }
}
