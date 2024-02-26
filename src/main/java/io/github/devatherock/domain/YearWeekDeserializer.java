package io.github.devatherock.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.threeten.extra.YearWeek;

import java.io.IOException;

public class YearWeekDeserializer extends StdDeserializer<YearWeek> {

    public YearWeekDeserializer() {
        this(null);
    }

    public YearWeekDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public YearWeek deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        final String s = jp.readValueAs(String.class);
        return YearWeek.parse(s);
    }
}