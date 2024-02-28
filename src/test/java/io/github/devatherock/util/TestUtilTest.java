package io.github.devatherock.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.devatherock.domain.Bar;
import io.github.devatherock.domain.Foo;
import io.github.devatherock.domain.YearWeekDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.YearWeek;

public class TestUtilTest {

	@Test
	public void testSayHello() {
		Assertions.assertEquals("Hello", TestUtil.sayHello());
	}

	@Test
	public void testJacksonMixin() throws JsonProcessingException {
		String json = """
 			{"yearWeek": "2011-W01"}
		""".trim();

		ObjectMapper objectMapper = new ObjectMapper();
		Bar bar = objectMapper.readValue(json, Bar.class);
		Assertions.assertEquals(2011, bar.getYearWeek().getYear());

		objectMapper = new ObjectMapper();
		objectMapper.addMixIn(YearWeek.class, YearWeekMixIn.class);
		Foo foo1 = objectMapper.readValue(json, Foo.class);
		Assertions.assertEquals(2011, foo1.yearWeek().getYear());

		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(YearWeek.class, new YearWeekDeserializer());
		objectMapper.registerModule(module);
		Foo foo2 = objectMapper.readValue(json, Foo.class);
		Assertions.assertEquals(2011, foo2.yearWeek().getYear());
	}
}
