package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PreAccountMaster implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String strID;
	
	private String strLastName;
	private String strFirstName;
	private String strMiddleName;
	
	private String strAccountType;
	private String strCountryCode;
	private String strCountryCodeShortName;

	//private Date birthDate;
	private String strGender;

	private String strDOB;
	private String strEmailID;
	private String strMobileNo;
	private String strPassword;
	private String encryptedPassword;

	private String strOtp;	
	
	private String strAddressProofDocumentId;	
	private String strAddressProofDocumentValue;
	
	private String strIdentityProofDocumentId;
	private String strAddressProofDocumentType;
	
	private String strAddressProofImage;	
	private String strIdentityProofDocumentType;
	
	private String strIdentityProofImage;
	private String strIdentityProofDocumentValue;	

	private String strCreatedBy;
	private String strAccountNumber;
	//private Date strDateOfCreation;

	private String strJwtToken;
	private String userName;
	
	//private String strIsAccountLinked;
	private String strIsAccountNoCreated;
	private String verifiedResponse;
	
	private String strImageName;
	private String strBase64Image;
	
	private String strAddProofImage;
	private String strIdeProofImage;
	private String strGeneratedAccountNumber;
	private String strIsKycVerified;
	
	private String strIsCustomerIdRelated;
	private String strIsAccountNoRelated;
	
	private String strGeneratedCustomerId;
	
	private String customerIdMap;
	private String isCustIdCreated;
	private String cust_id;
	private String accountTypeCategory;
	private String strCreditLimitCategory;
	private String screenType;
	
	
	private String fromDate;
	private String toDate;
	private String strDateofRegistration;
	private String strTimeofRegistration;
	private String strAccountRegistered;
	private String strStatus;
	private String strAgeing;
	
	private String strTier1PassPhoto;
	private String strTier2PassPhoto;
	private String strCurrentTier;
	private String strBvnNumber;
	private String strTier1PassportPhotograph;
	private String strTier2PassportPhotograph;
	
}
