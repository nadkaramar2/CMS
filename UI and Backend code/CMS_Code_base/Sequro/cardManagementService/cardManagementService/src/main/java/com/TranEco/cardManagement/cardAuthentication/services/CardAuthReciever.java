package com.TranEco.cardManagement.cardAuthentication.services;

import java.io.IOException;
import java.util.HashMap;

import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationInternalRequest;
import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationRequest;

public interface CardAuthReciever {
	
	public String authenticateCard(CardAuthenticationInternalRequest request)  throws IOException;
	public HashMap <String,String> parseTransaction(CardAuthenticationRequest request)  throws IOException;
	public String getTransactionType(String strProcessingCode, String strOfflineIndicator );
	public int logTransaction(HashMap <String,String> TransactionData, String strResponseCode )  throws IOException;

}
