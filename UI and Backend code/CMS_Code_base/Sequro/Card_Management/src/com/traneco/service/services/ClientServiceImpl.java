package com.traneco.service.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.traneco.clientSearch.model.DetailedClientCardResponse;
import com.traneco.clientSearch.model.DocumentMaintainanceRequest;
import com.traneco.clientSearch.model.DocumentMaintainanceResponse;
import com.traneco.common.KeyValueBean;
import com.traneco.common.ResponseBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.CloseAccountMaster;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.dao.ServiceDao;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountRequest;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.BulkUpload;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.Client;
import com.traneco.service.model.CreditCardCustomerAccountCreationModel;
import com.traneco.service.model.CustomerAdditionResponse;
import com.traneco.service.model.EmbossCardList;
import com.traneco.service.model.EmbossRequest;
import com.traneco.service.model.FundomoAccountRequest;
import com.traneco.service.model.InventoryManagement;
import com.traneco.service.model.PinPrintingModel;
import com.traneco.service.model.ServiceBean;
import com.traneco.service.model.TransactionIdTable;
import com.traneco.service.model.TransactionView;

@Service
@PropertySource({"classpath:config.properties"})
public class ClientServiceImpl implements ClientService 
{
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@Autowired
	ConfigurationService configurationService;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	ServiceDao serviceDao;

	@Autowired
	LoggerUtil log;

	@Autowired
	ClientService clientService;

	private String className = this.getClass().getSimpleName();

	public CustomerAdditionResponse addClient(Client client) 
	{
		client.setStrAddressPrimaryFlag(TranecoStatusCode.PRIMARY_FLAG);
		client.setStrEmailPrimaryFlag(TranecoStatusCode.PRIMARY_FLAG);
		client.setStrPhonePrimaryFlag(TranecoStatusCode.PRIMARY_FLAG);
		ResponseEntity<CustomerAdditionResponse> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.customerAddition"), client,
				CustomerAdditionResponse.class);
		return response.getBody();

	}

	@Override
	public ResponseBean addService(ServiceBean serviceBean) 
	{
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.debitCardIssuance"), serviceBean,
				ResponseBean.class);
		return response.getBody();
	}

	@Override
	public ResponseBean addAccount(AccountRequest request) 
	{
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(env.getProperty("cms.url") + env.getProperty("cms.uri.accountManagement"), request, ResponseBean.class);
		return response.getBody();
	}

	@Override
	public void requestEmboss(EmbossRequest embossRequest) 
	{
		restTemplate.postForEntity(env.getProperty("cms.url") + env.getProperty("cms.uri.embossRequest"), embossRequest, null);
	}

	@Override
	public List<KeyValueBean> getCardList(String cid) 
	{
		return serviceDao.getCardList(cid);
	}

	@Override
	public DocumentMaintainanceResponse addDocument(DocumentMaintainanceRequest documentMaintainanceRequest) {
		ResponseEntity<DocumentMaintainanceResponse> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.document"), documentMaintainanceRequest,
				DocumentMaintainanceResponse.class);
		return response.getBody();
	}

	@Override
	public List<KeyValueBean> getCurrencyList() 
	{
		return serviceDao.getCurrencyList();
	}

	@Override
	public List<KeyValueBean> getParticipantConfigKey() 
	{
		return serviceDao.getParticipantConfigKey().stream().filter(line -> !"INSTANTNAME".equals(line.getLkpkey()))
				.collect(Collectors.toList());
	}

	@Override
	public void addAccountToFundomo(DetailedClientCardResponse detailedClientCardResponse, String accNo,
			String cardNo) 
	 {
		String methodName = "addClientForm";
		try 
		{
			FundomoAccountRequest request = new FundomoAccountRequest();
			request.setAccountNumber(accNo);
			request.setNcmcId("" + new Random().nextInt(999999));
			request.setFirstName(detailedClientCardResponse.getCustomerDetails().getStrFirstName());
			request.setLastName(detailedClientCardResponse.getCustomerDetails().getStrLastName());
			request.setIdNumber("" + new Random().nextInt(999999));
			request.setEmailId(detailedClientCardResponse.getEmailDetailsList().get(0).getStrEmailID());
			request.setLanguage("eng");
			request.setCardNumber(serviceDao.getClearCard(cardNo));
			request.setMnoCode("Master");
			request.setChannelCode("sat");
			request.setPin("12345");
			log.doLog(4, className, methodName, "addAccountToFundomo Request: "+request.toString());
			RestTemplate restTemplate1 = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(request), headers);
	        ResponseEntity<String> response = restTemplate1.exchange(env.getProperty("cms.fundomo.addaccount.url"), HttpMethod.POST, entity, String.class);
			
			log.doLog(4, className, methodName, "addAccountToFundomo Response: "+response.getBody().toString());
		} 
		catch (Exception e) 
		{
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
	}

	@Override
	public List<EmbossRequest> getEmbossFileList() 
	{
		// TODO Auto-generated method stub
		//File folder = new File(embossPath);
		//File folder = new File("/usr/local/tomcat/paths/cms/emboss");
		File folder = new File(env.getProperty("emboss.file.path"));
		File[] listOfFiles = folder.listFiles();
		//sortFilesByDateCreated(listOfFiles);
		List<EmbossRequest> embossList = new ArrayList<EmbossRequest>();
		int i=1;
		if(listOfFiles != null) 
		{
		for (File file : listOfFiles) 
		{
		    if (file.isFile()) 
		    {
		    	EmbossRequest emboss = new EmbossRequest();
		    	//emboss.setFilePath("/usr/local/tomcat/paths/cms/emboss"+"/"+file.getName());
		    	//emboss.setFilePath(embossPath+"/"+file.getName());
		    	emboss.setFilePath(env.getProperty("emboss.file.path")+"/"+file.getName());
		    	emboss.setFileName(file.getName());
		    	emboss.setId(i);
		    	embossList.add(emboss);
		    	i++;
		    }
		 }
		}
		Collections.sort(embossList);
		return embossList;
	}
	
	public static void sortFilesByDateCreated (File[] files) {
	      Arrays.sort(files, new Comparator<File>() {
	          public int compare (File f1, File f2) {
	              long l1 = getFileCreationEpoch(f1);
	              long l2 = getFileCreationEpoch(f2);
	              return Long.valueOf(l1).compareTo(l2);
	          }
	      });
	  }
	
	public static long getFileCreationEpoch (File file) 
		{
	      try 
	      {
	          BasicFileAttributes attr = Files.readAttributes(file.toPath(),
	                  BasicFileAttributes.class);
	          return attr.creationTime()
	                     .toInstant().toEpochMilli();
	      } 
	      catch (IOException e) 
	      {
	          throw new RuntimeException(file.getAbsolutePath(), e);
	      }
		}

	@Override
	public List<ServiceBean> getCard(String cardNo) {
		return serviceDao.getCard(cardNo);
	}

	@Override
	public int updateCustomerID(String custID, String card) {
		return serviceDao.updateCustomerID(custID, card);
	}

	@Override
	public List<EmbossCardList> getEmbossCardList() {
		return serviceDao.getEmbossCardList();
	}

	@Override
	public String getEndpointURL(String cardNO) {
		return serviceDao.getEndpointURL(cardNO);
	}

	@Override
	public List<PinPrintingModel> getPendingPinPrinting() 
	{
		return serviceDao.getPendingPinPrinting();
	}

	@Override
	public int updatePinMailer(String cardType) {
		return serviceDao.updatePinMailer(cardType);
	}

	@Override
	public int readTextFile(String filePath) {
		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

			String line = "";
			 BufferedReader br = new BufferedReader(new FileReader(filePath));
	         while ((line = br.readLine()) != null) 
	         {
	             list.add(getJSONRequest((line)));
	         }
			serviceDao.insertBatch(list);
			//processBulkFile(list);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list.size();
	}
	
	private String getJSONRequest(String data) 
	{
		Gson gson = new Gson();
		try 
		{
				String processData [] = data.split("\\|",-1);
				/*
				 * int i = 0; for(String d : processData) { System.out.println(i +" "+d); i++; }
				 */
				ServiceBean serviceBean = new ServiceBean();
				AccountRequest accountRequest = new AccountRequest();
				Client client = new Client();
				BulkUpload bulkUpload = new BulkUpload();
					
					switch (processData[0]) {
					
					case "NEW":
						
						serviceBean.setInFunction(TranecoStatusCode.NEW_CARD_FUNCTION);
						serviceBean.setInCard(null);
						serviceBean.setCitizenID(processData[2]);
						serviceBean.setCifKey(processData[3]);
						serviceBean.setInCardType(""+configurationService.getBinCardType(processData[4]));
						serviceBean.setCardFlag("Y");
						serviceBean.setInUserID(""+sessionDTO.getUserID());
						serviceBean.setInEmbossLine1(processData[6]);
						serviceBean.setInEmbossLine2(processData[7]);
						serviceBean.setInEncodeFirstName(processData[8]);
						serviceBean.setInEncodeMiddleName(processData[9]);
						serviceBean.setInEncodeLastName(processData[10]);
						serviceBean.setInCardIssueCode(processData[5]);
						serviceBean.setInPartID(sessionDTO.getParticipantid());
						serviceBean.setInInstantFlag("N");
						
						accountRequest.setStrFunctionType("LINK");
						accountRequest.setStrCardSeqNumber("1");
						accountRequest.setStrPrimaryFlag("N");
						accountRequest.setCifKey(processData[3]);
						accountRequest.setCitizenID(processData[2]);
						accountRequest.setStrAccountNumber(processData[30]);
						accountRequest.setStrAccountType(""+configurationService.getAccountID(processData[31]));
						accountRequest.setStrCurrencyCode(processData[32]);
						accountRequest.setStrAccountBranch(processData[33]);
						accountRequest.setStrParticipantID(sessionDTO.getParticipantid());
						
						client.setStrCIFKey(processData[3]);
						client.setStrCitizenID(processData[2]);
						client.setStrFirstName(processData[12]);
						client.setStrMiddleName(processData[13]);
						client.setStrLastName(processData[14]);
						client.setStrAddress1(processData[15]);
						client.setStrAddress2(processData[16]);
						client.setStrCountryCode(""+configurationService.getCountryID(processData[17]));
						client.setStrState(""+configurationService.getStateID(processData[18]));
						client.setStrCity(""+configurationService.getCityID(processData[19]));
						client.setStrPinCode(processData[20]);
						client.setStrAddressType(""+configurationService.getAddressType(processData[21]));
						client.setStrPhoneNo(processData[22]);
						client.setStrEmailID(processData[23]);
						client.setStrEmailType(""+configurationService.getEmailType(processData[24]));
						client.setStrGender(processData[25]);
						client.setStrDOB(processData[26]);
						client.setStrMotherMaidenName(processData[27]);
						client.setStrDocumentType(""+configurationService.getDocType(processData[28]));
						client.setStrDocumentNumber(processData[29]);
						client.setStrParticipantID(sessionDTO.getParticipantid());
						client.setStrSelectID("");
						
					case "RPL": 
						
						break;	
	
					case "RSC": // Replace Same Card
						
						break;
					
					case "RNC": // Replace new card
						
						break;	
						
					default:
						break;
					}
					bulkUpload.setAccountRequest(accountRequest);
					bulkUpload.setClient(client);
					bulkUpload.setServiceBean(serviceBean);
				 return gson.toJson(bulkUpload);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	private void processBulkFile(List<String> list) 
	{
		try 
		{
			for (String data : list) {
				String processData[] = data.split("\\|", -1);
				/*
				 * int i = 0; for(String d : processData) { System.out.println(i +" "+d); i++; }
				 */
				ServiceBean serviceBean = new ServiceBean();
				AccountRequest accountRequest = new AccountRequest();
				Client client = new Client();

				switch (processData[0]) 
					{
					
					case "NEW":
						
						serviceBean.setInFunction(TranecoStatusCode.NEW_CARD_FUNCTION);
						serviceBean.setInCard(null);
						serviceBean.setCitizenID(processData[2]);
						serviceBean.setCifKey(processData[3]);
						serviceBean.setInCardType(""+configurationService.getBinCardType(processData[4]));
						serviceBean.setCardFlag("Y");
						serviceBean.setInUserID(""+sessionDTO.getUserID());
						serviceBean.setInEmbossLine1(processData[6]);
						serviceBean.setInEmbossLine2(processData[7]);
						serviceBean.setInEncodeFirstName(processData[8]);
						serviceBean.setInEncodeMiddleName(processData[9]);
						serviceBean.setInEncodeLastName(processData[10]);
						serviceBean.setInCardIssueCode(processData[5]);
						serviceBean.setInPartID(sessionDTO.getParticipantid());
						serviceBean.setInInstantFlag("N");
						
						accountRequest.setStrFunctionType("LINK");
						accountRequest.setStrCardSeqNumber("1");
						accountRequest.setStrPrimaryFlag("N");
						accountRequest.setCifKey(processData[3]);
						accountRequest.setCitizenID(processData[2]);
						accountRequest.setStrAccountNumber(processData[30]);
						accountRequest.setStrAccountType(""+configurationService.getAccountID(processData[31]));
						accountRequest.setStrCurrencyCode(processData[32]);
						accountRequest.setStrAccountBranch(processData[33]);
						accountRequest.setStrParticipantID(sessionDTO.getParticipantid());
						
						client.setStrCIFKey(processData[3]);
						client.setStrCitizenID(processData[2]);
						client.setStrFirstName(processData[12]);
						client.setStrMiddleName(processData[13]);
						client.setStrLastName(processData[14]);
						client.setStrAddress1(processData[15]);
						client.setStrAddress2(processData[16]);
						client.setStrCountryCode(""+configurationService.getCountryID(processData[17]));
						client.setStrState(""+configurationService.getStateID(processData[18]));
						client.setStrCity(""+configurationService.getCityID(processData[19]));
						client.setStrPinCode(processData[20]);
						client.setStrAddressType(""+configurationService.getAddressType(processData[21]));
						client.setStrPhoneNo(processData[22]);
						client.setStrEmailID(processData[23]);
						client.setStrEmailType(""+configurationService.getEmailType(processData[24]));
						client.setStrGender(processData[25]);
						client.setStrDOB(processData[26]);
						client.setStrMotherMaidenName(processData[27]);
						client.setStrDocumentType(""+configurationService.getDocType(processData[28]));
						client.setStrDocumentNumber(processData[29]);
						client.setStrParticipantID(sessionDTO.getParticipantid());
						client.setStrSelectID("");
						
						CustomerAdditionResponse custResponse = clientService.addClient(client);
						
						if(TranecoStatusCode.STATUS_SUCCESS.equals(custResponse.getOutResponseCode())) {
							serviceBean.setInCustomerID(custResponse.getOutcustomerid());
							ResponseBean cardResponse = clientService.addService(serviceBean);
							if(TranecoStatusCode.STATUS_SUCCESS.equals(cardResponse.getOutResponseCode())) {
								accountRequest.setStrCardNumber(cardResponse.getOutTokenCard());
								ResponseBean response = clientService.addAccount(accountRequest);
								if(TranecoStatusCode.STATUS_SUCCESS.equals(response.getOutResponseCode())) {
	
								}else {
									clientService.rollBackData(custResponse.getOutcustomerid(),cardResponse.getOutTokenCard());
									break;
								}
							}else {
								clientService.rollBackData(custResponse.getOutcustomerid(),cardResponse.getOutTokenCard());
								break;
							}
								
						}else {
							break;
						}
						
						break;
					
					case "RPL":
						
						break;	
	
					case "RSC":
						
						break;
					
					case "RNC":
						
						break;	
						
					default:
						break;
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int rollBackData(String id, String card_no) {
		return  serviceDao.rollBackData(id, card_no);
	}

	@Override
	public List<BulkUploadData> getUploadDataList() {
		return serviceDao.getUploadDataList();
	}

	@Override
	public List<KeyValueBean> getBatchData(String batchID) {
		return serviceDao.getBatchData(batchID);
	}

	@Override
	public void processBatchData(List<KeyValueBean> batchData) {
		Gson gson = new Gson();
		ResponseBean response = new ResponseBean();
		try {
			for (KeyValueBean keyValueBean : batchData) {
				try {
					BulkUpload bulkData = gson.fromJson(keyValueBean.getLkpkey(), BulkUpload.class);
					ServiceBean serviceBean = bulkData.getServiceBean();
					AccountRequest accountRequest = bulkData.getAccountRequest();
					Client client = bulkData.getClient();

					switch (serviceBean.getInFunction()) 
					{
					case TranecoStatusCode.NEW_CARD_FUNCTION:

						CustomerAdditionResponse custResponse = clientService.addClient(client);
						response.setMessage(custResponse.getMessage());
						if (TranecoStatusCode.STATUS_SUCCESS.equals(custResponse.getOutResponseCode())) {
							serviceBean.setInCustomerID(custResponse.getOutcustomerid());
							response = clientService.addService(serviceBean);
							if (TranecoStatusCode.STATUS_SUCCESS.equals(response.getOutResponseCode())) {
								accountRequest.setStrCardNumber(response.getOutTokenCard());
								response = clientService.addAccount(accountRequest);
								if (TranecoStatusCode.STATUS_SUCCESS.equals(response.getOutResponseCode())) {

								} else {
									clientService.rollBackData(custResponse.getOutcustomerid(),
											response.getOutTokenCard());
									break;
								}
							} else {
								clientService.rollBackData(custResponse.getOutcustomerid(), response.getOutTokenCard());
								break;
							}

						} else {
							break;
						}

						break;

					case "RPL":

						break;

					case "RSC":

						break;

					case "RNC":

						break;

					default:
						break;
					}
				} catch (Exception e) {
					response.setMessage(e.getMessage());
				}
				serviceDao.updateBatchData(keyValueBean.getLkpvalue(), response.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TransactionView> getTxnDetails() {
		return serviceDao.getTxnDetails();
	}

	@Override
	public List<InventoryManagement> getInventroy() {
		return serviceDao.getInventroy();
	}

	@Override
	public List<MobileTokenModel> getMobileToken(String mobile) 
	{
		String token = serviceDao.getMobileToken(mobile);
		if (StringUtils.isNoneBlank(token)) 
		{
			Gson gson = new Gson();
			List<MobileTokenModel> mobileTokenList = new LinkedList<MobileTokenModel>(
					Arrays.asList(gson.fromJson(token, MobileTokenModel[].class)));
			return mobileTokenList;
		}
		return Collections.emptyList();
	}

	@Override
	public List<CardAccountLinkage> getCardAccountLinkage(String accountType, String accountNumber) {
		return serviceDao.getCardAccountLinkage(accountType, accountNumber);
	}

	@Override
	public List<CardAccountLinkage> getCardAccountLinkageCard(String accountType, String accountNumber) {
		return serviceDao.getCardAccountLinkageCard(accountType, accountNumber);
	}

	/**
	 * Account Statement Changes by Jyoti Shirahatti
	 */
	@Override
	public List<AccountStatement> getAccountStatementByDate(String fromDate, String toDate) {
		return serviceDao.getAccountStatementByDate(fromDate, toDate);
	}

	@Override
	public List<AccountStatement> getAccountStatement(String accountType, String accountNumber, String fromDate,
			String toDate) {
		return serviceDao.getAccountStatement(accountType, accountNumber, fromDate, toDate);
	}
	
	/**
	 * View Issued Account Changes by Jyoti Shirahatti
	 * @param accountIssuedFlag
	 * @return
	 */
	@Override
	public List<AccountCreation> getAccountIssueData(String accountIssuedFlag) {
		return serviceDao.getAccountIssueData(accountIssuedFlag);
	}

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
			String createdDate) {
		return serviceDao.saveGLAccountList(glAccountType, glAccountDescription, glAccountNumber, createdDate);
	}

	public List<GLAccountCreation> getGLAccountTypeList() {
		return serviceDao.getGLAccountTypeList();

	}

	@Override public List<GlAccountTypeWiseStatementMaster>
	  getGlAccountStatementByDate(String fromDate, String toDate) 
	{ 
		return serviceDao.getGlAccountStatementByDate(fromDate, toDate); 
		
	}

	@Override
	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatement(String accountType, String accountNumber,
			String fromDate, String toDate) 
	{ 
		return serviceDao.getGlAccountStatement(accountType, accountNumber, fromDate, toDate);
	}
	
	@Override
	public int  saveTransactionIdDetails(TransactionIdTable transactionIdTable){
		int count = serviceDao.saveTransactionIdDetails(transactionIdTable);
		return count;
	}
	
	public List<TransactionIdTable> getTransactionIdList(String year,String julianDate){
		return serviceDao.getTransactionIdTableList(year, julianDate);
	}
	
	public int updateTransactionIdDetails(TransactionIdTable transactionIdTable) 
	{
		return serviceDao.updateTransactionIdDetails(transactionIdTable);
	}

	
	@Override
	public CreditCardCustomerAccountCreationModel generateCardAccount(CreditCardCustomerAccountCreationModel creditCardCustomerAccountCreation) {
		try 
		{
			String serverUrl = env.getProperty("cms.url") + env.getProperty("cms.uri.cardAccountCreation");
			CreditCardCustomerAccountCreationModel responseEntity = this.restTemplate.postForObject(serverUrl , creditCardCustomerAccountCreation, CreditCardCustomerAccountCreationModel.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
