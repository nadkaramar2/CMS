package com.TranEco.cardManagement.limitUpdate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateRequest;
import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateResponse;
import com.TranEco.cardManagement.limitUpdate.services.LimitUpdateServiceImpl;

@RestController
public class LimitUpdate {

	@Autowired
	LimitUpdateServiceImpl limitUpdateService;
	
	@RequestMapping(method = RequestMethod.POST, value = "limitUpdate", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public LimitUpdateResponse LimitUpdateAddition(@RequestBody LimitUpdateRequest request) {
		LimitUpdateResponse response = new LimitUpdateResponse();
		try {
			response = limitUpdateService.updateLimit(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
}
