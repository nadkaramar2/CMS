package com.TranEco.cardManagement.inquiryServices.model;

import lombok.Data;

@Data
public class SearchClientCardRequest {
	private String strParticipantID;
	private String strCardNumber;
	private String strAccountNumber;
	private String strCustomerName;
	private String strCitizenID;
	private String strCIFKey;
	private String strSearchType;
	private String strCardType;
}
