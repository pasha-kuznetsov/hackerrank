package trees.lowestCommonAncestor;

import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.
        if (root.left != null && v1 < root.data && v2 < root.data) {
            return lca(root.left, v1, v2);
        }
        if (root.right != null && v1 > root.data && v2 > root.data) {
            return lca(root.right, v1, v2);
        }
        return root;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            if (data <= root.data) {
                root.left = insert(root.left, data);
            } else {
                root.right = insert(root.right, data);
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Node root;
        int v1;
        int v2;
        try (Scanner scan = new Scanner(System.in)) {
            int t = scan.nextInt();
            root = parseTree(scan, t);
            v1 = scan.nextInt();
            v2 = scan.nextInt();
        }
        Node ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }

    static Node parseTree(Scanner scan, int t) {
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        return root;
    }
}
