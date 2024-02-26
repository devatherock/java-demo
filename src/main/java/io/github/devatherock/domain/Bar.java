package io.github.devatherock.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.threeten.extra.YearWeek;

import java.util.Map;

@Getter
@Setter
public class Bar {
    private YearWeek yearWeek;

    @JsonCreator
    public static Bar creator(Map<String, String> json) {
        Bar bar = new Bar();
        bar.yearWeek = YearWeek.parse(json.get("yearWeek"));

        return bar;
    }
}
