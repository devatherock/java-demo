package io.github.devatherock.util

import spock.lang.Specification

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test custom source set'() {
        expect:
        TestUtilSpec.class.classLoader.getResourceAsStream('config/checkstyle.xml').text.contains('SuppressWarningsFilter')
    }
}