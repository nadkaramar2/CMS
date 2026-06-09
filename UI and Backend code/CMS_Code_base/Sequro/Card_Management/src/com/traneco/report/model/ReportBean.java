package com.traneco.report.model;

import lombok.Data;

@Data
public class ReportBean {

	private TransactionDetails transactionDetails;
	private TransactionBean transactionBean;
	private CardInventoryIssued cardInventoryIssued;
	private CardInventoryPending cardInventoryPending;
	
	
}
