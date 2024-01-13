package io.github.devatherock.util

import spock.lang.Specification
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.type.TypeReference
import io.github.devatherock.entities.BaseJobField
import io.github.devatherock.entities.JobCurrencyField
import io.github.devatherock.entities.Currency

class TestUtilSpec extends Specification {
    
    void 'test say hello'() {
        expect:
        TestUtil.sayHello() == 'Hello'
    }

    void 'test deserialize'() {
        given:
        ObjectMapper objectMapper = new ObjectMapper();
        List<BaseJobField> jobFields = new ArrayList<>();
        jobFields.add(JobCurrencyField.create("currencyUS", "USD", 10.4));

        and:
        String fields = objectMapper.writeValueAsString(jobFields);

        when:
        List<BaseJobField> ret = objectMapper.readValue(fields, new TypeReference<List<BaseJobField>>(){});
        JobCurrencyField jobCurrencyField = (JobCurrencyField) ret.get(0);
        Currency currency = (Currency)jobCurrencyField.getValue();

        then:
        currency instanceof Currency
        currency.currencyName == 'USD'
        currency.amount == 10.4d
    }
}