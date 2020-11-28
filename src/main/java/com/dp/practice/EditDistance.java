package com.dp.practice;

/**
 * Convert String A to String B
 * <p>
 * 3 operations permitted
 * 1. Deletion C(d)
 * 2. Insertion C(i)
 * 3. Replacement C(r)
 * <p>
 * Recursion : DP[i,j] to convert first i characters of A into first j characters of B
 * <p>
 * DP[i,j] = Min of
 * 1. DP[i-1, j] + C(d) --- 1 char is deleted from A (remaining i-1 char of A has to be converted to j char of B)
 * 2. DP[i, j-1] + C(i) --- 1 char is inserted into A (remaining i char of A has to be converted to j-1 char of B)
 * 3. DP[i-1,j-1]  --- if A[i] == B[j]
 * 4. DP[i-1][j-1] + C(r)  --- if A[i] != B[j]
 */
public class EditDistance {

    private int cD = 1;
    private int cI = 1;
    private int cR = 1;

    private int callCount = 0;

    private int getEditDistanceRecursive(String a, String b) { // complexity 3^n
        callCount++;
        System.out.println("Call getEditDistanceRecursive " + callCount);
        if (a.length() == 0) {
            return b.length() * cI;
        }
        if (b.length() == 0) {
            return a.length() * cD;
        }
        return Math.min(Math.min(getEditDistanceRecursive(a.substring(1), b) + cD,
                getEditDistanceRecursive(a, b.substring(1)) + cI),
                getEditDistanceRecursive(a.substring(1), b.substring(1)) + (a.charAt(0) == b.charAt(0) ? 0 : cR));
    }

    private int getEditDistanceDP(String a, String b) { // complexity m*n
        int[][] dpArr = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            dpArr[i][0] = i;
        }
        for (int i = 0; i <= b.length(); i++) {
            dpArr[0][i] = i;
        }
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                dpArr[i][j] = Math.min(Math.min(dpArr[i - 1][j] + cD, dpArr[i][j - 1] + cI),
                        dpArr[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : cR));
            }
        }
        return dpArr[a.length()][b.length()];
    }

    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        System.out.println(obj.getEditDistanceRecursive("ang", "dnh"));
        System.out.println(obj.getEditDistanceDP("ang", "dnh"));
    }
}
