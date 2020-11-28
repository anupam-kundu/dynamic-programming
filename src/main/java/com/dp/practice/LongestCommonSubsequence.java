package com.dp.practice;

import java.util.Arrays;

public class LongestCommonSubsequence {


    private String LCS(String s1, String s2) {
        String result = "";
        if (s1.length() == 0 || s2.length() == 0) {
            return result;
        }
        if (s1.charAt(0) == s2.charAt(0)) {
            result = s1.charAt(0) + LCS(s1.substring(1), s2.substring(1));
        } else {
            String str1 = LCS(s1.substring(1), s2);
            String str2 = LCS(s1, s2.substring(1));
            result = str1.length() > str2.length() ? str1 : str2;
        }

        return result;
    }

    private String[][] dpArr;

    private String LCS_DP(String s1, int idx1, String s2, int idx2) {
        if (dpArr[idx1][idx2] == null) {
            if (s1.length() == idx1 || s2.length() == idx2) {
                dpArr[idx1][idx2] = "";
            } else if (s1.charAt(idx1) == s2.charAt(idx2)) {
                dpArr[idx1][idx2] = s1.charAt(idx1) + LCS_DP(s1, idx1 + 1, s2, idx2 + 1);
            } else {
                String str1 = LCS_DP(s1, idx1 + 1, s2, idx2);
                String str2 = LCS_DP(s1, idx1, s2, idx2 + 1);
                dpArr[idx1][idx2] = str1.length() > str2.length() ? str1 : str2;
            }
        }
        return dpArr[idx1][idx2];
    }

    private Integer[][] dpArrLength;

    public int lcsLength(String s1, int idx1, String s2, int idx2) {
        if (dpArrLength[idx1][idx2] == null) {
            if (s1.length() == idx1 || s2.length() == idx2) {
                dpArrLength[idx1][idx2] = 0;
            } else if (s1.charAt(idx1) == s2.charAt(idx2)) {
                dpArrLength[idx1][idx2] = 1 + lcsLength(s1, idx1 + 1, s2, idx2 + 1);
            } else {
                dpArrLength[idx1][idx2] = Math.max(lcsLength(s1, idx1 + 1, s2, idx2), lcsLength(s1, idx1, s2, idx2 + 1));
            }
        }
        return dpArrLength[idx1][idx2];
    }

    private int[][] dpArrLen;

    public int lcsLengthDP(String s1, String s2) {
        dpArrLen = new int[s1.length()+1][s2.length()+1];
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dpArrLen[i][j] = 1 + dpArrLen[i-1][j-1];
                }else{
                    dpArrLen[i][j] = Math.max(dpArrLen[i-1][j],dpArrLen[i][j-1]);
                }
            }
        }
        return dpArrLen[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        lcs.dpArrLength = new Integer[s1.length() + 1][s2.length() + 1];
        System.out.println(lcs.lcsLength(s1, 0, s2, 0));
        System.out.println(lcs.LCS(s1, s2));
        lcs.dpArr = new String[s1.length() + 1][s2.length() + 1];
        System.out.println(lcs.LCS_DP(s1, 0, s2, 0));

        System.out.println(lcs.lcsLengthDP(s1,s2));
        Arrays.stream(lcs.dpArr).sequential().forEachOrdered(e -> System.out.println(Arrays.toString(e)));
        Arrays.stream(lcs.dpArrLength).sequential().forEachOrdered(e -> System.out.println(Arrays.toString(e)));
        Arrays.stream(lcs.dpArrLen).sequential().forEachOrdered(e -> System.out.println(Arrays.toString(e)));

    }
    
}
