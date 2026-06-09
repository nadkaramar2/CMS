package com.traneco.accountmanagement.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.traneco.accountmanagement.model.AccountMasterResponse;
import com.traneco.accountmanagement.model.ApproveUpgradeTier;
import com.traneco.accountmanagement.model.ExternalServerRequestResponseModel;
import com.traneco.accountmanagement.model.FeeTypeMaster;
import com.traneco.accountmanagement.model.TxnReqRespLogMaster;
import com.traneco.accountmanagement.model.VatTypeMaster;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountInterestMaster;
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

public interface AccountManagementService 
{
	AccountCreation getAccountInformation(AccountCreation accountCreation) throws Exception;

	List<AccountTypeMaster> getNonCreditAccounTypeObject(AccountTypeMaster accountTypeMster) throws Exception;

	AccountTranMaster addAccountTranMaster(AccountTranMaster accountTranMaster) throws Exception;

	String addAccountTxnData(LoadBalanceModel loadBalanceModel) throws Exception;

	String addAccountStatement(LoadBalanceModel loadBalanceModel, AccountCreation accountCreation) throws Exception;

	int updateBalanceRelatedData(AccountCreation accountCreation) throws Exception;

	String saveAccountLoadMasterData(LoadBalanceModel loadBalanceModel) throws Exception;

	List<GLAccountTypeMaster> getListOfGLAccountTypelist(GLAccountTypeMaster glAccountTypeMaster) throws Exception;

	List<GLAccountCreation> getGlAccountTypeAccountNoListForThirdPartyY(GLAccountCreation glAccountCreation) throws Exception;
	
	String getClosingBalanceOfGlAccount(GLAccountTypeMaster glAccountTypeMaster) throws Exception;

	int updateGlaccountTypeDetails(GLAccountTypeMaster glAccountTypeMaster) throws Exception;

	String loadGLAccountInformationData(GLAccountLoadingMaster glAccountLoadingMaster) throws Exception;

	String addAccountStatementForGLAccount(AccountTranMaster accountTranMaster, String closingBalance) throws Exception;

	AccountTranMaster addAccountTransactionMasterForGLAccount(GLAccountLoadingMaster glAccountLoadingMaster)
			throws Exception;

	List<AccountTypeMaster> getAccountTypeMasterList() throws Exception;

	int addAccountTypeMaster(AccountTypeMaster accountTypeMaster) throws Exception;

	List<CategoryListModel> getAccountTypeCategoryList(CategoryListModel categoryListModel) throws Exception;

	List<TaxConfigModel> getTaxTypeConfigList(TaxConfigModel taxConfigModel) throws Exception;

	boolean isAccountTypeAlreadyExist(AccountTypeMaster accountTypeMaster) throws Exception;

	List<AccountTypeMaster> getAccountTypeMasterDataListByParticipantWise(String participantId) throws Exception;

	List<AccountCreditLimitCategory> getAccountCreditLimitCategoryListByParticipantWise(String participantId)
			throws Exception;

	int addAccountCreation(AccountCreation accountCreation) throws Exception;

	List<AccountCreation> getIssuedAccountBasedOnCardLinked(AccountCreation accountCreation) throws Exception;

	String creatingInstantAccount(InstantAccountCreation instantAccountCreation);

	AccountCreditLimitCategory saveAccountCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory)
			throws Exception;

	List<MerchantCategoryCodeMaster> getMCCList(MerchantCategoryCodeMaster merchantCategoryCodeMaster) throws Exception;

	MccWiseInterestModel saveMccInterest(MccWiseInterestModel mccWiseInterestModel) throws Exception;

	List<MccWiseInterestModel> getMccWiseInterest(MccWiseInterestModel mccWiseInterestModel) throws Exception;

	List<CardAccountLinkage> getCardAccountLinkageBasedOnAccount(CardAccountLinkage cardAccountLinkage)
			throws Exception;

	List<CardAccountLinkage> getCardAccountLinkageDataBasedOnCard(CardAccountLinkage cardAccountLinkage)
			throws Exception;

	List<AccountCreditCardTransactionModel> getAccountCreditCardTxn(
			AccountCreditCardTransactionModel accountCreditCardTransactionModel) throws Exception;

	AccountCreation getOutstandingAccount(AccountCreation accountCreation) throws Exception;

	List<AccountInterestMaster> getOutStandingInterestList(AccountInterestMaster accountInterestMaster)
			throws Exception;

	List<PreAccountMaster> getPreAccountDataList(PreAccountMaster preAccountMaster) throws Exception;

	AccountKycDetails getAccountKycDetailsInfo(AccountKycDetails accountKycDetails) throws Exception;

	List<AccountCreation> getAccountInfoListBasedOnTypes(AccountCreation accountCreation) throws Exception;

	PreAccountMaster getPreAccountMaster(PreAccountMaster preAccountMaster) throws Exception;

	int updateAccountCreationFromInstanceAccount(AccountCreation accountCreation) throws Exception;

	String sendMail(EmailTemplate emailTemplate) throws Exception;

	int updatePreAccountMasterAccount(PreAccountMaster preAccountMaster) throws Exception;

	CardAccountLinkage addCardAccountLinkageData(CardAccountLinkage cardAccountLinkage) throws Exception;

	boolean isCardAccountLinkageDataExist(CardAccountLinkage cardAccountLinkage) throws Exception;

	public Boolean validateMccWiseInterst(MccWiseInterestModel mccWiseInterestModel) throws Exception; // Added by
																										// Pankaj P for
																										// Validate the
																										// values

	List<IdentityProofDocumentTypeMaster> getIdentityProofDocumentTypeMasters(
			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster) throws Exception;

	List<AddressProofDocumentTypeMaster> getAddressProofDocumentTypeMasterList(
			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster) throws Exception;

	List<Channels> getChannelsList(Channels channels) throws Exception;

	AccountTransactionLimitation getAccountTxnLimitBasedOnParam(
			AccountTransactionLimitation accountTransactionLimitation) throws Exception;

	boolean isCreditTypeAlreadyExist(AccountCreditLimitCategory accountCreditLimitCategory) throws Exception;

	GLAccountCreation addGLAccountType(GLAccountCreation glAccountCreation) throws Exception;

	boolean isGLAccountTypeAlreadyExist(GLAccountCreation glAccountCreation) throws Exception;

	boolean isGLAccountAccountNumberAlreadyExist(GLAccountCreation glAccountCreation) throws Exception;

	int updateAccountAfterLinkedCard(AccountCreation accountCreation) throws Exception;

	CardAccountLinkage getCardAccountLinkages(CardAccountLinkage cardAccountLinkage) throws Exception;

	List<CardAccountLinkage> getCardLinkAccountList(CardAccountLinkage cardAccountLinkage) throws Exception;

	List<WalletAccountMaster> getLinkedAccountWalletList(WalletAccountMaster walletAccountMaster) throws Exception;

	List<AccountStatement> getAccountStatements(AccountStatement accountStatement) throws Exception;

	List<AccountStatement> getAccountStatementByDate(AccountStatement accountStatement) throws Exception;

	boolean isAccountAlreadyExist(AccountCreation accountCreation) throws Exception;

	CustomerIdMap getCustomerId() throws Exception;

	int insertCustIdDetails(CustomerIdCreation customerIdCreation) throws Exception;

	int updateCustId(CustomerIdTable customerIdTable) throws Exception;

	CustomerIdCreation getCustMstrDetails(CustomerIdCreation customerIdCreation) throws Exception;

	List<CustomerIdCreation> getCustomerDetailsbyCustId(CustomerIdCreation customerIDCreation) throws Exception;

	int saveCustomerInformation(CustomerIdCreation customerIdCreation) throws Exception;

	boolean isCustomerAccountAlreadyConfigured(AccountCreation accountCreation) throws Exception;

	List<CardAccountLinkage> getLinkageCardDetailsBasedOnCustId(CardAccountLinkage cardAccountLinkage) throws Exception;

	List<PreAccountMaster> getNonLinkedCustomersListForCustomerId(PreAccountMaster preAccountMaster) throws Exception;

	List<PreAccountMaster> getNonLinkedCustomersListForAccountNo(PreAccountMaster preAccountMaster) throws Exception;

	int updatePreAccountAccountBasedOnParam(PreAccountMaster preAccountMaster) throws Exception;

	List<ChargeRelatedMaster> getChargeRelatedList(ChargeRelatedMaster chargeRelatedMaster) throws Exception;

	int addChargeType(ChargeMaster chargeMaster) throws Exception;

	String getChargeRelatedDescription(ChargeRelatedMaster chargeRelatedMaster);

	Boolean validateChargeType(ChargeMaster chargeMaster) throws Exception;

	List<AccountTypeMaster> getAccountTypeForIsCredit(AccountTypeMaster accountTypeMaster) throws Exception;

	int updateIsRevolvingCredit(AccountTypeMaster accountTypeMaster);

	int addRevolvingCreditCardData(RevolvingCreditCardMaster revolvingCreditCardMaster) throws Exception;

	CustomerIdCreation getCustDetailsByMobileNumber(String mobileNumber) throws Exception;

	int updateIsAccountNoCreatedField(PreSubAccountMaster preSubAccountMaster) throws Exception;

	// Addded by Abhishek T Start
	List<MccWiseInterestModel> getmccwisedata(MccWiseInterestModel mccWiseInterestModel) throws Exception;

	List<GLAccountTypeMaster> getGLAccountcreationdata(GLAccountTypeMaster glAccountviewModel) throws Exception;

	List<AccountTypeMaster> getYCreditAccounTypeObject(AccountTypeMaster accountTypeMster) throws Exception;
	// Addded by Abhishek T End

	Boolean validateChargeRelated(ChargeRelatedMaster chargeRelatedMaster) throws Exception;

	int addChargeRelatedData(ChargeRelatedMaster chargeRelatedMaster) throws Exception;

	int updateIsRevolvingCreditFromMcc(AccountTypeMaster accountTypeMaster) throws Exception;

	// Added by Abhishek T for AccountTypeCategory on 17-03-2023 =start
	List<Categorytype> getcategoryList(Categorytype categorytype);

	boolean isTypeAlreadyExist(CategoryListModel categoryListModel);

	CategoryListModel saveCategoryListModel(CategoryListModel categoryListModel) throws Exception;
	// Added by Abhishek T for AccountTypeCategory on 17-03-2023 =End

	// added for txn Type Creation By sagark --START
	TransactionTypeModel addTranscationTypeData(TransactionTypeModel transactionTypeModel) throws Exception;
	// added for txn Type Creation By sagark --END

	List<CustomerIdCreation> getCustomerAccountDetails(CustomerIdCreation customerIdCreation);

	List<CustomerIdCreation> getCustomerAccountDetailsBasedOnCustId(CustomerIdCreation customerIdCreation);

	int updateCustomerAccountDetails(CustomerIdCreation customerIdCreation) throws Exception;

	/* Changes done for QrCode Genaration and update in account_master - Start */
	void updateQrCodeEntry(Map<String, String> qrCodeMap);
	/* Changes done for QrCode Genaration and update in account_master - End */

	/* Added transaction data related changes Start */
	Map<String, String> getInfofrmCardLinkage(String cardNumber);

	//TxnReqRes sendTransactionData(TxnReqRes tranMaster);
	
	ProcessResponse sendTransactionData(TxnReqRes tranMaster);
	
	int updateBalnceLimitValues(AccountCreation accountCreation);

	int updateAccountStatment(AccountStatement accountStatement);

	int insertEntry(TranMaster tranMaster);

	String getisRevolingCredit(String accountType);

	String getGracePeriod(String accntType);

	String getGracePeriodfrmMCCwise(String accntType);

	int insertEntryCrdTxnMaster(RevolvingCreditCardTxnMaster revolvingCreditCardTxnMaster);

	int insertEntryCrdInt(RevolvingCreditInterestTxn revolvingCreditInterestTxn);
	/* Added transaction data related changes End */

	// added by prashant tayde for limit save
	int insertLimitinAccountMaster(AccountCreation accountCreation);

	String getCreditLimit(AccountCreditLimitCategory accCreditLimitCatogory);

	boolean validateRevolvingData(RevolvingCreditCardMaster revolvingCreditCardMaster);

	List<GLAccountCreation> getGlAccountTypeAccountNoList(GLAccountCreation glAccountCreation);

	String addAccountStatementForGLAccountStatement(AccountTranMaster accountTranMaster, String closingBalance)
			throws Exception;

	TxnReqRes sendTransactionDataViaSwitch(TxnReqRes tranMaster);

	String addGLAccountEntry(GLAccountTypeMaster glAccountTypeMaster) throws Exception;

	TxnReqRes loadAccountBalance(TxnReqRes txnReqRes) throws Exception;

	// Added by pankaj pawar for charts of accounts menu.
	public String addGLAccountStatementbyLoadingBalance(AccountCreation accountCreation, String closingBalance)
			throws Exception;

	List<GLAccountTypeMaster> getGLAccountDetailsList(GLAccountTypeMaster gLAccountTypeMaster);

	// Added by ankit on 05-04-2023
	List<GLAccountTypeMaster> getGLAccountTypeList();
	// Added by ankit on 05-04-2023

	String journalTransfer(JournalTransfer journalTransfer);
	// Added by ankit on 05-04-2023

	// added by ankit on 06-04-2023
	List<GLAccountTypeMaster> getGLAvailableBalnce(GLAccountTypeMaster glAccountTypeMaster);
	// added by ankit on 06-04-2023

	// added by ankit --start
	String journalTransferGLToAcc(JournalTransfer journalTransfer);

	List<AccountCreation> getAvailableBalnceOfAccount(AccountCreation accountCreation);

	List<AccountTypeMaster> getStrAccDescription(AccountTypeMaster accountTypeMaster);

	List<GLAccountTypeMaster> getStrGLDescription(GLAccountTypeMaster glAcountTypeMaster);
	// added by ankit --end

	// Added by Sagar k for authorise list [START]
	List<JournalTransfer> getAccountAuthoriseDetailsList(JournalTransfer journalTransfer);
	// Added by Sagar k for authorise list [END]

	List<JournalTransfer> getTxnIdApprovallist(JournalTransfer journalTransfer);

	int UpdateReasonDetails(JournalTransfer journalTransfer);

	JournalTransfer processToApproveJournalTransfer(JournalTransfer journalTransfer);

	JournalTransfer processToRejectJournalTransfer(JournalTransfer journalTransfer);

	int insertPrecaccountMaster(PreAccountMaster preAccountMaster);

	List<CountryCodeMaster> getCountryCode(CountryCodeMaster countryCodeMaster);

	GLAccountCreation isGLAccountTypeAlreadyExistDetails(GLAccountCreation glAccountCreation) throws Exception;

	int updateGLClosingBalance(GLAccountCreation glAccountCreation) throws Exception;

	GLAccountCreation getControlAccountTypeObj(GLAccountCreation glAccountCreation) throws Exception;

	GLAccountCreation getAllGLAccountBalanceTogether(GLAccountCreation glAccountCreation) throws Exception;

	List<GLAccountTypeMaster> getGLAccountTypeListExceptCTR();

	// Added By Sagar khawse Start
	int updateIsMobileCustIdlink(PreAccountMaster preAccountMaster);

	List<AccountCreation> getRegCustWithLinkAccount(AccountCreation accountCreation);

	List<PreSubAccountMaster> getPendingRegCustWithLinkAccount(PreSubAccountMaster preSubAccountMaster);

	List<DenominationMaster> getdenominationMasterDetails(DenominationMaster denominationMaster);

	List<TransactionTypeModel> getTxnTypeList(TransactionTypeModel transactionTypeModel);

	List<JournalTransfer> getJournalStatementlist(JournalTransfer journalTransfer);

	List<AccountTranMaster> getTxnReportlist(AccountTranMaster accountTranMaster);

	List<PreAccountMaster> getRegisterCustomers(PreAccountMaster preAccountMaster);

	List<DenominationMaster> getdenominationMasterDetailsbyAgent(DenominationMaster denominationMaster);
	// Added By Sagar khawse End

	List<AccountTranMaster> searchTransactionByTxnId(AccountTranMaster accountTranMaster);

	// added by prashant Tayde
	ProcessResponse applyCardIssuanceCharges(CardAccountLinkage cardAccountLinkage);
	
	// Added by Prashant Tayde26 April 2024
	ProcessResponse applyCardAnnualCharges(AccountWiseCharges accountWiseCharges);
	

	String setApplicationName(Map<Object, Object> map);

	String getActiveCurrentTierBasedOnMobileNo(String strMobileNo);

	List<ApproveUpgradeTier> getUpgradeTierType(ApproveUpgradeTier approveUpgradeTier);

	List<CustomerIdCreation> getCustomerDetailsWithActiveTier(CustomerIdCreation customerIDCreation);

	String updateUpgradeRequest(UpgradeTierReqRes upgradeTierReqRes);

	// added by ankit 23-05-2023
	List<NubanTypeConfig> getNubanTypeAndDescription();

	// added by ankit on 25-05-2023 -start
	int addNubanCodeConfig(NubanCodeConfig nubanCodeConfig);

	NubanTypeConfig getNubanTypeDescription(NubanTypeConfig nubanTypeConfig);
	// added by ankit on 25-05-2023 -end

	List<GLAccountTypeMaster> getGLAccountTypeLists();

	List<TransactionTypeModel> getTransactionTypeModelData(TransactionTypeModel transactionTypeModel);

	boolean isGLAccountAlreadyExistAgainstAccountType(AccountTypeMaster accountTypeMaster);

	int updateAccountTypeCategory(CategoryListModel categoryListModel);

	List<AccountTypeMaster> getAccountTypeMasterDetailsBasedOnAccountType(AccountTypeMaster accountTypeMaster);

	int updateAccountTypeDetails(AccountTypeMaster accountTypeMaster);

	int updateAccountLimitCredit(AccountCreditLimitCategory accountCreditLimitCategory);

	// Added by prashant 01-06-2023
	List<CategoryListModel> getCategoryTypeListData(CategoryListModel categoryListModels);

	List<Categorytype> getCategoryTypeList(Categorytype categorytype);

	// created by ankit on 05-06-2023
	List<AccountCreation> getStrAccountHolderName(AccountCreation accountCreation);
	// created by ankit on 05-06-2023

	List<ProcessResponse> addOneToManyAccountsData(BulkTransfer bulkTransfer);

	List<ProcessResponse> addManyToOneAccountsData(BulkTransfer bulkTransfer);

	CloseAccountResponse saveCloseAccountRequest(CloseAccountMaster closeAccountMaster);

	// added by sunil Y for geeting list of account clouser
	List<CloseAccountMaster> getListOfAccountClouser();

	CloseAccountMaster getCLoseAccountMasterDataByCustId(CloseAccountMaster closeAccountMaster);

	ResponseEntity<?> getAccountLimitInfoToCompareTransferAmount(BulkTransfer bulkTransfer);

	ResponseEntity<?> getCumlativeBalanceCompareTransAmt(BulkTransfer bulkTransfer);

	CloseAccountMaster requestStatusApproveReject(CloseAccountMaster closeAccountMaster);

	CloseAccountMaster getCloseAccountMasterDetails(CloseAccountMaster closeAccountMaster);

	ProcessResponse getThirdPartyBankList(ExternalServerRequestResponseModel externalServerRequestResponseModel);

	ProcessResponse getThirdPartyAccountName(ExternalServerRequestResponseModel externalServerRequestResponseModel);

	ProcessResponse addGLToManyAccountsData(BulkTransfer bulkTransfer);

	ProcessResponse addManyToGLAccountsData(BulkTransfer bulkTransfer);

	List<AccountMasterResponse> getAccountInformationForClosingAccount(AccountCreation accountCreation);
	
	//added by prashant T 30Aug 2023
	List<VatTypeMaster> getVatTypeList(VatTypeMaster vatTypeMaster);

	int addVatTypeMappedData(VatTypeMaster vatTypeMaster);

	Boolean validateVatType(VatTypeMaster vatTypeMaster);

	boolean validateFeeType(FeeTypeMaster feeTypeMaster);

	int addFeeTypeMappedData(FeeTypeMaster feeTypeMaster);

	boolean validateVatTypeList(FeeTypeMaster feeTypeMaster);

	Boolean isTransactionTypeAlreadyExist(TransactionTypeModel transactionTypeModel);

	Boolean isGLAccountTypeExist(TransactionTypeModel transactionTypeModel);

	List<TxnReqRespLogMaster> getTxnReqRespLogMaster(TxnReqRespLogMaster txnReqRespLogMaster);

	ProcessResponse sendMultiCurrencyTransactionData(TxnReqRes txnReqRes);

	ProcessResponse sendCreditCardTransactionData(TxnReqRes txnReqResponse);

	public List<AccountCreation> getAccountTypeList();

	Client accountCreation(Client client);

}
