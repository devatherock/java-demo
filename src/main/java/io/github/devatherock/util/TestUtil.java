package io.github.devatherock.util;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

public class TestUtil {

    public static String sayHello() {
        return "Hello";
    }
    
    public static List<String> makeHttpRequest() {
    	WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081")
    			.build();
    	String reference = "1234";
    	
    	ObjectMapper objectMapper = new ObjectMapper(); 
    	List<String> response = webClient
    	  .post()
    	  .uri("/validation/{reference}", reference)
    	  .retrieve()
    	  .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
    	  .onErrorResume(WebClientResponseException.class, ex -> {
				try {
					return ex.getRawStatusCode() == 422 ? 
							Mono.just(objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<List<String>>() {})) : 
								Mono.error(ex);
				} catch (JsonProcessingException jsonException) {
					return Mono.error(jsonException);
				}
			})
    	  .block();
    	
    	return response;
    }
}
