package com.dp.practice;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * find maximum subarray with equal letter & number
 * A represents letter
 * B represents number
 * Arr : A B A A A B
 *
 * complexity O(n)
 *
 * ** try matrix approach DP  +1 & -1 copy
 *
 *
 * Alt: Find max sub array having number of letters is greater than number of digits
 *
 */
public class MaximumSubArrayOfEqualLetterNumber {

    private char[] deAction(char[] arr) {
        int[] diffArray = findDifference(arr);
        Map.Entry<Integer, Integer> longestDistanceEqualDiff = getLongestDistanceEqualDiff(diffArray);
        return Arrays.copyOfRange(arr, longestDistanceEqualDiff.getKey() + 1, longestDistanceEqualDiff.getValue());
    }

    private Map.Entry<Integer, Integer> getLongestDistanceEqualDiff(int[] diffArray) {
        Map<Integer, Integer> diffIndexMap = new HashMap<>();
        int minIdx = 0, maxIdx = 0, maxIndexDiff = 0;
        for (int i = 0; i < diffArray.length; i++) {
            if (diffIndexMap.get(diffArray[i]) != null) {
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

}
