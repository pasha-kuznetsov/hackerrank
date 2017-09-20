// Lexicographically Minimal Splice
// "Morgan and a String"
// https://www.hackerrank.com/challenges/morgan-and-a-string

package com.tydbits.hackerrank.strings.lexicographically_minimal_splice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int n = Integer.parseInt(in.readLine());
            for (int i = 0; i < n; i++) {
                String s1 = in.readLine();
                String s2 = in.readLine();
                String result = new LexicographicallyMinimal(s1, s2).splice();
                System.out.print(result);
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

