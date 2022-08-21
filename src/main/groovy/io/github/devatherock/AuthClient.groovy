package io.github.devatherock

import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

import io.github.devatherock.entities.AuthResponse

class AuthClient {

    public AuthResponse authTokenRequest(String url, MultiValueMap map) {
        RestTemplate restTemplate = new RestTemplate()
        ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(url, createAuthEntity(map), AuthResponse.class)
        return responseEntity.getBody()?:null
    }
    
    private def createAuthEntity(MultiValueMap map) {
        return map
    }
}