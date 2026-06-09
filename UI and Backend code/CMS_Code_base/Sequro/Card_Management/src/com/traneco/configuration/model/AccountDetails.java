package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

//created by prashant tayde for view Account Details

@Data
public class AccountDetails implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strParticipantId;
	private String strAccountNumber;
	private String strAccountType;
	private String strAccountCatogory;
	private String strIsInstanceAccount;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strGender;
	private String strDOB;
	private String strEmailId;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strCountry;
	private String strCountryCode;
	private String strState;
	private String strCity;
	private String strMobileNo;
	private String strPincode;
	private String strAccountCreationDate;
	private String strAccountIssuedDate;
	private String strAssignedSingleLimit;
	private String strAssignedDailyLimit;
	private String strAssignedMonthlyLimit;
	private String strAssignedYearlyLimit;
	private String strDailyAvailableLimit;
	private String strMonthlyAvailableLimit;
	private String strYearlyAvailableLimit;
	private String strAssignedCreditLimit;
	private String strAvailableCreditLimit;
	private String strAvailableBalance;
	private String strNoOfBalanceLoading;
	private String strTotalBalanceLoaded;
	private String strDateofDormancy;
	private String strCardNumber;
	private String strTotalPrincipalOutStanding;
	private String strTotalInterestOuStanding;
}
