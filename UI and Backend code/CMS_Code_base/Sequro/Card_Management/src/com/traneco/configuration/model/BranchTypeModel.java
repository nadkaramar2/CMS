package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class BranchTypeModel {
	private String strID;
	private String strBranchName;
	private String strBranchDesc;
	private String strSelectID;
}