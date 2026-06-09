package com.TranEco.cardManagement.inquiryServices.model;

import lombok.Data;

@Data
public class CardDetailsList {

	private String strCardType;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strServiceCode;
	private String strEmbossLine1;
	private String strEmbossLine2;
	private String strEncodeFirstName;
	private String strEncodeMiddleName;
	private String strEncodeLastName;
	private String strCardIssueDate;
	private String strExpiryDate;
	private String strCardStatus;
	private String strDailyPinRetryLimit;
	private String strDailyPinRetryCount;
	private String strConsecutivePinRetryLimit;
	private String strConsecutivePinRetryCount;
	private String strPinMailerIssueDate;
	private String strOnlineATMLimit;
	private String strOnlineATMLimitUsed;
	private String strOnlinePOSLimit;
	private String strOnlinePOSLimitUsed;
	private String strOnlineECOMLimit;
	private String strOnlineECOMLimitUsed;
	private String strMonthlyLimit;
	private String strMonthlyLimitUsed;
	private String strWeeklyLimit;
	private String strWeeklyLimitUsed;
	private String strDailyLimit;
	private String strDailyLimitUsed;
	private String strLastUpdatedDate;
	private String strCardIssuedUser;
	private String strLastUpdatedUser;
}
