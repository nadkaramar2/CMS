package com.TranEco.cardManagement.customerManagement.model;

import lombok.Data;

@Data
public class DocumentMaintainanceRequest {
	private String strParticipantID;
	private String strCustomerID;
	private String strCitizenID;
	private String strCIFKey;
	private String strFunction;
	private String strDocumentType;
	private String strDocumentNumber;
	private String strDocumentID;
}
