package com.dp.practice;

import java.util.Arrays;

public class LongestCommonSubstring {

    /**
     * Current element count/match is not dependent on just one element before
     * So can not be created dp array like LongestCommonSubsequence
     */
    private int max = 0;
    private int max_i = 0, max_j = 0;

    public int longestSubString(String s1, String s2) {

        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        max_i = i;
                        max_j = j;
                    }
                }
            }
        }
        System.out.println(s1.substring(max_i - max + 1, max_i + 1));
        System.out.println(s2.substring(max_j - max + 1, max_j + 1));
        return max;
    }

    public int getMax() {
        return max;
    }

    public int getMax_i() {
        return max_i;
    }

    public int getMax_j() {
        return max_j;
    }

    private Integer[][] dpArrLength;
//    private int count = 0;
    private int maxDpCh = 0;
    private int max_idx1 = 0;

    public int lcsLength(String s1, int idx1, String s2, int idx2) {
//        System.out.println(" Printing iteration : "+ (++count));
//        Arrays.stream(dpArrLength).sequential().forEachOrdered(e -> System.out.println(Arrays.toString(e)));
        if(idx1 >= s1.length() || idx2 >= s2.length()) {
            return 0;
        }
        if(dpArrLength[idx1][idx2] == null) {
            if (s1.charAt(idx1) == s2.charAt(idx2)) {
                dpArrLength[idx1][idx2] = 1 + lcsLength(s1, idx1 + 1, s2, idx2 + 1);
            } else {
                dpArrLength[idx1][idx2] = 0;
                lcsLength(s1, idx1 + 1, s2, idx2);
                lcsLength(s1, idx1, s2, idx2 + 1);
            }

            if (dpArrLength[idx1][idx2] > maxDpCh) {
                maxDpCh = dpArrLength[idx1][idx2];
                max_idx1 = idx1;
            }
        }
        return dpArrLength[idx1][idx2];
    }

    public static void main(String[] args) {
        LongestCommonSubstring obj = new LongestCommonSubstring();
        // System.out.println(obj.longestSubString("xxxxxabmcksmceabcder",
        // "dreabcdee"));

        String s1 = "ttmlabcdcbazktt";
        String s2 = "mabcdcbazxkmlabcdsdmlabcdcbazkfet";
        System.out.println(obj.longestSubString(s1, s2));



        obj.dpArrLength = new Integer[s1.length()][s2.length()];
        obj.lcsLength(s1, 0, s2, 0);

//        System.out.println(obj.max_idx1);
//        System.out.println(obj.maxDpCh);
        Arrays.stream(obj.dpArrLength).sequential().forEachOrdered(e -> System.out.println(Arrays.toString(e)));

        System.out.println(s1.substring(obj.max_idx1, obj.max_idx1 + obj.maxDpCh));
    }

}
