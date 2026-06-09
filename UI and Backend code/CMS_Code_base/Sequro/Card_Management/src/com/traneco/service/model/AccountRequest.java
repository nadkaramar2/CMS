package com.traneco.service.model;

import lombok.Data;

@Data
public class AccountRequest {
	private String strParticipantID;
	private String strFunctionType;
	private String strCardNumber;
	private String strCardMask;
	private String strCardSeqNumber;
	private String strAccountNumber;
	private String strAccountType;
	private String strCurrencyCode;
	private String strAccountBranch;
	private String strPrimaryFlag;
	private String citizenID;
	private String cifKey;
}
