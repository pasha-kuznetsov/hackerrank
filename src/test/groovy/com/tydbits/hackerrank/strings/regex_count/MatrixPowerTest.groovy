package com.tydbits.hackerrank.strings.regex_count

import spock.lang.Specification
import spock.lang.Unroll

class MatrixPowerTest extends Specification {

    @Unroll
    def "test #m ^ #p"() {
        expect:
        new MatrixPower(m as long[][]).power(p) == expectedResult as long[][]

        where:
        m        | p | expectedResult

        [[1, 2],
         [3, 4]] | 1 | [[1, 2],
                        [3, 4]]

        [[1, 2],
         [3, 4]] | 2 | [[7, 10],
                        [15, 22]]

        [[1, 2],
         [3, 4]] | 3 | [[37, 54],
                        [81, 118]]

        [[1, 2],
         [3, 4]] | 4 | [[199, 290],
                        [435, 634]]

        [[1, 2],
         [3, 4]] | 5 | [[1069, 1558],
                        [2337, 3406]]

        [[1, 2],
         [3, 4]] | 7 | [[30853, 44966],
                        [67449, 98302]]
    }
}
