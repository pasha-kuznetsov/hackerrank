package strings.two_chars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class CharAlternator {
    private List<List<Integer>> byStart = new ArrayList<>();

    CharAlternator(String str) {
        HashMap<Character, List<Integer>> byChar = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            byChar.compute(str.charAt(i), (c, pos) -> {
                if (pos == null) byStart.add(pos = new ArrayList<>());
                return pos;
            }).add(i);
        }
    }

    int max() {
        int max = 0;
        for (int i = 0; i < byStart.size() - 1; ++i) {
            List<Integer> a = byStart.get(i);
            for (int j = i + 1; j < byStart.size(); ++j) {
                List<Integer> b = byStart.get(j);
                if (a.size() + b.size() > max && isAlternating(a, b)) {
                    max = a.size() + b.size();
                }
            }
        }
        return max;
    }

    // 0,  2,  4
    //   1,  3[, 5]
    private boolean isAlternating(List<Integer> a, List<Integer> b) {
        if (a.size() < b.size() || a.size() > b.size() + 1)
            return false;
        int i = 1, j = 1;
        for (; i < a.size() && j < b.size(); ++i, ++j) {
            if (a.get(i) > b.get(j) || a.get(i) < b.get(j - 1))
                return false;
        }
        return i == a.size() || a.get(i) > b.get(j - 1);
    }
}
