package com.example.common.algorithm;

public class A01 {
    public static void main(String[] args) {
        int[][] nums = new int[10][10];
        int x = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                nums[i][j] = x++;
            }
        }
        System.out.println(func(nums, 55));

    }

    /**
     * 二维数组中查找
     *
     * @param nums 查找的二维数组，从左到右增大，从上到下增大
     * @param num  待查找的数字
     */
    private static boolean func(int[][] nums, int num) {
        int rows = nums.length;
        int cols = nums[0].length;

        int row = 0;
        int col = cols - 1;
        while (row >= 0 && col >= 0 && row < rows) {

            if (num == nums[row][col]) {
                System.out.println("row:" + row + ", col:" + col);
                return true;
            } else if (num > nums[row][col]) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}
