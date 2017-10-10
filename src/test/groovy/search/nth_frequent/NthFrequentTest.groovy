package search.nth_frequent

import spock.lang.Specification
import spock.lang.Unroll

class NthFrequentTest extends Specification {
    @Unroll
    def "#input"() {
        expect:
        Solution.nth_frequent(new Scanner(input)) == expectedOutput

        where:
        input               | expectedOutput

        '5 2 4 \
         1 4 4 2 3' | 1

        '5 3 4 \
         1 4 4 2 3' | 0

        '5 4 4 \
         1 4 4 2 3' | -1
    }
}
