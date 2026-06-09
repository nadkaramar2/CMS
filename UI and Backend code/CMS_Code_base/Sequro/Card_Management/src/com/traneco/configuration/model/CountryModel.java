package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CountryModel {
	private String strID;
	private String strCountryName;
	private String strShortName;
	private String strStatus;
	private String strSelectID;
	private String strPhoneCode;
}
