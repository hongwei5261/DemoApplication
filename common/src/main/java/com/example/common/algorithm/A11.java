package com.example.common.algorithm;

public class A11 {
    public static void main(String[] args) {
        func(new int[]{1, 2, 2, 3, 9, 3, 2, 9, 1, 8});

    }

    private static void func(int[] aar) {
        if (aar == null || aar.length <= 1) {
            return;
        }

        int N = aar.length;
        int sIndex = 0;
        int eIndex = N - 1;
        int temp;
        while (sIndex < eIndex) {

            while (aar[sIndex] % 2 != 0 && sIndex < eIndex) {
                sIndex++;
            }

            while (aar[eIndex] % 2 != 1 && sIndex < eIndex) {
                eIndex--;
            }

            temp = aar[sIndex];
            aar[sIndex] = aar[eIndex];
            aar[eIndex] = temp;
            sIndex++;
            eIndex--;
        }

        for (int i : aar) {
            System.out.print(i + ", ");
        }
    }
}
