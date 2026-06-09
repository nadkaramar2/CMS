package com.traneco.accountmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TierAccountMaster implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strId;
	private String str;
	private String strAccountNumber;
	private String strAccountType;
	private String strTier1DailyCumlimit;
	private String strTier2DailyCumlimit;
	private String strTier3DailyCumlimit;
	private String strAvailableTier1DailyCumlimit;
	private String strAvailableTier2DailyCumlimit;
	private String strAvailableTier3DailyCumlimit;
	private String strCreateDate;
	private String strCreatedBy;
	
}

