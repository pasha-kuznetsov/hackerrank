// Count Strings
// https://www.hackerrank.com/challenges/count-strings

package com.tydbits.hackerrank.strings.regex_count;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    static long count(String regex, int len) {
        return new Regex(regex).count(len);
    }
}

class Regex {
    static char empty = '\0';

    final Node root;

    Regex(String regex) {
        this.root = new Parser(regex).parse();
    }

    long count(int len) {
        return 0;
    }

    private static class Parser {
        private final String regex;
        private int pos;

        Parser(String regex) {
            this.regex = regex;
            this.pos = 0;
        }

        private Node parse() {
            return sequence().start;
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
                throw syntaxError(") expected");
            if (expr.start == expr.end) return nested;
            expr.end.edges.add(new Edge(empty, nested.start));
            expr.end = nested.end;
            return expr;
        }

        private IllegalStateException syntaxError(String error) {
            return new IllegalStateException("syntax error: " + error);
        }

        private Expression pipe(Expression first) {
            Expression second = sequence();
            Expression expr = new Expression();
            expr.start.edges.add(new Edge(empty, first.start));
            expr.start.edges.add(new Edge(empty, second.start));
            first.end.edges.add(new Edge(empty, expr.end = new Node()));
            second.end.edges.add(new Edge(empty, expr.end));
            return expr;
        }

        private Expression star(Expression inner) {
            Expression expr = new Expression();
            expr.start.edges.add(new Edge(empty, inner.start));
            inner.end.edges.add(new Edge(empty, inner.start));
            inner.end.edges.add(new Edge(empty, expr.end = new Node()));
            expr.start.edges.add(new Edge(empty, expr.end));
            return expr;
        }

        private char take() {
            return skipWs() ? regex.charAt(pos++) : empty;
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
        final HashMap<Integer, Long> count = new HashMap<>();
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
