package dp.jumping_on_clouds

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {
    @Unroll
    def "test #input"() {
        when:
        int result = Result.jumpingOnClouds(input as int[])

        then:
        result == expected

        where:
        input                       | expected
        [0, 0, 1, 0, 0, 1, 0]       | 4
        [0, 0, 0, 0, 1, 0]          | 3
    }
}
