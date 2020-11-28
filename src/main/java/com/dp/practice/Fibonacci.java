package com.dp.practice;

import java.util.Arrays;

public class Fibonacci {

    private int[] dpArr;
    private final int n;

    public Fibonacci(int n) {
        this.n = n;
        dpArr = new int[n];
    }


    public int getFibNth(int i) {
        if (dpArr[i - 1] == 0) {
            if (i == 1 || i == 2) {
                dpArr[i - 1] = 1;
            } else {
                dpArr[i - 1] = getFibNth(i - 1) + getFibNth(i - 2);
            }
        }
        return dpArr[i - 1];
    }

    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci(10);
        System.out.println(fibonacci.getFibNth(5));
        System.out.println(Arrays.toString(fibonacci.dpArr));
    }
}
