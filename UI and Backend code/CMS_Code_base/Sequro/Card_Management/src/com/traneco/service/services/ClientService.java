package com.traneco.service.services;

import java.util.List;

import com.traneco.clientSearch.model.DetailedClientCardResponse;
import com.traneco.clientSearch.model.DocumentMaintainanceRequest;
import com.traneco.clientSearch.model.DocumentMaintainanceResponse;
import com.traneco.common.KeyValueBean;
import com.traneco.common.ResponseBean;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountRequest;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.Client;
import com.traneco.service.model.CreditCardCustomerAccountCreationModel;
import com.traneco.service.model.CustomerAdditionResponse;
import com.traneco.service.model.EmbossCardList;
import com.traneco.service.model.EmbossRequest;
import com.traneco.service.model.InventoryManagement;
import com.traneco.service.model.PinPrintingModel;
import com.traneco.service.model.ServiceBean;
import com.traneco.service.model.TransactionIdTable;
import com.traneco.service.model.TransactionView;

public interface ClientService {

	public CustomerAdditionResponse addClient(Client client);

	public ResponseBean addService(ServiceBean serviceBean);

	public ResponseBean addAccount(AccountRequest request);

	public void requestEmboss(EmbossRequest embossRequest);

	public List<EmbossRequest> getEmbossFileList();

	public List<KeyValueBean> getCardList(String cid);

	public DocumentMaintainanceResponse addDocument(DocumentMaintainanceRequest documentMaintainanceRequest);

	public List<KeyValueBean> getCurrencyList();

	public List<KeyValueBean> getParticipantConfigKey();

	public void addAccountToFundomo(DetailedClientCardResponse detailedClientCardResponse, String accNo, String cardNo);

	public List<ServiceBean> getCard(String cardNo);

	public int updateCustomerID(String custID, String card);

	public List<EmbossCardList> getEmbossCardList();

	public String getEndpointURL(String cardNO);

	public List<PinPrintingModel> getPendingPinPrinting();

	public int updatePinMailer(String cardType);

	public int readTextFile(String filePath);

	public int rollBackData(String id, String card_no);

	public List<BulkUploadData> getUploadDataList();

	public List<KeyValueBean> getBatchData(String batchID);

	public void processBatchData(List<KeyValueBean> data);

	public List<TransactionView> getTxnDetails();

	public List<InventoryManagement> getInventroy();

	public List<MobileTokenModel> getMobileToken(String mobile);
	public List<CardAccountLinkage> getCardAccountLinkage(String accountType,String accountNumber);
	public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType,String cardNumber);

	/**
	 * Account Statement Changes by Jyoti Shirahatti
	 */
	public List<AccountStatement> getAccountStatementByDate(String fromDate,String toDate);

	public List<AccountStatement> getAccountStatement(String accountType,String accountNumber,String fromDate,String toDate);
	
	public List<AccountCreation> getAccountIssueData(String accountIssuedFlag);

	/**
	 * GL ACCOUNT CREATION CODE by Jyoti Shirahatti
	 * 
	 * @param glAccountType
	 * @param glAccountDescription
	 * @param glAccountNumber
	 * @param createdDate
	 * @return
	 */
	public int saveGLAccountList(String glAccountType, String glAccountDescription, String glAccountNumber,
			String createdDate);
	
	public List<GLAccountCreation> getGLAccountTypeList();

	List<GlAccountTypeWiseStatementMaster> getGlAccountStatementByDate(String fromDate, String toDate);

	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatement(String accountType, String accountNumber,
			String fromDate, String toDate);		
	//TransactionDetails
	public int saveTransactionIdDetails(TransactionIdTable transactionIdTable);
	
	public int updateTransactionIdDetails(TransactionIdTable transactionIdTable);
	
	public List<TransactionIdTable> getTransactionIdList(String year,String julianDate);

	public CreditCardCustomerAccountCreationModel generateCardAccount(CreditCardCustomerAccountCreationModel creditCardCustomerAccountCreation);

}
