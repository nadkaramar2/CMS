package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CloseAccountMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strID;	
	private String transactionId;
	private String closureReqDate;
	private String closureReqTime;
	private String strAccountType;
	private String strAccountNumber;	
	private String accountHolderName;
	private String strCustId;
	private String currentAccountBalance;
	private String currentAccountTier;
	private String closureInitiatedBy;
	private String closureReason;
	private String makerUserId;
	private String chekerUserId;
	private String requestStatus;
	private String reasonForRejection;
	private String strAccountClosureRejectedDate;
	private String strAccountClosureRejectedTime;
	private String strAccountClosedDate;	
	private String strAccountClosedTime;	
	private String balanceTransferTo;
	private String recipientBankId;
	private String recipientAccountType;
	private String recipientAccountNo;
	private String recipientAccountHolderName;
	private Double transferAmount;
	
	private String strBvnNumber;
	private String strTier1PassportPhoto;
	private String strTier2PassportPhoto;
	private String strTier1PassportPhotograph;
	private String strTier2PassportPhotograph;
	private String strIdentityProofImage;
	private String strAddressProofImage;
	private String strAddProofImage;
	private String strIdeProofImage;
	
	private String strAddressProofDocumentId;
	private String strAddressProofDocumentValue;
	private String strIdentityProofDocumentId;
	private String strIdentityProofDocumentValue;
	
	private String strRejectedReason;
	private String upgradeAction;
	private String strRequestStatus;
	
	private String strClouserReqDate;
	private String strClouserReqTime;
	
	
	private String selectedAccountTypeForClose;
	private String selectedAccountNoForClose;
}
