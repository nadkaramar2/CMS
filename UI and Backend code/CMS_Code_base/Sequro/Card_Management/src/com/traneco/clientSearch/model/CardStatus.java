package com.traneco.clientSearch.model;

import lombok.Data;

@Data
public class CardStatus {
	private String strParticipant_ID; 
	private String strCardNumber; 
	private String strMemberNo; 
	private String strStatusCode; 
	private String strDescription;
	private String StrStatusChangeUser;
}
