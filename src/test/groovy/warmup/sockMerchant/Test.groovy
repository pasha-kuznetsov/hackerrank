package warmup.sockMerchant

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {
    @Unroll
    def "test #input"() {
        given:
        def ar = input as int[]

        when:
        int result = Result.sockMerchant(ar.length, ar)

        then:
        result == expected

        where:
        input                                   | expected
        [1, 2, 1, 2, 1, 3, 2]                   | 2
        [10, 20, 20, 10, 10, 30, 50, 10, 20]    | 3
    }
}
