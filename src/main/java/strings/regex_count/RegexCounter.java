package strings.regex_count;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class RegexCounter {
    private final long[][] adjacencyMatrix;
    private final ArrayList<Integer> finalStates;

    RegexCounter(Regex regex) {
        Map<Regex.Node, Integer> indexes = new HashMap<>();
        this.adjacencyMatrix = new long[regex.nodes.length][regex.nodes.length];
        this.finalStates = new ArrayList<>();
        for (Regex.Node node : regex.nodes) {
            int index = getIndex(indexes, node);
            for (Regex.Edge edge : node.edges) {
                int next = getIndex(indexes, edge.node);
                adjacencyMatrix[index][next] = 1;
            }
        }
    }

    private int getIndex(Map<Regex.Node, Integer> indexes, Regex.Node node) {
        Integer index = indexes.get(node);
        if (index == null) {
            indexes.put(node, index = indexes.size());
            if (node.isFinal)
                finalStates.add(index);
        }
        return index;
    }

    long count(int len) {
        long[][] m = new MatrixPower(adjacencyMatrix).power(len);
        long count = 0;
        for (int finalState : finalStates)
            count = (count + m[0][finalState]) % 1000000007L;
        return count;
    }
}
