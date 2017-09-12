# Regular Expressions

## "Count Strings" at [HR](https://www.hackerrank.com/challenges/count-strings)

A regular expression is used to describe a set of strings. 
For this problem the alphabet is limited to 'a' and 'b'.

We define `R` to be a valid regular expression if: 
1) `R` is `a` or `b`. 
2) `R` is of the form `(R1R2)`, where `R1` and `R2` are regular expressions. 
3) `R` is of the form `(R1|R2)` where `R1` and `R2` are regular expressions. 
4) `R` is of the form `(R1*)` where `R1` is a regular expression.

Regular expressions can be nested and will always have have two elements 
in the parentheses. (`*` is an element, `|` is not; basically, there will 
always be pairwise evaluation) Additionally, `*` will always be the second element; 
`(*a)` is invalid.

The set of strings recognized by  are as follows: 
1) If `R` is `a`, then the set of strings recognized = `a`. 
2) If `R` is `b`, then the set of strings recognized = `b`. 
3) If `R` is of the form `(R1R2)` then the set of strings recognized = all strings 
which can be obtained by a concatenation of strings `s1` and `s2`, where 
`s1` is recognized by `R1` and `s2` by `R2`. 
4) If `R` is of the form `(R1|R2)` then the set of strings recognized = 
union of the set of strings recognized by `R1` and `R2`. 
5) If `R` is of the form `(R1*)` then the the strings recognized are 
the empty string and the concatenation of an arbitrary number of copies 
of any string recognized by `R1`.

### Task
 
Given a regular expression and an integer, `L`, count how many strings of length `L` 
are recognized by it.
