package io.github.devatherock.util;

import org.threeten.extra.YearWeek;
import com.fasterxml.jackson.annotation.JsonCreator;

public class YearWeekMixIn {
    @JsonCreator
    public static YearWeek parse(String value) {
        return YearWeek.parse(value);
    }
}