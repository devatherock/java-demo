package io.github.devatherock.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.github.devatherock.entities.Export;

public class TestUtil {

	public static String sayHello() {
		return "Hello";
	}

	public static Export readXML(String xml) throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		JsonNode node = xmlMapper.readTree(xml.getBytes());

		return jsonMapper.convertValue(node, Export.class);
	}
}
