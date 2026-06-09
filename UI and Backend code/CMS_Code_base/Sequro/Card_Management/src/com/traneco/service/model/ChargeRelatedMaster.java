package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChargeRelatedMaster implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strChargeRelated;
	private String strChargeRelatedDescription;
	
	
}
