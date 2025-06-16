package com.dp.practice;

/**
 * a child can take 1 step or 2 step in stair case
 * find number of ways he can reach the top
 * <p>
 * same like fibonacci problem
 * <p>
 * f(n) = f(n-1)+f(n-2)
 * <p>
 * Alt: child can take 1 , 2 or 3 steps
 * <p>
 * f(n) = f(n-1) + f(n-2) + f(n-3)
 * <p>
 * f(n) = f(n-1) + f(n-2)
 * f(2) = f(1) + f(0)
 * f(2) = 1 + 1 = 2
 * <p>
 * f(3) = f(2) + f(1)
 * f(3) = 2 + 1 = 3
 * <p>
 * f(4) = f(3) + f(2) = 3+2 = 5
 */
public class StaircaseProblem {
    public int climbStairs(int n) {
        int[] dpArr = new int[n + 1];
        if (n == 0 || n == 1) {
            return 1;
        }
        dpArr[0] = dpArr[1] = 1;
        for (int i = 2; i <= n; i++) {
            dpArr[i] = dpArr[i - 1] + dpArr[i - 2];
        }
        return dpArr[n];
    }


    /**
     * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
     * <p>
     * You can either start from the step with index 0, or the step with index 1.
     * <p>
     * Return the minimum cost to reach the top of the floor.
     * <p>
     * dp[i] = cost[i] + Min(dp[i-1], dp[i-2]) // current step cost + min cost came from previous step or previous to previous step
     */

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1]; // +1 to reach last with cost 0
        if (cost.length == 1) {
            return 0;
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = (i == cost.length ? 0 : cost[i]) + Math.min(dp[i - 1], dp[i - 2]);
        }
        return dp[cost.length];
    }
}
