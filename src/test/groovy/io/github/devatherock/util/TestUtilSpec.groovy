package io.github.devatherock.util

import org.springframework.oxm.jaxb.Jaxb2Marshaller
import spock.lang.Specification

import javax.xml.transform.stream.StreamSource

class TestUtilSpec extends Specification {

    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test jaxb unmarshaller'() {
        given:
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller()
        jaxb2Marshaller.setClassesToBeBound(Generic)

        and:
        String soapResponse =
        '''
        <ns2:GenericRequest xmlns:ns2="http://www.oracle.com/UCM">
            <ns2:Service>testService</ns2:Service>
        </ns2:GenericRequest>
        '''.stripIndent()

        when:
        def object = jaxb2Marshaller.createUnmarshaller()
                .unmarshal(new StreamSource(new StringReader(soapResponse)), Generic)
        def response = object.value

        then:
        response instanceof Generic
        response.service == 'testService'
    }
}