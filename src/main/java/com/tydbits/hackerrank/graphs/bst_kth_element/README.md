# K<sup>th</sup> largest node in a BST

> **See also:**
> * [Tests](../../../../../../../test/groovy/com/tydbits/hackerrank/graphs/bst_kth_element)

## "Delete kth largest node from a BST" at [HackerRank](https://www.hackerrank.com/contests/daa-assignment-1/challenges/delete-kth-largest-node-from-a-bst)

Given a Binary Search Tree, delete the kth largest node from it and 
print a level order traversal of the resulting BST.

Your node class/struct shouldn't have any other variables other than key, 
and pointers to left and right node.

### Input Format

* The first line of contains the number of nodes in the tree, 
`N` followed by `k` (k<sup>th</sup> largest node to be deleted).
* In the next line there will a list of `N` unique numbers, which are 
the keys that you will use to construct your BST. 
The keys are given in level order fashion.

### Output Format

You have to print the level-order traversal of BST obtained after deletion 
of the k<sup>th</sup> largest element.

**Note:** The levels of the BST are printed on the same line

### Test Case 1

Input

    5 1
    4 2 7 1 3

Output

    4 2 1 3

The tree constructed is:

                4
               / \
              2   7
             / \
            1   3

Since k=1, we have to delete the largest element which is 7 and hence 
the tree after deletion of 7 is:

                4
               / 
              2   
             / \
            1   3

### Test Case 2

Input

    6 5
    10 8 12 7 11 9

Output

    10 9 12 7 11

The tree constructed is:

                10
               /  \
              8    12
             / \   /
            7   9 11

Since k=5, we have to delete 5<sup>th</sup> largest element which is 8 
and hence the tree after deletion of 8 is:

                10
               /  \
              9    12
             /    /
            7    11