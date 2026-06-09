package com.traneco.clientSearch.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SearchClientCardRequest {
	private String strParticipantID;
	private String strCardNumber;
	private String strAccountNumber;
	private String strCustomerName;
	private String strCitizenID;
	private String strCIFKey;
	private String strCustomerID;
	private String strSearchType;
	private String strCardType;

}
