package com.example.common.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考：https://blog.csdn.net/weixin_40236948/article/details/82152468
 */
public class TreeTest {
    public static void main(String[] args) {
//        Node root = new Node(28);
//        insert(root, 16);
//        insert(root, 30);
//        insert(root, 13);
//        insert(root, 22);
//        insert(root, 29);
//        insert(root, 42);
        TreeNode root = createTree(new int[]{28, 16, 30, 13, 22, 29, 42});
        preOrder(root);
        System.out.print("\n");
        inOrder(root);
        System.out.print("\n");
        postOrder(root);
        System.out.print("\n");
        levelOrder(root);
    }

    public static TreeNode createTree(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            insert(root, arr[i]);
        }
        return root;
    }


    public static TreeNode insert(TreeNode node, int item) {
        if (node == null) {
            return new TreeNode(item);
        }

        if (node.item == item) {
            node.item = item;
        } else if (node.item > item) {
            node.leftNode = insert(node.leftNode, item);
        } else {
            node.rightNode = insert(node.rightNode, item);
        }
        return node;
    }


    /**
     * 前序遍历
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.item + ", ");
            preOrder(node.leftNode);
            preOrder(node.rightNode);
        }
    }


    /**
     * 中序遍历
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.leftNode);
            System.out.print(node.item + ", ");
            inOrder(node.rightNode);
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.leftNode);
            postOrder(node.rightNode);
            System.out.print(node.item + ", ");
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);

        while (!list.isEmpty()) {
            TreeNode node = list.get(0);
            System.out.print(node.item + ", ");
            list.remove(0);
            if (node.leftNode != null) {
                list.add(node.leftNode);
            }

            if (node.rightNode != null) {
                list.add(node.rightNode);
            }
        }

    }
}
