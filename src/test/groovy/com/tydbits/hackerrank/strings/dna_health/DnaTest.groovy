package com.tydbits.hackerrank.strings.dna_health

import spock.lang.Specification
import spock.lang.Unroll

class DnaTest extends Specification {

    @Unroll
    def "test search '#query' @ #genes"() {
        given:
        def d = [genes as String[], new long[genes.size()]] as Solution.Dna

        when:
        def results = []
        d.search(query, { found ->
            results.addAll(found.keySet().collect { id -> genes.get(id) })
        })

        then:
        results == expectedResults

        where:
        genes                           | query        | expectedResults

        ["ans", "h", "is", "bang", "his", "his", // duplicate
         "history", "is",  // duplicate
         "hers", "she", "tor", "oric"]  | "sehistoric" | ["h", "is", "is", "his", "his", "tor", "oric"]

        ["ans", "h", "is", "bang", "his", "his", // duplicate
         "history", "is",  // duplicate
         "hers", "she", "tor", "oric"]  | "hishebang"  | "h, is, is, his, his, h, she, bang".split(", ")

        ["ans", "h", "is", "bang", "his", "his", // duplicate
         "history", "is",  // duplicate
         "hers", "she", "tor", "oric"]  | "banshee"    | ["ans", "h", "she"]

        ["a", "b", "c", "aa", "d", "b"] | "caaab"      | "c, a, a, aa, a, aa, b, b".split(", ")

        ["a", "b", "c", "aa", "d", "b"] | "xyz"        | []

        ["a", "b", "c", "aa", "d", "b"] | "bcdybc"     | ["b", "b", "c", "d", "b", "b", "c"]
    }
}
