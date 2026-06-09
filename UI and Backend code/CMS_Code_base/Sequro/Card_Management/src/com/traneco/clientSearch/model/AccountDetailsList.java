package com.traneco.clientSearch.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AccountDetailsList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strCardNumber;
	private String strMaskCard;
	private String strCardSeqNumber;
	private String strAccountNumber;
	private String strAccountType;
	private String strCurrencyCode;
	private String strAccountBranch;
	private String strPrimaryFlag;

	public String getStrCardNumber() {
		return strCardNumber;
	}

}
