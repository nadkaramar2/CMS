package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegisteredCustWithLinkAccounts implements Serializable{
		
		
		private static final long serialVersionUID = 1L;
		private String strID;
		private String fromDate;
		private String toDate;
		private String strCreationDate;
		private String strStatus;
		
		
		
		
		
	
}
