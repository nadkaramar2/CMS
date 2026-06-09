package com.TranEco.cardManagement.inquiryServices.model;
import lombok.Data;

@Data

public class AccountDetailsList {
	private String strCardNumber;
	private String strMaskCard;
	private String strCardSeqNumber;
	private String strAccountNumber;
	private String strAccountType;
	private String strCurrencyCode;
	private String strAccountBranch;
	private String strPrimaryFlag;
}
