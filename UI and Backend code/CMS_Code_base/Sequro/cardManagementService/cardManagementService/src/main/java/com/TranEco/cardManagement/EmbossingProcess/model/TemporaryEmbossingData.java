package com.TranEco.cardManagement.EmbossingProcess.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TemporaryEmbossingData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strParticipantID;
	private String strCardNo;
	private String tokenCard;
	private String strMemberNumber;
	private String strActiveDate;
	private String strExpiryDate;
	private String strServiceCode;
	private String strEmbossLine1;
	private String strEmbossLine2;
	private String strEncodeFirstName;
	private String strEncodeMiddleName;
	private String strEncodeLastName;
	private String strCVV;
	private String strCVV2;
	private String striCVV;
	private String strCardType;
}
