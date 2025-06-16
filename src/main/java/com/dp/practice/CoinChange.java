package com.dp.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anupam
 * <p>
 * minimum number of coins to make M
 * <p>
 * check matrix approach
 * i -> coins available in soted order
 * j -> total value
 * T[i][j] = MIN(T[i-1][j], 1 + T[i][j-v(i)]) if (j-v(i)) >= 0 else T[i-1][j]
 *
 *
 * Coin Change total ways
 */
public class CoinChange {

    private static final int[] COINS = new int[]{1, 5, 10, 20, 25, 50};
    private static int minCoinsCount = 0;

    private static int minCoins(int M) {
        if (M == 0) {
            return 0;
        }
        minCoinsCount++;
        System.out.println("Iteration minCoins " + minCoinsCount);
        int min = Integer.MAX_VALUE;
        for (int coin : COINS) {
            if (coin <= M) {
                min = Math.min(min, minCoins(M - coin) + 1);
            }
        }
        return min;
    }

    private static int minCoinsDpCount = 0;
    private static Map<Integer, Integer> dpMap = new HashMap<>();

    private static int minCoinsDp(int M) {
        if (dpMap.containsKey(M)) {
            return dpMap.get(M);
        }
        minCoinsDpCount++;
        System.out.println("Iteration minCoinsDp " + minCoinsDpCount);
        if (M == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int coin : COINS) {
            if (coin <= M) {
                min = Math.min(min, minCoinsDp(M - coin) + 1);
            }
        }
        dpMap.put(M, min);
        return min;
    }

    private static void minCoinsDpMatrix(int totalChange) {
        int[][] dp = new int[COINS.length][totalChange + 1];

        for (int i = 0; i < COINS.length; i++) {
            for (int j = 1; j <= totalChange; j++) {
                if (j - COINS[i] >= 0) { // current ith idx coin can be added
                    int excludedCurrent = i > 0 ? dp[i - 1][j] : 0;
                    int includedCurrent = 1 + dp[i][j - COINS[i]];
                    dp[i][j] = excludedCurrent == 0 ? includedCurrent : Math.min(excludedCurrent, includedCurrent);
                } else { // current coin can not be added
                    dp[i][j] = i > 0 ? dp[i - 1][j] : 0;
                }
            }
        }

        // Print all elements of the 2D array
        for (int i = 0; i < dp.length; i++) { // Iterate over rows
            for (int j = 0; j < dp[i].length; j++) { // Iterate over columns
                System.out.print(dp[i][j] + " "); // Print element with a space
            }
            System.out.println(); // Move to the next line after each row
        }
    }


    public static void main(String[] args) {
        System.out.println(minCoins(40));
        System.out.println(minCoinsDp(40));
        minCoinsDpMatrix(80);
    }

    /**
     * Coin Change - Count Ways to Make Sum
     *
     *
     *
     * Given an integer array of coins[] of size n representing different types of denominations and an integer sum,
     * the task is to count all combinations of coins to make a given value sum.
     * Note: Assume that you have an infinite supply of each type of coin.
     *
     * KnapSack repetition with modification
     *
     * number of ways till 0 to ith coins for sum j
     * dpArr[i][j] = dpArr[i-1][j] + dpArr[i][j-coins[i]]  if (j-coins[i] > 0) else dpArr[i-1][j]

     */
    private static int coinChangeTotalWaysDpMatrix(int[] coins, int sum) {
        int n = coins.length;

        // 2d dp array where n is the number of coin
        // denominations and sum is the target sum
        int[][] dp = new int[n + 1][sum + 1];

        // Represents the base case where the target sum is 0,
        // and there is only one way to make change: by not
        // selecting any coin
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {

                // Add the number of ways to make change without
                // using the current coin
                dp[i][j] = dp[i - 1][j];

                if ((j - coins[i - 1]) >= 0) {

                    // Add the number of ways to make change
                    // using the current coin
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
}
