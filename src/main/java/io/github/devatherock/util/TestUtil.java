package io.github.devatherock.util;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class TestUtil {

	public static String sayHello() {
		return "Hello";
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

	public static void addTypeDescription(List<String> propertyNames, List<String> getterNames,
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
}
