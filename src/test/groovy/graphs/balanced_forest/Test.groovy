package graphs.balanced_forest

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {

    @Unroll
    def "test #test"() {
        when:
        def result = Result.balancedForest(c, edges)

        then:
        result == expected

        where:
        test | c                        | edges                                     | expected
        1   | [15, 12, 8, 14, 13]       | [[1, 2], [1, 3], [1, 4], [4, 5]]          | 19
        2   | [1, 2, 2, 1, 1]           | [[1, 2], [1, 3], [3, 5], [1, 4]]          | 2
        3   | [1, 3, 5]                 | [[1, 3], [1, 2]]                          | -1
        4   | [12, 10, 8, 12, 14, 12]   | [[1, 2], [1, 3], [1, 4], [2, 5], [4, 6]]  | 4
        5   | [12, 10, 1, 24, 1, 12]    | [[1, 2], [1, 3], [1, 4], [2, 5], [4, 6]]  | 12
        6   | [7, 7, 4, 1, 1, 1]        | [[1, 2], [3, 1], [2, 4], [2, 5], [2, 6]]  | -1
        7   | [1, 1, 1, 18, 10, 11, 5, 6] |
                           [[1, 2], [1, 4], [2, 3], [1, 8], [8, 7], [7, 6], [5, 7]] | 10
        8   | [12, 7, 11, 17, 20, 10]   | [[1, 2], [2, 3], [4, 5], [6, 5], [1, 4]]  | 13
        9   | [2, 3, 3, 4]              | [[1, 2], [1, 4], [2, 3]]                  | 6
    }
}
