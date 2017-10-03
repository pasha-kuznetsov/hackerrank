package strings.two_chars;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.nextInt();
        String s = in.next();
        System.out.print(new CharAlternator(s).max());
    }
}
