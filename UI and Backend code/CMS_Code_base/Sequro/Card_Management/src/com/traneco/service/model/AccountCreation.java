package com.traneco.service.model;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class AccountCreation implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strAccountNumber; 
	private String strTitle;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strCity;
	private String strState;
	private String strCountry; 
	private String strCountryCode;
	private String strPinCode;
	private String strMobileNo;
	private String strPhoneCode;
	private String strPhoneNo;
	private String strEmailID;
	private String strDOB;
	private String strGender;
	private String strDateOfCreation;
	private String strCreatedBy;
	private String strStatus;
	private String strCreditLimitCategory;
	private String strCreditLimitAmount;
	private String strAvailableCreditLimit;
	private String strTaxType;
	private String strIsInstantAccount;
	private String strOpeningBalance;
	private String strClosingBalance;
	private String strLastAccNumber;
	private String strSelectID;
	//private String strLoadCounter;
	private String strLoadCount;
	private String strAvailableDailyLimit;
	private String strAvailableMonthlyLimit;
	private String strAvailableYearlyLimit;
	private String strTotalOutstandingBalance;
	private String strTotalOutstandingBal;
	private String strTotalOutstandingInterest;
	private Date strDateofAccountClosure;
	private Date strDateofDormancy;
	
	private String strAccountWiseTxnLimit;
	
	private String strAddressProofDocumentId;
	private String strAddressProofDocumentValue;
	private String strIdentityProofDocumentId;
	private String strIdentityProofDocumentValue;
	//private AccountTransactionLimitation accountTransactionLimitation;
	private String strIsCreditType;	
	private String strBankName;
	private String strRequestBtn;
	private String strCreditTypeCategory;
	
	private String strIsLinkedwithCard;
	private String strKycUpdateRequired;
	
	private String strCustId;
	
	private String strQrCodeData;
	private String strQrCodeFilePath;
	
	private int strPerTxnLimit;
	private int strDailyTxnLimit;
	private String strMonthlyTxnLimit;
	private String strYearlyTxnLimit;
	
	private String strAccountHolderName;
	private String strDateOfRegistration;
	private String strAccountIssueDate;

	
	//added by ankit 
	private String strAmount;
	private String strChargeStatus;
	
}
