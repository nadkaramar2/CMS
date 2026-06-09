package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTransactionLimitation implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strSingleTxnLimit;
	private String strDailyTxnLimit;
	private String strMonthlyTxnLimit;
	private String strYearlyTxnLimit;
	private String strCreatedBy;
}
