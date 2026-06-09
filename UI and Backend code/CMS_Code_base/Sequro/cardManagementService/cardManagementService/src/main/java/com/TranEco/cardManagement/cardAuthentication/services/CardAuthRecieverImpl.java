package com.TranEco.cardManagement.cardAuthentication.services;

import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TranEco.cardManagement.cardAuthentication.dao.CardAuthenticationDaoImpl;
import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationInternalRequest;
import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationRequest;
import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class CardAuthRecieverImpl implements CardAuthReciever {

	@Autowired
	EhCacheServiceImpl ehCacheService;

	@Autowired
	CardAuthenticationDaoImpl cardAuthenticationDao;

	// currency code,branch

	@Override
	public String authenticateCard(CardAuthenticationInternalRequest request) throws IOException 
	{
		if (validatePosEntryMode(request.getStrPOSEntryMode())) 
		{
			return Constant.CMS_Constants.INVALID_ACCOUNT_TYPE;
		} 
		else 
		{
			return cardAuthenticationDao.authenticateCard(request);
		}
	}

	/*
	 * @Override public String parseTransaction(CardAuthenticationRequest request)
	 * throws IOException {
	 * 
	 * if (request.getRequestType().equals("0200") ||
	 * request.getRequestType().equals("0420")) { String strTransType; if
	 * (request.getProcessingCode() != null) { strTransType =
	 * getTransactionType(request.getProcessingCode(), request.getFunctionCode()); }
	 * 
	 * } else { return Constant.Card_Authentication_Constants.INVALID_REQUEST_TYPE;
	 * }
	 * 
	 * 
	 * if(validatePosEntryMode(request.getStrPOSEntryMode())) { return
	 * Constant.CMS_Constants.INVALID_ACCOUNT_TYPE; }else { return
	 * cardAuthenticationDao.authenticateCard(request); } }
	 */

	public boolean validatePosEntryMode(String strPOSEntryMode) {
		return ehCacheService.getCfg_POS_Entry_Mode().get(strPOSEntryMode) == null ? true : false;
	}

	public String getTransactionType(String strProcessingCode, String strOfflineIndicator) {
		String strISOTransType = strProcessingCode.substring(0, 1);

		if (strISOTransType.equals("00") && strOfflineIndicator.equals("Y"))
			return "10"; // offine debit
		else if (strISOTransType.equals("00") && strOfflineIndicator.equals("N"))
			return "01"; // purchase
		else if (strISOTransType.equals("09"))
			return "04"; // purchase with cashback
		else if (strISOTransType.equals("01"))
			return "06"; //
		else if (strISOTransType.equals("28"))
			return "05"; //
		else if (strISOTransType.equals("83"))
			return "07"; // Service Creation
		else if (strISOTransType.equals("31"))
			return "10"; // Balance Inquiry
		else
			return "00";
	}

	public int logTransaction(HashMap<String, String> TransactionData, String strResponseCode) throws IOException {
		return cardAuthenticationDao.logTransactioninDB(TransactionData, strResponseCode);
	}

	public HashMap<String, String> parseTransaction(CardAuthenticationRequest request) throws IOException {

		HashMap<String, String> TransactionData = new HashMap();
		if (request.getRequestType() != null) 
		{
			TransactionData.put("REQ.REQUEST_TYPE", request.getRequestType());
		}
		if (request.getCardNumber() != null) 
		{
			TransactionData.put("REQ.CARD_NUMBER", request.getCardNumber());
		}
		if (request.getProcessingCode() != null) 
		{
			String strOfflineDebitIndicator = "N";
			if (request.getFunctionCode() != null)
				if (request.getFunctionCode().equals("100"))
					strOfflineDebitIndicator = "Y";
			String strTransType = getTransactionType(request.getProcessingCode(), strOfflineDebitIndicator);
			TransactionData.put("REQ.TRANSACTION_TYPE", strTransType);
			String strFmAccountType = request.getProcessingCode().substring(2, 3);
			TransactionData.put("REQ.FROM_ACCOUNT_TYPE", strFmAccountType);
			String strToAccountType = request.getProcessingCode().substring(4, 5);
			TransactionData.put("REQ.TO_ACCOUNT_TYPE", strToAccountType);
		}
		if (request.getTransactionAmount() != null) 
		{
			TransactionData.put("REQ.TRANSACTION_AMOUNT", request.getTransactionAmount());
		}
		if (request.getAcquirerTransactionDate() != null) 
		{
			TransactionData.put("REQ.ACQ_TRANSACTION_DATE", request.getAcquirerTransactionDate());
		}
		if (request.getAcquirerTransactionTime() != null) 
		{
			TransactionData.put("REQ.ACQ_TRANSACTION_TIME", request.getAcquirerTransactionTime());
		}
		if (request.getTransactionReferenceNo() != null) 
		{
			TransactionData.put("REQ.TRANSACTION_REF_NUMBER", request.getTransactionReferenceNo());
		}
		if (request.getMCCCode() != null) 
		{
			TransactionData.put("REQ.MCC_CODE", request.getMCCCode());
		}
		if (request.getPOSEntryMode() != null) 
		{
			TransactionData.put("REQ.POSENTRY_MODE", request.getPOSEntryMode());
		}
		if (request.getCardSequenceNo() != null) 
		{
			TransactionData.put("REQ.CARD_SEQUENC_NO", request.getCardSequenceNo());
		}
		if (request.getAcquirerID() != null) 
		{
			TransactionData.put("REQ.ACQUIRER_ID", request.getAcquirerID());
		}
		if (request.getIssuerID() != null) 
		{
			TransactionData.put("REQ.ISSUER_ID", request.getIssuerID());
		}
		if (request.getFunctionCode() != null) 
		{
			TransactionData.put("REQ.FUNCTION_CODE", request.getFunctionCode());
		}
		if (request.getEMVData() != null)
			TransactionData.put("REQ.EMV_DATA", request.getEMVData());
		if (request.getCardDataInputDetails() != null)
			TransactionData.put("REQ.CARDDATA_INPUT_DETAILS", request.getCardDataInputDetails());
		if (request.getTerminalID() != null)
			TransactionData.put("REQ.TERMINAL_ID", request.getTerminalID());
		if (request.getMerchantID() != null)
			TransactionData.put("REQ.MERCHANT_ID", request.getMerchantID());
		if (request.getMerchantData() != null)
			TransactionData.put("REQ.MERCHANT_DATA", request.getMerchantData());
		if (request.getAdditionalAmount() != null)
			TransactionData.put("REQ.ADDITIONAL_AMOUNT", request.getAdditionalAmount());
		if (request.getReasonCode() != null)
			TransactionData.put("REQ.REASON_CODE", request.getReasonCode());
		if (request.getApprovalNo() != null)
			TransactionData.put("REQ.APPROVAL_NO", request.getApprovalNo());
		if (request.getSettlementCurrencyCode() != null)
			TransactionData.put("REQ.SETTLEMENT_CURRENCY_CODE", request.getSettlementCurrencyCode());
		if (request.getSettlementDate() != null)
			TransactionData.put("REQ.SETTLEMENT_DATE", request.getSettlementDate());
		if (request.getFeeDetails() != null) 
		{
			if (request.getFeeDetails().getFeeDebitCreditInd() != null)
				TransactionData.put("REQ.FEE_DEBIT_CREDIT_INDICATOR", request.getFeeDetails().getFeeDebitCreditInd());
			if (request.getFeeDetails().getFeeCurrencyCode() != null)
				TransactionData.put("REQ.FEE_CURRENCY_CODE", request.getFeeDetails().getFeeCurrencyCode());
			if (request.getFeeDetails().getFeeAmount() != null)
				TransactionData.put("REQ.FEE_AMOUNT", request.getFeeDetails().getFeeAmount());
			if (request.getFeeDetails().getFeeTpCd() != null)
				TransactionData.put("REQ.FEE_TIP_CODE", request.getFeeDetails().getFeeTpCd());
		}
		return TransactionData;
	}

}
