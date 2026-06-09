package com.traneco.clientSearch.model;
import java.io.Serializable;

import lombok.Data;

@Data
public class PhoneDetailsList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strPhoneID;
	private String strPhoneType;
	private String strPhoneTypeDesc;
	private String strPhoneNo;
	private String strPhonePrimaryFlag;
	
	private String strCustomerID;
	
	
	
}
