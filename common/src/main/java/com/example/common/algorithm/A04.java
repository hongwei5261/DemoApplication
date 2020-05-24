package com.example.common.algorithm;

public class A04 {
    public static void main(String[] args) {

        TreeNode node = func(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        TreeTest.preOrder(node);
        System.out.print("\n");
        TreeTest.inOrder(node);
    }

    /**
     * 重建二叉树
     * pre:1 2 4 7 3 5 6 8
     * in :4 7 2 1 5 3 8 6
     */
    private static TreeNode func(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length != inOrder.length || inOrder.length < 1) {
            return null;
        }
        return func(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private static TreeNode func(int[] preOrder, int ps, int pe, int[] inOrder, int is, int ie) {

        if (ps < 0 || ps > pe || ps >= preOrder.length) {
            return null;
        }

        int value = preOrder[ps];
        int index = is;
//        while (index <= ie && inOrder[index] != value) {
//            index++;
//        }
        int count = 0;
        for (int i = is; i < ie; i++) {
            count++;
            if (value == inOrder[i]) {
                index = i;
                break;
            }
        }

        TreeNode node = new TreeNode(value);
//        node.leftNode = func(preOrder, ps + 1, ps + count, inOrder, is, is + count);
//        node.rightNode = func(preOrder, preOrder.length - count - 1, pe, inOrder, index + 1, ie);

        node.leftNode = func(preOrder, ps + 1, ps + index - is, inOrder, is, index - 1);
        node.rightNode = func(preOrder, ps + index - is + 1, pe, inOrder, index + 1, ie);
        return node;
    }
}
