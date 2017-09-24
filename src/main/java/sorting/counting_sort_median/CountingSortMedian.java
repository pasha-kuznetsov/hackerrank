package sorting.counting_sort_median;

import java.util.ArrayList;
import java.util.Collections;

class CountingSortMedian {
    private final long counts[] = new long[201];
    private final ArrayList<Integer> values = new ArrayList<>();
    private int size;

    void add(int value) {
        ++size;
        if (++counts[value] != 1) return;
        values.add(-Collections.binarySearch(values, value) - 1, value);
    }

    void remove(int value) {
        --size;
        if (--counts[value] != 0) return;
        values.remove(Collections.binarySearch(values, value));
    }

    double median() {
        double a = 0, b = 0;
        long n = 0;
        for (Integer value : values) {
            long count = counts[value];
            n += count;
            if (n <= size / 2) a = value;
            if (n >= size / 2 + 1) {
                if (n - count < size / 2) a = value;
                if (n - count <= size / 2 + 1) b = value;
                break;
            }
        }
        return size % 2 == 0 ? (a + b) / 2 : b;
    }
}
