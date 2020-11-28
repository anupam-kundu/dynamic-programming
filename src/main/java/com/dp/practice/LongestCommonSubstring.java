package com.dp.practice;

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

    public static void main(String[] args) {
        LongestCommonSubstring obj = new LongestCommonSubstring();
        // System.out.println(obj.longestSubString("xxxxxabmcksmceabcder",
        // "dreabcdee"));

        String s1 = "ttmlabcdcbazktt";
        String s2 = "mabcdcbazxkmlabcdsdmlabcdcbazkfet";
        System.out.println(obj.longestSubString(s1, s2));
    }

}
