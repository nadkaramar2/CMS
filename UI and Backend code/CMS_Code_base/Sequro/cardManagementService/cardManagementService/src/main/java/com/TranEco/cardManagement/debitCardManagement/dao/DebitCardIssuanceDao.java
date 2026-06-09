package com.TranEco.cardManagement.debitCardManagement.dao;

import java.util.Optional;

import com.TranEco.cardManagement.debitCardManagement.model.CardStatusRequest;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusResponse;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceRequest;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationRequest;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationResponse;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementRequest;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementResponse;
import com.TranEco.cardManagement.model.Cfg_Card_Template;

public interface DebitCardIssuanceDao {
	
	public DebitCardIssuanceResponse debitCardIssuanceRequest(DebitCardIssuanceRequest request,  Cfg_Card_Template cfg_Card_template, String strBin);
	public CardStatusResponse cardStatusRequest(CardStatusRequest request);
	public PINManagementResponse PINManagementRequest(PINManagementRequest request);
	public NCMCServiceUpdationResponse NCMCServiceUpdationRequest(NCMCServiceUpdationRequest request);
	public int getBinSuffixFlag(String bin);
	public String getLastSequenceNo(String strParticipantID, String strCardBin);
	public Optional<String> getTokenCard(String cardNo);
	public CardStatusResponse getCardDetailsBasedOnCardNumber(CardStatusRequest request);
}
