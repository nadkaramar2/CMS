package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class EmbossConfigModel {
	private String strID;
	private String strServiceName;
	private String strFormat;
	private String strSelectID;
}
