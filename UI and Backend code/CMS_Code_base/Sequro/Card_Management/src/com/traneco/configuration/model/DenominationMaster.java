package com.traneco.configuration.model;

import java.io.Serializable;
import java.sql.Time;

import lombok.Data;

@Data
public class DenominationMaster implements Serializable {

	private static final long serialVersionUID = 5459581698798549309L;

	private String strID;
	private String txnId;
	private int d10;
	private int d20;
	private int d50;
	private int d100;
	private int d200;
	private int d500;
	private int d1000;
	private int d2000;
	private String strTxnAmount;
	private String strTxnType;
	private String strTxnMode;
	private String fromAccount;
	private String toAccount;
	private String strDate;
	private Time strTime;
	private String strFromDate;
	private String strToDate;
	private String strAccountNumber;
}
