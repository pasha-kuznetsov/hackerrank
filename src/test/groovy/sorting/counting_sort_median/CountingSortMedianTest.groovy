package sorting.counting_sort_median

import spock.lang.Specification
import spock.lang.Unroll

class CountingSortMedianTest extends Specification {
    @Unroll
    def "test #expenditures / #days"() {
        given:
        def m = new CountingSortMedian()

        when:
        int i = 0;
        for (; i < days; ++i)
            m.add(expenditures[i]);

        def medians = []
        for (; i < expenditures.size(); ++i) {
            medians.add(m.median())
            m.add(expenditures[i])
            m.remove(expenditures[i - days])
        }

        then:
        medians == expectedMedians

        where:
        expenditures                | days | expectedMedians
        [2, 3, 4, 2, 3, 6, 8, 4, 5] | 1    | [2.0, 3.0, 4.0, 2.0, 3.0, 6.0, 8.0, 4]
        [2, 3, 4, 2, 3, 6, 8, 4, 5] | 2    | [2.5, 3.5, 3.0, 2.5, 4.5, 7.0, 6.0]
        [2, 3, 4, 2, 3, 6, 8, 4, 5] | 4    | [2.5, 3.0, 3.5, 4.5, 5.0]
        [2, 3, 4, 2, 3, 6, 8, 4, 5] | 5    | [3.0, 3.0, 4.0, 4.0]
    }
}
