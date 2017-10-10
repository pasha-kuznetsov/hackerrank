package search.nth_frequent;

import java.util.*;

// Given a list of numbers with repeated elements,
// find the frequency of the element, which occurs Nth most frequent.
// https://www.hackerrank.com/contests/codenigmapast/challenges/nthlargest
public class Solution {
    public static void main(String[] args) {
        System.out.print(nth_frequent(new Scanner(System.in)));
    }

    static int nth_frequent(Scanner scanner) {
        int m = scanner.nextInt(), rank = scanner.nextInt(), max = scanner.nextInt();

        int maxCount = 0;
        int[] itemCounts = new int[max + 1];
        for (int i = 0; i < m; i++) {
            int item = scanner.nextInt();
            maxCount = Math.max(maxCount, ++itemCounts[item]);
        }

        Integer[] itemByCount = new Integer[maxCount + 1];
        for (int item = 0; item <= max; ++item) {
            Integer count = itemCounts[item];
            if (itemByCount[count] == null)
                itemByCount[count] = item;
        }

        return rank > itemByCount.length ? -1 : itemByCount[itemByCount.length - rank];
    }
}
