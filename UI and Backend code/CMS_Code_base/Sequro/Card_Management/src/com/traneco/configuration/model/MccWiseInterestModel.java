package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MccWiseInterestModel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strMccCode;
	private String strInterestRate;
	private String strGracePeriod;
	private String strPaymentReceivedWithinDays;
	private String strAmountDuePercentage;
	private String strLatePaymentFees;
	private String strCreatedBy;
	private String strCreatedDate;
	private String strSelectID;
}
