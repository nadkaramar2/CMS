package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTypeWiseBlockedMccMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strID;
	private String strParticipantID;
	private String strAccounType;
	private String strBlockedMCCCode;
	private String strCreatedBy;
	private String strMccCode;
	private String strMccCodeDesc;
	private String strCreditAcType;
}
