package com.traneco.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:apiconfig.properties" })
public class ApiPropertyConfig 
{
	@Autowired
	Environment environment;

	private String accountManagementAPIurl;
	private String serverAccountManagementAPIurl;
	private String accountManagementFileDownloadApiUrl;
	private String multiCurrencyAPIurl;
	private String creditCardurl;
	
	private String amsAccountType;
	private String amsAccountApi;
	private String amsInstantAccountApi;

	private String amsMccCodeApi;
	private String amsMccWiseInterestApi;

	private String amsAccountCreditCardTxnApi;
	private String amsAccountWiseCreditCardInterestApi;

	private String amsCardAccountLinkageApi;

	private String amsAccoutTxnMaster;
	private String amsAccountStatementApi;
	private String amsAccountLoadApi;
	private String amsGlAccountLoadApi;
	private String amsGlAccountTypeApi;
	private String taxTypeConfigApi;
	private String accountTypeCategoryApi;
	private String amsCreditLimitApi;
	private String amsGlAccountCreateApi;

	private String preAccountMasterApi;
	private String preSubAccountMasterApi;
	private String amsAccountWiseKycDetailsApi;

	private String amsExportPdfApi;
	private String amsExportExcelApi;
	private String downloadFilePath;

	private String amsNotifyByApi;

	private String customerIdApi;

	private String customerIdTableApi;

	private String amsAddressProofDocumentTypeApi;
	private String amsIdentityProofDocumentTypeApi;

	private String amsChannelsApi;
	private String amsAccountTxnLimitApi;
	private String amsGlAccountTypeCreationApi;

	private String amsWalletAccountApi;

	private String amsChargeRelatedApi;

	private String amsChargeTypeApi;

	private String amsChargeRelatedDesc;

	private String amsRevolvingCreditCard;

	// Abhishek Tamrakar Start
	private String amsCategoryType;
	// Abhishek Tamrakar End

	private String amsTransactionTypeCreationApi;

	// QR code generation - Start
	private String amsQrCodeController;
	// QR code generation - End

	// Transaction related changes Start
	private String tranMasterApi;

	private String amsrevolvingCreditCardMasterApi;

	private String amsrevolvingCreditCardTxnApi;

	private String amsrevolvingCreditCardIntApi;

	private String amsTransactionHandler;
	// Transaction related changes End

	private String amsGLAccountStatementApi;

	private String amsJournalTransferApi;

	private String amsCountryCodeApi;

	// Added changes on 05-May-2023 Start
	private String amsAccountWiseCharges;

	private String amsDenominationMasterApi;

	// Added for Transaction Report by sagark [START]
	private String amsAccountReportApi;
	// Added for Transaction Reportby sagark [END]
	// Added changes on 05-May-2023 End

	private String amsApplicationName;

	private String amsApproveTierType;

	private String amsAccountClosure;

	private String accountClouserMaster;

	private String amsBulkTransfer;

	private String amsThirdPartyPath;

	private String feeTypeMaster;

	private String vatTypeMaster;
	
	private String transactionReqResLog;
	
	private String AmsTransactionControl;

	@PostConstruct
	public void init() 
	{
		setAccountManagementAPIurl(environment.getProperty("ams.account_mgmt_url"));
		setServerAccountManagementAPIurl(environment.getProperty("ams.server.account_mgmt_url"));
		setAccountManagementFileDownloadApiUrl(environment.getProperty("ams.file_download_url"));
		
		//Added by prashant 25 Dec2023 For Multicurrency API
		setMultiCurrencyAPIurl(environment.getProperty("ams.multiCurrencyAPIurl"));
		//Added by prashant 25 Dec2023
		
		//Added by prashant T 22Jan2024 For credit card txn
		setCreditCardurl(environment.getProperty("ams.creditCardurl"));
		//Ended by prashant T 22Jan2024 For credit card txn
		
		setAmsAccountType(environment.getProperty("ams.account_type"));
		setAmsAccountApi(environment.getProperty("ams.account"));
		setAmsInstantAccountApi(environment.getProperty("ams.instant_account"));

		setAmsMccCodeApi(environment.getProperty("ams.mcc_code"));
		setAmsMccWiseInterestApi(environment.getProperty("ams.mcc_wise_interest"));
		setAmsCardAccountLinkageApi(environment.getProperty("ams.card_account_linkage"));
		setAmsAccountCreditCardTxnApi(environment.getProperty("ams.account_credit_card_txn"));
		setAmsAccountWiseCreditCardInterestApi(environment.getProperty("ams.account_wise_credit_interest"));

		setAmsAccoutTxnMaster(environment.getProperty("ams.account_txn_master"));
		setAmsAccountStatementApi(environment.getProperty("ams.account_statement"));
		setAmsAccountLoadApi(environment.getProperty("ams.account_load"));
		setAmsGlAccountLoadApi(environment.getProperty("ams.gl_account_load"));
		setAmsGlAccountTypeApi(environment.getProperty("ams.gl_account_type"));
		setTaxTypeConfigApi(environment.getProperty("ams.tax_type_config"));
		setAccountTypeCategoryApi(environment.getProperty("ams.account_type_category"));
		setAmsCreditLimitApi(environment.getProperty("ams.credit_limit"));
		setAmsGlAccountCreateApi(environment.getProperty("ams.gl_account_create"));

		setPreAccountMasterApi(environment.getProperty("ams.pre_account_master"));
		setPreSubAccountMasterApi(environment.getProperty("ams.pre_sub_account_master"));
		setAmsAccountWiseKycDetailsApi(environment.getProperty("ams.account_wise_kyc_details"));

		setAmsExportPdfApi(environment.getProperty("ams.export_pdf"));
		setAmsExportExcelApi(environment.getProperty("ams.export_excel"));
		setDownloadFilePath(environment.getProperty("DOWNLOAD_FILE_FOLDER_PATH"));

		setAmsNotifyByApi(environment.getProperty("ams.notify_by"));

		setAmsCustomerApi(environment.getProperty("ams.customer_id"));

		setAmsCustomerIdTbleApi(environment.getProperty("ams.customer_id_table"));

		setAmsAddressProofDocumentTypeApi(environment.getProperty("ams.address_proof_document_type"));
		setAmsIdentityProofDocumentTypeApi(environment.getProperty("ams.identity_proof_document_type"));

		setAmsChannelsApi(environment.getProperty("ams.channels"));
		setAmsAccountTxnLimitApi(environment.getProperty("ams.account_txn_limit"));
		setAmsGlAccountTypeCreationApi(environment.getProperty("ams.gl_account_type_creation"));

		setAmsWalletAccountApi(environment.getProperty("ams.wallet_account"));

		setAmsChargeRelatedApi(environment.getProperty("ams.chargeRelated"));

		setAmsChargeTypeApi(environment.getProperty("ams.charges_master_list"));

		setAmsRevolvingCreditCard(environment.getProperty("ams.revolving_credit_card"));

		setAmsCategoryType(environment.getProperty("ams.category_type"));

		setAmsTransactionTypeCreationApi(environment.getProperty("ams.transaction_type_master"));

		setTranMasterApi(environment.getProperty("ams.tran_master"));

		setAmsrevolvingCreditCardMasterApi(environment.getProperty("ams.revolving_credit_card_master"));

		setAmsrevolvingCreditCardTxnApi(environment.getProperty("ams.revolving_card_txn"));

		setAmsrevolvingCreditCardIntApi(environment.getProperty("ams.revolving_card_intrst"));

		setAmsTransactionHandler(environment.getProperty("ams.transaction_hanlder"));

		setAmsQrCodeController(environment.getProperty("ams.qr_code"));

		setAmsGLAccountStatementApi(environment.getProperty("ams.glaccount_statement"));

		setAmsJournalTransferApi(environment.getProperty("ams.journal_transfer"));

		setAmsCountryCodeApi(environment.getProperty("ams.country_code_api"));

		// Added changes on 05-May-2023 Start
		setAmsAccountWiseCharges(environment.getProperty("ams.accountWiseCharge"));

		setAmsDenominationMasterApi(environment.getProperty("ams.denomination_master"));

		setAmsAccountReportApi(environment.getProperty("ams.transaction_report"));
		// Added changes on 05-May-2023 End

		setAmsApplicationName(environment.getProperty("ams.application_name"));

		setAmsApproveTierType(environment.getProperty("ams.approve_upgrade_tier"));

		// setAmsAccountClosure(environment.getProperty("ams.account_closure"));
		setAmsThirdPartyPath(environment.getProperty("ams.third_party_path"));

		setAmsBulkTransfer(environment.getProperty("ams.bulk_transfer"));

		setAccountClouserMaster(environment.getProperty("ams.accountclouser"));

		setFeeTypeMaster(environment.getProperty("ams.fee_type_master"));

		setVatTypeMaster(environment.getProperty("ams.vat_type_master"));
		
		setTransactionReqResLog(environment.getProperty("ams.txn_req_res_log"));
		
		setAmsTransactionControl(environment.getProperty("ams.txn_control"));

	}

	public String getAmsCustomerIdTbleApi() {
		return customerIdTableApi;
	}

	public void setAmsCustomerIdTbleApi(String customerIdTableApi) {
		this.customerIdTableApi = customerIdTableApi;
	}

	public String getAmsCustomerApi() {
		return customerIdApi;
	}

	public void setAmsCustomerApi(String customerIdApi) {
		this.customerIdApi = customerIdApi;
	}

	public String getAccountManagementAPIurl() {
		return accountManagementAPIurl;
	}

	public void setAccountManagementAPIurl(String accountManagementAPIurl) {
		this.accountManagementAPIurl = accountManagementAPIurl;
	}

	public String getAmsAccountType() {
		return amsAccountType;
	}

	public void setAmsAccountType(String amsAccountType) {
		this.amsAccountType = amsAccountType;
	}

	public String getAmsAccoutTxnMaster() {
		return amsAccoutTxnMaster;
	}

	public void setAmsAccoutTxnMaster(String amsAccoutTxnMaster) {
		this.amsAccoutTxnMaster = amsAccoutTxnMaster;
	}

	public String getAmsAccountStatementApi() {
		return amsAccountStatementApi;
	}

	public void setAmsAccountStatementApi(String amsAccountStatementApi) {
		this.amsAccountStatementApi = amsAccountStatementApi;
	}

	public String getAmsAccountApi() {
		return amsAccountApi;
	}

	public void setAmsAccountApi(String amsAccountApi) {
		this.amsAccountApi = amsAccountApi;
	}

	public String getAmsInstantAccountApi() {
		return amsInstantAccountApi;
	}

	public void setAmsInstantAccountApi(String amsInstantAccountApi) {
		this.amsInstantAccountApi = amsInstantAccountApi;
	}

	public String getAmsMccCodeApi() {
		return amsMccCodeApi;
	}

	public void setAmsMccCodeApi(String amsMccCodeApi) {
		this.amsMccCodeApi = amsMccCodeApi;
	}

	public String getAmsMccWiseInterestApi() {
		return amsMccWiseInterestApi;
	}

	public void setAmsMccWiseInterestApi(String amsMccWiseInterestApi) {
		this.amsMccWiseInterestApi = amsMccWiseInterestApi;
	}

	public String getAmsCardAccountLinkageApi() {
		return amsCardAccountLinkageApi;
	}

	public void setAmsCardAccountLinkageApi(String amsCardAccountLinkageApi) {
		this.amsCardAccountLinkageApi = amsCardAccountLinkageApi;
	}

	public String getAmsAccountLoadApi() {
		return amsAccountLoadApi;
	}

	public void setAmsAccountLoadApi(String amsAccountLoadApi) {
		this.amsAccountLoadApi = amsAccountLoadApi;
	}

	public String getAmsGlAccountLoadApi() {
		return amsGlAccountLoadApi;
	}

	public void setAmsGlAccountLoadApi(String amsGlAccountLoadApi) {
		this.amsGlAccountLoadApi = amsGlAccountLoadApi;
	}

	public String getAmsGlAccountTypeApi() {
		return amsGlAccountTypeApi;
	}

	public void setAmsGlAccountTypeApi(String amsGlAccountTypeApi) {
		this.amsGlAccountTypeApi = amsGlAccountTypeApi;
	}

	public String getAccountManagementFileDownloadApiUrl() {
		return accountManagementFileDownloadApiUrl;
	}

	public void setAccountManagementFileDownloadApiUrl(String accountManagementFileDownloadApiUrl) {
		this.accountManagementFileDownloadApiUrl = accountManagementFileDownloadApiUrl;
	}

	public String getAmsExportPdfApi() {
		return amsExportPdfApi;
	}

	public void setAmsExportPdfApi(String amsExportPdfApi) {
		this.amsExportPdfApi = amsExportPdfApi;
	}

	public String getDownloadFilePath() {
		return downloadFilePath;
	}

	public void setDownloadFilePath(String downloadFilePath) {
		this.downloadFilePath = downloadFilePath;
	}

	public String getAmsExportExcelApi() {
		return amsExportExcelApi;
	}

	public void setAmsExportExcelApi(String amsExportExcelApi) {
		this.amsExportExcelApi = amsExportExcelApi;
	}

	public String getTaxTypeConfigApi() {
		return taxTypeConfigApi;
	}

	public void setTaxTypeConfigApi(String taxTypeConfigApi) {
		this.taxTypeConfigApi = taxTypeConfigApi;
	}

	public String getAccountTypeCategoryApi() {
		return accountTypeCategoryApi;
	}

	public void setAccountTypeCategoryApi(String accountTypeCategoryApi) {
		this.accountTypeCategoryApi = accountTypeCategoryApi;
	}

	public String getAmsCreditLimitApi() {
		return amsCreditLimitApi;
	}

	public void setAmsCreditLimitApi(String amsCreditLimitApi) {
		this.amsCreditLimitApi = amsCreditLimitApi;
	}

	public String getAmsGlAccountCreateApi() {
		return amsGlAccountCreateApi;
	}

	public void setAmsGlAccountCreateApi(String amsGlAccountCreateApi) {
		this.amsGlAccountCreateApi = amsGlAccountCreateApi;
	}

	public String getAmsAccountCreditCardTxnApi() {
		return amsAccountCreditCardTxnApi;
	}

	public void setAmsAccountCreditCardTxnApi(String amsAccountCreditCardTxnApi) {
		this.amsAccountCreditCardTxnApi = amsAccountCreditCardTxnApi;
	}

	public String getAmsAccountWiseCreditCardInterestApi() {
		return amsAccountWiseCreditCardInterestApi;
	}

	public void setAmsAccountWiseCreditCardInterestApi(String amsAccountWiseCreditCardInterestApi) {
		this.amsAccountWiseCreditCardInterestApi = amsAccountWiseCreditCardInterestApi;
	}

	public String getPreAccountMasterApi() {
		return preAccountMasterApi;
	}

	public void setPreAccountMasterApi(String preAccountMasterApi) {
		this.preAccountMasterApi = preAccountMasterApi;
	}

	public String getAmsAccountWiseKycDetailsApi() {
		return amsAccountWiseKycDetailsApi;
	}

	public void setAmsAccountWiseKycDetailsApi(String amsAccountWiseKycDetailsApi) {
		this.amsAccountWiseKycDetailsApi = amsAccountWiseKycDetailsApi;
	}

	public String getAmsNotifyByApi() {
		return amsNotifyByApi;
	}

	public void setAmsNotifyByApi(String amsNotifyByApi) {
		this.amsNotifyByApi = amsNotifyByApi;
	}

	public String getAmsAddressProofDocumentTypeApi() {
		return amsAddressProofDocumentTypeApi;
	}

	public void setAmsAddressProofDocumentTypeApi(String amsAddressProofDocumentTypeApi) {
		this.amsAddressProofDocumentTypeApi = amsAddressProofDocumentTypeApi;
	}

	public String getAmsIdentityProofDocumentTypeApi() {
		return amsIdentityProofDocumentTypeApi;
	}

	public void setAmsIdentityProofDocumentTypeApi(String amsIdentityProofDocumentTypeApi) {
		this.amsIdentityProofDocumentTypeApi = amsIdentityProofDocumentTypeApi;
	}

	public String getAmsChannelsApi() {
		return amsChannelsApi;
	}

	public void setAmsChannelsApi(String amsChannelsApi) {
		this.amsChannelsApi = amsChannelsApi;
	}

	public String getAmsAccountTxnLimitApi() {
		return amsAccountTxnLimitApi;
	}

	public void setAmsAccountTxnLimitApi(String amsAccountTxnLimitApi) {
		this.amsAccountTxnLimitApi = amsAccountTxnLimitApi;
	}

	public String getAmsGlAccountTypeCreationApi() {
		return amsGlAccountTypeCreationApi;
	}

	public void setAmsGlAccountTypeCreationApi(String amsGlAccountTypeCreationApi) {
		this.amsGlAccountTypeCreationApi = amsGlAccountTypeCreationApi;
	}

	public String getAmsWalletAccountApi() {
		return amsWalletAccountApi;
	}

	public void setAmsWalletAccountApi(String amsWalletAccountApi) {
		this.amsWalletAccountApi = amsWalletAccountApi;
	}

	public String getAmsChargeRelatedApi() {
		return amsChargeRelatedApi;
	}

	public void setAmsChargeRelatedApi(String amsChargeRelatedApi) {
		this.amsChargeRelatedApi = amsChargeRelatedApi;
	}

	public String getAmsChargeTypeApi() {
		return amsChargeTypeApi;
	}

	public void setAmsChargeTypeApi(String amsChargeTypeApi) {
		this.amsChargeTypeApi = amsChargeTypeApi;
	}

	public String getAmsChargeRelatedDesc() {
		return amsChargeRelatedDesc;
	}

	public void setAmsChargeRelatedDesc(String amsChargeRelatedDesc) {
		this.amsChargeRelatedDesc = amsChargeRelatedDesc;
	}

	public String getAmsRevolvingCreditCard() {
		return amsRevolvingCreditCard;
	}

	public void setAmsRevolvingCreditCard(String amsRevolvingCreditCard) {
		this.amsRevolvingCreditCard = amsRevolvingCreditCard;
	}

	public String getPreSubAccountMasterApi() {
		return preSubAccountMasterApi;
	}

	public void setPreSubAccountMasterApi(String preSubAccountMasterApi) {
		this.preSubAccountMasterApi = preSubAccountMasterApi;
	}

	public String getCustomerIdApi() {
		return customerIdApi;
	}

	public void setCustomerIdApi(String customerIdApi) {
		this.customerIdApi = customerIdApi;
	}

	public String getCustomerIdTableApi() {
		return customerIdTableApi;
	}

	public void setCustomerIdTableApi(String customerIdTableApi) {
		this.customerIdTableApi = customerIdTableApi;
	}

	// Added by Abhishek Tamrakar Start
	public String getAmsCategoryType() {
		return amsCategoryType;
	}

	public void setAmsCategoryType(String amsCategoryType) {
		this.amsCategoryType = amsCategoryType;
	}
	// Added by Abhishek Tamrakar End

	public String getAmsTransactionTypeCreationApi() {
		return amsTransactionTypeCreationApi;
	}

	public void setAmsTransactionTypeCreationApi(String amsTransactionTypeCreationApi) {
		this.amsTransactionTypeCreationApi = amsTransactionTypeCreationApi;
	}

	public String getAmsQrCodeController() {
		return amsQrCodeController;
	}

	public void setAmsQrCodeController(String amsQrCodeController) {
		this.amsQrCodeController = amsQrCodeController;
	}

	public String getTranMasterApi() {
		return tranMasterApi;
	}

	public void setTranMasterApi(String tranMasterApi) {
		this.tranMasterApi = tranMasterApi;
	}

	public String getAmsrevolvingCreditCardMasterApi() {
		return amsrevolvingCreditCardMasterApi;
	}

	public void setAmsrevolvingCreditCardMasterApi(String amsrevolvingCreditCardMasterApi) {
		this.amsrevolvingCreditCardMasterApi = amsrevolvingCreditCardMasterApi;
	}

	public String getAmsrevolvingCreditCardTxnApi() {
		return amsrevolvingCreditCardTxnApi;
	}

	public void setAmsrevolvingCreditCardTxnApi(String amsrevolvingCreditCardTxnApi) {
		this.amsrevolvingCreditCardTxnApi = amsrevolvingCreditCardTxnApi;
	}

	public String getAmsrevolvingCreditCardIntApi() {
		return amsrevolvingCreditCardIntApi;
	}

	public void setAmsrevolvingCreditCardIntApi(String amsrevolvingCreditCardIntApi) {
		this.amsrevolvingCreditCardIntApi = amsrevolvingCreditCardIntApi;
	}

	public String getAmsTransactionHandler() {
		return amsTransactionHandler;
	}

	public void setAmsTransactionHandler(String amsTransactionHandler) {
		this.amsTransactionHandler = amsTransactionHandler;
	}

	public String getAmsGLAccountStatementApi() {
		return amsGLAccountStatementApi;
	}

	public void setAmsGLAccountStatementApi(String amsGLAccountStatementApi) {
		this.amsGLAccountStatementApi = amsGLAccountStatementApi;
	}

	public String getServerAccountManagementAPIurl() {
		return serverAccountManagementAPIurl;
	}

	public void setServerAccountManagementAPIurl(String serverAccountManagementAPIurl) {
		this.serverAccountManagementAPIurl = serverAccountManagementAPIurl;
	}

	public String getAmsJournalTransferApi() {
		return amsJournalTransferApi;
	}

	public void setAmsJournalTransferApi(String amsJournalTransferApi) {
		this.amsJournalTransferApi = amsJournalTransferApi;
	}

	public String getAmsCountryCodeApi() {
		return amsCountryCodeApi;
	}

	public void setAmsCountryCodeApi(String amsCountryCodeApi) {
		this.amsCountryCodeApi = amsCountryCodeApi;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String getAmsAccountWiseCharges() {
		return amsAccountWiseCharges;
	}

	public void setAmsAccountWiseCharges(String amsAccountWiseCharges) {
		this.amsAccountWiseCharges = amsAccountWiseCharges;
	}

	public String getAmsDenominationMasterApi() {
		return amsDenominationMasterApi;
	}

	public void setAmsDenominationMasterApi(String amsDenominationMasterApi) {
		this.amsDenominationMasterApi = amsDenominationMasterApi;
	}

	public String getAmsAccountReportApi() {
		return amsAccountReportApi;
	}

	public void setAmsAccountReportApi(String amsAccountReportApi) {
		this.amsAccountReportApi = amsAccountReportApi;
	}

	public String getAmsApplicationName() {
		return amsApplicationName;
	}

	public void setAmsApplicationName(String amsApplicationName) {
		this.amsApplicationName = amsApplicationName;
	}

	public String getAmsApproveTierType() {
		return amsApproveTierType;
	}

	public void setAmsApproveTierType(String amsApproveTierType) {
		this.amsApproveTierType = amsApproveTierType;
	}

	public String getAmsBulkTransfer() {
		return amsBulkTransfer;
	}

	public void setAmsBulkTransfer(String amsBulkTransfer) {
		this.amsBulkTransfer = amsBulkTransfer;
	}

	public String getAccountClouserMaster() {
		return accountClouserMaster;
	}

	public void setAccountClouserMaster(String accountClouserMaster) {
		this.accountClouserMaster = accountClouserMaster;
	}

	public String getAmsAccountClosure() {
		return amsAccountClosure;
	}

	public void setAmsAccountClosure(String amsAccountClosure) {
		this.amsAccountClosure = amsAccountClosure;
	}

	public String getAmsThirdPartyPath() {
		return amsThirdPartyPath;
	}

	public void setAmsThirdPartyPath(String amsThirdPartyPath) {
		this.amsThirdPartyPath = amsThirdPartyPath;
	}

	public String getFeeTypeMaster() {
		return feeTypeMaster;
	}

	public void setFeeTypeMaster(String feeTypeMaster) {
		this.feeTypeMaster = feeTypeMaster;
	}

	public String getVatTypeMaster() {
		return vatTypeMaster;
	}

	public void setVatTypeMaster(String vatTypeMaster) {
		this.vatTypeMaster = vatTypeMaster;
	}

	public String getTransactionReqResLog() {
		return transactionReqResLog;
	}

	public void setTransactionReqResLog(String transactionReqResLog) {
		this.transactionReqResLog = transactionReqResLog;
	}

	public String getAmsTransactionControl() {
		return AmsTransactionControl;
	}

	public void setAmsTransactionControl(String amsTransactionControl) {
		AmsTransactionControl = amsTransactionControl;
	}

	public String getMultiCurrencyAPIurl() {
		return multiCurrencyAPIurl;
	}

	public void setMultiCurrencyAPIurl(String multiCurrencyAPIurl) {
		this.multiCurrencyAPIurl = multiCurrencyAPIurl;
	}

	public String getCreditCardurl() {
		return creditCardurl;
	}

	public void setCreditCardurl(String creditCardurl) {
		this.creditCardurl = creditCardurl;
	}
	
	
	
}
