package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreditCardCustomerAccountCreationModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int strId;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strExtAccount;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strCity;
	private String strState;
	private String strCountryCode;
	private String strPinCode;
	private String strPhoneNo;
	private String strEmailID;
	private String strDOB;
	private String strGender;
	private String strMotherMaidenName;
	private String strDocumentType;
	private String strDocumentNumber;
	private String strBranchCode;
	private String strCardIssueCode;
	private String strCardType;
	private String strEmbossLine1;
	private String strEmbossLine2;
	private String strEncodeFirstName;
	private String strEncodeMiddleName;
	private String strEncodeLastName;
	private String strFunction;
	private String strCardBin;
	private String strBin;
	private String strBvnNumber;
	private String strDebitAccountNumber;
	private String strBankCustId;
	private String strInstititionId;
	private String strRequestId;
	
	private String strSelectID;
	
	 private String outCard;
	 private String strCCAccountNumber;
	
	 private String strResponseCode;
	 private String strMessage;
	 private String strCustId;
	 private String strEmbossName; 
	 private String strMbr;
	   
	  
	 	private String outResponseCode;
		private String message;
		private String outTokenCard;
		private String encryptedCardNumber;
		private String code;
		private String desc;
		private String status;
		private String result;
		private String count;
	    private String accountNumber;
	
	
	
}
