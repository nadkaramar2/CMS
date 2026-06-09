package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;


@Data
public class CreditCardModel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strParticipantId;
	private String strAccountType;
	private String strAccountNumber;
	private String strMcc;
	private String strTransactionAmount;
	private String strAmountPaid;
	private String strAmountPaidDate;
	private String strIsPaid;
	
}
