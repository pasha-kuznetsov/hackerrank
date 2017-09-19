package com.tydbits.hackerrank.strings.regex_count

import spock.lang.Specification
import spock.lang.Unroll

class RegexTest extends Specification {

    @Unroll
    def "test #regex"() {
        expect:
        dump(new Regex(regex).root) == expectedResult

        where:
        regex           | expectedResult

        "((ab)|(ba))"   | '''
1
  a -> 2
      b -> 3
  b -> 4
      a -> 5'''.trim()

        "(a*)"      | '''
1
  a -> 2
      a -> 2'''.trim()


        "((a*)*)"      | '''
1
  a -> 2
      a -> 2'''.trim()


        "(((a*)*)*)"      | '''
1
  a -> 2
      a -> 2'''.trim()


        "((a|b)*)"      | '''
1
  a -> 2
      a -> 2
      b -> 3
          a -> 2
          b -> 3
  b -> 3'''.trim()


        "((a*)(b(a*)))" | '''
1
  a -> 2
      a -> 2
      b -> 3
          a -> 4
              a -> 4
  b -> 3'''.trim()
    }

    String dump(Regex.Node root) {
        return dumpNode(new HashMap<>(), root, 0).trim()
    }

    String dumpNode(map, Regex.Node node, int level) {
        Integer id = map.get(node)
        if (id != null)
            return new StringBuilder().append(id).append('\n')
        def out = new StringBuilder()
        map.put(node, id = map.size() + 1)
        out.append(id).append('\n')
        for (Regex.Edge edge : node.edges) {
            out.append(dumpEdge(map, edge, level + 2))
        }
        return out.toString()
    }

    String dumpEdge(map, Regex.Edge edge, int level) {
        StringBuilder out = new StringBuilder()
        appendIndent(out, level)
        out.append(dumpValue(edge)).append(" -> ").append(dumpNode(map, edge.node, level + 2))
        return out.toString()
    }

    String dumpValue(Regex.Edge edge) {
        return new String(edge.value).replace('\0', '')
    }

    void appendIndent(StringBuilder out, int level) {
        out.append(new String(new char[level]).replace('\0', ' '))
    }
}
