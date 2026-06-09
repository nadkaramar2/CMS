package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ParticipantConfigModel {
	private int id;
	private String participantName;
	private String participantType;
	private String description;
	private String flag;
}
