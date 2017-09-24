package strings.regex_count;

import java.util.HashMap;
import java.util.Map;

class MatrixPower {
    private final long[][] matrix;
    private final Map<Integer, long[][]> powers;

    MatrixPower(long[][] matrix) {
        this.matrix = matrix;
        this.powers = new HashMap<>();
    }

    long[][] power(int p) {
        if (p == 1) return matrix;
        long[][] result = powers.get(p);
        if (result != null)
            return result;
        result = power(p / 2);
        powers.put(p / 2 * 2, result = multiply(result, result));
        if (p % 2 > 0)
            powers.put(p, result = multiply(result, power(p % 2)));
        return result;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        long[][] m = new long[a.length][a.length];
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a.length; ++j) {
                long sum = 0;
                for (int k = 0; k < a.length; ++k)
                    sum = (sum + a[i][k] * b[k][j]) % 1000000007L;
                m[i][j] = sum;
            }
        }
        return m;
    }
}
