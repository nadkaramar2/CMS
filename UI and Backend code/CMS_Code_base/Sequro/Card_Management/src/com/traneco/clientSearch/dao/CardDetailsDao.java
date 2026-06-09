package com.traneco.clientSearch.dao;

import java.util.List;

import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.common.KeyValueBean;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ServiceBean;

public interface CardDetailsDao {
	
	public CardDetailsList getCard(String cardNo, String mbr);
	public List<KeyValueBean> getCardStatus(String partID);
	public List<KeyValueBean> getNCMCService(String partID,String cardNo);
	public List<KeyValueBean> getDocumentType();
	public int updateClient(CustomerDetails customerDetails);
	public int addCardMCC(CardTypeModel cardTypeModel);
	
	//Created by ankit
	public List<CardDetails> getClearCard(CardDetails cardDetails);
	//Created by ankit
	
	public int updateCardStatus(CardDetails cardDetails) ;
	
	public CardAccountLinkage getLinkageCardDetailsOnCustId(CardAccountLinkage cardAccountLinkage) ;
	
	public int updateCardStatusActive(CardAccountLinkage cardAccountLinkage);
	
	public int updateCardStatusBlock(CardAccountLinkage cardAccountLinkage);
	
	
	public List<CardDetails> getCardDetailsData(CardDetails cardDetails);
	public int addInstantInfo(ServiceBean serviceBean);
	
	public List<CardDetailsList> getUnsoldCardList(String cardType);
	
	//Added by prashangt T for Audit
	public CustomerDetails getOldClientDetails(CustomerDetails customerDetails);
	public CardTypeModel getCardMccOldDetails(CardTypeModel cardTypeModel);
}
