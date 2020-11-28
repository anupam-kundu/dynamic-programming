package com.dp.practice;

public class MaximumContiguousSubsequence {

    // brute force
    public int getMaxSumBF(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            int currentSum = 0;
            for (int j = i; j < a.length; j++) {
                currentSum += a[j];
                if (currentSum > maxSum)
                    maxSum = currentSum;
            }
        }
        return maxSum;
    }

    // divide conquer
    public int getMaxSumDC(int[] a) {
        return getMaxSum(a, 0, a.length - 1);
    }

    private int getMaxSum(int[] a, int low, int high) {
        if (low == high) {
            return a[low];
        }
        int mid = low + (high - low) / 2;
        int maxLeftSum = getMaxSum(a, low, mid);
        int maxRightSum = getMaxSum(a, mid + 1, high);
        int leftMidSum = Integer.MIN_VALUE, rightMidSum = Integer.MIN_VALUE;
        for (int i = mid, sum = 0; i >= low; i--) {
            sum += a[i];
            leftMidSum = sum > leftMidSum ? sum : leftMidSum;
        }
        for (int i = mid + 1, sum = 0; i <= high; i++) {
            sum += a[i];
            rightMidSum = sum > rightMidSum ? sum : rightMidSum;
        }
        return Math.max(Math.max(maxRightSum, maxLeftSum), leftMidSum + rightMidSum);
    }

    // dynamic programming
    // kadane's Algo
    public int maxSumSubArray(int[] a) {
        int sum = a[0];
        for (int i = 1; i < a.length; i++) {
            sum = Math.max(sum + a[i], a[i]);
        }

        return sum;
    }

    public int maxSumSubArrayRecursive(int[] a, int idx) {
        if (idx == a.length) {
            return 0;
        }
        return Math.max(a[idx], a[idx] + maxSumSubArrayRecursive(a, idx + 1));
    }


    /**
     * Maximum sum sub array with no two continuous element
     */

    private int maxSumNo2ContinuousRec(int[] a, int idx) {
        if (idx >= a.length) {
            return 0;
        }
        return Math.max(a[idx] + maxSumNo2ContinuousRec(a, idx + 2),
                maxSumNo2ContinuousRec(a, idx + 1));
    }
}
