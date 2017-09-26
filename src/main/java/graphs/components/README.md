# Graph Components

> **See also [Tests](../../../../test/groovy/graphs/components)**

## "Journey to the Moon" at [HR](https://www.hackerrank.com/challenges/journey-to-the-moon) `Medium`

The member states of the UN are planning to send `2` people to the Moon. 
But there is a problem. In line with their principles of global unity, 
they want to pair astronauts of `2` different countries.

There are `N` trained astronauts numbered from `0` to `N-1`. But those in charge 
of the mission did not receive information about the citizenship of each 
astronaut. The only information they have is that some particular pairs 
of astronauts belong to the same country.

Your task is to compute in how many ways they can pick a pair of astronauts 
belonging to different countries. Assume that you are provided enough pairs 
to let you identify the groups of astronauts even though you might not know 
their country directly. For instance, if `1`, `2`, `3` are astronauts from 
the same country; it is sufficient to mention that `(1, 2)` and `(2, 3)` 
are pairs of astronauts from the same country without providing information 
about a third pair `(1, 3)`. 

**Sample Input 0**
```
5 3
0 1
2 3
0 4
```

**Sample Output 0**
```
6
```

**Sample Input 1**
```
4 1
0 2
```

**Sample Output 1**
```
5
```

## Implementation

* Build the graph.
* Count component sizes via a DFS walk.
* Multiply the above to get the number of possible pairings.
