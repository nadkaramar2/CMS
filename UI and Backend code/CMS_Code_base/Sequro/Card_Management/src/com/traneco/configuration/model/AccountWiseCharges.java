package com.traneco.configuration.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.traneco.service.model.AccountCreation;

import lombok.Data;

@Data
public class AccountWiseCharges implements Serializable {

	private static final long serialVersionUID = 1L;
	private String participantId;
	private String strAccountType;
	private String strAccountNumber;
	private String strCardType;
	private String strCardNumber;
	private String strChargeType;
	private String strAmount;
	private BigDecimal strClosingBalance;
	private String strGLAccountNumber;
	private String strGLAccountType;
	private String strTranType;
	private String strTranMode;
	private String strTransactionId;
	private AccountCreation accountCreation;

	private String strCardExpiryDate;
	private Date strCardIssueDate;
}
