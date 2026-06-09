package com.traneco.clientSearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PINManagementRequest {
	@JsonProperty("strPartID")
	@JsonAlias({"strParticipantID","strPartID"})
	private String strParticipantID; 
	@JsonAlias({"strCardNumber","strCardNo"})
	@JsonProperty("strCardNumber")
	private String strCardNo; 
	@JsonAlias({"strCardSeqNumber","strMemberNo"})
	@JsonProperty("strCardSeqNumber")
	private String strMemberNo; 
	private String strPinMailerUpdateFlag;
	private String strPinMailerIssueFlag;
	private String strPinRetryFlag;
	private String strDailyPinRetryLimit;
	private String strConsecutivePinRetryLimit;
	private String strUserID;
}
