// Determining DNA Health
// https://www.hackerrank.com/challenges/determining-dna-health/problem

package com.tydbits.hackerrank.strings.dna_health;

import java.util.*;
import java.util.function.Consumer;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        String[] genes = new String[n];
        for(int g=0; g < n; g++) genes[g] = in.next();

        long[] health = new long[n];
        for(int h=0; h < n; h++) health[h] = in.nextLong();

        Dna dna = new Dna(genes, health);
        DnaHealth dnaHealth = new DnaHealth(dna);

        int s = in.nextInt();
        for (int a0 = 0; a0 < s; a0++){
            int first = in.nextInt();
            int last = in.nextInt();
            String d = in.next();
            dnaHealth.processStrand(first, last, d);
        }

        System.out.printf("%d %d\n", dnaHealth.minHealth(), dnaHealth.maxHealth());
    }

    static class DnaHealth {
        private final Dna dna;
        private long minHealth = Long.MAX_VALUE;
        private long maxHealth = Long.MIN_VALUE;

        DnaHealth(Dna dna) { this.dna = dna; }

        long minHealth() { return minHealth; }
        long maxHealth() { return maxHealth; }

        void processStrand(int first, int last, String d) {
            long health = getHealth(first, last, d);
            if (health < minHealth) minHealth = health;
            if (health > maxHealth) maxHealth = health;
        }

        private long getHealth(int first, int last, String d) {
            final long[] health = new long[] {0};
            dna.search(d, (GenesHealth genesHealth) -> {
                for (long geneHealth : genesHealth.subMap(first, true, last, true).values())
                    health[0] += geneHealth;
            });
            return health[0];
        }
    }

    static class Dna {
        final Node root;

        Dna(String[] genes, long[] health) {
            this.root = new Node(null, '\0');
            for (int i = 0; i < genes.length; ++i)
                addGene(i, genes[i], health[i]);
            setSuffixes();
        }

        // `predicate` returns `true` to stop searching
        void search(String str, Consumer<GenesHealth> output) {
            Node node = root;
            for (int i = 0; i < str.length(); ++i) {
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

        private void addGene(int id, String str, long health) {
            Node gene = root;
            for (int i = 0; i < str.length(); ++i) {
                char letter = str.charAt(i);
                Node next = gene.children.get(letter);
                if (next == null)
                    gene.children.put(letter, next = new Node(gene, letter));
                gene = next;
            }
            if (gene.output == null)
                gene.output = new GenesHealth();
            gene.output.put(id, health);
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
