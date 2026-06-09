package com.TranEco.cardManagement.handler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.cardAuthentication.dao.CardAuthenticationDao;
import com.TranEco.cardManagement.cardAuthentication.services.TransactionManager;
import com.TranEco.cardManagement.participantNode.dao.ParticipantNodeDao;
import com.TranEco.cardManagement.services.EhCacheService;
import com.TranEco.cardManagement.utils.CardUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class VisaHandleImpl implements VisaHandle {

	@Autowired
	ParticipantNodeDao participantNodeDao;
	
	@Autowired
	TransactionManager transactionManager;
	
	@Autowired
	EhCacheService ehCacheService;
	
	@Autowired
	CardAuthenticationDao cardAuthenticationDao;
	
	@Override
	public void processTxn(Map<String, Object> request, Map<String, Object> response) 
	{
		HashMap <String,String> transactionData = new HashMap();
		
		String txnClass = request.get("de0").toString();
		
		transactionData.put("REQ.REQUEST_TYPE", txnClass);
		
		// Identify Message Type MTI
		if (request.get("de0") == "0800" || request.get("de0") == "0810")
		{
			processNMMMsg(transactionData, request, response);
		}
		else if (request.get("de0") == "0200" || request.get("de0") == "0100" || request.get("de0") == "0120") 
		{
			processTranAuthRequest(transactionData, request, response);
		}
		else if (request.get("de0") == "0400") 
		{
			processReversalRequest(transactionData, request, response);
		}
		
		
		// 0800  - Check DE 70 - Sign On - Change State to Logged ON
		// 0800  - Check DE 70 - Echo - Check State if LOgged then send successful response
		// 0800  - Check DE 70 - Sign Off - Change state to Log Off Then send Successful response
		
		
		// 0420 
		// get Unique key and Find Original 
		// If Original Found then send to transaction Manager
		
		
	}

	@Override
	public void processNMMMsg(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response) 
	{
		if (request.get("de0") == "0800")
		{
			processNMMRequest(transactionData, request, response);
		} 
		else if (request.get("de0") == "0810")
		{
			processNMMResponse(transactionData, request, response);
		}
		
	}

	@Override
	public void processTranAuthRequest(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response) {
		// TODO Auto-generated method stub
		// 0200  
				//Create Unique_Txn_Key - Check for Duplicate : 
				//Identify Acquirer ID and Set in the message
				//Send transaction to Transaction Manager
		
		String uuid = "";
		uuid = UUID.randomUUID().toString();
		
		int participantID = identifyParticipant(StringUtils.defaultString(request.get("de32").toString(), "0"));
		if (participantID == 0)
		{
			//Set Negative response
		}
		
		//Set Message Class
		
		// Develop LOgic to identify transaction type from INterface Config - Transaction type mapping to br maintained against new key type 
		String transactionType = identifyInternalValue(StringUtils.substring(request.get("de3").toString(), 0 , 2));
		if (transactionType.isEmpty())
			//Prepare Response and send decline response back
		transactionData.put("REQ.TRANSACTION_TYPE", transactionType);
		transactionData.put("REQ.ACCOUNT_FORM", identifyInternalValue(StringUtils.substring(request.get("de3").toString(), 2 , 4)));
		transactionData.put("REQ.ACCOUNT_TO", identifyInternalValue(StringUtils.substring(request.get("de3").toString(), 4 , 6)));
		//SetAccountType(request, transactionData);
				
		transactionData.put("REQ.ACQ_PARTICIPANT_ID", String.valueOf(participantID));

		//Set PAN
	//	if (request.get("de2").toString().isEmpty())
			

		//Set Acquirer Switch Transaction Time
		transactionData.put("REQ.ACQ_SWITCH_TRANS_TIME", StringUtils.defaultString(request.get("de7").toString()));
			
		//Set STAN
		transactionData.put("REQ.STAN", StringUtils.defaultString(request.get("de11").toString(), "0"));
						
		//Set Local Transaction date
		transactionData.put("REQ.LOCAL_TRANS_DATE", StringUtils.defaultString(request.get("de12").toString()));				

		//Set Local Transaction time
		transactionData.put("REQ.LOCAL_TRANS_TIME", StringUtils.defaultString(request.get("de13").toString()));
		
		//Set RRN
		transactionData.put("REQ.RRN", StringUtils.defaultString(request.get("de36").toString(), ""));
		
		//Set Terminal ID
		transactionData.put("REQ.TERMINAL_ID", StringUtils.defaultString(request.get("de41").toString(), ""));
		
		//Set Merchant ID
		transactionData.put("REQ.MERCHANT_ID", StringUtils.defaultString(request.get("de42").toString(), ""));
		
		//Set Merchant Terminal Address
		transactionData.put("REQ.TERMINAL_ADDRESS", StringUtils.defaultString(request.get("de43").toString(), " "));
				
		
		
		//get and store Unique ID
		GenerateAndStoreUniqueID(request, uuid, transactionData);
		
		transactionManager.ProcessRequest(transactionData);
		//ProcessTranAuthResponse(response, transactionData, response);
		
	}

	private String identifyInternalValue(String id) {
		return ehCacheService.getInterface_Config().stream().filter(k -> k.getKey_type().equals(id)).map(k -> k.getKey_value()).findAny().orElse(null);
	}

	private int identifyParticipant(String id) {
		return Integer.parseInt(ehCacheService.getInterface_Config().stream().filter(k -> k.getKey_type().equals(id)).map(k -> k.getKey_value()).findAny().orElse("0"));
	}

	@Override
	public void processReversalRequest(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response) {
		// TODO Auto-generated method stub
		transactionManager.logRequest(request);
		//ProcessReversalResponse(response, transactionData)
		
	}

	@Override
	public void processNMMRequest(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response) {
	
		//Set all response fields and Bit Maps
		//POssible NOde Status
		// 0 - Loaded
		// 1 - Connected
		// 2 - Signed On
		// 3 - Signed Off
		// 4  - Inactive
		
		response.put("de0", "810");
		response.put("de39", "00");
		response.put("de7", request.get("de7"));
		response.put("de11", request.get("de11"));
		response.put("de37", request.get("de37"));
		response.put("de63", "000000");
		response.put("de70", request.get("de70"));
		if (request.get("de99").toString().length() > 0)
		{
			response.put("de99" ,request.get("de99")); 
		}
		
		if (request.get("de70") == "071" || request.get("de70") == "171")  // Log On
		{
			participantNodeDao.updateNodeStatus(transactionData.get("REQ.PARTICIPANT_ID").toString(), "2");
			// Set Participant Node As Sign On
		}
		else if (request.get("de70") == "072" || request.get("de70") == "172")  // Log Off
		{
			// Set Participant Node As Sign Off
			participantNodeDao.updateNodeStatus(transactionData.get("REQ.PARTICIPANT_ID").toString(), "3");
		}
		else if (request.get("de70") == "301")  // Echo
		{
			// Echo Message -- Update Last Echo time in DB
			//response.Set("de70") = request.Set("de70");
		}
		else
		{
			response.put("de39", "30");  // Invalid NMIC
		}
			
		
	}
	
	public void GenerateAndStoreUniqueID(Map<String, Object> request, String uuid, HashMap<String, String> transactionData) 
	{
		// TODO Auto-generated method stub
		//Get the Unique ID from VISA TRansaction
		//String UUID = UUID.randomUUID().toString();
		String uniqueTranID = StringUtils.join(CardUtils.hashing(request.get("DE2").toString()), request.get("DE11"), request.get("DE41"), request.get("DE42"), " ", "_");
		
		//uniqueTranID = (request.get("DE2") + request.get("DE11")+ request.get("DE41") + request.get("DE42"), " ", "_");
		
		// Store Unique Tran ID in table against TRansaction UUID.

		cardAuthenticationDao.transactionLog(this.getClass().getName(), uuid, uniqueTranID, LocalDate.now().toString(), "", objectToJson(transactionData), objectToJson(request), 1);
		
	}

	@Override
	public void processNMMResponse(HashMap <String,String> transactionData, Map<String, Object> request, Map<String, Object> response) {
		// TODO Auto-generated method stub
		
		// Log Invalid response recieved from VISA
			if (request.get("de70") == "071") // Sign On response
			{
				if (request.get("de39") == "00")
				{
				//Set Participant Node Status as Signed On
					participantNodeDao.updateNodeStatus(transactionData.get("REQ.PARTICIPANT_ID").toString(), "2");	
			    }
				else
				{
					// Log the error response
					//Set Participant Node Status as Connected
					participantNodeDao.updateNodeStatus(transactionData.get("REQ.PARTICIPANT_ID").toString(), "4");
				}
			}
			else if  (request.get("de70") == "072") // Sign OFf response
			{
				if (request.get("DE39") != "00")
				{
					// Log the error response
					//Set Participant Node Status as Connected
					participantNodeDao.updateNodeStatus(transactionData.get("REQ.PARTICIPANT_ID").toString(), "3");
				}
			
			}
			else if  (request.get("de70") == "301") // Echo response
			{
				if (request.get("DE39") != "00")
				{
					// Log the error response
					//Set Participant Node Status as Connected 
					participantNodeDao.updateNodeStatus(transactionData.get("REQ.PARTICIPANT_ID").toString(), "1");
				}
			}
			
		}
	
		private String objectToJson(Object obj) {
			try {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				return ow.writeValueAsString(obj);
			}catch (Exception e) {
				return null;
			}
		}
		
	}

