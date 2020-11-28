package com.dp.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * check matrix approach
 * i -> items weight in sorted order
 * j -> total weight
 * T[i][j] = MAX(T[i-1][j] , V[i] + T[i][j-i]) if(j-i > 0) else T[i-1][j]
 *
 */
public class KnapsackRepetion {

    // Time complexity O(n log n)
    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        int maxValue = getMaxValue(wt, val, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);

        System.out.println("Maximum value we can obtain = " + getMaxValueDP(wt, val, capacity));
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


}
