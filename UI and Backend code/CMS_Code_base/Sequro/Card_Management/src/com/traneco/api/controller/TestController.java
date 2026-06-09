package com.traneco.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.traneco.common.util.AESEncDec;
import com.traneco.common.util.Utils;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.services.ConfigurationService;

@RestController
public class TestController 
{
	
	@Autowired
	AESEncDec aesEncDec;
	
	@Autowired
	ConfigurationService configurationService;
	
	@RequestMapping(value = "/getCardEncDec", method = RequestMethod.POST)
	public ResponseEntity<ProcessResponse> getCardTypeBasedOnParticipntId(@RequestBody CardTypeModel cardTypeModel)
	{
		ProcessResponse processWebResponse = new ProcessResponse();
		try 
		{ 
			if (cardTypeModel != null && cardTypeModel.getStrCardNumber() != null)
			{
				String maskValue = Utils.maskValue(cardTypeModel.getStrCardNumber().trim());
				cardTypeModel.setCardNumber(maskValue.trim());
				List<CardTypeModel> cardTypeModels = configurationService.getCardTypeInfo(cardTypeModel);
				processWebResponse.setCardTypeList(cardTypeModels);
				return ResponseEntity.ok(processWebResponse);
			}
			else 
			{
				processWebResponse.setCode("E0000");
				processWebResponse.setStatus("Failed");
				processWebResponse.setMessage("Card Number Not Found From AMS");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(processWebResponse);
	}
	
	
}
