package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class BranchCodeModel {
	
	private String strID;
	private String strBranchCode;
	private String strBranchAddress;
	private String strBranchType;
	private String strExtID;
	private String strPartID;
	private String strSelectID;
	
}
