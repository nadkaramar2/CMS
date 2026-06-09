package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTypeWiseWalletMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strID;
	private String strParticipantID;
	private String strAccounType;
	private String strMccCode;
	private String strMccCodeDesc;
	private String strCreatedBy;
	private String strCardType;
	private String strPercentage;
	private String strIsCreditType; 
}
