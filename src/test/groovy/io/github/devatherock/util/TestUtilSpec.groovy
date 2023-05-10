package io.github.devatherock.util

import spock.lang.Specification

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test reverse word order'() {
        expect:
        TestUtil.reverseWordOrder(input) == output

        where:
        input << [
                'the quick brown fox jumps over the lazy dog',
                'the quick brown fox jumps over the lazy dog ',
                ' the quick brown fox jumps over the lazy dog',
                'the quick brown   fox jumps over the lazy dog',
                'hello'
        ]
        output << [
                'dog lazy the over jumps fox brown quick the',
                ' dog lazy the over jumps fox brown quick the',
                'dog lazy the over jumps fox brown quick the ',
                'dog lazy the over jumps fox   brown quick the',
               'hello'
        ]
    }
}