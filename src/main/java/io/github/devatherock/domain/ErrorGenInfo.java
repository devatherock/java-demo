package io.github.devatherock.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ErrorGenInfo {
	private int code = 500;
	private BigDecimal rate = new BigDecimal("0.35");
	private String contentType = "application/problem+json";
	private String content = """
			{"status":503,"title":"Internal Server Error","detail":"Too busy","cause":""}
			""".trim();
}
