package com.example.common.algorithm;

public class A07 {
    public static void main(String[] args) {
        System.out.println("sum:" + func(10));
        System.out.println("sum:" + func2(10));
    }

    private static int func(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int sum = func(n - 1) + func(n - 2);
        System.out.println(n + " sum:" + sum);
        return sum;
    }

    private static int func2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int sum = 0;
        int pre1 = 1;
        int pre2 = 1;
        int cur = 2;

        for (int i = 3; i <= n; i++) {
            cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
            System.out.println(i + "  cur:" + cur);
        }

        return cur;
    }
}
