package graphs.balanced_forest;

import java.util.*;

class Node {
    int n;
    int data;
    long sum;
    List<Node> adjacent = new ArrayList<>();
    Set<Node> parents = new HashSet<>();
    List<Node> children = new ArrayList<>();

    Node(int n, int data) {
        this.n = n;
        this.data = data;
        this.sum = data;
    }

    public String toString() {
        return String.format("%s,%s", this.n, this.data);
    }
}

class Tree {
    Node root;
    Node[] nodes;
    SortedMap<Long, List<Node>> bySum = new TreeMap<>();

    Tree(List<Integer> c, List<List<Integer>> edges) {
        this.nodes = new Node[c.size()];
        for (int i = 0; i < c.size(); ++i) {
            this.nodes[i] = new Node(i + 1, c.get(i));
        }
        for (List<Integer> edge : edges) {
            addAdjacent(nodes[edge.get(0) - 1], nodes[edge.get(1) - 1]);
        }
        this.root = nodes[0];
        addChildren(root, null);
    }

    private void addAdjacent(Node node1, Node node2) {
        node1.adjacent.add(node2);
        node2.adjacent.add(node1);
    }

    private long addChildren(Node node, Node parent) {
        for (Node adj : node.adjacent) {
            if (adj != parent) {
                adj.parents.add(node);
                adj.parents.addAll(node.parents);
                node.children.add(adj);
                node.sum += addChildren(adj, node);
            }
        }
        List<Node> subtrees = this.bySum.computeIfAbsent(node.sum, k -> new ArrayList<>());
        subtrees.add(node);
        return node.sum;
    }
}

class Result {
    public static int balancedForest(List<Integer> c, List<List<Integer>> edges) {
        Tree tree = new Tree(c, edges);

        long minw = Integer.MAX_VALUE;

        // try detaching each subtree `a` except root, figuring out the other node `b` which can also be removed
        for (Map.Entry<Long, List<Node>> aEntry : tree.bySum.entrySet()) {
            long aSum = aEntry.getKey();
            List<Node> aNodes = aEntry.getValue();

            if (3 * aSum > tree.root.sum) {
                long w = 3 * aSum - tree.root.sum;
                if (w < minw) {
                    // two disjoint subtrees

                    // 1. a = b, adding `w` to the root
                    // r-a-b + w  =  a  =  b
                    // r-2a + w  =  a
                    // w = 3a-r
                    if (aNodes.size() > 1) {
                        minw = w;
                    }

                    // 2. b < a, adding `w` to `b`
                    // r-a-b  =  a  =  b+w
                    // b  =  r-2a
                    // w  =  a-b  = 3a-r
                    else {
                        long bSum = tree.root.sum - 2 * aSum;
                        List<Node> bNodes = tree.bySum.get(bSum);
                        if (bNodes != null && anyNotChild(aNodes, bNodes)) {
                            minw = w;
                        }
                    }
                }

                // `a` is nested in `b`

                Set<Long> parentSums = new HashSet<>();
                for (Node a : aNodes) {
                    for (Node b : a.parents) {
                        parentSums.add(b.sum);
                    }
                }

                // 3. adding `w` to root:
                // b-a=a  ->  b=2a
                // w  =  a-(r-b)  =  a-(r-2a)  =  3a-r
                if (parentSums.contains(2 * aSum)) {
                    minw = w;
                }

                // 4. adding `w` to `b`:
                // r-b=a  ->  b=r-a
                // w  =  a-(b-a)  =  a-(r-a-a)  =  3a-r
                if (parentSums.contains(tree.root.sum - aSum)) {
                    minw = w;
                }
            }

            // `b` is nested in `a`

            // 5. adding `w` to `b`
            // r-a  =  a-b  =  b+w
            // b  =  2a-r
            // w  =  r-a-b  =  r-a-(2a-r)  =  2r-3a
            if (2 * aSum > tree.root.sum) {
                long bSum = 2 * aSum - tree.root.sum;
                long w = 2 * tree.root.sum - 3 * aSum;
                if (w > 0 && w < minw) {
                    List<Node> bNodes = tree.bySum.get(bSum);
                    if (bNodes != null && anyChild(aNodes, bNodes)) {
                        minw = w;
                    }
                }
            }
        }

        return (int) (minw < Integer.MAX_VALUE ? minw : -1);
    }

    private static boolean anyNotChild(List<Node> aNodes, List<Node> bNodes) {
        for (Node b : bNodes) {
            for (Node a : aNodes) {
                if (!b.parents.contains(a))
                    return true;
            }
        }
        return false;
    }

    private static boolean anyChild(List<Node> aNodes, List<Node> bNodes) {
        for (Node b : bNodes) {
            for (Node a : aNodes) {
                if (b.parents.contains(a))
                    return true;
            }
        }
        return false;
    }
}
