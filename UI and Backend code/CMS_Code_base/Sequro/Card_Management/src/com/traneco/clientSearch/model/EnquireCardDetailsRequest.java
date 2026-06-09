package com.traneco.clientSearch.model;
import lombok.Data;

@Data
public class EnquireCardDetailsRequest {
	private String strParticipantID;
	private String strCardNumber;
	private String strMemberNo;
}
