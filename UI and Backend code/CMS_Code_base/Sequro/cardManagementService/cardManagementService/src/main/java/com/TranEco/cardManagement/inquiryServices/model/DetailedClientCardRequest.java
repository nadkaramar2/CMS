package com.TranEco.cardManagement.inquiryServices.model;

import lombok.Data;

@Data
public class DetailedClientCardRequest 
{
	private String strParticipantID;
	private String strCustomerID;
	private String strCitizenID;
	private String strCIFKey;
}