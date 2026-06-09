package com.TranEco.cardManagement.inquiryServices.model;
import lombok.Data;

@Data
public class LimitDetails {
	private String strPartID;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strOnlineATMLimit;
	private String strOnlineATMLimitUsed;
	private String strOnlinePOSLimit;
	private String strOnlinePOSLimitUsed;
	private String strOnlineECOMLimit;
	private String strOnlineECOMLimitUsed;
	private String strOfflineLimit;
	private String strOfflineLimitUsed;
	private String strMonthlyLimit;
	private String strMonthlyLimitUsed;
	private String strWeeklyLimit;
	private String strWeeklyLimitUsed;
	private String strDailyLimit;
	private String strDailyLimitUsed;
}
