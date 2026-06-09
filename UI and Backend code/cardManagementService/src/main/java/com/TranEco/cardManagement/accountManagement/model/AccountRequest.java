package com.TranEco.cardManagement.accountManagement.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AccountRequest {
	private String strParticipantID;
	private String strFunctionType;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strAccountNumber;
	private String strAccountType;
	private String strCurrencyCode;
	private String strAccountBranch;
	private String strPrimaryFlag;
}
