package graphs.bst_kth_element

import spock.lang.Specification
import spock.lang.Unroll
import utils.InputOutput

class BinarySearchTreeTest extends Specification {

    @Unroll
    def "delete #k from #input"() {
        given:
        def io = new InputOutput(input)
        def tree = Solution.parseTree(new Scanner(io.input), null)

        when:
        tree.delete(k)
        Solution.printTree(io.print, tree)

        then:
        io.output as String == expectedOutput

        where:
        input            | k  | expectedOutput
        '4 2 7 1 3'      | 0  | '4 2 7 1 3 '
        '4 2 7 1 3'      | 99 | '4 2 7 1 3 '
        '4 2 7 1 3'      | 4  | '7 2 1 3 '
        '4 2 7 1 3'      | 2  | '4 3 7 1 '
        '4 2 7 1 3'      | 7  | '4 2 1 3 '
        '4 2 7 1 3'      | 1  | '4 2 7 3 '
        '4 2 7 1 3'      | 3  | '4 2 7 1 '
        '10 8 12 7 11 9' | 0  | '10 8 12 7 9 11 '
        '10 8 12 7 11 9' | 99 | '10 8 12 7 9 11 '
        '10 8 12 7 11 9' | 10 | '11 8 12 7 9 '
        '10 8 12 7 11 9' | 8  | '10 9 12 7 11 '
        '10 8 12 7 11 9' | 12 | '10 8 11 7 9 '
        '10 8 12 7 11 9' | 7  | '10 8 12 9 11 '
        '10 8 12 7 11 9' | 11 | '10 8 12 7 9 '
        '10 8 12 7 11 9' | 9  | '10 8 12 7 11 '
    }

    @Unroll
    def "delete k-th #expectedOutput"() {
        given:
        def io = new InputOutput(input)

        when:
        Solution.deleteKthLargest(io.input, io.print)

        then:
        io.output as String == expectedOutput

        where:
        input                        | expectedOutput
        '''
        5 1
        4 2 7 1 3'''      | '4 2 1 3 '
        '''
        6 5
        10 8 12 7 11 9''' | '10 9 12 7 11 '
    }
}
