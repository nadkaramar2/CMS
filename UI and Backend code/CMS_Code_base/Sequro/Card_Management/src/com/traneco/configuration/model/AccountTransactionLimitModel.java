package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTransactionLimitModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strParticipantId;
	private String strAccountType;
	private String strAccountNumber;
	private String strSingleTxnLimit;
	private String strDailyTxnLimit;
	private String strMonthlyTxnLimit;
	private String strYearlyTxnLimit;
	private String strCreationDate;
	private String strCreatedBy;
	
}
