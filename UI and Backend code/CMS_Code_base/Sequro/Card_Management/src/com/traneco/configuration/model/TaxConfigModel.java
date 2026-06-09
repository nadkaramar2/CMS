package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaxConfigModel implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private int strID;
	private String strParticipantId;
	private String strTaxType;
	private String strTaxValue;
	private String strCreatedBy;
	private String strCreatedDate;
	
}
