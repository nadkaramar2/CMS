package com.traneco.configuration.dao;

import java.util.List;
import java.util.Map;

import com.traneco.clientSearch.model.AddressDetailsList;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardStatus;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DocumentDetailsList;
import com.traneco.clientSearch.model.EmailDetailsList;
import com.traneco.clientSearch.model.PhoneDetailsList;
import com.traneco.common.KeyValueBean;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountDetails;
import com.traneco.configuration.model.AccountInterestMaster;
import com.traneco.configuration.model.AccountLinkedWalletMaster;
import com.traneco.configuration.model.AccountNumberModel;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.AccountTransactionLimitModel;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.AccountTypeModel;
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
import com.traneco.configuration.model.GLAccountLoadingMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.GlAccountTypeChargesMaster;
import com.traneco.configuration.model.IdentityProofDocumentTypeMaster;
import com.traneco.configuration.model.InstantAccountCreation;
import com.traneco.configuration.model.IsoConfigModel;
import com.traneco.configuration.model.KeyConfigModel;
import com.traneco.configuration.model.LoadBalanceModel;
import com.traneco.configuration.model.MccCodeModel;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.model.NcmcServiceModel;
import com.traneco.configuration.model.ParticipantConfigModel;
import com.traneco.configuration.model.PhoneTypeModel;
import com.traneco.configuration.model.StateModel;
import com.traneco.configuration.model.TaxConfigModel;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TransactionTypeModel;
import com.traneco.configuration.model.UpdateGLAccount;
import com.traneco.configuration.model.cardAcntLinkageMaster;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.AccountTransactionLimitation;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;

public interface ConfigurationDao {

	int addBin(BinModel paramBinModel);

	List<BinModel> getBin();

	List<CountryModel> getCountry();

	int addCountry(CountryModel paramCountryModel);

	List<StateModel> getState();
	
	List<StateModel> getStateData(String countryId);
	
	List<CityModel> getCityData(String stateId);

	List<CountryModel> getPhoneCode();
	
	int addState(StateModel paramStateModel);

	List<BranchTypeModel> getBranch();

	int addBranch(BranchTypeModel paramBranchTypeModel);

	List<BranchCodeModel> getBranchCode();

	int addBranchCode(BranchCodeModel paramBranchCodeModel);

	List<AccountTypeModel> getAccount();

	int addAccount(AccountTypeModel paramAccountTypeModel);

	List<AddressTypeModel> getAddress();

	int addAddress(AddressTypeModel paramAddressTypeModel);

	List<CardTypeModel> getCardType();

	int addCardType(CardTypeModel paramCardTypeModel);

	List<CardTemplateModel> getCardTemplate();

	int addCardTemplate(CardTemplateModel paramCardTemplateModel);

	List<CardPlasticModel> getCardPlastic();

	int addCardPlastic(CardPlasticModel paramCardPlasticModel);

	List<EmailTypeModel> getEmail();

	int addEmail(EmailTypeModel paramEmailTypeModel);

	List<PhoneTypeModel> getPhone();

	int addPhone(PhoneTypeModel paramPhoneTypeModel);

	List<NcmcServiceModel> getNcmc();

	int addNcmcService(NcmcServiceModel paramNcmcServiceModel);

	List<KeyConfigModel> getKeyConfig();

	// int addKeyConfig(KeyConfigModel paramKeyConfigModel);

	int addKeyConfig(String attr_id, String attr_value, String part_id);

	int validateSenPwd(CardNoModel paramCardNoModel);

	int deleteBin(String paramString);

	int updateBin(BinModel paramBinModel);

	int deleteBranch(String paramString);

	int updateBranch(BranchTypeModel paramBranchTypeModel);

	int deleteBranchCode(String paramString);

	int updateBranchCode(BranchCodeModel paramBranchCodeModel);

	int deleteAccount(String paramString);

	int updateAccount(AccountTypeModel paramAccountTypeModel);

	int deleteAddress(String paramString);

	int updateAddress(AddressTypeModel paramAddressTypeModel);

	int deleteCardType(String paramString);

	int updateCardType(CardTypeModel paramCardTypeModel);

	int deleteCardTemplate(String paramString);

	int updateCardTemplate(CardTemplateModel paramCardTemplateModel);

	int deleteCardPlastic(String paramString);

	int updateCardPlastic(CardPlasticModel paramCardPlasticModel);

	int deleteEmail(String paramString);

	int updateEmail(EmailTypeModel paramEmailTypeModel);

	int deleteNCMC(String paramString);

	int updateNCMC(NcmcServiceModel paramNcmcServiceModel);

	int deleteKey(String paramString);

	int updateKey(KeyConfigModel paramKeyConfigModel);

	List<StateModel> getStateList(String paramString);

	List<CityModel> getCityList(String paramString);

	Map<String, Object> getCardNo(String paramString);

	List<EmbossConfigModel> getEmbossConfig();

	List<EmbossConfigModel> getEmbossNameConfig();

	int addEmboss(EmbossConfigModel paramEmbossConfigModel);

	List<NcmcServiceModel> getNcmcCode();

	List<CardTypeModel> getCardTypeNCMC();

	int addNCMCService(NcmcServiceModel paramNcmcServiceModel);

	List<KeyValueBean> getncmcList(String paramString);

	String getNetworkScheme(String paramString);

	String getBinFlag(String paramString);

	List<KeyValueBean> getEndPoint();

	List<KeyValueBean> getCfg_MCC();

	List<KeyValueBean> getCard_Type_MCC(String paramString);

	List<KeyValueBean> getCard_MCC(String paramString);

	int getBinCardType(String paramString);

	int getAddressType(String paramString);

	int getEmailType(String paramString);

	int getDocType(String paramString);

	int getCityID(String paramString);

	int getStateID(String paramString);

	int getCountryID(String paramString);

	int getAccountID(String paramString);

	int updateMCC(CardTypeModel paramCardTypeModel);

	int getBatchUpdateCount(String paramString);

	int addCardToken(CardTokenModel paramCardTokenModel);

	List<CardTokenModel> getCardTokenList();

	List<CardTypeModel> getCfgCardType();

	List<MobileTokenModel> getMobileTokenList();

	int addIsoData(IsoConfigModel paramIsoConfig);

	List<IsoConfigModel> getISOConfigList();

	List<KeyValueBean> getParticipantList();

	int addConnection(ConnectionConfigModel connConfig);

	List<ConnectionConfigModel> getConnectionList();

	int addParticipant(ParticipantConfigModel partConfig);

	List<ParticipantConfigModel> getParticipantConfigList();

	
	
	//Code Changes done by prashant for acccount management(AMS)
	
	List<ChargesTypeModel> getChargesTypeList();

	// List<AccountType> getAccountTypeList();

	List<CardType> getCardTypeList();

	List<TaxConfigModel>getTaxType();
		
	List<AccountCreation> getAccountCreation();

	int addAccountCreation(AccountCreation accountCreation);

	List<AccountCreation> getAccountType();
	
	List <MccCodeModel> getMccCodeList();

	int deleteAccountCreation(String paramString);

	int addAccountTypeMaster(AccountTypeMaster accountTypeMaster);

	int updateAccountType(AccountTypeMaster accountTypeMaster);

	List<AccountTypeMaster> getAccounTypeMaster();

	int deleteAccountType(String paramString);

	int updateAccountCreation(AccountCreation accountCreation);

	int deleteCardAccountLinkage(String paramString);

	int addCardAccountLinkage(CardAccountLinkage cardAccountLinkage);

	int updateCardAccountLinkage(CardAccountLinkage cardAccountLinkage);

	List<CardAccountLinkage> getCardAccountLinkage();
	
	List<AccountCreditLimitCategory> getAccountCreditLimiCategory();
	
	int addCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory);
	
	int updateAccountLimitCredit(AccountCreditLimitCategory accountCreditLimitCategory);
	
	int saveMccInterest(MccWiseInterestModel mccWiseInterestModel);
	
	List<MccWiseInterestModel> getMccWiseInterest();
	
	List<CategoryListModel> getCategoryList();
	
	int updateMCCWiseInterest(MccWiseInterestModel mccWiseInterestModel);
	
	
	int addAccountTransactionLimit(AccountTransactionLimitModel accountTransactionLimitModel);
	
	int addLoadBalance(LoadBalanceModel loadBalanceModel);
	
	List<LoadBalanceModel> getLoadBalance();
	
	
	
	 public List<AccountDetails>  getAccountDetailsData(AccountDetails accountDetails);
	
	public List<AccountTransactionLimitModel> getTransactionLimitData(String accountType);
	 
	//Code Changes done by prashant for acccount management(AMS)
	
	// Added by Sunny Soni for getting account Number Start
	int updateLastAccountNumber(String updatedAccountNo, String accountType);

	String getAccounNumberBasedOnAccountType(String accountType);

	List<AccountNumberModel> getAccounNumberListBasedOnAccountType(String accountType);
	// Added by Sunny Soni for getting account Number End
	
	int addInstantAccount(InstantAccountCreation instantAccountCreation);
	
	public List<InstantAccountCreation> getInstantAccount();
	
	public List<AccountCreation> getAccountCreationData(String id);
	
	//Added by Sunny Soni for updating account master from instant account Start
	 int updateAccountCreationFromInstanceAccount(AccountCreation accountCreation);
	 //Added by Sunny Soni for updating account master from instant account End

	 public int [] batchAccountInsert(List<AccountCreation> accountCreationlist);
	 
	//Added by Sunny Soni for get instance or viewed type related account list for edit Start
	 List<AccountCreation> getAccountCreationListDataBasedOnTypes(String isInstanceAccountStr);
	//Added by Sunny Soni for get instance or viewed type related account list for edit End
	  
	 List<AccountTransactionLimitation> getAccountTransactionLimitationList(AccountTypeMaster accountTypeMaster);
	  
	 public List<CardAccountLinkage> getCardAccountLinkage(String accountType,String accountNumber);
	  
	 public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType,String cardNumber);
	  
	  List<AddressProofDocumentTypeMaster> getAddressProofDocumentTypeMasters(String participantId);
	  
	  List<IdentityProofDocumentTypeMaster> getIdentityProofDocumentTypeMasters(String participantId);
	  List<AccountTypeMaster> getAmountTransactionlist();
	
	  List<AccountCreditLimitCategory> getAccountCreditTypeCategorylist();
	  
	  List<AccountCreation> getInterestOutstanding(String creditType);
	  
	  List<AccountInterestMaster> getOutStandingList(String accountNumber);
	  
	  CardAccountLinkage getCardAccountLinkages(CardAccountLinkage cardAccountLinkage);
	  
	  AccountTransactionLimitModel getAccountTransactionLimitModel(AccountTransactionLimitModel accountTransactionLimitModel);
	  
	  String getStateName(String id);	  
	  
	  String getCityName(String id);
	  
	  String getCountryName(String id);
	  
	  List<CardAccountLinkage> getCardLinkAccountList(CardAccountLinkage cardAccountLinkage);

	  List<AccountCreditLimitCategory> getCreditTypelist();

	  List<AccountLinkedWalletMaster> getLinkedAccountList(AccountLinkedWalletMaster accountLinkedWalletMaster);

	  List<AccountLinkedWalletMaster> getlinkedAccountWallet(String walletAccountNumber);
	  
	//added on 23-12-22 by ankit
	List<AccountTypeMaster> getAccounTypeFromAcocuntMaster();
	//added on 23-12-22 by ankit

	//added on 23-12-22 by ankit
	int addLoadBalances(LoadBalanceModel loadBalanceModel);
	//added on 23-12-22 by ankit
	
	//added on 23-12-22 by ankit
	List<AccountCreation> getAccountMasterDetails(String strAccountType,String strAccountNumber);
	//added on 23-12-22 by ankit

	//added on 23-12-22 by ankit
	int updateBalance(BalanceUpdateInAccountMaster balance);
	//added on 23-12-22 by ankit

	//added on 26-12-22 to get loadCash
	String getCategoryTypeFromAccountTypeMaster(String accountType);

	//added on 26-12-22 to get loadCash
	String getAllowLoadCashFromAccountTypeMaster(String strAccountType);

	//added on 27-12-22 to get loadCash
	List<Channels> getChannels();

	//added on 02-01-23 to get Available Balance
	String getAvailableBalance(String strAccountType,String strAccountNumber);

	//added on 02-01-23 to get the Last TransactionID
	List<AccountTranMaster> getLastTransaction();

	int addTransaction(AccountTranMaster accountTranMaster);

	int addEntryInStatement(AccountStatement accountStatement);
	
	int addGLEntryInStatement(GLAccountStatement accountStatement);
	/**
	 * Account Statement changes by Jyoti Shirahatti
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<AccountStatement> getAccountStatementByDate(String fromDate,String toDate);
	public List<AccountStatement> getAccountStatement(String accountType,String accountNumber,String fromDate,String toDate);

		
		//created by ankit to fetch GL Account Types on 09-01-2023
		List<GLAccountLoadingMaster> getGLAccountTypes();

		//fetch closing balance only
		//List<GLAccountLoadingMaster> LoadCountAndClosingBalance(String strAccountNumber);

		//updating closing balance in the gl_account by ankit on 09-01-2023
		int updateGLAccountDetails(UpdateGLAccount updateGLAccount);

		//method to fetcht the closing balace in ajax Call to get the closng balance by ankit on 09-01-2023
		String fetchClosingBalanceOfGlAccount(String accountType,String accountNumber);

		//fetching account type and description by ankit on 09-02-2023
		List<GLAccountLoadingMaster> fetchGLAccountDetails(String accountNumber);

		//for inserting data in gl_loading by ankit on 09-01-2023
		int addLoadInGLAccount(GLAccountLoadingMaster glAccountLoadingMaster);
	
		
		List<GlAccountTypeChargesMaster> getAccountTypecharges();
		
		List<GlAccountTypeChargesMaster> getGlAccountType();
		
		//Added by Sunny SOni for getting card number Start
		List<Map<String, Object>> getCardNoList();
		
		String getSinglemaskedCardNumber(String cardNumber, String cardType);
		
		List<CardTypeModel> getCardTypeInfo(CardTypeModel cardTypeModel);
		
		//Added by Sunny SOni for getting card number End
		//Added by Sagark for transaction Type Creation Start
		int addTranscationTypeData(TransactionTypeModel transactionTypeModel);
		//Added by Sagark for transaction Type Creation End
		
		boolean	checkTransactionTypeDataAlreadyConfigured(TransactionTypeModel transactionTypeModel);
		
		cardAcntLinkageMaster getInfofrmCardLinkage(String cardNumber);

		int insertEntry(TranMaster tranMaster);

		CardDetailsList getCardDetails(String cardNumber);
		
		boolean checkStanAlreadyExist(String stan);

		int addCardAccountLinkageCMS(CardAccountLinkage cardAccountLinkage);
		
		String getNetworkType(CardAccountLinkage cardAccountLinkage);
		
		String getCardHolderName(CardAccountLinkage cardAccountLinkage);
		
		boolean isCardAlreadyLinked(String tokenCard);
		
		CardStatus getCardStatus(String tokenCard);

		//Added by prashant Tayde 20Aug 2023 
		int updateIssueCardToCustomer(CardDetails cardDetails);
		
		List<ConnectionConfigModel> getEndPoints();

		int addAccountTypeDesc(AccountTypeMaster accountTypeMaster);

		int addEndPointEntry(ConnectionConfigModel connConfig);

		BinModel getBinOldDataforUpdate(BinModel binModel2);
		BinModel getBinOldDataforDelete(BinModel binModel);
		
		BranchTypeModel getBranchTypeOldDataForUpdate(BranchTypeModel branchTypeModel);
		BranchTypeModel getBranchTypeOldDataForDelete(BranchTypeModel branchTypeModel);
		
		BranchCodeModel getOldBranchCodeDataForUpdate(BranchCodeModel branchCodeModel);
		BranchCodeModel getOldBranchCodeDataForDelete(BranchCodeModel branchCodeModel);

		AccountTypeModel getOldAccountTypeDataForUpdate(AccountTypeModel accountTypeModel);
		AccountTypeModel getOldAccountTypeDataForDelete(AccountTypeModel accountTypeModel);

		AddressTypeModel getOldAddressTypeDataForDelete(AddressTypeModel addressTypeModel);
		AddressTypeModel getOldAddressTypeDataForUpdate(AddressTypeModel addressTypeModel);

		CardTypeModel getOldCardTypeDataForDelete(CardTypeModel cardTypeModel);
		CardTypeModel getOldCardTypeDataForUpdate(CardTypeModel cardTypeModel);
		
		CardTemplateModel getoldCardTemplateData(CardTemplateModel cardTemplateModel);
		
		
		CardPlasticModel getOldCardPlasticData(CardPlasticModel cardPlasticModel);

		EmailTypeModel getOldEmailData(EmailTypeModel emailTypeModel);

		NcmcServiceModel getOldNCMCData(NcmcServiceModel ncmcServiceModel);

		KeyConfigModel getOldKeyData(KeyConfigModel keyConfigModel);

		CardDetails getOldCardDetails(CardDetails cardDetails);

		
		CardTypeModel getOldCardTypeMccDataForDelete(CardTypeModel cardtypeModel);

		CardTokenModel getOldCardTokenData(CardTokenModel cardTokenModel);

		CardDetails getOldCustomerIdOnTokencard(CardDetails cardDetails);

		CardPlasticModel getOldCardPlasticForUpdate(CardPlasticModel cardPlasticModel);

		CustomerDetails getOldCustomerDetailsOnId(CustomerDetails customerDetails);

		AddressDetailsList getOldAddressDetailsBasedOnCustId(AddressDetailsList addressDetails);

		PhoneDetailsList getOldPhoneDetailsData(PhoneDetailsList phoneDetails);

		BulkUploadData getOldBulkUploadData(BulkUploadData bulkUploadData);

		DocumentDetailsList getDocumentDetailsList(DocumentDetailsList documentDetailsList);

		EmailDetailsList getEmailDetailsList(EmailDetailsList emailDetailsList);

		CardDetails getOldCardPlasticDetailsData(CardDetails cardDetails);

		CardAccountLinkage getOldCardAccountLinkageDetails(CardAccountLinkage cardAccountLinkage);

		List<CardTypeModel> getCardTypeBasedOnParticipantId(CardTypeModel cardTypeModel);

		List<CardTypeModel> getCardType(CardTypeModel cardTypeModel);
		
		
		//Ended by prashant Tayde 20 Aug 2023
}