package com.traneco.configuration.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.traneco.service.model.AccountTransactionLimitation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AccountTypeMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantId;
	private String strAccountType;
	private String strDescription;
	private String strAccountNumber;
	private String strAccNumLength;
	private String strAccNumStartDigit;
	private String strLastAccNumber;
	private String strIsCreditType;       
	private String strAccountTypeCreateDate;
	private String strStatus;
	private String strCategory;
	private String strDormantPeriod;
	
	private String strSingleTxnLimit;
	private String strDailyTxnLimit;
	private String strMonthlyTxnLimit;
	private String strYearlyTxnLimit;
	
	private String strTaxType;
	private String strTaxVal;
	private String strIGST;
	private String strCGST;
	private String strSGST;
	private String strCreatedBy;
	private String strUpdatedBy;
	private String strUpdatedDate;
	private String strSelectID;
	private String strCategoryType;
	private String strDormancyPeriodsInDays;
	private String strAllowLoadCash;
	
	private String strGLAccountType;
	private String strGLAccountNumber;
	private String strGlAccType;
	
	//added by ankit on 28-05-2023
	private String strNubanType;
	private String strAccountTypeCode;
	//added by ankit on 28-05-2023
	
	//private String strAccountWiseTxnLimit;
	private AccountTransactionLimitation accountTransactionLimitation;
	
	private String strIsRevolvingCredit;
	
	private String fromDate;
	private String toDate;
	
	private String strIsAllowWallet;
	private String strAccountTypeCategory;
	
	private String strIsWithdrawAllowAtAgent;	
	private String strIsDepositAllowAtAgent;
	private String strVAT;
	private String strAccountHolderName;
	
	
	private String strDailyCumulativeTxnLimit1;
	private String strCumulativeBalanceLimit1;
	
	private String strDailyCumulativeTxnLimit2;
	private String strCumulativeBalanceLimit2;
	
	private String strDailyCumulativeTxnLimit3;
	private String strCumulativeBalanceLimit3;
}
