
package com.traneco.accountmanagement.model;

import lombok.Data;

@Data
public class TxnReqRespLogMaster {
	
	private String id;
	private String participantId;
	private String montraTxnId;
	private String txnId;
	private String txnRequest;
	private String txnResponse;
	private String reqTxnDate;
	private String resTxnDate;
	
	private String reqTxnTime;
	private String resTxnTime;
	
	
	private String fromDate;
	private String fromTime;
	private String toDate;
	private String toTime;
	
	
	
	
	
	

}
