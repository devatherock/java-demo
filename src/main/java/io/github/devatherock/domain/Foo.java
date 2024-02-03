package io.github.devatherock.domain;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@XmlRootElement(name = "foo")
public class Foo {
	@XmlElement
	private Bar bar;
}