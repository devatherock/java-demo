package io.github.devatherock.util

import spock.lang.Specification

class TestUtilSpec2 extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }
}