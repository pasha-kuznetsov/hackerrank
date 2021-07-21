package warmup.repeatedString;

public class Result {

    /*
     * Complete the 'repeatedString' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. LONG_INTEGER n
     */

    public static long repeatedString(String s, long n) {
        // Write your code here

        // Number of 'a' in whole `s` occurrences repeated up till `n` chars
        long result = s.length() <= n
                ? countOfA(s, s.length()) * (n / s.length())
                : 0;

        // Number of 'a' in the last characters of `n` filling partial `s`
        result += countOfA(s, n % s.length());

        return result;
    }

    private static long countOfA(String s, long n) {
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++count;
            }
        }
        return count;
    }
}
