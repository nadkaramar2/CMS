package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountLoadMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantId;
	private String strAccountType;
	private String strAccountNumber;
	private String strAccountCategory;
	private String strDateOfLoading; 
	private String strLoadedBalance;
	private String strChannel;
	private String strTimeOfLoading;
	private String strTransactionId;
	private String strCreatedDate;
    private String strCreatedBy;
}
