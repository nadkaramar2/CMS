package com.traneco.clientSearch.model;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CardDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("strParticipantID")
	@JsonAlias({"strParticipantID","strPartID"})
	private String strPartID;
	private String strCardType;
	@JsonProperty("strCardNo")
	@JsonAlias({"strCardNo","strCardNumber"})
	private String strCardNumber;
	@JsonProperty("strMemberNo")
	@JsonAlias({"strCardSeqNumber","strMemberNo"})
	private int strId;
	private String strCardSeqNumber;
	private String strTokenCard;
	private String strServiceCode;
	private String strEmbossLine1;
	private String strEmbossLine2;
	private String strEncodeFirstName;
	private String strEncodeMiddleName;
	private String strEncodeLastName;
	private String strCardIssueDate;
	private String strCardIssueCode;
	private String strCardHolderSince;
	private String strExpiryDate;
	private String strNewExpiryDate;
	private String strCardStatus;
	private String strDailyPinRetryLimit;
	private String strDailyPinRetryCount;
	private String strConsecutivePinRetryLimit;
	private String strConsecutivePinRetryCount;
	private String strLastUpdatedDate;
	private String strCardIssuedUser;
	private String strLastUpdatedUser;
	private String strCardMailerIssueDate;
	private String strPinMailerIssueDate;
	private String strPinMailerIssueFlag;
	private String strCardMailerIssueFlag;
	private String strPinMailerUpdateFlag;
	private String strPinRetryFlag;
	private String strUserID;
	//variable added by ankit
	private String strClearCardNo;
	//variable added by ankit
	
	//added by prashant
	private Date strIssuedToCustomer;
	private String strCustomerId;
}
