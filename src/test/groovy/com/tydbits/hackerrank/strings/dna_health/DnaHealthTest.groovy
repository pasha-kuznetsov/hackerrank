package com.tydbits.hackerrank.strings.dna_health

import spock.lang.Specification
import spock.lang.Timeout
import spock.lang.Unroll

class DnaHealthTest extends Specification {

    @Unroll
    def "test #genes"() {
        given:
        def d = new Solution.DnaHealth([genes as String[], health as long[]] as Solution.Dna)

        when:
        strands.each {
            d.processStrand(it.get(0) as int, it.get(1) as int, it.get(2) as String)
        }

        then:
        d.minHealth() == min
        d.maxHealth() == max

        where:
        genes                  | health             | strands            | min | max
        "a b c aa d b".split() | [1, 2, 3, 4, 5, 6] | [[1, 5, "caaab"],
                                                       [0, 4, "xyz"],
                                                       [2, 4, "bcdybc"]] | 0   | 19
    }

    @Unroll
    @Timeout(5)
    def "test performance #n / #s"() {
        given:
        def genes = []
        def health = []
        for (int i = 1; i < n; i++) {
            genes.add(nChars(i, c.charAt(0)))
            health.add(i)
        }
        def d = new Solution.DnaHealth([genes as String[], health as long[]] as Solution.Dna)

        when:
        for (int i = s; i >= 1; i--)
            d.processStrand(i / 3 as int, Math.max(i, i * 2 / 3 as int), nChars(i, c.charAt(0)))

        then:
        d.minHealth() == min
        d.maxHealth() == max

        where:
        c   | n     | s   | min | max
        'a' | 512   | 128 | 1   | 259505
        'a' | 1024  | 128 | 1   | 259505
        'a' | 10240 | 128 | 1   | 259505
        'a' | 10240 | 512 | 1   | 259505
    }

    private static String nChars(int n, char c) {
        return (new char[n] as String).replace(0 as char, c)
    }
}
