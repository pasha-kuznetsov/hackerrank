package dp.maxsubarray

import spock.lang.Specification
import spock.lang.Unroll

class MaxSubArrayTest extends Specification {
    @Unroll
    def "test #array"() {
        given:
        def maxSubArray = new MaxSubArray(array as int[])

        expect:
        [maxSubArray.contiguousMax, maxSubArray.nonContiguousMax] == [expectedContiguous, expectedNonContiguous]

        where:
        array                | expectedContiguous | expectedNonContiguous
        [1, 2, 3, 4]         | 10                 | 10
        [2, -1, 2, 3, 4, -5] | 10                 | 11
    }
}
