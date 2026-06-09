package com.traneco.accountmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTypeTierBasedLimit implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strAccountType;
	private String strTierType;
	private String strDailyCumulativeTxnLimit;
	private String strCumulativeBalanceLimit;
	private String strCreatedDate;
	private String strCreatedTime;
	private String strCreatedBy;
}
