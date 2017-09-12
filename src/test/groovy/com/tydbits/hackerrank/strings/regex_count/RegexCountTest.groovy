package com.tydbits.hackerrank.strings.regex_count

import spock.lang.Specification
import spock.lang.Unroll

class RegexCountTest extends Specification {

    @Unroll
    def "test #regex #len"() {
        expect:
        Solution.count(regex, len) == expectedResult

        where:
        regex           | len | expectedResult
        "((ab)|(ba))"   | 2   | 2
        "((a|b)*)"      | 5   | 32
        "((a*)(b(a*)))" | 100 | 100
    }
}
