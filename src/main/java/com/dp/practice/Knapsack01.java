package com.dp.practice;

/*
 * check youtube : https://www.youtube.com/watch?v=8LusJS5-AGo
 * 
 * 
 * row i (i number of items) 
 * 
 * and 
 * 
 * column j+1 [final weight j] [0,1,2,...j]
 * 
 * formula
 * 
 * if(j < wt[i]){
 * 	T[i][j] = T[i-1][j];
 * } else {
 * 	T[i][j] = MAX (T[i-1][j] , v[i] + T[i-1][j-wt[i]])
 * }
 * 
 * 
 * 
 */
public class Knapsack01 {

	private static int MaxKnapsack01Recursive(int W, int[] val, int[] wt, int n) {
		if (W == 0 || n == 0) {
			return 0;
		}
		if (wt[wt.length - 1] > W) {
			return MaxKnapsack01Recursive(W, val, wt, n - 1);
		} else {
			return Math.max(val[val.length - 1] + MaxKnapsack01Recursive(W - wt[wt.length - 1], val, wt, n - 1),
					MaxKnapsack01Recursive(W, val, wt, n - 1));
		}
	}

	private static int MaxKnapsack01DP(int[] val, int[] wt) {
		int[][] dp = new int[val.length + 1][wt.length + 1];

		for (int i = 0; i <= val.length; i++) {
			for (int w = 0; w <= wt.length; w++) {
				if (i == 0 || w == 0) {
					dp[i][w] = 0;
				} else if (wt[i - 1] < w) {
					dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
				} else {
					dp[i][w] = dp[i - 1][w];
				}
			}
		}

		return dp[val.length][wt.length];
	}

	private static void MaxKnapsack01DPMatrix() {

	}
}
