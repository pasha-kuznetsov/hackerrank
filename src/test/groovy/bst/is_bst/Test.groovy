package bst.is_bst

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {

    @Unroll
    def "test #test"() {
        when:
        def result = Result.checkBST(tree)

        then:
        result == expected

        where:
        test | tree | expected
        0 | new Node(1) | true
        1 | new Node(5, new Node(1), new Node(7)) | true
        2 | new Node(2, null, new Node(6)) | true
        3 | new Node(3, new Node(2), new Node(5)) | true
        4 | new Node(3,
                new Node(2, null, new Node(6)),
                new Node(5, new Node(1), new Node(7))) | false
        5 | new Node(4,
                    new Node(2, new Node(1), new Node(3)),
                    new Node(6, new Node(5), new Node(7))) | true
    }
}
