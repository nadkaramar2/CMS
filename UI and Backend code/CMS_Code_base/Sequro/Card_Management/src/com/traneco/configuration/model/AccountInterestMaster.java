package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountInterestMaster implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strAccountNumber;
	private String strMcc;
	private String strTransactionID;
	private String strTransactionAmount;
	private String strTransactionDate;
	private String strTransactionTime;
	private String strInterestPaidAmount;
	private String strInterestPaidDate;
	private String strInterestCalculateDate;
	private String strIsPaid;
	private String strCalculateInterest;
	private String strCalculateGST;
	private String strCreateDate;

}
