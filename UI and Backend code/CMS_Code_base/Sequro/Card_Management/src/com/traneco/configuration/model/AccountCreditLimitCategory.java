package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountCreditLimitCategory implements Serializable
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strParticipantId;
	private String strCreditType;
	private String strCreditLimit;
	private String strAccountNumber;
	private String strCreatedBy;
	private String strCreatedDate;
	private String strSelectID;
	
    private String strCreditTypeCategory;
    private String strAccountType;
}
