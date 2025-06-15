package com.dp.practice;

/**
 * unbounded Knapsack problem
 * Given a rod of length n inches and an array of prices that includes prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * For example, if the length of the rod is 8 and the values of different pieces are given as the following,
 * then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 * <p>
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 * <p>
 * x -> piece element array
 * length -> weight of the knapsack
 * total weight -> length of the rod = 8
 * y -> weight array
 * each element of  array gives max value
 * <p>
 * M [i,j] = M [i-1, j] if {j - l[i] < 0} ith element can not be included
 * M [i,j] = MAX (M [i-1, j] , p[i] + M[i, j-l[i]]) if {j - l[i] > 0} ith element can be included
 */
public class CuttingRodProblem {

    private static void maxCutLength(int[] cutLength, int[] price, int totalLength) {
        int[][] dpArr = new int[cutLength.length][totalLength + 1];
        for (int i = 0; i < dpArr.length; i++) {
            dpArr[i][0] = 0;
        }
        for (int i = 0; i < dpArr[0].length; i++) {
            dpArr[0][i] = 0;
        }
        for (int i = 1; i < cutLength.length; i++) {
            for (int j = 1; j <= totalLength; j++) {
                if (cutLength[i] > j) { // can not select current element
                    dpArr[i][j] = dpArr[i - 1][j];
                } else { // current element can be selected
                    dpArr[i][j] = Math.max(
                            dpArr[i - 1][j], // not taking current element
                            price[i] + dpArr[i][j - cutLength[i]] // taking the current element
                    );
                }
            }
        }

        System.out.println("Max value : " + dpArr[cutLength.length - 1][totalLength]);

        for (int i = 1; i < cutLength.length; i++) {
            for (int j = 1; j <= totalLength; j++) {
                System.out.print("  " + dpArr[i][j]);
            }
            System.out.println();
        }
        System.out.println(" get the rod lengths");
        for (int i = cutLength.length - 1, j = totalLength; i > 0 && j > 0; ) {
            if (dpArr[i][j] == dpArr[i - 1][j]) {
                i--;
            } else {
                System.out.println(" Found rod at index " + i + " value " + cutLength[i] + " price " + price[i]);
                j = j - price[i];
            }
        }

    }

    public static void main(String[] args) {
        int[] length = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] price = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20};

        maxCutLength(length, price, 22);
    }
}
