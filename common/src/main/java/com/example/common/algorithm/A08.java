package com.example.common.algorithm;

public class A08 {
    public static void main(String[] args) {
        System.out.println(func(64));

    }

    private static int func(int num) {
        int count = 0;
        if (num <= 0) {
            return 0;
        } else if (num == 1) {
            return 1;
        }

        while (num > 0) {
            if (num % 2 == 1) {
                count++;
            }
            num = num / 2;
        }
        return count;
    }
}
