package com.tydbits.hackerrank.graphs.cut_the_tree;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        System.out.println(main(System.in));
    }

    static int main(InputStream in) {
        Scanner scanner = new Scanner(in);
        Node[] nodes = new Node[scanner.nextInt()];
        for (int i = 0; i < nodes.length; ++i)
            nodes[i] = new Node(scanner.nextInt());
        for (int i = 0; i < nodes.length - 1; ++i)
            addAdjacent(nodes[scanner.nextInt() - 1], nodes[scanner.nextInt() - 1]);
        nodes[0].addChildren(null);
        return minDiff(nodes);
    }

    private static void addAdjacent(Node node1, Node node2) {
        node1.adjacent.add(node2);
        node2.adjacent.add(node1);
    }

    private static int minDiff(Node[] nodes) {
        int min = Integer.MAX_VALUE;
        int total = nodes[0].sum;
        for (int i = 1; i < nodes.length; ++i) {
            int sum1 = nodes[i].sum;
            int sum2 = total - sum1;
            int diff = Math.abs(sum2 - sum1);
            if (diff < min)
                min = diff;
        }
        return min;
    }

    static class Node {
        ArrayList<Node> adjacent = new ArrayList<>();
        int sum;

        Node(int data) {
            this.sum = data;
        }

        int addChildren(Node parent) {
            for (Node adj : adjacent) {
                if (adj != parent) {
                    sum += adj.addChildren(this);
                }
            }
            return sum;
        }
    }
}
