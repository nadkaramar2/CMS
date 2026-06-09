package com.traneco.accountmanagement.model;

import java.io.Serializable;
import java.util.List;

import com.traneco.service.model.AccountCreation;

import lombok.Data;

@Data
public class OneToManyAccount implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	private String strId;
	private String strFromAccountType;
	private String strFromAccountNumber;
	private String strAccountHolderName;
	private String strAvailableBalance;
	private String strTransferAmount;
	
	private String strAmount;
	private String strClosingBalance;
	
	private String strToAccountType;
	private String strToAccountNumber;
	
	private List<com.traneco.service.model.BulkTransfer> bulkTrasnferToAccounts;
	
	private List<AccountCreation> accountCreation; 

	private String strBulkData;
	
	private String accountNo;
	private String strAccountName;
	private String strAmount1;

	
}
