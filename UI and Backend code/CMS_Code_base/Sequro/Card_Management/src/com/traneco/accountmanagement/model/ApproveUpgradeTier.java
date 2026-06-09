package com.traneco.accountmanagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApproveUpgradeTier implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int strId;
	private String strCustId;
	private String strTierType;
	private String strReqStatus;
	private String strReqDateTime;
	private String strResDateTime;
	
	
	private String strCustName;
}
