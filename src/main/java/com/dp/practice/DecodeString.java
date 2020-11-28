package com.dp.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * <p>
 * given a number 12345 find number of possible decode string
 * 12345 -> (1+2+3+4+5) & (12 + 3+4+5) & (1+23+4+5) = 3
 * 12 -> 12 & 1+2 = 2
 * 02 -> 0
 */
public class DecodeString {

    private static int decode(int[] number, Set<String> dictionary) {
        System.out.println(Arrays.toString(number));
        if(number.length==0){
            return 1;
        }
        if(!dictionary.contains(String.valueOf(number[0]))){
            return 0;
        }
        if(number.length == 1 && dictionary.contains(String.valueOf(number[0]))){
            return 1;
        }

        return decode(Arrays.copyOfRange(number,1,number.length), dictionary) +
                (number.length > 1 && dictionary.contains(number[0]+"" + number[1]) ?
                    decode(Arrays.copyOfRange(number, 2,number.length),dictionary ): 0);
    }

    private static int decodeDP(int[] number, Set<String> dictionary) {
        int [] dpArr = new int[number.length+1];
        dpArr[0] = 1; // zero length returns 1;
        for(int i=1;i<dpArr.length;i++){
            if(dictionary.contains(String.valueOf(number[i-1]))){
                dpArr[i] = dpArr[i-1];
            } else {
                dpArr[i] = 0;
            }
            if(i>1 && dictionary.contains(number[i-2]+"" + number[i-1])){
                dpArr[i] += dpArr[i-2];
            }
        }
        System.out.println(Arrays.toString(dpArr));
        return dpArr[dpArr.length-1];
    }

    public static void main(String[] args) {
        Set<String> dic = new HashSet<>();
        IntStream.range(1, 27).forEach(i -> dic.add(i+""));

        System.out.println(decode(new int[]{1,2,0,4,5},dic));
        System.out.println(decode(new int[]{1,2,0,2,0},dic));
        System.out.println(decode(new int[]{1,2,3,4,5},dic));
        System.out.println(decodeDP(new int[]{1,2,0,4,5},dic));
        System.out.println(decodeDP(new int[]{1,2,0,2,0},dic));
        System.out.println(decodeDP(new int[]{1,2,3,4,5},dic));
    }
}
