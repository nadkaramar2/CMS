package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RevolvingCreditCardMaster implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strBillingCycleDate;
	private String strGracePeriodInDays;
	private String strMadRate;
	private String strInterestRate;
	private String strPenultyInterestRate;
	private String strLatePaymentFees;
	private String strDeliquencyDays;
	private String strCreatedDate;
	private String strCreatedBy;
}
