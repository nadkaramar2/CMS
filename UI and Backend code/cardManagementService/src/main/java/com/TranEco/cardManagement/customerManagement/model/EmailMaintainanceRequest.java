package com.TranEco.cardManagement.customerManagement.model;

import lombok.Data;

@Data
public class EmailMaintainanceRequest {
	private String strParticipantID;
	private String strFunctionType;
	private String strCitizenID;
	private String strCIFKey;
	private String strFunction;
	private String strEmailType;
	private String strEmailPrimaryFlag;
	private String strEmailID;
	private String strCustomerID;
}
