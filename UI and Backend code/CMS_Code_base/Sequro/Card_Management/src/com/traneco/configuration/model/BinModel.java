package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class BinModel 
{
	private String strBin;
	private String strBinDesc;
	private String strSelectID;
	private String strID;
	private String strNetworkScheme;
	private String flag;
	
}
