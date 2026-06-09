package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerIdCreation implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strCustId;
	private String strAccountNumber;
	private String strAccountType;
	private String strAccountCatogory;
	private String strIsInstanceAccount;
	private String strTitle;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strGender;
	private String strDOB;
	private String strEmailID;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strCountry;
	private String strCountryCode;
	private String strState;
	private String strCity;
	private String strMobileNo;
	private String strPinCode;
	private String strAccountCreationDate;
	private String strAccountIssuedDate;
	private String strCreatedBy;
	private String StrKycUpdateRequired;
	
	private String strAction;
	private String strJulianYear;
	private String strJulianDate;
	
	private String strAddressProofDocumentId;
	private String strAddressProofDocumentValue;
	private String strIdentityProofDocumentId;
	private String strIdentityProofDocumentValue;
	private String strPhoneCode;
	private String strPhoneNo;
	
	private String strfromDate;
	private String strtoDate;
	private String strAccountHolderName;	

	private String strActiveTier;
	private String strActiveTierDate;
	private String strActiveTierTime;
	private String strTier1Date;
	private String strTier1Time;
	private String strTier1Status;
	private String strTier2Date;
	private String strTier2Time;
	private String strTier2Status;
	private String strTier3Date;
	private String strTier3Time;
	private String strTier3Status;
	
	private String strTierType;
	
	private String strCurrentTier;
	private String strBvnNumber;
	private String strTier1PassportPhotograph;
	private String strTier2PassportPhotograph;
	
	private String strTier1PassportPhoto;
	private String strTier2PassportPhoto;
	
	private String strIdentityProofImage;
	private String strAddressProofImage;
	
	private String strAddProofImage;
	private String strIdeProofImage;
	
	private String upgradeAction;
	private String strRejectedReason;
	
	private String strClosingBalance;
	private String closureReason;
	private String makerUserId;
	private String accountHolderName;
	private String currentAccountTier;
	private String currentAccountBalance;
	private String strRequestStatus;
	

}	



