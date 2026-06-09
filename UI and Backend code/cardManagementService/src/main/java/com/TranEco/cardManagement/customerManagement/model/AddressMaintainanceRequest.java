package com.TranEco.cardManagement.customerManagement.model;

import lombok.Data;

@Data
public class AddressMaintainanceRequest {
	private String strParticipantID;
	private String strFunctionType;
	private String strCitizenID;
	private String strCIFKey;
	private String strCustomerID;
	private String strAddressType;
	private String strAddress1;
	private String strAddress2;
	private String strAddress3;
	private String strCity;
	private String strState;
	private String strCountryCode;
	private String strPinCode;
	private String strAddressPrimaryFlag;
}
