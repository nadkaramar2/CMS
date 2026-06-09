package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountCreditCardTransactionModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strAccountNumber;
	private String strMcc;
	private String strTransactionAmount;
	private String strAmountPaid;
	private String strAmountPaidDate;
	private String strIsPaid;
	private String strDateOfInterest;
	private String strTxnDate;
	private String strTxnTime;
}



