package io.github.devatherock.util

import spock.lang.Specification

class TestUtilSpec extends Specification {

    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test get velocity template'() {
        expect:
        TestUtil.getVelocityTemplate('hello_world') == 'Hello $w'
    }

    void 'test expand velocity template'() {
        expect:
        TestUtil.expandVelocityTemplate('hello_world', ['w': 'World']) == 'Hello World'
    }
}