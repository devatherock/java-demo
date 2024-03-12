package io.github.devatherock.app;

import generated.Latency_process;
import io.github.devatherock.util.LatencyProcessorTester;

public class App {

	public static void main(String args[]) {
		LatencyProcessorTester tester = new Latency_process();
		tester.process();
	}
}
