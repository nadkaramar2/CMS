
package com.TranEco.cardManagement.inquiryServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardResponse;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsRequest;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsResponse;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardResponse;
import com.TranEco.cardManagement.inquiryServices.services.DetailedClientCardService;
import com.TranEco.cardManagement.inquiryServices.services.SearchCardDetailsService;
import com.TranEco.cardManagement.inquiryServices.services.SearchClientCardService;

@RestController
public class InquiryServices {

	@Autowired
	SearchClientCardService searchClientCardService;
	

	@Autowired
	DetailedClientCardService detailedClientCardService;
	
	@Autowired
	SearchCardDetailsService searchCardDetailsService;
	
	@RequestMapping(method = RequestMethod.POST, value = "searchClientCard", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public SearchClientCardResponse searchClientCard(@RequestBody SearchClientCardRequest request) {
		SearchClientCardResponse response = new SearchClientCardResponse();
		try {
			response = searchClientCardService.searchClientCard(request);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "detailedClientCard", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public DetailedClientCardResponse detailedClientCard(@RequestBody DetailedClientCardRequest request) {
		DetailedClientCardResponse response = new DetailedClientCardResponse();
		try {
			return response = detailedClientCardService.detailedClientCard(request);
		} 
		catch(EmptyResultDataAccessException er) {
			response.setOutResponseCode("01");	
			response.setMessage("Error: Could not fetch Customer Data");
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "searchCardDetails", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public EnquireCardDetailsResponse searchCardDetails(@RequestBody EnquireCardDetailsRequest request) {
		EnquireCardDetailsResponse response = new EnquireCardDetailsResponse();
		try {
			response = searchCardDetailsService.searchCardDetails(request);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
}
