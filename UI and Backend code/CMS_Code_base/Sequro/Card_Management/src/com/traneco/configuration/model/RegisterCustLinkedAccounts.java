package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegisterCustLinkedAccounts implements Serializable {

	private static final long serialVersionUID = 1L;
	private String strCustId;
	private String strDateOfRegistration;
	private String strTimeofRegistration;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strAccountType;
	private String strAccountNumber;
	private String strAccountIssueDate;
	
	
	private String strDescription;
	private String ageing;
}
