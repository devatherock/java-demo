package io.github.devatherock.util;

import io.github.devatherock.annotation.Latency;

public interface LatencyProcessorTester {

	@Latency
	void process();
}
