package com.TranEco.cardManagement.debitCardManagement.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.debitCardManagement.model.CardStatusRequest;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusResponse;
import com.TranEco.cardManagement.debitCardManagement.model.CardToken;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceRequest;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationRequest;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationResponse;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementRequest;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementResponse;
import com.TranEco.cardManagement.debitCardManagement.services.DebitCardIssuanceServiceImpl;
import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.scheduler.service.SchedulerService;
import com.TranEco.cardManagement.utils.logger.LoggerUtil;

@RestController
public class DebitCardIssuance {
	
	@Autowired
	DebitCardIssuanceServiceImpl debitCardIssuanceService;

	@Autowired
	LoggerUtil loggerUtil;
	
	@Autowired
	@Lazy
	SchedulerService schedulerService;
	
	private String className = DebitCardIssuance.class.getName();
	
	@RequestMapping(method = RequestMethod.POST, value = "debitCardIssuance", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public DebitCardIssuanceResponse debitCardIssuanceRequest(@RequestBody DebitCardIssuanceRequest request) {
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		try 
		{
			response = debitCardIssuanceService.debitCardIssuanceRequest(request);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "cardStatus", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public CardStatusResponse cardStatus(@RequestBody CardStatusRequest request) 
	{
		CardStatusResponse response = null;
		try 
		{
			response = debitCardIssuanceService.cardStatusRequest(request);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response = new CardStatusResponse();
			response.setMessageNumber("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "pinManagement", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public PINManagementResponse pinManagement(@RequestBody PINManagementRequest request) {
		PINManagementResponse response = null;
		try 
		{
			response = debitCardIssuanceService.PINManagementRequest(request);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "NCMCServiceUpdation", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public NCMCServiceUpdationResponse NCMCServiceUpdation(@RequestBody NCMCServiceUpdationRequest request) {
		NCMCServiceUpdationResponse response = new NCMCServiceUpdationResponse();
		try 
		{
			response = debitCardIssuanceService.NCMCServiceUpdation(request);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "scheduleCardToken", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<NCMCServiceUpdationResponse> scheduleCardToken(@RequestBody SchedulerModel request) 
	{
		String methodName = "scheduleCardToken";
		NCMCServiceUpdationResponse response = new NCMCServiceUpdationResponse();
		try
		{
			if(StringUtils.isNoneBlank(request.getCardNo())) 
			{
				boolean flag = schedulerService.addCardTokenTask(request);
				if(!flag)
				{
					response.setMessage("Card Number Not Found Or Card Token flag not available");
					response.setOutResponseCode("99");
					return new ResponseEntity<NCMCServiceUpdationResponse>(response,HttpStatus.OK);
				}
			}
			else
			{
				schedulerService.scheduleTask(request);
			}
			response.setMessage("Success");
			response.setOutResponseCode("00");
		}
		catch (Exception e) 
		{
			loggerUtil.doLog(2, className, methodName, LoggerUtil.getExStackTrace(e));
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
			return new ResponseEntity<NCMCServiceUpdationResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<NCMCServiceUpdationResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "getCardToken", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DebitCardIssuanceResponse> getCardToken(@RequestBody CardToken request) {
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		try {
			return new ResponseEntity<DebitCardIssuanceResponse>(debitCardIssuanceService.getTokenCard(request.getCardNumber()), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
			return new ResponseEntity<DebitCardIssuanceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "scheduleMobileToken", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DebitCardIssuanceResponse> scheduleMobileToken(@RequestBody MobileTokenModel request) {
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		try {
			return new ResponseEntity<DebitCardIssuanceResponse>(schedulerService.scheduleMobileToken(request), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
			return new ResponseEntity<DebitCardIssuanceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "getMobileToken", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getMobileToken(@RequestParam String mobile) {
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		try {
			List<MobileTokenModel> mobileList = schedulerService.getMobileToken(mobile);
			if(mobileList != null && !mobileList.isEmpty()) {
				return new ResponseEntity<List>(mobileList, HttpStatus.OK);
			}else {
				response.setMessage("Mobile Token not found");
				response.setOutResponseCode("99");
				return new ResponseEntity<DebitCardIssuanceResponse>(response, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
			return new ResponseEntity<DebitCardIssuanceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
