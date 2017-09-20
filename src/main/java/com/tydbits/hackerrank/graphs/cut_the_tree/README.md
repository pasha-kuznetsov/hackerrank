# Cut the Tree

> **See also [Tests](../../../../../../../test/groovy/com/tydbits/hackerrank/graphs/cut_the_tree)**

## "Cut the Tree" at [HackerRank](https://www.hackerrank.com/challenges/cut-the-tree)

Anna loves graph theory! She has an `n`-vertex tree, `t`, where each vertex `u`:
* Is indexed with a unique integer from `1` to `n`.
* Contains a data value, `data[u]`.

Anna observes that cutting any edge, `u<->v`, in `t` results in the formation
of two separate trees denoted by `t1` and `t2`. She also defines the following:
* The _sum_ of a tree is the sum of the `data` values for all vertices in the tree.
* The difference between two trees created by cutting edge `u<->v` is denoted by
`d[u<->v] = |sum(t1) - sum(t2)|`.

Given the definition of tree `t`, remove some edge `u<->v` such that the value 
of `d[u<->v]` is minimal. Then print the value of the minimum possible `d[u<->v]`
as your answer.

**Notes:**
* The tree is always rooted at vertex `1`.

**Sample Input**
```
6
100 200 100 500 100 600
1 2
2 3
2 5
4 5
5 6
```

**Sample Output**
```
400
```

## Implementation

The trick is that the input is given as an undirected graph,
so we cannot easily infer which of an edge's nodes is a parent,
hence treat it as a graph, where the tree is discovered while iteration:  
* Process input, recording adjacent nodes.
* DFS recording sum of the respective subtrees `sum(ti)`.
* Iterate through the nodes to find the min `d[u<->v]`.
