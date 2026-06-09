package com.traneco.accountmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CashBackCalculationModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strID;
	private String strCashbackName;
	private String strCashbackStatus;
	private String strCashbackCalculatedValue;
	private String strCashbackCalculatedBasedValue;
	private String strCashbackCreatedDate;
	private String strCashbackCreatedTime;
	private String strCashbackUpdatedDate;
	private String strCashbackUpdatedTime;
	private String strCashbackStartDate;
	private String strCashbackEndDate;
	private String strCashbackExpiryDate;
	private String strCreatedBy;
	private String strCreatedDate;
	private String strUpdatedBy;
	private String strUpdatedDate;
	
	
}
