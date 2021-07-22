package trees.lowestCommonAncestor

import spock.lang.Specification
import spock.lang.Unroll
import utils.InputOutput

class Test extends Specification {

    @Unroll
    def "lca #input"() {
        given:
        def io = new InputOutput(input)
        def root = Solution.parseTree(new Scanner(io.input), t)

        when:
        def result = Solution.lca(root, v1, v2)

        then:
        result.data == expectedOutput

        where:
        t | input            | v1 | v2 | expectedOutput
        6 | '2 1 4 3 5 6'    | 4  | 6  | 4
        6 | '4 2 3 1 7 6'    | 1  | 7  | 4
    }
}
