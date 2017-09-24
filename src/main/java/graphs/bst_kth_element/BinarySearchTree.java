package graphs.bst_kth_element;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

class BinarySearchTree {
    private Node root;

    void add(int key) { root = add(root, key); }

    private Node add(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = add(node.left, key);
        if (key > node.key) node.right = add(node.right, key);
        return node;
    }

    void delete(int key) { root = delete(root, key); }

    private Node delete(Node node, int key) {
        if (node == null) return null;
        if (key == node.key) return delete(node);
        if (key < node.key) node.left = delete(node.left, key);
        if (key > node.key) node.right = delete(node.right, key);
        return node;
    }

    private Node delete(Node node) {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;
        node.key = min(node.right);
        node.right = delete(node.right, node.key);
        return node;
    }

    private int min(Node node) {
        while (node.left != null) node = node.left;
        return node.key;
    }

    Iterator<Node> reverse() { return new Reverse(root); }

    Iterator<Node> bfs() { return new Bfs(root); }

    private static class Reverse implements Iterator<Node> {
        Stack<Node> stack = new Stack<>();

        Reverse(Node root) { pushNext(root); }

        public boolean hasNext() { return !stack.isEmpty(); }

        public Node next() {
            Node node = stack.pop();
            if (node != null) pushNext(node.left);
            return node;
        }

        private void pushNext(Node node) {
            for (; node != null; node = node.right)
                stack.push(node);
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    private static class Bfs implements Iterator<Node> {
        Queue<Node> queue = new ArrayDeque<>();

        Bfs(Node root) { queue.add(root); }

        public boolean hasNext() { return !queue.isEmpty(); }

        public Node next() {
            Node node = queue.poll();
            if (node == null) return null;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
            return node;
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    static class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }
}
