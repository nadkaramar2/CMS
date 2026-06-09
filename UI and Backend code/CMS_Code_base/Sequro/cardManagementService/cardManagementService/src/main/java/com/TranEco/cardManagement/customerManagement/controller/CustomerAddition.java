package com.TranEco.cardManagement.customerManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceResponse;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionRequest;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionResponse;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceResponse;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceResponse;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceResponse;
import com.TranEco.cardManagement.customerManagement.services.AddressMaintainanceServiceImpl;
import com.TranEco.cardManagement.customerManagement.services.CustomerAdditionServiceImpl;
import com.TranEco.cardManagement.customerManagement.services.DocumentMaintainanceServiceImpl;
import com.TranEco.cardManagement.customerManagement.services.EmailMaintainanceServiceImpl;
import com.TranEco.cardManagement.customerManagement.services.PhoneMaintainanceServiceImpl;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

import io.swagger.annotations.Api;

@Api("Customer Operations")
@RestController
public class CustomerAddition {
	
	@Autowired
	CustomerAdditionServiceImpl customerAdditionService;
	
	@Autowired
	AddressMaintainanceServiceImpl addressMaintainanceService;
	
	@Autowired
	PhoneMaintainanceServiceImpl phoneMaintainanceService;
	
	@Autowired
	EmailMaintainanceServiceImpl emailMaintainanceService;
	
	@Autowired
	DocumentMaintainanceServiceImpl documentMaintainanceService;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@RequestMapping(method = RequestMethod.POST, value = "customerAddition", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public CustomerAdditionResponse customerAddition(@RequestBody CustomerAdditionRequest request) {
		CustomerAdditionResponse response = new CustomerAdditionResponse();
		try {
			response = customerAdditionService.addCustomer(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "addressMaintainance", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public AddressMaintainanceResponse addressMaintainance(@RequestBody AddressMaintainanceRequest request) {
		AddressMaintainanceResponse response = new AddressMaintainanceResponse();
		try {
			response = addressMaintainanceService.maintainAddress(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "phoneMaintainance", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public PhoneMaintainanceResponse phoneMaintainance(@RequestBody PhoneMaintainanceRequest request) {
		PhoneMaintainanceResponse response = new PhoneMaintainanceResponse();
		try {
			response = phoneMaintainanceService.maintainPhone(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}

	

	@RequestMapping(method = RequestMethod.POST, value = "emailMaintainance", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public EmailMaintainanceResponse emailMaintainance(@RequestBody EmailMaintainanceRequest request) {
		EmailMaintainanceResponse response = new EmailMaintainanceResponse();
		try {
			response = emailMaintainanceService.maintainEmail(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "documentMaintainance", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public DocumentMaintainanceResponse documentMaintainance(@RequestBody DocumentMaintainanceRequest request) {
		DocumentMaintainanceResponse response = new DocumentMaintainanceResponse();
		try {
			response = documentMaintainanceService.maintainDocument(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.GET,value = "refreshCache")
	public String refreshCache() throws Throwable {
		try 
		{
			ehCacheService.refreshCache();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
}
