package com.traneco.clientSearch.model;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CardDetailsList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strCardType;
	private String strCardNumber;
	private String strMaskCardNumber;
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
	private String strOfflineLimit;
	private String strPartID;
	
	private String strCustomerIssuedDate;
	private String strTokenCard;
	
}
