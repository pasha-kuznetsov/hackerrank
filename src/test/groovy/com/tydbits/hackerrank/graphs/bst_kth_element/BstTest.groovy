package com.tydbits.hackerrank.graphs.bst_kth_element

import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.StandardCharsets

class BstTest extends Specification {

    @Unroll
    def "test"() {
        given:
        def inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8.name()))

        expect:
        Solution.deleteKthLargest(inputStream) == expectedOutput

        where:
        input                        | expectedOutput
        '''
        5 1
        4 2 7 1 3'''      | '4 2 1 3'
        '''
        6 5
        10 8 12 7 11 9''' | '10 9 12 7 11'
    }
}
