package com.traneco.service.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountStatementFooterView implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String openingBalance;
	private String debitCount;
	private String creditCount;
	private String debits;
	private String credits;
	private String closingBalance;
}
