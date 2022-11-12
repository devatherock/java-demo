package io.github.devatherock.util

import spock.lang.Specification
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }
    
    void 'test deserialize'() {
    	given:
    	String json = '''
		{
		  "@id": "bba35e58-5d4b-44ce-9a5a-486f55f79af7",
		  "processId": "21ef7f9d-4fcc-417c-96e8-4327206d2592",
		  "users": [
		    {
		      "@id": "69d2f392-8213-4f34-9cb5-f0c403170787",
		      "userId": "5a17ec5f-c20a-4873-93af-bf69fad4eb26",
		      "roles": [
		        {
		          "roleId": "f6ad33a7-9d03-4260-81c2-a4a4c791e30a",
		          "users": []
		        }
		      ],
		      "processes": []
		    }
		  ],
		  "units": [
		    {
		      "unitTypeId": "c784d197-1dc7-446e-b3e5-6468a7954878",
		      "unit": {
		        "unitId": "aba76d05-e2ea-4b5a-828b-349966595258"
		      },
		      "isResponsibleUnit": true
		    }
		  ],
		  "furtherComment": "",
		  "answeredQuestionnaires": [
		    {
		      "@id": "7ca1af09-eefd-4c56-9587-581858fbbc57"
		    }
		  ]
		}
    	'''.stripIndent().trim()
    	
    	and:
    	ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.registerModule(new JavaTimeModule())
        
        // when:
        ProcessDTORequest request = objectMapper.readValue(json, ProcessDTORequest)
        
        // then:
        request
    }
}