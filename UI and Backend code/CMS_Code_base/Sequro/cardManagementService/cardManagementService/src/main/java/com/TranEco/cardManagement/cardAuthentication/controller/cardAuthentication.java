package com.TranEco.cardManagement.cardAuthentication.controller;

import java.util.Random;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.TranEco.cardManagement.cardAuthentication.dao.CardAuthenticationDaoImpl;
import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationRequest;
import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationResponse;
import com.TranEco.cardManagement.cardAuthentication.services.CardAuthRecieverImpl;
import com.TranEco.cardManagement.common.Constant.CardAuthConstant;

@RestController
public class cardAuthentication 
{
	@Autowired
	CardAuthRecieverImpl cardAuthenticationService;

	@Autowired
	CardAuthenticationDaoImpl cardAuthenticationDao;
	
	private static Logger logger = Logger.getLogger(cardAuthentication.class.getName());

	@RequestMapping(method = RequestMethod.POST, value = "cardAuthentication", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public CardAuthenticationResponse CardAuthenticationRequest(@RequestBody CardAuthenticationRequest request) {
		CardAuthenticationResponse response = new CardAuthenticationResponse();
		try 
		{
			logger.info("CardAuthenticationRequest"+request.toString());
			switch (request.getProcessingCode().substring(0, 2)) 
			{
			case CardAuthConstant.PURCHASE: // OFFLINE_DEBIT_POST
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setApprovalNo(String.valueOf(new Random().nextInt(9999)));
				response.setCardSequenceNo("1");
				response.setResponseCode("00");
				response.setChipData("");
				response.setAdditionalData("1002356C000000100000");
				break;

			case CardAuthConstant.PURCHASE_WITH_CASHBACK:
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setApprovalNo(String.valueOf(new Random().nextInt(9999)));
				response.setCardSequenceNo("1");
				response.setResponseCode("00");
				response.setChipData("");
				response.setAdditionalData("1002356C000000100000");
				break;

			case CardAuthConstant.CASH_POS:
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setApprovalNo(String.valueOf(new Random().nextInt(9999)));
				response.setCardSequenceNo("1");
				response.setResponseCode("00");
				response.setChipData("");
				response.setAdditionalData("1002356C000000100000");
				break;

			case CardAuthConstant.MONEY_ADD:
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setApprovalNo(String.valueOf(new Random().nextInt(9999)));
				response.setCardSequenceNo("1");
				response.setResponseCode("00");
				response.setChipData("");
				response.setAdditionalData("1002356C000000100000");
				break;

			case CardAuthConstant.SERVICE_CREATION:
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setCardSequenceNo("1");
				response.setResponseCode("00");
				response.setAdditionalData("1002356C000000100000");
				break;

			case CardAuthConstant.BALANCE_INQUIRY:
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setApprovalNo(String.valueOf(new Random().nextInt(9999)));
				response.setCardSequenceNo("1");
				response.setResponseCode("00");
				response.setChipData("");
				response.setAdditionalData("1002356C000000100000");
				response.setBalanceInfo("1002356C000000100000");
				break;

			default:
				response.setTransactionReferenceNo(request.getTransactionReferenceNo());
				response.setResponseCode("91");
				break;
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setResponseCode("99");
			logger.info("CardAuthenticationError"+e.getMessage(),e);
		}
		logger.info("CardAuthenticationResponse"+response.toString());
		return response;
	}

}
