package warmup.counting_valleys

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {
    @Unroll
    def "test #input"() {
        when:
        int result = Result.countingValleys(input.length(), input)

        then:
        result == expected

        where:
        input                        | expected
        'DDUUUUDD'                   | 1
        'UDDDUDUU'                   | 1
    }
}
