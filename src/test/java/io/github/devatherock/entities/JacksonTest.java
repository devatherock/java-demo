package io.github.devatherock.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JacksonTest {

    @Test
    public void testDeserialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BaseJobField<?>> jobFields = new ArrayList<>();
        jobFields.add(JobCurrencyField.create("currencyUS", "USD", 10.4));
        String fields = objectMapper.writeValueAsString(jobFields);

        List<BaseJobField<?>> ret = objectMapper.readValue(fields, new TypeReference<List<BaseJobField<?>>>(){});
        JobCurrencyField jobCurrencyField = (JobCurrencyField) ret.get(0);
        Currency currency = (Currency)jobCurrencyField.getValue();

        Assertions.assertTrue(currency instanceof Currency);
        Assertions.assertEquals("USD", currency.getCurrencyName());
        Assertions.assertEquals(10.4d, currency.getAmount());
    }
}
