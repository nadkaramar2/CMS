package com.TranEco.cardManagement.cardEncDecKey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;
import com.TranEco.cardManagement.cryptservice.EncryptKeyDataService;

@RestController
public class CardEncDecController 
{
	@Autowired
	EncryptKeyDataService encryptKeyDataService;	
	
	@RequestMapping(value = "keyAddition", method = RequestMethod.POST,produces = "application/json")
	public EncryptKeyModel keyAddition(@RequestBody String value) 
	{
		EncryptKeyModel responseBean = new EncryptKeyModel();
		try 
		{
			responseBean = encryptKeyDataService.addGenetaedKey(value);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			responseBean.setMessage("Internal Error");
			responseBean.setOutResponseCode("99");
		}
		return responseBean;
	}
}
