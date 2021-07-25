package bst.is_bst;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node(int data) {
        this(data, null, null);
    }
}

public class Result {
    /*
    Hidden stub code will pass a root argument to the function below.
    Complete the function to solve the challenge.
    Hint: you may want to write one or more helper functions.
    */
    static boolean checkBST(Node node) {
        return checkBST(node, null, null);
    }

    static boolean checkBST(Node node, Integer min, Integer max) {
        return (min == null || node.data > min) &&
                (max == null || node.data < max) &&
                (node.left == null || checkBST(node.left, min, node.data)) &&
                (node.right == null || checkBST(node.right, node.data, max));
    }
}
