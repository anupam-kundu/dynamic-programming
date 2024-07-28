package com.dp.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * check matrix approach
 * i -> items weight [include weight till index i]
 * j -> total weight (capacity)
 * T[i][j] = MAX(T[i-1][j] , V[i] + T[i][j-wt[i]]) if(j-wt[i] >= 0) else T[i-1][j]
 *
 */
public class KnapsackRepetition {

    // Time complexity O(n log n)
    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30};
        int[] val = {6, 40, 100, 120};
        int capacity = 50;

        int maxValue = getMaxValue(wt, val, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);

        System.out.println("Maximum value we can obtain = " + getMaxValueDP(wt, val, capacity));

        getMaxValueDpMatrix(wt, val, capacity);
    }

    // // function to get maximum value
    private static int getMaxValue(int[] wt, int[] val, int capacity) {
        if (capacity == 0) {
            return 0;
        }
        System.out.println("Iteration getMaxValue");
        int totalVal = 0;
        for (int i = 0; i < wt.length; i++) {
            if (capacity - wt[i] >= 0)
                totalVal = Math.max(totalVal, getMaxValue(wt, val, capacity - wt[i]) + val[i]);
        }

        return totalVal;

    }

    private static Map<Integer, Integer> dpCapacity = new HashMap<>();

    private static int getMaxValueDP(int[] wt, int[] val, int capacity) {
        if (dpCapacity.containsKey(capacity)) {
            return dpCapacity.get(capacity);
        }
        if (capacity == 0) {
            return 0;
        }
        System.out.println("Iteration getMaxValueDP");
        int totalVal = 0;
        for (int i = 0; i < wt.length; i++) {
            if (capacity - wt[i] >= 0)
                totalVal = Math.max(totalVal, getMaxValueDP(wt, val, capacity - wt[i]) + val[i]);
        }

        dpCapacity.put(capacity, totalVal);
        return totalVal;

    }

    // space and time complexity : O(w*n)
    private static void getMaxValueDpMatrix(int[] wt, int[] val, int capacity) {

        int [][] dp = new int[wt.length][capacity+1];

        for(int i=0; i < wt.length; i++) {
            for(int j=0; j <= capacity; j++) {
                if(j-wt[i] >= 0 ) {  // current element can be included
                    dp[i][j] = Math.max(i>0 ? dp[i-1][j] : 0, val[i] + dp[i][j-wt[i]]);
                } else {  // current element can not be included
                    dp[i][j] = i>0 ? dp[i-1][j] : 0;
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


}
