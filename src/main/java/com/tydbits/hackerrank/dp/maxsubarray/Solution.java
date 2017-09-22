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
            System.out.print(maxSubArray.contiguousMax);
            System.out.print(' ');
            System.out.print(maxSubArray.nonContiguousMax);
            System.out.println();
        }
    }
}

class MaxSubArray {
    int contiguousMax;
    int nonContiguousMax;

    MaxSubArray(int[] array) {
        int nextPosMax = contiguousMax = nonContiguousMax = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; --i) {
            int current = array[i];
            int currentMax = Math.max(current, current + nextPosMax);
            if (currentMax > contiguousMax)
                contiguousMax = currentMax;
            if (nonContiguousMax >= 0 && current > 0)
                nonContiguousMax += current;
            else if (current > nonContiguousMax)
                nonContiguousMax = current;
            nextPosMax = currentMax;
        }
    }
}
