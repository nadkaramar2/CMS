package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountStatement implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strAccountHolderName;
	private String strAccountNumber;
	private String strAccountType;
	private String strTransactionID;
	private String strTransactionDate;
	private String strTransactionAmount;
	private String strTransactionDetails;
	private String strTranMode;
	private String strTranType;
	private String strCreditAmount;
	private String strDebitAmount;
	private String strClosingBalance;
	
	private String strCreatedBy;
	private String strCreatedDate;
	private String strIsGLType;
	
	
	//added by ankit check the Query also 
	private String strParticipantId;
	private String strTransactionType;
	private String strTransactionMode;
	
	private String fromDate;	
	private String toDate;
	private String strNaration;
	
	private String strTxnDate;
	private String strTxnTime;
}