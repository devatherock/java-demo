package io.github.devatherock.util

import spock.lang.Specification

class ClosureTemplateUtilSpec extends Specification {

    void 'test compose'() {
        expect:
        ClosureTemplateUtil.composeWithSoyTofu() == 'Accounts account request: test'
        ClosureTemplateUtil.composeWithSoySauce() == 'Accounts account request: test'
    }
}
