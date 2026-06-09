package com.traneco.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class WalletAccountMaster implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String strID;	
	private String strMccCode;
	private String strCreatedBy;
	private String strPercentage;
	private String strAccountType;	
	private Date strDateOfCreation;
	private String strAccountNumber;	
	private String strParticipantID;	
	private String strAvailableBalance;
	private String strWalletAccountNumber;	
}
