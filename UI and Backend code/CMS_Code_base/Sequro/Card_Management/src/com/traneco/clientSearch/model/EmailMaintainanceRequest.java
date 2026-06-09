package com.traneco.clientSearch.model;

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
	private String strRequestData;
	private String strCustomerID;
	private String strEmailFlag;
}
