package com.traneco.service.dao;

import java.util.List;

import com.traneco.common.KeyValueBean;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.EmbossCardList;
import com.traneco.service.model.InventoryManagement;
import com.traneco.service.model.PinPrintingModel;
import com.traneco.service.model.ServiceBean;
import com.traneco.service.model.TransactionIdTable;
import com.traneco.service.model.TransactionView;

public interface ServiceDao {
	public List<KeyValueBean> getCardList(String cid);
	public List<KeyValueBean> getCurrencyList();
	public List<KeyValueBean> getParticipantConfigKey();
	public List<ServiceBean> getCard(String cardNo);
	public int updateCustomerID(String custID,String card);
	public List<EmbossCardList> getEmbossCardList();
	public String getClearCard(String cardNo);
	public String getEndpointURL(String cardNO);
	public List<PinPrintingModel> getPendingPinPrinting();
	public int updatePinMailer(String cardType);
	public int rollBackData(String id, String card_no);
	public int insertBatch(List<String> dataList);
	public List<BulkUploadData> getUploadDataList();
	public List<KeyValueBean> getBatchData(String batchID);
	public void updateBatchData(String id,String msg);
	public List<TransactionView> getTxnDetails();
	public List<InventoryManagement> getInventroy();
	public String getMobileToken(String mobile);
	public List<CardAccountLinkage> getCardAccountLinkage(String accountType,String accountNumber);
	public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType,String cardNumber);
	
	/**
	 * Account Statement changes by Jyoti Shirahatti
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<AccountStatement> getAccountStatementByDate(String fromDate,String toDate);
	public List<AccountStatement> getAccountStatement(String accountType,String accountNumber,String fromDate,String toDate);

	public List<AccountCreation> getAccountIssueData(String accountIssuedFlag);
	
	
	/**
	 * GL ACCOUNT CREATION CODE by Jyoti Shirahatti
	 * @param glAccountType
	 * @param glAccountDescription
	 * @param glAccountNumber
	 * @param createdDate
	 * @return
	 */
	 
	public int saveGLAccountList(String glAccountType,String glAccountDescription,String glAccountNumber,String createdDate);
	
	public List<GLAccountCreation> getGLAccountTypeList();
	
	
	public int saveTransactionIdDetails(TransactionIdTable transactionIdDetails);
	
	public List<TransactionIdTable> getTransactionIdTableList(String year,String julianDate);
	
	public int updateTransactionIdDetails(TransactionIdTable transactionIdDetails);
	 
	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatementByDate(String fromDate, String toDate);
	
	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatement(String accountType, String accountNumber,
			String fromDate, String toDate);

}
