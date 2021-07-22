package warmup.sock_merchant;

import java.util.*;
import java.util.stream.IntStream;

class Result {

    /*
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */

    public static int sockMerchant(int n, int[] ar) {
        // Write your code here

        int pairs = 0;
        Set<Integer> toMatch = new HashSet<>();
        for (int color : ar) {
            if (toMatch.remove(color)) {
                ++pairs;
            } else {
                toMatch.add(color);
            }
        }
        return pairs;
    }
}

public class Solution {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] ar = IntStream.range(0, n).map(i -> scan.nextInt()).toArray();
        int result = Result.sockMerchant(n, ar);
        System.out.println(result);
    }
}
