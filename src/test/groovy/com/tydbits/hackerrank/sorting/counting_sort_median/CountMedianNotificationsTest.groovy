package com.tydbits.hackerrank.sorting.counting_sort_median

import spock.lang.Specification
import spock.lang.Unroll

import java.nio.charset.StandardCharsets

class CountMedianNotificationsTest extends Specification {
    @Unroll
    def "test #expenditures / #days"() {
        given:
        def input = new Scanner(new ByteArrayInputStream(expenditures.getBytes(StandardCharsets.UTF_8.name())))

        when:
        def notifications = Solution.countNotifications(input, n, days)

        then:
        notifications == expectedNotifications

        where:
        expenditures        | n | days | expectedNotifications
        "1 2 3 4 4"         | 5 | 4    | 0
        "2 3 4 2 3 6 8 4 5" | 9 | 5    | 2
        "2 3 4 2 3 6 8 4 5" | 9 | 4    | 2
        "2 3 4 2 3 6 8 4 5" | 9 | 2    | 1
        "2 3 4 2 3 6 8 4 5" | 9 | 1    | 1
    }
}
