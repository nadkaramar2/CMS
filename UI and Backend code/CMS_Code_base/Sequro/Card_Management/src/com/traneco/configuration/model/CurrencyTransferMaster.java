package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CurrencyTransferMaster  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	private String strID;
	
	
	private String sweepOutCurrencyCode;
	
	
	private String sweepOutCurrencyValue;
	
	private String sweepInCurrencyCode;
	
	
	private String sweepInCurrencyValue;
	
	private String sweepInAmount;
	
	
	private String sweepOutAmount;
	
	
	private String tranId;
	
	
	private String createdBy;
	
	
	private String baseAccountNumber;
	
	
	private String baseAccountType;
	

	private double feeAmount;
	

	private double gstAmount;
	

	private Date createdDate;
}
