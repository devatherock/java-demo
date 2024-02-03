package io.github.devatherock.domain;

import lombok.Setter;

import javax.xml.bind.annotation.XmlMixed;
import java.io.Serializable;
import java.util.List;

@Setter
public class Bar {
	@XmlMixed
	private List<Serializable> content;
}
