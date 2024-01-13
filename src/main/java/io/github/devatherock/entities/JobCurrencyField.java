package io.github.devatherock.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonTypeName("CURRENCY")
public class JobCurrencyField extends BaseJobField<Currency> {

    public static JobCurrencyField create(String name, String currencyName, Double amount) {
        Currency currency = new Currency();
        currency.setCurrencyName(currencyName);
        currency.setAmount(amount);
        JobCurrencyField jobCurrencyField = new JobCurrencyField();
        jobCurrencyField.setName(name);
        jobCurrencyField.setValue(currency);
        jobCurrencyField.setFieldType("CURRENCY");
        return jobCurrencyField;
    }
    
}
