package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class GlAccountTypeChargesMaster implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strAccountType;
	private String strChargeRelated;
	private String strChargeType;
	private String strChargeAmountAs;
	private String strAmountCharge;
	private String strFreeTxnCountInMonth;
	private String strActiveFlag;
	private String strCreatedBy;
	private String strCreationDate;
    private String strAccountNumber;  //extra add for display account serach list
}
