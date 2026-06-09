package com.traneco.service.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.clientSearch.model.AddressDetailsList;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DocumentDetailsList;
import com.traneco.clientSearch.model.EmailDetailsList;
import com.traneco.clientSearch.model.PhoneDetailsList;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.util.AESEncDec;
import com.traneco.common.util.Utils;
import com.traneco.configuration.model.CardPlasticModel;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.configuration.services.ConfigurationService;
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

@Repository
public class ServiceDaoImpl implements ServiceDao {

	@Autowired
	JdbcTemplate jdbcCMSTemplate;

	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	AESEncDec aesEncDec;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	ConfigurationService configurationService;
	

	@Override
	public List<KeyValueBean> getCardList(String cid) 
	{
		List<KeyValueBean> cardList = jdbcCMSTemplate.query(QueryConstant.GET_CARD_LIST,
				new Object[] { sessionDTO.getParticipantid(), cid },
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return cardList;
	}

	@Override
	public List<KeyValueBean> getCurrencyList() 
	{
		List<KeyValueBean> currencyList = jdbcCMSTemplate.query(QueryConstant.GET_CURRENCY_CODE,
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return currencyList;
	}

	@Override
	public List<KeyValueBean> getParticipantConfigKey() 
	{
		List<KeyValueBean> keyList = jdbcCMSTemplate.query(QueryConstant.GET_PART_CONFIG_KEY,
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return keyList;
	}

	@Override
	public List<ServiceBean> getCard(String cardNo) 
	{
		try 
		{
			List<ServiceBean> cardList = jdbcCMSTemplate.query(QueryConstant.GET_CARD, new Object[] { cardNo },
					new BeanPropertyRowMapper<ServiceBean>(ServiceBean.class));
			
				cardList.get(0).setInCard(aesEncDec.decrypt(cardList.get(0).getInCard()));
				return cardList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCustomerID(String custID, String card) 
	{
		try 
		{
			CardDetails cardDetails = new CardDetails();
			cardDetails.setStrTokenCard(card);
			cardDetails = configurationService.getOldCustomerIdOnTokencard(cardDetails);
			if(cardDetails.getStrCustomerId() != null)
			{
				CardDetails cardDetails1 = new CardDetails();
				cardDetails1.setStrCustomerId(custID);
				configurationService.updateAuditData(cardDetails1, cardDetails, "card_details");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return jdbcCMSTemplate.update(QueryConstant.UPDATE_CARD, new Object[] { custID, card });
	}

	@Override
	public List<EmbossCardList> getEmbossCardList() 
	{
		List<EmbossCardList> cardList = jdbcCMSTemplate.query(QueryConstant.GET_EMBOSS_CARD,
				new BeanPropertyRowMapper<EmbossCardList>(EmbossCardList.class));
		return cardList;
	}

	@Override
	public String getClearCard(String cardNo) 
	{
		try 
		{
			String card = (String) jdbcCMSTemplate.queryForObject(QueryConstant.GET_CLEAR_CARD, new Object[] { cardNo },
					String.class);
			String ccard=aesEncDec.decrypt(card);
			return ccard;
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getEndpointURL(String cardNO) {
		try {
			String url = (String) jdbcCMSTemplate.queryForObject(QueryConstant.GET_ENDPOINT_URL,
					new Object[] { cardNO }, String.class);
			return url;
		} 
		catch (EmptyResultDataAccessException e) 
		{
			return null;
		}
	}

	@Override
	public List<PinPrintingModel> getPendingPinPrinting() 
	{
		List<PinPrintingModel> pinList = jdbcCMSTemplate.query(QueryConstant.GET_Pinprinting_List,
				new Object[] { sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<PinPrintingModel>(PinPrintingModel.class));
		return pinList;
	}

	@Override
	public int updatePinMailer(String cardType) 
	{
		try
		{
			CardPlasticModel cardPlasticModel = new CardPlasticModel();
			cardPlasticModel.setStrCardType(cardType);
			cardPlasticModel.setStrSelectID(sessionDTO.getParticipantid());
			cardPlasticModel = configurationService.getOldCardPlasticForUpdate(cardPlasticModel);
			if (cardPlasticModel != null)
			{
				CardPlasticModel cardPlasticModel2 = new CardPlasticModel();
				cardPlasticModel2.setPinMailerFlag("N");
				cardPlasticModel2.setPinMailerIssueDate(new Date());
				configurationService.updateAuditData(cardPlasticModel2, cardPlasticModel, "card_plastic_details");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = jdbcCMSTemplate.update(QueryConstant.UPDATE_PIN_PRINTING,
				new Object[] { "N", cardType, sessionDTO.getParticipantid(), sessionDTO.getParticipantid() });
		
		return count;
	}

	@Override
	public int rollBackData(String id, String card_no) 
	{
		try 
		{
			CustomerDetails customerDetails = new CustomerDetails();
			customerDetails.setId(id);
			customerDetails = configurationService.getOldCustomerDetailsOnId(customerDetails);
			if (customerDetails != null)
			{
				configurationService.deletedAuditRecords(customerDetails, "customer_details");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = jdbcCMSTemplate.update(QueryConstant.DELETE_CUST, new Object[] { id });
		try 
		{
			AddressDetailsList addressDetails = new AddressDetailsList();
			addressDetails.setStrCustomerID(id);
			addressDetails = configurationService.getOldAddressDetailsBasedOnCustId(addressDetails);
			if (addressDetails != null)
			{
				configurationService.deletedAuditRecords(addressDetails, "customer_address");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_ADDRESS, new Object[] { id });
		
		try 
		{
			PhoneDetailsList phoneDetails = new PhoneDetailsList();
			phoneDetails.setStrCustomerID(id);
			phoneDetails = configurationService.getOldPhoneDetailsData(phoneDetails);
			if (phoneDetails != null)
			{
				configurationService.deletedAuditRecords(phoneDetails, "customer_contact");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_CONTACT, new Object[] { id });
		
		try 
		{
			DocumentDetailsList documentDetailsList = new DocumentDetailsList();
			documentDetailsList.setStrCustomerId(id);
			
			documentDetailsList = configurationService.getDocumentDetailsList(documentDetailsList);
			if (documentDetailsList != null)
			{
				configurationService.deletedAuditRecords(documentDetailsList, "customer_document");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_DOCUMENT, new Object[] { id });
		
		try 
		{
			EmailDetailsList emailDetailsList = new EmailDetailsList();
			emailDetailsList.setStrCustomerId(id);
			emailDetailsList = configurationService.getEmailDetailsList(emailDetailsList);
			
			if (emailDetailsList != null)
			{
				configurationService.deletedAuditRecords(emailDetailsList, "customer_email");
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_EMAIL, new Object[] { id });
		
		try 
		{
			CardDetails cardDetails = new CardDetails();
			cardDetails.setStrTokenCard(card_no);
			cardDetails = configurationService.getOldCardDetails(cardDetails);
			
			if (cardDetails != null)
			{
				configurationService.deletedAuditRecords(cardDetails, "card_details");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_CARD, new Object[] { card_no });
		
		try 
		{
			CardDetails cardPlasticDetails = new CardDetails();
		    cardPlasticDetails.setStrTokenCard(card_no);
		  
		  cardPlasticDetails = configurationService.getOldCardPlasticDetailsData(cardPlasticDetails);
		  
		  if (cardPlasticDetails != null)
		  {
			  configurationService.deletedAuditRecords(cardPlasticDetails, "card_plastic_details");
		  }
		  
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_CARD_PLST, new Object[] { card_no });
		
		try 
		{
			CardAccountLinkage cardAccountLinkage = new CardAccountLinkage();
			cardAccountLinkage.setStrCardNumber(card_no);
			
			cardAccountLinkage = configurationService.getOldCardAccountLinkageDetails(cardAccountLinkage);
			 if (cardAccountLinkage != null)
			 {
				 configurationService.deletedAuditRecords(cardAccountLinkage, "card_account_linkage");
			 }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		count += jdbcCMSTemplate.update(QueryConstant.DELETE_ACCOUNT, new Object[] { card_no });
		
		return count;
	}

	@Override
	public int insertBatch(List<String> dataList) {
		jdbcCMSTemplate.batchUpdate(QueryConstant.INSERT_BULK_DATA, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, "BATCH" + Utils.getSysDate());
				ps.setString(2, dataList.get(i));
				ps.setString(3, "Pending");
				ps.setString(4, "" + sessionDTO.getUserID());
				ps.setString(5, sessionDTO.getParticipantid());
			}

			@Override
			public int getBatchSize() {
				return dataList.size();
			}
		});
		return dataList.size();
	}

	@Override
	public List<BulkUploadData> getUploadDataList() {
		List<BulkUploadData> bulkList = jdbcCMSTemplate.query(QueryConstant.GET_BULK_DATA,
				new BeanPropertyRowMapper<BulkUploadData>(BulkUploadData.class));
		return bulkList;
	}

	@Override
	public List<KeyValueBean> getBatchData(String batchID) {
		List<KeyValueBean> batchList = jdbcCMSTemplate.query(QueryConstant.GET_BATCH_ID_DATA,
				new Object[] { batchID, sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return batchList;
	}

	@Override
	public void updateBatchData(String id, String msg) 
	{
		try 
		{
			BulkUploadData bulkUploadData = new BulkUploadData();
			bulkUploadData.setId(id);
			bulkUploadData = configurationService.getOldBulkUploadData(bulkUploadData);
			if (bulkUploadData != null)
			{
				BulkUploadData bulkUpload1 = new BulkUploadData();
				bulkUpload1.setStatus(msg);
				bulkUpload1.setModifiedBy(String.valueOf(sessionDTO.getUserID()));
				bulkUpload1.setModifiedDate(new Date());
				configurationService.updateAuditData(bulkUpload1, bulkUploadData, "bulk_data");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		jdbcCMSTemplate.update(QueryConstant.UPDATE_BULK_DATA, new Object[] { msg, sessionDTO.getUserID(), id });
		
	}

	@Override
	public List<TransactionView> getTxnDetails() {
		List<TransactionView> txnList = jdbcCMSTemplate.query(QueryConstant.GET_TXN,
				new BeanPropertyRowMapper<TransactionView>(TransactionView.class));
		return txnList;
	}

	@Override
	public List<InventoryManagement> getInventroy() {
		return jdbcCMSTemplate.query(QueryConstant.GET_INVENTROY,
				new BeanPropertyRowMapper<InventoryManagement>(InventoryManagement.class));
	}

	@Override
	public String getMobileToken(String mobile) {
		try {
			return jdbcCMSTemplate.queryForObject(QueryConstant.GET_MOBILE_TOKEN_STR, new Object[] { mobile },
					String.class);
		} catch (Exception e) {
			return null;
		}
	}
	//Below Code is For AMS - Added By Prashant T 07-Nov-23
	
	@Override
	public List<CardAccountLinkage> getCardAccountLinkage(String accountType, String accountNumber) {
		List<CardAccountLinkage> getCardAccountLinkage = jdbcCMSTemplate.query(QueryConstant.ACCOUNT_LINKAGE_SEARCH,
				new BeanPropertyRowMapper<CardAccountLinkage>(CardAccountLinkage.class), new Object[] { accountType, accountNumber });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getCardAccountLinkage.size());
		return getCardAccountLinkage;

	}
	
	@Override
	public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType, String cardNumber) {
		List<CardAccountLinkage> getCardAccountLinkage = jdbcCMSTemplate.query(QueryConstant.CARD_LINKAGE_SEARCH,
				new BeanPropertyRowMapper<CardAccountLinkage>(CardAccountLinkage.class), new Object[] { cardType, cardNumber });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getCardAccountLinkage.size());
		return getCardAccountLinkage;

	}
	
	
	/**
	 * Account Statement Changes by Jyoti Shirahatti
	 */
	@Override
	public List<AccountStatement> getAccountStatementByDate(String fromDate, String toDate) {
		List<AccountStatement> getAccountStatementByDate = jdbcCMSTemplate.query(QueryConstant.ACCOUNT_STATEMENT_DATE_SEARCH,
				new BeanPropertyRowMapper<AccountStatement>(AccountStatement.class), new Object[] { fromDate, toDate });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getAccountStatementByDate.size());
		return getAccountStatementByDate;

	}
	
	@Override
	public List<AccountStatement> getAccountStatement(String accountType,String accountNumber,String fromDate,String toDate) 
	{
		List<AccountStatement> getAccountStatement = jdbcCMSTemplate.query(QueryConstant.ACCOUNT_STATEMENT_SEARCH,
		new BeanPropertyRowMapper<AccountStatement>(AccountStatement.class), 
		new Object[] { accountType, accountNumber,fromDate, toDate });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getAccountStatement.size());
		return getAccountStatement;
	}
	
	/**
	 * View Issued Account Changes by Jyoti Shirahatti
	 * @param accountIssuedFlag
	 * @return
	 */
	@Override
	public List<AccountCreation> getAccountIssueData(String accountIssuedFlag) 
	{
		List<AccountCreation> getAccountIssueData = jdbcCMSTemplate.query(QueryConstant.ACCOUNT_ISSUE_SEARCH,
				new BeanPropertyRowMapper<AccountCreation>(AccountCreation.class), new Object[] { accountIssuedFlag});

		return getAccountIssueData;
	}
	
	public int saveTransactionIdDetails(TransactionIdTable transactionIdDetails){
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO transaction_id_table (year,julian_date,last_txn_serial_no,created_date,created_by) VALUES (?,?,?,?,?)",
				new Object[] { transactionIdDetails.getStrYear(), transactionIdDetails.getStrJulianDate(),transactionIdDetails.getStrLastTxnSerialNo(),transactionIdDetails.getStrCreatedDate(),this.sessionDTO.getLoginID() });
		return count;
		
	}
	
	public int updateTransactionIdDetails(TransactionIdTable transactionIdDetails) {
		int count = jdbcCMSTemplate.update("UPDATE transaction_id_table "
				+ "SET last_txn_serial_no = ?, created_date = ?, created_by = ? "
				+ "WHERE year = ? and julian_date = ?",
				new Object[]
				{
						transactionIdDetails.getStrLastTxnSerialNo(),
						transactionIdDetails.getStrCreatedDate(),
						this.sessionDTO.getLoginID(), 
						transactionIdDetails.getStrYear(), 
						transactionIdDetails.getStrJulianDate()
				});
		return count;
	}
	
	public List<TransactionIdTable> getTransactionIdTableList(String year,String julianDate){
		List<TransactionIdTable> getTransactionIdTableList = jdbcCMSTemplate.query(QueryConstant.TRANSACTION_ID_DATA_SEARCH,
				new BeanPropertyRowMapper<TransactionIdTable>(TransactionIdTable.class), new Object[] {year,julianDate});
        return getTransactionIdTableList;
	}
	
	public List<GLAccountCreation> getGLAccountTypeList()
	{
		List<GLAccountCreation> getGLAccountTypeList = jdbcCMSTemplate.query("SELECT id As strId,gl_account_type AS strGLAccountType, "
				+ "account_description AS strGLDescription,account_number AS strGLAccountNumber FROM gl_account_type_master",
				new BeanPropertyRowMapper<GLAccountCreation>(GLAccountCreation.class), new Object[] {});
		return getGLAccountTypeList;
	}

	@Override
	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatementByDate(String fromDate, String toDate) {

		List<GlAccountTypeWiseStatementMaster> getGlAccountStatementByDate = jdbcCMSTemplate.query(
				QueryConstant.GLACCOUNT_STATEMENT_DATE_SEARCH,
				new BeanPropertyRowMapper<GlAccountTypeWiseStatementMaster>(GlAccountTypeWiseStatementMaster.class),
				new Object[] { fromDate, toDate });
		try {
			System.out.println("ReportDaoImpl.getGlAccountSatement()" + getGlAccountStatementByDate.size());
			return getGlAccountStatementByDate;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ServiceDaoImpl.getGlAccountStatement()" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatement(String accountType, String accountNumber,
			String fromDate, String toDate) {
		try
		{
			List<GlAccountTypeWiseStatementMaster> getGlAccountStatement = jdbcCMSTemplate.query(""
					+ "SELECT account_number AS strAccountNumber,account_type AS strAccountType,"
			 + " Ref AS strRef,tran_id AS strTranID,amount AS strAmount,transaction_date AS strTransactionDate"
			+ " FROM gl_account_statement where account_type=? AND account_number=? and "
			+ " transaction_date BETWEEN ? AND ? ORDER BY transaction_date ASC",
			new BeanPropertyRowMapper<GlAccountTypeWiseStatementMaster>(GlAccountTypeWiseStatementMaster.class),new Object[] { accountType, accountNumber,fromDate, toDate });
			 System.out.println("ReportDaoImpl.getGlAccountSatement()" + getGlAccountStatement.size());
			 return getGlAccountStatement;
		}
		catch (Exception e)
		{
			System.out.println("ServiceDaoImpl.getGlAccountStatement()"+e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int saveGLAccountList(String glAccountType, String glAccountDescription, String glAccountNumber,
			String createdDate) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
