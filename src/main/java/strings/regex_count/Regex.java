package strings.regex_count;

import java.util.*;

class Regex {
    static char e = '\0';

    final Node root;
    final Node[] nodes;

    Regex(String regex) {
        Parser parser = new Parser(regex);
        parser.parse();
        SubsetConstruction subset = new SubsetConstruction(parser.expr.start, parser.expr.end);
        this.nodes = subset.dfa();
        this.root = nodes[0];
    }

    static class SubsetConstruction {
        private final Node nfaEnd;
        private final Queue<State> queue;
        private final Map<Set<Node>, Node> dfaMap;
        private final Node dfaRoot;

        SubsetConstruction(Node nfaRoot, Node nfaEnd) {
            this.nfaEnd = nfaEnd;
            this.queue = new ArrayDeque<>();
            this.dfaMap = new HashMap<>();
            this.dfaRoot = addState(eClosure(nfaRoot)).dfa;
        }

        Node[] dfa() {
            while (!queue.isEmpty()) {
                State state = queue.poll();
                for (char c : new char[]{'a', 'b'}) {
                    Set<Node> nfaNext = eClosure(next(state.nfa, c));
                    if (nfaNext.isEmpty())
                        continue;
                    Node dfaNext = dfaMap.get(nfaNext);
                    if (dfaNext == null)
                        dfaNext = addState(nfaNext).dfa;
                    state.dfa.edges.add(new Edge(c, dfaNext));
                }
            }
            return getNodes();
        }

        private Node[] getNodes() {
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(dfaRoot);
            for (Node node : dfaMap.values())
                if (node != dfaRoot) nodes.add(node);
            return nodes.toArray(new Node[nodes.size()]);
        }

        private State addState(Set<Node> nfa) {
            State state = new State(nfa);
            state.dfa.isFinal = nfa.contains(nfaEnd);
            dfaMap.put(state.nfa, state.dfa);
            queue.add(state);
            return state;
        }

        static class State {
            final Set<Node> nfa;
            final Node dfa = new Node();
            State(Set<Node> nfa) { this.nfa = nfa; }
        }

        private Set<Node> eClosure(Node node) {
            return eClosure(Collections.singletonList(node));
        }

        private static Set<Node> eClosure(Collection<Node> nodes) {
            Set<Node> closure = new HashSet<>();
            Stack<Node> stack = new Stack<>();
            stack.addAll(nodes);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (closure.add(node))
                    stack.addAll(next(node, e));
            }
            return closure;
        }

        private static Collection<Node> next(Collection<Node> nodes, char c) {
            Collection<Node> list = new ArrayList<>();
            for (Node node : nodes)
                list.addAll(next(node, c));
            return list;
        }

        private static Collection<Node> next(Node node, char c) {
            Collection<Node> list = new ArrayList<>();
            for (Edge edge : node.edges)
                if (edge.value == c) list.add(edge.node);
            return list;
        }
    }

    static class Parser {
        private final String regex;
        private int pos;
        Expression expr;

        Parser(String regex) {
            this.regex = regex;
            this.pos = 0;
        }

        Node parse() {
            return (expr = sequence()).start;
        }

        private static class Expression {
            Node start = new Node();
            Node end = start;
        }

        private Expression sequence() {
            Expression expr = new Expression();
            for (; ; ) {
                char c = take();
                switch (c) {
                    case 'a':
                    case 'b': literal(expr, c); break;
                    case '(': expr = parenthesis(expr); break;
                    case '|': expr = pipe(expr); break;
                    case '*': expr = star(expr); break;
                    default: putback(); return expr;
                }
            }
        }

        private void literal(Expression expr, char c) {
            expr.end.edges.add(new Edge(c, expr.end = new Node()));
        }

        private Expression parenthesis(Expression expr) {
            Expression nested = sequence();
            if (take() != ')')
                throw new IllegalStateException("syntax error: " + ") expected");
            if (expr.start == expr.end) return nested;
            expr.end.edges.add(new Edge(e, nested.start));
            expr.end = nested.end;
            return expr;
        }

        private Expression pipe(Expression first) {
            Expression second = sequence();
            Expression expr = new Expression();
            expr.start.edges.add(new Edge(e, first.start));
            expr.start.edges.add(new Edge(e, second.start));
            first.end.edges.add(new Edge(e, expr.end = new Node()));
            second.end.edges.add(new Edge(e, expr.end));
            return expr;
        }

        private Expression star(Expression inner) {
            Expression expr = new Expression();
            expr.start.edges.add(new Edge(e, inner.start));
            inner.end.edges.add(new Edge(e, inner.start));
            inner.end.edges.add(new Edge(e, expr.end = new Node()));
            expr.start.edges.add(new Edge(e, expr.end));
            return expr;
        }

        private char take() {
            return skipWs() ? regex.charAt(pos++) : e;
        }

        private void putback() {
            if (pos >= 0) --pos;
        }

        private boolean skipWs() {
            while (pos < regex.length() && Character.isWhitespace(regex.charAt(pos))) ++pos;
            return pos < regex.length();
        }
    }

    static class Node {
        final ArrayList<Edge> edges = new ArrayList<>();
        boolean isFinal;
    }

    static class Edge {
        final char value;
        final Node node;

        Edge(char value, Node node) {
            this.value = value;
            this.node = node;
        }
    }
}
