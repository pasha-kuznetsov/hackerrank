Lexicographically Minimal Splice
================================

"Morgan and a String" at [HackerRank](https://www.hackerrank.com/challenges/morgan-and-a-string)
-------------------------------------

Jack and Daniel are friends. Both of them like letters, especially upper-case ones. 
They are cutting upper-case letters from newspapers, and each one of them has their 
collection of letters stored in separate stacks.
 
One beautiful day, Morgan visited Jack and Daniel. He saw their collections. 
Morgan wondered what is the lexicographically minimal string, made of that 
two collections. He can take a letter from a collection when it is on the top 
of the stack. 

Also, Morgan wants to use all the letters in the boys' collections.

Input Format
------------

The first line contains the number of test cases, `T`. 
Every next two lines have such format: the first line contains string `s1`, 
and the second line contains string `s2`.

Constraints
-----------

```
T:    [1, 5]
|s1|: [1, 10^5]
|s2|: [1, 10^5]
```

Implementation
==============

While the current chars in the strings are different, taking the one which is less,
clearly results in lexicographically minimal string. 

Things become less trivial when the chars are equal, as one needs to examine 
the suffixes beyond to figure out which order of consuming the chars would 
result in a lexicographically smaller string.

For example, for `ABAA` and `ABA`, let's assume we've already taken the first
`A`'s from both strings, and now we have two possible paths, depending on which
of the `B`'s we take first:
```
AA BAA -> AAB AA  -> AABA A   -> AABAA    -> AABAABA
   BA         BA          BA           BA 
```
```
AA BAA -> AAB BAA -> AABA BAA -> AABAB AA -> AABABAA
   BA         A
```

Clearly, the algorithm should look ahead to ensure the produced result is `AABAABA`,
not `AABABAA`. That is, in the above example we should choose the suffix which is
lexicographically less than the other, including the above special case of treating
`BAA` as being "less" than `BA`.

The computational challenge here is avoiding comparing the same suffixes over and over,
which could've been solved by using Suffix Arrays, but a simpler approach is sufficient
of just caching the suffixes which we had to  "look ahead".
```
HashMap<Long /* i1, i2 */, Boolean> suffixLess;
```
