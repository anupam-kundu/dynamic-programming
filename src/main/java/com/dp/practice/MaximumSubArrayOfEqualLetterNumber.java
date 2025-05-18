package com.dp.practice;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * find maximum subarray with equal letter & number
 * find maximum subarray with equal 0 & 1
 * A represents letter
 * B represents number
 * Arr : A B A A A B A
 * <p>
 * complexity O(n)
 * <p>
 * ** try matrix approach DP  +1 & -1 copy
 * <p>
 * <p>
 * Alt: Find max sub array having number of letters is greater than number of digits
 */
public class MaximumSubArrayOfEqualLetterNumber {

    private char[] doAction(char[] arr) {
        int[] diffArray = findDifference(arr);
        Map.Entry<Integer, Integer> longestDistanceEqualDiff = getLongestDistanceEqualDiff(diffArray);
        return Arrays.copyOfRange(arr, longestDistanceEqualDiff.getKey() + 1, longestDistanceEqualDiff.getValue());
    }

    private Map.Entry<Integer, Integer> getLongestDistanceEqualDiff(int[] diffArray) {
        Map<Integer, Integer> diffIndexMap = new HashMap<>();
        int minIdx = 0, maxIdx = 0, maxIndexDiff = 0;
        for (int i = 0; i < diffArray.length; i++) {
            if (diffIndexMap.get(diffArray[i]) == null) {
                diffIndexMap.put(diffArray[i], i);
            } else {
                int prevIndex = diffIndexMap.get(diffArray[i]);
                int diff = i - prevIndex;
                if (diff > maxIndexDiff) {
                    maxIndexDiff = diff;
                    minIdx = prevIndex;
                    maxIdx = i;
                }
            }
        }

        return new AbstractMap.SimpleEntry<>(minIdx, maxIdx);
    }

    private int[] findDifference(char[] arr) {
        int[] diff = new int[arr.length];
        int isChar = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'A') {
                diff[i] = ++isChar;
            } else {
                diff[i] = --isChar;
            }
        }
        return diff;
    }

    private int maxSubArrayWithEqual01(int[] arr) {  // complexity O(n^2)
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) { // starting from i to n [sub array]
                sum += arr[j] == 0 ? -1 : 1; // 0 represents -1 nad 1 represents +1
                if (sum == 0) {
                    result = Math.max(result, (j - i + 1));
                }
            }
        }

        return result;
    }

    /**
     * If we consider every 0 as -1, then this problem become same as the longest subarray with 0 sum problem.
     * <p>
     * We traverse the array and compute the prefix sum
     * <p>
     * If current prefix sum == 0, it means that subarray from index (0) till present index has equal number of 0's and 1's.
     * If we encounter a prefix sum value which we have already encountered before,
     * which means that subarray from the previous index+1 till the present index has equal number of 0's and 1's
     * as they give a cumulative sum of 0.
     */

    private int maxSubArrayWithEqual01DP(int[] arr) {
        int result = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumIndexMap = new HashMap<>();
        // Traverse through the given array
        for (int i = 0; i < arr.length; i++) {
            // Add current element to sum
            // if current element is zero, add -1
            prefixSum += arr[i] == 0 ? -1 : 1;

            // To handle sum = 0 at last index
            if (prefixSum == 0) {
                result = i + 1;
            }

            // If this sum is seen before, then update
            // result with maximum
            if (prefixSumIndexMap.containsKey(prefixSum)) {
                result = Math.max(result, i - prefixSumIndexMap.get(prefixSum));
            } else {
                prefixSumIndexMap.put(prefixSum, i); // Else put this sum in hash table
            }
        }

        return result;
    }

}
