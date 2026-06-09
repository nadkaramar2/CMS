package com.traneco.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ServiceBean {
	
	private String cardFlag;
	private String inCardType;
	private String inBulkCardType;
	private String inEmbossLine1;
	private String inEmbossLine2;
	private String inEncodeFirstName;
	private String inEncodeMiddleName;
	private String inEncodeLastName;
	private String inCustomerID;
	private String inFunction;
	private String inCardIssueCode;
	private String inPartID;
	private String inUserID;
	private String inHotListFlag;
	private String inCardIssurance;
	private String inCard;
	private String inMbr;
	private String inBranchCode;
	private String inQty;
	private String inInstantFlag;
	private String strSelectID;
	private String citizenID;
	private String cifKey;
	private String inCode;
	
	private String inChargeType;
	private String strAccountType;
	private String inTokenCard;
	
}
