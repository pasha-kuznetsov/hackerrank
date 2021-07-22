package strings.repeated_string

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {
    @Unroll
    def "test #s #n"() {
        when:
        def result = Result.repeatedString(s, n)

        then:
        result == expected

        where:
        s           | n             | expected
        'abcac'     |            10 | 4
        'aba'       |            10 | 7
        'a'         | 1000000000000 | 1000000000000
    }
}
