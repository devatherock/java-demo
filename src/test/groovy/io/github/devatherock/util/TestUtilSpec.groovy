package io.github.devatherock.util

import io.github.devatherock.domain.ResponseBody
import org.yaml.snakeyaml.Yaml
import spock.lang.Specification

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test create yaml'() {
        given:
        String expectedOutput = '''
        trace_enabled: false
        error_gen_enabled: false
        trace_info:
          filter: filter-12345
          status: 200
          method: PUT
        error_gen_info:
          code: 500
          rate: 0.35
          content_type: application/problem+json
          content: '{"status":503,"title":"Internal Server Error","detail":"Too busy","cause":""}'
        '''.stripIndent().trim()

        when:
        String output = TestUtil.toYaml(new ResponseBody())

        then:
        Yaml yaml = new Yaml()
        yaml.load(output) == yaml.load(expectedOutput)
    }

    void 'test to yaml nested keys'() {
        given:
        String expectedOutput = '''
        a:
          b: c
          d: e
          f:
            g: h
        i.: j
        k: l
        m:
          n: o
        p:
          q:
            r: s
        '''

        and:
        def input = [
                'a.b': 'c',
                'a.d': 'e',
                'a.f.g': 'h',
                'i.': 'j',
                'k': 'l',
                'm': ['n': 'o'],
                'p.q': ['r': 's'],
        ]

        when:
        String output = TestUtil.toYamlNestedKeys(input)

        then:
        Yaml yaml = new Yaml()
        yaml.load(output) == yaml.load(expectedOutput)
    }

    void 'test to yaml nested maps'() {
        given:
        String expectedOutput = '''
        a:
          b: c
          d: e
        '''

        when:
        String output = TestUtil.toYamlNestedMaps()

        then:
        Yaml yaml = new Yaml()
        yaml.load(output) == yaml.load(expectedOutput)
    }

    void 'test to yaml exclusions'() {
        given:
        String expectedOutput = '''
        id: x12345
        firstName: Test
        lastName: User
        '''

        when:
        String output = TestUtil.toYaml()

        then:
        Yaml yaml = new Yaml()
        yaml.load(output) == yaml.load(expectedOutput)
    }
}