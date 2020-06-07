package com.example.common.algorithm;

public class A14 {
    public static void main(String[] args) {

        ListNode head1 = ListNodeTest.create(new int[]{1, 2, 3, 4, 5, 6});
        ListNode head2 = ListNodeTest.create(new int[]{2, 2, 5, 8, 9, 10});

        ListNodeTest.printList(func1(head1, head2));
    }

    /**
     * 递归实现
     */
    private static ListNode func1(ListNode head1, ListNode head2) {
        ListNode head = null;
        ListNode end = null;
        if (head1 == null && head2 == null) {

        } else if (head1 == null && head2 != null) {
            head = head2;
        } else if (head1 != null && head2 == null) {
            head = head1;
        } else {
            if (head1.value < head2.value) {
                head = head1;
                head1 = head.next;
            } else {
                head = head2;
                head2 = head.next;
            }
            end = head;
            while (head1 != null && head2 != null) {
                if (head1.value < head2.value) {
                    end.next = head1;
                    head1 = head1.next;
                    end = end.next;
                } else {
                    end.next = head2;
                    head2 = head2.next;
                    end = end.next;
                }
            }

            if (head1 == null && head2 == null) {

            } else if (head1 == null && head2 != null) {
                end.next = head2;
            } else {
                end.next = head1;
            }
        }
        return head;
    }

    private static ListNode func2(ListNode head1, ListNode head2) {
        ListNode head = null;
        // TODO

        return head;
    }
}
