package com.traneco.configuration.services;

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
import com.traneco.service.model.AccountTransactionLimitation;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ChargeMaster;
import com.traneco.service.model.PreAccountMaster;

public interface ConfigurationService 
{
	int addBin(BinModel paramBinModel);

	List<BinModel> getBin();

	List<CountryModel> getCountry();

	int addCountry(CountryModel paramCountryModel);

	List<StateModel> getState();

	List<StateModel> getStateData(String countryId);

	List<CityModel> getCityData(String stateId);

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

	List<CardTypeModel> getCardTypeNCMC();

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

	int addCardToken(CardTokenModel paramCardTokenModel);

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

	int addNCMCService(NcmcServiceModel paramNcmcServiceModel);

	List<KeyValueBean> getncmcList(String paramString);

	String getNetworkScheme(String paramString);

	String getBinFlag(String paramString);

	List<KeyValueBean> getEndPoint();

	List<KeyValueBean> getCfg_MCC();

	List<KeyValueBean> getCard_Type_MCC(String paramString);

	int getBinCardType(String paramString);

	int getAddressType(String paramString);

	int getEmailType(String paramString);

	int getDocType(String paramString);

	int getCityID(String paramString);

	int getStateID(String paramString);

	int getCountryID(String paramString);

	int getAccountID(String paramString);

	int updateMCC(CardTypeModel paramCardTypeModel);

	List<KeyValueBean> getCard_MCC(String paramString);

	int getBatchUpdateCount(String paramString);

	List<CardTokenModel> getCardTokenList();

	List<CardTypeModel> getCfgCardType();

	Object addCardScheduler(CardTokenModel paramCardTokenModel);

	Object addMobileScheduler(MobileTokenModel paramMobileTokenModel);

	List<MobileTokenModel> getMobileTokenList();

	int addIsoData(IsoConfigModel paramIsoConfig);

	List<IsoConfigModel> getISOConfigList();

	List<KeyValueBean> getParticipantList();

  int addConnection(ConnectionConfigModel connConfig);

  List<ConnectionConfigModel> getConnectionList();

  int addParticipant(ParticipantConfigModel partConfig);
  
  List<ParticipantConfigModel> getParticipantConfigList();
  
  //Added by prashant Tayde for account management(AMS)
  
  List<ChargesTypeModel> getChargesTypeList();
  
  //List<AccountType> getAccountTypeList();
  
  List<TaxConfigModel>getTaxType();
  
  List<CardType> getCardTypeList();
  
  List<AccountCreation> getAccountCreation();
  
  int addAccountCreation(AccountCreation accountCreation);
  
  List<AccountCreation> getAccountType();
  
  List <MccCodeModel> getMccCodeList();
  
  List<CountryModel> getPhoneCode();
  
  int deleteAccountCreation(String paramString);
   
  int updateAccountCreation(AccountCreation accountCreation);
  
  int addAccountTypeMaster(AccountTypeMaster accountTypeMaster);
  
  int deleteAccountType(String paramString);
  
  int updateAccountType(AccountTypeMaster accountTypeMaster);
  
  List<AccountTypeMaster> getAccounTypeMaster();
  
  int deleteCardAccountLinkage(String paramString);
  
  int addCardAccountLinkage(CardAccountLinkage cardAccountLinkage);
  
  int updateCardAccountLinkage(CardAccountLinkage cardAccountLinkage);
  
  List<CardAccountLinkage> getCardAccountLinkage();
  
  List<AccountCreditLimitCategory> getAccountCreditLimiCategory();
  
  
  int addCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory);
  
  int updateAccountLimitCredit(AccountCreditLimitCategory accountCreditLimitCategory);
  
  List<MccWiseInterestModel> getMccWiseInterest();
  
  List<CategoryListModel> getCategoryList();
  
  int updateMCCWiseInterest(MccWiseInterestModel mccWiseInterestModel);
  
  int addAccountTransactionLimit(AccountTransactionLimitModel accountTransactionLimitModel);
  
  
  int addLoadBalance(LoadBalanceModel loadBalanceModel);
   
  List<LoadBalanceModel> getLoadBalance();
  
  //Ended by Prashant Tayde for account management(AMS)
  
  
  //Added by Sunny Soni for getting account Number Start
  int updateLastAccountNumber(String updatedAccountNo, String accountType);
  
  String getAccounNumberBasedOnAccountType(String accountType);
  
  
  
  List<AccountNumberModel> getAccounNumberListBasedOnAccountType(String accountType);
  //Added by Sunny Soni for getting account Number End
  
  
  //Added by prashant Tayde
  
  int addInstantAccount(InstantAccountCreation instantAccountCreation);
  
  public List<InstantAccountCreation> getInstantAccount();
  
  
  public List<AccountCreation> getAccountCreationData(String id);

  public List<AccountDetails>  getAccountDetailsData(AccountDetails accountDetails);
  
  public List<AccountTransactionLimitModel> getTransactionLimitData(String accountType);
  
  //Ended by Prashant Tayde
  
  
  //Added by Sunny Soni for updating account master from instant account Start
  int updateAccountCreationFromInstanceAccount(AccountCreation accountCreation);
  //Added by Sunny Soni for updating account master from instant account End
  
  public int [] creatingInstancesAccountsByBatchInsert(InstantAccountCreation instantAccountCreation);

  //Added by Sunny Soni for get instance or viewed type related account list for edit Start
  List<AccountCreation> getAccountCreationListDataBasedOnTypes(String isInstanceAccountStr);
  
  List<AccountCreation> getAccountInfoListBasedOnTypes(AccountCreation accountCreation);
  
  String creatingInstantAccount(InstantAccountCreation instantAccountCreation);
  //Added by Sunny Soni for get instance or viewed type related account list for edit End
  
  //Added by Sunny Soni for getting mcc code and description from account mgmt Start
  List<MerchantCategoryCodeMaster> getMCCList(MerchantCategoryCodeMaster merchantCategoryCodeMaster);  
  //Added by Sunny Soni for getting mcc code and description from account mgmt End
  
  int saveMccInterest(MccWiseInterestModel mccWiseInterestModel);
  
  List<MccWiseInterestModel> getMccWiseInterestData(MccWiseInterestModel mccWiseInterestModel);
  
  public List<CardAccountLinkage> getCardAccountLinkage(String accountType,String accountNumber);
  
  public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType,String cardNumber);
  
  List<AccountTypeMaster> getAmountTransactionlist();
  
  //List<AccountCreation> getInterestOutstanding(String creditTypeCategory,String accountNumber);
  
  //Added by Sunny Soni for wallet related changes Start
  ParticipantWiseWalletMaster addParticipantWiseWalletMaster(ParticipantWiseWalletMaster participantWiseWalletMaster); 
  
  List<ParticipantWiseWalletMaster> getMCCListParticipantWise(ParticipantWiseWalletMaster participantWiseWalletMaster); 
  
  AccountTypeWiseBlockedMccMaster addAccountTypeWiseBlockedMccMaster(AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster);
  
  AccountTypeWiseWalletMaster addAccountTypeWiseWalletMaster(AccountTypeWiseWalletMaster accountTypeWiseWalletMaster);
  //Added by Sunny Soni for wallet related changes End
  
  String getLastAccounNumberBasedOnAccountType(String accountType);
  
  List<AccountTypeMaster> getAccountTypeMasterDataList() throws Exception;
  
  List<AccountTypeMaster> getAccountTypeMasterDataListByParticipantWise(String participantId) throws Exception;
  
  AccountTypeMaster getSingleAccountTypeObject(AccountTypeMaster accountTypeMaster) throws Exception;
  
  List<AccountCreditLimitCategory> getAccountCreditLimitCategoryListByParticipantWise(String participantId) throws Exception;
  
  List<AccountTransactionLimitation> getAccountTransactionLimitationList(AccountTypeMaster accountTypeMaster) throws Exception;
  
  List<AddressProofDocumentTypeMaster> getAddressProofDocumentTypeMasters(String participantId) throws Exception;
  
  List<IdentityProofDocumentTypeMaster> getIdentityProofDocumentTypeMasters(String participantId) throws Exception;
  
  List<AccountCreation> getAccountInfoListByAccountNo(AccountCreation accountCreation) throws Exception;
  
  List<MerchantCategoryCodeMaster> getUnSelectedAllMCC(MerchantCategoryCodeMaster merchantCategoryCodeMaster) throws Exception;
  
  List<MerchantCategoryCodeMaster> getSelectedMCCList(MerchantCategoryCodeMaster merchantCategoryCodeMaster) throws Exception;

  List<AccountTypeWiseWalletMaster> getAccountTypeWiseWalletMaster(AccountTypeWiseWalletMaster accountTypeWiseWalletMaster) throws Exception;
  
  List<AccountTypeWiseBlockedMccMaster> getAccountTypeWiseUnBlockedMccMaster(AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster) throws Exception;
  
  public List<AccountCreditLimitCategory> getAccountCreditTypeCategorylist();
  
  // Get Block MCC List Changes by Prashant Tayde

  List<AccountTypeWiseBlockedMccMaster> getBlockMccListAccountTypeWise(AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster);

  
  //List<InterestRateMaster> getOutStandingList(String acccontType, String accountNumber);
  
  CardAccountLinkage getCardAccountLinkages(CardAccountLinkage cardAccountLinkage);
  
  AccountTransactionLimitModel getAccountTransactionLimitModel(AccountTransactionLimitModel accountTransactionLimitModel);
  
  AccountCreation getAccountInformation(AccountCreation accountCreation);
  
  String getCountryName(String id);
  
  String getStateName(String id);
  
  String getCityName(String id);
  
  AccountCreditLimitCategory getAccountCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory);
  
  List<CardAccountLinkage> getCardLinkAccountList(CardAccountLinkage cardAccountLinkage);
 
  //added by sagark linked wallet list Date:22-12-2022
  List<AccountLinkedWalletMaster> getLinkedAccountList(AccountLinkedWalletMaster accountLinkedWalletMaster);
  
  //added by sagark linked wallet list Date:22-12-2022
  List<AccountLinkedWalletMaster> getlinkedAccountWallet(String walletAccountNumber);

  List<AccountCreditLimitCategory> getCreditTypelist();

  List<AccountCreation> getInterestOutstanding(String creditType);

  List<AccountInterestMaster> getOutStandingList(String accountNumber);

  String getDownloadedPDFPath(List<MerchantCategoryCodeMaster> merchantCategoryCodeMasters);
  
  String getDownloadedAccountStatementFilePath(List<AccountStatement> accountStatements);
  
  String getDownloadedAccountStatementFilePathForExcel(List<AccountStatement> accountStatements);
	
	
	 String getDownloadedGLPDFPath(List<GlAccountTypeWiseStatementMaster> glAccountStatements);
	 
	 String getDownloadedGLAccountStatmntFilePath(List<GlAccountTypeWiseStatementMaster> glAccountStatements);
	  
	 String getDownloadedGLAccountStatmntFilePathForExcel(List<GlAccountTypeWiseStatementMaster> glAccountStatements) ;
	
	
	
	
	
	
	

	// added on 23-12-22 by ankit
	List<AccountTypeMaster> getAccountTypeFromAccountTypeMaster();
	// added on 23-12-22

	// added on 23-12-22 by ankit
	//String addLoadBalances(LoadBalanceModel loadBalanceModel);

	// added on 23-12-22 by ankit
	int addBalanceInAccountMaster(BalanceUpdateInAccountMaster balance);

	// added on 27-12-22 to fetch channels by ankit
	List<Channels> getChannels();
	// added on 27-12-22 to fetch channels by ankit

	String getAvailableBalance(String accountType, String accountNumber);

	//not needed so commented
	//String addTransaction(LoadBalanceModel loadBalanceModel);

	//added by ankit commented not needed
	//String addAccountStatement(LoadBalanceModel loadBalanceModel);

	
	
	
	
	List<AccountTypeCharges> getSelectedChargesAccountTypeWise(AccountTypeCharges accountTypeCharges) throws Exception;
	
	// for getting card charges list --code added by prashant Tayde
	List<ChargeMaster> getChargeMasterList(ChargeMaster chargeMaster) throws Exception;

	// for getting transaction charges list --code added by prashant Tayde
	List<ChargeMaster> getTransactionChargeList(ChargeMaster chargeMaster) throws Exception;

	// for getting fuel charges List -----code added by prashant Tayde
	List<ChargeMaster> getFuelChargeList(ChargeMaster chargeMaster) throws Exception;

	
	AccountTypeCharges addChargingConfigData(AccountTypeCharges accountTypeCharges);
	
	List<AccountTypeCharges> getChargeDescriptionBasedOnChargeType(String strChargeType);
	
	//added by ankit
	 List<AccountCreation> checkAccountExistance(LoadBalanceModel loadBalanceModel);

	  int getAllowedLoadCashCount(LoadBalanceModel loadBalanceModel);
	  
	  String addLoadBalanceNew(LoadBalanceModel loadBalanceModel,int newLoadCountInteger);
	  //added by ankit on 12-01-2023
	  
	  //not required
	  //added GL account loading Master model in Transeco.configuration.Model
	  //List<GLAccountLoadingMaster> getGLAccountTypes();

	  //not required
	  //int addLoadBalanceInGlAccount(GLAccountLoadingMaster glAccountLoadingMaster);
	
	/**
	 * Account Statement Changes by Jyoti Shirahatti
	 */
	public List<AccountStatement> getAccountStatementByDate(String fromDate, String toDate);

	public List<AccountStatement> getAccountStatement(String accountType, String accountNumber, String fromDate, String toDate);

	List<GlAccountTypeChargesMaster> getGlAccountType();
	
	List<GlAccountTypeChargesMaster> getAccountTypecharges();
	
	List<AccountTypeMaster> getNonCreditAccounTypeObject(AccountTypeMaster accountTypeMster);

	String getAvailableBalanceBasedOnAccountTypeAndNumber(AccountCreation accountCreation);
	
	PreAccountMaster getKycDataForverification(PreAccountMaster preAccountMaster);
	
	//Added by Sunny SOni for getting card number Start
	List<Map<String, Object>> getCardNoList();
	
	String getSinglemaskedCardNumber(String cardNumber, String cardType);
	
	List<CardTypeModel> getCardTypeInfo(CardTypeModel cardTypeModel);
	//Added by Sunny SOni for getting card number End
	
	boolean isAccountAlreadyExist(AccountCreation accountCreation) throws Exception;
	
	//Added by Sagark for transaction Type Creation Start
	int addTranscationTypeData(TransactionTypeModel transactionTypeModel);
	//Added by Sagark for transaction Type Creation End
	
	boolean	checkTransactionTypeDataAlreadyConfigured(TransactionTypeModel transactionTypeModel) throws Exception;
	
	cardAcntLinkageMaster getInfofrmCardLinkage(String cardNumber);

	int insertRequestEntry(TranMaster tranMaster);
	
	int insertResponseEntry(TranMaster tranMaster);

	CardDetailsList getCardDetails(String cardNumber);
	
	boolean checkStanAlreadyExist(String stan);
	
	int addCardAccountLinkageCMS(CardAccountLinkage cardAccountLinkage);
	
	//added on 13-04-2023
	String getNetworkType(CardAccountLinkage cardAccountLinkage);
	
	String getCardHolderName(CardAccountLinkage cardAccountLinkage);
	//added on 13-04-2023
	
	boolean isCardAlreadyLinked(String tokenCard) throws Exception;

	int updateIssueCardToCustomer(CardDetails cardDetails);

	//added by prashant tayde--20Aug 2023
	List<ConnectionConfigModel> getEndPoints();
	
	//Added by Jyoti
	CardStatus getCardStatus(String tokenCard) throws Exception;

	int addAccountTypeDesc(AccountTypeMaster accountTypeMaster);

	int addEndPointEntry(ConnectionConfigModel connConfig);
	
	//void addAuditRecords(Object obj, String tableName);
	void updateAuditData(Object obj,Object objs, String tableName);
	void deletedAuditRecords(Object obj, String tableName);

	BinModel getBinOldDataforUpdate(BinModel binModel);
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

	List<CardTypeModel> getAccountTypeList();


	

	

	
}
