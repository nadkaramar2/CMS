package com.traneco.accountmanagement.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traneco.api.model.AccountStatementHeader;
import com.traneco.config.ApiPropertyConfig;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.DenominationMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.PreSubAccountMaster;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.AccountStatementFooterView;
import com.traneco.service.model.GLAccountTypeMaster;
import com.traneco.service.model.JournalTransfer;
import com.traneco.service.model.PreAccountMaster;

@Service
public class AmsStatementServiceImpl implements AmsStatementService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ApiPropertyConfig apiPropertyConfig;
	
	/*private final String AMS_MAIN_API_URL = apiPropertyConfig.getAccountManagementAPIurl();
	private final String AMS_DOWNLOAD_API_URL = apiPropertyConfig.getAccountManagementFileDownloadApiUrl();*/
	
	@Override
	public List<AccountStatement> getAccountStatementBasedonParameter(AccountStatement accountStatement)
			throws Exception {
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl()
				+ apiPropertyConfig.getAmsAccountStatementApi() + "/getAccountStatementList";
		AccountStatement[] responseEntity = this.restTemplate.postForObject(serverUrl, accountStatement,
				AccountStatement[].class);
		List<AccountStatement> accountTypeMasters = Arrays.asList(responseEntity);
		return accountTypeMasters;
	}

	@Override
	public List<GLAccountStatement> getGLAccountStatement(GLAccountStatement glAccountStatement) throws Exception {
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl()
				+ apiPropertyConfig.getAmsGLAccountStatementApi() + "/getGLAccountStatementList";
		GLAccountStatement[] responseEntity = this.restTemplate.postForObject(serverUrl, glAccountStatement,
				GLAccountStatement[].class);
		List<GLAccountStatement> accountTypeMasters = Arrays.asList(responseEntity);
		return accountTypeMasters;
	}

	//added by Sunil Y , new api creation for header response Started
	@Override
	public List<AccountStatementHeader> getAccountStatementHeader(
			GLAccountStatement glAccountStatement) throws Exception {
		AccountStatementHeader[] accountStatementHeader;

		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl()
				+ apiPropertyConfig.getAmsGLAccountStatementApi() + "/getAccountStatementandGlAccountStatementHeader";
		AccountStatementHeader[] responseEntity = this.restTemplate.postForObject(serverUrl, glAccountStatement,
				AccountStatementHeader[].class);
		List<AccountStatementHeader> accountTypeMasters = Arrays.asList(responseEntity);
		System.out.println(accountTypeMasters.get(0));
		return accountTypeMasters;
	}
	
	
	//added by Sunil Y , new api creation for header response Started
		@Override
		public List<GLAccountTypeMaster> getGLAccountStatementHeader(GLAccountStatement glAccountStatement) throws Exception {
			AccountStatementHeader[] accountStatementHeader;

			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl()
					+ apiPropertyConfig.getAmsGLAccountStatementApi() + "/getGlAccountStatementHeader";
			GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, glAccountStatement,
					GLAccountTypeMaster[].class);
			List<GLAccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
			System.out.println(accountTypeMasters.get(0));
			return accountTypeMasters;
		}
	//added by Sunil Y , new api creation for header response End

	@Override
	public String getDownloadedAccountStatementFilePath(List<AccountStatement> accountStatements) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			int count = 1;
			int debitCount = 0;
			int creditCount = 0;

			double debits = 0d;
			double credits = 0d;
			double closingBalance = 0d;
			double openingBalance = 0d;

			for (AccountStatement accountStatement : accountStatements) 
			{
				String txnMode = accountStatement.getStrTranMode();
				
				if ("CREDIT".equalsIgnoreCase(txnMode)) 
				{
					if (credits == 0) 
					{
						credits = Double.parseDouble(accountStatement.getStrTransactionAmount());
					} 
					else 
					{
						credits = credits + Double.parseDouble(accountStatement.getStrTransactionAmount());
					}

					if (count == 1) 
					{
						Double firstClosBal = Double.parseDouble(accountStatement.getStrClosingBalance());
						if (credits == firstClosBal) 
						{
							openingBalance = firstClosBal;
						}
					}
					creditCount = creditCount + 1;
				} else {
					if (debits == 0) {
						debits = Double.parseDouble(accountStatement.getStrTransactionAmount());
					} else {
						debits = debits + Double.parseDouble(accountStatement.getStrTransactionAmount());
					}
					debitCount = debitCount + 1;
				}

				if (accountStatements.size() == count) {
					closingBalance = Double.parseDouble(accountStatement.getStrClosingBalance());
				}
				count++;
			}
			// Opening Balance getting logic Start
			if (openingBalance == 0) {
				openingBalance = (closingBalance + debits) - credits;
			}
			// Opening Balance getting logic End

			Map<String, Object> data = new HashMap<>();

			Map<String, Object> headderMap = new HashMap<>();
			Map<String, Object> footerMap = new HashMap<>();

			data.put("headerInfoMap", headderMap);
			//Added by Sunil Y new api for header response 27-05-23 Start
			GLAccountStatement glAccountStatement = new GLAccountStatement();
			glAccountStatement.setStrAccountNumber(accountStatements.get(0).getStrAccountNumber());
			List<AccountStatementHeader> accountMasterHeader = getAccountStatementHeader(
					glAccountStatement);			
			data.put("bodyInfoInList", accountStatements);
			AccountStatementFooterView accountStatementFooterView = new AccountStatementFooterView();
			accountStatementFooterView.setOpeningBalance(String.valueOf(openingBalance));
			accountStatementFooterView.setCredits(String.valueOf(credits));
			accountStatementFooterView.setDebits(String.valueOf(debits));
			accountStatementFooterView.setCreditCount(String.valueOf(creditCount));
			accountStatementFooterView.setDebitCount(String.valueOf(debitCount));
			accountStatementFooterView.setClosingBalance(String.valueOf(closingBalance));

			footerMap.put("footer", accountStatementFooterView);
			headderMap.put("header", accountMasterHeader.get(0));

			data.put("headerInfoMap", headderMap);
			data.put("footerInfoMap", footerMap);
			// data.put("templateName", "account-statement");
			data.put("templateName", "ams_account-statement");
			data.put("fileName", "account-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedAccountStatementFilePathForExcel(List<AccountStatement> accountStatements) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			//Added By Sunil Y changed to LinkedHashMap
			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", accountStatements);
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();

			headerDataMap.put("strTxnDate", "Txn Date");
			headerDataMap.put("strTxnTime", "Txn Time");
			headerDataMap.put("strTransactionID", "Txn Id");
			headerDataMap.put("strTranType", "Txn Type");
			headerDataMap.put("strDebitAmount", "Txn Amount");
			headerDataMap.put("strClosingBalance", "Current Balance");
			headerDataMap.put("strTransactionDetails", "Transaction Description");

			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "account-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	@Override
	public String getDownloadedGLAccountStatmntFilePathForExcel(List<AccountStatement> accountStatementList) 
	{
		try
		{
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl() + apiPropertyConfig.getAmsExportExcelApi() +"/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    Map<String, Object> data = new HashMap<>();			    
	        data.put("bodyInfoInList", accountStatementList);
	        
	        //key contains mapping data for getting value and value contains header name
	        
		    Map<String, String> headerDataMap = new HashMap<>();		    

		    headerDataMap.put("strAccountType", "Account Type");
		    headerDataMap.put("strAccountNumber", "Account Number");
		    headerDataMap.put("strTransactionID", "Transaction ID");
		    headerDataMap.put("strTransactionDetails", "Narration");
		    headerDataMap.put("strTransactionAmount", "Transaction Amount");
		    headerDataMap.put("strTransactionDate", "Transaction Date");
		    headerDataMap.put("strTranMode", "Transaction Type");
		    
	        data.put("headerInfoMap", headerDataMap);
	        
	        data.put("fileName", "gl-account-statement");
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");			    
		    String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("responseEntity::"+responseEntity);
		    
		    return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
		return null;
	}
	*/
	
	@Override
	public String getDownloadedGLAccountStatmntFilePathForExcel(List<GLAccountStatement> glAccountStatementList) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", glAccountStatementList);

			// key contains mapping data for getting value and value contains header name
			//added by sunil Y  LinkedHashMap  
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();

			headerDataMap.put("strTxnDate", "Txn Date");
			headerDataMap.put("strTxnTime", "Txn Time");
			headerDataMap.put("strTxnId", "Txn ID");
			headerDataMap.put("strAmount", "Txn Amount");
			headerDataMap.put("strRef", "Narration");
			headerDataMap.put("strTranType", "Txn Type");
			headerDataMap.put("strTranMode", "Txn Mode");

			data.put("headerInfoMap", headerDataMap);
			data.put("fileName", "gl-account-statement");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);
			headerDataMap.clear();
			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedGLAccountStatmntFilePath(List<GLAccountStatement> glAccountStatementList) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			//Added By Sunil Y , Header call of Gl account statement. Started 24-May-23
			GLAccountStatement glAccountTypeMaster = new GLAccountStatement();
			glAccountTypeMaster.setStrAccountNumber(glAccountStatementList.get(0).getStrAccountNumber());
			List<GLAccountTypeMaster> accountMasterHeader = getGLAccountStatementHeader(glAccountTypeMaster);
			Map<String, Object> data = new HashMap<>();

			Map<String, Object> headerMap = new HashMap<>();
			headerMap.put("header", accountMasterHeader.get(0));

			data.put("bodyInfoInList", glAccountStatementList);
			data.put("headerInfoMap", headerMap);
			data.put("templateName", "gl-account-statement");
			data.put("fileName", "gl-account-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	@Override
	public String getDownloadedGLAccountStatmntFilePath(List<AccountStatement> accountStatementList)
	{
		try
		{
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl() + apiPropertyConfig.getAmsExportPdfApi() +"/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    Map<String, Object> data = new HashMap<>();			    
	        data.put("bodyInfoInList", accountStatementList);        
	        //data.put("headerInfoMap", customer);
	        data.put("templateName", "gl-account-statement");
	        data.put("fileName", "gl-account-statement-");
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");			    
		    String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("responseEntity::"+responseEntity);
		    
		    return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
		return null;
	}
	*/
	//Added by Sagar Start
	@Override
	public String getDownloadedJournalTransferFilePathForExcel(List<JournalTransfer> journalTransferList) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", journalTransferList);

			// key contains mapping data for getting value and value contains header name

			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();

			headerDataMap.put("strTxnDte", "Txn Date");
			headerDataMap.put("strTxnTime", "Txn Time");
			headerDataMap.put("strTxnId", "Txn ID");
			headerDataMap.put("strFromAccountType", "From A/c Type");
			headerDataMap.put("strFromAccountNumber", "From A/c Number");
			headerDataMap.put("strFromAccountName", "From A/c Name");
			headerDataMap.put("strToAccountType", "To A/c Type");
			headerDataMap.put("strToAccountNumber", "To A/c Number");
			headerDataMap.put("strToAccountName", "To A/c Name");
			headerDataMap.put("strAmoutToTransfer", "Amount To Transfer");
			headerDataMap.put("strNarration", "Narration");
			headerDataMap.put("strTxnStatus", "Status");
			headerDataMap.put("strMakerId", "Maker UserID");
			headerDataMap.put("strCheckerId", "Checker UserID");
			System.out.println(headerDataMap.get(0));
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "journal-report-statement");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedTxnStatmentPdfFilePath(List<AccountTranMaster> accountTranMasterList) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();
			Collections.reverse(accountTranMasterList);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", accountTranMasterList);
			// data.put("headerInfoMap", customer);
			data.put("templateName", "txn-report-statement");
			data.put("fileName", "txn-report-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getDownloadedGlReportStatement(List<GLAccountTypeMaster> glAccountTypeMasters) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", glAccountTypeMasters);
			// data.put("headerInfoMap", customer);
			data.put("templateName", "gl-report-statement");
			data.put("fileName", "gl-report-statement-demo-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedTxnStatmentFilePathForExcel(List<AccountTranMaster> accountTranMasterList) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", accountTranMasterList);

			// key contains mapping data for getting value and value contains header name
			//Added By Sunil Y added LinkedHashMap 
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();

			headerDataMap.put("txn_Date", "Txn Date");
			headerDataMap.put("txn_Time", "Txn Time");
			headerDataMap.put("strTxn_id", "Txn ID");
			headerDataMap.put("strTran_type", "Txn Type");
			headerDataMap.put("strTransaction_amount", "Txn Amount");
			headerDataMap.put("strResponseCode", "Txn Response Code");
			headerDataMap.put("strAccountNumber", "Account Number");
			headerDataMap.put("strFrom_account_number", "From Account No");
			headerDataMap.put("strTo_account_number", "To Account No");

			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "txn-report-statement");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedChartReportPdfFilePath(List<GLAccountTypeMaster> glAccountTypeMasterList) {
		try {
			// sort the data by account of type

			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			Collections.reverse(glAccountTypeMasterList);
			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", glAccountTypeMasterList);
			// data.put("headerInfoMap", customer);
			data.put("templateName", "gl-report-statement");
			data.put("fileName", "chart-account-statement-");
			
			Map<String, Object> headerMap = new HashMap<>();
			headerMap.put("header", glAccountTypeMasterList.get(0));
			data.put("headerInfoMap", headerMap);

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public String getDownloadedChartReportFilePathForExcel(List<GLAccountTypeMaster> gLAccountTypeMaster) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", gLAccountTypeMaster);

			// key contains mapping data for getting value and value contains header name

			Map<String, String> headerDataMap = new HashMap<>();

			headerDataMap.put("strAccountType", "Type");
			headerDataMap.put("strGLAccountType", "GL Account Type");
			headerDataMap.put("strAccountNumber", "GL Account Number");
			headerDataMap.put("strGLAccountDescription", "Description");
			headerDataMap.put("strClosingBalance", "Closing Balance");

			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "gl-report-statement");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	@Override
	public String getDownloadedRegisterCustLinkedFilePathForExcel(List<AccountCreation> accountCreation) 
	{
		try 
		{
			  String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl() + apiPropertyConfig.getAmsExportExcelApi() +"/getDownloadFile";
				HttpHeaders headers = new HttpHeaders();
				
				headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    
			    Map<String, Object> data = new HashMap<>();			    
		        data.put("bodyInfoInList", accountCreation);
		      
		      //key contains mapping data for getting value and value contains header name
		      
			    Map<String, String> headerDataMap = new HashMap<>();		    

			    headerDataMap.put("strCustId", "Customer ID");
			    headerDataMap.put("strDateOfRegistration", "Date of Registration");
			    headerDataMap.put("strFirstName", "First Name");
			    headerDataMap.put("strMiddleName", "Middle Name");
			    headerDataMap.put("strLastName", "Last Name");
			    headerDataMap.put("strAccountType", "Account Type");
			    headerDataMap.put("strAccountType", "strAccountNumber");
			    headerDataMap.put("strAccountIssueDate", "Account Opening Date");
			
			  
		      data.put("headerInfoMap", headerDataMap);
		      
		      data.put("fileName", "gl-report-statement");
			    
			    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
			    
			    System.out.println("Calling .....processToGETDownloadedFile Request");			    
			    String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			    System.out.println("responseEntity::"+responseEntity);
			    
			    return responseEntity;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	*/
	//Added by Sagar End

	@Override
	public String getDownloadedregisteredCustomersFilePathForExcel(List<PreAccountMaster> preAccountMasterlist) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", preAccountMasterlist);

			// key contains mapping data for getting value and value contains header name
			//added By Sunil Y LinkedHashMap , 
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();
			headerDataMap.put(" cust_id", "CustID");
			headerDataMap.put("strDateofRegistration", "Date of Registration");
			headerDataMap.put("strTimeofRegistration", "Time of Registration");
			headerDataMap.put("strFirstName", "First Name");
			headerDataMap.put("strMiddleName", "Middle Name");
			headerDataMap.put("strLastName", "Last Name");
			headerDataMap.put("strAccountRegistered", "Account Registration(Y/N)");
			headerDataMap.put("strAgeing", "Ageing");
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "registered-customers");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedRegisterCustLinkedFilePathForExcel(List<PreSubAccountMaster> preSubAccountMaster) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", preSubAccountMaster);

			// key contains mapping data for getting value and value contains header name
			//added by Sunil Y , LinkedHashMap
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();
			
			headerDataMap.put("strDateOfRegistration", "Date of Registration");
			headerDataMap.put("strCustId", "Customer ID");
			headerDataMap.put("strAccountType", "Account Type");
			headerDataMap.put("strDescription", "Account Type Description");
			headerDataMap.put("ageing", "Ageing");
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "registered-customered-pending-statment-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedRegisterCustLinkedFilePathFor(List<PreSubAccountMaster> preSubAccountMaster) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", preSubAccountMaster);
			// data.put("headerInfoMap", customer);
			data.put("templateName", "registeredCust_pending-statement");
			data.put("fileName", "registeredCust_pending-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCashDeptStatementPdfFilePath(List<DenominationMaster> denominationMaster) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", denominationMaster);
			// data.put("headerInfoMap", customer);
			data.put("templateName", "cashDept-statement");
			data.put("fileName", "cashDept-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCashDeptStatementPathForExcel(List<DenominationMaster> denominationMaster) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", denominationMaster);

			// key contains mapping data for getting value and value contains header name
			// added by Sunil Y , LinkedHashMap
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();

			headerDataMap.put("strDate", "Txn Date");
			headerDataMap.put("strTime", "Txn Time");
			headerDataMap.put("txnId", "Txn ID");
			headerDataMap.put("fromAccount", "Agent Account Number");
			headerDataMap.put("toAccount", "Customer Account Number");
			headerDataMap.put("strTxnType", "Transaction Type");
			headerDataMap.put("strTxnAmount", "Amount");
			headerDataMap.put("d10", "Deno 10");
			headerDataMap.put("d20", "Deno 20");
			headerDataMap.put("d50", "Deno 50");
			headerDataMap.put("d100", "Deno 100");
			headerDataMap.put("d200", "Deno 200");
			headerDataMap.put("d500", "Deno 500");
			headerDataMap.put("d1000", "Deno 1000");
			headerDataMap.put("d2000", "Deno 2000");
			
			System.out.println(headerDataMap);
			
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "cashDept-statement");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// added by sunil Y , cash withdrawal statement pdf and exel button methods ,
	// 22-May-23 START
	@Override
	public String getDownloadedCashWithdrawalStatmntFilePathForExcel(List<DenominationMaster> denominationMasters) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportExcelApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			LinkedHashMap<String, Object> data = new LinkedHashMap<>();
			data.put("bodyInfoInList", denominationMasters);

			// key contains mapping data for getting value and value contains header name

			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();

			headerDataMap.clear();
			headerDataMap.put("strDate", "Txn Date");
			headerDataMap.put("strTime", "Txn Time");
			headerDataMap.put("txnId", "Txn ID");
			headerDataMap.put("fromAccount", "Agent Account Numbe");
			headerDataMap.put("toAccount", "Customer Account Number");
			headerDataMap.put("strTxnType", "Transaction Type");
			headerDataMap.put("strTxnAmount", "Amount");
			headerDataMap.put("d10", "Deno 10");
			headerDataMap.put("d20", "Deno 20");
			headerDataMap.put("d50", "Deno 50");
			headerDataMap.put("d100", "Deno 100");
			headerDataMap.put("d200", "Deno 200");
			headerDataMap.put("d500", "Deno 500");
			headerDataMap.put("d1000", "Deno 1000");
			headerDataMap.put("d2000", "Deno 2000");

			data.put("headerInfoMap", headerDataMap);
			data.put("fileName", "cash-withdraw-statement");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);
			headerDataMap.clear();
			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCashWithdrawalStatmntFilePath(List<DenominationMaster> denominationMasters) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", denominationMasters);
			data.put("templateName", "denomination-withdrawal-statement");
			data.put("fileName", "denomination-withdrawal-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// added by sunil Y , JournalTransferFile Pdf service 
	@Override
	public String getDownloadedJournalTransferFilePathForPDF(List<JournalTransfer> journalTransferList) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementFileDownloadApiUrl()
					+ apiPropertyConfig.getAmsExportPdfApi() + "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", journalTransferList);
			// data.put("headerInfoMap", customer);
			data.put("templateName", "journal-report-statement");
			data.put("fileName", "journal-report-statement-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountStatementHeader> getAccountStatementAndGLAccountStatementHeader(GLAccountStatement glAccountStatement) throws Exception 
	{
		AccountStatementHeader[] accountStatementHeader;

		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl()
				+ apiPropertyConfig.getAmsGLAccountStatementApi() + "/getAccountStatementandGlAccountStatementHeader";
		AccountStatementHeader[] responseEntity = this.restTemplate.postForObject(serverUrl, glAccountStatement,
				AccountStatementHeader[].class);
		List<AccountStatementHeader> accountTypeMasters = Arrays.asList(responseEntity);
		System.out.println(accountTypeMasters.get(0));
		return accountTypeMasters;
	}
	
	
}
