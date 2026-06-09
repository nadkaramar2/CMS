package com.traneco.report.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TransactionDetails {

	private int id;
	private String uuid;
	private String transaction_time;
	private String request_type;
	private String transaction_type;
	private String transaction_status;
	private String transaction_result;
	private int acquirer_id;
	private int issuer_id;
	private int transaction_amount;
	private int transaction_ref_no;
	private Timestamp transaction_timeout_time;

}
