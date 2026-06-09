package com.traneco.clientSearch.model;
import java.io.Serializable;

import lombok.Data;

@Data
public class CardStatusDetailsList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strPartID;
	private String strCardNumber;
	private String strCardSeqNumber;
	private String strCardStatusCode;
	private String strCardStatusDescription;
	private String strCardStatusChangeUser;
	private String strCardStatusChangeDate;
	private String strCardStatusMemo;	
}
