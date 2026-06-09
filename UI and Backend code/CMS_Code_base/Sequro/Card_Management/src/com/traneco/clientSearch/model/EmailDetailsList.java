package com.traneco.clientSearch.model;
import java.io.Serializable;

import lombok.Data;

@Data
public class EmailDetailsList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strEmailType;
	private String strEmailTypeDesc;
	private String strEmailPrimaryFlag;
	private String strEmailID;
	private String strCustomerId;
}
