package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class GLAccountStatement implements Serializable{

	private static final long serialVersionUID = 1L;
	private String strID;
	private String participantIdString;
	private String strAccountNumber;
	private String strAccountType;
	private String strClosingBalance;
	private String strCreatedBy;
	private String strCreatedDate;
	private String strIsGLType;
	private String strTransactionID;
	private String strTransactionDate;
	private String strTransactionDetails;
	private String strTransactionType;
	private String strTransactionMode;
	private String strTransactionAmount;
	
	private String strTranMode;
	private String strTranType;
	
	private String strRef;
	private String strAmount;
	private String strTxnId;
	private Date transactionDate;
	private String strCreated_by;
	private String strGLAccountType;
	private String fromDate;
	private String toDate;
	private String strTxnDate;
	private String strTxnTime;
}
