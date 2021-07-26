package lists.cycle

import spock.lang.Specification
import spock.lang.Unroll

class Test extends Specification {

    @Unroll
    def "test #input"() {
        given:
        def list = createList(input)

        when:
        def result = Solution.hasCycle(list)

        then:
        result == expected

        where:
        input           | expected
        []              | false
        [1]             | false
        [1, 2, 3]       | false
        [1, 2, 3, 1]    | true
    }

    static SinglyLinkedListNode createList(List<Integer> input) {
        SinglyLinkedListNode head = null, tail = null
        Map<Integer, SinglyLinkedListNode> nodes = new HashMap<>()
        for (int data : input) {
            def node = nodes.computeIfAbsent(data, d -> new SinglyLinkedListNode(d))
            if (head == null) {
                head = node
            }
            if (tail != null) {
                tail.next = node
            }
            tail = node
        }
        return head
    }
}
