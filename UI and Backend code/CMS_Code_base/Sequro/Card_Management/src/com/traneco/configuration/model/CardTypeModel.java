package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CardTypeModel 
{
	private String strCardType;
	private String strCardDesc;
	private String strPartID;
	private String strBin;
	private String strBinSuffix;
	private String strCVN;
	private String strPinFormat;
	private String strDecTable;
	private String strID;
	private String strSelectID;
	private String strFlag;
	private String strEndpoint;
	private String strCardGenerationType;
	private String strMcc;
	private int iCardTokenFlag;
	
	//Added By Sunny Soni for card information Start
	private String strCardTypeData;
	private String strCardId;
	private String cardNumber;
	private String strCardNumber;
	private String strCardEncCard;
	private String strCardDescr;
	//Added By Sunny Soni for card information End
	
	private String strTokenCard;
	private String strCardExpDate;
	private String strCardHolderName;
	
	private String strCardTemplateId;
	private String strCardPlasticId;
	
	private String strParticipantID;
	
	private String strAccountType;
	private String strAccountTypeDesc;
	private String status;
	

}
