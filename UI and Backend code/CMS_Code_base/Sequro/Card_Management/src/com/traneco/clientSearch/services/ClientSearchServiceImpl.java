package com.traneco.clientSearch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traneco.clientSearch.dao.CardDetailsDao;
import com.traneco.clientSearch.dao.CardTypeDao;
import com.traneco.clientSearch.model.AddressDetailsList;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardStatusRequest;
import com.traneco.clientSearch.model.CardTypeList;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DetailedClientCardRequest;
import com.traneco.clientSearch.model.DetailedClientCardResponse;
import com.traneco.clientSearch.model.EmailMaintainanceRequest;
import com.traneco.clientSearch.model.EnquireCardDetailsRequest;
import com.traneco.clientSearch.model.EnquireCardDetailsResponse;
import com.traneco.clientSearch.model.NCMCServiceUpdationRequest;
import com.traneco.clientSearch.model.PhoneMaintainanceRequest;
import com.traneco.clientSearch.model.SearchClientCardRequest;
import com.traneco.clientSearch.model.SearchClientCardResponse;
import com.traneco.common.KeyValueBean;
import com.traneco.common.ResponseBean;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ServiceBean;

@Service
public class ClientSearchServiceImpl implements ClientSearchService 
{
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CardDetailsDao cardDetailsDao;

	@Autowired
	Environment env;
	
	@Autowired
	CardTypeDao cardTypeDao;
	
	
	@Override
	public SearchClientCardResponse searchClient(SearchClientCardRequest request) {
		ResponseEntity<SearchClientCardResponse> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.searchClientCard"), request,
				SearchClientCardResponse.class);
		return response.getBody();
	}

	@Override
	public DetailedClientCardResponse clientDetails(DetailedClientCardRequest request) 
	{
		ResponseEntity<DetailedClientCardResponse> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.detailedClientCard"), request,
				DetailedClientCardResponse.class);
		return response.getBody();
	}

	@Override
	public ResponseBean addressUpdate(AddressDetailsList request) {
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.addressMaintainance"), request,
				ResponseBean.class);
		return response.getBody();
	}

	@Override
	public ResponseBean emailUpdate(EmailMaintainanceRequest request) {
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.emailMaintainance"), request, ResponseBean.class);
		return response.getBody();
	}

	@Override
	public ResponseBean phoneUpdate(PhoneMaintainanceRequest request) {
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.phoneMaintainance"), request, ResponseBean.class);
		return response.getBody();
	}

	@Override
	public CardDetailsList getCard(String cardNo, String mbr) {
		return cardDetailsDao.getCard(cardNo, mbr);
	}

	@Override
	public ResponseBean updateCard(CardDetailsList request) {
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.limitUpdate"), request, ResponseBean.class);
		return response.getBody();
	}

	@Override
	public List<KeyValueBean> getCardStatus(String partID) {
		return cardDetailsDao.getCardStatus(partID);
	}

	@Override
	public ResponseBean updateCardStatus(CardStatusRequest request) {
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.cardStatus"), request, ResponseBean.class);
		return response.getBody();
	}

	@Override
	public EnquireCardDetailsResponse getCardDetails(EnquireCardDetailsRequest request) {
		ResponseEntity<EnquireCardDetailsResponse> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.searchCardDetails"), request,
				EnquireCardDetailsResponse.class);
		return response.getBody();
	}

	@Override
	public ResponseBean updatePinMailer(CardDetails card) {
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.pinManagement"), card, ResponseBean.class);
		return response.getBody();
	}

	@Override
	public List<KeyValueBean> getNCMCService(String partID, String cardNo) {
		return cardDetailsDao.getNCMCService(partID, cardNo);
	}

	@Override
	public ResponseBean updateNCMCService(NCMCServiceUpdationRequest request) 
	{
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.NCMCServiceUpdation"), request,
				ResponseBean.class);
		return response.getBody();
	}

	@Override
	public List<KeyValueBean> getDocumentType() {
		return cardDetailsDao.getDocumentType();
	}

	@Override
	public int updateClient(CustomerDetails customerDetails) {
		return cardDetailsDao.updateClient(customerDetails);
	}

	@Override
	public int addCardMCC(CardTypeModel cardTypeModel) {
		return cardDetailsDao.addCardMCC(cardTypeModel);
	}

	//created by ankit
	@Override
	public List<CardDetails> getClearCardNo(CardDetails cardDetails) {
		return cardDetailsDao.getClearCard(cardDetails);
	}
	//created by ankit
	
	@Override
	public CardAccountLinkage getCardDetails(CardAccountLinkage cardAccountLinkage) {
		return cardDetailsDao.getLinkageCardDetailsOnCustId(cardAccountLinkage);
	}
	
	@Override
	public int updateCardStatusBlock(CardAccountLinkage cardAccountLinkage) {
		 cardDetailsDao.updateCardStatusBlock(cardAccountLinkage);
		 return Integer.valueOf(cardAccountLinkage.getStrID());
	}
	@Override
	public int updateCardStatusActive(CardAccountLinkage cardAccountLinkage) {
		 cardDetailsDao.updateCardStatusActive(cardAccountLinkage);
		 return Integer.valueOf(cardAccountLinkage.getStrID());
	}
	
	//Added by prashant Tayde 04 Aug 2023
	
	@Override
	public List<CardDetails> getCardDetailsData(CardDetails cardDetails) {
		return cardDetailsDao.getCardDetailsData(cardDetails);
	}

	@Override
	public int addInstantInfo(ServiceBean serviceBean) {
		return cardDetailsDao.addInstantInfo(serviceBean);
		//return Integer.valueOf(serviceBean.getInUserID());
	}
	
	@Override
	public List<CardTypeList> getCardTypeList() {
		return cardTypeDao.getCardTypeList();
	}

	@Override
	public List<CardDetailsList> getUnsoldCardList(String cardType) {
		return cardDetailsDao.getUnsoldCardList(cardType);
	}

	@Override
	public CustomerDetails getOldClientDetails(CustomerDetails customerDetails) 
	{
		return cardDetailsDao.getOldClientDetails(customerDetails);
	}

	@Override
	public CardTypeModel getCardMccOldDetails(CardTypeModel cardTypeModel) {
		return cardDetailsDao.getCardMccOldDetails(cardTypeModel);
	}
	
	
}
