package io.github.devatherock.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, property = "fieldType", include=JsonTypeInfo.As.EXISTING_PROPERTY, visible=true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = JobCurrencyField.class, name = "CURRENCY")
})
public abstract class BaseJobField<T> {
    private String name;
    private T value;
    private String fieldType;
}
