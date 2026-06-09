package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Client implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strPartID;
	private String strCitizenID;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strCIFKey;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strAddressType;
	private String strCity;
	private String strState;
	private String strCountryCode;
	private String strPinCode;
	private String strAddressPrimaryFlag;
	private String strPhoneType;
	private String strPhoneNo;
	private String strPhonePrimaryFlag;
	private String strEmailType;
	private String strEmailPrimaryFlag;
	private String strEmailID;
	private String strDOB;
	private String strGender;
	private String strMotherMaidenName;
	private String strDocumentType;
	private String strDocumentNumber;
	private String strParticipantID;
	private String strSelectID;
	private String inCard;


}
