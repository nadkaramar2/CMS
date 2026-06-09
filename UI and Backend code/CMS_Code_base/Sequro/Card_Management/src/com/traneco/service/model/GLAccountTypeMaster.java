package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class GLAccountTypeMaster implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strGLAccountType;	
	//Added a Variable by ankit
	private String strStatus;
	//Added a Variable by ankit
	private String strGLAccountDescription;
	private String strAccountNumber;
	private String strOpeningBalance;
	private String strClosingBalance;
	private String strCreatedDate;
	private String strCreatedBy;
	
	private String strAccountType;
	private String userAccountType;
	private String txnAmount;
	private String tranId;
	
	private String strParticipantId;
	private String strAllGLBalance;
}