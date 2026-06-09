package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RevolvingCreditInterestTxn implements Serializable{

		private static final long serialVersionUID = 1L;
		private int strId;
		private String strParticipantId;
		private String strAccountType;
		private String strAccountNumber;
		private String strMcc;
		private String strTxnId;
		private String  strTxnAmount;
		private Date  strTxnDate;
		private String  strTxnTime;
		private String strCalculateInterestAmt;
		private String strCalcualteGstAmt;
		private String strInterestCalDate;
		
}
