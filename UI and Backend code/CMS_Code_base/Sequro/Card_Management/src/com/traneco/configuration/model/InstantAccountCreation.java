package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class InstantAccountCreation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantID;
	private String strAccountType;
	private String strQuantity;
	private String strFirstName;
	private String strMiddleName;
	private String strLastName;
	private String strAccountName;
	private String strAccountNumber;
	private String strCreatedBy;
	private String strCreationDate;
	private String strModifiedDate;
	
	private String strSelectID;
}
