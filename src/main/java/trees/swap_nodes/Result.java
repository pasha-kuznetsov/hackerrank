package trees.swap_nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }
}

public class Result {
    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        Node root = buildTree(indexes);
        List<List<Integer>> results = new ArrayList<>();
        for (int k : queries) {
            List<Integer> result = new ArrayList<>();
            swapNodes(result, root, k, 1);
            results.add(result);
        }
        return results;
    }

    private static Node buildTree(List<List<Integer>> indexes) {
        Map<Integer, Node> nodes = new HashMap<>();
        int index = 0;
        for (List<Integer> children : indexes) {
            Node node = nodes.computeIfAbsent(++index, Node::new);
            int left = children.size() > 0 ? children.get(0) : -1;
            int right = children.size() > 1 ? children.get(1) : -1;
            node.left = left == -1 ? null : nodes.computeIfAbsent(left, Node::new);
            node.right = right == -1 ? null : nodes.computeIfAbsent(right, Node::new);
        }
        return nodes.get(1);
    }

    private static void swapNodes(List<Integer> result, Node node, int k, int d) {
        if (d % k == 0) {
            Node l = node.left;
            node.left = node.right;
            node.right = l;
        }
        if (node.left != null) {
            swapNodes(result, node.left, k, d + 1);
        }
        result.add(node.data);
        if (node.right != null) {
            swapNodes(result, node.right, k, d + 1);
        }
    }
}
