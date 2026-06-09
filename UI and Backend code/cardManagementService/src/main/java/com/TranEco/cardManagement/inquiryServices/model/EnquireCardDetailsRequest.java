package com.TranEco.cardManagement.inquiryServices.model;
import lombok.Data;

@Data
public class EnquireCardDetailsRequest {
	private String strParticipantID;
	private String strCardNumber;
	private String strMemberNo;
}
