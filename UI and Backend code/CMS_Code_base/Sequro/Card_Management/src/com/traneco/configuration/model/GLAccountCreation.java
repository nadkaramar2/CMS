package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class GLAccountCreation implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strId;
	private String strParticipantId;
	private String strGLAccountType;
	private String strGLDescription;
	private String strGLAccountNumber;
	
	private String strGLAccountNumber2;
	private String strGLAcountNumber1;
	
	private String strOpeningBalance;
	private String strClosingBalance;
	private String strStatus;
	private String strCreatedDate;
	private String strCreatedBy;
	
	private String responseMsg;
	private String strThirdPartyAllow;
	private String strParticipantName;
	private String strIsThirdPartyTransfer;
	private String strTransferTranType;
	private String strTransferAmount;
	private String strAllGLBalance;
}
