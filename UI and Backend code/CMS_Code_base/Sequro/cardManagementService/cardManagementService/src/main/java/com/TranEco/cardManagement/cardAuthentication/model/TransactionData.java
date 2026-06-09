package com.TranEco.cardManagement.cardAuthentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
	private int transaction_id;
	private String field;
	private String value;
}
