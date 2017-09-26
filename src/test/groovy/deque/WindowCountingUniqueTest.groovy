package deque

import spock.lang.Specification
import spock.lang.Unroll

class WindowCountingUniqueTest extends Specification {
    @Unroll
    def "test #input"() {
        when:
        def window = new WindowCountingUnique(windowSize)
        for (def sc = new Scanner(input); sc.hasNext();)
            window.add(sc.nextInt())

        then:
        window.maxUnique == expectedMaxUnique

        where:
        input                 | windowSize | expectedMaxUnique
        '5 3 5 2 3 2'         | 3          | 3
        '1 1 1 1 1 1 1 1 1 1' | 3          | 1
    }
}
