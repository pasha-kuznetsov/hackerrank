# Two Chars

## "Two Chars" at [HR](https://www.hackerrank.com/challenges/two-characters) `Easy`

String `t` always consists of two distinct alternating characters.
For example, if string `t`'s two distinct characters are `x` and `y`,
then `t` could be `xyxyx` or `yxyxy` but not `xxyy` or `xyyx`.

You can convert some string `s` to string `t` by deleting characters from `s`.
When you delete a character from `s`, you must delete all occurrences of it in `s`.
For example, if `s` = `abaacdabd` and you delete the character `a`,
then the string `s` becomes `bcdbd`.

Given `s`, convert it to the longest possible string `t`.
Then print the length of string `t` on a new line;
if no string `t` can be formed from `s`, print `0` instead.

**Sample Input**
```
10
beabeefeab
```

**Sample Output**
```
5
```

## Implementation

> **See also [Tests](../../../../test/groovy/strings/two_chars)**

Generally, it's pretty simple, other than a twist to avoid removing the chars.

* Create indexes of all positions of every char.
* For each two such indexes check if the positions alternate.
* Return sum of longest alternating indexes, if any.
