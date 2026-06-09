package com.traneco.clientSearch.services;

import java.util.List;

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

public interface ClientSearchService {

	public SearchClientCardResponse searchClient(SearchClientCardRequest request);

	public DetailedClientCardResponse clientDetails(DetailedClientCardRequest request);

	public ResponseBean addressUpdate(AddressDetailsList request);

	public ResponseBean emailUpdate(EmailMaintainanceRequest request);

	public ResponseBean phoneUpdate(PhoneMaintainanceRequest request);

	public CardDetailsList getCard(String cardNo, String mbr);

	public ResponseBean updateCard(CardDetailsList reCardDetailsList);

	public List<KeyValueBean> getCardStatus(String partID);

	public ResponseBean updateCardStatus(CardStatusRequest request);

	public EnquireCardDetailsResponse getCardDetails(EnquireCardDetailsRequest request);

	public ResponseBean updatePinMailer(CardDetails card);

	public List<KeyValueBean> getNCMCService(String partID, String cardNo);

	public ResponseBean updateNCMCService(NCMCServiceUpdationRequest request);

	public List<KeyValueBean> getDocumentType();

	public int updateClient(CustomerDetails customerDetails);

	public int addCardMCC(CardTypeModel cardTypeModel);
	
	//created by ankit
	public List<CardDetails> getClearCardNo(CardDetails cardDetails);
	//created by ankit
	
	public CardAccountLinkage getCardDetails(CardAccountLinkage cardAccountLinkage);
	
	public int updateCardStatusBlock(CardAccountLinkage cardAccountLinkage);
	
	public int updateCardStatusActive(CardAccountLinkage cardAccountLinkage);

	//Added By prashant Tayde - 04 Aug 2023
	public List<CardDetails> getCardDetailsData(CardDetails cardDetails);

	public int addInstantInfo(ServiceBean serviceBean);
	
	/**
	 * Card Account Linkage Changes
	 * Date - 04-09-2023
	 * @author Jyoti S
	 */
	public List<CardTypeList> getCardTypeList();
	
	public List<CardDetailsList> getUnsoldCardList(String cardType);

	public CustomerDetails getOldClientDetails(CustomerDetails customerDetails);

	public CardTypeModel getCardMccOldDetails(CardTypeModel cardTypeModel);
}
