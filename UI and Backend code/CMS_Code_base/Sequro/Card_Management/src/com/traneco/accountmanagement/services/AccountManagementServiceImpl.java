package com.traneco.accountmanagement.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traneco.accountmanagement.model.AccountMasterResponse;
import com.traneco.accountmanagement.model.ApproveUpgradeTier;
import com.traneco.accountmanagement.model.ExternalServerRequestResponseModel;
import com.traneco.accountmanagement.model.FeeTypeMaster;
import com.traneco.accountmanagement.model.TxnReqRespLogMaster;
import com.traneco.accountmanagement.model.VatTypeMaster;
import com.traneco.common.util.Utils;
import com.traneco.config.ApiPropertyConfig;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountInterestMaster;
import com.traneco.configuration.model.AccountLoadMaster;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.AccountWiseCharges;
import com.traneco.configuration.model.AddressProofDocumentTypeMaster;
import com.traneco.configuration.model.CategoryListModel;
import com.traneco.configuration.model.Categorytype;
import com.traneco.configuration.model.Channels;
import com.traneco.configuration.model.CloseAccountMaster;
import com.traneco.configuration.model.CountryCodeMaster;
import com.traneco.configuration.model.CustomerIdCreation;
import com.traneco.configuration.model.CustomerIdMap;
import com.traneco.configuration.model.CustomerIdTable;
import com.traneco.configuration.model.DenominationMaster;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GLAccountLoadingMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.IdentityProofDocumentTypeMaster;
import com.traneco.configuration.model.InstantAccountCreation;
import com.traneco.configuration.model.LoadBalanceModel;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MerchantCategoryCodeMaster;
import com.traneco.configuration.model.NubanCodeConfig;
import com.traneco.configuration.model.NubanTypeConfig;
import com.traneco.configuration.model.PreSubAccountMaster;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.model.RevolvingCreditCardMaster;
import com.traneco.configuration.model.RevolvingCreditCardTxnMaster;
import com.traneco.configuration.model.RevolvingCreditInterestTxn;
import com.traneco.configuration.model.TaxConfigModel;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TransactionTypeModel;
import com.traneco.configuration.model.TxnReqRes;
import com.traneco.configuration.model.UpgradeTierReqRes;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountCreditCardTransactionModel;
import com.traneco.service.model.AccountKycDetails;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.AccountTransactionLimitation;
import com.traneco.service.model.BulkTransfer;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ChargeMaster;
import com.traneco.service.model.ChargeRelatedMaster;
import com.traneco.service.model.Client;
import com.traneco.service.model.CloseAccountResponse;
import com.traneco.service.model.CreditCardCustomerAccountCreationModel;
import com.traneco.service.model.EmailTemplate;
import com.traneco.service.model.GLAccountTypeMaster;
import com.traneco.service.model.JournalTransfer;
import com.traneco.service.model.PreAccountMaster;
import com.traneco.service.model.WalletAccountMaster;


@Service
public class AccountManagementServiceImpl implements AccountManagementService
{
	@Autowired
	RestTemplate restTemplate;

	ApiPropertyConfig apiPropertyConfig;
	
	private final String AMS_MAIN_API_URL;
	
	private final String SERVER_AMS_MAIN_API_URL;
	
	private final String AMS_ACCOUNT_TYPE_API;
	
	private final String MULTI_CURRENCY_API_URL;
	
	private final String CREDIT_CARD_URL;
	
	Logger logger = LoggerFactory.getLogger(AccountManagementServiceImpl.class);
	
	@Autowired
	Environment env;
	
	@Autowired
	public AccountManagementServiceImpl(ApiPropertyConfig apiPropertyConfig)
	{
		this.apiPropertyConfig = apiPropertyConfig;
		
		AMS_MAIN_API_URL = apiPropertyConfig.getAccountManagementAPIurl();
		
		AMS_ACCOUNT_TYPE_API = apiPropertyConfig.getAmsAccountType();
		
		SERVER_AMS_MAIN_API_URL = apiPropertyConfig.getServerAccountManagementAPIurl();
		
		MULTI_CURRENCY_API_URL = apiPropertyConfig.getMultiCurrencyAPIurl();
		
		CREDIT_CARD_URL = apiPropertyConfig.getCreditCardurl();
		
	}
	
	@Override
	public AccountCreation getAccountInformation(AccountCreation accountCreation) throws Exception
	{
		String serverUrl = apiPropertyConfig.getCreditCardurl() +  apiPropertyConfig.getAmsAccountApi() +"/getAccountInformation";//getAmsTransactionHandler
		return this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation.class);
	}
	
	@Override
	public List<AccountTypeMaster> getNonCreditAccounTypeObject(AccountTypeMaster accountTypeMster) throws Exception
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() + "/getNonCreditAccounType";
		AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMster, AccountTypeMaster[].class);
		List<AccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
		return accountTypeMasters;
	}
	
	@Override
	public AccountTranMaster addAccountTranMaster(AccountTranMaster accountTranMaster) throws Exception
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccoutTxnMaster() +"/add";
		logger.info("Inside addAccountTranMaster serverUrl::["+serverUrl+"]");
		logger.info("Inside addAccountTranMaster accountTranMaster::["+accountTranMaster+"]");
		
		logger.info("Inside addAccountTranMaster restTemplate::["+this.restTemplate+"]");
		AccountTranMaster responseEntity = this.restTemplate.postForObject(serverUrl , accountTranMaster, AccountTranMaster.class);
		return responseEntity;
	}
	
	@Override
	public String addAccountTxnData(LoadBalanceModel loadBalanceModel) throws Exception
	{
		AccountTranMaster accountTranMaster = new AccountTranMaster();
		accountTranMaster.setStrTxn_id(loadBalanceModel.getStrTransactionId());
		accountTranMaster.setStrAccountNumber(loadBalanceModel.getStrAccountNumber());
		accountTranMaster.setStrTransaction_amount(loadBalanceModel.getStrLoadedBalance());
		
		accountTranMaster.setStrParticipantId(loadBalanceModel.getStrParticipantId());
		
		accountTranMaster.setStrLocal_tran_date(new Date().getTime()+"");
		accountTranMaster.setStrLocal_tran_time(LocalTime.now()+"");
		accountTranMaster.setStrTran_type("CR");
		
		accountTranMaster = addAccountTranMaster(accountTranMaster);
		return accountTranMaster.getStrID();
	}
	
	@Override
	public String addAccountStatement(LoadBalanceModel loadBalanceModel, AccountCreation accountCreation) throws Exception 
	{
		AccountStatement accountStatement = new AccountStatement();
		accountStatement.setStrAccountNumber(accountCreation.getStrAccountNumber());
		accountStatement.setStrAccountType(accountCreation.getStrAccountType());
		accountStatement.setStrClosingBalance(accountCreation.getStrClosingBalance());
		accountStatement.setStrIsGLType("N");
		
		accountStatement.setStrTransactionAmount(loadBalanceModel.getStrLoadedBalance());			
		accountStatement.setStrTransactionID(loadBalanceModel.getStrTransactionId());
		accountStatement.setStrParticipantId(loadBalanceModel.getStrParticipantId());
		
		accountStatement.setStrNaration("Transaction Successful.");
		accountStatement.setStrTransactionMode("CREDIT");
		accountStatement.setStrTransactionType("Deposit");
		
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccountStatementApi() +"/add";
		AccountStatement responseEntity = this.restTemplate.postForObject(serverUrl , accountStatement, AccountStatement.class);
		return responseEntity.getStrID();
	}
	
	@Override
	public int updateBalanceRelatedData(AccountCreation accountCreation) throws Exception 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccountApi() +"/updateBalanceRelatedInfo";
		Integer responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, Integer.class);
		return responseEntity.intValue();
	}
	
	@Override
	public String saveAccountLoadMasterData(LoadBalanceModel loadBalanceModel) throws Exception
	{
		AccountLoadMaster accountLoadMaster = new AccountLoadMaster();
		accountLoadMaster.setStrParticipantId(loadBalanceModel.getStrParticipantId());
		accountLoadMaster.setStrAccountType(loadBalanceModel.getStrAccountType());
		accountLoadMaster.setStrAccountNumber(loadBalanceModel.getStrAccountNumber());
		accountLoadMaster.setStrAccountCategory(loadBalanceModel.getStrAccountCategory());
		accountLoadMaster.setStrLoadedBalance(loadBalanceModel.getStrLoadedBalance());
		accountLoadMaster.setStrChannel(loadBalanceModel.getStrChannel());
		accountLoadMaster.setStrTransactionId(loadBalanceModel.getStrTransactionId());
		accountLoadMaster.setStrCreatedBy(loadBalanceModel.getStrCreatedBy());
		
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccountLoadApi() +"/add";
		AccountLoadMaster responseEntity = this.restTemplate.postForObject(serverUrl , accountLoadMaster, AccountLoadMaster.class);
		return responseEntity.getStrID();
	}
	
	@Override
	public List<GLAccountTypeMaster> getListOfGLAccountTypelist(GLAccountTypeMaster glAccountTypeMaster) throws Exception 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsGlAccountTypeApi() + "/getGLAccountTypeList";
		GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , glAccountTypeMaster, GLAccountTypeMaster[].class);
		List<GLAccountTypeMaster> glAccountTypeMasterslist = Arrays.asList(responseEntity);
		return glAccountTypeMasterslist;
	}
	
	@Override
	public String getClosingBalanceOfGlAccount(GLAccountTypeMaster glAccountTypeMaster) throws Exception 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsGlAccountTypeApi() +"/get-glaccount-closing-bal";
		String responseEntity = this.restTemplate.postForObject(serverUrl , glAccountTypeMaster, String.class);
		return responseEntity;
	}
	
	@Override
	public int updateGlaccountTypeDetails(GLAccountTypeMaster glAccountTypeMaster) throws Exception 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsGlAccountTypeApi() +"/update-glaccount-type-details";
		Integer responseEntity = this.restTemplate.postForObject(serverUrl , glAccountTypeMaster, Integer.class);
		return responseEntity.intValue();
	}
	
	@Override
	public String loadGLAccountInformationData(GLAccountLoadingMaster glAccountLoadingMaster) throws Exception 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsGlAccountLoadApi() +"/add";
		String responseEntity = this.restTemplate.postForObject(serverUrl , glAccountLoadingMaster, String.class);
		return responseEntity;
	}
	
	@Override
	public String addAccountStatementForGLAccount(AccountTranMaster accountTranMaster, String closingBalance) throws Exception 
	{
		AccountStatement accountStatement = new AccountStatement();
		accountStatement.setStrAccountNumber(accountTranMaster.getStrAccountNumber());
		accountStatement.setStrAccountType(accountTranMaster.getStrAccountType());
		accountStatement.setStrClosingBalance(closingBalance);
		
		accountStatement.setStrTransactionAmount(accountTranMaster.getStrTransaction_amount());			
		accountStatement.setStrTransactionID(accountTranMaster.getStrSys_id());
		accountStatement.setStrParticipantId(accountTranMaster.getStrParticipantId());
		
		accountStatement.setStrIsGLType("Y");
		accountStatement.setStrTransactionMode("CREDIT");
		accountStatement.setStrTransactionType("Deposit");
		
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccountStatementApi() +"/add";
		AccountStatement responseEntity = this.restTemplate.postForObject(serverUrl , accountStatement, AccountStatement.class);
		return responseEntity.getStrID();
	}
	
	@Override
	public List<AccountTypeMaster> getAccountTypeMasterList() 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + AMS_ACCOUNT_TYPE_API +"/getAccountType";
			AccountTypeMaster[] responseEntity = this.restTemplate.getForObject(serverUrl , AccountTypeMaster[].class);
			List<AccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
			return accountTypeMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int addAccountTypeMaster(AccountTypeMaster accountTypeMaster) 
	{
		int count = 0;
		
		String accNumber = Utils.getGeneratedAcNumber(accountTypeMaster.getStrAccNumLength(), accountTypeMaster.getStrAccNumStartDigit());
		accountTypeMaster.setStrLastAccNumber(accNumber);
		
		String apiUrl = AMS_MAIN_API_URL + AMS_ACCOUNT_TYPE_API + "/saveAccountTypeInfo";
		
		String result = this.restTemplate.postForObject(apiUrl, accountTypeMaster, String.class);
		if (result != null && result.trim().equals("success"))
		{
			return 1;
		}		
		return count;
	}
	
	@Override
	public boolean isAccountTypeAlreadyExist(AccountTypeMaster accountTypeMaster) throws Exception 
	{
		String serverUrl = AMS_MAIN_API_URL + AMS_ACCOUNT_TYPE_API +"/isAccountTypeExist";
		Boolean responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMaster, Boolean.class);
		return responseEntity.booleanValue();
	}
	
	@Override
	public List<CategoryListModel> getAccountTypeCategoryList(CategoryListModel categoryListModel) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAccountTypeCategoryApi() +"/getAccountTypeCategory";
			CategoryListModel[] responseEntity = this.restTemplate.postForObject(serverUrl , categoryListModel, CategoryListModel[].class);
			List<CategoryListModel> categoryListModelslist = Arrays.asList(responseEntity);
			return categoryListModelslist;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AccountTypeMaster> getAccountTypeMasterDataListByParticipantWise(String participantId) 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + AMS_ACCOUNT_TYPE_API +"/getACTypeListByParticipantWise";
			AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , participantId, AccountTypeMaster[].class);
			List<AccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
			return accountTypeMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AccountCreditLimitCategory> getAccountCreditLimitCategoryListByParticipantWise(String participantId)
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCreditLimitApi() +"/getCreditLimitListByParticipantWise";
			AccountCreditLimitCategory[] responseEntity = this.restTemplate.postForObject(serverUrl , participantId, AccountCreditLimitCategory[].class);
			List<AccountCreditLimitCategory> accountCreditLimitCategories = Arrays.asList(responseEntity);
			return accountCreditLimitCategories;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<TaxConfigModel> getTaxTypeConfigList(TaxConfigModel taxConfigModel) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getTaxTypeConfigApi() +"/getTaxTypeConfig";
			TaxConfigModel[] responseEntity = this.restTemplate.postForObject(serverUrl , taxConfigModel, TaxConfigModel[].class);
			List<TaxConfigModel> taxConfigModelslist = Arrays.asList(responseEntity);
			return taxConfigModelslist;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public AccountTranMaster addAccountTransactionMasterForGLAccount(GLAccountLoadingMaster glAccountLoadingMaster) throws Exception 
	{
		AccountTranMaster accountTranMaster = new AccountTranMaster();
		
		accountTranMaster.setStrSys_id(glAccountLoadingMaster.getStrTransactionId());
		accountTranMaster.setStrTransaction_amount(glAccountLoadingMaster.getStrLoadedBalance());
		accountTranMaster.setStrParticipantId(glAccountLoadingMaster.getStrParticipantId());
		
		accountTranMaster.setStrTran_type("CR");
		accountTranMaster.setStrLocal_tran_time(Utils.getLocalTime());
		accountTranMaster.setStrLocal_tran_date(Utils.getLocalDate());
		
		return addAccountTranMaster(accountTranMaster);
	}
	
	@Override
	public int addAccountCreation(AccountCreation accountCreation) 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountApi() +"/saveAccountInfo";
			String resultCount = this.restTemplate.postForObject(serverUrl, accountCreation, String.class);
			
			if (resultCount!=null) 
			{
				int result = Integer.parseInt(resultCount);
				return result;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<AccountCreation> getIssuedAccountBasedOnCardLinked(AccountCreation accountCreation) throws Exception 
	{
		String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountApi() +"/getIssuedAccount";
		AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation[].class);
		List<AccountCreation> accountCreationsList = Arrays.asList(responseEntity);
		return accountCreationsList;
	}
	
	@Override
	public String creatingInstantAccount(InstantAccountCreation instantAccountCreation) 
	{
		String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsInstantAccountApi() +"/create";
		return this.restTemplate.postForObject(serverUrl, instantAccountCreation, String.class);
	}
	
	@Override
	public AccountCreditLimitCategory saveAccountCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCreditLimitApi() +"/add";
			AccountCreditLimitCategory responseEntity = this.restTemplate.postForObject(serverUrl , accountCreditLimitCategory, AccountCreditLimitCategory.class);
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<MerchantCategoryCodeMaster> getMCCList(MerchantCategoryCodeMaster merchantCategoryCodeMaster)
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsMccCodeApi() +"/getAllMccCode";
			MerchantCategoryCodeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , merchantCategoryCodeMaster, MerchantCategoryCodeMaster[].class);
			List<MerchantCategoryCodeMaster> mcclistData = Arrays.asList(responseEntity);
			return mcclistData;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public MccWiseInterestModel saveMccInterest(MccWiseInterestModel mccWiseInterestModel) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsMccWiseInterestApi() +"/add";
			MccWiseInterestModel responseEntity = this.restTemplate.postForObject(serverUrl , mccWiseInterestModel, MccWiseInterestModel.class);
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<MccWiseInterestModel> getMccWiseInterest(MccWiseInterestModel mccWiseInterestModel) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsMccWiseInterestApi() +"/getMccWiseInterest";
			MccWiseInterestModel[] responseEntity = this.restTemplate.postForObject(serverUrl , mccWiseInterestModel, MccWiseInterestModel[].class);
			List<MccWiseInterestModel> mccWiseInterestModels = Arrays.asList(responseEntity);
			return mccWiseInterestModels;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<CardAccountLinkage> getCardAccountLinkageBasedOnAccount(CardAccountLinkage cardAccountLinkage) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCardAccountLinkageApi() +"/getLinkagedata-based-on-account";
			CardAccountLinkage[] responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, CardAccountLinkage[].class);
			List<CardAccountLinkage> cardAccountLinkages = Arrays.asList(responseEntity);
			return cardAccountLinkages;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AccountCreation getOutstandingAccount(AccountCreation accountCreation) throws Exception 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccountApi() +"/getCreditCardOutstanding";
		return this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation.class);
	}
	
	@Override
	public List<AccountInterestMaster> getOutStandingInterestList(AccountInterestMaster accountInterestMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountWiseCreditCardInterestApi() +"/getOutStandingInterest";
			AccountInterestMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountInterestMaster, AccountInterestMaster[].class);
			List<AccountInterestMaster> accountInterestMasters = Arrays.asList(responseEntity);
			return accountInterestMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AccountCreditCardTransactionModel> getAccountCreditCardTxn(AccountCreditCardTransactionModel accountCreditCardTransactionModel) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountCreditCardTxnApi() +"/getCreditCardTxnWise";
			AccountCreditCardTransactionModel[] responseEntity = this.restTemplate.postForObject(serverUrl , accountCreditCardTransactionModel, AccountCreditCardTransactionModel[].class);
			List<AccountCreditCardTransactionModel> accountInterestMasters = Arrays.asList(responseEntity);
			return accountInterestMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<CardAccountLinkage> getCardAccountLinkageDataBasedOnCard(CardAccountLinkage cardAccountLinkage) throws Exception {
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCardAccountLinkageApi() +"/getLinkagedata-based-on-card";
			CardAccountLinkage[] responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, CardAccountLinkage[].class);
			List<CardAccountLinkage> cardAccountLinkages = Arrays.asList(responseEntity);
			return cardAccountLinkages;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<PreAccountMaster> getPreAccountDataList(PreAccountMaster preAccountMaster) 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi() +"/getPreAccountData";
			PreAccountMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , preAccountMaster, PreAccountMaster[].class);
			List<PreAccountMaster> preAccountMasters = Arrays.asList(responseEntity);
			return preAccountMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AccountKycDetails getAccountKycDetailsInfo(AccountKycDetails accountKycDetails) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountWiseKycDetailsApi() +"/getSingleAccountKycDetail";
			AccountKycDetails responseEntity = this.restTemplate.postForObject(serverUrl , accountKycDetails, AccountKycDetails.class);
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<AccountCreation> getAccountInfoListBasedOnTypes(AccountCreation accountCreation)
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountApi() +"/getAccountInfoListBasedOnTypes";
			AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation[].class);
			List<AccountCreation> accountCreationlist = Arrays.asList(responseEntity);
			return accountCreationlist;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	///getPreAccount
	
	@Override
	public PreAccountMaster getPreAccountMaster(PreAccountMaster preAccountMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi() +"/getPreAccount";
			PreAccountMaster responseEntity = this.restTemplate.postForObject(serverUrl , preAccountMaster, PreAccountMaster.class);
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int updateAccountCreationFromInstanceAccount(AccountCreation accountCreation) throws Exception 
	{
		int count = 0;
		try {

			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountApi() +"/updateInstantAccountInfo";
			String resultCount = this.restTemplate.postForObject(serverUrl, accountCreation, String.class);
			
			if (resultCount!=null) 
			{
				int result = Integer.parseInt(resultCount);
				return result;
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public String sendMail(EmailTemplate emailTemplate) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsNotifyByApi() +"/sendEmail";
			String result = this.restTemplate.postForObject(serverUrl, emailTemplate, String.class);
			return result;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int updatePreAccountMasterAccount(PreAccountMaster preAccountMaster) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi() +"/updatePreAccountAccount";
			Integer resultCount	= this.restTemplate.postForObject(serverUrl, preAccountMaster, Integer.class);
			return resultCount.intValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public CardAccountLinkage addCardAccountLinkageData(CardAccountLinkage cardAccountLinkage) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCardAccountLinkageApi() +"/add";
			CardAccountLinkage responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, CardAccountLinkage.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isCardAccountLinkageDataExist(CardAccountLinkage cardAccountLinkage) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCardAccountLinkageApi() +"/checkDataExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Added by Pankaj P for Validate the values - Start
	@Override
	public Boolean validateMccWiseInterst(MccWiseInterestModel mccWiseInterestModel) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsMccWiseInterestApi() +"/validateMccWiseInterst";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , mccWiseInterestModel, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	// Added by Pankaj P for Validate the values - End

	@Override
	public List<IdentityProofDocumentTypeMaster> getIdentityProofDocumentTypeMasters(IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsIdentityProofDocumentTypeApi() +"/documentList";
			IdentityProofDocumentTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , identityProofDocumentTypeMaster, IdentityProofDocumentTypeMaster[].class);
			List<IdentityProofDocumentTypeMaster> identityProofDocumentTypeMasters = Arrays.asList(responseEntity);
			return identityProofDocumentTypeMasters;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AddressProofDocumentTypeMaster> getAddressProofDocumentTypeMasterList(AddressProofDocumentTypeMaster addressProofDocumentTypeMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAddressProofDocumentTypeApi() +"/documentList";
			AddressProofDocumentTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , addressProofDocumentTypeMaster, AddressProofDocumentTypeMaster[].class);
			List<AddressProofDocumentTypeMaster> addressProofDocumentTypeMasters = Arrays.asList(responseEntity);
			return addressProofDocumentTypeMasters;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Channels> getChannelsList(Channels channels) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChannelsApi() +"/getChannelList";
			Channels[] responseEntity = this.restTemplate.postForObject(serverUrl , channels, Channels[].class);
			List<Channels> channelList = Arrays.asList(responseEntity);
			return channelList;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AccountTransactionLimitation getAccountTxnLimitBasedOnParam(AccountTransactionLimitation accountTransactionLimitation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountTxnLimitApi() +"/getAccountTxnLimit";
			AccountTransactionLimitation responseEntity = this.restTemplate.postForObject(serverUrl , accountTransactionLimitation, AccountTransactionLimitation.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isCreditTypeAlreadyExist(AccountCreditLimitCategory accountCreditLimitCategory) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCreditLimitApi()+"/isCreditTypeExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , accountCreditLimitCategory, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public GLAccountCreation addGLAccountType(GLAccountCreation glAccountCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi() +"/add";
			GLAccountCreation responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, GLAccountCreation.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isGLAccountTypeAlreadyExist(GLAccountCreation glAccountCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi()+"/isGLAccountTypeExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isGLAccountAccountNumberAlreadyExist(GLAccountCreation glAccountCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi()+"/isGLAccountNumberExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int updateAccountAfterLinkedCard(AccountCreation accountCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountApi() +"/updateAccountCardLinked";
			Integer responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, Integer.class);
			return responseEntity.intValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public CardAccountLinkage getCardAccountLinkages(CardAccountLinkage cardAccountLinkage) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsCardAccountLinkageApi() +"/getLinkagedata";
			CardAccountLinkage responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, CardAccountLinkage.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CardAccountLinkage> getCardLinkAccountList(CardAccountLinkage cardAccountLinkage) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsCardAccountLinkageApi() +"/getCardLinkAccountList";
			CardAccountLinkage[] responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, CardAccountLinkage[].class);
			List<CardAccountLinkage> cardAccountLinkagesList = Arrays.asList(responseEntity);
			return cardAccountLinkagesList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<WalletAccountMaster> getLinkedAccountWalletList(WalletAccountMaster walletAccountMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsWalletAccountApi() +"/getLinkedAccountWalletList";
			WalletAccountMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , walletAccountMaster, WalletAccountMaster[].class);
			List<WalletAccountMaster> accountWalletList = Arrays.asList(responseEntity);
			return accountWalletList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountStatement> getAccountStatements(AccountStatement accountStatement) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountStatementApi() +"/getAccountStatements";
			AccountStatement[] responseEntity = this.restTemplate.postForObject(serverUrl , accountStatement, AccountStatement[].class);
			List<AccountStatement> accountStatements = Arrays.asList(responseEntity);
			return accountStatements;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountStatement> getAccountStatementByDate(AccountStatement accountStatement) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountStatementApi() +"/getAccountStatementsDateWise";
			AccountStatement[] responseEntity = this.restTemplate.postForObject(serverUrl , accountStatement, AccountStatement[].class);
			List<AccountStatement> accountStatements = Arrays.asList(responseEntity);
			return accountStatements;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isAccountAlreadyExist(AccountCreation accountCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountApi()+"/isAccountAlreadyExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public CustomerIdMap getCustomerId() throws Exception 
	{
		try 
		{
			CustomerIdMap customerIdMap = new CustomerIdMap();
			
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/getCustomerId";
			customerIdMap = this.restTemplate.getForObject(serverUrl, CustomerIdMap.class);
			
			if (customerIdMap != null) 
			{
				return customerIdMap;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int insertCustIdDetails(CustomerIdCreation customerIdCreation) throws Exception
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/insertCustId";
			CustomerIdCreation cusCreation	= this.restTemplate.postForObject(serverUrl, customerIdCreation, CustomerIdCreation.class);
			if (cusCreation!=null && cusCreation.getStrID()!=null)
			{
				return Integer.parseInt(cusCreation.getStrID());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCustId(CustomerIdTable customerIdTable) throws Exception {
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerIdTbleApi() +"/updateCustId";
			Integer resultCount	= this.restTemplate.postForObject(serverUrl, customerIdTable, Integer.class);
			return resultCount.intValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public CustomerIdCreation getCustMstrDetails(CustomerIdCreation customerIdCreation) throws Exception {
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/getCustomerInfo";
			CustomerIdCreation customerIdCreation2	= this.restTemplate.postForObject(serverUrl, customerIdCreation, CustomerIdCreation.class);
			return customerIdCreation2;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CustomerIdCreation getCustDetailsByMobileNumber(String mobileNumber) throws Exception {
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/getCustByMobileNumber";
			CustomerIdCreation customerIdCreation2	= this.restTemplate.postForObject(serverUrl, mobileNumber, CustomerIdCreation.class);
			return customerIdCreation2;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CustomerIdCreation> getCustomerDetailsbyCustId(CustomerIdCreation customerIDCreation) {
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/getCustomerInfo";
			CustomerIdCreation[] responseEntity	= this.restTemplate.postForObject(serverUrl, customerIDCreation, CustomerIdCreation[].class);
			List<CustomerIdCreation> customerIdCreation = Arrays.asList(responseEntity);
			return customerIdCreation;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int saveCustomerInformation(CustomerIdCreation customerIdCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/saveCustomerInfo";
			String resultCount = this.restTemplate.postForObject(serverUrl, customerIdCreation, String.class);
			
			if (resultCount!=null) 
			{
				int result = Integer.parseInt(resultCount);
				return result;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isCustomerAccountAlreadyConfigured(AccountCreation accountCreation) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountApi()+"/isCustomerAccountAlreadyConfigured";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CardAccountLinkage> getLinkageCardDetailsBasedOnCustId(CardAccountLinkage cardAccountLinkage) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsCardAccountLinkageApi() +"/getLinkageCardDetailsBasedOnCustId";
			CardAccountLinkage[] responseEntity = this.restTemplate.postForObject(serverUrl , cardAccountLinkage, CardAccountLinkage[].class);
			List<CardAccountLinkage> cardAccountLinkages = Arrays.asList(responseEntity);
			return cardAccountLinkages;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PreAccountMaster> getNonLinkedCustomersListForCustomerId(PreAccountMaster preAccountMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi() +"/nonLinkedCustomersForCutomerId";
			PreAccountMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , preAccountMaster, PreAccountMaster[].class);
			List<PreAccountMaster> preAccountMasters = Arrays.asList(responseEntity);
			return preAccountMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PreAccountMaster> getNonLinkedCustomersListForAccountNo(PreAccountMaster preAccountMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi() +"/nonLinkedCustomerForAccountNo";
			PreAccountMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , preAccountMaster, PreAccountMaster[].class);
			List<PreAccountMaster> preAccountMasters = Arrays.asList(responseEntity);
			return preAccountMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updatePreAccountAccountBasedOnParam(PreAccountMaster preAccountMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi() +"/updatePreAccountAccountBasedOnParam";
			Integer resultCount	= this.restTemplate.postForObject(serverUrl, preAccountMaster, Integer.class);
			return resultCount.intValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ChargeRelatedMaster> getChargeRelatedList(ChargeRelatedMaster chargeRelatedMaster) throws Exception {
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChargeRelatedApi() + "/getchargeRelatedList";
			ChargeRelatedMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, chargeRelatedMaster,
					ChargeRelatedMaster[].class);
			List<ChargeRelatedMaster> chargeRelatedList = Arrays.asList(responseEntity);
			return chargeRelatedList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addChargeType(ChargeMaster chargeMaster) {
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChargeTypeApi() + "/addChargeType";
			String resultCnt = this.restTemplate.postForObject(serverUrl, chargeMaster, String.class);

			if (resultCnt != null) {
				int result = Integer.parseInt(resultCnt);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getChargeRelatedDescription(ChargeRelatedMaster chargeRelatedMaster) {
		String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChargeRelatedApi() + "/getChargeRelatedDescription";
		String responseEntity = this.restTemplate.postForObject(serverUrl, chargeRelatedMaster, String.class);
		return responseEntity;
	}

	@Override
	public Boolean validateChargeType(ChargeMaster chargeMaster) throws Exception {
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChargeTypeApi()
					+ "/validateChargeType";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl, chargeMaster, Boolean.class);
			return responseEntity.booleanValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Added by prashant Tayde for is credit acc type Y --Revolving Credit
	@Override
	public List<AccountTypeMaster> getAccountTypeForIsCredit(AccountTypeMaster accountTypeMaster) throws Exception {
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() + "/getCreditAccountType";
		AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, accountTypeMaster,
				AccountTypeMaster[].class);
		List<AccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
		return accountTypeMasters;
	}

	@Override
	public int addRevolvingCreditCardData(RevolvingCreditCardMaster revolvingCreditCardMaster) throws Exception {
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsRevolvingCreditCard() + "/addRevolvingCreditCard";
			String resultCnt = this.restTemplate.postForObject(serverUrl, revolvingCreditCardMaster, String.class);

			if (resultCnt != null) {
				int result = Integer.parseInt(resultCnt);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int updateIsRevolvingCredit(AccountTypeMaster accountTypeMaster) 
	{
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountType()	+ "/updateIsRevolvingCredit";
			Integer resultCount = this.restTemplate.postForObject(serverUrl, accountTypeMaster, Integer.class);
			return resultCount.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateIsAccountNoCreatedField(PreSubAccountMaster preSubAccountMaster) throws Exception 
	{
		try 
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreSubAccountMasterApi() +"/updateIsAccountNoCreatedField";
			Integer resultCount	= this.restTemplate.postForObject(serverUrl, preSubAccountMaster, Integer.class);
			return resultCount.intValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//Abhishek T to view mcc_wise_interest table start
		@Override
		public List<MccWiseInterestModel> getmccwisedata(MccWiseInterestModel mccWiseInterestModel) throws Exception
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsMccWiseInterestApi() +"/getMccWiseInterestView";
				MccWiseInterestModel[] responseEntity = this.restTemplate.postForObject(serverUrl ,mccWiseInterestModel, MccWiseInterestModel[].class);
				List<MccWiseInterestModel> mccWiseInterestModels = Arrays.asList(responseEntity);
				return mccWiseInterestModels;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<GLAccountTypeMaster> getGLAccountcreationdata(GLAccountTypeMaster glAccountviewModel) throws Exception 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeApi()  +"/getGlAccountTypedata";
				GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl ,glAccountviewModel, GLAccountTypeMaster[].class);
				List<GLAccountTypeMaster> glAccountviewModels = Arrays.asList(responseEntity);
				return glAccountviewModels ;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Abhishek T -start
		@Override
		public List<AccountTypeMaster> getYCreditAccounTypeObject(AccountTypeMaster accountTypeMster) throws Exception
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() + "/getYCreditAccounType";
			AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMster, AccountTypeMaster[].class);
			List<AccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
			return accountTypeMasters;
		}
		//Abhishek T-End
		
		@Override
		public int addChargeRelatedData(ChargeRelatedMaster chargeRelatedMaster) throws Exception 
		{
			try {
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChargeRelatedApi() + "/addChargeRelated";
				String resultCnt = this.restTemplate.postForObject(serverUrl, chargeRelatedMaster, String.class);

				if (resultCnt != null) {
					int result = Integer.parseInt(resultCnt);
					return result;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public Boolean validateChargeRelated(ChargeRelatedMaster chargeRelatedMaster) throws Exception{
			try {
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsChargeRelatedApi() + "/validateChargeRelated";
				Boolean responseEntity = this.restTemplate.postForObject(serverUrl, chargeRelatedMaster, Boolean.class);
				return responseEntity.booleanValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public int updateIsRevolvingCreditFromMcc(AccountTypeMaster accountTypeMaster) 
		{
			try {
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountType() + "/updateIsRevolvingCreditFromMcc";
				Integer resultCount = this.restTemplate.postForObject(serverUrl, accountTypeMaster, Integer.class);
				return resultCount.intValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		//Added by Abhishek T for AccountTypeCategory on 17-03-2023 =start
		@Override
		public List<Categorytype> getcategoryList(Categorytype categorytype) {
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCategoryType() +"/categoryTypeData";
			Categorytype[] responseEntity	= this.restTemplate.postForObject(serverUrl, categorytype, Categorytype[].class);
			List<Categorytype> cid = Arrays.asList(responseEntity);
			return cid;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
			return null;
		}
		//Added by Abhishek T for AccountTypeCategory on 17-03-2023 =End

		@Override
		public boolean isTypeAlreadyExist(CategoryListModel categoryListModel) {
			try {
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCategoryType() +"/categorytypeExit";
				Boolean responseEntity = this.restTemplate.postForObject(serverUrl , categoryListModel, Boolean.class);
				return responseEntity.booleanValue();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public CategoryListModel saveCategoryListModel(CategoryListModel categoryListModel) throws Exception {
			try {
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCategoryType() +"/save";
				CategoryListModel responseEntity = this.restTemplate.postForObject(serverUrl , categoryListModel, CategoryListModel.class);
				return responseEntity;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public TransactionTypeModel addTranscationTypeData(TransactionTypeModel transactionTypeModel)  throws Exception
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsTransactionTypeCreationApi() + "/saveTxnTypeCreationData";
				TransactionTypeModel responseEntity = this.restTemplate.postForObject(serverUrl , transactionTypeModel, TransactionTypeModel.class);
				return responseEntity;
				
			} catch (Exception e) {
					e.printStackTrace();
			}
			return null;
			
		}

		@Override
		public int updateCustomerAccountDetails(CustomerIdCreation customerIdCreation) throws Exception 
		{
			int count = 0;
			try {
				String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsCustomerApi() +"/updateCustomerAccountDetails";
				String resultCount = this.restTemplate.postForObject(serverUrl, customerIdCreation, String.class);
				if (resultCount!=null) 
				{
					int result = Integer.parseInt(resultCount);
					return result;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		
		}

		@Override
		public List<CustomerIdCreation> getCustomerAccountDetails(CustomerIdCreation customerIdCreation)
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/getCustomerAccountInfo";
				CustomerIdCreation[] responseEntity	= this.restTemplate.postForObject(serverUrl, customerIdCreation, CustomerIdCreation[].class);
				List<CustomerIdCreation> cid = Arrays.asList(responseEntity);
				return cid;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<CustomerIdCreation> getCustomerAccountDetailsBasedOnCustId(CustomerIdCreation customerIdCreation) 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() +"/getCustomerAccountDetailsBasedOnCustId";
				CustomerIdCreation[] responseEntity	= this.restTemplate.postForObject(serverUrl, customerIdCreation, CustomerIdCreation[].class);
				List<CustomerIdCreation> cid = Arrays.asList(responseEntity);
				return cid;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		
		/* Changes done for QrCode Genaration and update in account_master - Start */
		@Override
		public void updateQrCodeEntry(Map<String, String> qrCodeMap) {
			try 
			{
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsQrCodeController() +"/genarate";
				this.restTemplate.postForObject(serverUrl , qrCodeMap, String.class);
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
		/* Changes done for QrCode Genaration and update in account_master - End */
		
		/* Added transaction data related changes Start */		
		

		@Override
		public int updateBalnceLimitValues(AccountCreation accountCreation) {
			int count = 0;
			try {

				String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountApi()+"/updateBalnceLimitValues";
				String resultCount = this.restTemplate.postForObject(serverUrl, accountCreation, String.class);
				
				if (resultCount!=null) 
				{
					int result = Integer.parseInt(resultCount);
					return result;
				}
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}

		@Override
		public int updateAccountStatment(AccountStatement accountStatement) {
			int count = 0;
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountStatementApi() +"/add";
				String resultCount = this.restTemplate.postForObject(serverUrl, accountStatement, String.class);
				System.out.println("resultCount:::"+resultCount);
				// System.out.println("resultCount:::"+Integer.valueOf(resultCount));
				
				if (resultCount!=null) 
				{
					int result = 1;
					return result;
				}
				System.out.println("result::::::"+count);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return count;	
		}

		@Override
		public int insertEntry(TranMaster tranMaster) {
			int count = 0;
			try{

				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getTranMasterApi() +"/add";
				String resultCount = this.restTemplate.postForObject(serverUrl, tranMaster, String.class);
				System.out.println("resultCount:::"+resultCount);
				// System.out.println("resultCount:::"+Integer.valueOf(resultCount));
				
				if (resultCount!=null) 
				{
					int result = 1;
					return result;
				}
				System.out.println("result::::::"+count);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return count;	
		}

		@Override
		public String getisRevolingCredit(String accountType) {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() +"/getisRevolingCredit";
				String responseEntity = this.restTemplate.postForObject(serverUrl , accountType, String.class);
				return responseEntity;
		}

		@Override
		public String getGracePeriod(String accntType) {
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsrevolvingCreditCardMasterApi() +"/getGracePeriod";
			String responseEntity = this.restTemplate.postForObject(serverUrl , accntType, String.class);
			return responseEntity;
		}

		@Override
		public String getGracePeriodfrmMCCwise(String mcc) {
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsMccWiseInterestApi() +"/getGracePeriod";
			String responseEntity = this.restTemplate.postForObject(serverUrl , mcc, String.class);
			return responseEntity;
		}

		@Override
		public int insertEntryCrdTxnMaster(RevolvingCreditCardTxnMaster revolvingCreditCardTxnMaster) {
			int count = 0;
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsrevolvingCreditCardTxnApi() +"/add";
			String responseEntity = this.restTemplate.postForObject(serverUrl , revolvingCreditCardTxnMaster, String.class);
			if(responseEntity != null)
			{
				count++;
			}
			return count;
		}

		@Override
		public int insertEntryCrdInt(RevolvingCreditInterestTxn revolvingCreditInterestTxn) {
			int count = 0;
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsrevolvingCreditCardIntApi() +"/add";
			String responseEntity = this.restTemplate.postForObject(serverUrl , revolvingCreditInterestTxn, String.class);
			if(responseEntity != null)
			{
				count = 1;
			}
			return count;
			
		}
		/* Added transaction data related changes End */

		@Override
		public Map<String, String> getInfofrmCardLinkage(String cardNumber) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int insertLimitinAccountMaster(AccountCreation accountCreation) {
			int count = 0;
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() +"/saveLimitValues";
			String responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, String.class);
			if(responseEntity != null)
			{
				count = 1;
			}
			return count;
		}

		@Override
		public String getCreditLimit(AccountCreditLimitCategory accCreditLimitCatogory) {
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsCreditLimitApi() +"/getCreditLimit";
			String responseEntity = this.restTemplate.postForObject(serverUrl , accCreditLimitCatogory, String.class);
			return responseEntity;
		}

		@Override
		public boolean validateRevolvingData(RevolvingCreditCardMaster revolvingCreditCardMaster) {
			try {
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsRevolvingCreditCard() +"/validateRevolvingCreditCard";
				Boolean responseEntity = this.restTemplate.postForObject(serverUrl , revolvingCreditCardMaster, Boolean.class);
				return responseEntity.booleanValue();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public List<GLAccountCreation> getGlAccountTypeAccountNoList(GLAccountCreation glAccountCreation) 
		{
			{
				try 
				{
					String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi() +"/getGlAccTypeAccNumber";
					GLAccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, GLAccountCreation[].class);
					List<GLAccountCreation> list = Arrays.asList(responseEntity);
					return list;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}			
		}
		
		
		@Override
		public List<GLAccountCreation> getGlAccountTypeAccountNoListForThirdPartyY(GLAccountCreation glAccountCreation) 
		{
				try 
				{
					String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi() +"/getGlAccTypeAccNumberList";
					GLAccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, GLAccountCreation[].class);
					List<GLAccountCreation> list = Arrays.asList(responseEntity);
					return list;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}			
		
		
		@Override
		public String addAccountStatementForGLAccountStatement(AccountTranMaster accountTranMaster,	String closingBalance) throws Exception
		{
			GLAccountStatement gLAccountStatement = new GLAccountStatement();
			gLAccountStatement.setParticipantIdString(accountTranMaster.getStrParticipantId());
			gLAccountStatement.setStrAccountNumber(accountTranMaster.getStrAccountNumber());
			gLAccountStatement.setStrClosingBalance(closingBalance);
			
			gLAccountStatement.setStrAmount(accountTranMaster.getStrTransaction_amount());
			gLAccountStatement.setStrGLAccountType(accountTranMaster.getStrAccountType());
			gLAccountStatement.setStrCreatedDate(Utils.getLocalDate());
			gLAccountStatement.setStrTxnId(accountTranMaster.getStrSys_id());
			gLAccountStatement.setTransactionDate(new Date());
			gLAccountStatement.setStrTranType(accountTranMaster.getStrAccountType());
			gLAccountStatement.setStrRef("CI Charges");
			gLAccountStatement.setStrTranMode("Deposit");
			
			String serverUrlforGL = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsGLAccountStatementApi() +"/add";
			System.out.println("Servergl"+serverUrlforGL);
			GLAccountStatement responseEntityforGL = this.restTemplate.postForObject(serverUrlforGL , gLAccountStatement, GLAccountStatement.class);
			return responseEntityforGL.getStrID();
		}

		@Override
		public TxnReqRes sendTransactionDataViaSwitch(TxnReqRes txnReqRes) 
		{
			try 
			{
				TxnReqRes txnReqResponse = null;
				String serverUrl = SERVER_AMS_MAIN_API_URL + apiPropertyConfig.getAmsTransactionHandler()+"/processTxn";
				System.out.println("serverUrl:::::::["+serverUrl+"]");
				txnReqResponse = this.restTemplate.postForObject(serverUrl, txnReqRes, TxnReqRes.class);
				
				List<TxnReqRes> tranMasterResponse = Arrays.asList(txnReqRes);
				System.out.println("tranMasterResponse::::"+tranMasterResponse);
				if (txnReqResponse != null) 
				{
					 return txnReqResponse;
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public String addGLAccountEntry(GLAccountTypeMaster glAccountTypeMaster) throws Exception 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeApi() +"/addGLAccountEntry";
				GLAccountTypeMaster responseEntity = this.restTemplate.postForObject(serverUrl , glAccountTypeMaster, GLAccountTypeMaster.class);
				if(responseEntity != null) 
				{
					return responseEntity.getStrID();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public TxnReqRes loadAccountBalance(TxnReqRes txnReqRes) throws Exception 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsTransactionHandler() +"/processToLoadBalance";
			System.out.println("loadAccountBalance:: serverUrl=["+serverUrl+"]");
			return this.restTemplate.postForObject(serverUrl , txnReqRes, TxnReqRes.class);
		}
		
		@Override
		public String addGLAccountStatementbyLoadingBalance(AccountCreation accountCreation,
				String closingBalance) throws Exception 
		{
			GLAccountStatement gLAccountStatement = new GLAccountStatement();
			gLAccountStatement.setParticipantIdString(accountCreation.getStrParticipantID());
			gLAccountStatement.setStrAccountNumber(accountCreation.getStrAccountNumber());
			gLAccountStatement.setStrClosingBalance(closingBalance);
			gLAccountStatement.setStrCreatedBy(accountCreation.getStrCreatedBy());
			
			gLAccountStatement.setStrAmount(closingBalance);
			gLAccountStatement.setStrCreated_by(accountCreation.getStrCreatedBy());
			gLAccountStatement.setStrGLAccountType(accountCreation.getStrAccountType());
			gLAccountStatement.setStrCreatedDate(Utils.getLocalDate());
			//gLAccountStatement.setStrTxnId(accountCreation);
			gLAccountStatement.setTransactionDate(new Date());
			gLAccountStatement.setStrTranType(accountCreation.getStrAccountType());
			gLAccountStatement.setStrRef("CI Charges");
			gLAccountStatement.setStrTranMode("Deposit");
			
			String serverUrlforGL = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsGLAccountStatementApi() +"/add";
			System.out.println("Servergl"+serverUrlforGL);
			GLAccountStatement responseEntityforGL = this.restTemplate.postForObject(serverUrlforGL , gLAccountStatement, GLAccountStatement.class);
			return responseEntityforGL.getStrID();
		}
		// Added by pankaj pawar for charts of accounts menu [Start]
		@Override
		public List<GLAccountTypeMaster> getGLAccountDetailsList(GLAccountTypeMaster gLAccountTypeMaster) {
			try
			{
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsGlAccountTypeApi() + "/getGLAccountList";
				GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , gLAccountTypeMaster, GLAccountTypeMaster[].class);
				List<GLAccountTypeMaster> gLAccountTypeMasterResp = Arrays.asList(responseEntity);
				return gLAccountTypeMasterResp;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		// Added by pankaj pawar for charts of accounts menu [End]
		
		//created by ankit
		@Override
		public List<GLAccountTypeMaster> getGLAccountTypeList() {	
			try 
			{
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsGlAccountTypeApi() + "/getThirdPartyAllowGLAccountTypeList";
				GLAccountTypeMaster[] responseEntity = this.restTemplate.getForObject(serverUrl , GLAccountTypeMaster[].class);
				List<GLAccountTypeMaster> glAccountTypeMasters = Arrays.asList(responseEntity);
				return glAccountTypeMasters;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public String journalTransfer(JournalTransfer journalTransfer)
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsJournalTransferApi() + "/add";
				String responseEntity = this.restTemplate.postForObject(serverUrl, journalTransfer, String.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//created by ankit

		//created by ankit
		@Override
		public List<GLAccountTypeMaster> getGLAvailableBalnce(GLAccountTypeMaster glAccountTypeMaster) {
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsGlAccountTypeApi() + "/getGLAccountData";
				GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , glAccountTypeMaster , GLAccountTypeMaster[].class);
				List<GLAccountTypeMaster> glAccountTypeMasters = Arrays.asList(responseEntity);
				return glAccountTypeMasters;
				}catch(Exception e) {
					e.printStackTrace();
				}
				return null;
		}
		//created by ankit
		
		
		//Created by ankit --not in use
		@Override
		public String journalTransferGLToAcc(JournalTransfer journalTransfer) {
			return null;
		}
		//Created by ankit --not in use

		//created by ankit 
		@Override
		public List<AccountCreation> getAvailableBalnceOfAccount(AccountCreation accountCreation) {
			try {
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountApi() + "/getAccountBalanceAndName";
			AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl ,  accountCreation , AccountCreation[].class);
			List<AccountCreation> accountCreation2 = Arrays.asList(responseEntity);
			return accountCreation2;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//created by ankit 

		
		@Override
		public List<AccountTypeMaster> getStrAccDescription(AccountTypeMaster accountTypeMaster) {
			
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() + "/getAccDescription";
				AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMaster , AccountTypeMaster[].class);
				List<AccountTypeMaster> glAccountTypeMasters = Arrays.asList(responseEntity);
				return glAccountTypeMasters;
				}catch(Exception e) {
					e.printStackTrace();
				}
			return null;
		}

		@Override
		public List<GLAccountTypeMaster> getStrGLDescription(GLAccountTypeMaster glAccountTypeMaster) {
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsGlAccountTypeApi() + "/getGLDescription";
				GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , glAccountTypeMaster , GLAccountTypeMaster[].class);
				List<GLAccountTypeMaster> glAccountTypeMasters = Arrays.asList(responseEntity);
				return glAccountTypeMasters;
				}catch(Exception e) {
					e.printStackTrace();
				}
				return null;
		}
		//created by ankit -end-
		
		@Override
		public List<JournalTransfer> getAccountAuthoriseDetailsList(JournalTransfer journalTransfer) 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsJournalTransferApi() + "/getAccountauthoriselist";
				JournalTransfer[] responseEntity = this.restTemplate.postForObject(serverUrl , journalTransfer, JournalTransfer[].class);
				List<JournalTransfer> accountCreationAuthoriselist = Arrays.asList(responseEntity);
				return accountCreationAuthoriselist;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<JournalTransfer> getTxnIdApprovallist(JournalTransfer journalTransfer) 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsJournalTransferApi() + "/getTxnIdAuthoriselist";
				JournalTransfer[] responseEntity = this.restTemplate.postForObject(serverUrl, journalTransfer, JournalTransfer[].class);
				List<JournalTransfer> TxnIdAuthoriselist = Arrays.asList(responseEntity);
				return TxnIdAuthoriselist;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
      }

		@Override
		public int UpdateReasonDetails(JournalTransfer journalTransfer) 
		{
			try {
				String serverUrl =  AMS_MAIN_API_URL + apiPropertyConfig.getAmsJournalTransferApi() + "/updateReasonInfo";
				String resultCount = this.restTemplate.postForObject(serverUrl, journalTransfer, String.class);
				System.out.println("resultCount:::"+resultCount);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
		}

		@Override
		public JournalTransfer processToApproveJournalTransfer(JournalTransfer journalTransfer) 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsJournalTransferApi() + "/approve";
				JournalTransfer responseEntity = this.restTemplate.postForObject(serverUrl , journalTransfer, JournalTransfer.class);
				return responseEntity;
				
			} catch (Exception e) {
					e.printStackTrace();
			}
			return null;
		}

		@Override
		public JournalTransfer processToRejectJournalTransfer(JournalTransfer journalTransfer)
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsJournalTransferApi() + "/reject";
				JournalTransfer responseEntity = this.restTemplate.postForObject(serverUrl , journalTransfer, JournalTransfer.class);
				return responseEntity;
				
			} catch (Exception e) {
					e.printStackTrace();
			}
			return null;
		}
		
		//Add for Insert Data in PreAccountMaster
		@Override
		public int insertPrecaccountMaster(PreAccountMaster preAccountMaster) 
		{
			try {
				String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getPreAccountMasterApi() +"/insertDatalist";
				PreAccountMaster responseEntity = this.restTemplate.postForObject(serverUrl, preAccountMaster, PreAccountMaster.class);
				System.out.println("responseEntity:::"+responseEntity);
				if(responseEntity!=null && responseEntity.getStrID()!=null) 
				{
					return 1;
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public List<CountryCodeMaster> getCountryCode(CountryCodeMaster countryCodeMaster)
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCountryCodeApi() + "/getCountryCode";
				CountryCodeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, countryCodeMaster, CountryCodeMaster[].class);
				List<CountryCodeMaster> countryCodeMasters = Arrays.asList(responseEntity);
				return countryCodeMasters;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public GLAccountCreation isGLAccountTypeAlreadyExistDetails(GLAccountCreation glAccountCreation) throws Exception 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi()+"/isGLAccountTypeExistdetails";
				GLAccountCreation responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, GLAccountCreation.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return glAccountCreation;
		}
		
		@Override
		public int updateGLClosingBalance(GLAccountCreation glAccountCreation) throws Exception 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi()+"/updateGLClosingBalance";
				int responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, Integer.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		@Override
		public GLAccountCreation getControlAccountTypeObj(GLAccountCreation glAccountCreation) throws Exception 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi()+"/glCtrAccountTypeObj";
				GLAccountCreation responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, GLAccountCreation.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return glAccountCreation;
		}
		
		@Override
		public GLAccountCreation getAllGLAccountBalanceTogether(GLAccountCreation glAccountCreation) throws Exception 
		{
			try 
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsGlAccountTypeCreationApi()+"/allGLAccountBalance";
				GLAccountCreation responseEntity = this.restTemplate.postForObject(serverUrl , glAccountCreation, GLAccountCreation.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return glAccountCreation;
		}
		@Override
		public List<GLAccountTypeMaster> getGLAccountTypeListExceptCTR() 
		{	
			try 
			{
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsGlAccountTypeApi() + "/getGLAccountTypeListExceptCTR";
				GLAccountTypeMaster[] responseEntity = this.restTemplate.getForObject(serverUrl , GLAccountTypeMaster[].class);
				List<GLAccountTypeMaster> glAccountTypeMasters = Arrays.asList(responseEntity);
				return glAccountTypeMasters;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Added By Sagar Khawse on 05-May-2023 Start
		@Override
		public int updateIsMobileCustIdlink(PreAccountMaster preAccountMaster) 
		{
			try {
				String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountApi() +"/updateMobileAgaintCustId";
				String resultCount = this.restTemplate.postForObject(serverUrl, preAccountMaster, String.class);
				System.out.println("resultCount:::"+resultCount);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
				
		//Add by Abhishek-Start
		@Override
		public List<PreAccountMaster> getRegisterCustomers(PreAccountMaster preAccountMaster) {
			try 
			{
				String ServerUrl=AMS_MAIN_API_URL + apiPropertyConfig.getPreAccountMasterApi()+"/registerCustomersList";
				PreAccountMaster[] responseEntity = this.restTemplate.postForObject(ServerUrl, preAccountMaster, PreAccountMaster[].class);
				List<PreAccountMaster> registeredCustlist = Arrays.asList(responseEntity);
				return registeredCustlist;
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}
		//Add by Abhishek-End

		@Override
		public List<AccountCreation> getRegCustWithLinkAccount(AccountCreation accountCreation) 
		{
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsAccountApi() + "/getRegCustWithLinkAccount";
				AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl, accountCreation,
						AccountCreation[].class);
				List<AccountCreation> accountCreationsList = Arrays.asList(responseEntity);
				return accountCreationsList;
			}
		}

		@Override
		public List<PreSubAccountMaster> getPendingRegCustWithLinkAccount(PreSubAccountMaster preSubAccountMaster)
		{
			{
				String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getPreSubAccountMasterApi()
						+ "/getPendingRegCustWithLinkAccount";
				PreSubAccountMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, preSubAccountMaster,
						PreSubAccountMaster[].class);
				List<PreSubAccountMaster> accountCreationsList = Arrays.asList(responseEntity);
				return accountCreationsList;
			}
		}

		@Override
		public List<DenominationMaster> getdenominationMasterDetails(DenominationMaster denominationMaster) 
		{
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsDenominationMasterApi() + "/getTxnDenominationDetails";
				DenominationMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, denominationMaster, DenominationMaster[].class);
				List<DenominationMaster> denominationMasterResp = Arrays.asList(responseEntity);
				return denominationMasterResp;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<TransactionTypeModel> getTxnTypeList(TransactionTypeModel transactionTypeModel) 
		{
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountReportApi() + "/getAccountTxnType";
				TransactionTypeModel[] responseEntity = this.restTemplate.postForObject(serverUrl, transactionTypeModel,
						TransactionTypeModel[].class);
				List<TransactionTypeModel> transactionType = Arrays.asList(responseEntity);
				return transactionType;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<JournalTransfer> getJournalStatementlist(JournalTransfer journalTransfer) 
		{
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountReportApi() + "/getJouranalTxnReport";
				JournalTransfer[] responseEntity = this.restTemplate.postForObject(serverUrl, journalTransfer,
						JournalTransfer[].class);
				List<JournalTransfer> JournalStatementlist = Arrays.asList(responseEntity);
				return JournalStatementlist;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<AccountTranMaster> getTxnReportlist(AccountTranMaster accountTranMaster) 
		{
			try {
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountReportApi() + "/getTxnReportStatement";
				AccountTranMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, accountTranMaster,
						AccountTranMaster[].class);
				List<AccountTranMaster> txnStatementlist = Arrays.asList(responseEntity);
				return txnStatementlist;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<DenominationMaster> getdenominationMasterDetailsbyAgent(DenominationMaster denominationMaster) 
		{
			try
			{
				//String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsDenominationMasterApi() + "/getTxnDenominationDetailsbyAgent";
				String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsDenominationMasterApi() + "/getTxnDenominationDetails";
				DenominationMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , denominationMaster, DenominationMaster[].class);
				List<DenominationMaster> denominationMasterResp = Arrays.asList(responseEntity);
				return denominationMasterResp;
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	//Added By Sagar Khawse  on 05-May-2023 End	
	@Override
	public List<AccountTranMaster> searchTransactionByTxnId(AccountTranMaster accountTranMaster) {
		try 
		{
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccoutTxnMaster() +"/searchTransactionByTxnId";
			AccountTranMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTranMaster, AccountTranMaster[].class);
			List<AccountTranMaster> txnbyId = Arrays.asList(responseEntity);
			return txnbyId;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Added by prashant Tayde for Charges
	/* @Override
	public ProcessResponse applyCardIssuanceCharges(CardAccountLinkage cardAccountLinkage) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountWiseCharges() + "/processToCharge";
			ProcessResponse processResponse = this.restTemplate.postForObject(serverUrl, cardAccountLinkage, ProcessResponse.class);
			if (processResponse != null) 
			{
				return processResponse;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	} */
	
	@Override
	public ProcessResponse applyCardIssuanceCharges(CardAccountLinkage cardAccountLinkage) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getCreditCardurl() + apiPropertyConfig.getAmsAccountType()+ "/chargesforrevolvingcreditcard";
			ProcessResponse processResponse = this.restTemplate.postForObject(serverUrl, cardAccountLinkage, ProcessResponse.class);
			if (processResponse != null) 
			{
				return processResponse;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public ProcessResponse applyCardAnnualCharges(AccountWiseCharges accountWiseCharges) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountWiseCharges() + "/processToAnnualCharge";
			ProcessResponse processResponse = this.restTemplate.postForObject(serverUrl, accountWiseCharges, ProcessResponse.class);
			if (processResponse != null) 
			{
				return processResponse;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Added by prashant Tayde For Application Call
	@Override
	public String setApplicationName(Map<Object, Object> map)
	{
		try
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsApplicationName() + "/setApplicationName";
			String responseEntity = this.restTemplate.postForObject(serverUrl, map, String.class);
			return responseEntity;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//Added by Prashant to identify application for nigeria client on 11-May-2023 Start
	@Override
	public String getActiveCurrentTierBasedOnMobileNo(String strMobileNo) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsCustomerApi()
					+ "/getActiveTier";
			String responseEntity = this.restTemplate.postForObject(serverUrl, strMobileNo, String.class);
			return responseEntity;
		
	}

	@Override
	public List<ApproveUpgradeTier> getUpgradeTierType(ApproveUpgradeTier approveUpgradeTier) 
	{
		String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsApproveTierType() + "/getUpgradeTierType";
		ApproveUpgradeTier[] responseEntity = this.restTemplate.postForObject(serverUrl, approveUpgradeTier,
				ApproveUpgradeTier[].class);
		List<ApproveUpgradeTier> approveUpgradeTierList = Arrays.asList(responseEntity);
		return approveUpgradeTierList;
	}

	@Override
	public List<CustomerIdCreation> getCustomerDetailsWithActiveTier(CustomerIdCreation customerIDCreation) 
	{
		try
		{
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCustomerApi() + "/getCustomerAccountUgradeInfo";
			CustomerIdCreation[] responseEntity = this.restTemplate.postForObject(serverUrl, customerIDCreation,
					CustomerIdCreation[].class);
			List<CustomerIdCreation> cid = Arrays.asList(responseEntity);
			return cid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Added by Prashant to identify application for nigeria client on 12-May-2023 Start		

	@Override
	public String updateUpgradeRequest(UpgradeTierReqRes upgradeTierReqRes) 
	{
		String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsApproveTierType() + "/"+upgradeTierReqRes.getAction();
		String responseEntity = this.restTemplate.postForObject(serverUrl, upgradeTierReqRes, String.class);
		return responseEntity;
	}
	
	//added by ankit start
	@Override
	public List<NubanTypeConfig> getNubanTypeAndDescription()
	{
	    try 
		{
		String serverUrl = AMS_MAIN_API_URL + "/nubanType" +"/getType";
		NubanTypeConfig[] responseEntity = this.restTemplate.getForObject(serverUrl , NubanTypeConfig[].class);
			List<NubanTypeConfig> nubanTypeList = Arrays.asList(responseEntity);
			return nubanTypeList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public NubanTypeConfig getNubanTypeDescription(NubanTypeConfig nubanTypeConfig)
	{
	    try 
		{		
    		String serverUrl = AMS_MAIN_API_URL + "/nubanType/getDescription";
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		HttpEntity<NubanTypeConfig> requestEntity = new HttpEntity<>(nubanTypeConfig, headers);
    		ResponseEntity<NubanTypeConfig> responseEntity = restTemplate.postForEntity(serverUrl, requestEntity, NubanTypeConfig.class);
    		return responseEntity.getBody();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addNubanCodeConfig(NubanCodeConfig nubanCodeConfig)
	{
	    try 
		{
		
		String strNubanType = nubanCodeConfig.getStrNubanType();
		String nubanCode;
		if(strNubanType.equals("DMB")) 
		{
		    nubanCode = nubanCodeConfig.getStrDMBNumber1() + nubanCodeConfig.getStrDMBNumber2();
		}
		else 
		{
		    nubanCode = nubanCodeConfig.getStrOFINumber1() + nubanCodeConfig.getStrOFINumber2();
		}
		nubanCodeConfig.setStrNubanCode(nubanCode);
		String serverUrl = AMS_MAIN_API_URL + "/nubanCode" +"/add";
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		HttpEntity<NubanCodeConfig> requestEntity = new HttpEntity<>(nubanCodeConfig, headers);
    		ResponseEntity<String> postForEntity = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
    		
    		int statusCodeValue = postForEntity.getStatusCodeValue();
    		if(statusCodeValue == 200) 
    		{
    		    return 1;
    		}
    		else
    		{
    			return 0;
    		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
		//added by ankit end
	}

	@Override
	public List<GLAccountTypeMaster> getGLAccountTypeLists() 
	{
		try 
		{
			GLAccountTypeMaster gLAccountTypeMaster = new GLAccountTypeMaster();
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl()
					+ apiPropertyConfig.getAmsGlAccountTypeApi() + "/getGLAccountList";
			GLAccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, gLAccountTypeMaster,
					GLAccountTypeMaster[].class);
			List<GLAccountTypeMaster> gLAccountTypeMasterResp = Arrays.asList(responseEntity);
			return gLAccountTypeMasterResp;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<TransactionTypeModel> getTransactionTypeModelData(TransactionTypeModel transactionTypeModel) {
		try {
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsTransactionTypeCreationApi() + "/getTransactionTypeModelData";
			TransactionTypeModel[] responseEntity = this.restTemplate.postForObject(serverUrl, transactionTypeModel,
					TransactionTypeModel[].class);
			List<TransactionTypeModel> cid = Arrays.asList(responseEntity);
			return cid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isGLAccountAlreadyExistAgainstAccountType(AccountTypeMaster accountTypeMaster) {
		String serverUrl = AMS_MAIN_API_URL + AMS_ACCOUNT_TYPE_API +"/isGLAccountTypeExist";
		Boolean responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMaster, Boolean.class);
		return responseEntity.booleanValue();
	}

	@Override
	public int updateAccountTypeCategory(CategoryListModel categoryListModel) {
		int count = 0;
		try {
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAccountTypeCategoryApi()+"/updateAccountTypeCategory";
			String resultCount = this.restTemplate.postForObject(serverUrl, categoryListModel, String.class);
			
			if (resultCount!=null) 
			{
				int result = Integer.parseInt(resultCount);
				return result;
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<AccountTypeMaster> getAccountTypeMasterDetailsBasedOnAccountType(AccountTypeMaster accountTypeMaster) {
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountType() + "/getAccountTypeMasterDetailsBasedOnAccountType";
			AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMaster, AccountTypeMaster[].class);
			List<AccountTypeMaster> accountTypeMasterslist = Arrays.asList(responseEntity);
			return accountTypeMasterslist;
		}

	}

	@Override
	public int updateAccountTypeDetails(AccountTypeMaster accountTypeMaster) {
		{
			int count = 0;
			try {
				String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsAccountType() +"/updateAccountTypeDetails";
				String resultCount = this.restTemplate.postForObject(serverUrl, accountTypeMaster, String.class);
				if (resultCount!=null) 
				{
					int result = Integer.parseInt(resultCount);
					return result;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
	}
	
	
	
	@Override
	public int updateAccountLimitCredit(AccountCreditLimitCategory accountCreditLimitCategory) 
	{
		try {
			String serverUrl = AMS_MAIN_API_URL +  apiPropertyConfig.getAmsCreditLimitApi()+"/updateCreditTypeCategoryLimit";
			String resultCount = this.restTemplate.postForObject(serverUrl, accountCreditLimitCategory, String.class);
			
			if (resultCount!=null) 
			{
				int result = Integer.parseInt(resultCount);
				return result;
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}   
	
		return 0; 	    
	}
	
	@Override
	public List<Categorytype> getCategoryTypeList(Categorytype categorytype) {
	try {
		String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsCategoryType() +"/getCategoryTypeList";
		Categorytype[] responseEntity	= this.restTemplate.postForObject(serverUrl, categorytype, Categorytype[].class);
		List<Categorytype> cid = Arrays.asList(responseEntity);
		return cid;
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	
	
	@Override
	public List<CategoryListModel> getCategoryTypeListData(CategoryListModel categoryListModels) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAccountTypeCategoryApi() + "/getCategoryTypeListData";
		CategoryListModel[] responseEntity = this.restTemplate.postForObject(serverUrl , categoryListModels, CategoryListModel[].class);
		List<CategoryListModel> categoryList = Arrays.asList(responseEntity);
		return categoryList;
	}
	
	
	@Override
	public List<AccountCreation> getStrAccountHolderName(AccountCreation accountCreation)
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsAccountApi() + "/getAccountHolderName";
			AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl, accountCreation, AccountCreation[].class);
			List<AccountCreation> accountCreationList = Arrays.asList(responseEntity);
			return accountCreationList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return null;
	}

	@Override
	public List<ProcessResponse> addOneToManyAccountsData(BulkTransfer bulkTransfer) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsBulkTransfer()+"/oneToMany";
			List<ProcessResponse> responseEntity = Arrays.asList(this.restTemplate.postForObject(serverUrl, bulkTransfer, ProcessResponse[].class));
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}   
		return null;
	}

	@Override
	public List<ProcessResponse> addManyToOneAccountsData(BulkTransfer bulkTransfer) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsBulkTransfer()+"/manyToOne";
			List<ProcessResponse> responseEntity = Arrays.asList(this.restTemplate.postForObject(serverUrl, bulkTransfer, ProcessResponse[].class));
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}   
		return null;
	}

	@Override
	public ResponseEntity<ProcessResponse> getAccountLimitInfoToCompareTransferAmount(BulkTransfer bulkTransfer) {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsBulkTransfer() + "/getAccountLimitInfoToCompareTransferAmount";
			
			//ResponseEntity<ProcessResponse> postForObject = this.restTemplate.postForObject(serverUrl, bulkTransfer, ResponseEntity.class);	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<BulkTransfer> request = new HttpEntity<>(bulkTransfer, headers);
			ResponseEntity<ProcessResponse> response = restTemplate.postForEntity(serverUrl, request, ProcessResponse.class);
			ProcessResponse processResponse = response.getBody();
			System.out.println("Response::::"+processResponse);
			return response;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CloseAccountResponse saveCloseAccountRequest(CloseAccountMaster closeAccountMaster) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAccountClouserMaster() +"/request";
		CloseAccountResponse responseEntity = this.restTemplate.postForObject(serverUrl , closeAccountMaster, CloseAccountResponse.class);
		return responseEntity;
	}
	
	@Override
	public List<CloseAccountMaster> getListOfAccountClouser() {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAccountClouserMaster() + "/accountmasterclouserlist";
			CloseAccountMaster[] responseEntity = this.restTemplate.getForObject(serverUrl , CloseAccountMaster[].class);
			List<CloseAccountMaster> listOfAccountClouser = Arrays.asList(responseEntity);
			return listOfAccountClouser;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CloseAccountMaster getCLoseAccountMasterDataByCustId(CloseAccountMaster closeAccountMaster) {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAccountClouserMaster() + "/getdataforclosingaccount";
			CloseAccountMaster responseEntity = this.restTemplate.postForObject(serverUrl , closeAccountMaster, CloseAccountMaster.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public CloseAccountMaster requestStatusApproveReject(CloseAccountMaster closeAccountMaster) {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAccountClouserMaster() + "/checkerprocess";
			CloseAccountMaster responseEntity = this.restTemplate.postForObject(serverUrl , closeAccountMaster, CloseAccountMaster.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<?> getCumlativeBalanceCompareTransAmt(BulkTransfer bulkTransfer) {
		try 
		{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsBulkTransfer() + "/getCumlativeBalanceCompareTransAmt";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BulkTransfer> request = new HttpEntity<>(bulkTransfer, headers);
		ResponseEntity<ProcessResponse> response = restTemplate.postForEntity(serverUrl, request, ProcessResponse.class);
		ProcessResponse processResponse = response.getBody();
		System.out.println("Response::::"+processResponse);
		return response;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}

	@Override
	public CloseAccountMaster getCloseAccountMasterDetails(CloseAccountMaster closeAccountMaster) {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAccountClouserMaster() + "/getCloseAccountMasterDetails";
			CloseAccountMaster responseEntity = this.restTemplate.postForObject(serverUrl , closeAccountMaster, CloseAccountMaster.class);
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public ProcessResponse addGLToManyAccountsData(BulkTransfer bulkTransfer) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsBulkTransfer()+"/glToMany";
			ProcessResponse responseEntity = this.restTemplate.postForObject(serverUrl, bulkTransfer, ProcessResponse.class);
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProcessResponse addManyToGLAccountsData(BulkTransfer bulkTransfer) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsBulkTransfer()+"/manyToGL";
			ProcessResponse responseEntity = this.restTemplate.postForObject(serverUrl, bulkTransfer, ProcessResponse.class);
			return responseEntity;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return null;
	}
	
	
	@Override
	public List<AccountMasterResponse> getAccountInformationForClosingAccount(AccountCreation accountCreation)
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() +  apiPropertyConfig.getAmsAccountApi() +"/getAccountInfoForClosingAccount";
		AccountMasterResponse[] responseEntity = this.restTemplate.postForObject(serverUrl, accountCreation, AccountMasterResponse[].class);
		List<AccountMasterResponse> accountTypeMasters = Arrays.asList(responseEntity);
		return accountTypeMasters;
	}
	
	@Override
	public ProcessResponse getThirdPartyBankList(ExternalServerRequestResponseModel externalServerRequestResponseModel) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsThirdPartyPath() + "/getThirdPartyBankList"; 
		ProcessResponse responseEntity = this.restTemplate.postForObject(serverUrl, externalServerRequestResponseModel, ProcessResponse.class);
		return responseEntity;
	}

	@Override
	public ProcessResponse getThirdPartyAccountName(ExternalServerRequestResponseModel externalServerRequestResponseModel) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsThirdPartyPath() + "/getThirdPartyAccountName";
		ProcessResponse responseEntity = this.restTemplate.postForObject(serverUrl, externalServerRequestResponseModel, ProcessResponse.class);
		return responseEntity;
	}
	
	
	//added by Prashant Tayde 30 Aug 2023
	@Override
	public List<VatTypeMaster> getVatTypeList(VatTypeMaster vatTypeMaster) {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getVatTypeMaster() + "/getVatTypeList";
			VatTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl ,vatTypeMaster, VatTypeMaster[].class);
			List<VatTypeMaster> vatTypeList = Arrays.asList(responseEntity);
			return vatTypeList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public int addVatTypeMappedData(VatTypeMaster vatTypeMaster) 
	{
		//int count = 0;
		try {
		
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getVatTypeMaster()+"/addVatTypeMapping";
			String resultcount  = this.restTemplate.postForObject(serverUrl , vatTypeMaster, String.class);
			if (resultcount != null)
			{
				int result = Integer.parseInt(resultcount);
				return result;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("AccountManagementServiceImpl.addVatTypeMappedData()"+e);
		}
		return 0;
	}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public Boolean validateVatType(VatTypeMaster vatTypeMaster) {
		try {
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getVatTypeMaster() + "/validateVatType";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl, vatTypeMaster, Boolean.class);
			return responseEntity.booleanValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public boolean validateFeeType(FeeTypeMaster feeTypeMaster) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getFeeTypeMaster() +"/validateFeeType";
		Boolean responseEntity = this.restTemplate.postForObject(serverUrl , feeTypeMaster, Boolean.class);
		return responseEntity.booleanValue();
	}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public int addFeeTypeMappedData(FeeTypeMaster feeTypeMaster) {
		
		try {
			
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getFeeTypeMaster()+"/addFeeTypeMapping";
			String resultcount  = this.restTemplate.postForObject(serverUrl , feeTypeMaster, String.class);
			if (resultcount != null)
			{
				int result = Integer.parseInt(resultcount);
				return result;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("AccountManagementServiceImpl.addFeeTypeMappedData()"+e);
		}
		return 0;
	}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public boolean validateVatTypeList(FeeTypeMaster feeTypeMaster) {
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getFeeTypeMaster() +"/isVatTypeAlreadyExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , feeTypeMaster, Boolean.class);
			return responseEntity.booleanValue();
		}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public Boolean isTransactionTypeAlreadyExist(TransactionTypeModel transactionTypeModel) 
	{
		String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsTransactionTypeCreationApi() +"/isTransactionTypeAlreadyExist";
		Boolean responseEntity = this.restTemplate.postForObject(serverUrl , transactionTypeModel, Boolean.class);
		return responseEntity.booleanValue();
	}

	//added by Prashant Tayde 30 Aug 2023
	@Override
	public Boolean isGLAccountTypeExist(TransactionTypeModel transactionTypeModel) {
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getAmsTransactionTypeCreationApi()+"/isGLAccountTypeExist";
			Boolean responseEntity = this.restTemplate.postForObject(serverUrl , transactionTypeModel, Boolean.class);
			return responseEntity.booleanValue();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<TxnReqRespLogMaster> getTxnReqRespLogMaster(TxnReqRespLogMaster txnReqRespLogMaster) 
	{
		try 
		{
			String serverUrl = apiPropertyConfig.getAccountManagementAPIurl() + apiPropertyConfig.getTransactionReqResLog() + "/getTxnReqResLogsList";
			TxnReqRespLogMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, txnReqRespLogMaster, TxnReqRespLogMaster[].class);
			List<TxnReqRespLogMaster> txnReqResList = Arrays.asList(responseEntity);
			return txnReqResList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Purchase Txn for Simulator
	/*
	@Override
	public TxnReqRes sendTransactionData(TxnReqRes txnReqRes) 
	{
		try 
		{
			TxnReqRes txnReqResponse = null;
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsTransactionHandler()+"/processTxn";
			System.out.println("serverUrl:::::::"+serverUrl);
			txnReqResponse = this.restTemplate.postForObject(serverUrl, txnReqRes, TxnReqRes.class);
			
			List<TxnReqRes> tranMasterResponse = Arrays.asList(txnReqResponse);
			System.out.println("tranMasterResponse::::"+tranMasterResponse);
			if (txnReqResponse != null) 
			{
				 return txnReqResponse;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	@Override
	public ProcessResponse sendTransactionData(TxnReqRes txnReqRes) 
	{
		try 
		{
			ProcessResponse processResponse = null;
			String serverUrl = AMS_MAIN_API_URL + apiPropertyConfig.getAmsTransactionHandler()+"/processTxn";
			System.out.println("serverUrl:::::::"+serverUrl);
			processResponse = this.restTemplate.postForObject(serverUrl, txnReqRes, ProcessResponse.class);
			
			List<TxnReqRes> tranMasterResponse = Arrays.asList(txnReqRes);
			System.out.println("tranMasterResponse::::"+tranMasterResponse);
			if (processResponse != null) 
			{
				 return processResponse;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//MultiCurrency Purchase Txn for Simulator
	@Override
	public ProcessResponse sendMultiCurrencyTransactionData(TxnReqRes txnReqRes) 
	{
		try 
		{
			ProcessResponse processResponse = null;
			//TxnReqRes txnReqResponse = null;
			String serverUrl = MULTI_CURRENCY_API_URL + apiPropertyConfig.getAmsTransactionControl()+"/doTransactionForSimulator";
			//String serverUrl = "http://192.168.19.33:8285/AccountManagementAPI/transactionControl/doTransactionForSimulator";
			System.out.println("serverUrl:::::::"+serverUrl);
			processResponse = this.restTemplate.postForObject(serverUrl, txnReqRes, ProcessResponse.class);
			List<TxnReqRes> tranMasterResponse = Arrays.asList(txnReqRes);
			System.out.println("tranMasterResponse::::"+tranMasterResponse);
			if (processResponse != null) 
			{
				 return processResponse;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Credit Card Transaction For Simulator
	@Override
	public ProcessResponse sendCreditCardTransactionData(TxnReqRes txnReqRes) 
	{
			try 
			{
				//String serverUrl = "http://192.168.19.33:8585/AccountManagementAPI/transactionHandler/processTxn" ;
				String serverUrl = CREDIT_CARD_URL + apiPropertyConfig.getAmsTransactionHandler()+"/processTxn";
				System.out.println("serverUrl:::::::"+serverUrl);
				txnReqRes = this.restTemplate.postForObject(serverUrl, txnReqRes, TxnReqRes.class);
				System.out.println("Prashant ::AccountManagementServiceImpl.sendCreditCardTransactionData()"+txnReqRes);
				List<TxnReqRes> tranMasterResponse = Arrays.asList(txnReqRes);
				System.out.println("tranMasterResponse::::"+tranMasterResponse);
				if (txnReqRes.getResponse() != null) 
				{
					 return txnReqRes.getResponse();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		
	}

	@Override
	public List<AccountCreation> getAccountTypeList() 
	{
		AccountCreation accountCreation = null;
		try 
		{
			String serverUrl = CREDIT_CARD_URL;
			accountCreation = this.restTemplate.postForObject(serverUrl, accountCreation, AccountCreation.class);
			List<AccountCreation> tranMasterResponse = new ArrayList<>();
			if (tranMasterResponse != null)
			{
				 tranMasterResponse.add(accountCreation);
			}
			return tranMasterResponse;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return Collections.emptyList();
		}	
	}

	@Override
	public Client accountCreation(Client client) 
	{
		try 
		{
			//String serverUrl = CREDIT_CARD_URL;
			String serverUrl = "http://localhost:8785/AmsAPI/account/saveAccountInfrm";
			Client responseEntity = this.restTemplate.postForObject(serverUrl, client, Client.class);
			if (responseEntity != null)
			{
				return responseEntity;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		return null;
		
	}

}