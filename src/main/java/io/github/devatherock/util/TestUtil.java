package io.github.devatherock.util;

import io.github.devatherock.domain.Employee;
import io.github.devatherock.domain.ErrorGenInfo;
import io.github.devatherock.domain.ResponseBody;
import io.github.devatherock.domain.TraceInfo;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TestUtil {

	public static String sayHello() {
		return "Hello";
	}

	public static String toYaml() {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
		Representer representer = new Representer(dumperOptions);

		TypeDescription typeDescription = new TypeDescription(Employee.class);
		typeDescription.setExcludes("name", "dateOfBirth"); // fields to exclude
		representer.addTypeDescription(typeDescription);

		// To disable the tag with class name
		representer.addClassTag(Employee.class, Tag.MAP);

		Yaml yaml = new Yaml(representer);
		String result = yaml.dump(new Employee());

		LOGGER.info("Result: \n{}", result);
		return result;
	}

	public static String toYaml(ResponseBody yamlObject) {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
		dumperOptions.setSplitLines(false);

		Representer representer = new Representer(dumperOptions);

		// To rename fields
		addTypeDescription(Collections.singletonList("content_type"), Collections.singletonList("getContentType"),
				Collections.singletonList("setContentType"), new String[]{"contentType"},
				Collections.singletonList(String.class), ErrorGenInfo.class, representer);
		addTypeDescription(Arrays.asList("trace_enabled", "error_gen_enabled", "trace_info", "error_gen_info"),
				Arrays.asList("isTraceEnabled", "isErrorGenEnabled", "getTraceInfo", "getErrorGenInfo"),
				Arrays.asList("setTraceEnabled", "setErrorGenEnabled", "setTraceInfo", "setErrorGenInfo"),
				new String[]{"traceEnabled", "errorGenEnabled", "traceInfo", "errorGenInfo"},
				Arrays.asList(Boolean.class, Boolean.class, TraceInfo.class, ErrorGenInfo.class), ResponseBody.class,
				representer);

		// To disable the tags with class name
		representer.addClassTag(ResponseBody.class, Tag.MAP);
		representer.addClassTag(ErrorGenInfo.class, Tag.MAP);

		Yaml yaml = new Yaml(representer);
		String result = yaml.dump(yamlObject);

		LOGGER.info("Result: \n{}", result);
		return result;
	}

	private static void addTypeDescription(List<String> propertyNames, List<String> getterNames,
			List<String> setterNames, String[] propertiesToExclude, List<Class<?>> propertyClasses, Class<?> beanClass,
			Representer representer) {
		TypeDescription typeDescription = new TypeDescription(beanClass);

		for (int index = 0; index < propertyNames.size(); index++) {
			typeDescription.substituteProperty(propertyNames.get(index), propertyClasses.get(index),
					getterNames.get(index), setterNames.get(index));
		}
		typeDescription.setExcludes(propertiesToExclude);

		representer.addTypeDescription(typeDescription);
	}

	public static String toYamlNestedMaps() {
		Map<String, Object> input = new HashMap<>();
		Map<String, Object> innerMap = new HashMap<>();
		innerMap.put("b", "c");
		innerMap.put("d", "e");
		input.put("a", innerMap);

		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
		Yaml yaml = new Yaml(dumperOptions);
		String result = yaml.dump(input);

		LOGGER.info("Result: \n{}", result);
		return result;
	}

	public static String toYamlNestedKeys(Map<String, Object> input) {
		prepareInput(input);

		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
		Yaml yaml = new Yaml(dumperOptions);
		String result = yaml.dump(input);

		LOGGER.info("Result: \n{}", result);
		return result;
	}

	private static void prepareInput(Map<String, Object> input) {
		List<String> keysToRemove = new ArrayList<>();
		Map<String, Object> output = new HashMap<>();

		input.forEach((key, value) -> {
			if (key.contains(".")) {
				keysToRemove.add(key);
				output.putAll(mergeValues(output, splitKey(key, value)));
			}
		});

		// Remove entries that have been converted into nested maps
		keysToRemove.forEach(key -> {
			input.remove(key);
		});

		// Add the nested maps
		output.forEach((key, value) -> {
			input.put(key, value);
		});
	}

	private static Map<String, Object> mergeValues(Map<String, Object> completeOutput, Map<String, Object> tempOutput) {
		Map.Entry<String, Object> entry = tempOutput.entrySet().stream().findFirst().get();
		Map<String, Object> output = new HashMap<>();
		output.putAll(completeOutput);

		if (completeOutput.containsKey(entry.getKey())) {
			output.put(entry.getKey(), mergeValues((Map<String, Object>) completeOutput.get(entry.getKey()),
					(Map<String, Object>) entry.getValue()));
		} else {
			output.put(entry.getKey(), entry.getValue());
		}

		return output;
	}

	private static Map<String, Object> splitKey(String key, Object value) {
		Map<String, Object> output = new HashMap<>();

		int dotIndex = key.lastIndexOf('.');
		if (dotIndex == -1 || dotIndex == key.length() - 1) {
			output.put(key, value);
		} else {
			String innerKey = key.substring(dotIndex + 1, key.length());
			Map<String, Object> innerObject = new HashMap<>();
			innerObject.put(innerKey, value);

			String outerKey = key.substring(0, dotIndex);
			output.putAll(splitKey(outerKey, innerObject));
		}

		return output;
	}
}
