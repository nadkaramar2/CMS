package com.traneco.clientSearch.model;
import java.io.Serializable;

import lombok.Data;

@Data
public class LimitDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
