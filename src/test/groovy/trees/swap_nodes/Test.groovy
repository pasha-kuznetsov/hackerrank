package trees.swap_nodes

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {

    @Unroll
    def "test #test"() {
        when:
        def result = Result.swapNodes(indexes, queries)

        then:
        result == expected

        where:
        test | indexes          | queries      | expected
        1   | [[2, 3]]          | [1, 1]       | [[3, 1, 2], [2, 1, 3]]
        2   | [[2, 3], [4], [5], [6], [7, 8], [-1, 9], [], [10, 11]]
                                | [2, 4]       | [[2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10],
                                                  [2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11]]
    }
}
