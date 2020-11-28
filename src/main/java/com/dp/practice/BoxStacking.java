package com.dp.practice;

/**
 * Longest Increasing sub sequence
 * Considering
 * H(i) = Max(H(j)) + hj {j<i, wj>wi, dj>di>}
 */
public class BoxStacking {

    private int[] dp;

    private int getMaxHeight(int[] h, int[] w, int[] d) {
        dp = new int[h.length];
        for (int i = 0; i < h.length; i++) {
            dp[i] = h[i];
        }
        for (int i = 1; i < h.length; i++) {
            for (int j = 0; j < i; j++) {
                if (w[j] > w[i] && d[j] > d[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + h[j]);
                }
            }
        }

        int max = 0;
        for (int item : dp) {
            max = Math.max(max, item);
        }
        return max;
    }
}
