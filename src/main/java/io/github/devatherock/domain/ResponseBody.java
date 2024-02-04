package io.github.devatherock.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBody {
	private boolean traceEnabled = false;
	private boolean errorGenEnabled = false;
	private TraceInfo traceInfo = new TraceInfo();
	private ErrorGenInfo errorGenInfo = new ErrorGenInfo();
}
