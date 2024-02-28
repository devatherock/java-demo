package io.github.devatherock.util;

import org.threeten.extra.YearWeek;
import com.fasterxml.jackson.annotation.JsonCreator;

public abstract class YearWeekMixIn {
    @JsonCreator
    public static YearWeek parse(CharSequence value) {
        return null;
    }
}