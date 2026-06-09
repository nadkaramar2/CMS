package com.traneco.configuration.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class RevolvingCreditCardTxnMaster implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private int strId;
	private String strParticipantId;
	private String strAccountType;
	private String strAccountNumber;
	private String strMcc;
	private String  strInterestRate;
	private String strTxnId;
	private String strTranType;
	private String strTxnAmount;
	private String strTranTypeDes;
	private Date TxnDate;
	// private String strTxnDate;
	private Time TxnTime;
	// private String strTxnTime;
	private String strRemaningGracePeriod;
	// private Double strUpdateTxnAmount;
	//private Double totalOutstandingInterest;	
	// private Double totalCalGstAmount;
	
}
