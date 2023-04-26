package io.github.devatherock.util;

import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

public class Example {
	
	public static void main(String[] args) throws Exception {
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, null, null);
		System.out.println("Provider: " + context.getProvider());
		SSLEngine engine = context.createSSLEngine();
		System.out.println(Arrays.toString(engine.getSupportedCipherSuites()));
	}
}