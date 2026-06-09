package com.TranEco.cardManagement.cardAuthentication.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.dao.EhCacheDao;
import com.TranEco.cardManagement.model.Transaction_Routing;

@Service
public class TransactionManagerImpl implements TransactionManager {

	@Autowired
	CardAuthManager cardAuthManager;
	
	@Autowired
	EhCacheDao ehCacheDao;
	
	/*
	 * @Override public void logRequest(HashMap <String,String> transactionData) {
	 * HashMap<String, String> request = null;
	 * cardAuthManager.sendRequestHandler(request); }
	 */
	@Override
	public void ProcessRequest(HashMap <String,String> transactionData) {
		// TODO Auto-generated method stub
		// Acquirer ID
		String acqId = transactionData.get("REQ.ACQ_PARTICIPANT_ID");
		String panNo = transactionData.get("REQ.PAN");	
		
		
	HashMap<String, String> request = null;
	Optional<Transaction_Routing> routingData =	ehCacheDao.transactionRouting().stream().filter(k -> k.getAcq_partid() == Integer.parseInt(request.get("REQ.AQU.PART"))).findAny();
		if(routingData.isPresent()) 
		{
			Transaction_Routing routing = routingData.get();
			String cardNo = request.get("REQ.CARD"); 
			int startCard = Integer.parseInt(cardNo.subSequence(0, 6).toString());
			if(startCard >= routing.getBin_start() && startCard <= routing.getBin_end()) {
				//Log Request
				//logRequest(new HashMap(request));
				
				// Call card Auth Manager
				//Update transaction Status If fail then send response back 
				
				// Call CBS Handler 
				//Update transaction Status If fail then send response back 
							
				// Send Response to VISA
			}else {
				// TODO decline txn
			}
		}
	}

	@Override
	public void ProcessResponse(Map<String, Object> request) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void logRequest(Map<String, Object> request) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logRequest(HashMap<String, String> transactionData) {
		// TODO Auto-generated method stub
		
	}

}
