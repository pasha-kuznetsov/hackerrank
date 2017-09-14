// Count Strings
// https://www.hackerrank.com/challenges/count-strings

package com.tydbits.hackerrank.strings.regex_count;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    static long count(String regex, int len) {
        Regex re = new Regex(regex);
        return 0;
    }
}

class Regex {
    static char empty = '\0';

    final Node root;

    Regex(String regex) {
        this.root = new Parser(regex).parse();
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

        private Expression sequence() {
            Expression expr = new Expression();
            for (; ; ) {
                switch (peek()) {
                    case 'a':
                    case 'b':
                        Node node = expr.end = new Node();
                        expr.start.edges.add(new Edge(take(), node));
                        break;
                    case '(':
                        take();
                        Expression nested = sequence();
                        if (take() != ')')
                            throw syntaxError(") expected");
                        if (expr.start == expr.end) {
                            expr = nested;
                        } else {
                            expr.end.edges.add(new Edge(empty, nested.start));
                            expr.end = nested.end;
                        }
                        break;
                    case '|':
                        take();
                        expr = pipe(expr);
                        break;
                    case '*':
                        take();
                        expr = star(expr);
                        break;
                    default:
                        return expr;
                }
            }
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
            return expr;
        }

        private class Expression {
            Node start = new Node();
            Node end = start;
        }

        private char peek() {
            return skipWs() ? regex.charAt(pos) : empty;
        }

        private char take() {
            return skipWs() ? regex.charAt(pos++) : empty;
        }

        private boolean skipWs() {
            while (pos < regex.length() && Character.isWhitespace(regex.charAt(pos))) ++pos;
            return pos < regex.length();
        }
    }

    static class Node {
        final ArrayList<Edge> edges = new ArrayList<>();
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
