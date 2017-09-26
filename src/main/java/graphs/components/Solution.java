package graphs.components;

import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        int nodes = scan.nextInt(), edges = scan.nextInt();
        GraphComponents g = new GraphComponents(nodes);
        for(int i = 0; i < edges; i++)
            g.addEdge(scan.nextInt(), scan.nextInt());
        System.out.println(g.pairings());
    }
}

class GraphComponents {
    private final Map<Integer, Node> nodes = new HashMap<>();

    GraphComponents(int n) {
        for (int i = 0; i < n; ++i) nodes.put(i, new Node());
    }

    void addEdge(int a, int b) {
        addEdge(nodes.get(a), nodes.get(b));
    }

    private void addEdge(Node a, Node b) {
        a.edges.add(b);
        b.edges.add(a);
    }

    List<Integer> components() {
        List<Integer> components = new ArrayList<>();
        Set<Node> seen = new HashSet<>();
        for (Node root: nodes.values()) {
            if (!seen.add(root)) continue;
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            int componentSize = 1;
            do {
                Node node = stack.pop();
                for (Node next: node.edges) {
                    if (!seen.add(next)) continue;
                    stack.add(next);
                    componentSize++;
                }
            } while (!stack.isEmpty());
            components.add(componentSize);
        }
        return components;
    }

    long pairings() {
        long pairings = 0;
        int totalComponents = 0;
        for (int c : components()) {
            pairings += totalComponents * c;
            totalComponents += c;
        }
        return pairings;
    }

    private static class Node {
        ArrayList<Node> edges = new ArrayList<>();
    }
}
