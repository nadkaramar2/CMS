package com.TranEco.cardManagement.cardAuthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CardAuthenticationResponse {
	private String TransactionReferenceNo;
	private String ResponseCode;
	@JsonIgnore
	private String ApprovalNo;
	@JsonIgnore
	private String CardSequenceNo;
	@JsonIgnore
	private String ChipData;
	@JsonIgnore
	private String AdditionalData;
	@JsonIgnore
	private String BalanceInfo;
	
}
