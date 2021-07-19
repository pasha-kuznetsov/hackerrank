package warmup.countingValleys;

public class Result {
    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    public static int countingValleys(int steps, String path) {
        // Write your code here
        int level = 0;
        int valleys = 0;
        for (char c : path.toCharArray()) {
            switch (c) {
                case 'U':
                    if (++level == 0)
                        ++valleys;
                    break;

                case 'D':
                    --level;
            }
        }
        return valleys;
    }
}
