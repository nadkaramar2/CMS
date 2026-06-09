package com.TranEco.cardManagement.debitCardManagement.model;

import lombok.Data;

@Data
public class DebitCardIssuanceRequest {
	private String inPartID; 
	private String inFunction; 
	private String inCustomerID;
	private String inCard;
	private String inNewCard;
	private String inMbr;
	private String inCardType;
	private String inCardIssueCode; //Physical /Virtual
	private String inEmbossLine1;
	private String inEmbossLine2;
	private String inEncodeFirstName;
	private String inEncodeMiddleName;
	private String inEncodeLastName;
	private String inCardMailerFlag;
	private String inPinMailerFlag;
	private String inUserID;
	private String inHotListFlag;
	private String inInstantFlag;
	private String inCode;
	private String inBranchCode;
}
