package com.TranEco.cardManagement.customerManagement.model;

import lombok.Data;

@Data
public class PhoneMaintainanceRequest {
	private String strParticipantID;
	private String strFunctionType;
	private String strCitizenID;
	private String strCIFKey;
	private String strFunction;
	private String strPhoneType;
	private String strPhoneNo;
	private String strPhonePrimaryFlag;
	private String strCustomerID;
}
