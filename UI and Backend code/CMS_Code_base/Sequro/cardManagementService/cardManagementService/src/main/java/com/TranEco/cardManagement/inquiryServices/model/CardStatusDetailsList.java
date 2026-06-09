package com.TranEco.cardManagement.inquiryServices.model;
import lombok.Data;

@Data
public class CardStatusDetailsList {
	private String strPartID;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strCardStatusCode;
	private String strCardStatusDescription;
	private String strCardStatusChangeUser;
	private String strCardStatusChangeDate;
	private String strCardStatusMemo;	
}
