package com.dp.practice;

public class MaximumSumSubmatrix {

    int[] arr;
    int[][] matrix;
    int maxSum, maxLeft, maxRight, maxUp, maxDown;
    int currentSum, up, down;

    // kadane algo to find max sum sub array

    private void maxSumSubArray() {
        up = 0;
        down = 0;
        currentSum = Integer.MIN_VALUE;
        int tempSum = arr[0];
        int tempUp = 0, tempDown = 0;
        for (int i = 1; i < arr.length; i++) {
            if ((tempSum + arr[i]) > arr[i]) {
                tempSum = tempSum + arr[i];
                tempDown = i;
            } else {
                tempSum = arr[i];
                tempUp = i;
            }
            if (tempSum > currentSum) {
                currentSum = tempSum;
                up = tempUp;
                down = tempDown;
            }
        }
    }

    private void maxSumSubMatrix() {
        maxSum = Integer.MIN_VALUE;
        maxLeft = maxRight = maxUp = maxDown = 0;
        for (int left = 0; left < matrix[0].length; left++) {

            for (int row = 0; row < matrix.length; row++) {
                arr[row] = 0;
            }
            for (int right = left; right < matrix[0].length; right++) {

                for (int row = 0; row < matrix.length; row++) {
                    arr[row] += matrix[row][right]; // add with previous column elements
                }
                maxSumSubArray();
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxUp = up;
                    maxDown = down;
                    maxLeft = left;
                    maxRight = right;
                }
            }
        }
    }

    public static void main(String[] args) {
        MaximumSumSubmatrix obj = new MaximumSumSubmatrix();
        obj.matrix = new int[][]{
                {2, 1, -3, -4, 5},
                {0, 6, 3, 4, 1},
                {2, -2, -1, 4, -5},
                {-3, 3, 1, 0, 3}
        };
        obj.arr = new int[obj.matrix.length];
        obj.maxSumSubMatrix();
        System.out.println("Max sum : " + obj.maxSum);
        System.out.println("Left : " + obj.maxLeft + " Right : "
                + obj.maxRight + " Up : " + obj.maxUp + " Down : " + obj.maxDown);
    }
}
