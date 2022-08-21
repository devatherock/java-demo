package io.github.devatherock

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

import io.github.devatherock.entities.AuthResponse
import spock.lang.Specification
import spock.lang.Subject

class AuthClientSpec extends Specification {
    
    @Subject
    AuthClient client = new AuthClient()
    
    def "AuthTokenRequest"() {
        given:
        def url = "https://authEndpoint"
        def map = new LinkedMultiValueMap<>()
        RestTemplate restTemplate = GroovySpy(global: true)
        AuthResponse response = new AuthResponse()
        restTemplate.postForEntity(*_) >> new ResponseEntity(response, HttpStatus.OK)
    
        when:
        def responseEntity = client.authTokenRequest(url, map)
    
        then:
        responseEntity == response
    }
}