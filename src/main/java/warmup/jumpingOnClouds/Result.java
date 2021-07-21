package warmup.jumpingOnClouds;

public class Result {

    /*
     * Complete the 'jumpingOnClouds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY c as parameter.
     */

    public static int jumpingOnClouds(int[] c) {
        // Write your code here

        int n = c.length;

        // for each cloud `c[i]` the corresponding `jumps[i]` specifies
        // how many jumps it takes from the cloud `c[i]` to the last cloud `c[n-1]`
        int[] jumps = new int[n + 1];

        // the cost to reach the last cloud `c[n-1]` via itself is `0`
        jumps[n - 1] = 0;

        // the cost to reach the last cloud by jumping beyond it is "infinity"
        jumps[n] = n;

        // fill the `jumps` counting backward from the last cloud `c[n-1]`
        for (int i = n - 2; i >= 0; --i) {
            jumps[i] = c[i] == 0
                    // the cost to reach `c[n-1]` via a "cumulus" cloud `c[i]` is minimum of the +1 and +2 jumps, plus 1
                    ? Math.min(jumps[i + 1], jumps[i + 2]) + 1
                    // the cost to reach `c[n-1]` via a "thunderhead" `c[i]` cloud is "infinity"
                    : n;
        }

        return jumps[0];
    }
}
