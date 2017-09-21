package com.tydbits.hackerrank.graphs.bst_kth_element;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        deleteKthLargest(System.in, System.out);
    }

    static void deleteKthLargest(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        BinarySearchTree tree = parseTree(scanner, n);
        tree.delete(kMax(tree, k));
        printTree(out, tree);
    }

    static BinarySearchTree parseTree(Scanner scanner, Integer n) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; n != null ? i < n : scanner.hasNextInt(); ++i)
            tree.add(scanner.nextInt());
        return tree;
    }

    private static int kMax(BinarySearchTree tree, int k) {
        int kMax = 0;
        for (Iterator<BinarySearchTree.Node> rev = tree.reverse(); k > 0; --k)
            kMax = rev.next().key;
        return kMax;
    }

    static void printTree(PrintStream out, BinarySearchTree tree) {
        for (Iterator<BinarySearchTree.Node> bfs = tree.bfs(); bfs.hasNext(); ) {
            out.print(bfs.next().key);
            out.print(' ');
        }
    }
}

