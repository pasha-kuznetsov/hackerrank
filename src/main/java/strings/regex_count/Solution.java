// Count Strings
// https://www.hackerrank.com/challenges/count-strings

package strings.regex_count;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int n = sc.nextInt(); n > 0; --n)
            System.out.println(count(sc.next(), sc.nextInt()));
    }

    static long count(String regex, int len) {
        return len == 0 ? 0 : new RegexCounter(new Regex(regex)).count(len);
    }
}

