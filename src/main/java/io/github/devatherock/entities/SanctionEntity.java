package io.github.devatherock.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanctionEntity {
	private String designationDetails;
	private String unitedNationId;
	private String euReferenceNumber;
	private String logicalId;
	private String designationDate;
	private List<String> remark;
}
