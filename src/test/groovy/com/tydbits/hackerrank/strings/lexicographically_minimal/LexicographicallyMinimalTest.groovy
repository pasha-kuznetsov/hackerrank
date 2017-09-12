package com.tydbits.hackerrank.strings.lexicographically_minimal

import spock.lang.Specification
import spock.lang.Unroll

class LexicographicallyMinimalTest extends Specification {

    @Unroll
    def "splice #s1 #s2"() {
        expect:
        new LexicographicallyMinimal(s1, s2).splice() == expectedResult

        where:
        s1         | s2         | expectedResult
        "JACK"     | "DANIEL"   | "DAJACKNIEL"
        "ABACABA"  | "ABACABA"  | "AABABACABACABA"
        "ABACABAZ" | "ABACABAZ" | "AABABACABACABAZZ"
        "ABA"      | "ABAA"     | "AABAABA"
        "ABAA"     | "ABA"      | "AABAABA"
        "ABAZ"     | "ABAAZ"    | "AABAABAZZ"
        "ABAAZ"    | "ABAZ"     | "AABAABAZZ"
        "BCBA"     | "BCBBA"    | "BBCBACBBA"
        "BCBBA"    | "BCBA"     | "BBCBACBBA"
    }
}
