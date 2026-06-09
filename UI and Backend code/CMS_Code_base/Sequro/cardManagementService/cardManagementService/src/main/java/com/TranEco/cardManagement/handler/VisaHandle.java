package com.TranEco.cardManagement.handler;

import java.util.HashMap;
import java.util.Map;

public interface VisaHandle {
	
	public void processTxn(Map<String, Object> request, Map<String, Object> response);
	public void processNMMMsg(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response);
	public void processNMMRequest(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response);
	public void processNMMResponse(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response);
	public void processTranAuthRequest(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response);
	public void processReversalRequest(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response);
}
