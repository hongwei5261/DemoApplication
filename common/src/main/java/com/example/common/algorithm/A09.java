package com.example.common.algorithm;

public class A09 {
    public static void main(String[] args) {
        func(3);

    }

    private static void func(int n) {
        if (n <= 0) {
            return;
        }
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        while (addOne(arr) == 0) {
            printArr(arr);
        }
    }

    private static int addOne(int[] arr) {


        int index = arr.length - 1;
        int in = 1;
        while (index >= 0 && in > 0) {
            arr[index] += in;
            in = arr[index] / 10;
            arr[index] %= 10;
            index--;
        }

        if (in == 1) {
            return 1;
        }

        return 0;
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.print("\n");
    }
}
