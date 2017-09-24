package sorting.counting_sort_median;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        System.out.println(countNotifications(in, n, d));
    }

    static int countNotifications(Scanner in, int n, int d) {
        int notifications = 0;
        Queue<Integer> expenditures = new ArrayDeque<>();
        CountingSortMedian m = new CountingSortMedian();
        for (int i = 0; i < n; i++) {
            int expenditure = in.nextInt();
            expenditures.add(expenditure);
            if (expenditures.size() > d) {
                if (expenditure >= 2 * m.median()) notifications += 1;
                m.remove(expenditures.poll());
            }
            m.add(expenditure);
        }
        return notifications;
    }
}
