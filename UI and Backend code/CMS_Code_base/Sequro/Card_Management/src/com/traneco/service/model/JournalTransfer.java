package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JournalTransfer implements Serializable{

	private static final long serialVersionUID = 1L;
	private int strId;
	private String strTxnId;
	private String strFromAccountType;
	private String strFromAccountNumber;
	private String strFromAccountName;
	private String strToAccountType;
	private String strToAccountNumber;
	private String strToAccountName;
	private Double strAmoutToTransfer;
	private String strNarration;
	private String strTxnStatus;
	private String strTxnDate;
	private String strTxnTime;
	private String strMakerId;
	private String strCheckerId;
	private String strRejectReason;
	private String txnJournalTransferType;
	private String responseCode;
	private String responseMessage;
	private String loginUserCode;
	
	private String fromDate;
	private String toDate;
	private String strTxnDte;
	
}