package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerIdTable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String strID;
	private String strYear;
	private String strJulianDate;
	private String strLastTxnSerialNo;
	private String strCreatedDate;
	private String strCreatedBy;
	private String strAction;
}
