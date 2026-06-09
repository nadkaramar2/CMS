package com.TranEco.cardManagement.debitCardManagement.model;

import java.util.List;

import lombok.Data;

@Data
public class DebitCardIssuanceAPIRequest {
	private String inFunction; 
	private String inCzID;
	private String inCIFkey;
	private String inBIN;
	private String inCustSchemeCode;
	private String inCard;
	private String inMbr;
	private String inPartID;
	private String inReqCardType;
	private String inCardType;
	private List<String> inAccList;
	private String inFName;
	private String inMName;
	private String inLName;
	private String inSufx;
	private String inAddress1;
	private String inAddress2;
	private String inAddress3;
	private String inCity;
	private String inState;
	private String inCountry;
	private String inZIPCode;
	private String inHPhone;
	private String inExpdt;
	private String inOffsetFlag;
	private String inPin;
	private String inOffset;
	private String inPVV;
	private String inDOB;
	private String inSex;
	private String inEmpFlg;
	private String inCardIssueCode; //New /Existing
	private String inCard_Mailer_Flag;
	private String inPin_Mailer_Flag;
}
