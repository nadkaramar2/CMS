package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MerchantCategoryCodeMaster implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strMccCode;
	private String strMccCodeDesc;
	private String strCardType;
	private String strParticipantID;
}
