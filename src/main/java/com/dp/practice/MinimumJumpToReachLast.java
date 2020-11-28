package com.dp.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * arr : 1,2,3,4,2,1,0,5,3,6,7,1,2,9,5,2,1
 * 
 * jumpArr :
 * 
 * prevNode :
 *
 */
public class MinimumJumpToReachLast {

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 2, 1, 0, 5, 3, 6, 7, 1, 2, 9, 5, 2, 1 };
		int[] jumpArr = new int[arr.length];
		int[] prevNode = new int[arr.length];

		for (int i = 0; i < jumpArr.length; i++) {
			jumpArr[i] = Integer.MAX_VALUE;
		}
		jumpArr[0] = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] >= i - j && jumpArr[i] > jumpArr[j] + 1) { // jump possible and results optimal
					jumpArr[i] = jumpArr[j] + 1;
					prevNode[i] = j;
				}
			}
		}

		System.out.println("minimum jump : " + jumpArr[arr.length - 1]);
		List<Integer> nodeIdx = new ArrayList<>();

		int idx = arr.length - 1;
		while (idx > 0) {
			nodeIdx.add(idx);
			idx = prevNode[idx];
		}
		System.out.println(nodeIdx);
	}

}
