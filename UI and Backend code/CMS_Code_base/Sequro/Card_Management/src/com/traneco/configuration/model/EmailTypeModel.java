package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class EmailTypeModel {
	private String strID;
	private String strEmail;
	private String strPart;
	private String strExt;
	private String strSelectID;
}
