package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoadBalanceModel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strParticipantId;
	private String strAccountType;
	private String strAccountNumber;
	private String strAccountCategory;
	private String strDateOfLoading; //added on 26th by ankit
	private String strLoadedBalance;
	private String strChannel;
	private String strTimeOfLoading; //added on 26th by ankit
	private String strTransactionId;
	private String strCreatedDate;
    private String strCreatedBy;
    private String strIsGLType;
   
}
