package com.TranEco.cardManagement.cardAuthentication.model;

import lombok.Data;

@Data
public class CardAuthenticationInternalResponse {
	private String strParticipantID;
	private String strCardNumber;
	private String strSequenceNo;
	private String strTransType;
	private String strTransAmount;
	private String strTransLimitClass;
	private String strCvv2;
	private String strExpiry;
	private String strServiceCode;
	private String strPinBlock;
	private String strPOSEntryMode;
	private String strEMVData;
}
