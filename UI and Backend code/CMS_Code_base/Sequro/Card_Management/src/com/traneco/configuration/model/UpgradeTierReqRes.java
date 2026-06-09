package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UpgradeTierReqRes implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strId;
	private String strCustId;
	private String strReqStatus;
	private String strRejectedReason;
	private String strTierType;
	private Date strReqDateTime;
	private Date strResDateTime;
	
	private String action;
}
