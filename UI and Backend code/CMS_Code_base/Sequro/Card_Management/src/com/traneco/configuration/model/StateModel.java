package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StateModel {
	private String strID;
	private String strCountryID;
	private String strStateName;
	private String strStatus;
	private String strSelectID;
}
