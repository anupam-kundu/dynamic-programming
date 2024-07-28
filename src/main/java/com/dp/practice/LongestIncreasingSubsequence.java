package com.dp.practice;

import java.util.Arrays;

/**
 * LIS[i] = longest increasing subsequence till ith element from 0
 */
public class LongestIncreasingSubsequence {

    public int longestIncreasingSubsequence(int[] arr) {
        int[] lis = new int[arr.length];
        int max = 0;
//        for (int i = 0; i < arr.length; i++) {
//            lis[i] = 1;
//        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(lis));
        for (int m : lis) {
            max = Math.max(max, m);
        }

        return max;
    }

    public static void main(String[] args) {
        int arr[] = {10, 22, 9, 33, 21, 50, 41, 60, 59};
        LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
        System.out.println("Length of lis is " + obj.longestIncreasingSubsequence(arr));
    }
}
