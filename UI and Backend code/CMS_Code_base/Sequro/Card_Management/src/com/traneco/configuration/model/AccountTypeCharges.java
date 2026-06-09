package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTypeCharges implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String strId;
	private String strParticipantID;
	private String strAccountType;
	private String strChargeType;
	private String strChargeDescription;
	private String strAmount;
	private String strPercentage;
	private String strCreatedBy;
	private String strCreatedDate;
	
	
	private String strSelectedCardChargeType;
	private String strSelectedTxnChargeType;
	private String strSelectedFuelChargeType;
	private String strChargeRelated;
	private String selectedChargeTypeWithAmount;
	private String selectedChargeTypeWithPercentage;
	
	private String strIsRewardActive;
	
	private String strAccountNumber;
}
