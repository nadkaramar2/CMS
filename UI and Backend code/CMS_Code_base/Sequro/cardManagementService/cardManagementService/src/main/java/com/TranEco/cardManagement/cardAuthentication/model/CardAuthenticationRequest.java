package com.TranEco.cardManagement.cardAuthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CardAuthenticationRequest {
	private String RequestType; 
	private String CardNumber;
	private String ProcessingCode;
	@JsonIgnore
	private String TransactionAmount;
	private String AcquirerTransactionDate;
	private String AcquirerTransactionTime;
	private String TransactionReferenceNo;
	private String MCCCode;
	private String POSEntryMode;
	@JsonIgnore
	private String CardSequenceNo;
	private String AcquirerID;
	@JsonIgnore
	private String IssuerID;
	@JsonIgnore
	private String FunctionCode;
	@JsonIgnore
	private String Track2;
	private String EMVData;
	private String CardDataInputDetails;
	@JsonIgnore
	private String PINBlock;
	private String TerminalID;
	private String MerchantID;
	private String MerchantData;
	@JsonIgnore
	private String AdditionalAmount;
	@JsonIgnore
	private String ReasonCode;
	@JsonIgnore
	private String ApprovalNo;	
	@JsonIgnore
	private String SettlementCurrencyCode;
	@JsonIgnore
	private String SettlementDate;
	@JsonIgnore
	private FeeDetails FeeDetails;
}
