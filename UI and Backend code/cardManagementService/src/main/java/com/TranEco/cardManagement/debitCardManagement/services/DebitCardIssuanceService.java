package com.TranEco.cardManagement.debitCardManagement.services;

import com.TranEco.cardManagement.debitCardManagement.model.CardStatusRequest;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusResponse;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceRequest;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationRequest;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationResponse;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementRequest;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementResponse;

public interface DebitCardIssuanceService {

	public DebitCardIssuanceResponse debitCardIssuanceRequest(DebitCardIssuanceRequest request);
	public CardStatusResponse cardStatusRequest(CardStatusRequest request);
	public PINManagementResponse PINManagementRequest(PINManagementRequest request);
	public NCMCServiceUpdationResponse NCMCServiceUpdation(NCMCServiceUpdationRequest request);
	public DebitCardIssuanceResponse getTokenCard(String cardNo);
}
