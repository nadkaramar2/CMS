package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;


@Data
public class AccountLinkedWalletMaster implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strParticipantId;
	private String strAccountNumber;
	private String strWalletAccountNumber;
	private String strAccountType;
	private String strMcc;
	private String strPercentage;
	private String strAvailableBalance;
	private String strCreationDate;
}
