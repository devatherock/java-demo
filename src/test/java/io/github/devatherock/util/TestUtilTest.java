package io.github.devatherock.util;

import io.github.devatherock.domain.Bar;
import io.github.devatherock.domain.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TestUtilTest {

	@Test
	public void testSerialize() throws JAXBException {
		// given:
		List<Serializable> content = new ArrayList<>();
		content.add(new JAXBElement(new QName("a"), String.class, "value-a"));
		content.add("Some text");
		content.add(new JAXBElement(new QName("b"), String.class, "value-b"));
		Bar bar = new Bar();
		bar.setContent(content);
		Foo foo = new Foo();
		foo.setBar(bar);

		// when:
		StringWriter writer = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(Foo.class, Bar.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		marshaller.marshal(foo, writer);
		String xmlString = writer.toString();

		// then:
		Assertions.assertEquals("<foo><bar><a>value-a</a>Some text<b>value-b</b></bar></foo>", xmlString);
	}
}
