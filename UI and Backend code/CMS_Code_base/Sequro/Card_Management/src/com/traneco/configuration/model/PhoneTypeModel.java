package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class PhoneTypeModel 
{
	private String strID;
	private String strPhone;
	private String strPart;
	private String strExt;
	private String strSelectID;
}
