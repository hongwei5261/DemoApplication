package com.example.common.algorithm;

public class A10 {
    public static void main(String[] args) {
        ListNode head = ListNodeTest.create(new int[]{1, 2, 3, 8, 7, 6});
        ListNode h2 = deleteNode(head, ListNodeTest.getNodeByIndex(head, 4));
        ListNodeTest.printList(h2);

    }

    private static ListNode deleteNode(ListNode head, ListNode deleteNode) {
        if (head == null || deleteNode == null) {
            return null;
        }

        if (head == deleteNode) {
            return head.next;
        }

        if (deleteNode.next == null) {
            ListNode p = head;
            while (p.next != deleteNode) {
                p = p.next;
            }
            p.next = null;
        } else {
            deleteNode.value = deleteNode.next.value;
            deleteNode.next = deleteNode.next.next;
        }

        return head;
    }
}
