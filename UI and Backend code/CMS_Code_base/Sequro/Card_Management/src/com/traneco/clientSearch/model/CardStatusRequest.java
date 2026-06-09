package com.traneco.clientSearch.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardStatusRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strParticipant_ID; 
	private String strCardNo; 
	private String strMemberNo; 
	private String strStatusCode; 
	private String strDescription;
	private String strStatusChangeUser;
}
