package io.github.devatherock.util

import spock.lang.Specification

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test list field access'() {
        when:
        TestUtil.listFieldAccess()

        then:
        noExceptionThrown()
    }
}