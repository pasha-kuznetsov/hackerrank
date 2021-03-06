package strings.lexicographically_minimal_splice;

import java.util.HashMap;

// See README.md for implementation comments.
class LexicographicallyMinimal {
    private final String s1;
    private final String s2;
    private final HashMap<Long /* i1, i2 */, Boolean> suffixLess;

    public LexicographicallyMinimal(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.suffixLess = new HashMap<>();
    }

    String splice() {
        StringBuilder result = new StringBuilder();

        int i1 = 0, i2 = 0;
        while (i1 < s1.length() || i2 < s2.length()) {

            while (isCharLessThan(s1, i1, s2, i2))
                result.append(s1.charAt(i1++));

            while (isCharLessThan(s2, i2, s1, i1))
                result.append(s2.charAt(i2++));

            if (isCharEqualTo(s1, i1, s2, i2)) {
                if (isSuffixLessThan(s1, i1, s2, i2))
                    result.append(s1.charAt(i1++));
                else
                    result.append(s2.charAt(i2++));
            }
        }
        return result.toString();
    }

    private boolean isCharEqualTo(String s, int pos, String other, int otherPos) {
        return pos < s.length() && otherPos < other.length() && s.charAt(pos) == other.charAt(otherPos);
    }

    private boolean isCharLessThan(String s, int pos, String other, int otherPos) {
        return pos < s.length() && (otherPos >= other.length() || s.charAt(pos) < other.charAt(otherPos));
    }

    private boolean isSuffixLessThan(String s, int pos, String other, int otherPos) {
        Boolean isLess = suffixLess.get(suffixKey(pos, otherPos));
        if (isLess == null) {
            int i = 0;
            while (isCharEqualTo(s, pos + i, other, otherPos + i))
                ++i;

            isLess = isCharLessThan(s, pos + i, other, otherPos + i);
            for (; i >= 0; --i)
                suffixLess.put(suffixKey(pos + i, otherPos + i), isLess);
        }
        return isLess;
    }

    private long suffixKey(int pos, long otherPos) {
        return pos | (otherPos << Integer.SIZE);
    }
}
