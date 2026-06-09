package com.TranEco.cardManagement.inquiryServices.model;
import lombok.Data;

@Data
public class CardList {
	private String strCardType;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strEmbossLine1;
	private String strCardIssueDate;
	private String strExpiryDate;
	private String strCardStatus;
	private String strCardIssuedUser;
	private String strMaskCardNumber;	
}
