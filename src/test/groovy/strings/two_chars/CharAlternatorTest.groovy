package strings.two_chars

import spock.lang.Specification
import spock.lang.Unroll

class CharAlternatorTest extends Specification {

    @Unroll
    def "test #str"() {
        expect:
        new CharAlternator(str).max() == expectedResult

        where:
        str          | expectedResult
        "yaxyaxy"    | 5
        "yaxyaxyxa"  | 6
        "abaacdabd"  | 4
        "beabeefeab" | 5
        "abbaa"      | 0
        "abaab"      | 0
        "ababb"      | 0
        "ababaa"     | 0
    }

    def "test 7-1"() {
        expect:
        new CharAlternator(str).max() == expectedResult

        where:
        str      | expectedResult

        "fkffkk" | 0
        // 0 2 3
        //  1 4 5

        "ssuu"   | 0
        // 0 1
        //  2 3
    }

    def "test 7-2"() {
        expect:
        new CharAlternator(str).max() == expectedResult

        where:
        str                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
                expectedResult
        "ezfnjymgqtjnmstbadgdsrxvntnacwljnkgchtjeaoivfcindgxipmrjuqmmcpntpotplodjhijxqpogjmzipygacfdjgmewechuebxvcbnakszzcxkozxwavzgmesrvysonomhvufezislfntgncspthcpneyminpbjildobozfirvcgdratdpmmpkujcywvtzkdytzyfejbytsobvudvutfueveevgrqnxjiwpkrvllsjxmqhotlnpgjxkjnobxfqodlyiqsisdeuwqmntxouzdtisgutdafostmwticvncjwldpknuodmfksusaqpsoosgpiveyxipfklmhypdxpdncpgaswpycoxsuxasqduojpblctcyvyxldcgzevedvxiwinfppkjbtifuuapickknwxxjmjmtxlpfalxdgepmekaxijuphqfafrnezyldokwcnzenhpibktlfuxjfmeqajmvopbhuslnnnlmkmoteceiwbytjhhxqnkuazevswrkaofggfrnapciuoexqogscugzspwuvzkyrdfkhixcaqctfwadewpqksxxvqiigvjjpagvqikuojlwhfyztu" |
                0
    }
}
