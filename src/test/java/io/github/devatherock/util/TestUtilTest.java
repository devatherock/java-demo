package io.github.devatherock.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUtilTest {
	
	@Test
	public void testSayHello() {
		Assertions.assertEquals("Hello", TestUtil.sayHello());
	}
}
