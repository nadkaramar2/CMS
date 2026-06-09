package com.TranEco.cardManagement.EmbossingProcess.model;

import lombok.Data;

@Data
public class TemporaryPinPrintingData {
	private String strParticipantID;
	private String strCardNumber;
	private String strMemberNumber;
	private String strExpiryDate;
	private String strServiceCode;
	private String strEmbossLine1;
	private String strEmbossLine2;
	private String strEncodeFirstName;
	private String strEncodeMiddleName;
	private String strEncodeLastName;
	private String strCVV;
	private String strCVV2;
	private String striCVV;
}
