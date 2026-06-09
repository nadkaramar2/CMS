package com.TranEco.cardManagement.inquiryServices.model;

import lombok.Data;

@Data
public class SearchClientCardResponseList {
	private int id;
	private String strCustomerID;
	private String strCardNumber;
	private String strAccountNumber;
	private String strCustomerName;
	private String strCitizenID;
	private String strCIFKey;
}
