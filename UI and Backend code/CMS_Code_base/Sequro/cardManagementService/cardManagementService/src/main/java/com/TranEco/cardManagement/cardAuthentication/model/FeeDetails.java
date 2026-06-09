package com.TranEco.cardManagement.cardAuthentication.model;

import lombok.Data;

@Data
public class FeeDetails {
	private String FeeDebitCreditInd;
	private String FeeAmount;
	private String FeeCurrencyCode;
	private String FeeTpCd;
}
