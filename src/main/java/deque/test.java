package deque;

import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        WindowCountingUnique window = new WindowCountingUnique(in.nextInt());
        for (int i = 0; i < n; i++)
            window.add(in.nextInt());
        System.out.print(window.maxUnique);
    }
}

class WindowCountingUnique {
    private final int m;
    private final Deque<Integer> deque = new ArrayDeque<>();
    private final HashMap<Integer, Integer> counts = new HashMap<>();
    private int unique = 0;
    int maxUnique = 0;

    WindowCountingUnique(int m) { this.m = m; }

    void add(int added) {
        if (counts.compute(added, (num, c) -> c == null ? 1 : c + 1) == 1)
            unique++;

        deque.add(added);

        if (deque.size() > m && counts.compute(deque.poll(), (num, c) -> c - 1) == 0)
            unique--;

        if (deque.size() == m && unique > maxUnique)
            maxUnique = unique;
    }
}
