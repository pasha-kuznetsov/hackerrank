package strings.aho_corasick

import spock.lang.Specification
import spock.lang.Unroll

class DnaHealthTest extends Specification {

    @Unroll
    def "test #genes"() {
        given:
        def d = new DnaHealth([genes as String[], health as long[]] as Dna)

        when:
        strands.each {
            d.processStrand(it.get(0) as int, it.get(1) as int, it.get(2) as String)
        }

        then:
        d.minHealth() == min
        d.maxHealth() == max

        where:
        genes                  | health             | strands            | min | max
        "a b c aa d b".split() | [1, 2, 3, 4, 5, 6] | [[1, 5, "caaab"],
                                                       [0, 4, "xyz"],
                                                       [2, 4, "bcdybc"]] | 0   | 19
    }

    @Unroll
    def "test performance #n / #s"() {
        given:
        def genes = []
        def health = []
        for (int i = 1; i < n; i++) {
            genes.add(nChars(i, c))
            health.add(i)
        }
        def strands = []
        for (int i = s; i >= 1; i--)
            strands.add(nChars(i, c))
        DnaHealth d
        def initDuration = benchmark {
            d = new DnaHealth([genes as String[], health as long[]] as Dna)
        }

        when:
        def searchDuration = benchmark {
            for (String strand : strands)
                d.processStrand(
                        strand.length() / 3 as int,
                        Math.max(strand.length(),
                                strand.length() * 2 / 3 as int), strand)
        }

        then:
        d.outputs < expectedOutputs
        initDuration < expectedInitDuration
        searchDuration < expectedSearchDuration

        where:
        c   | n     | s    | expectedInitDuration | expectedSearchDuration | expectedOutputs
        'a' | 512   | 128  | 1000                 | 1000                   | 200000
        'a' | 1024  | 128  | 1000                 | 1000                   | 200000
        'a' | 10240 | 128  | 3000 /* ??? */       | 3000                   | 200000
        // 'a' | 10240 | 1024 | 5000                 | 5000                   | 80000000
    }

    @Unroll
    def "test short key performance #n / #s"() {
        given:
        def genes = []
        def health = []
        for (int i = 1; i < n; i++) {
            genes.add(nChars(s, c))
            health.add(i)
        }
        def strands = []
        for (int i = s; i >= 1; i--)
            strands.add(nChars(s * 2, c))
        DnaHealth d
        def initDuration = benchmark {
            d = new DnaHealth([genes as String[], health as long[]] as Dna)
        }

        when:
        def searchDuration = benchmark {
            for (String strand : strands)
                d.processStrand(
                        strand.length() / 3 as int,
                        Math.max(strand.length(),
                                strand.length() * 2 / 3 as int), strand)
        }

        then:
        d.outputs < expectedOutputs
        initDuration < expectedInitDuration
        searchDuration < expectedSearchDuration

        where:
        c    | n     | s | expectedInitDuration | expectedSearchDuration | expectedOutputs
        'ab' | 512   | 3 | 1000                 | 1000                   | 400000
        'ab' | 1024  | 5 | 1000                 | 1000                   | 400000
        'ab' | 10240 | 7 | 3000 /* ??? */       | 3000                   | 400000
        // 'a' | 10240 | 1024 | 5000                 | 5000                   | 80000000
    }

    def benchmark = { closure ->
        def start = System.currentTimeMillis()
        closure.call()
        def now = System.currentTimeMillis()
        return now - start
    }

    private static String nChars(int n, String c) {
        return (new char[n] as String).replace('\0', c)
    }
}
