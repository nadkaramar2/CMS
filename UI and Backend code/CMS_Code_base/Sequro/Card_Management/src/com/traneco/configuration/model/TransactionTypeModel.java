package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransactionTypeModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strID;
	private String strParticipantId;
	private String strTxnTypeId;
	private String strTxnTypeDescrp;
	private String strTxnTypeKeyWord;
	private String strProcessingCode;
	
	private String fromDate;
	private String toDate;
	
	
	private String txn_Date;
	private String txn_Time;
	
	private String strGLAccountType;
	private String strGLAccountNumber;

}
