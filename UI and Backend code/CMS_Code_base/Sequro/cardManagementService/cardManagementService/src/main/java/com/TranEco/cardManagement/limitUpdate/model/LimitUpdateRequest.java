package com.TranEco.cardManagement.limitUpdate.model;

import lombok.Data;

@Data
public class LimitUpdateRequest 
{
	private String strPartID;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strOnlineATMLimit;
	private String strOnlinePOSLimit;
	private String strOnlineECOMLimit;
	private String strOfflineLimit;
	private String strMonthlyLimit;
	private String strWeeklyLimit;
	private String strDailyLimit;
}
