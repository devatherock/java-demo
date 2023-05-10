package io.github.devatherock.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Export {
	private String generationDate;
	private String globalFileId;
	private List<SanctionEntity> sanctionEntity;
}