package com.tydbits.hackerrank.strings.dna_health

import spock.lang.Specification
import spock.lang.Unroll

class SolutionParserTest extends Specification {

    @Unroll
    def "test search '#query' @ #genes"() {
        given:
        def d = new Solution.Dna()
        Solution.parse(d, n, genes, health)

        when:
        def results = []
        def g = genes.trim().split()
        d.search(query, { found ->
            found.each { k, v ->
                results.add(g[k] + ": " + v)
            }
        })

        then:
        results == expectedResults

        where:
        n || genes           | health         | query    | expectedResults
        6 || "a b c aa d b"  | "1 2 3 4 5 6"  | "caaab"  | "c: 3, a: 1, a: 1, aa: 4, a: 1, aa: 4, b: 2, b: 6".split(", ")
        6 || "a b c aa d b " | "1 2 3 4 5 6 " | "xyz"    | []
        6 || " a b c aa d b" | " 1 2 3 4 5 6" | "bcdybc" | "b: 2, b: 6, c: 3, d: 5, b: 2, b: 6, c: 3".split(", ")
    }
}
