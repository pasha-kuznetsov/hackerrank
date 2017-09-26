package graphs.components

import spock.lang.Specification
import spock.lang.Unroll

class GraphComponentsTest extends Specification {
    @Unroll
    def "test #edges"() {
        when:
        def g = new GraphComponents(nodes)
        edges.forEach { edge -> g.addEdge(edge[0], edge[1]) }

        then:
        g.components().sort() == expectedComponents
        g.pairings() == expectedPairings

        where:
        nodes || edges    | expectedComponents | expectedPairings
        5     || [[0, 1],
                  [2, 3],
                  [0, 4]] | [2, 3]             | 6
        5     || [[0, 1],
                  [2, 3]] | [1, 2, 2]          | 8
        4     || [[0, 2]] | [1, 1, 2]          | 5
    }
}
