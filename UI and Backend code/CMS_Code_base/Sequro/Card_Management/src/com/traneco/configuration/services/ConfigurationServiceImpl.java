package com.traneco.configuration.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.clientSearch.model.AddressDetailsList;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardStatus;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DocumentDetailsList;
import com.traneco.clientSearch.model.EmailDetailsList;
import com.traneco.clientSearch.model.PhoneDetailsList;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.CommonConstant;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.util.Utils;
import com.traneco.configuration.dao.ConfigurationDao;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountDetails;
import com.traneco.configuration.model.AccountInterestMaster;
import com.traneco.configuration.model.AccountLinkedWalletMaster;
import com.traneco.configuration.model.AccountNumberModel;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.AccountTransactionLimitModel;
import com.traneco.configuration.model.AccountTypeCharges;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.AccountTypeModel;
import com.traneco.configuration.model.AccountTypeWiseBlockedMccMaster;
import com.traneco.configuration.model.AccountTypeWiseWalletMaster;
import com.traneco.configuration.model.AddressProofDocumentTypeMaster;
import com.traneco.configuration.model.AddressTypeModel;
import com.traneco.configuration.model.BalanceUpdateInAccountMaster;
import com.traneco.configuration.model.BinModel;
import com.traneco.configuration.model.BranchCodeModel;
import com.traneco.configuration.model.BranchTypeModel;
import com.traneco.configuration.model.CardNoModel;
import com.traneco.configuration.model.CardPlasticModel;
import com.traneco.configuration.model.CardTemplateModel;
import com.traneco.configuration.model.CardTokenModel;
import com.traneco.configuration.model.CardType;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.CategoryListModel;
import com.traneco.configuration.model.Channels;
import com.traneco.configuration.model.ChargesTypeModel;
import com.traneco.configuration.model.CityModel;
import com.traneco.configuration.model.ConnectionConfigModel;
import com.traneco.configuration.model.CountryModel;
import com.traneco.configuration.model.EmailTypeModel;
import com.traneco.configuration.model.EmbossConfigModel;
import com.traneco.configuration.model.GlAccountTypeChargesMaster;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.configuration.model.IdentityProofDocumentTypeMaster;
import com.traneco.configuration.model.InstantAccountCreation;
import com.traneco.configuration.model.IsoConfigModel;
import com.traneco.configuration.model.KeyConfigModel;
import com.traneco.configuration.model.LoadBalanceModel;
import com.traneco.configuration.model.MccCodeModel;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MerchantCategoryCodeMaster;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.model.NcmcServiceModel;
import com.traneco.configuration.model.ParticipantConfigModel;
import com.traneco.configuration.model.ParticipantWiseWalletMaster;
import com.traneco.configuration.model.PhoneTypeModel;
import com.traneco.configuration.model.StateModel;
import com.traneco.configuration.model.TaxConfigModel;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TransactionTypeModel;
import com.traneco.configuration.model.cardAcntLinkageMaster;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.AccountStatementFooterView;
import com.traneco.service.model.AccountTransactionLimitation;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ChargeMaster;
import com.traneco.service.model.PreAccountMaster;

@Service
public class ConfigurationServiceImpl implements ConfigurationService 
{
	@Autowired
	ConfigurationDao configurationDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment env;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	SessionDTO sessionDTO;

	public int addBin(BinModel binModel) 
	{
		int count = this.configurationDao.addBin(binModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<BinModel> getBin() 
	{
		return this.configurationDao.getBin();
	}

	public List<CountryModel> getCountry() 
	{
		return this.configurationDao.getCountry();
	}

	public int addCountry(CountryModel countryModel) 
	{
		return this.configurationDao.addCountry(countryModel);
	}

	public List<StateModel> getState() 
	{
		return this.configurationDao.getState();
	}
	
	@Override
	public List<StateModel> getStateData(String countryId) 
	{
		return this.configurationDao.getStateData(countryId);
	}

	@Override
	public List<CityModel> getCityData(String stateId) 
	{
		return configurationDao.getCityData(stateId);
	}

	public int addState(StateModel stateModel) 
	{
		return this.configurationDao.addState(stateModel);
	}

	public List<BranchTypeModel> getBranch() 
	{
		return this.configurationDao.getBranch();
	}

	public int addBranch(BranchTypeModel branchTypeModel) 
	{
		int count = this.configurationDao.addBranch(branchTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<BranchCodeModel> getBranchCode() 
	{
		return this.configurationDao.getBranchCode();
	}

	public int addBranchCode(BranchCodeModel branchCodeModel) 
	{
		return this.configurationDao.addBranchCode(branchCodeModel);
	}

	public List<AccountTypeModel> getAccount() 
	{
		return this.configurationDao.getAccount();
	}

	public int addAccount(AccountTypeModel accountTypeModel) 
	{
		int count = this.configurationDao.addAccount(accountTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<AddressTypeModel> getAddress() 
	{
		return this.configurationDao.getAddress();
	}

	public int addAddress(AddressTypeModel addressTypeModel) 
	{
		return this.configurationDao.addAddress(addressTypeModel);
	}

	public List<CardTypeModel> getCardType() 
	{
		return this.configurationDao.getCardType();
	}

	public int addCardType(CardTypeModel cardTypeModel) 
	{
		  //For Montra Changes - Added by Prashant T 
		  if(cardTypeModel.getStrAccountType().indexOf("-") != -1) 
		  { 
	      String accountTypeDesc[] = cardTypeModel.getStrAccountType().split("-");
		  cardTypeModel.setStrAccountType(accountTypeDesc[0]);
		  cardTypeModel.setStrAccountTypeDesc(accountTypeDesc[1]); 
		  }
		 
		int count = this.configurationDao.addCardType(cardTypeModel);
		this.restTemplate.getForEntity(String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),	String.class, new Object[0]);
		CardTokenModel cardTokenModel = new CardTokenModel();
		if (cardTypeModel.getICardTokenFlag() == 1) 
		{
			cardTokenModel.setStrCardType(String.valueOf(count));
			cardTokenModel.setStrDemandFlag(cardTypeModel.getICardTokenFlag());
			this.restTemplate.postForEntity(String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.addCardScheduler"), cardTokenModel, Object.class, new Object[0]);
		}
		return count;
	}

	public List<CardTemplateModel> getCardTemplate() 
	{
		return this.configurationDao.getCardTemplate();
	}

	public int addCardTemplate(CardTemplateModel cardTemplateModel) 
	{
		int count = this.configurationDao.addCardTemplate(cardTemplateModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<CardPlasticModel> getCardPlastic() 
	{
		return this.configurationDao.getCardPlastic();
	}

	public int addCardPlastic(CardPlasticModel cardPlasticModel) 
	{
		int count = this.configurationDao.addCardPlastic(cardPlasticModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<EmailTypeModel> getEmail() 
	{
		return this.configurationDao.getEmail();
	}

	public int addEmail(EmailTypeModel emailTypeModel) 
	{
		int count = this.configurationDao.addEmail(emailTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<PhoneTypeModel> getPhone() 
	{
		return this.configurationDao.getPhone();
	}

	public int addPhone(PhoneTypeModel phoneTypeModel) 
	{
		int count = this.configurationDao.addPhone(phoneTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<NcmcServiceModel> getNcmc() 
	{
		return this.configurationDao.getNcmc();
	}

	public int addNcmcService(NcmcServiceModel ncmcServiceModel) 
	{
		int count = this.configurationDao.addNcmcService(ncmcServiceModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public List<KeyConfigModel> getKeyConfig() 
	{
		return this.configurationDao.getKeyConfig();
	}

	public int addKeyConfig(String attr_id, String attr_value, String part_id) {
		return this.configurationDao.addKeyConfig(attr_id, attr_value, part_id);
	}

	public int validateSenPwd(CardNoModel cardNoModel) 
	{
		return this.configurationDao.validateSenPwd(cardNoModel);
	}

	public int deleteBin(String id) 
	{
		int count = this.configurationDao.deleteBin(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateBin(BinModel binModel) 
	{
		int count = this.configurationDao.updateBin(binModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteBranch(String id) 
	{
		int count = this.configurationDao.deleteBranch(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateBranch(BranchTypeModel branchTypeModel) 
	{
		int count = this.configurationDao.updateBranch(branchTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteBranchCode(String id) 
	{
		int count = this.configurationDao.deleteBranchCode(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateBranchCode(BranchCodeModel branchCodeModel) 
	{
		int count = this.configurationDao.updateBranchCode(branchCodeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteAccount(String id) 
	{
		int count = this.configurationDao.deleteAccount(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateAccount(AccountTypeModel accountTypeModel) 
	{
		int count = this.configurationDao.updateAccount(accountTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteAddress(String id) 
	{
		int count = this.configurationDao.deleteAddress(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateAddress(AddressTypeModel addressTypeModel) 
	{
		int count = this.configurationDao.updateAddress(addressTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteCardType(String id) 
	{
		int count = this.configurationDao.deleteCardType(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateCardType(CardTypeModel cardTypeModel) 
	{
		int count = this.configurationDao.updateCardType(cardTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteCardTemplate(String id) 
	{
		int count = this.configurationDao.deleteCardTemplate(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateCardTemplate(CardTemplateModel cardTemplateModel) 
	{
		int count = this.configurationDao.updateCardTemplate(cardTemplateModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteCardPlastic(String id) 
	{
		int count = this.configurationDao.deleteCardPlastic(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateCardPlastic(CardPlasticModel cardPlasticModel) 
	{
		int count = this.configurationDao.updateCardPlastic(cardPlasticModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteEmail(String id) 
	{
		int count = this.configurationDao.deleteEmail(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int updateEmail(EmailTypeModel emailTypeModel) 
	{
		int count = this.configurationDao.updateEmail(emailTypeModel);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	public int deleteNCMC(String id) 
	{
		return this.configurationDao.deleteNCMC(id);
	}

	public int updateNCMC(NcmcServiceModel ncmcServiceModel) 
	{
		return this.configurationDao.updateNCMC(ncmcServiceModel);
	}

	public int deleteKey(String id) 
	{
		return this.configurationDao.deleteKey(id);
	}

	public int updateKey(KeyConfigModel keyConfigModel) 
	{
		return this.configurationDao.updateKey(keyConfigModel);
	}

	public List<StateModel> getStateList(String id) 
	{
		return this.configurationDao.getStateList(id);
	}

	public List<CityModel> getCityList(String id) 
	{
		return this.configurationDao.getCityList(id);
	}

	public Map<String, Object> getCardNo(String tokenCard) 
	{
		return this.configurationDao.getCardNo(tokenCard);
	}

	public List<EmbossConfigModel> getEmbossConfig() 
	{
		return this.configurationDao.getEmbossConfig();
	}

	public int addEmboss(EmbossConfigModel embossConfigModel) 
	{
		return this.configurationDao.addEmboss(embossConfigModel);
	}

	public List<EmbossConfigModel> getEmbossNameConfig() 
	{
		return this.configurationDao.getEmbossNameConfig();
	}

	public List<NcmcServiceModel> getNcmcCode() 
	{
		return this.configurationDao.getNcmcCode();
	}

	public List<CardTypeModel> getCardTypeNCMC() 
	{
		return this.configurationDao.getCardTypeNCMC();
	}

	public int addNCMCService(NcmcServiceModel ncmcServiceModel) 
	{
		return this.configurationDao.addNCMCService(ncmcServiceModel);
	}

	public List<KeyValueBean> getncmcList(String type) 
	{
		return this.configurationDao.getncmcList(type);
	}

	public String getNetworkScheme(String bin) 
	{
		return this.configurationDao.getNetworkScheme(bin);
	}

	public String getBinFlag(String bin) 
	{
		return this.configurationDao.getBinFlag(bin);
	}

	public List<KeyValueBean> getEndPoint() 
	{
		return this.configurationDao.getEndPoint();
	}

	public List<KeyValueBean> getCfg_MCC() 
	{
		return this.configurationDao.getCfg_MCC();
	}

	public List<KeyValueBean> getCard_Type_MCC(String id) 
	{
		return this.configurationDao.getCard_Type_MCC(id);
	}

	public int getBinCardType(String bin) 
	{
		return this.configurationDao.getBinCardType(bin);
	}

	public int getAddressType(String type) 
	{
		return this.configurationDao.getAddressType(type);
	}

	public int getEmailType(String type) 
	{
		return this.configurationDao.getEmailType(type);
	}

	public int getDocType(String type) 
	{
		return this.configurationDao.getDocType(type);
	}

	public int getCityID(String id) 
	{
		return this.configurationDao.getCityID(id);
	}

	public int getStateID(String id) {
		return this.configurationDao.getStateID(id);
	}

	public int getCountryID(String id) 
	{
		return this.configurationDao.getCountryID(id);
	}

	public int getAccountID(String id) 
	{
		return this.configurationDao.getAccountID(id);
	}

	public int updateMCC(CardTypeModel cardTypeModel) 
	{
		return this.configurationDao.updateMCC(cardTypeModel);
	}

	public List<KeyValueBean> getCard_MCC(String id) 
	{
		return this.configurationDao.getCard_MCC(id);
	}

	public int getBatchUpdateCount(String id) 
	{
		return this.configurationDao.getBatchUpdateCount(id);
	}

	public int addCardToken(CardTokenModel cardTokenModel) 
	{
		return this.configurationDao.addCardToken(cardTokenModel);
	}

	public List<CardTokenModel> getCardTokenList() 
	{
		return this.configurationDao.getCardTokenList();
	}

	public List<CardTypeModel> getCfgCardType() 
	{
		return this.configurationDao.getCfgCardType();
	}

	public Object addCardScheduler(CardTokenModel cardTokenModel) 
	{
		ResponseEntity<String> response = this.restTemplate.postForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.addCardScheduler"),
				cardTokenModel, String.class, new Object[0]);
		return response.getBody();
	}

	public Object addMobileScheduler(MobileTokenModel mobileTokenModel) 
	{
		ResponseEntity<String> response = this.restTemplate.postForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.addMobileScheduler"),
				mobileTokenModel, String.class, new Object[0]);
		return response.getBody();
	}

	public List<MobileTokenModel> getMobileTokenList() 
	{
		return this.configurationDao.getMobileTokenList();
	}

	public int addIsoData(IsoConfigModel isoConfig) 
	{
		return this.configurationDao.addIsoData(isoConfig);
	}

	public List<IsoConfigModel> getISOConfigList() 
	{
		return this.configurationDao.getISOConfigList();
	}

	@Override
	public List<KeyValueBean> getParticipantList() 
	{
		return configurationDao.getParticipantList();
	}

	@Override
	public int addConnection(ConnectionConfigModel connConfig) 
	{
		return configurationDao.addConnection(connConfig);
	}

	@Override
	public List<ConnectionConfigModel> getConnectionList() 
	{
		return configurationDao.getConnectionList();
	}

	@Override
	public int addParticipant(ParticipantConfigModel partConfig) 
	{
		return configurationDao.addParticipant(partConfig);
	}

	@Override
	public List<ParticipantConfigModel> getParticipantConfigList() 
	{
		return configurationDao.getParticipantConfigList();
	}

	// Added by prashant Tayde

	@Override
	public List<ChargesTypeModel> getChargesTypeList() 
	{
		return configurationDao.getChargesTypeList();
	}

	/*
	 * @Override public List<AccountType> getAccountTypeList() { return
	 * configurationDao.getAccountTypeList(); }
	 */

	@Override
	public List<CardType> getCardTypeList() 
	{
		return configurationDao.getCardTypeList();
	}

	@Override
	public int addAccountTypeMaster(AccountTypeMaster accountTypeMaster) 
	{
		String accNumber = Utils.getGeneratedAcNumber(accountTypeMaster.getStrAccNumLength(), accountTypeMaster.getStrAccNumStartDigit());
		accountTypeMaster.setStrLastAccNumber(accNumber);
		int count = 0;
		
		String result = this.restTemplate.postForObject(this.env.getProperty("ams.account_mgmt_url") + "/saveAccountTypeInfo", accountTypeMaster, String.class);
		if (result!=null && result.trim().equals("success")) {
			return 1;
		}		
		return count;
	}

	public int deleteAccountType(String id) {
		int count = this.configurationDao.deleteAccountType(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	@Override
	public int updateAccountType(AccountTypeMaster accountTypeMaster) {
			int count = this.configurationDao.updateAccountType(accountTypeMaster);
			this.restTemplate.getForEntity(
					String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
					String.class, new Object[0]);
			return count;
		}

	@Override
	public List<AccountTypeMaster> getAccounTypeMaster() {
		return configurationDao.getAccounTypeMaster();
	}

	@Override
	public int deleteCardAccountLinkage(String id) {
		int count = this.configurationDao.deleteCardAccountLinkage(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	@Override
	public int updateCardAccountLinkage(CardAccountLinkage cardAccountLinkage) {
		int count = this.configurationDao.updateCardAccountLinkage(cardAccountLinkage);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	@Override
	public int addCardAccountLinkage(CardAccountLinkage cardAccountLinkage) {
		int count = this.configurationDao.addCardAccountLinkage(cardAccountLinkage);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	@Override
	public List<CardAccountLinkage> getCardAccountLinkage() {
		return configurationDao.getCardAccountLinkage();
	}
	
	@Override
	public List<CardAccountLinkage> getCardLinkAccountList(CardAccountLinkage cardAccountLinkage) {
		return configurationDao.getCardLinkAccountList(cardAccountLinkage);
	}

	@Override
	public List<AccountCreation> getAccountCreation() {
		return configurationDao.getAccountCreation();
	}

	@Override
	public int addAccountCreation(AccountCreation accountCreation) 
	{
		try 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/saveAccountInfo";
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
		/*
		int count = this.configurationDao.addAccountCreation(accountCreation);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
		*/
		return 0;
	}

	@Override
	public List<MccCodeModel> getMccCodeList() {
		return configurationDao.getMccCodeList();
	}

	@Override
	public List<AccountCreation> getAccountType() {
		return configurationDao.getAccountType();
	}

	@Override
	public int deleteAccountCreation(String id) {
		int count = this.configurationDao.deleteAccountCreation(id);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		return count;
	}

	@Override
	public int updateAccountCreation(AccountCreation accountCreation) 
	{
		int count = 0;
		try 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/updateAccountInfo";
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
		/*
		int count = this.configurationDao.updateAccountCreation(accountCreation);
		this.restTemplate.getForEntity(
				String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
				String.class, new Object[0]);
		*/		
		return count;
	}
	
	//Ended by Prashant Tayde

	//Added by Sunny Soni for updating account master from instant account Start
	@Override
	public int updateAccountCreationFromInstanceAccount(AccountCreation accountCreation) 
	{
		int count = 0;
		try {

			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/updateInstantAccountInfo";
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
		//return this.configurationDao.updateAccountCreationFromInstanceAccount(accountCreation);
		return count;
	}
  //Added by Sunny Soni for updating account master from instant account End

	
	//Added by Sunny Soni for getting account Number Start
		@Override
		public int updateLastAccountNumber(String updatedAccountNo, String accountType) {
			return this.configurationDao.updateLastAccountNumber(updatedAccountNo, accountType);
		}
		
		
		
		@Override
		public String getAccounNumberBasedOnAccountType(String accountType)
		{
			return this.configurationDao.getAccounNumberBasedOnAccountType(accountType);
		}	
		//Added by Sunny Soni for getting account Number End
		
		//Added by Sunny Soni for getting account Number list Start
		@Override
		public List<AccountNumberModel> getAccounNumberListBasedOnAccountType(String accountType) {
			return configurationDao.getAccounNumberListBasedOnAccountType(accountType);
		}
		//Added by Sunny Soni for getting account Number list End

		
		//Added by prashant Tayde
		
		@Override
		public int addInstantAccount(InstantAccountCreation instantAccountCreation) {
			int count = this.configurationDao.addInstantAccount(instantAccountCreation);
			this.restTemplate.getForEntity(
					String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
					String.class, new Object[0]);
			return count;
		}

		@Override
		public List<InstantAccountCreation> getInstantAccount() {
			return configurationDao.getInstantAccount();
		}

		@Override
		public List<AccountCreation> getAccountCreationData(String id) {
		
			return configurationDao.getAccountCreationData(id);
		}
		
		//Ended by Prashant Tayde
		
		//Added by Sunny Soni for inserting multiple records of account Start
		@Override
		public int[] creatingInstancesAccountsByBatchInsert(InstantAccountCreation instantAccountCreation)
		{
			int[] responseArr = {};
			try 
			{
				String accountType = instantAccountCreation.getStrAccountType();
				String lastAccountNumber = configurationDao.getAccounNumberBasedOnAccountType(accountType);
				
				List<AccountCreation> accountCreationlist = new ArrayList<AccountCreation>();
				
				int totalQuantity = Integer.parseInt(instantAccountCreation.getStrQuantity());
				for (int i = 0; i < totalQuantity; i++)
				{
					String newAccountNo = Utils.getUpdatedAccNumber(lastAccountNumber);
					
					AccountCreation accountCreation = new AccountCreation();
					accountCreation.setStrAccountType(instantAccountCreation.getStrAccountType());
					accountCreation.setStrFirstName(instantAccountCreation.getStrFirstName());
					accountCreation.setStrAccountNumber(newAccountNo);
					
					accountCreationlist.add(accountCreation);
					
					lastAccountNumber = newAccountNo;
				}
				int count = configurationDao.updateLastAccountNumber(lastAccountNumber, accountType);
				System.out.println("Updated count::"+count);
				if (count > 0) 
				{
					responseArr = configurationDao.batchAccountInsert(accountCreationlist);
				}
				System.out.println("responseArr::"+responseArr);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in creatingInstancesAccountsByBatchInsert::"+e);
			}
			return responseArr;
		}
		//Added by Sunny Soni for inserting multiple records of account End
		@Override
		public List<AccountCreation> getAccountCreationListDataBasedOnTypes(String isInstanceAccountStr) 
		{
			return configurationDao.getAccountCreationListDataBasedOnTypes(isInstanceAccountStr);
		}
		//Added by Sunny Soni for get instance or viewed type related account list for edit Startg

		
		//Added by prashant Tayde
		
		@Override
		public List<CountryModel> getPhoneCode() {
			return configurationDao.getPhoneCode();
		}

		//Added by prashant Tayde
		@Override
		public List<AccountCreditLimitCategory> getAccountCreditLimiCategory() {
			return configurationDao.getAccountCreditLimiCategory();
		}

		//Added by prashant Tayde
		@Override
		public int addCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory) {
			int count = this.configurationDao.addCreditLimitCategory(accountCreditLimitCategory);
			this.restTemplate.getForEntity(
					String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
					String.class, new Object[0]);
			return count;
		}

		//Added by prashant Tayde
		@Override
		public List<TaxConfigModel> getTaxType() {
			return configurationDao.getTaxType();
		}

		
		//Added by prashant Tayde
		@Override
		public List<MccWiseInterestModel> getMccWiseInterest() {
			return configurationDao.getMccWiseInterest();
		}
		
		//Added by prashant Tayde
		@Override
		public int updateAccountLimitCredit(AccountCreditLimitCategory accountCreditLimitCategory) {
			int count = this.configurationDao.updateAccountLimitCredit(accountCreditLimitCategory);
			this.restTemplate.getForEntity(
					String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
					String.class, new Object[0]);
			return count;
		}
		
		//Ended by Prashant Tayde
		
		@Override
		public List<AccountCreation> getAccountInfoListBasedOnTypes(AccountCreation accountCreation)
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/getAccountInfoListBasedOnTypes";
				AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation[].class);
				List<AccountCreation> accountCreationlist = Arrays.asList(responseEntity);
				return accountCreationlist;				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Exception while getAccountInfoListBasedOnTypes ::"+e);
			}
			return null;
		}
		//Added by Sunny Soni for get instance or viewed type related account list for edit Startg
		
		@Override
		public String creatingInstantAccount(InstantAccountCreation instantAccountCreation) 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.instant_account") +"/create";
			return this.restTemplate.postForObject(serverUrl, instantAccountCreation, String.class);
		}		
		// /account
		
		//Added by Sunny Soni for getting mcc code and description from account mgmt Start
		@Override
		public List<MerchantCategoryCodeMaster> getMCCList(MerchantCategoryCodeMaster merchantCategoryCodeMaster)
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.mcc_code") +"/getAllMccCode";
				MerchantCategoryCodeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , merchantCategoryCodeMaster, MerchantCategoryCodeMaster[].class);
				List<MerchantCategoryCodeMaster> mcclistData = Arrays.asList(responseEntity);
				return mcclistData;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//Added by Sunny Soni for getting mcc code and description from account mgmt End
		
		//Added by Sunny Soni for wallet related changes Start
		@Override
		public ParticipantWiseWalletMaster addParticipantWiseWalletMaster(ParticipantWiseWalletMaster participantWiseWalletMaster) 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.participant_wallet") +"/add";
				ParticipantWiseWalletMaster responseEntity = this.restTemplate.postForObject(serverUrl , participantWiseWalletMaster, ParticipantWiseWalletMaster.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public List<ParticipantWiseWalletMaster> getMCCListParticipantWise(ParticipantWiseWalletMaster participantWiseWalletMaster) 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.participant_wallet") +"/getParticipantBasedMcc";
				ParticipantWiseWalletMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , participantWiseWalletMaster, ParticipantWiseWalletMaster[].class);
				List<ParticipantWiseWalletMaster> mcclistData = Arrays.asList(responseEntity);
				return mcclistData;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public AccountTypeWiseBlockedMccMaster addAccountTypeWiseBlockedMccMaster(AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster) 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.blocked_mcc_account_type_wise") +"/add";
				AccountTypeWiseBlockedMccMaster responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeWiseBlockedMccMaster, AccountTypeWiseBlockedMccMaster.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public AccountTypeWiseWalletMaster addAccountTypeWiseWalletMaster(AccountTypeWiseWalletMaster accountTypeWiseWalletMaster) 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type_wallet") +"/add";
				AccountTypeWiseWalletMaster responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeWiseWalletMaster, AccountTypeWiseWalletMaster.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//Added by Sunny Soni for wallet related changes End
		
		@Override
		public String getLastAccounNumberBasedOnAccountType(String accountType)
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type") +"/getLastAccounNumber";
			String responseEntity = this.restTemplate.postForObject(serverUrl , accountType, String.class);
			return responseEntity;
		}
		
		@Override
		public AccountTypeMaster getSingleAccountTypeObject(AccountTypeMaster accountTypeMaster) throws Exception 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type") +"/getSingleAccountType";
				AccountTypeMaster responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMaster, AccountTypeMaster.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public List<AccountTypeMaster> getAccountTypeMasterDataList() throws Exception 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type") +"/getAccountType";
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
		public List<AccountTypeMaster> getAccountTypeMasterDataListByParticipantWise(String participantId) throws Exception 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type") +"/getACTypeListByParticipantWise";
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
		public List<AccountCreditLimitCategory> getAccountCreditLimitCategoryListByParticipantWise(String participantId) throws Exception
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.credit_limit") +"/getCreditLimitListByParticipantWise";
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
		public AccountCreditLimitCategory getAccountCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory) 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.credit_limit") +"/getAccountCreditLimitCategoryObj";
				AccountCreditLimitCategory responseEntity = this.restTemplate.postForObject(serverUrl , accountCreditLimitCategory, AccountCreditLimitCategory.class);
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public List<AccountTransactionLimitation> getAccountTransactionLimitationList(AccountTypeMaster accountTypeMaster) throws Exception {
			return this.configurationDao.getAccountTransactionLimitationList(accountTypeMaster);
		}
		
		@Override
		public List<AddressProofDocumentTypeMaster> getAddressProofDocumentTypeMasters(String participantId) throws Exception {
			return this.configurationDao.getAddressProofDocumentTypeMasters(participantId);
		}
		
		@Override
		public List<IdentityProofDocumentTypeMaster> getIdentityProofDocumentTypeMasters(String participantId) throws Exception {
			return this.configurationDao.getIdentityProofDocumentTypeMasters(participantId);
		}
		
		@Override
		public List<AccountCreation> getAccountInfoListByAccountNo(AccountCreation accountCreation)
		{
			try 
			{
				if(accountCreation.getStrAccountNumber()!=null)
				{
					String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/getAccountInfoListByAccountNo";
					AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation[].class);
					List<AccountCreation> accountCreationlist = Arrays.asList(responseEntity);
					return accountCreationlist;				
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Exception while getAccountInfoListBasedOnTypes ::"+e);
			}
			return null;
		}
		
		@Override
		public List<MerchantCategoryCodeMaster> getSelectedMCCList(MerchantCategoryCodeMaster merchantCategoryCodeMaster) throws Exception 
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.mcc_code") +"/getSelectedMCCList";
				MerchantCategoryCodeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , merchantCategoryCodeMaster, MerchantCategoryCodeMaster[].class);
				List<MerchantCategoryCodeMaster> mcclistData = Arrays.asList(responseEntity);
				return mcclistData;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public List<MerchantCategoryCodeMaster> getUnSelectedAllMCC(MerchantCategoryCodeMaster merchantCategoryCodeMaster) throws Exception 
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.mcc_code") +"/getUnSelectedAllMCC";
				MerchantCategoryCodeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , merchantCategoryCodeMaster, MerchantCategoryCodeMaster[].class);
				List<MerchantCategoryCodeMaster> mcclistData = Arrays.asList(responseEntity);
				return mcclistData;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		@Override
		public List<AccountTypeWiseWalletMaster> getAccountTypeWiseWalletMaster(AccountTypeWiseWalletMaster accountTypeWiseWalletMaster) throws Exception 
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type_wallet") +"/accountTypeBasedWallet";
				AccountTypeWiseWalletMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeWiseWalletMaster, AccountTypeWiseWalletMaster[].class);
				List<AccountTypeWiseWalletMaster> mccWiseWalletlistData = Arrays.asList(responseEntity);
				return mccWiseWalletlistData;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		
		//prashant
		@Override
		public List<AccountTypeWiseBlockedMccMaster> getBlockMccListAccountTypeWise(AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster) 
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.blocked_mcc_account_type_wise") +"/getBlockMccListAccountTypeWise"; 
				AccountTypeWiseBlockedMccMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeWiseBlockedMccMaster, AccountTypeWiseBlockedMccMaster[].class);
				
				if (responseEntity != null && responseEntity.length > 0)
				{
					List<AccountTypeWiseBlockedMccMaster> mccWiseblocklistData = Arrays.asList(responseEntity);
					return mccWiseblocklistData;
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public List<AccountTypeWiseBlockedMccMaster> getAccountTypeWiseUnBlockedMccMaster(AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster) throws Exception 
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.blocked_mcc_account_type_wise") +"/getUnblockMCC";
				AccountTypeWiseBlockedMccMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeWiseBlockedMccMaster, AccountTypeWiseBlockedMccMaster[].class);
				
				if (responseEntity != null && responseEntity.length > 0)
				{
					List<AccountTypeWiseBlockedMccMaster> mccWiseBlocklistData = Arrays.asList(responseEntity);
					return mccWiseBlocklistData;
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}

	/*
	 * // Added by Prashant Start
	 * 
	 * @Override public List<MccCodeModel> getMccCodeList() { return
	 * configurationDao.getMccCodeList(); }
	 */
	@Override
	public List<CategoryListModel> getCategoryList() {
		return configurationDao.getCategoryList();
	}

	@Override
	public int updateMCCWiseInterest(MccWiseInterestModel mccWiseInterestModel) {
		return this.configurationDao.updateMCCWiseInterest(mccWiseInterestModel);
	}

	@Override
	public int addAccountTransactionLimit(AccountTransactionLimitModel accountTransactionLimitModel) {
		return this.configurationDao.addAccountTransactionLimit(accountTransactionLimitModel);
	}

		@Override
		public int addLoadBalance(LoadBalanceModel loadBalanceModel) {
			int count = this.configurationDao.addLoadBalance(loadBalanceModel);
			this.restTemplate.getForEntity(
					String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
					String.class, new Object[0]);
			return count;
		}

		@Override
		public List<LoadBalanceModel> getLoadBalance() {
			return configurationDao.getLoadBalance();
		}

		@Override
		public List<AccountDetails> getAccountDetailsData(AccountDetails accountDetails) {
			return configurationDao.getAccountDetailsData(accountDetails);
		}

		@Override
		public List<AccountTransactionLimitModel> getTransactionLimitData(String accountType) {
			return configurationDao.getTransactionLimitData(accountType);
		}

		@Override
		public int saveMccInterest(MccWiseInterestModel mccWiseInterestModel) {
			int count = this.configurationDao.saveMccInterest(mccWiseInterestModel);
			this.restTemplate.getForEntity(
					String.valueOf(this.env.getProperty("cms.url")) + this.env.getProperty("cms.uri.refresh.cache"),
					String.class, new Object[0]);
			return count;
		}

		@Override
		public List<MccWiseInterestModel> getMccWiseInterestData(MccWiseInterestModel mccWiseInterestModel) {
			try {
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")
						+ this.env.getProperty("ams.mccwiseinterestmaster") + "/getMccDataListByParticipantId";
				MccWiseInterestModel[] responseEntity = this.restTemplate.postForObject(serverUrl, mccWiseInterestModel,
						MccWiseInterestModel[].class);
				List<MccWiseInterestModel> mccWiseInterestList = Arrays.asList(responseEntity);
				return mccWiseInterestList;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ConfigurationServiceImpl.getMccWiseInterestData()" + e);

			}
			return null;
		}

		@Override
		public List<CardAccountLinkage> getCardAccountLinkage(String accountType, String accountNumber)
		{
			return configurationDao.getCardAccountLinkage(accountType, accountNumber);
		}

		@Override
		public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType, String cardNumber) {
			return configurationDao.getCardAccountLinkageCard(cardType, cardNumber);
		}

		@Override
		public List<AccountTypeMaster> getAmountTransactionlist() {
			return configurationDao.getAmountTransactionlist();
		}

		@Override
		public List<AccountCreation> getInterestOutstanding(String accountNumber) {
			return configurationDao.getInterestOutstanding(accountNumber);
		}
		//Added by Prashant End
		
		@Override
		public List<AccountCreditLimitCategory> getAccountCreditTypeCategorylist()
		{
			return configurationDao.getAccountCreditTypeCategorylist();
		}
		
		@Override
		public List<AccountInterestMaster> getOutStandingList(String accountNumber) {
			return configurationDao.getOutStandingList(accountNumber);
		}
		
		@Override
		//public List<CardAccountLinkage> getCardAccountLinkages(CardAccountLinkage cardAccountLinkage) 
		public CardAccountLinkage getCardAccountLinkages(CardAccountLinkage cardAccountLinkage)
		{
			return configurationDao.getCardAccountLinkages(cardAccountLinkage);
		}
		
		@Override
		public AccountTransactionLimitModel getAccountTransactionLimitModel(AccountTransactionLimitModel accountTransactionLimitModel) 
		{
			return configurationDao.getAccountTransactionLimitModel(accountTransactionLimitModel);
		}
		
		@Override
		public AccountCreation getAccountInformation(AccountCreation accountCreation) 
		{
			try 
			{
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/getAccountInfoListByAccountNo";
				AccountCreation[] responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, AccountCreation[].class);
				
				if (responseEntity!=null && responseEntity.length > 0)
				{
					accountCreation = responseEntity[0];
				}
				return accountCreation;				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Exception while getAccountInfoListBasedOnTypes ::"+e);
			}
			return null;
		}
		
		@Override
		public String getCountryName(String id) 
		{
			return configurationDao.getCountryName(id);
		}
		
		@Override
		public String getStateName(String id)
		{
			return configurationDao.getStateName(id);
		}
		
		@Override
		public String getCityName(String id)
		{
			return configurationDao.getCityName(id);
		}
		
		@Override
		public String getDownloadedPDFPath(List<MerchantCategoryCodeMaster> merchantCategoryCodeMasters) 
		{
			try 
			{
				//String responseEntity = this.restTemplate.postForObject(serverUrl , "", String.class);
				
				//String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/test";
				//String responseEntity = this.restTemplate.getForObject(serverUrl , String.class);

				String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				
				headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			   
			    
			    //String _url = "http://localhost:1170/FileDownloaderAPI/export-pdf/getDownloadFile";
			    
			    
			    Map<String, Object> data = new HashMap<>();
			    

		        data.put("bodyInfoInList", merchantCategoryCodeMasters);	        
		        //data.put("headerInfoMap", customer);
		        data.put("templateName", "mcc-list");
		        data.put("fileName", "mcclist-");
		        
			    
			    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
			    
			    System.out.println("Calling .....processToGETDownloadedFile Request");
			    
			    String responseEntity = restTemplate.postForObject(serverUrl, request, String.class);
			    System.out.println("personResultAsJsonStr::"+responseEntity);
			
				return responseEntity;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public String getDownloadedAccountStatementFilePath(List<AccountStatement> accountStatements)
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
				HttpHeaders headers = new HttpHeaders();
				
				headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    
			    Map<String, Object> data = new HashMap<>();			    
			    //data.put("headerInfoMap", customer);
		        data.put("bodyInfoInList", accountStatements);   
		        
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
		        		if (credits == 0) {
		        			credits = Double.parseDouble(accountStatement.getStrTransactionAmount());
		        		}
		        		else {
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
		        	}
		        	else
		        	{
		        		if (debits == 0) {
		        			debits = Double.parseDouble(accountStatement.getStrTransactionAmount());
		        		}
		        		else {
		        			debits = debits + Double.parseDouble(accountStatement.getStrTransactionAmount());
		        		}
		        		debitCount = debitCount + 1;
		        	}
		        	
		        	if (accountStatements.size() == count)
		        	{
		        		closingBalance = Double.parseDouble(accountStatement.getStrClosingBalance());
		        	}
		        	count++;
		        }
		        //Opening Balance getting logic Start
		        if (openingBalance == 0) 
		        {
		        	openingBalance = (closingBalance + debits) - credits;
		        }
		        //Opening Balance getting logic End
		        
		        AccountStatementFooterView accountStatementFooterView = new AccountStatementFooterView();
		        accountStatementFooterView.setOpeningBalance(String.valueOf(openingBalance));	        
		        accountStatementFooterView.setCredits(String.valueOf(credits));
		        accountStatementFooterView.setDebits(String.valueOf(debits));
		        accountStatementFooterView.setCreditCount(String.valueOf(creditCount));
		        accountStatementFooterView.setDebitCount(String.valueOf(debitCount));
		        accountStatementFooterView.setClosingBalance(String.valueOf(closingBalance));
		        
		        Map<String, Object> footerMap = new HashMap<>();
		        footerMap.put("footer", accountStatementFooterView);
		        
		        data.put("footerInfoMap", footerMap);
		        //data.put("templateName", "account-statement");
		        data.put("templateName", "ams_account-statement");
		        data.put("fileName", "account-statement-");
			    
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
		
		@Override
		public String getDownloadedAccountStatementFilePathForExcel(List<AccountStatement> accountStatements) 
		{
			try
			{
				String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_excel") +"/getDownloadFile";
				HttpHeaders headers = new HttpHeaders();
				
				headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    
			    Map<String, Object> data = new HashMap<>();			    
		        data.put("bodyInfoInList", accountStatements);
		        
		        //key contains mapping data for getting value and value contains header name
		        
			    Map<String, String> headerDataMap = new HashMap<>();
			    headerDataMap.put("strTransactionID", "Transaction Id");
			    headerDataMap.put("strTransactionDate", "Transaction Date");
			    headerDataMap.put("strTranType", "Transaction Type");
			    headerDataMap.put("strTranMode", "Transaction Mode");
			    headerDataMap.put("strDebitAmount", "Withdraw Amount");
			    headerDataMap.put("strCreditAmount", "Deposit Amount");
			    headerDataMap.put("strClosingBalance", "Closing Balance");
		        data.put("headerInfoMap", headerDataMap);
		        
		        data.put("fileName", "account-statement-");
			    
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
		
		@Override
		public List<AccountCreditLimitCategory> getCreditTypelist() {
			return configurationDao.getCreditTypelist();
		}

		@Override
		public List<AccountLinkedWalletMaster> getLinkedAccountList(AccountLinkedWalletMaster accountLinkedWalletMaster) {
			return configurationDao.getLinkedAccountList(accountLinkedWalletMaster);
		}

	@Override
	public List<AccountLinkedWalletMaster> getlinkedAccountWallet(String walletAccountNumber) {
		return configurationDao.getlinkedAccountWallet(walletAccountNumber);
	}

	// added on 23-12-22 by ankit
	@Override
	public List<AccountTypeMaster> getAccountTypeFromAccountTypeMaster() {
		List<AccountTypeMaster> accounTypeList = configurationDao.getAccounTypeFromAcocuntMaster();
		// Removing the "CRD" Accounts from List
		// List<String> filteredAccountTypes =
		// accounTypeList.stream().filter(accountType ->
		// !accountType.equals("CRD")).collect(Collectors.toList());
		// System.out.println(filteredAccountTypes);
		return accounTypeList;
	}
	// added on 23-12-22 by ankit

	// added on 23-12-22 by ankit
	
	
	//===========================added methods on 12-01-2023===================================
	
	//added on 04-01-2023 to check Account Details by ankit
		@Override
		public int getAllowedLoadCashCount(LoadBalanceModel loadBalanceModel) 
		{
			try
			{
				AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
				accountTypeMaster.setStrAccountType(loadBalanceModel.getStrAccountType());				
				
				String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type") +"/getSingleAccountType";
				accountTypeMaster = this.restTemplate.postForObject(serverUrl , accountTypeMaster, AccountTypeMaster.class);
				
				int allowLoadCashInteger = Integer.parseInt(accountTypeMaster.getStrAllowLoadCash());
				return allowLoadCashInteger;
			}
			catch (Exception e) 
			{
				e.printStackTrace();	
			}
			/*
			String strAccountType = loadBalanceModel.getStrAccountType();
			String allowLoadCashFromAccountTypeMaster = this.configurationDao.getAllowLoadCashFromAccountTypeMaster(strAccountType);
			int allowLoadCashInteger = Integer.parseInt(allowLoadCashFromAccountTypeMaster);
			return allowLoadCashInteger;
			*/
			return -1;
		}
			
		//added on 23-12-22 by ankit
		@Override
		@Transactional(rollbackFor = Exception.class)
		public String addLoadBalanceNew(LoadBalanceModel loadBalanceModel, int newLoadCountInteger)
		{
			try 
			{
				//String accountTypeStr = loadBalanceModel.getStrAccountType();
				String localDate = Utils.getLocalDate();
				String localTime = Utils.getLocalTime();
				loadBalanceModel.setStrDateOfLoading(localDate);
				loadBalanceModel.setStrTimeOfLoading(localTime);
				
				//String categoryTypeFromAccountTypeMaster = this.configurationDao.getCategoryTypeFromAccountTypeMaster(accountTypeStr);
				//loadBalanceModel.setStrAccountCategory(categoryTypeFromAccountTypeMaster);
				
				AccountTranMaster accountTranMaster = mapToAccountTranMaster(loadBalanceModel);
				int addTransaction = addTransaction(accountTranMaster);
				
				
				long strTxn_id = Long.parseLong(accountTranMaster.getStrTxn_id());
				String transactionIdString = String.valueOf(strTxn_id);
				
				BalanceUpdateInAccountMaster balance = updateClosingBalanceAndLoadCount(loadBalanceModel);
				loadBalanceModel.setStrTransactionId(transactionIdString);
				int addBalanceInAccountMaster = addBalanceInAccountMaster(balance);
				
				int addLoadBalances = this.configurationDao.addLoadBalances(loadBalanceModel);
						
				AccountStatement accountStatement = mapToAccountStatement(accountTranMaster, balance);
				int addAccountStatement = addAccountStatement(accountStatement);
					
				if (addBalanceInAccountMaster == 1 && addTransaction == 1 && addAccountStatement == 1 && addLoadBalances == 1) {
						String successFlag = "Y";
						return successFlag;
				} 
				else 
				{
						String message = "Balance Could Not Be Loaded";
						return message;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public BalanceUpdateInAccountMaster updateClosingBalanceAndLoadCount(LoadBalanceModel loadBalanceModel) {
			
			String strAccountNumber = loadBalanceModel.getStrAccountNumber();
			String strAccountType = loadBalanceModel.getStrAccountType();
			String strLoadedBalance = loadBalanceModel.getStrLoadedBalance();
			int addedLoadBalanceInteger = Integer.parseInt(strLoadedBalance);
			List<AccountCreation> accountMasterDetails = this.configurationDao.getAccountMasterDetails(strAccountType,
					strAccountNumber);

			//String strLoadCounter = accountMasterDetails.get(0).getStrLoadCounter();
			String strLoadCounter = accountMasterDetails.get(0).getStrLoadCount();
			String strClosingBalance = accountMasterDetails.get(0).getStrClosingBalance();
			
			int fetchedLoadCountInteger = (strLoadCounter != null) ? Integer.parseInt(strLoadCounter) : 0;
			int newLoadCountInteger = fetchedLoadCountInteger + 1;
			String newLoadCountString = String.valueOf(newLoadCountInteger);
			
			int fetchedClosingBalanceInteger = (strClosingBalance != null) ? Integer.parseInt(strClosingBalance) : 0;
			int newClosingBalanceInteger = fetchedClosingBalanceInteger + addedLoadBalanceInteger;
			String newClosingBalanceString = String.valueOf(newClosingBalanceInteger);
			
			BalanceUpdateInAccountMaster balance = new BalanceUpdateInAccountMaster();
			balance.setStrAccountNumber(strAccountNumber);
			balance.setStrAccountType(strAccountType);
			balance.setStrClosingBalance(newClosingBalanceString);
			balance.setStrLoadCount(newLoadCountString);
			return balance;
		}
		
		//mapper method to set the data to Account TranMaster added on 04-01-2023
		public AccountTranMaster mapToAccountTranMaster(LoadBalanceModel loadBalanceModel) 
		{
			String strAccountNumber = loadBalanceModel.getStrAccountNumber();
			String strAccountType = loadBalanceModel.getStrAccountType();
			String strDateOfLoading = loadBalanceModel.getStrDateOfLoading();
			String strTimeOfLoading = loadBalanceModel.getStrTimeOfLoading();
			String strCreatedBy = loadBalanceModel.getStrCreatedBy();
			String strChannel = loadBalanceModel.getStrChannel();
			String strLoadedBalance = loadBalanceModel.getStrLoadedBalance();
			
			AccountTranMaster accountTranMaster = new AccountTranMaster();
			accountTranMaster.setStrAccountNumber(strAccountNumber);
			accountTranMaster.setStrAccountType(strAccountType);
			accountTranMaster.setStrLocal_tran_date(strDateOfLoading);			
			accountTranMaster.setStrLocal_tran_time(strTimeOfLoading);
			
			accountTranMaster.setStrSys_id("001");
			accountTranMaster.setStrTran_type("CR");
			accountTranMaster.setStrTo_account_number(strAccountNumber);
			accountTranMaster.setStrTransaction_amount(strLoadedBalance);
			return accountTranMaster;
		}
	
		// added on 02-01-2022 support method to add the transaction modiefied on 04-01-22
		public int addTransaction(AccountTranMaster accountTranMaster) 
		{
			String julianDate = Utils.getJulidanDate();
			String strLocalTranDate = accountTranMaster.getStrLocal_tran_date();
			List<AccountTranMaster> lastTransaction = this.configurationDao.getLastTransaction();
			if (lastTransaction.size() == 0) 
			{
				// add a new Transaction
				String transactionNumnerStringOneString = "0000001";
				String transactionIdString = julianDate + transactionNumnerStringOneString;
				long strTxn_id = Long.parseLong(transactionIdString);
				accountTranMaster.setStrTxn_id( String.valueOf(strTxn_id));
				int addTransaction = this.configurationDao.addTransaction(accountTranMaster);
				
				return addTransaction;
			}
			else if (strLocalTranDate.compareTo(lastTransaction.get(0).getStrLocal_tran_date()) > 0)
			{
				// add a new Transaction
				String transactionNumnerStringOneString = "0000001";
				String transactionIdString = julianDate + transactionNumnerStringOneString;
				long transactionIdInteger = Long.parseLong(transactionIdString);
				accountTranMaster.setStrTxn_id(String.valueOf(transactionIdInteger));
				int addTransaction = this.configurationDao.addTransaction(accountTranMaster);
				return addTransaction;
			}
			else 
			{
				long strTxn_id = Long.parseLong(lastTransaction.get(0).getStrTxn_id());
				System.out.println(strTxn_id);
				long newTxnIdLong = strTxn_id + 1;
				accountTranMaster.setStrTxn_id(String.valueOf(newTxnIdLong));
				int addTransaction = this.configurationDao.addTransaction(accountTranMaster);
				return addTransaction;
			}
		}

		//created on 04-01-2023
		public AccountStatement mapToAccountStatement(AccountTranMaster accountTranMaster,BalanceUpdateInAccountMaster balance) 
		{
			String strAccountNumber = accountTranMaster.getStrAccountNumber();
			String strAccountType = accountTranMaster.getStrAccountType();
			String strLocalTranDate = accountTranMaster.getStrLocal_tran_date();
			Date date = null;
			try 
			{
				date = new SimpleDateFormat("dd-MM-yyyy").parse(strLocalTranDate);
			}
			catch (ParseException e) 
			{
				e.printStackTrace();
			}  
			String strTransaction_amount = accountTranMaster.getStrTransaction_amount();
			long strTxn_id = Long.parseLong(accountTranMaster.getStrTxn_id());
			String transactionId = String.valueOf(strTxn_id);
			accountTranMaster.getStrLocal_tran_time();
			String strClosingBalance = balance.getStrClosingBalance();
			
			AccountStatement accountStatement = new AccountStatement();
			accountStatement.setStrAccountNumber(strAccountNumber);
			accountStatement.setStrAccountType(strAccountType);
			accountStatement.setStrClosingBalance(strClosingBalance);
			accountStatement.setStrIsGLType("N");
			accountStatement.setStrTransactionAmount(strTransaction_amount);
			//accountStatement.setStrTransactionDate(date);
			accountStatement.setStrTransactionDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
			
			accountStatement.setStrTransactionID(transactionId);
			accountStatement.setStrTransactionMode("CREDIT");
			accountStatement.setStrTransactionType("DEPOSIT");
			return accountStatement;
		}
		
		//added on 04-01-2023
		public int addAccountStatement(AccountStatement accountStatement) 
		{
			int addEntryInStatement = this.configurationDao.addEntryInStatement(accountStatement);
			return addEntryInStatement;
		}
	
	// added on 23-12-22 by ankit
	@Override
	public int addBalanceInAccountMaster(BalanceUpdateInAccountMaster balance) 
	{
		int count = this.configurationDao.updateBalance(balance);
		System.out.println(count);
		return count;
	}
	// added on 23-12-22 by ankit

	
	// support method for setting data to balanceUpdateModel
	public BalanceUpdateInAccountMaster mapTobalanceBalanceUpdateInAccountMaster(LoadBalanceModel loadBalanceModel) {
		String strLoadedBalance = loadBalanceModel.getStrLoadedBalance();
		String strAccountNumber = loadBalanceModel.getStrAccountNumber();
		String strAccountType = loadBalanceModel.getStrAccountType();
		BalanceUpdateInAccountMaster balance = new BalanceUpdateInAccountMaster();
		balance.setStrOpeningBalance(strLoadedBalance);
		balance.setStrAccountNumber(strAccountNumber);
		balance.setStrAccountType(strAccountType);
		balance.setStrClosingBalance(strLoadedBalance);

		return balance;
	}
	// added on 23-12-22 by ankit

	// added on 27-12-22 by ankit to fetch the channels
	@Override
	public List<Channels> getChannels() {
		List<Channels> channels = this.configurationDao.getChannels();
		return channels;
	}
	// added on 27-12-22 by ankit to fetch the channels

	@Override
	public String getAvailableBalance(String accountType, String accountNumber) {
		String availableBalance = this.configurationDao.getAvailableBalance(accountType, accountNumber);
		return availableBalance;
	}

	
	
	
	//added on 04-01-2023 to check Account Details by ankit
	@Override
	public List<AccountCreation> checkAccountExistance(LoadBalanceModel loadBalanceModel) 
	{
		String strAccountNumber = loadBalanceModel.getStrAccountNumber();
		String strAccountType = loadBalanceModel.getStrAccountType();
		List<AccountCreation> accountMasterDetails = this.configurationDao.getAccountMasterDetails(strAccountType, strAccountNumber);
		
		return accountMasterDetails;
	}
	
	// code added by prashant T
	@Override
	public List<ChargeMaster> getChargeMasterList(ChargeMaster chargeMaster) throws Exception
	{
		try {
			String serverUrl = this.env.getProperty("ams.account_mgmt_url") + this.env.getProperty("ams.charges_master_list") + "/getChargesMasterList";
			ChargeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, chargeMaster, ChargeMaster[].class);
			List<ChargeMaster> chargesTypeData = Arrays.asList(responseEntity);
			return chargesTypeData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// code added by prashant T
	@Override
	public List<ChargeMaster> getTransactionChargeList(ChargeMaster chargeMaster) throws Exception
	{
		try {
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")	+ this.env.getProperty("ams.charges_master_list") + "/getTransactionChargeList";
			ChargeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, chargeMaster, ChargeMaster[].class);
			List<ChargeMaster> chargesTypeData = Arrays.asList(responseEntity);
			return chargesTypeData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// code added by prashant T
	@Override
	public List<ChargeMaster> getFuelChargeList(ChargeMaster chargeMaster) throws Exception
	{
		try {
			String serverUrl = this.env.getProperty("ams.account_mgmt_url") + this.env.getProperty("ams.charges_master_list") + "/getFuelChargeList";
			ChargeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl, chargeMaster, ChargeMaster[].class);
			List<ChargeMaster> chargesTypeData = Arrays.asList(responseEntity);
			return chargesTypeData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountTypeCharges> getSelectedChargesAccountTypeWise(AccountTypeCharges accountTypeCharges)
			throws Exception {
		try {
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")	+ this.env.getProperty("ams.charges_master_list") + "/getSelectedChargesAccountTypeWise";
			AccountTypeCharges[] responseEntity = this.restTemplate.postForObject(serverUrl, accountTypeCharges, AccountTypeCharges[].class);
			List<AccountTypeCharges> accTypechargesData = Arrays.asList(responseEntity);
			return accTypechargesData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountStatement> getAccountStatementByDate(String fromDate, String toDate) {
		return configurationDao.getAccountStatementByDate(fromDate, toDate);
	}

	@Override
	public List<AccountStatement> getAccountStatement(String accountType, String accountNumber, String fromDate,
			String toDate) {
		return configurationDao.getAccountStatement(accountType, accountNumber, fromDate, toDate);
	}

	@Override
	public AccountTypeCharges addChargingConfigData(AccountTypeCharges accountTypeCharges) {
		try {
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")	+ this.env.getProperty("ams.account_type_charges") + "/add";
			AccountTypeCharges responseEntity = this.restTemplate.postForObject(serverUrl, accountTypeCharges,
					AccountTypeCharges.class);
			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<AccountTypeCharges> getChargeDescriptionBasedOnChargeType(String strChargeType) {
		try 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")
					+ this.env.getProperty("ams.charges_master_list") + "/getChargeDescriptionOnChargeType";
			AccountTypeCharges responseEntity = this.restTemplate.postForObject(serverUrl, strChargeType,
					AccountTypeCharges.class);
			List<AccountTypeCharges> accTypechargesData = Arrays.asList(responseEntity);
			return accTypechargesData;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	  @Override
      public List<GlAccountTypeChargesMaster> getGlAccountType() {
          return configurationDao.getGlAccountType();
      }

	@Override
	public String getDownloadedGLPDFPath(List<GlAccountTypeWiseStatementMaster> glAccountStatements) {
		
		try 
		{
			
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		  
		    Map<String, Object> data = new HashMap<>();
		    

	        data.put("bodyInfoInList", glAccountStatements);	        
	        //data.put("headerInfoMap", customer);
	        data.put("templateName", "gl-account-statement");
	        data.put("fileName", "gl-account-statement-");
	        
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");
		    
		    String responseEntity = restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("personResultAsJsonStr::"+responseEntity);
		
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedGLAccountStatmntFilePath(List<GlAccountTypeWiseStatementMaster> glAccountStatements)
	{
		try
		{
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    Map<String, Object> data = new HashMap<>();			    
	        data.put("bodyInfoInList", glAccountStatements);        
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
	
	@Override
	public String getDownloadedGLAccountStatmntFilePathForExcel(List<GlAccountTypeWiseStatementMaster> glAccountStatements) 
	{
		try
		{
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_excel") +"/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    Map<String, Object> data = new HashMap<>();			    
	        data.put("bodyInfoInList", glAccountStatements);
	        
	        //key contains mapping data for getting value and value contains header name
	        
		    Map<String, String> headerDataMap = new HashMap<>();		    

		    headerDataMap.put("strAccountType", "Account Type");
		    headerDataMap.put("strAccountNumber", "Account Number");
		    headerDataMap.put("strRef", "Reference Number");
		    headerDataMap.put("strTranID", "Transaction ID");
		    headerDataMap.put("strAmount", "Amount");
		    headerDataMap.put("strTransactionDate", "Creation Date");
		    
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

	@Override
	public List<GlAccountTypeChargesMaster> getAccountTypecharges() {
		return configurationDao.getAccountTypecharges();
	}

	
	@Override
	public List<AccountTypeMaster> getNonCreditAccounTypeObject(AccountTypeMaster accountTypeMster) 
	{
		try 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account_type") +"/getNonCreditAccounType";
			AccountTypeMaster[] responseEntity = this.restTemplate.postForObject(serverUrl , accountTypeMster, AccountTypeMaster[].class);
			List<AccountTypeMaster> accountTypeMasters = Arrays.asList(responseEntity);
			return accountTypeMasters;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAvailableBalanceBasedOnAccountTypeAndNumber(AccountCreation accountCreation) 
	{
		try 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.account") +"/getAvailableBalBasedOnAccountTypeAndNumber";
			String responseEntity = this.restTemplate.postForObject(serverUrl , accountCreation, String.class);
			return responseEntity;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception while getAccountInfoListBasedOnTypes ::"+e);
		}
		return null;
	}
	
	@Override
	public PreAccountMaster getKycDataForverification(PreAccountMaster preAccountMaster)
	{
		try 
		{
			String serverUrl = this.env.getProperty("ams.account_mgmt_url")+ this.env.getProperty("ams.pre_account_master") +"/getKycDataForverification";
			PreAccountMaster responseEntity = this.restTemplate.postForObject(serverUrl , preAccountMaster, PreAccountMaster.class);
			return responseEntity;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception while getKycDataForverification ::"+e);
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> getCardNoList() 
	{
		return configurationDao.getCardNoList();
	}
	
	//Added by Sunny Soni for gettig card number Start 
	@Override
	public String getSinglemaskedCardNumber(String cardNumber, String cardType)
	{
		return configurationDao.getSinglemaskedCardNumber(cardNumber, cardType);
	}
	
	@Override
	public List<CardTypeModel> getCardTypeInfo(CardTypeModel cardTypeModel) 
	{
		return configurationDao.getCardTypeInfo(cardTypeModel);
	}
	
	//Added by Sunny Soni for gettig card number End
	@Override
	public boolean isAccountAlreadyExist(AccountCreation accountCreation) throws Exception
	{
		return false;
	}
	
	@Override
	public int addTranscationTypeData(TransactionTypeModel transactionTypeModel) {
		int addTxnEntry =this.configurationDao.addTranscationTypeData(transactionTypeModel);
		return addTxnEntry;

	}

	@Override
	public boolean checkTransactionTypeDataAlreadyConfigured(TransactionTypeModel transactionTypeModel) throws Exception {
		return configurationDao.checkTransactionTypeDataAlreadyConfigured(transactionTypeModel);
	}
	
	@Override
	public cardAcntLinkageMaster getInfofrmCardLinkage(String cardNumber) {
		return configurationDao.getInfofrmCardLinkage(cardNumber);
	}


	@Override
	public CardDetailsList getCardDetails(String cardNumber) {
		return configurationDao.getCardDetails(cardNumber);
	}
	
	@Override
	public int insertRequestEntry(TranMaster tranMaster) {
		 return configurationDao.insertEntry(tranMaster);
	}

	@Override
	public int insertResponseEntry(TranMaster tranMaster) {
		return configurationDao.insertEntry(tranMaster);
	}

	@Override
	public boolean checkStanAlreadyExist(String stan) {
		return configurationDao.checkStanAlreadyExist(stan);
	}

	@Override
	public int addCardAccountLinkageCMS(CardAccountLinkage cardAccountLinkage) {
		int count = this.configurationDao.addCardAccountLinkageCMS(cardAccountLinkage);
		return count;
	}
	
	@Override
	public String getNetworkType(CardAccountLinkage cardAccountLinkage) {
		return configurationDao.getNetworkType(cardAccountLinkage);
	}

	@Override
	public String getCardHolderName(CardAccountLinkage cardAccountLinkage) {
		return configurationDao.getCardHolderName(cardAccountLinkage);
	}

	@Override
	public boolean isCardAlreadyLinked(String tokenCard) throws Exception {
		return configurationDao.isCardAlreadyLinked(tokenCard);
	}

	//Added by prashant Tayde 20 Aug 2023
	@Override
	public int updateIssueCardToCustomer(CardDetails cardDetails) 
	{
		return configurationDao.updateIssueCardToCustomer(cardDetails);
	}
	
	//Added by prashant Tayde 20 Aug 2023
	@Override
	public List<ConnectionConfigModel> getEndPoints() 
	{
		return configurationDao.getEndPoints();
	}
	
	//Added by Jyoti
	@Override
	public CardStatus getCardStatus(String tokenCard) throws Exception {
		
		return configurationDao.getCardStatus(tokenCard);
	}

	@Override
	public int addAccountTypeDesc(AccountTypeMaster accountTypeMaster) 
	{
		return configurationDao.addAccountTypeDesc(accountTypeMaster);
	}

	@Override
	public int addEndPointEntry(ConnectionConfigModel connConfig) 
	{
		return configurationDao.addEndPointEntry(connConfig);
	}
	
	@Override
	public BinModel getBinOldDataforUpdate(BinModel binModel2) 
	{
		return configurationDao.getBinOldDataforUpdate(binModel2);
	}
	
	@Override
	public BinModel getBinOldDataforDelete(BinModel binModel) 
	{
		return configurationDao.getBinOldDataforDelete(binModel);
	}

	@Override
	public BranchTypeModel getBranchTypeOldDataForDelete(BranchTypeModel branchTypeModel) 
	{
		return configurationDao.getBranchTypeOldDataForDelete(branchTypeModel);
	}
	
	@Override
	public BranchTypeModel getBranchTypeOldDataForUpdate(BranchTypeModel branchTypeModel) 
	{
		return configurationDao.getBranchTypeOldDataForUpdate(branchTypeModel);
	}

	
	/*
	 * public void addAuditRecords(Object obj, String tableName) { try {
	 * ObjectMapper objectMapper = new ObjectMapper(); Map<String, Object> map =
	 * objectMapper.convertValue(obj, Map.class); List<String> list = new
	 * ArrayList<>(); map.forEach((k,v) -> { String key = k; Object value = v;
	 * //list.add(key); list.add(key + " : " + value); }); if (list.size() > 0) {
	 * String columnNames = list.toString(); System.out.println(columnNames);
	 * auditService.addAudit(sessionDTO.getParticipantid(), tableName, columnNames,
	 * null, null, "INSERT", this.sessionDTO.getLoginID()); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
	
	@SuppressWarnings("unchecked")
	public void updateAuditData(Object newFeild, Object oldFeild, String tableName)
	{
		try 
		{
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map = objectMapper.convertValue(newFeild, Map.class);
			List<String> list = new ArrayList<>();
			map.forEach((k,v) ->
			{
				String key = k;
				Object value = v;
				list.add(key + " : " + value);
			});
			if (list.stream().findAny().isPresent())
			{
				String newFeilds = list.toString();
				System.out.println(newFeilds);
				
				if(!Optional.ofNullable(newFeilds).isPresent())
				{
					auditService.addAudit(sessionDTO.getParticipantid(), tableName, null, newFeilds, null, "INSERT", this.sessionDTO.getLoginID());
				}
				else 
				{
					ObjectMapper objectMappers = new ObjectMapper();
					Map<String, Object> maps = objectMappers.convertValue(oldFeild, Map.class);
					List<String> lists = new ArrayList<>();
					maps.forEach((k,v) ->
					{
						String key = k;
						Object value = v;
						lists.add(key + " : " + value);
					});
					if (lists.stream().findAny().isPresent())
					{
						String oldFeilds = lists.toString();
						auditService.addAudit(sessionDTO.getParticipantid(), tableName, null, newFeilds, oldFeilds, CommonConstant.AuditConfiguration.UPDATE_AUDIT, this.sessionDTO.getLoginID());
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void deletedAuditRecords(Object obj, String tableName)
	{
		try 
		{
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map = objectMapper.convertValue(obj, Map.class);
			List<String> list = new ArrayList<>();
			map.forEach((k,v) ->
			{
				String key = k;
				Object value = v;
				list.add(key + " : " + value);
			});
			if (list.stream().findAny().isPresent())
			{
				String columnNames = list.toString();
				auditService.addAudit(sessionDTO.getParticipantid(), tableName, columnNames, null, null, CommonConstant.AuditConfiguration.DELETE_AUDIT, this.sessionDTO.getLoginID());
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public BranchCodeModel getOldBranchCodeDataForUpdate(BranchCodeModel branchCodeModel) 
	{
		return configurationDao.getOldBranchCodeDataForUpdate(branchCodeModel);
	}
	
	@Override
	public BranchCodeModel getOldBranchCodeDataForDelete(BranchCodeModel branchCodeModel) 
	{
		return configurationDao.getOldBranchCodeDataForDelete(branchCodeModel);
	}

	@Override
	public AccountTypeModel getOldAccountTypeDataForUpdate(AccountTypeModel accountTypeModel) 
	{
		return configurationDao.getOldAccountTypeDataForUpdate(accountTypeModel);
	}
	@Override
	public AccountTypeModel getOldAccountTypeDataForDelete(AccountTypeModel accountTypeModel) 
	{
		return configurationDao.getOldAccountTypeDataForDelete(accountTypeModel);
	}

	@Override
	public AddressTypeModel getOldAddressTypeDataForUpdate(AddressTypeModel addressTypeModel) 
	{
		return configurationDao.getOldAddressTypeDataForUpdate(addressTypeModel);
	}
	
	@Override
	public AddressTypeModel getOldAddressTypeDataForDelete(AddressTypeModel addressTypeModel) 
	{
		return configurationDao.getOldAddressTypeDataForDelete(addressTypeModel);
	}
	
	@Override
	public CardPlasticModel getOldCardPlasticData(CardPlasticModel cardPlasticModel) 
	{
		return configurationDao.getOldCardPlasticData(cardPlasticModel);
	}

	@Override
	public CardTypeModel getOldCardTypeDataForDelete(CardTypeModel cardTypeModel) 
	{
		return configurationDao.getOldCardTypeDataForDelete(cardTypeModel);
	}
	
	@Override
	public CardTypeModel getOldCardTypeDataForUpdate(CardTypeModel cardTypeModel) 
	{
		return configurationDao.getOldCardTypeDataForUpdate(cardTypeModel);
	}
	
	@Override
	public EmailTypeModel getOldEmailData(EmailTypeModel emailTypeModel) 
	{
		return configurationDao.getOldEmailData(emailTypeModel);
	}

	@Override
	public NcmcServiceModel getOldNCMCData(NcmcServiceModel ncmcServiceModel) 
	{
		return configurationDao.getOldNCMCData(ncmcServiceModel);
	}

	@Override
	public KeyConfigModel getOldKeyData(KeyConfigModel keyConfigModel) 
	{
		return configurationDao.getOldKeyData(keyConfigModel);
	}

	@Override
	public CardDetails getOldCardDetails(CardDetails cardDetails) 
	{
		return configurationDao.getOldCardDetails(cardDetails);
	}

	@Override
	public CardTypeModel getOldCardTypeMccDataForDelete(CardTypeModel cardtypeModel) 
	{
		return configurationDao.getOldCardTypeMccDataForDelete(cardtypeModel);
	}

	@Override
	public CardTemplateModel getoldCardTemplateData(CardTemplateModel cardTemplateModel) 
	{
		return configurationDao.getoldCardTemplateData(cardTemplateModel);
	}

	@Override
	public CardTokenModel getOldCardTokenData(CardTokenModel cardTokenModel) 
	{
		return configurationDao.getOldCardTokenData(cardTokenModel);
	}

	@Override
	public CardDetails getOldCustomerIdOnTokencard(CardDetails cardDetails) 
	{
		return configurationDao.getOldCustomerIdOnTokencard(cardDetails);
	}

	@Override
	public CardPlasticModel getOldCardPlasticForUpdate(CardPlasticModel cardPlasticModel) 
	{
		return configurationDao.getOldCardPlasticForUpdate(cardPlasticModel);
	}

	@Override
	public CustomerDetails getOldCustomerDetailsOnId(CustomerDetails customerDetails) 
	{
		return configurationDao.getOldCustomerDetailsOnId(customerDetails);
	}

	@Override
	public AddressDetailsList getOldAddressDetailsBasedOnCustId(AddressDetailsList addressDetails) 
	{
		return configurationDao.getOldAddressDetailsBasedOnCustId(addressDetails);
	}

	@Override
	public PhoneDetailsList getOldPhoneDetailsData(PhoneDetailsList phoneDetails) 
	{
		return configurationDao.getOldPhoneDetailsData(phoneDetails);
	}

	@Override
	public BulkUploadData getOldBulkUploadData(BulkUploadData bulkUploadData) 
	{
		return configurationDao.getOldBulkUploadData(bulkUploadData);
	}

	@Override
	public DocumentDetailsList getDocumentDetailsList(DocumentDetailsList documentDetailsList) 
	{
		return configurationDao.getDocumentDetailsList(documentDetailsList);
	}

	@Override
	public EmailDetailsList getEmailDetailsList(EmailDetailsList emailDetailsList) 
	{
		return configurationDao.getEmailDetailsList(emailDetailsList);
	}

	@Override
	public CardDetails getOldCardPlasticDetailsData(CardDetails cardDetails) {
		
		return configurationDao.getOldCardPlasticDetailsData(cardDetails);
	}

	@Override
	public CardAccountLinkage getOldCardAccountLinkageDetails(CardAccountLinkage cardAccountLinkage) 
	{
		return configurationDao.getOldCardAccountLinkageDetails(cardAccountLinkage);
	}

	@Override
	public List<CardTypeModel> getCardTypeBasedOnParticipantId(CardTypeModel cardTypeModel) 
	{
		return configurationDao.getCardTypeBasedOnParticipantId(cardTypeModel);
	}

	@Override
	public List<CardTypeModel> getCardType(CardTypeModel cardTypeModel) 
	{
		return configurationDao.getCardType(cardTypeModel);
	}

	@Override
	public List<CardTypeModel> getAccountTypeList() 
	{
		try 
		{
			String serverUrl = this.env.getProperty("ams.creditCardurl")+ this.env.getProperty("ams.account_type") +"/getAccountTypeList";
			CardTypeModel[] responseEntity = this.restTemplate.getForObject(serverUrl , CardTypeModel[].class);
			List<CardTypeModel> cardList = Arrays.asList(responseEntity);
			return cardList;				
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception while getAccountInfoListBasedOnTypes ::"+e);
		}
		return null;
	}

	

}
