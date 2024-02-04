package io.github.devatherock.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraceInfo {
	private String filter = "filter-12345";
	private int status = 200;
	private String method = "PUT";
}
