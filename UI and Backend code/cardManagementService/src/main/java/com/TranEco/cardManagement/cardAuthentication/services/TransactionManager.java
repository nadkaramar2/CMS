package com.TranEco.cardManagement.cardAuthentication.services;

import java.util.HashMap;
import java.util.Map;

public interface TransactionManager {
	
	public void ProcessRequest(HashMap <String,String> transactionData);
	//card bin - Participant ID  - Handler , CMS Participant  - CArd Authentication  
	public void ProcessResponse(Map<String, Object> request);
	public void logRequest(Map<String, Object> request);
	public void logRequest(HashMap<String, String> transactionData);
	
	
	
}
