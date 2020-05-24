package com.example.common.algorithm;

public class A02 {
    public static void main(String[] args) {

        char[] chars = new char[]{'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd', ' ', ' ', 'h', 'h', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',};
        func(chars, 15);
    }

    /**
     * 根据给定字符串数组，空格替换为%20
     *
     * @param string    给定的数组
     * @param useLength 已经使用的长度
     * @return 是否替换成功
     */
    private static boolean func(char[] string, int useLength) {
        if (string == null || string.length < useLength) {
            return false;
        }
        int emptyCount = 0;
        for (int i =0;i<useLength;i++) {
            if (string[i] == ' ') {
                emptyCount++;
            }
        }

        int targetLength = emptyCount * 2 + useLength;
        if (string.length < targetLength) {
            return false;
        }

        targetLength = targetLength - 1;
        for (int i = useLength - 1; i >= 0; i--) {
            if (string[i] == ' ') {
                string[targetLength--] = '0';
                string[targetLength--] = '2';
                string[targetLength--] = '%';
            } else {
                string[targetLength--] = string[i];
            }
        }
        for (int i = 0; i < string.length; i++) {
            System.out.print(string[i] + "");
        }

        return true;
    }
}
