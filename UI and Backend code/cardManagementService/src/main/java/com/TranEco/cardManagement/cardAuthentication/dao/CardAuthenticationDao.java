package com.TranEco.cardManagement.cardAuthentication.dao;

import java.io.IOException;
import java.util.HashMap;

import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationInternalRequest;

public interface CardAuthenticationDao {
	public boolean UpdateTransactionResultinDB(int iTransactionId, String strResponseCode, String strTransactionStatus);

	public int logTransactioninDB(HashMap<String, String> transactionData, String strResponseCode);

	public String authenticateCard(CardAuthenticationInternalRequest request) throws IOException;

	public int transactionLog(String handlerId, String uuid, String unique_msg, String process_dt,
			String transaction_class, String internalData, String external_data, int request_flag);
}
