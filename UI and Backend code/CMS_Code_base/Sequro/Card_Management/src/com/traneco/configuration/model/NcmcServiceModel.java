package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class NcmcServiceModel {
	private String strID;
	private String strSelectID;
	private String strPartID;
	private String strNcmcID;
	private String strFlag;
	private String strCardType;
	private String ncmcList;
}
