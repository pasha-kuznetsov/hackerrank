package com.tydbits.hackerrank.graphs.cut_the_tree

import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.StandardCharsets

class TreeTest extends Specification {

    @Unroll
    def "test #expectedResult"() {
        given:
        def inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8.name()))

        expect:
        Solution.main(inputStream) == expectedResult

        where:
        input | expectedResult
        """6
        100 200 100 500 100 600
        1 2
        2 3
        2 5
        4 5
        5 6
        """ | 400
    }
}
