package com.traneco.configuration.model;

import lombok.Data;

@Data
public class BalanceUpdateInAccountMaster {

	private String strAccountType;
	private String strAccountNumber;
	private String strOpeningBalance;
	private String strClosingBalance;
	private String strLoadCount;
}
