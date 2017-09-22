package com.tydbits.hackerrank.dp.maxsubarray;

import java.util.Scanner;

public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        for (int t = scanner.nextInt(); t > 0; --t) {
            int n = scanner.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; ++i)
                array[i] = scanner.nextInt();
            MaxSubArray maxSubArray = new MaxSubArray(array);
            System.out.print(maxSubArray.contiguous);
            System.out.print(' ');
            System.out.print(maxSubArray.nonContiguous);
            System.out.println();
        }
    }
}

class MaxSubArray {
    int contiguous;
    int nonContiguous;

    MaxSubArray(int[] array) {
        int nextPosMax = contiguous = nonContiguous = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; --i) {
            int current = array[i];
            int currentMax = Math.max(current, current + nextPosMax);
            if (currentMax > contiguous)
                contiguous = currentMax;
            if (nonContiguous >= 0 && current > 0)
                nonContiguous += current;
            else if (current > nonContiguous)
                nonContiguous = current;
            nextPosMax = currentMax;
        }
    }
}
