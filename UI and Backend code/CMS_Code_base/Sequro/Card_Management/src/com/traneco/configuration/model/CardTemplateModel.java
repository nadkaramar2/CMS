package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CardTemplateModel {
	private String strCardType;
	private String strServiceCode;
	private String strCardIssue;
	private String strCardMailerFlag;
	private String strPinMailerFlag;
	private String strTempLimitFlag;
	private String strDailyPinLimit;
	private String strConPinLimit;
	private String strOnlineAtmLimit;
	private String strOnlinePosLimit;
	private String strOnlineEcomLimit;
	private String strOfflineLimit;
	private String strMonthlyLimit;
	private String strWeeklyLimit;
	private String strDailyLimit;
	private String strSelectID;
	private String strID;
	private String typeDesc;
	private String strExpiryType;
	private String strExpireValue;
	private String strParticipantId;
	private int cardStatus;
	private int expiryMonth;
	private int expiryYear;
}
