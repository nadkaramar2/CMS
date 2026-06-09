package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;


@Data
public class CustomerIdMap implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String strCustID;
	private String strYear;
	private String strJulianDate;
	private String strAction;

}
