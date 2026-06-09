package com.TranEco.cardManagement.cardAuthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CardAuthenticationInternalRequest {
	private String strParticipantID;
	private String strCardNumber;
	@JsonIgnore
	private String strSequenceNo;
	private String strTransType;
	@JsonIgnore
	private String strTransAmount;
	@JsonIgnore
	private String strTransLimitClass;
	@JsonIgnore
	private String strTrack2;
	@JsonIgnore
	private String strPinBlock;
	private String strPOSEntryMode;
	@JsonIgnore
	private String strEMVData;
	@JsonIgnore
	private String strResponseCode;
}
