package io.github.devatherock.util

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock

import groovy.json.JsonOutput

import spock.lang.Shared
import spock.lang.Specification

class TestUtilSpec extends Specification {

    @Shared
    WireMockServer mockServer = new WireMockServer(8081)

    void setupSpec() {
        WireMock.configureFor(8081)
        mockServer.start()
    }

    void cleanupSpec() {
        mockServer.stop()
    }

    void cleanup() {
        mockServer.resetAll()
    }
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test make http request'() {
        given:
        def responseBody = ['line1', 'line2', 'line3']
        WireMock.givenThat(WireMock.post('/validation/1234')
                .willReturn(WireMock.aResponse()
                        .withStatus(422)
                        .withBody(JsonOutput.toJson(responseBody))))

        expect:
        TestUtil.makeHttpRequest() == responseBody
    }
}