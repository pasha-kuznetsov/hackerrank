# Aho–Corasick algorithm

> **See also [Tests](../../../../../../../test/groovy/com/tydbits/hackerrank/strings/aho_corasick)**

## "Determining DNA Health" at [HackerRank](https://www.hackerrank.com/challenges/determining-dna-health)

DNA is a nucleic acid present in the bodies of living things. Each piece of DNA contains a number of genes,
some of which are beneficial and increase the DNA's total health. Each gene has a health value,
and the total health of a DNA is the sum of the health values of all the beneficial genes that occur
as a substring in the DNA. We represent genes and DNA as non-empty strings of lowercase English alphabetic
letters, and the same gene may appear multiple times as a susbtring of a DNA.

Given the following:

* An array of beneficial gene strings, `genes = [g0, g1 ... g n-1]`.
  Note that these gene sequences are not guaranteed to be distinct.

* An array of gene health values, `health = [h0, h1 ... h n-1]`, 
  where each `h[i]` is the health value for gene `g[i]`.

* A set of `s` DNA strands where the definition of each gene has three components,
  `first`, `last`, and `d`, where string `d` is a DNA for which genes 
  `g[first] ... g[last]` are healthy.

Find and print the respective total healths of the unhealthiest (minimum total health)
and healthiest (maximum total health) strands of DNA as two space-separated values
on a single line.

**Sample Input**
```
6
a b c aa d b
1 2 3 4 5 6
3
1 5 caaab
0 4 xyz
2 4 bcdybc
```

**Sample Output**
```
0 19
```

## Implementation

See Aho–Corasick algorithm description at [Wikipedia](https://en.wikipedia.org/wiki/Aho–Corasick_algorithm)
