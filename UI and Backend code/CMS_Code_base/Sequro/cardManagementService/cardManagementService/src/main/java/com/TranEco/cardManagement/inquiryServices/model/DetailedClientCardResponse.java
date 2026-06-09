package com.TranEco.cardManagement.inquiryServices.model;

import java.util.List;

import lombok.Data;

@Data
public class DetailedClientCardResponse {
	private String outResponseCode;
	private String message;
	private CustomerDetails CustomerDetails;
	private List<CardList> CardList;
	private List<AccountDetailsList> AccountDetailsList;
	private List<AddressDetailsList> AddressDetailsList;
	private List<PhoneDetailsList> PhoneDetailsList;
	private List<EmailDetailsList> EmailDetailsList;
	private List<DocumentDetailsList> DocumentDetailsList;
}
