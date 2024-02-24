package io.github.devatherock.util;

import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

public class TestUtilTest {
	private static int errorCounter = 0;
	private static int resultIndex = 0;
	private static List<Integer> args = Arrays.asList(14, 15, 100, -10);

	@ParameterizedRepeatedIfExceptionsTest(repeats = 3)
	@ValueSource(ints = {14, 15, 100, -10})
	public void testRepeat(int argument) {
		if ((argument == 14 || argument == -10) && errorCounter++ < 2) {
			throw new RuntimeException("Testing repeat");
		}
		errorCounter = 0;
		Assertions.assertEquals(args.get(resultIndex++), argument);
	}
}
