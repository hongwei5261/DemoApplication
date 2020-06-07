package com.example.common.leek;

public class L1 {
    public static void main(String[] arge) {
        int[] arr = new int[]{1, 2, 0, 1, 0, 3, 2, 3, 0, 2, 0};
        System.out.println(func(arr));
    }

    private static int func(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }

        int count = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 1; i <= max; i++) {
            for (int j=0;j<arr.length;j++) {
                if (arr[j] - i > 0){

                }
            }
        }

        return count;
    }
}
