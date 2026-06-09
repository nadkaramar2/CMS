package com.traneco.service.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CardAccountLinkage implements Serializable 
{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strAccountNumber;
	private String strCardType;
	private String strCardNumber;
	private String strCreationDate;
	
	private String strCreatedBy;
	private String strCardStatus;
	private String strAccountStatus;
	private String strSelectID;
	private String type;
	
	private String strCardEncCard;
	private String strCardDescription;
	private String strCardId;
	
	private String strCustId;
	
	private String strTokenCard;
	private String strCardExpDate;
	private String strCardHolderName;
	private String strNetworkType;
	private String strBin;
	
	private String strCardCvv;
	
	private Date creationDate;
	
	private String strChargeType;
}
