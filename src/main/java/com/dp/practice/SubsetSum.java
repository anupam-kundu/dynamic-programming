package com.dp.practice;

import java.util.Arrays;

/*
 * check whether there exists a subset of the given set of numbers who's sum is K
 *
 * 0/1 Knapsack problem
 */
public class SubsetSum {

    int K = 17;
    int[] arr = new int[]{3, 2, 4, 19, 3, 7, 13, 10, 6, 11};

    private boolean isPresent(int sum, int startIdx) {
        if (sum == K) {
            return true;
        } else if (sum > K) {
            return false;
        } else {
            boolean result = false;
            for (int i = startIdx; i < arr.length; i++) {
                if (result) {
                    break;
                }
                result = result || isPresent(sum + arr[i], i + 1);
            }
            return result;
        }
    }

    /*
     * DP solution : 0/1 Knapsack problem
     *
     * Move forward if key does not exist
     */

    private boolean isPresentDP() {
        boolean[][] dp = new boolean[arr.length][K + 1];
        for (int i = 0; i <= K; i++) {
            dp[0][i] = (i-arr[0] == 0);
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = dp[i-1][j] || (j-arr[i] >= 0 && dp[i-1][j-arr[i]]);
            }
        }
        for (int i=0;i< arr.length;i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[arr.length-1][K];
    }

    public static void main(String[] args) {
        SubsetSum subsetSum = new SubsetSum();
        System.out.println(subsetSum.isPresent(0, 0));
        System.out.println(subsetSum.isPresentDP());
    }

}
