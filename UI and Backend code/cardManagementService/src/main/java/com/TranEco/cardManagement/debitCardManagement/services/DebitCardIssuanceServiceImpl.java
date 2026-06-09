package com.TranEco.cardManagement.debitCardManagement.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.debitCardManagement.dao.DebitCardIssuanceDaoImpl;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusRequest;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusResponse;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceRequest;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationRequest;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationResponse;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementRequest;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementResponse;
import com.TranEco.cardManagement.model.Cfg_Card_Template;
import com.TranEco.cardManagement.model.Cfg_Card_Type;
import com.TranEco.cardManagement.scheduler.model.CardTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.scheduler.service.SchedulerService;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;
import com.TranEco.cardManagement.utils.CardNumberGenerator;
import com.google.gson.Gson;

@Service("debitCardIssuanceService")
public class DebitCardIssuanceServiceImpl implements DebitCardIssuanceService {

	@Autowired
	DebitCardIssuanceDaoImpl debitCardIssuanceDao;

	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Autowired
	@Lazy
	SchedulerService schedulerService;

	@Override
	public DebitCardIssuanceResponse debitCardIssuanceRequest(DebitCardIssuanceRequest request) {
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		String strCardTypeKey = request.getInPartID() + request.getInCardType();
		Cfg_Card_Type cfg_Card_Type = ehCacheService.getCfg_Card_Type().get(strCardTypeKey);

		if (cfg_Card_Type != null) 
		{
			String strBin = cfg_Card_Type.getBin();
			if (validateCardType(strCardTypeKey)) 
			{
				response.setOutResponseCode(Constant.CMS_Constants.INVALID_CARD_TYPE);
				response.setMessage("Invalid Card Type");
			} 
			else if (strBin == null) 
			{
				response.setOutResponseCode(Constant.CMS_Constants.INVALID_CARD_BIN);
				response.setMessage("Invalid BIN");
			} 
			else if (!validateFunction(request)) {
				response.setOutResponseCode(Constant.CMS_Constants.INVALID_FUNCTION_TYPE);
				response.setMessage("Invalid Function Type");
			} 
			else 
			{
				if ("NEWCARD".equals(request.getInFunction()) || "INSTANTCARD".equals(request.getInFunction())) 
				{
					//String strBinSufix = ehCacheService.getCfg_Card_Type().get(strCardTypeKey).getBin_suffix();
					String binSuffix = "";
					
					if(debitCardIssuanceDao.getBinSuffixFlag(cfg_Card_Type.getBin()) > 0) {
						 binSuffix = strBin +""+ (cfg_Card_Type.getBin_suffix() == null ? "" : cfg_Card_Type.getBin_suffix());
					}else {
						 binSuffix = strBin ;
					}
					
					if("R".equals(cfg_Card_Type.getStrCardGenerationType())) {
						request.setInCard(CardNumberGenerator.generate(binSuffix, 16));
					}
					else 
					{
						request.setInCard(debitCardIssuanceDao.getLastSequenceNo(request.getInPartID(), binSuffix));
					}
					
					Cfg_Card_Template cfg_card_Template = null;
					cfg_card_Template = ehCacheService.getCfg_Card_Template()
							.get(request.getInPartID() + request.getInCardType());

					if (cfg_card_Template != null) 
					{
						strBin = strBin + cfg_Card_Type.getBin_suffix();
						response = debitCardIssuanceDao.debitCardIssuanceRequest(request, cfg_card_Template, strBin);
					}
					else 
					{
						response.setOutResponseCode(Constant.CMS_Constants.INVALID_CARD_TEMPLATE);
						response.setMessage("Card Template Data not configured properly");
					}
					
				}
				else if("RPLCARD".equals(request.getInFunction())) 
				{
					Cfg_Card_Template cfg_card_Template = null;
					cfg_card_Template = ehCacheService.getCfg_Card_Template()
							.get(request.getInPartID() + request.getInCardType());
					
					String binSuffix = "";
					
					if(debitCardIssuanceDao.getBinSuffixFlag(cfg_Card_Type.getBin()) > 0) 
					{
						 binSuffix = strBin +""+ (cfg_Card_Type.getBin_suffix() == null ? "" : cfg_Card_Type.getBin_suffix());
					}
					else 
					{
						 binSuffix = strBin ;
					}
					if("R".equals(cfg_Card_Type.getStrCardGenerationType())) 
					{
						request.setInNewCard(CardNumberGenerator.generate(binSuffix, 16));
					}
					else 
					{
						request.setInNewCard(debitCardIssuanceDao.getLastSequenceNo(request.getInPartID(), binSuffix));
					}
					/*
					 * String strBinSufix =
					 * ehCacheService.getCfg_Card_Type().get(strCardTypeKey).getBin_suffix();
					 * request.setInNewCard( CardNumberGenerator.generate(strBin + (strBinSufix ==
					 * null ? "" : strBinSufix), 16));
					 */
						response = debitCardIssuanceDao.debitCardIssuanceRequest(request, cfg_card_Template, null);
				}
				else if("RNWCARD".equals(request.getInFunction())) 
				{
					Cfg_Card_Template cfg_card_Template = null;
					cfg_card_Template = ehCacheService.getCfg_Card_Template()
							.get(request.getInPartID() + request.getInCardType());
					response = debitCardIssuanceDao.debitCardIssuanceRequest(request, cfg_card_Template, null);
				}

				if(cfg_Card_Type != null && cfg_Card_Type.getCard_token() == 1 && response != null) 
				{
					SchedulerModel schedulerModel = new SchedulerModel();
					schedulerModel.setCardNo(response.getOutCard());
					schedulerModel.setEncryptedCardNumber(response.getEncryptedCardNumber());
					response.setEncryptedCardNumber(null);
					schedulerService.addCardTokenTask(schedulerModel);
				}

			}
		} 
		else 
		{
			response.setOutResponseCode(Constant.CMS_Constants.INVALID_CARD_TYPE);
			response.setMessage("Invalid Card Type !!");
		}

		return response;
	}

	private boolean validateCardType(String bin) {
		return ehCacheService.getCfg_Card_Type().get(bin) == null ? true : false;
	}

	private boolean validateCardBin(String bin) {
		return ehCacheService.getCfg_Bin().get(bin) == null ? true : false;
	}

	private boolean validateFunction(DebitCardIssuanceRequest request) 
	{
		if (request.getInFunction().equals("NEWCARD") || request.getInFunction().equals("RPLCARD")
				|| request.getInFunction().equals("RNWCARD") || request.getInFunction().equals("DUPCARD") || request.getInFunction().equals("INSTANTCARD")) 
		{
			if (request.getInFunction().equals("NEWCARD") && (request.getInCard() != null))
				return false;
			else if ((request.getInFunction().equals("RPLCARD") || request.getInFunction().equals("RNWCARD")
					|| request.getInFunction().equals("DUPCARD")) && (request.getInCard() == null))
				return false;
			else
				return true;
		} 
		else
			return false;

	}

	@Override
	public CardStatusResponse cardStatusRequest(CardStatusRequest request) {
		CardStatusResponse response = new CardStatusResponse();
		if (!validateStatus(request.getStrParticipant_ID(), request.getStrStatusCode())) 
		{
			//response = debitCardIssuanceDao.cardStatusRequest(request);
			response = debitCardIssuanceDao.getCardDetailsBasedOnCardNumber(request);
		} 
		else 
		{
			response.setOutResponseCode("99");
			response.setMessage("Card Status Not Found !!");
		}
		return response;
	}

	public boolean validateStatus(String partID, String status) {
		return ehCacheService.getCfg_Status().get(status) == null ? true : false;
	}

	@Override
	public PINManagementResponse PINManagementRequest(PINManagementRequest request) {
		PINManagementResponse response = new PINManagementResponse();
		if (!request.getStrPinMailerUpdateFlag().equals("Y") && !request.getStrPinRetryFlag().equals("Y")) {
			response.setOutResponseCode("99");
			response.setMessage("No Data available to Update");
			return response;
		} else
			response = debitCardIssuanceDao.PINManagementRequest(request);
		return response;

	}

	@Override
	public NCMCServiceUpdationResponse NCMCServiceUpdation(NCMCServiceUpdationRequest request) {
		NCMCServiceUpdationResponse response = new NCMCServiceUpdationResponse();
		response = debitCardIssuanceDao.NCMCServiceUpdationRequest(request);
		return response;

	}

	@Override
	public DebitCardIssuanceResponse getTokenCard(String cardNo) {
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		Gson gson = new Gson();
		Optional<String> cardToken = debitCardIssuanceDao.getTokenCard(cardNo);
		if(cardToken != null && cardToken.isPresent()) {
			List<CardTokenModel> cardList = Arrays.asList(gson.fromJson(cardToken.get(), CardTokenModel[].class));	
			Collections.sort(cardList);
			response.setMessage("Success");
			response.setOutResponseCode("00");
			response.setOutTokenCard(cardList.get(0).getCardToken());
		}else {
			response.setMessage("Token Function Not Allowed");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
}
