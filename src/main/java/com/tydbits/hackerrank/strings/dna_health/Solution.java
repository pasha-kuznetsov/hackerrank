// Determining DNA Health
// https://www.hackerrank.com/challenges/determining-dna-health/problem

package com.tydbits.hackerrank.strings.dna_health;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Solution {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int n = Integer.parseInt(in.readLine());
            String genes = in.readLine();
            String health = in.readLine();

            Dna dna = new Dna();
            parse(dna, n, genes, health);

            DnaHealth dnaHealth = new DnaHealth(dna);
            int s = Integer.parseInt(in.readLine());
            for (int i = 0; i < s; i++) {
                String str = in.readLine();
                LongParser longs = new LongParser(str);
                int first = (int) longs.next();
                int last = (int) longs.next();
                dnaHealth.processStrand(first, last, str, skipUntil(str, longs.pos, Character::isAlphabetic));
            }

            System.out.printf("%d %d\n", dnaHealth.minHealth(), dnaHealth.maxHealth());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void parse(Dna dna, int n, String geneStr, String healthStr) {
        int pos = 0;
        LongParser healthParser = new LongParser(healthStr);
        for (int i = 0; i < n; ++i)
            pos = dna.parseGene(i, geneStr, pos, healthParser.next());
        dna.setSuffixes();
    }

    static class LongParser {
        final String str;
        int pos;

        LongParser(String str) {
            this.str = str;
        }

        long next() {
            pos = skipUntil(str, pos, Character::isDigit);
            StringBuilder sb = new StringBuilder(32);
            char ch;
            for (; pos < str.length() && Character.isDigit(ch = str.charAt(pos)); ++pos)
                sb.append(ch);
            return Long.parseLong(sb.toString());
        }
    }

    private static int skipUntil(String str, int pos, Predicate<Character> predicate) {
        while (pos < str.length() && !predicate.test(str.charAt(pos)))
            ++pos;
        return pos;
    }

    static class DnaHealth {
        private final Dna dna;
        private long minHealth = Long.MAX_VALUE;
        private long maxHealth = Long.MIN_VALUE;

        long outputs;

        DnaHealth(Dna dna) { this.dna = dna; }

        long minHealth() { return minHealth; }
        long maxHealth() { return maxHealth; }

        void processStrand(int first, int last, String d) {
            processStrand(first, last, d, 0);
        }

        void processStrand(int first, int last, String d, int start) {
            long health = getHealth(first, last, d, start);
            if (health < minHealth) minHealth = health;
            if (health > maxHealth) maxHealth = health;
        }

        private long getHealth(int first, int last, String d, int start) {
            final long[] health = new long[] {0};
            dna.search(d, start, (GenesHealth genesHealth) -> {
                for (long geneHealth : genesHealth.subMap(first, true, last, true).values()) {
                    health[0] += geneHealth;
                    outputs += 1;
                }
            });
            return health[0];
        }
    }

    static class Dna {
        final Node root;

        Dna() {
            this.root = new Node(null, '\0');
        }

        Dna(String[] genes, long[] health) {
            this();
            for (int i = 0; i < genes.length; ++i)
                addGene(i, genes[i], health[i]);
            setSuffixes();
        }

        private void addGene(int id, String str, long health) {
            parseGene(id, str, 0, health);
        }

        private int parseGene(int id, String str, int start, long health) {
            int i = start;
            while (i < str.length() && !Character.isAlphabetic(str.charAt(i)))
                ++i;
            char letter;
            Node gene = root;
            for (; i < str.length() && Character.isAlphabetic(letter = str.charAt(i)); ++i) {
                Node next = gene.children.get(letter);
                if (next == null)
                    gene.children.put(letter, next = new Node(gene, letter));
                gene = next;
            }
            if (gene.output == null)
                gene.output = new GenesHealth();
            gene.output.put(id, health);
            return i;
        }

        private void setSuffixes() {
            Queue<Node> queue = new ArrayDeque<>();

            // `root` and its immediate children point back to `root`
            root.suffix = root;
            for (Node gene : root.children.values()) {
                gene.suffix = root;
                queue.addAll(gene.children.values());
            }

            // everyone else discovers their suffix from their parent
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                queue.addAll(node.children.values());

                Node suffix = node.parent;
                do {
                    suffix = suffix.suffix;
                    node.suffix = suffix.children.getOrDefault(node.letter, suffix == root ? root : null);
                } while (node.suffix == null);

                node.suffixOutput = node.suffix.output == null
                        ? node.suffix.suffixOutput
                        : node.suffix;
            }
        }

        // `predicate` returns `true` to stop searching
        void search(String str, Consumer<GenesHealth> output) {
            search(str, 0, output);
        }

        void search(String str, int start, Consumer<GenesHealth> output) {
            Node node = root;
            for (int i = start; i < str.length(); ++i) {
                char letter = str.charAt(i);

                Node next;
                do {
                    next = node.children.get(letter);
                    if (next != null) node = next;
                    else if (node == root) break;
                    else node = node.suffix;
                } while (next == null);

                for (Node suffix = node.suffixOutput; suffix != null; suffix = suffix.suffixOutput)
                    output.accept(suffix.output);

                if (node.output != null)
                    output.accept(node.output);
            }
        }

        private static class Node {
            final Node parent;
            final char letter;
            final HashMap<Character, Node> children;
            GenesHealth output;
            Node suffix;
            Node suffixOutput;

            Node(Node parent, char letter) {
                this.parent = parent;
                this.letter = letter;
                this.children = new HashMap<>();
            }
        }
    }

    static class GenesHealth extends TreeMap<Integer, Long> {
    }
}
