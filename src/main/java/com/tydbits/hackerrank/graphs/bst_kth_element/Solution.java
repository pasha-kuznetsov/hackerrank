package com.tydbits.hackerrank.graphs.bst_kth_element;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        deleteKthLargest(System.in, System.out);
    }

    static void deleteKthLargest(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Tree tree = new Tree();
        for (int i = 0; i < n; ++i)
            tree.add(scanner.nextInt());

        // TODO: find k-th
        Tree.Node node = tree.findParent(Integer.MAX_VALUE);
        tree.delete(node);

        for (Tree.BFS bfs = new Tree.BFS(tree.root); bfs.hasNext(); ) {
            out.print(bfs.next().key);
            out.print(' ');
        }
    }
}

class Tree {
    Node root;

    void add(int key) {
        if (root == null)
            root = new Node(key);
        else
            add(root, new Node(key));
    }

    private void add(Node parent, Node node) {
        findParent(parent, node.key).setChild(node);
    }

    Node findParent(int key) {
        return findParent(root, key);
    }

    private Node findParent(Node parent, int key) {
        for (Node child = parent; child != null; child = parent.getChild(key))
            parent = child;
        return parent;
    }

    void delete(Node node) {
        Node parent = root, child = parent;
        for (; child != node && child != null; child = parent.getChild(node.key))
            parent = child;
        if (parent.left == child) parent.left = null;
        if (parent.right == child) parent.right = null;
        // TODO: rotate etc
    }

    static class BFS {
        Queue<Node> queue = new ArrayDeque<>();

        BFS(Node root) { queue.add(root); }

        boolean hasNext() { return !queue.isEmpty(); }

        Node next() {
            Node node = queue.poll();
            if (node == null) return null;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
            return node;
        }
    }

    static class Node {
        int key;
        Node left, right;

        Node(int key) { this.key = key; }

        Node getChild(int key) { return key <= this.key ? left : right; }

        void setChild(Node child) {
            if (child.key <= this.key)
                left = child;
            else
                right = child;
        }

        @Override
        public String toString() {
            return Integer.toString(key);
        }
    }
}
