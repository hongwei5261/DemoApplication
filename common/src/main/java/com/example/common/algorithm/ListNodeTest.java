package com.example.common.algorithm;

public class ListNodeTest {

    public static void main(String[] args) {
        ListNode head = create(new int[]{1, 2, 3, 8, 7, 6});
        printList(head);
        System.out.println(getNodeByIndex(head, 0).value);
        System.out.println(getNodeByIndex(head, 1).value);
        System.out.println(getNodeByIndex(head, 2).value);
        System.out.println(getNodeByIndex(head, 3).value);
        System.out.println(getNodeByIndex(head, 4).value);
        System.out.println(getNodeByIndex(head, 5).value);
    }

    public static ListNode getNodeByIndex(ListNode head, int index) {
        ListNode p = head;
        index--;
        while (p != null && index >= 0) {
            p = p.next;
            index--;
        }

        return p;
    }

    public static void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.value + ", ");
            p = p.next;
        }
        System.out.println("\n");
    }

    public static ListNode create(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        ListNode head = new ListNode();
        head.value = arr[0];
        ListNode p = head;

        for (int i = 1; i < arr.length; i++) {
            ListNode temp = new ListNode();
            temp.value = arr[i];
            p.next = temp;
            p = temp;
        }
        return head;
    }
}
