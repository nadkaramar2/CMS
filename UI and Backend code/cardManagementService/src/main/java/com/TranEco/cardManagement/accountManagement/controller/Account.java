package com.TranEco.cardManagement.accountManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.accountManagement.model.AccountRequest;
import com.TranEco.cardManagement.accountManagement.model.AccountResponse;
import com.TranEco.cardManagement.accountManagement.services.AccountServiceImpl;

@RestController
public class Account 
{

	@Autowired
	AccountServiceImpl accountService;

	@RequestMapping(method = RequestMethod.POST, value = "accountManagement", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public AccountResponse accountAddition(@RequestBody AccountRequest request) 
	{
		AccountResponse response = new AccountResponse();
		try 
		{
			response = accountService.accountManagement(request);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
}
