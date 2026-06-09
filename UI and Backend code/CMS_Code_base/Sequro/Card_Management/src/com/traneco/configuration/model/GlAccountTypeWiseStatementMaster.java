package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data              //GenrealLegar(GL)
public class GlAccountTypeWiseStatementMaster implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strAccountType;
	private String strAccountNumber;
	private String strRef;
	private String strTranID;
	private String strAmount;
	private String strCreatedBy;
	private String strCreatedDate;
    private String strTransactionDate;
	

}
