package com.TranEco.cardManagement.debitCardManagement.model;

import java.io.Serializable;
import java.sql.Date;

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
	private Date strStatusChangedDate;
}
