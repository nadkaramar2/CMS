package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class GLAccountLoadingMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strId;
	private String strParticipantId;
	private String strGLAccountType;
	private String strGLAccountDescription;
	private String strAccountNumber;
	private String strLoadCount;
	private String strOpeningBalance;
	private String strClosingBalance;
	private String strLoadedBalance;
	private String strTotalBalance;
	private String strChannel;
	private String strAvailableBalance;
	private Date   strAccountCreationDate;
	private String strAccountCreatedBy;
	private String strDateOfLoading;
	private String strTimeOfLoading;
	private String strTransactionId;
}
