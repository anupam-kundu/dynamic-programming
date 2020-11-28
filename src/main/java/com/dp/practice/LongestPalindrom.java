package com.dp.practice;

public class LongestPalindrom {

	public void longestPalindrom(String s1) {
		StringBuilder sb = new StringBuilder(s1);
		String s2 = sb.reverse().toString();
		LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
		int len = longestCommonSubstring.longestSubString(s1, s2);

		System.out.println("Total length : " + len);
		System.out.println("Longest Palindrom : "
				+ s1.substring(longestCommonSubstring.getMax_i() - longestCommonSubstring.getMax() + 1,
						longestCommonSubstring.getMax_i() + 1));
	}

	public static void main(String[] args) {
		LongestPalindrom obj = new LongestPalindrom();
		obj.longestPalindrom("xxxasababcasdabababacddsdsc");
	}
}
