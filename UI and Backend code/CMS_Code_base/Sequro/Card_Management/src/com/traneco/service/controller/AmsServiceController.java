package com.traneco.service.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.accountmanagement.model.ApproveUpgradeTier;
import com.traneco.accountmanagement.model.CashBackCalculationModel;
import com.traneco.accountmanagement.model.FeeTypeMaster;
import com.traneco.accountmanagement.model.TxnReqRespLogMaster;
import com.traneco.accountmanagement.model.VatTypeMaster;
import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.SearchClientCardRequest;
import com.traneco.clientSearch.services.ClientSearchService;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountDetails;
import com.traneco.configuration.model.AccountLinkedWalletMaster;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.AccountTypeCharges;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.AccountTypeWiseBlockedMccMaster;
import com.traneco.configuration.model.AccountTypeWiseWalletMaster;
import com.traneco.configuration.model.AddressProofDocumentTypeMaster;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.CategoryListModel;
import com.traneco.configuration.model.Categorytype;
import com.traneco.configuration.model.Channels;
import com.traneco.configuration.model.CloseAccountMaster;
import com.traneco.configuration.model.CustomerIdCreation;
import com.traneco.configuration.model.CustomerIdMap;
import com.traneco.configuration.model.CustomerIdTable;
import com.traneco.configuration.model.DenominationMaster;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GLAccountLoadingMaster;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.configuration.model.IdentityProofDocumentTypeMaster;
import com.traneco.configuration.model.InstantAccountCreation;
import com.traneco.configuration.model.LoadBalanceModel;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MerchantCategoryCodeMaster;
import com.traneco.configuration.model.NubanCodeConfig;
import com.traneco.configuration.model.NubanTypeConfig;
import com.traneco.configuration.model.ParticipantWiseWalletMaster;
import com.traneco.configuration.model.PreSubAccountMaster;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.model.RegisteredCustWithLinkAccounts;
import com.traneco.configuration.model.RevolvingCreditCardMaster;
import com.traneco.configuration.model.TaxConfigModel;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TransactionTypeModel;
import com.traneco.configuration.model.TxnData;
import com.traneco.configuration.model.TxnReqRes;
import com.traneco.configuration.model.UpgradeTierReqRes;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountKycDetails;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.AccountTransactionLimitation;
import com.traneco.service.model.BulkTransfer;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ChargeMaster;
import com.traneco.service.model.ChargeRelatedMaster;
import com.traneco.service.model.CloseAccountResponse;
import com.traneco.service.model.EmailTemplate;
import com.traneco.service.model.GLAccountTypeMaster;
import com.traneco.service.model.JournalTransfer;
import com.traneco.service.model.PreAccountMaster;
import com.traneco.service.model.WalletAccountMaster;
import com.traneco.service.services.CardChargesService;
import com.traneco.service.services.ClientService;

@Controller
public class AmsServiceController {
	@Autowired
	LoggerUtil log;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	ClientService clientService;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	Environment env;

	@Autowired
	ConfigurationService configurationService;

	@Autowired
	ClientSearchService clientSearchService;

	@Autowired
	SearchClientCardRequest searchClientCardRequest;

	@Autowired
	AccountManagementService accountManagementService;
	
	@Autowired
	CardChargesService cardChargesService;

	SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String className = this.getClass().getSimpleName();

	@RequestMapping(value = { "/accountTypeMasterConfig" }, method = { RequestMethod.GET })
	public String accountTypeMasterConfig(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "accountTypeMasterConfig";
		try {
			model.addAttribute("appl_name", sessionDTO.getApplicationName());

			model.addAttribute("accountTypeMaster", accountTypeMaster);
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CREATE_UPDATE_ACCOUNT_TYPE);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.CREATE_UPDATE_ACCOUNT_TYPE);

			String participantId = sessionDTO.getParticipantid();
			CategoryListModel categoryListModel = new CategoryListModel();
			categoryListModel.setStrParticipantID(participantId);
			// model.addAttribute("categoryList",
			// accountManagementService.getAccountTypeCategoryList(categoryListModel));

			List<CategoryListModel> mCategoryListModels = accountManagementService
					.getAccountTypeCategoryList(categoryListModel);
			model.addAttribute("categoryList", mCategoryListModels);
			model.addAttribute("categoryListJson", this.mapper.writeValueAsString(mCategoryListModels));

			TaxConfigModel taxConfigModel = new TaxConfigModel();
			taxConfigModel.getStrParticipantId();
			model.addAttribute("taxType", accountManagementService.getTaxTypeConfigList(taxConfigModel));

			// added by ankit 28-05-2023
			List<NubanTypeConfig> nubanTypeAndDescription = accountManagementService.getNubanTypeAndDescription();
			model.addAttribute("nubanTypes", nubanTypeAndDescription);
			// added by ankit 28-05-2023

			GLAccountCreation glAccountCreation = new GLAccountCreation();
			glAccountCreation.setStrParticipantId(participantId);

			List<GLAccountCreation> glAccountCreations = accountManagementService
					.getGlAccountTypeAccountNoList(glAccountCreation);
			model.addAttribute("glAccountTypeList", glAccountCreations);

			// model.addAttribute("categoryList", configurationService.getCategoryList());
			// model.addAttribute("taxType", configurationService.getTaxType());

			List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountTypeMasterConfig";
	}

	@RequestMapping(value = { "/accountTypeMasterConfigForm" }, method = { RequestMethod.POST })
	public String accountTypeMasterConfigForm(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "accountTypeMasterConfigForm";
		try {
			model.addAttribute("appl_name", sessionDTO.getApplicationName());

			accountTypeMaster.setStrAccNumLength("14");
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CREATE_UPDATE_ACCOUNT_TYPE);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.CREATE_UPDATE_ACCOUNT_TYPE);

			String participantId = sessionDTO.getParticipantid();
			CategoryListModel categoryListModel = new CategoryListModel();
			categoryListModel.setStrParticipantID(participantId);
			// model.addAttribute("categoryList",
			// accountManagementService.getAccountTypeCategoryList(categoryListModel));

			List<CategoryListModel> mCategoryListModels = accountManagementService.getAccountTypeCategoryList(categoryListModel);
			model.addAttribute("categoryList", mCategoryListModels);
			model.addAttribute("categoryListJson", this.mapper.writeValueAsString(mCategoryListModels));

			TaxConfigModel taxConfigModel = new TaxConfigModel();
			taxConfigModel.getStrParticipantId();
			model.addAttribute("taxType", accountManagementService.getTaxTypeConfigList(taxConfigModel));

			GLAccountCreation glAccountCreation = new GLAccountCreation();
			glAccountCreation.setStrParticipantId(participantId);
			List<GLAccountCreation> glAccountCreations = accountManagementService
					.getGlAccountTypeAccountNoList(glAccountCreation);
			model.addAttribute("glAccountTypeList", glAccountCreations);

			// added by ankit 28-05-2023
			List<NubanTypeConfig> nubanTypeAndDescription = accountManagementService.getNubanTypeAndDescription();
			model.addAttribute("nubanTypes", nubanTypeAndDescription);
			// added by ankit 28-05-2023

			boolean isAlreadyExist = accountManagementService.isAccountTypeAlreadyExist(accountTypeMaster);
			if (isAlreadyExist) 
			{
				model.addAttribute("error", "Duplicate Account Type. Account Already Exist!");
				return "accountTypeMasterConfig";
			}

			if (accountTypeMaster.getStrGLAccountType() != null && accountTypeMaster.getStrGLAccountType().indexOf("-") != -1) 
			{
				String glAccType[] = accountTypeMaster.getStrGLAccountType().split("-");
				accountTypeMaster.setStrGLAccountType(glAccType[0].trim());
				accountTypeMaster.setStrGLAccountNumber(glAccType[1].trim());
			}

			boolean isGLAlreadyExist = accountManagementService.isGLAccountAlreadyExistAgainstAccountType(accountTypeMaster);
			if (isGLAlreadyExist) 
			{
				model.addAttribute("error", "Please Select Different GL Account Type");
				return "accountTypeMasterConfig";
			}

			double taxValue = 0d;
			if (accountTypeMaster.getStrIGST() != null && accountTypeMaster.getStrIGST().trim().length() > 0) 
			{
				String iGst = accountTypeMaster.getStrIGST().substring(0,
						accountTypeMaster.getStrIGST().lastIndexOf("%"));
				taxValue = Double.parseDouble(iGst);
			} 
			else 
			{
				String cGst = accountTypeMaster.getStrCGST().substring(0,
						accountTypeMaster.getStrCGST().lastIndexOf("%"));
				String sGst = accountTypeMaster.getStrSGST().substring(0,
						accountTypeMaster.getStrSGST().lastIndexOf("%"));
				taxValue = Double.parseDouble(cGst) + Double.parseDouble(sGst);
			}
			accountTypeMaster.setStrTaxVal(taxValue + "");

			accountTypeMaster.setStrParticipantId(sessionDTO.getParticipantid());
			accountTypeMaster.setStrCreatedBy(sessionDTO.getLoginID());

			// Added by Sagar For credit account category change Start
			if ("C".equalsIgnoreCase(accountTypeMaster.getStrCategoryType())) 
			{
				accountTypeMaster.setStrIsCreditType("Y");
			}
			// Added by Sagar For credit account category change End

			int count = accountManagementService.addAccountTypeMaster(accountTypeMaster);

			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Account Master Added Successfully!");
			} else {
				model.addAttribute("error", "Account Master Adding Failed!");
			}
			//added by prashant Tayde 26 Aug 2023 ---No Need to Add in AMS
			String accountType = accountTypeMaster.getStrAccountType();
			String accountTypeDesc = accountTypeMaster.getStrDescription();

			String accountTypes = accountType.concat("-").concat(accountTypeDesc).trim();
			accountTypeMaster.setStrAccountType(accountTypes);

			int counts = this.configurationService.addAccountTypeDesc(accountTypeMaster);
			if (counts > 0) 
			{
				System.out.println("Account Type Added in CMS");
			} 
			else 
			{
				System.out.println("Account Type Failed to Save in CMS");
			}
			// model.addAttribute("accountTypeJson",
			// this.mapper.writeValueAsString(accountManagementService.getAccountTypeMasterList()));
			List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding Account Type !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountTypeMasterConfig";
	}

	@RequestMapping(value = { "/viewAccountTypeMasterConfig" }, method = { RequestMethod.GET })
	public String viewAccountTypeMasterConfig(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "accountTypeMasterConfig";
		try {
			model.addAttribute("appl_name", sessionDTO.getApplicationName());
			model.addAttribute("accountTypeMaster", accountTypeMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.VIEW_ACCOUNT_TYPE);
			List<AccountTypeMaster> accountTypeMastersList = accountManagementService.getAccountTypeMasterList();

			model.addAttribute("accountTypeMasterList", accountTypeMastersList);
			model.addAttribute("accountType", this.mapper.writeValueAsString(accountTypeMastersList));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountTypeConfig";
	}

	@RequestMapping(value = { "/editAccountTypeMasterConfig" }, method = { RequestMethod.GET })
	public String editAccountTypeMasterConfig(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "editAccountTypeMasterConfig";
		try {
			model.addAttribute("appl_name", sessionDTO.getApplicationName());

			model.addAttribute("accountTypeMaster", accountTypeMaster);
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CREATE_UPDATE_ACCOUNT_TYPE);

			String participantId = sessionDTO.getParticipantid();

			accountTypeMaster = this.accountManagementService
					.getAccountTypeMasterDetailsBasedOnAccountType(accountTypeMaster).get(0);

			CategoryListModel categoryListModel = new CategoryListModel();
			categoryListModel.setStrParticipantID(participantId);
			// model.addAttribute("categoryList",
			// accountManagementService.getAccountTypeCategoryList(categoryListModel));
			List<CategoryListModel> mCategoryListModels = accountManagementService
					.getAccountTypeCategoryList(categoryListModel);
			model.addAttribute("categoryList", mCategoryListModels);
			model.addAttribute("categoryListJson", this.mapper.writeValueAsString(mCategoryListModels));

			TaxConfigModel taxConfigModel = new TaxConfigModel();
			taxConfigModel.getStrParticipantId();
			model.addAttribute("taxType", accountManagementService.getTaxTypeConfigList(taxConfigModel));

			GLAccountCreation glAccountCreation = new GLAccountCreation();
			glAccountCreation.setStrParticipantId(participantId);

			List<GLAccountCreation> glAccountCreations = accountManagementService
					.getGlAccountTypeAccountNoList(glAccountCreation);
			model.addAttribute("glAccountTypeList", glAccountCreations);
			// model.addAttribute("glAccntTypeListJson",
			// this.mapper.writeValueAsString(glAccountCreations));
			// model.addAttribute("categoryList", configurationService.getCategoryList());
			// model.addAttribute("taxType", configurationService.getTaxType());

			List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));

			model.addAttribute("accountTypeMaster", accountTypeMaster);
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "editAccountTypeMasterConfig";
	}

	@RequestMapping(value = "/updateEditedAccountTypeDetails", method = RequestMethod.POST)
	public String updateEditedAccountTypeDetails(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "updateEditedAccountTypeDetails";
		try {
			log.doLog(4, className, methodName, sessionDTO.getParticipantid());

			String participantId = sessionDTO.getParticipantid();
			accountTypeMaster.setStrParticipantId(participantId);

			double taxValue = 0d;
			if (accountTypeMaster.getStrIGST() != null && accountTypeMaster.getStrIGST().trim().length() > 0) {
				String iGst = accountTypeMaster.getStrIGST().substring(0,
						accountTypeMaster.getStrIGST().lastIndexOf("%"));
				taxValue = Double.parseDouble(iGst);
			} else {
				String cGst = accountTypeMaster.getStrCGST().substring(0,
						accountTypeMaster.getStrCGST().lastIndexOf("%"));
				String sGst = accountTypeMaster.getStrSGST().substring(0,
						accountTypeMaster.getStrSGST().lastIndexOf("%"));
				taxValue = Double.parseDouble(cGst) + Double.parseDouble(sGst);
			}
			accountTypeMaster.setStrTaxVal(taxValue + "");

			int count = accountManagementService.updateAccountTypeDetails(accountTypeMaster);

			if (count > 0) {
				model.addAttribute("success", "Account Type Details Updated Successfully");
			} else {
				model.addAttribute("error", "failed to save");
			}
			AccountTypeMaster accountTypeMaster2 = new AccountTypeMaster();
			model.addAttribute("accountTypeMaster", accountTypeMaster2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "redirect:viewAccountTypeMasterConfig";
	}

	@RequestMapping(value = { "/accountCreation" }, method = { RequestMethod.GET })
	public String accountCreation(Model model, AccountCreation accountCreation) {
		String methodName = "accountCreation";
		try {
			model.addAttribute("accountCreation", accountCreation);
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.CREATE_ACCOUNT);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.CREATE_ACCOUNT);

			String participantId = sessionDTO.getParticipantid();

			accountCreation.setStrParticipantID(participantId);
			accountCreation.setStrCreatedBy(sessionDTO.getLoginID());

			List<AccountTypeMaster> accountTypeMasters = accountManagementService
					.getAccountTypeMasterDataListByParticipantWise(participantId);
			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypelist", this.mapper.writeValueAsString(accountTypeMasters));

			List<AccountCreditLimitCategory> limitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(participantId);
			model.addAttribute("creditLimitCategorylist", limitCategories);
			model.addAttribute("limitCategoryStr", this.mapper.writeValueAsString(limitCategories));

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			/*
			 * model.addAttribute("POAList",configurationService.
			 * getAddressProofDocumentTypeMasters(participantId));
			 * model.addAttribute("POIList",configurationService.
			 * getIdentityProofDocumentTypeMasters(participantId));
			 */

			// model.addAttribute("taxType", configurationService.getTaxType());
			TaxConfigModel taxConfigModel = new TaxConfigModel();
			taxConfigModel.getStrParticipantId();
			model.addAttribute("taxType", accountManagementService.getTaxTypeConfigList(taxConfigModel));

			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			model.addAttribute("countryList", configurationService.getCountry());

			// New Added By Sunny SOni for customer id forced change Start
			// model.addAttribute("stateList",
			// configurationService.getStateData(accountCreation.getStrCountry()));
			// model.addAttribute("cityList",
			// configurationService.getCityData(accountCreation.getStrState()));
			// New Added By Sunny SOni for customer id forced change End
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountCreation";
	}

	@RequestMapping(value = { "/accountCreationForm" }, method = { RequestMethod.POST })
	public String addAccountCreation(Model model, AccountCreation accountCreation) {
		String methodName = "accountCreationForm";
		try {
			model.addAttribute("display", "block");

			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			model.addAttribute("countryList", configurationService.getCountry());

			model.addAttribute("accountCreation", accountCreation);
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.CREATE_ACCOUNT);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.CREATE_ACCOUNT);

			String participantId = sessionDTO.getParticipantid();

			accountCreation.setStrParticipantID(participantId);
			accountCreation.setStrCreatedBy(sessionDTO.getLoginID());

			List<AccountTypeMaster> accountTypeMasters = accountManagementService
					.getAccountTypeMasterDataListByParticipantWise(participantId);
			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypelist", this.mapper.writeValueAsString(accountTypeMasters));

			List<AccountCreditLimitCategory> limitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(participantId);
			model.addAttribute("creditLimitCategorylist", limitCategories);
			model.addAttribute("limitCategoryStr", this.mapper.writeValueAsString(limitCategories));

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			// Check Already Configured or not Start
			/*
			 * boolean isCustomerAccountExist =
			 * accountManagementService.isCustomerAccountAlreadyConfigured(accountCreation);
			 * if (isCustomerAccountExist) { model.addAttribute("error",
			 * "Selected Account Type Already Configured. Please choose different Account Type!!"
			 * ); return "accountCreation"; }
			 */
			// Check Already Configured or not Start

			AccountTransactionLimitation accountTransactionLimitation = new AccountTransactionLimitation();
			accountTransactionLimitation.setStrAccountType(accountCreation.getStrAccountType());

			accountTransactionLimitation = accountManagementService
					.getAccountTxnLimitBasedOnParam(accountTransactionLimitation);
			if (accountTransactionLimitation.getStrID() != null) {
				accountCreation.setStrAvailableDailyLimit(accountTransactionLimitation.getStrDailyTxnLimit());
				accountCreation.setStrAvailableMonthlyLimit(accountTransactionLimitation.getStrMonthlyTxnLimit());
				accountCreation.setStrAvailableYearlyLimit(accountTransactionLimitation.getStrYearlyTxnLimit());

				accountCreation
						.setStrDailyTxnLimit(Integer.valueOf(accountTransactionLimitation.getStrDailyTxnLimit()));
				accountCreation.setStrMonthlyTxnLimit(accountTransactionLimitation.getStrMonthlyTxnLimit());
				accountCreation.setStrYearlyTxnLimit(accountTransactionLimitation.getStrYearlyTxnLimit());
				accountCreation.setStrPerTxnLimit(Integer.valueOf(accountTransactionLimitation.getStrSingleTxnLimit()));
			}

			accountCreation.setStrParticipantID(sessionDTO.getParticipantid());
			accountCreation.setStrCreatedBy(sessionDTO.getLoginID());

			accountCreation.setStrKycUpdateRequired("NO");// For Inserting KYC
			accountCreation.setStrIsLinkedwithCard("N");// For Link not card with account.
			accountCreation.setStrStatus("Active");
			accountCreation.setStrOpeningBalance("0");
			accountCreation.setStrClosingBalance("0");
			accountCreation.setStrTotalOutstandingBal("0");
			accountCreation.setStrIsLinkedwithCard("N");

			int count = accountManagementService.addAccountCreation(accountCreation);
			if (count > 0) {
				/* Changes done for QrCode Genaration and update in account_master - Start */
				Map<String, String> qrCodeMap = new HashMap<String, String>();
				qrCodeMap.put("account_no", accountCreation.getStrAccountNumber());
				qrCodeMap.put("account_name", accountCreation.getStrFirstName());
				qrCodeMap.put("mobile_no", accountCreation.getStrMobileNo());
				qrCodeMap.put("account_type", accountCreation.getStrAccountType());
				accountManagementService.updateQrCodeEntry(qrCodeMap);
				/* Changes done for QrCode Genaration and update in account_master - End */

				model.addAttribute("success", "Account Creation added Successfully");
				AccountCreation accountCreation2 = new AccountCreation();
				model.addAttribute("accountCreation", accountCreation2);
			} else {
				model.addAttribute("error", "Account Creation Failed");
			}
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add!");
		}
		return "accountCreation";
	}

	@RequestMapping(value = "/editAccountCreationForm", method = RequestMethod.POST)
	public ModelAndView editAccountCreationForm(Model model, AccountCreation accountCreation) {
		String methodName = "editAccountCreationForm";
		try {
			log.doLog(4, className, methodName, sessionDTO.getParticipantid());

			String participantId = sessionDTO.getParticipantid();
			System.out.println("participantId::" + participantId);

			accountCreation = this.configurationService.getAccountInfoListByAccountNo(accountCreation).get(0);

			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("stateList", configurationService.getStateData(accountCreation.getStrCountry()));

			model.addAttribute("cityList", configurationService.getCityData(accountCreation.getStrState()));

			String strCountryCode = (accountCreation.getStrCountry() != null) ? accountCreation.getStrCountry() : "NA";
			String strState = (accountCreation.getStrState() != null) ? accountCreation.getStrState() : "NA";
			String strCity = (accountCreation.getStrCity() != null) ? accountCreation.getStrCity() : "NA";

			model.addAttribute("display", "block");
			model.addAttribute("is_issued_acc", false);
			model.addAttribute("strCountryCode", strCountryCode);
			model.addAttribute("strState", strState);
			model.addAttribute("strCity", strCity);

			/*
			 * model.addAttribute("POAList",configurationService.
			 * getAddressProofDocumentTypeMasters(sessionDTO.getParticipantid()));
			 * model.addAttribute("POIList",configurationService.
			 * getIdentityProofDocumentTypeMasters(sessionDTO.getParticipantid()));
			 */

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			List<AccountCreditLimitCategory> limitCategories = configurationService
					.getAccountCreditLimitCategoryListByParticipantWise(participantId);
			model.addAttribute("creditLimitCategorylist", limitCategories);
			model.addAttribute("limitCategoryStr", this.mapper.writeValueAsString(limitCategories));

			boolean isCreditTypeAccount = Boolean.parseBoolean(accountCreation.getStrIsCreditType());
			model.addAttribute("is_credit_acc", isCreditTypeAccount);

			if (accountCreation.getStrIsInstantAccount().equalsIgnoreCase("Y")) {
				model.addAttribute("aestrik_display", "\"none\"");
				model.addAttribute("is_instance_acc", true);
			} else {
				model.addAttribute("aestrik_display", "\"contents\"");
				model.addAttribute("is_instance_acc", false);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return new ModelAndView("editAccountCreationForm", "accountCreation", accountCreation);
	}

	@RequestMapping(value = "/updateEditedAccountForm", method = RequestMethod.GET)
	public String updateAccountMaster(Model model, AccountCreation accountCreation) {
		String methodName = "updateAccountMaster";
		String redirectionPageName = "viewAccountCreationForm";
		int count = 0;
		try {
			log.doLog(4, className, methodName, sessionDTO.getParticipantid());
			String participantId = sessionDTO.getParticipantid();
			accountCreation.setStrParticipantID(participantId);

			if (accountCreation.getStrIsInstantAccount() != null
					&& accountCreation.getStrIsInstantAccount().trim().equalsIgnoreCase("Y")) {
				accountCreation.setStrKycUpdateRequired("NO");// For Inserting KYC
				count = configurationService.updateAccountCreationFromInstanceAccount(accountCreation);
				redirectionPageName = "viewInstantAccountForm";
			} else {
				accountCreation.setStrKycUpdateRequired("YES");// For Updating KYC
				count = configurationService.updateAccountCreation(accountCreation);
			}
			System.out.println("count::" + count);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "redirect:" + redirectionPageName;
	}

	@RequestMapping(value = "/viewAccountCreationForm", method = RequestMethod.GET)
	public String viewAccountCreationForm(Model model, AccountCreation accountCreation) {
		String methodName = "viewAccountCreationForm";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_ISSUED_ACCOUNT);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.VIEW_ISSUED_ACCOUNT);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountCreationForm";
	}

	@RequestMapping(value = { "/instantAccountCreation" }, method = { RequestMethod.GET })
	public String instantAccountCreation(Model model, InstantAccountCreation instantAccountCreation) {
		String methodName = "instantAccountCreation";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CREATE_INSTANT_ACCOUNT);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.CREATE_INSTANT_ACCOUNT);
			List<AccountTypeMaster> accountTypeMasters = accountManagementService
					.getAccountTypeMasterDataListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accountType", accountTypeMasters);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "instantAccountCreation";

	}

	@RequestMapping(value = { "/addInstantAccountCreation" }, method = { RequestMethod.POST })
	public String addInstantAccountCreation(Model model, InstantAccountCreation instantAccountCreation) {
		String methodName = "addInstantAccountCreation";
		try {
			log.doLog(4, className, methodName, "Start add Service Method");
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.CREATE_INSTANT_ACCOUNT);

			/*
			 * StringBuilder accountName = new StringBuilder(); if
			 * (instantAccountCreation.getStrFirstName()!=null &&
			 * instantAccountCreation.getStrFirstName().trim().length()>0) {
			 * accountName.append(instantAccountCreation.getStrFirstName()); } if
			 * (instantAccountCreation.getStrMiddleName()!=null &&
			 * instantAccountCreation.getStrMiddleName().trim().length()>0) {
			 * accountName.append(" ").append(instantAccountCreation.getStrMiddleName()); }
			 * if (instantAccountCreation.getStrLastName()!=null &&
			 * instantAccountCreation.getStrLastName().trim().length()>0) {
			 * accountName.append(" ").append(instantAccountCreation.getStrLastName()); }
			 * instantAccountCreation.setStrAccountName(accountName.toString());
			 */

			instantAccountCreation.setStrParticipantID(sessionDTO.getParticipantid());
			instantAccountCreation.setStrCreatedBy(sessionDTO.getLoginID());

			String result = configurationService.creatingInstantAccount(instantAccountCreation);

			if (result != null && result.contains("success")) {
				model.addAttribute("success", "Instant Account Created SuccessFully");
			} else {
				model.addAttribute("failure", "Instant Account Creation Failed");
			}

		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "instantAccountCreation";
	}

	@RequestMapping(value = "/viewInstantAccountForm", method = RequestMethod.GET)
	public String viewInstantAccountForm(Model model, AccountCreation accountCreation) {
		String methodName = "viewInstantAccountForm";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_AVAILABLE_INSTANT_ACCOUNT);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.VIEW_INSTANT_ACCOUNT);

			accountCreation.setStrParticipantID(this.sessionDTO.getParticipantid());
			accountCreation.setStrIsInstantAccount("Y");

			model.addAttribute("accountCreationList",
					configurationService.getAccountInfoListBasedOnTypes(accountCreation));
			// model.addAttribute("accountTypeList",this.mapper.writeValueAsString(this.configurationService.getAccountCreation()));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewInstantAccountForm";
	}

	// Added by Prashant T for account credit limit
	@RequestMapping(value = "/viewAccountCreditLimitForm", method = RequestMethod.GET)
	public String viewAccountCreditLimit(Model model, AccountCreditLimitCategory accountCreditLimitCategory) {
		String methodName = "viewAccountCreditLimit";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.ACCOUNT_CREDIT_LIMIT);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.ACCOUNT_CREDIT_LIMIT);

			List<AccountCreditLimitCategory> limitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accountLimitCategoryList", limitCategories);
			model.addAttribute("accountCategory", this.mapper.writeValueAsString(limitCategories));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountCreditLimitForm";
	}

	// Added by Prashant T for account credit limit
	@RequestMapping(value = "/viewAccountCreditLimit", method = RequestMethod.POST)
	public String viewAccountCreditLimitForm(Model model, AccountCreditLimitCategory accountCreditLimitCategory) {
		String methodName = "viewAccountCreditLimit";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.ACCOUNT_CREDIT_LIMIT);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.ACCOUNT_CREDIT_LIMIT);

			accountCreditLimitCategory.setStrParticipantId(sessionDTO.getParticipantid());

			boolean isAlreadyExist = accountManagementService.isCreditTypeAlreadyExist(accountCreditLimitCategory);
			if (isAlreadyExist) {
				model.addAttribute("error", "Duplicate Account Type. Account Already Exist!");
			} else {
				accountCreditLimitCategory = accountManagementService
						.saveAccountCreditLimitCategory(accountCreditLimitCategory);

				if (accountCreditLimitCategory.getStrID() > 0) {
					model.addAttribute("success", "Account Credit Limit added Successfully");
				} else {
					model.addAttribute("error", "Account Credit Limit Failed");
				}
			}
		} catch (Exception e) {
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountCreditLimitForm";
	}

	// Added by Prashant T for MCC wise interest form
	@RequestMapping(value = "/viewMccWiseInterestForm", method = RequestMethod.GET)
	public String viewMccWiseInterestForm(Model model, MccWiseInterestModel mccWiseInterestModel) {
		String methodName = "viewMccWiseInterestForm";
		try {
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.MCC_WISE_INTEREST);
			model.addAttribute("leftcreditCardMenuID", TranecoStatusCode.MCC_WISE_INTEREST);

			String participantId = sessionDTO.getParticipantid();

			// Abhishek T for Account type dropdown by is_credit_type Start
			AccountTypeMaster accountTypeMster = new AccountTypeMaster();
			accountTypeMster.setStrParticipantId(sessionDTO.getParticipantid());

			List<AccountTypeMaster> accountTypeMasters = accountManagementService
					.getYCreditAccounTypeObject(accountTypeMster);
			model.addAttribute("accountType", accountTypeMasters);
			// Abhishek T for Account type dropdown by is_credit_type End

			MerchantCategoryCodeMaster merchantCategoryCodeMaster = new MerchantCategoryCodeMaster();
			merchantCategoryCodeMaster.setStrParticipantID(participantId);

			List<MerchantCategoryCodeMaster> merchantCategoryCodeMasters = accountManagementService
					.getMCCList(merchantCategoryCodeMaster);
			model.addAttribute("MccCodeList", merchantCategoryCodeMasters);

			mccWiseInterestModel.setStrParticipantID(participantId);
			List<MccWiseInterestModel> mccWiseInterestModels = accountManagementService
					.getMccWiseInterest(mccWiseInterestModel);

			model.addAttribute("MccWiseInterestList", mccWiseInterestModels);
			model.addAttribute("mccWiseInterestData", this.mapper.writeValueAsString(mccWiseInterestModels));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewMccWiseInterestForm";
	}

	@RequestMapping(value = "/viewMccWiseInterest", method = RequestMethod.POST)
	public String viewMccWiseInterest(Model model, MccWiseInterestModel mccWiseInterestModel) {
		String methodName = "viewMccWiseInterestForm";
		try {
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.MCC_WISE_INTEREST);
			model.addAttribute("leftcreditCardMenuID", TranecoStatusCode.MCC_WISE_INTEREST);
			model.addAttribute("mccWiseInterestModel", mccWiseInterestModel);

			String participantId = sessionDTO.getParticipantid();

			List<AccountTypeMaster> accountTypeMasters = accountManagementService
					.getAccountTypeMasterDataListByParticipantWise(participantId);
			model.addAttribute("accountType", accountTypeMasters);

			MerchantCategoryCodeMaster merchantCategoryCodeMaster = new MerchantCategoryCodeMaster();
			merchantCategoryCodeMaster.setStrParticipantID(participantId);

			List<MerchantCategoryCodeMaster> merchantCategoryCodeMasters = accountManagementService
					.getMCCList(merchantCategoryCodeMaster);
			model.addAttribute("MccCodeList", merchantCategoryCodeMasters);

			mccWiseInterestModel.setStrParticipantID(participantId);
			mccWiseInterestModel.setStrCreatedBy(sessionDTO.getLoginID());

			// Added by Pankaj P for Validate the values - Start
			String strErrMsg = "Duplicate MCC Interest Data - Failed.";

			Boolean ValidateMccWiseInterest = accountManagementService.validateMccWiseInterst(mccWiseInterestModel);
			System.out.println("ValidateMccWiseInterest:::" + ValidateMccWiseInterest);
			if (ValidateMccWiseInterest) {
				model.addAttribute("error", strErrMsg);
				return "viewMccWiseInterestForm";
			}
			// Added by Pankaj P for Validate the values - End
			mccWiseInterestModel = accountManagementService.saveMccInterest(mccWiseInterestModel);

			if (mccWiseInterestModel.getStrID() != null) {
				model.addAttribute("success", "MCC Interest Data Saved Successfully.");
				MccWiseInterestModel mccWiseInterestModelObj = new MccWiseInterestModel();
				model.addAttribute("mccWiseInterestModel", mccWiseInterestModelObj);
			} else {
				model.addAttribute("error", "MCC Interest Data Failed.");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewMccWiseInterestForm";
	}

	@RequestMapping(value = "/viewInterestOutStandingForm", method = RequestMethod.GET)
	public String viewInterestOutStandingForm(Model model, AccountCreditLimitCategory accountCreditLimitCategory,
			AccountCreation accountCreation) {
		String methodName = "viewInterestOutStandingForm";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_OUTSTANDING_ACCOUNT);
			model.addAttribute("leftAccountStatementMenuID",
					TranecoStatusCode.ReportStatement.VIEW_OUTSTANDING_ACCOUNT);

			List<AccountCreditLimitCategory> accountCreditLimitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accounCreditType", accountCreditLimitCategories);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewInterestOutStanding";
	}

	// Added changes for Outstanding INterest Balance Date:26-12-22
	// Added by sagark for creditTypeList display on dropdown list Date:21-12-2022

	@RequestMapping(value = "/viewInterestOutStandingData", method = RequestMethod.POST)
	// public String viewInterestOutStandingData(Model model, AccountCreation
	// accountCreation, AccountCreditLimitCategory accountCreditLimitCategory)
	public String viewInterestOutStandingData(Model model, AccountCreditLimitCategory accountCreditLimitCategory) {
		String methodName = "viewInterestOutStandingData";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_OUTSTANDING_ACCOUNT);
			model.addAttribute("leftAccountStatementMenuID",
					TranecoStatusCode.ReportStatement.VIEW_OUTSTANDING_ACCOUNT);

			List<AccountCreditLimitCategory> accountCreditLimitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accounCreditType", accountCreditLimitCategories);

			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrCreditLimitCategory(accountCreditLimitCategory.getStrCreditTypeCategory());
			accountCreation.setStrAccountNumber(accountCreditLimitCategory.getStrAccountNumber());
			AccountCreation accountCreationData = accountManagementService.getOutstandingAccount(accountCreation);

			List<AccountCreation> accountInterestlist = new ArrayList<AccountCreation>();
			accountInterestlist.add(accountCreationData);

			model.addAttribute("viewInterestRateForm", accountInterestlist);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewInterestOutStanding";
	}

	@RequestMapping(value = { "/cardAccountLinkageView" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String cardAccountLinkageView(Model model, SearchClientCardRequest clientBean, BindingResult result) {
		String methodName = "cardAccountLinkageView";
		// SearchClientCardResponse response = null;
		try {
			model.addAttribute("leftLinkCardMenuId", TranecoStatusCode.linkCards.CARD_ACCOUNT_LINKAGE_VIEW);
			log.doLog(4, className, methodName, "Inside Method :");

			List<AccountTypeMaster> accountTypeMasters = accountManagementService
					.getAccountTypeMasterDataListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accountType", accountTypeMasters);

			model.addAttribute("cardTypeList", configurationService.getCardType());
			clientBean.setStrParticipantID(sessionDTO.getParticipantid());

			// response = clientSearchService.searchClient(clientBean);
			model.addAttribute("display", "block");
			log.doLog(4, className, methodName, "End Inside Method :");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.instAddForm.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		model.addAttribute("clientBean", clientBean);
		return "cardAccountLinkageView";
	}

	@RequestMapping(value = { "/glAccountCreationForm" }, method = { RequestMethod.GET })
	public String glAccountCreationForm(Model model, GLAccountCreation glAccountCreation) {
		String methodName = "glAccountCreationForm";
		try {
			// model.addAttribute("leftGLAccountMenuID",
			// TranecoStatusCode.GL_ACCOUNT_CREATION);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.GL_ACCOUNT_CREATION);
			model.addAttribute("glAccountCreation", glAccountCreation);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccountCreationForm";
	}

	@RequestMapping(value = { "/glAccountCreationFormData" }, method = { RequestMethod.POST })
	public String glAccountCreationFormData(Model model, GLAccountCreation glAccountCreation) {
		String methodName = "glAccountCreationForm";
		String message = "";
		String glUsrEnterAccountNumber = "";
		try {
			// model.addAttribute("leftGLAccountMenuID",
			// TranecoStatusCode.GL_ACCOUNT_CREATION);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.GL_ACCOUNT_CREATION);
			model.addAttribute("glAccountCreation", glAccountCreation);

			glUsrEnterAccountNumber = glAccountCreation.getStrGLAccountNumber();

			String glAccountNumber = glAccountCreation.getStrGLAcountNumber1()
					+ glAccountCreation.getStrGLAccountNumber2() + glUsrEnterAccountNumber;
			glAccountCreation.setStrGLAccountNumber(glAccountNumber);
			glAccountCreation.setStrParticipantId(sessionDTO.getParticipantid());
			glAccountCreation.setStrCreatedBy(sessionDTO.getLoginID());

			boolean isExistAcType = accountManagementService.isGLAccountTypeAlreadyExist(glAccountCreation);
			if (!isExistAcType) {
				boolean isExistAcNo = accountManagementService.isGLAccountAccountNumberAlreadyExist(glAccountCreation);
				if (!isExistAcNo) {
					glAccountCreation.setStrStatus("A");
					glAccountCreation.setStrOpeningBalance("0");
					glAccountCreation.setStrClosingBalance("0");
					glAccountCreation = accountManagementService.addGLAccountType(glAccountCreation);
					if (glAccountCreation != null && glAccountCreation.getStrId() != null) {
						message = "Account Created Successfully.";
						model.addAttribute("success", message);

						GLAccountCreation glAccountCreationObj = new GLAccountCreation();
						model.addAttribute("glAccountCreation", glAccountCreationObj);
					} else {
						message = "Record not Saved.";
						model.addAttribute("error", message);
						glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
					}
				} else {
					message = "Duplicate Account Number";
					model.addAttribute("error", message);
					glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
				}
			} else {
				message = "Duplicate Account Type";
				model.addAttribute("error", message);
				glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccountCreationForm";
	}

	@RequestMapping(value = { "/cardAccountLinkage" }, method = { RequestMethod.GET })
	public String cardAccountLinkage(Model model, CardAccountLinkage cardAccountLinkage) 
	{
		String methodName = "cardAccountLinkage";
		try 
		{
			model.addAttribute("cardAccountLinkage", cardAccountLinkage);
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CARD_ACCOUNT_LINKAGE);
			model.addAttribute("leftLinkCardMenuId", TranecoStatusCode.linkCards.CARD_ACCOUNT_LINKAGE);
			// model.addAttribute("cardAccountLinkList",
			// configurationService.getCardAccountLinkage());

			List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterDataListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accountType", accountTypeMasters);

			model.addAttribute("cardTypeList", configurationService.getCardType());

			// model.addAttribute("cardAccountLink",this.mapper.writeValueAsString(this.configurationService.getCardAccountLinkage()));
		}
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardAccountLinkage";
	}

	/*
	@RequestMapping(value = "/cardAccountLinkageForm", method = RequestMethod.POST, produces = "application/json")
	public String cardAccountLinkageForm(Model model, @RequestBody CardAccountLinkage cardAccountLinkage) 
	{
		String methodName = "cardAccountLinkage";
		ProcessResponse processResponse = new ProcessResponse();
		try {
			model.addAttribute("leftLinkCardMenuId", TranecoStatusCode.linkCards.CARD_ACCOUNT_LINKAGE);
			model.addAttribute("display", "block");

			//List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterDataListByParticipantWise(sessionDTO.getParticipantid());
			//model.addAttribute("accountType", accountTypeMasters);

			//model.addAttribute("cardTypeList", configurationService.getCardType());

			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
			accountCreation.setStrAccountType(cardAccountLinkage.getStrAccountType());

			AccountCreation accCreationObj = accountManagementService.getAccountInformation(accountCreation);
			if (accCreationObj != null) 
			{
				CardTypeModel cardTypeModel = new CardTypeModel();
				cardTypeModel.setCardNumber(cardAccountLinkage.getStrCardNumber());
				cardTypeModel.setStrCardType(cardAccountLinkage.getStrCardType());
				
				String maskValue = Utils.maskValue(cardTypeModel.getCardNumber().trim());
				cardTypeModel.setStrCardNumber(maskValue);
				System.out.println("maskValue::::"+maskValue);

				List<CardTypeModel> cardTypeModels = configurationService.getCardTypeInfo(cardTypeModel);
				
				if (cardTypeModels != null && cardTypeModels.size() > 0) 
				{
					CardAccountLinkage cardAccountLinkageObject = new CardAccountLinkage();
					cardAccountLinkageObject.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
					cardAccountLinkageObject.setStrAccountType(cardAccountLinkage.getStrAccountType());
					cardAccountLinkageObject.setStrTokenCard(cardTypeModels.get(0).getStrTokenCard());
					cardAccountLinkageObject.setStrCardNumber(cardTypeModels.get(0).getStrCardNumber());
					
					if (cardTypeModels.get(0).getStrTokenCard() != null && cardTypeModels.get(0).getStrTokenCard().trim().length() > 0) {
						boolean isCardAlreadyLinked = configurationService.isCardAlreadyLinked(cardAccountLinkageObject.getStrTokenCard());
						if (!isCardAlreadyLinked) 
						{
							cardAccountLinkageObject.setStrCreatedBy(sessionDTO.getLoginID());
							cardAccountLinkageObject.setStrParticipantID(sessionDTO.getParticipantid());
							cardAccountLinkageObject.setStrCardStatus("Active");
							cardAccountLinkageObject.setStrAccountStatus("Active");

							cardAccountLinkageObject.setStrCardId(cardTypeModels.get(0).getStrCardId());
							cardAccountLinkageObject.setStrCardType(cardTypeModels.get(0).getStrCardTypeData());
							cardAccountLinkageObject.setStrCardEncCard(cardTypeModels.get(0).getStrCardEncCard());
							cardAccountLinkageObject.setStrCardDescription(cardTypeModels.get(0).getStrCardDescr());

							cardAccountLinkageObject.setStrCardExpDate(cardTypeModels.get(0).getStrCardExpDate());
							cardAccountLinkageObject.setStrBin(cardTypeModels.get(0).getStrBin());

							String networkType = configurationService.getNetworkType(cardAccountLinkageObject);
							cardAccountLinkageObject.setStrNetworkType(networkType);

							String cardHolderName = configurationService.getCardHolderName(cardAccountLinkageObject);
							cardAccountLinkageObject.setStrCardHolderName(cardHolderName);

							String inputDateString = cardAccountLinkageObject.getStrCardExpDate();
							Date date = inputFormat.parse(inputDateString);

							String expriryDate = outputFormat.format(date);
							System.out.println(expriryDate);

							cardAccountLinkageObject.setStrCardExpDate(expriryDate);

							// Making Entry in CMS cardaccount linkage Table Start
							int count = this.configurationService.addCardAccountLinkageCMS(cardAccountLinkageObject);
							// Making Entry in CMS cardaccount linkage Table End
							if (count > 0) 
							{
								// Added by prashant Tayde for updated Issued date
								CardDetails cardDetails = new CardDetails();
								cardDetails.setStrTokenCard(cardAccountLinkageObject.getStrTokenCard());
								cardDetails.setStrIssuedToCustomer(new Date());
								int update = this.configurationService.updateIssueCardToCustomer(cardDetails);
								// ended by prashant Tayde 20 Aug 2023
						
									accCreationObj.setStrIsLinkedwithCard("Y");
									this.accountManagementService.updateAccountAfterLinkedCard(accCreationObj);

									model.addAttribute("success", "Card Account Link Data added SuccessFully.");
									CardAccountLinkage cardAccountLinkageObj = new CardAccountLinkage();
									model.addAttribute("cardAccountLinkage", cardAccountLinkageObj);
									
									cardAccountLinkageObject.setStrChargeType("CIC");
									processResponse = accountManagementService.applyCardIssuanceCharges(cardAccountLinkageObject);
									if ("S0000".equalsIgnoreCase(processResponse.getCode()))
									{
										cardChargesService.addCardCharges(cardAccountLinkageObject);
										configurationService.insertResponseEntry(processResponse.getAccountTranMasterObj());
										
										processResponse.setCode("S0000");
										processResponse.setStatus("Success");
										processResponse.setMessage("Card Issuance Charges Applied Successfully !");
									}
									else 
									{
										processResponse.setCode("E0000");
										processResponse.setStatus("Failed");
										processResponse.setMessage("Failed to Apply Card Issuance Charges !");
									}
							} 
							else 
							{
								model.addAttribute("error", "Card Account Link Data Failed!");
							}
						}
						else 
						{
							model.addAttribute("error", "card already linked with account.");
						}
					} 
					else
					{
						model.addAttribute("error", "Invalid Card Number. Please Enter Different Card Number");
					}
				}
				else 
				{
					model.addAttribute("error", "Incorrect Card Number.Please Enter Correct Card Number.");
				}
			}
			else 
			{
				model.addAttribute("error", "Incorrect Account Number.Please Enter Correct Account Number.");
			}
		} catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardAccountLinkage";
	}
		*/
	
	
	//Created By Prashant Tayde For Card Account Linakage & CIC Charges - 20 May 2024
	
	@RequestMapping(value = "/cardAccountLinkageForm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> cardAccountLinkageForm(Model model, @RequestBody CardAccountLinkage cardAccountLinkage) 
	{
		ProcessResponse processResponse = new ProcessResponse();
		try 
		{
			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
			accountCreation.setStrAccountType(cardAccountLinkage.getStrAccountType());

			AccountCreation accCreationObj = accountManagementService.getAccountInformation(accountCreation);
			if (accCreationObj != null) 
			{
				CardTypeModel cardTypeModel = new CardTypeModel();
				cardTypeModel.setCardNumber(cardAccountLinkage.getStrCardNumber());
				cardTypeModel.setStrCardType(cardAccountLinkage.getStrCardType());
				
				String maskValue = Utils.maskValue(cardTypeModel.getCardNumber().trim());
				cardTypeModel.setStrCardNumber(maskValue);
				List<CardTypeModel> cardTypeModels = configurationService.getCardTypeInfo(cardTypeModel);
				
				if (cardTypeModels != null && cardTypeModels.size() > 0) 
				{
					CardAccountLinkage cardAccountLinkageObject = new CardAccountLinkage();
					cardAccountLinkageObject.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
					cardAccountLinkageObject.setStrAccountType(cardAccountLinkage.getStrAccountType());
					cardAccountLinkageObject.setStrTokenCard(cardTypeModels.get(0).getStrTokenCard());
					cardAccountLinkageObject.setStrCardNumber(cardTypeModels.get(0).getStrCardNumber());
					
					if (cardTypeModels.get(0).getStrTokenCard() != null && cardTypeModels.get(0).getStrTokenCard().trim().length() > 0) {
						boolean isCardAlreadyLinked = configurationService.isCardAlreadyLinked(cardAccountLinkageObject.getStrTokenCard());
						if (!isCardAlreadyLinked) 
						{
							cardAccountLinkageObject.setStrCreatedBy(sessionDTO.getLoginID());
							cardAccountLinkageObject.setStrParticipantID(sessionDTO.getParticipantid());
							cardAccountLinkageObject.setStrCardStatus("Active");
							cardAccountLinkageObject.setStrAccountStatus("Active");

							cardAccountLinkageObject.setStrCardId(cardTypeModels.get(0).getStrCardId());
							cardAccountLinkageObject.setStrCardType(cardTypeModels.get(0).getStrCardTypeData());
							cardAccountLinkageObject.setStrCardEncCard(cardTypeModels.get(0).getStrCardEncCard());
							cardAccountLinkageObject.setStrCardDescription(cardTypeModels.get(0).getStrCardDescr());

							cardAccountLinkageObject.setStrCardExpDate(cardTypeModels.get(0).getStrCardExpDate());
							cardAccountLinkageObject.setStrBin(cardTypeModels.get(0).getStrBin());

							String networkType = configurationService.getNetworkType(cardAccountLinkageObject);
							cardAccountLinkageObject.setStrNetworkType(networkType);

							String cardHolderName = configurationService.getCardHolderName(cardAccountLinkageObject);
							cardAccountLinkageObject.setStrCardHolderName(cardHolderName);

							String inputDateString = cardAccountLinkageObject.getStrCardExpDate();
							Date date = inputFormat.parse(inputDateString);

							String expriryDate = outputFormat.format(date);
							cardAccountLinkageObject.setStrCardExpDate(expriryDate);

							
							int count = this.configurationService.addCardAccountLinkageCMS(cardAccountLinkageObject);
							if (count > 0) 
							{
								CardDetails cardDetails = new CardDetails();
								cardDetails.setStrTokenCard(cardAccountLinkageObject.getStrTokenCard());
								cardDetails.setStrIssuedToCustomer(new Date());
								int update = this.configurationService.updateIssueCardToCustomer(cardDetails);
								
						
									accCreationObj.setStrIsLinkedwithCard("Y");
									this.accountManagementService.updateAccountAfterLinkedCard(accCreationObj);
									
									cardAccountLinkageObject.setStrChargeType("CIC");
									processResponse = accountManagementService.applyCardIssuanceCharges(cardAccountLinkageObject);
									if ("S0000".equalsIgnoreCase(processResponse.getCode()))
									{
										cardChargesService.addCardCharges(cardAccountLinkageObject);
										configurationService.insertResponseEntry(processResponse.getAccountTranMasterObj());
										
										processResponse.setCode("S0000");
										processResponse.setStatus("Success");
										processResponse.setMessage("Card Issuance Charges Applied Successfully !");
									}
									else 
									{
										processResponse.setCode("E0000");
										processResponse.setStatus("Failed");
										processResponse.setMessage("Failed to Apply Card Issuance Charges !");
									}
							} 
							else 
							{
								processResponse.setCode("E0000");
								processResponse.setStatus("Failed");
								processResponse.setMessage("Card Account Link Data Failed !");
								
							}
						}
						else 
						{
							processResponse.setCode("E0000");
							processResponse.setStatus("Failed");
							processResponse.setMessage("Card already linked with account !");
						}
					} 
					else
					{
						processResponse.setCode("E0000");
						processResponse.setStatus("Failed");
						processResponse.setMessage("Invalid Card Number. Please Enter Different Card Number !");
					}
				}
				else 
				{
					processResponse.setCode("E0000");
					processResponse.setStatus("Failed");
					processResponse.setMessage("Incorrect Card Number.Please Enter Correct Card Number. !");
				}
			}
			else 
			{
				processResponse.setCode("E0000");
				processResponse.setStatus("Failed");
				processResponse.setMessage("Incorrect Account Number.Please Enter Correct Account Number. !");
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(processResponse);
	
	}
	
	
	

	@RequestMapping(value = { "/linkedPreAccountForm" }, method = { RequestMethod.GET })
	public String linkedPreAccountForm(Model model, PreAccountMaster preAccountMaster) {
		String methodName = "linkedPreAccountForm";
		try {
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.APPROVE_CUSTOMER);
			preAccountMaster.setScreenType("customer");
			// model.addAttribute("screenType","customer");
			model.addAttribute("title", "Customer List to Link Customer Id");
			model.addAttribute("linkName", "Link Cust Id");
			model.addAttribute("preAccountMaster", preAccountMaster);
			// preAccountMaster.setStrIsAccountNoCreated("N");
			// List<PreAccountMaster> preAccountMasters =
			// accountManagementService.getPreAccountDataList(preAccountMaster);
			List<PreAccountMaster> preAccountMasters = accountManagementService
					.getNonLinkedCustomersListForCustomerId(preAccountMaster);
			model.addAttribute("preAccountList", preAccountMasters);
			model.addAttribute("preAccountListJson", this.mapper.writeValueAsString(preAccountMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display Connection Configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "linkedPreAccountForm";
	}

	@RequestMapping(value = { "/viewPreAccountForLink" }, method = { RequestMethod.POST })
	public String viewPreAccountForLink(Model model, PreAccountMaster preAccountMaster) {
		try {
			System.out.println("[viewPreAccountForLink] preAccountMaster::" + preAccountMaster);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.APPROVE_CUSTOMER);
			if (preAccountMaster != null && preAccountMaster.getStrIsAccountNoRelated() != null
					&& "N".equalsIgnoreCase(preAccountMaster.getStrIsAccountNoRelated())) {
				model.addAttribute("title", "Customer");
				CustomerIdCreation customerIdCreationObj = accountManagementService
						.getCustDetailsByMobileNumber(preAccountMaster.getStrMobileNo());
				if (customerIdCreationObj != null && customerIdCreationObj.getStrCustId() != null) {
					preAccountMaster.setIsCustIdCreated("Y"); // Customer Id Creation
					preAccountMaster.setStrIsKycVerified("Y");
					int count = accountManagementService.updatePreAccountMasterAccount(preAccountMaster);
					if (count > 0) {
						String subject = "Regarding to Customer Id";
						String bodyMsg = "Dear User,<br/><br/> " + "Please find the below created Customer Id : <br/> "
								+ "Customer Id is : <b>" + customerIdCreationObj.getStrCustId() + "</br> <br/> "
								+ "Powered by Sequro Technologies Pvt Ltd.";

						EmailTemplate emailTemplate = new EmailTemplate();
						emailTemplate.setStrFrom("contactus@sequrotechnologies.com");
						emailTemplate.setStrTo(customerIdCreationObj.getStrEmailID());
						emailTemplate.setStrSubject(subject);
						emailTemplate.setStrText(bodyMsg);

						String result = accountManagementService.sendMail(emailTemplate);
						if (result != null) {
							return "redirect:linkedPreAccountForm";
						}
					}
				}
			} else {
				model.addAttribute("title", "Account");
				try {
					AccountCreation accountCreation = new AccountCreation();
					accountCreation.setStrCustId(preAccountMaster.getCust_id());

					List<AccountCreation> accountDetailsList = accountManagementService
							.getAccountInfoListBasedOnTypes(accountCreation);
					model.addAttribute("accountDetailsList", accountDetailsList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			PreAccountMaster existingPreAccountObject = new PreAccountMaster();
			existingPreAccountObject.setStrMobileNo(preAccountMaster.getStrMobileNo());

			existingPreAccountObject = accountManagementService.getPreAccountMaster(existingPreAccountObject);
			System.out.println("[viewPreAccountForLink]  existingPreAccountObject::" + existingPreAccountObject);
			// if ("N".equalsIgnoreCase(preAccountMaster.getStrIsKycVerified()))
			{
				if ("NIGERIA".equalsIgnoreCase(sessionDTO.getApplicationName())) {
					String currentTier = accountManagementService
							.getActiveCurrentTierBasedOnMobileNo(preAccountMaster.getStrMobileNo());
					preAccountMaster.setStrCurrentTier(currentTier);
				}

				AccountKycDetails accountKycDetails = new AccountKycDetails();
				// accountKycDetails.setStrAccountType(preAccountMaster.getStrAccountType());
				accountKycDetails.setStrMobileNo(preAccountMaster.getStrMobileNo());

				accountKycDetails = accountManagementService.getAccountKycDetailsInfo(accountKycDetails);

				if (accountKycDetails.getStrID() != null) {
					preAccountMaster.setStrAddressProofDocumentId(accountKycDetails.getStrAddressProofDocumentType());
					preAccountMaster.setStrAddressProofDocumentValue(accountKycDetails.getStrAddressDocumentValue());
					preAccountMaster.setStrAddProofImage(accountKycDetails.getStrAddressDocumentName());

					preAccountMaster.setStrIdentityProofDocumentId(accountKycDetails.getStrIdentityProofDocumentType());
					preAccountMaster
							.setStrIdentityProofDocumentValue(accountKycDetails.getStrIdentityProofDocumentValue());
					preAccountMaster.setStrIdeProofImage(accountKycDetails.getStrIdentityProofDocumentName());
					if ("NIGERIA".equalsIgnoreCase(sessionDTO.getApplicationName())) {
						preAccountMaster.setStrBvnNumber(accountKycDetails.getStrBvnNumber());
						// preAccountMaster.setStrTier1PassportPhotograph(accountKycDetails.getStrTier1PassportPhotograph());
						// preAccountMaster.setStrTier2PassportPhotograph(accountKycDetails.getStrTier2PassportPhotograph());

						preAccountMaster.setStrTier1PassPhoto(accountKycDetails.getStrTier1PassportPhotograph());
						preAccountMaster.setStrTier2PassPhoto(accountKycDetails.getStrTier2PassportPhotograph());
					}

				}
			}

			preAccountMaster.setStrFirstName(existingPreAccountObject.getStrFirstName());
			preAccountMaster.setStrMiddleName(existingPreAccountObject.getStrMiddleName());
			preAccountMaster.setStrLastName(existingPreAccountObject.getStrLastName());
			preAccountMaster.setStrGender(existingPreAccountObject.getStrGender());
			preAccountMaster.setStrEmailID(existingPreAccountObject.getStrEmailID());
			preAccountMaster.setStrDOB(existingPreAccountObject.getStrDOB());
			preAccountMaster.setStrCountryCode(existingPreAccountObject.getStrCountryCode());

			if (preAccountMaster.getAccountTypeCategory() != null
					&& "C".equalsIgnoreCase(preAccountMaster.getAccountTypeCategory())) {
				List<AccountCreditLimitCategory> limitCategories = accountManagementService
						.getAccountCreditLimitCategoryListByParticipantWise(sessionDTO.getParticipantid());
				model.addAttribute("creditLimitCategorylist", limitCategories);
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			e.printStackTrace();
		}
		return "viewVerifyKycForm";
	}

	@RequestMapping(value = { "/sendAccountNumber" }, method = { RequestMethod.POST })
	public String sendAccountNumber(Model model, PreAccountMaster preAccountMaster) {
		int count = 0;
		String accountNo = "";
		String subject = "";
		String bodyMsg = "";
		try {
			String redirectionStr = "";
			// CustomerIdCreation customerIdCreationObj =
			// customerMasterService.getCustomerIdInfo(customerIdCreation);
			String toEmailId = preAccountMaster.getStrEmailID();
			if ("Y".equalsIgnoreCase(preAccountMaster.getStrIsAccountNoRelated())) 
			{
				redirectionStr = "nonLinkedCustomerForAccountNo";
				CustomerIdCreation customerIdCreation = new CustomerIdCreation();
				customerIdCreation.setStrCustId(preAccountMaster.getCust_id());
				customerIdCreation.setStrMobileNo(preAccountMaster.getStrMobileNo());

				CustomerIdCreation customerIdCreationObj = accountManagementService.getCustMstrDetails(customerIdCreation);

				AccountCreation accountCreation = new AccountCreation();
				accountCreation.setStrCustId(customerIdCreationObj.getStrCustId());

				accountCreation.setStrAccountType(preAccountMaster.getStrAccountType());

				accountCreation.setStrFirstName(customerIdCreationObj.getStrFirstName());
				accountCreation.setStrLastName(customerIdCreationObj.getStrLastName());
				accountCreation.setStrMiddleName(customerIdCreationObj.getStrMiddleName());

				accountCreation.setStrDOB(customerIdCreationObj.getStrDOB());
				accountCreation.setStrMobileNo(customerIdCreationObj.getStrMobileNo());
				accountCreation.setStrGender(customerIdCreationObj.getStrGender());

				accountCreation.setStrEmailID(customerIdCreationObj.getStrEmailID());
				accountCreation.setStrIsInstantAccount("N");

				accountCreation.setStrPhoneCode(customerIdCreationObj.getStrPhoneCode());
				accountCreation.setStrPhoneNo(customerIdCreationObj.getStrPhoneNo());

				accountCreation.setStrCountry(customerIdCreationObj.getStrCountry());
				accountCreation.setStrState(customerIdCreationObj.getStrState());
				accountCreation.setStrCity(customerIdCreationObj.getStrCity());
				accountCreation.setStrPinCode(customerIdCreationObj.getStrPinCode());

				accountCreation.setStrAddressProofDocumentId(preAccountMaster.getStrAddressProofDocumentId());
				accountCreation.setStrAddressProofDocumentValue(preAccountMaster.getStrAddressProofDocumentValue());

				accountCreation.setStrIdentityProofDocumentId(preAccountMaster.getStrIdentityProofDocumentId());
				accountCreation.setStrIdentityProofDocumentValue(preAccountMaster.getStrIdentityProofDocumentValue());

				accountCreation.setStrCreatedBy(sessionDTO.getLoginID());
				accountCreation.setStrParticipantID(sessionDTO.getParticipantid());

				AccountTransactionLimitation accountTransactionLimitation = new AccountTransactionLimitation();
				accountTransactionLimitation.setStrAccountType(accountCreation.getStrAccountType());

				accountTransactionLimitation = accountManagementService.getAccountTxnLimitBasedOnParam(accountTransactionLimitation);
				if (accountTransactionLimitation != null && accountTransactionLimitation.getStrID() != null) 
				{
					accountCreation.setStrAvailableDailyLimit(accountTransactionLimitation.getStrDailyTxnLimit());
					accountCreation.setStrAvailableMonthlyLimit(accountTransactionLimitation.getStrMonthlyTxnLimit());
					accountCreation.setStrAvailableYearlyLimit(accountTransactionLimitation.getStrYearlyTxnLimit());

					accountCreation.setStrDailyTxnLimit(Integer.valueOf(accountTransactionLimitation.getStrDailyTxnLimit()));
					accountCreation.setStrMonthlyTxnLimit(accountTransactionLimitation.getStrMonthlyTxnLimit());
					accountCreation.setStrYearlyTxnLimit(accountTransactionLimitation.getStrYearlyTxnLimit());
					accountCreation.setStrPerTxnLimit(Integer.valueOf(accountTransactionLimitation.getStrSingleTxnLimit()));
				}

				if (preAccountMaster.getAccountTypeCategory() != null && "C".equalsIgnoreCase(preAccountMaster.getAccountTypeCategory())) 
				{
					if (preAccountMaster.getStrCreditLimitCategory() != null && preAccountMaster.getStrCreditLimitCategory().trim().indexOf("-") != -1) 
					{
						String creditLimit[] = preAccountMaster.getStrCreditLimitCategory().split("-");
						if (creditLimit != null && creditLimit.length == 2) 
						{
							accountCreation.setStrCreditLimitCategory(creditLimit[0]);
							accountCreation.setStrCreditLimitAmount(creditLimit[1]);
							accountCreation.setStrAvailableCreditLimit(creditLimit[1]);
					    }
					}
				}

				int tCount = 0;
				if (preAccountMaster.getStrGeneratedAccountNumber() != null
						&& preAccountMaster.getStrGeneratedAccountNumber().trim().length() > 0) {
					accountNo = preAccountMaster.getStrGeneratedAccountNumber();
					accountCreation.setStrAccountNumber(preAccountMaster.getStrGeneratedAccountNumber());

					accountCreation.setStrStatus("Active");
					accountCreation.setStrClosingBalance("0");
					accountCreation.setStrOpeningBalance("0");
					accountCreation.setStrLoadCount("0");
					accountCreation.setStrKycUpdateRequired("YES");
					accountCreation.setStrIsLinkedwithCard("N");// For Link not card with account.
					accountCreation.setStrTotalOutstandingBal("0");

					tCount = accountManagementService.addAccountCreation(accountCreation);

					/* Changes done for QrCode Genaration and update in account_master - Start */
					Map<String, String> qrCodeMap = new HashMap<String, String>();
					qrCodeMap.put("account_no", accountCreation.getStrAccountNumber());
					qrCodeMap.put("account_name", accountCreation.getStrFirstName());
					qrCodeMap.put("mobile_no", accountCreation.getStrMobileNo());
					qrCodeMap.put("account_type", accountCreation.getStrAccountType());
					accountManagementService.updateQrCodeEntry(qrCodeMap);
					/* Changes done for QrCode Genaration and update in account_master - End */

					subject = "Account Creation Details";
					bodyMsg = "Dear User,<br/><br/> " + "Please find the below account number and user id : <br/> "
							+ "Account Number is : <b>" + accountNo + "</b> <br/> " + "User Id is: <b>"
							+ preAccountMaster.getStrMobileNo() + "</b>  <br/><br/><br/> "
							+ "Powered by Sequro Technologies Pvt Ltd.";
				} else {
					accountNo = preAccountMaster.getStrAccountNumber();
					accountCreation.setStrAccountNumber(preAccountMaster.getStrAccountNumber());
					accountCreation.setStrKycUpdateRequired("YES");
					tCount = accountManagementService.updateAccountCreationFromInstanceAccount(accountCreation);
				}

				if (tCount > 0) {
					PreSubAccountMaster preSubAccountMaster = new PreSubAccountMaster();
					preSubAccountMaster.setStrMobileNo(preAccountMaster.getStrMobileNo());
					preSubAccountMaster.setStrAccountType(preAccountMaster.getStrAccountType());
					preSubAccountMaster.setStrIsAccountNoCreated("Y"); // Account Number Creation

					count = accountManagementService.updateIsAccountNoCreatedField(preSubAccountMaster);
				}
			} else {
				redirectionStr = "linkedPreAccountForm";
				CustomerIdMap customerIdMap = mapper.readValue(preAccountMaster.getCustomerIdMap(),
						CustomerIdMap.class);
				CustomerIdCreation customerIDCreation = new CustomerIdCreation();

				System.out.println(customerIdMap);

				String action = customerIdMap.getStrAction();
				String julianYear = customerIdMap.getStrYear();
				String customerID = customerIdMap.getStrCustID();
				String julianDate = customerIdMap.getStrJulianDate();

				customerIDCreation.setStrAction(action);
				customerIDCreation.setStrCustId(customerID);
				customerIDCreation.setStrJulianYear(julianYear);
				customerIDCreation.setStrJulianDate(julianDate);

				String participantId = sessionDTO.getParticipantid();

				PreAccountMaster preAccountMasterData = accountManagementService.getPreAccountMaster(preAccountMaster);

				customerIDCreation.setStrCreatedBy(sessionDTO.getLoginID());
				customerIDCreation.setStrParticipantID(participantId);
				customerIDCreation.setStrKycUpdateRequired("YES");

				customerIDCreation.setStrFirstName(preAccountMasterData.getStrFirstName());
				customerIDCreation.setStrMiddleName(preAccountMasterData.getStrMiddleName());
				customerIDCreation.setStrLastName(preAccountMasterData.getStrLastName());
				customerIDCreation.setStrGender(preAccountMasterData.getStrGender());
				customerIDCreation.setStrDOB(preAccountMasterData.getStrDOB());
				customerIDCreation.setStrEmailID(preAccountMasterData.getStrEmailID());
				customerIDCreation.setStrPhoneCode(preAccountMasterData.getStrCountryCode());
				customerIDCreation.setStrMobileNo(preAccountMasterData.getStrMobileNo());

				int insertCount = accountManagementService.insertCustIdDetails(customerIDCreation);
				if (insertCount > 0) 
				{
					CustomerIdTable customerIdTable = new CustomerIdTable();
					customerIdTable.setStrAction(customerIDCreation.getStrAction());
					customerIdTable.setStrLastTxnSerialNo(customerIDCreation.getStrCustId());
					customerIdTable.setStrYear(customerIDCreation.getStrJulianYear());
					customerIdTable.setStrJulianDate(customerIDCreation.getStrJulianDate());
					customerIdTable.setStrCreatedBy(sessionDTO.getLoginID());
					customerIdTable.setStrCreatedDate(customerIDCreation.getStrAccountCreationDate());

					int upDatecount = accountManagementService.updateCustId(customerIdTable);
					if (upDatecount > 0) 
					{
						PreAccountMaster preAccountMasterObj = new PreAccountMaster();
						preAccountMasterObj.setStrMobileNo(preAccountMaster.getStrMobileNo());
						preAccountMasterObj.setIsCustIdCreated("Y"); // Customer Id Creation
						// preAccountMaster.setIsCustIdCreated("Y"); //Customer Id Creation
						// count =
						// accountManagementService.updatePreAccountMasterAccount(preAccountMaster);
						count = accountManagementService.updatePreAccountMasterAccount(preAccountMasterObj);
						subject = "Regarding to Customer Id";
						bodyMsg = "Dear User,<br/><br/> " + "Please find the below created Customer Id : <br/> "
								+ "Customer Id is : <b>" + customerIDCreation.getStrCustId() + "</br> <br/> "
								+ "Powered by Sequro Technologies Pvt Ltd.";
					}

				}
			}
			System.out.println("Count::[" + count + "]");
			if (count > 0) {
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setStrFrom("contactus@sequrotechnologies.com");
				emailTemplate.setStrTo(toEmailId);
				emailTemplate.setStrSubject(subject);
				emailTemplate.setStrText(bodyMsg);

				// emailTemplate.setStrBcc("ssoni@sequrotechnologies.com");

				String result = accountManagementService.sendMail(emailTemplate);

				if (result != null) {
					// return "redirect:linkedPreAccountForm";
					return "redirect:" + redirectionStr;
				}
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			e.printStackTrace();
		}
		// return "linkedPreAccountForm";
		return "viewVerifyKycForm";
	}

	@RequestMapping(value = { "/viewVerifyKycForm" }, method = { RequestMethod.GET })
	public String viewVerifyKycForm(Model model, PreAccountMaster preAccountMaster) {
		String methodName = "viewVerifyKycForm";
		try {
			String id = preAccountMaster.getStrID();
			System.out.println(id);
			String participantId = sessionDTO.getParticipantid();
			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAddressProofDocumentId(preAccountMaster.getStrAddressProofDocumentId());
			accountCreation.setStrAddressProofDocumentValue(preAccountMaster.getStrAddressProofDocumentType());
			accountCreation.setStrIdentityProofDocumentId(preAccountMaster.getStrIdentityProofDocumentId());
			accountCreation.setStrIdentityProofDocumentValue(preAccountMaster.getStrIdentityProofDocumentType());

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			/*
			 * model.addAttribute("POAList",
			 * configurationService.getAddressProofDocumentTypeMasters(participantId));
			 * model.addAttribute("POIList",
			 * configurationService.getIdentityProofDocumentTypeMasters(participantId));
			 */

			String strdoctType = (preAccountMaster.getStrAddressProofDocumentId() != null)
					? preAccountMaster.getStrAddressProofDocumentId()
					: "NA";
			String strdocValue = (preAccountMaster.getStrAddressProofDocumentType() != null)
					? preAccountMaster.getStrAddressProofDocumentType()
					: "NA";
			String strdocImage = (preAccountMaster.getStrAddressProofImage() != null)
					? preAccountMaster.getStrAddressProofImage()
					: "NA";

			model.addAttribute("strAddressProofDocumentType", strdoctType);
			model.addAttribute("strAddressDocumentValue", strdocValue);
			model.addAttribute("strAddressProofImage", strdocImage);

			/*
			 * model.addAttribute("instantAccountNo",
			 * this.configurationService.getIntantAccNumberBasedOnKyc(accountCreation));
			 * model.addAttribute("instantAccountNumberList",
			 * this.mapper.writeValueAsString(this.configurationService.
			 * getIntantAccNumberBasedOnKyc(accountCreation)));
			 */

			preAccountMaster = this.configurationService.getKycDataForverification(preAccountMaster);

			/*
			 * ResponseBean responseBean = new ResponseBean();
			 * 
			 * Blob blob = this.configurationService.fetchDocumentImgaebyId(id); byte barr[]
			 * = blob.getBytes(1, (int) blob.length());
			 * 
			 * responseBean.setPdfByte(barr); responseBean.setOutResponseCode("200");
			 */

		} catch (Exception e) {
			model.addAttribute("display", "block");
			System.out.println(e.getMessage());
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewVerifyKycForm";
	}

	/**
	 * @param model
	 * @param preAccountMaster
	 * @return
	 */
	@RequestMapping(value = { "/verifyKycForm" }, method = { RequestMethod.POST })
	public String verifyKycForm(Model model, PreAccountMaster preAccountMaster) {
		String methodName = "verifyKycForm";
		try {
			AccountCreation accountCreation = new AccountCreation();

			// List<PreAccountMaster> preAccountMaster = new ArrayList<PreAccountMaster>();
			preAccountMaster.setStrFirstName(accountCreation.getStrFirstName());
			preAccountMaster.setStrMiddleName(accountCreation.getStrMiddleName());
			preAccountMaster.setStrLastName(accountCreation.getStrLastName());
			preAccountMaster.setStrAccountType(accountCreation.getStrAccountType());
			// preAccountMaster.setStrAccountNumber(accountCreation.getStrAccountNumber());
			preAccountMaster.setStrMobileNo(accountCreation.getStrMobileNo());
			preAccountMaster.setStrGender(accountCreation.getStrGender());
			preAccountMaster.setStrEmailID(accountCreation.getStrEmailID());

			// model.addAttribute("accountCreation",
			// this.configurationService.updatePreAccountMasterData(accountCreation));

		} catch (Exception e) 
		{
			model.addAttribute("display", "block");
			System.out.println(e.getMessage());
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewVerifyKycForm";
	}

	@RequestMapping(value = "/viewLoadBalance", method = RequestMethod.GET)
	public String viewLoadBalance(Model model, LoadBalanceModel loadBalanceModel) {
		String methodName = "viewLoadBalance";
		try {
			model.addAttribute("LoadBalanceModel", loadBalanceModel);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.LOAD_BALANCE);

			Channels channels = new Channels();
			channels.setStrParticipantId(sessionDTO.getParticipantid());
			List<Channels> channelList = accountManagementService.getChannelsList(channels);

			model.addAttribute("channel", channelList);

			AccountTypeMaster accountTypeMster = new AccountTypeMaster();
			accountTypeMster.setStrParticipantId(sessionDTO.getParticipantid());
			model.addAttribute("accountType", accountManagementService.getNonCreditAccounTypeObject(accountTypeMster));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewLoadBalanceData";
	}

	@RequestMapping(value = "/loadBalance", method = RequestMethod.POST)
	public String loadBalance(Model model, LoadBalanceModel loadBalanceModel) {
		String methodName = "loadBalance";
		String modelName = "viewLoadBalanceData";
		boolean isErrorResponse = false;
		String errorResponseMsg = "";
		try {
			loadBalanceModel.setStrParticipantId(sessionDTO.getParticipantid());
			loadBalanceModel.setStrCreatedBy(sessionDTO.getLoginID());

			AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
			accountTypeMaster.setStrAccountType(loadBalanceModel.getStrAccountType());

			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.LOAD_BALANCE);

			Channels channels = new Channels();
			channels.setStrParticipantId(sessionDTO.getParticipantid());
			List<Channels> channelList = accountManagementService.getChannelsList(channels);

			model.addAttribute("channel", channelList);

			model.addAttribute("accountType", accountManagementService.getNonCreditAccounTypeObject(accountTypeMaster));

			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAccountType(loadBalanceModel.getStrAccountType());
			accountCreation.setStrAccountNumber(loadBalanceModel.getStrAccountNumber());
			accountCreation = accountManagementService.getAccountInformation(accountCreation);

			if (accountCreation.getStrID() == null) {
				isErrorResponse = true;
				errorResponseMsg = "Error: Account Number Not Found.";
			} else {
				TxnReqRes traReqRes = new TxnReqRes();
				TxnData txnData = new TxnData();
				txnData.setAccountNumber(loadBalanceModel.getStrAccountNumber());
				txnData.setAccountType(loadBalanceModel.getStrAccountType());
				txnData.setDe004_amount(loadBalanceModel.getStrLoadedBalance());

				if (loadBalanceModel.getStrChannel() != null && loadBalanceModel.getStrChannel().trim().length() > 0) {
					if (loadBalanceModel.getStrChannel().indexOf("-") != -1) {
						String channelArr[] = loadBalanceModel.getStrChannel().split("-");
						txnData.setChannel(channelArr[0]);
						txnData.setChannelCode(channelArr[1]);
					} else {
						txnData.setChannel(loadBalanceModel.getStrChannel());
					}
				}
				//txnData.setParticipant_id(Integer.parseInt(sessionDTO.getParticipantid()));
				txnData.setParticipant_id(sessionDTO.getParticipantid());
				traReqRes.setTxnData(txnData);

				traReqRes = accountManagementService.loadAccountBalance(traReqRes);

				ProcessResponse processResponse = traReqRes.getResponse();

				if ("S0000".equalsIgnoreCase(processResponse.getCode())) {
					model.addAttribute("success", "Success: Balance Loaded Succesfully");
				} else {
					isErrorResponse = true;
					errorResponseMsg = "Error: " + processResponse.getMessage();
				}

				/*
				 * String oldloadCountStr = accountCreation.getStrLoadCount(); int oldLoadCount
				 * = (oldloadCountStr != null) ? Integer.parseInt(oldloadCountStr) : 0; int
				 * newLoadCount = oldLoadCount + 1;
				 * 
				 * accountTypeMaster =
				 * this.configurationService.getSingleAccountTypeObject(accountTypeMaster);
				 * 
				 * int allowedLoadCount = (accountTypeMaster.getStrAllowLoadCash() != null) ?
				 * Integer.parseInt(accountTypeMaster.getStrAllowLoadCash()) : -1; if
				 * (newLoadCount > allowedLoadCount) { isErrorResponse = true; errorResponseMsg
				 * = "Error: Cash Loading Limit Reached"; } else { String txnId =
				 * Utils.getGeneratedTransactionId();
				 * loadBalanceModel.setStrTransactionId(txnId);
				 * loadBalanceModel.setStrAccountCategory(accountTypeMaster.getStrCategoryType()
				 * );
				 * 
				 * //Inserting in account_tran_master table
				 * //accountManagementService.addAccountTxnData(loadBalanceModel);
				 * 
				 * double existingClosingbal = (accountCreation.getStrClosingBalance()!=null &&
				 * accountCreation.getStrClosingBalance().trim().length() > 0) ?
				 * Double.parseDouble(accountCreation.getStrClosingBalance()) : 0; double
				 * updatedClosingBalance =
				 * Double.parseDouble(loadBalanceModel.getStrLoadedBalance()) +
				 * existingClosingbal; if (existingClosingbal == 0) {
				 * accountCreation.setStrOpeningBalance(updatedClosingBalance+""); } else {
				 * accountCreation.setStrOpeningBalance(accountCreation.getStrOpeningBalance());
				 * } accountCreation.setStrClosingBalance(updatedClosingBalance+"");
				 * accountCreation.setStrLoadCount(newLoadCount+"");
				 * 
				 * //Updating in acoount_master table
				 * accountManagementService.updateBalanceRelatedData(accountCreation);
				 * 
				 * //inserting in account_statement table
				 * accountManagementService.addAccountStatement(loadBalanceModel,
				 * accountCreation);
				 * 
				 * //inserting in account_load_master table
				 * accountManagementService.saveAccountLoadMasterData(loadBalanceModel);
				 * 
				 * //Added For GL Start GLAccountTypeMaster glAccountTypeMaster = new
				 * GLAccountTypeMaster();
				 * glAccountTypeMaster.setTranId(loadBalanceModel.getStrTransactionId());
				 * glAccountTypeMaster.setUserAccountType(loadBalanceModel.getStrAccountType());
				 * 
				 * accountManagementService.addGLAccountEntry(glAccountTypeMaster); //Added For
				 * GL End
				 * 
				 * model.addAttribute("success", "Success: Balance Loaded Succesfully"); }
				 */
			}
			if (isErrorResponse) {
				model.addAttribute("error", errorResponseMsg);
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service!");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			System.out.println("loadBalance:: Exception Occured While Adding Balance=" + e);
			e.printStackTrace();
		}
		// return "viewLoadBalanceData";
		return modelName;
	}

	@RequestMapping(value = "/glAccountLoadingForm", method = RequestMethod.GET)
	public String glLoadBalance(Model model, GLAccountLoadingMaster glAccountLoadingMaster) {
		String methodName = "glAccountLoading";
		try {
			model.addAttribute("glAccountLoadingMaster", glAccountLoadingMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.GLACCOUNT_BALANCELOADING);

			Channels channels = new Channels();
			channels.setStrParticipantId(sessionDTO.getParticipantid());
			List<Channels> channelList = accountManagementService.getChannelsList(channels);

			model.addAttribute("channel", channelList);

			// GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
			// List<GLAccountTypeMaster> glAccountTypeMastersList =
			// accountManagementService.getListOfGLAccountTypelist(glAccountTypeMaster);
			List<GLAccountTypeMaster> glAccountTypeMastersList = accountManagementService.getGLAccountTypeList();

			model.addAttribute("glAccountType", glAccountTypeMastersList);
			model.addAttribute("glAccountTypeJson", this.mapper.writeValueAsString(glAccountTypeMastersList));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service! or Account Not Found");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccountLoadingForm";
	}

	@RequestMapping(value = "/glAccountLoading", method = RequestMethod.POST)
	public String addGlLoadBalance(Model model, GLAccountLoadingMaster glAccountLoadingMaster) {
		String methodName = "glAccountLoading";
		try {
			model.addAttribute("glAccountLoadingMaster", glAccountLoadingMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.GLACCOUNT_BALANCELOADING);

			Channels channels = new Channels();
			channels.setStrParticipantId(sessionDTO.getParticipantid());
			List<Channels> channelList = accountManagementService.getChannelsList(channels);

			model.addAttribute("channel", channelList);

			GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
			// List<GLAccountTypeMaster> glAccountTypeMastersList =
			// accountManagementService.getListOfGLAccountTypelist(glAccountTypeMaster);
			List<GLAccountTypeMaster> glAccountTypeMastersList = accountManagementService.getGLAccountTypeList();

			model.addAttribute("glAccountType", glAccountTypeMastersList);
			model.addAttribute("glAccountTypeJson", this.mapper.writeValueAsString(glAccountTypeMastersList));

			String txnId = Utils.getGeneratedTransactionId();

			glAccountLoadingMaster.setStrTransactionId(txnId);
			glAccountLoadingMaster.setStrParticipantId(sessionDTO.getParticipantid());
			glAccountLoadingMaster.setStrAccountCreatedBy(sessionDTO.getLoginID());

			glAccountLoadingMaster.setStrTimeOfLoading(Utils.getLocalTime());

			// Inserting in account_tran master table
			AccountTranMaster accountTranMaster = accountManagementService
					.addAccountTransactionMasterForGLAccount(glAccountLoadingMaster);
			if (accountTranMaster != null) {
				// Inserting in gl_account_load_master master table
				accountManagementService.loadGLAccountInformationData(glAccountLoadingMaster);

				glAccountTypeMaster.setStrGLAccountType(glAccountLoadingMaster.getStrGLAccountType());
				glAccountTypeMaster.setStrAccountNumber(glAccountLoadingMaster.getStrAccountNumber());

				String closingBalance = accountManagementService.getClosingBalanceOfGlAccount(glAccountTypeMaster);
				if (closingBalance == null) {
					closingBalance = "";
				}

				Double closingBal = (closingBalance != null && closingBalance.length() > 0)
						? Double.parseDouble(closingBalance)
						: 0;
				Double updatedClosingBalance = closingBal
						+ Double.parseDouble(glAccountLoadingMaster.getStrLoadedBalance());

				if (closingBalance != null && closingBalance.trim().length() == 0) {
					glAccountTypeMaster.setStrOpeningBalance(glAccountLoadingMaster.getStrLoadedBalance());
				}

				glAccountTypeMaster.setStrClosingBalance(String.valueOf(updatedClosingBalance));

				// updating gl_account_type_master table
				accountManagementService.updateGlaccountTypeDetails(glAccountTypeMaster);

				// Inserting in account_statement table
				accountTranMaster.setStrAccountNumber(glAccountLoadingMaster.getStrAccountNumber());
				accountTranMaster.setStrAccountType(glAccountLoadingMaster.getStrGLAccountType());
				accountManagementService.addAccountStatementForGLAccount(accountTranMaster, updatedClosingBalance + "");

				accountManagementService.addAccountStatementForGLAccountStatement(accountTranMaster,
						updatedClosingBalance + "");

				GLAccountLoadingMaster glAccountLoadingMasterObj = new GLAccountLoadingMaster();
				model.addAttribute("glAccountLoadingMaster", glAccountLoadingMasterObj);

				model.addAttribute("success", "Success: Balance Loaded Succesfully");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service! or Account Not Found");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccountLoadingForm";
	}

	@RequestMapping(value = "/getAvailableBalanceGl", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getAvailableBalanceOfGL(@RequestBody GLAccountLoadingMaster glAccountLoadingMaster) {
		try {
			GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
			glAccountTypeMaster.setStrGLAccountType(glAccountLoadingMaster.getStrGLAccountType());
			glAccountTypeMaster.setStrAccountNumber(glAccountLoadingMaster.getStrAccountNumber());

			String availableBalance = accountManagementService.getClosingBalanceOfGlAccount(glAccountTypeMaster);
			System.out.println(availableBalance);
			if (availableBalance == null) {
				availableBalance = "";
			}
			return ResponseEntity.ok(availableBalance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// added by ankit

	// Added by SagarK for Gl account Statement Date:03-01-2023 --start
	@RequestMapping(value = { "/glAccountStatementForm" }, method = { RequestMethod.GET })
	public String glAccountStatementForm(Model model,
			GlAccountTypeWiseStatementMaster glAccountTypeWiseStatementMaster) {
		String methodName = "glAccountStatementForm";
		try {
			// model.addAttribute("leftAccountStatementMenuID",
			// TranecoStatusCode.GLACCOUNT_STATEMENT);
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.ReportStatement.GL_ACCOUNT_STATEMENT);
			model.addAttribute("glAccountStatementForm", glAccountTypeWiseStatementMaster);

			// model.addAttribute("glaccountTypelist",
			// this.configurationService.getGlAccountType());

			GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
			List<GLAccountTypeMaster> glAccountTypeMastersList = accountManagementService
					.getListOfGLAccountTypelist(glAccountTypeMaster);

			model.addAttribute("glaccountTypelist", glAccountTypeMastersList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccountStatementForm";
	}
	// Added by SagarK for Gl account Statement Date:03-01-2023 --end

	@RequestMapping(value = "/viewAccountDetailsForm", method = RequestMethod.GET)
	public String accountDetailsForm(Model model, AccountDetails accountDetails, HttpSession session) {
		String methodName = "accountDetailsForm";
		try {
			System.out.println(session);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.ACCOUNT_DETAILS);

			/*
			 * if (session.getAttribute("accountDetails") == null) {
			 * session.setAttribute("accountDetails", accountDetails); }
			 */

			StringBuilder accountSessionId = new StringBuilder();
			if (session.getAttribute("account_number") == null && session.getAttribute("masked_card_number") == null) {
				session.setAttribute("account_number", accountDetails.getStrAccountNumber());
				session.setAttribute("masked_card_number", accountDetails.getStrCardNumber());

				accountSessionId.append(accountDetails.getStrAccountNumber()).append("~")
						.append(accountDetails.getStrCardNumber());

				session.setAttribute(accountSessionId + "", accountDetails);
			} else {
				accountSessionId.append(session.getAttribute("account_number")).append("~")
						.append(session.getAttribute("masked_card_number"));

				// accountDetails = (AccountDetails) session.getAttribute("accountDetails");
				accountDetails = (AccountDetails) session.getAttribute(accountSessionId + "");
				System.out.println(accountDetails);
				accountDetails.setStrAccountType(accountDetails.getStrAccountType());
				model.addAttribute("accountDetails", accountDetails);
				return "viewAccountDetailsForm";
			}

			String participantId = sessionDTO.getParticipantid();
			CardAccountLinkage cardAccountLinkage = new CardAccountLinkage();
			cardAccountLinkage.setStrParticipantID(participantId);
			cardAccountLinkage.setStrAccountNumber(accountDetails.getStrAccountNumber());
			cardAccountLinkage.setStrCardNumber(accountDetails.getStrCardNumber());
			try {
				cardAccountLinkage = accountManagementService.getCardAccountLinkages(cardAccountLinkage);

				AccountTransactionLimitation accountTransactionLimitation = new AccountTransactionLimitation();
				accountTransactionLimitation.setStrParticipantID(participantId);
				accountTransactionLimitation.setStrAccountType(cardAccountLinkage.getStrAccountType());

				accountTransactionLimitation = accountManagementService
						.getAccountTxnLimitBasedOnParam(accountTransactionLimitation);

				accountDetails.setStrAssignedSingleLimit(accountTransactionLimitation.getStrSingleTxnLimit());
				accountDetails.setStrAssignedDailyLimit(accountTransactionLimitation.getStrDailyTxnLimit());
				accountDetails.setStrAssignedMonthlyLimit(accountTransactionLimitation.getStrMonthlyTxnLimit());
				accountDetails.setStrAssignedYearlyLimit(accountTransactionLimitation.getStrYearlyTxnLimit());
			} catch (Exception e) {
				e.printStackTrace();
			}

			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrParticipantID(participantId);
			accountCreation.setStrAccountType(cardAccountLinkage.getStrAccountType());
			accountCreation.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
			try {
				accountCreation = this.configurationService.getAccountInformation(accountCreation);

				accountDetails.setStrAccountType(accountCreation.getStrAccountType());
				accountDetails.setStrAccountCatogory(accountCreation.getStrCreditLimitCategory());
				accountDetails.setStrAccountNumber(accountCreation.getStrAccountNumber());
				accountDetails.setStrAvailableBalance(accountCreation.getStrClosingBalance());

				accountDetails.setStrIsInstanceAccount(
						(accountCreation.getStrIsInstantAccount().equalsIgnoreCase("Y")) ? "YES" : "NO");

				accountDetails.setStrFirstName(accountCreation.getStrFirstName());
				accountDetails.setStrMiddleName(accountCreation.getStrMiddleName());
				accountDetails.setStrLastName(accountCreation.getStrLastName());
				accountDetails.setStrGender(accountCreation.getStrGender());
				accountDetails.setStrDOB(accountCreation.getStrDOB());
				accountDetails.setStrEmailId(accountCreation.getStrEmailID());
				accountDetails.setStrAddress1(accountCreation.getStrAddress1());
				accountDetails.setStrAddress2(accountCreation.getStrAddress2());
				accountDetails.setStrAddress3(accountCreation.getStrAddress3());

				accountDetails.setStrCountry(configurationService.getCountryName(accountCreation.getStrCountry()));
				accountDetails.setStrState(configurationService.getStateName(accountCreation.getStrState()));
				accountDetails.setStrCity(configurationService.getCityName(accountCreation.getStrCity()));

				accountDetails.setStrPincode(accountCreation.getStrPinCode());
				accountDetails.setStrMobileNo(accountCreation.getStrMobileNo());
				accountDetails.setStrAccountCreationDate(accountCreation.getStrDateOfCreation());

				accountDetails.setStrAccountIssuedDate(cardAccountLinkage.getStrCreationDate());

				accountDetails.setStrDailyAvailableLimit(accountCreation.getStrAvailableDailyLimit());
				accountDetails.setStrMonthlyAvailableLimit(accountCreation.getStrAvailableMonthlyLimit());
				accountDetails.setStrYearlyAvailableLimit(accountCreation.getStrAvailableYearlyLimit());

				accountDetails.setStrAvailableCreditLimit(accountCreation.getStrAvailableCreditLimit());

				accountDetails.setStrNoOfBalanceLoading(accountCreation.getStrLoadCount());
				accountDetails.setStrTotalBalanceLoaded(accountCreation.getStrClosingBalance());

				accountDetails.setStrTotalPrincipalOutStanding(accountCreation.getStrTotalOutstandingBal());
				accountDetails.setStrTotalInterestOuStanding(accountCreation.getStrTotalOutstandingInterest());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				AccountCreditLimitCategory accountCreditLimitCategory = new AccountCreditLimitCategory();
				accountCreditLimitCategory.setStrParticipantId(participantId);
				accountCreditLimitCategory.setStrCreditType(accountCreation.getStrCreditLimitCategory());

				accountCreditLimitCategory = this.configurationService
						.getAccountCreditLimitCategory(accountCreditLimitCategory);

				accountDetails.setStrAssignedCreditLimit(accountCreditLimitCategory.getStrCreditLimit());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountDetailsForm";
	}

	@RequestMapping(value = "/viewAccountLinkageDetails", method = RequestMethod.GET)
	public String viewAccountLinkageDetailsForm(Model model, CardAccountLinkage cardAccountLinkage,
			HttpSession session) {
		String methodName = "viewAccountLinkageDetails";
		try {
			model.addAttribute("CardAccountLinkage", cardAccountLinkage);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CARD_ACCOUNT_LINKAGE_DETAILS);

			StringBuilder accountSessionId = new StringBuilder();
			accountSessionId.append(session.getAttribute("account_number")).append("~")
					.append(session.getAttribute("masked_card_number"));

			AccountDetails accountDetails = (AccountDetails) session.getAttribute(accountSessionId + "");

			// AccountDetails accountDetails = (AccountDetails)
			// session.getAttribute("accountDetails");

			CardAccountLinkage cardAccountLinkageInfo = new CardAccountLinkage();
			cardAccountLinkageInfo.setStrParticipantID(this.sessionDTO.getParticipantid());
			cardAccountLinkageInfo.setStrAccountNumber(accountDetails.getStrAccountNumber());

			List<CardAccountLinkage> listOfAccountLinkages = accountManagementService
					.getCardLinkAccountList(cardAccountLinkageInfo);

			model.addAttribute("cardAccountLinkList", listOfAccountLinkages);

			// model.addAttribute("cardAccountLinkList",
			// this.configurationService.getCardLinkAccountList(cardAccountLinkageInfo));
			// model.addAttribute("cardAccountLinkList",
			// this.configurationService.getCardAccountLinkage());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountLinkageDetails";
	}

	@RequestMapping(value = "/linkedAccountWallet", method = RequestMethod.GET)
	public String linkedAccountWallet(Model model, AccountLinkedWalletMaster accountLinkedWalletMaster,
			HttpSession session) {
		String methodName = "linkedAccountWallet";
		try {
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.VIEW_LINKED_ACCOUNT_WALLETS);

			StringBuilder accountSessionId = new StringBuilder();
			accountSessionId.append(session.getAttribute("account_number")).append("~")
					.append(session.getAttribute("masked_card_number"));

			AccountDetails accountDetails = (AccountDetails) session.getAttribute(accountSessionId + "");
			System.out.println(accountDetails);

			WalletAccountMaster walletAccountMaster = new WalletAccountMaster();
			walletAccountMaster.setStrAccountNumber(accountDetails.getStrAccountNumber());

			List<WalletAccountMaster> walletAccountMasters = accountManagementService
					.getLinkedAccountWalletList(walletAccountMaster);

			model.addAttribute("accountLinkedlist", getAccountLinkedWalletMasterMappedData(walletAccountMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "linkedAccountWallet";
	}

	private List<AccountLinkedWalletMaster> getAccountLinkedWalletMasterMappedData(
			List<WalletAccountMaster> walletAccountMasters) {
		List<AccountLinkedWalletMaster> accountLinkedWalletMasters = new ArrayList<AccountLinkedWalletMaster>();
		for (WalletAccountMaster walletAccountMaster : walletAccountMasters) {
			AccountLinkedWalletMaster accountLinkedWalletMaster = new AccountLinkedWalletMaster();
			accountLinkedWalletMaster.setStrWalletAccountNumber(walletAccountMaster.getStrWalletAccountNumber());
			accountLinkedWalletMaster.setStrAccountType(walletAccountMaster.getStrAccountType());
			accountLinkedWalletMaster.setStrMcc(walletAccountMaster.getStrMccCode());
			accountLinkedWalletMaster.setStrAccountNumber(walletAccountMaster.getStrAccountNumber());
			accountLinkedWalletMaster.setStrPercentage(walletAccountMaster.getStrPercentage());
			accountLinkedWalletMaster.setStrAvailableBalance(walletAccountMaster.getStrAvailableBalance());

			accountLinkedWalletMasters.add(accountLinkedWalletMaster);
		}
		return accountLinkedWalletMasters;
	}

	// Added by sunny Soni for showing add wallet screen Start
	@RequestMapping(value = { "/addWalletConfigForm" }, method = { RequestMethod.GET })
	public String addWalletConfigForm(Model model, MerchantCategoryCodeMaster merchantCategoryCodeMaster) {
		String methodName = "addWalletConfigForm";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_PARTICIPANT_WISE_WALLET);

			merchantCategoryCodeMaster.setStrParticipantID(sessionDTO.getParticipantid());

			List<MerchantCategoryCodeMaster> unSelectedMccList = this.configurationService
					.getUnSelectedAllMCC(merchantCategoryCodeMaster);
			if (unSelectedMccList != null && unSelectedMccList.size() > 0) {
				model.addAttribute("unselectedMccList", unSelectedMccList);
				model.addAttribute("unselectedMccListJson", this.mapper.writeValueAsString(unSelectedMccList));
			}

			List<MerchantCategoryCodeMaster> selectedMccList = this.configurationService
					.getSelectedMCCList(merchantCategoryCodeMaster);
			if (selectedMccList != null && selectedMccList.size() > 0) {
				model.addAttribute("selectedMccList", selectedMccList);
			}

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addWalletConfigForm";
	}

	@RequestMapping(value = { "/addParticipantWiseWallet" }, method = { RequestMethod.POST })
	public String addParticipantWiseWallet(Model model, MerchantCategoryCodeMaster merchantCategoryCodeMaster) {
		String methodName = "addParticipantWiseWallet";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_PARTICIPANT_WISE_WALLET);

			ParticipantWiseWalletMaster participantWiseWalletMaster = new ParticipantWiseWalletMaster();
			participantWiseWalletMaster.setStrMccCode(merchantCategoryCodeMaster.getStrMccCode());
			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());

			participantWiseWalletMaster = this.configurationService
					.addParticipantWiseWalletMaster(participantWiseWalletMaster);
			if (participantWiseWalletMaster.getStrID() != null) {
				model.addAttribute("success", "Wallet Added Successfully for Participant !");
			} else {
				model.addAttribute("error", "error");
			}

			merchantCategoryCodeMaster.setStrParticipantID(sessionDTO.getParticipantid());

			List<MerchantCategoryCodeMaster> selectedMccList = this.configurationService
					.getSelectedMCCList(merchantCategoryCodeMaster);
			if (selectedMccList != null && selectedMccList.size() > 0) {
				model.addAttribute("selectedMccList", selectedMccList);
			}

			List<MerchantCategoryCodeMaster> unSelectedMccList = this.configurationService
					.getUnSelectedAllMCC(merchantCategoryCodeMaster);
			if (unSelectedMccList != null && unSelectedMccList.size() > 0) {
				model.addAttribute("unselectedMccList", unSelectedMccList);
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display charge module configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addWalletConfigForm";
	}

	@RequestMapping(value = { "/showAccTypeWalletConfigForm" }, method = { RequestMethod.GET })
	public String addAccTypeWalletConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		String methodName = "addAccTypeWalletConfigForm";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_ACCOUNT_TYPE_WISE_WALLET);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());

			List<AccountTypeWiseBlockedMccMaster> accountTypeWiseBlockedMccMasters = this.configurationService
					.getAccountTypeWiseUnBlockedMccMaster(accountTypeWiseBlockedMccMaster);
			model.addAttribute("mccList", accountTypeWiseBlockedMccMasters);
			model.addAttribute("mccListJson", this.mapper.writeValueAsString(accountTypeWiseBlockedMccMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accTypeWalletConfigForm";
	}

	@RequestMapping(value = { "/addBlockMccConfigForm" }, method = { RequestMethod.GET })
	public String addBlockMccConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		String methodName = "addBlockMccConfigForm";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_BLOCK_MCC_CONFIG_FORM);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());

			List<ParticipantWiseWalletMaster> participantWiseWalletMasters = this.configurationService
					.getMCCListParticipantWise(participantWiseWalletMaster);
			model.addAttribute("mccList", participantWiseWalletMasters);
			model.addAttribute("mccListJson", this.mapper.writeValueAsString(participantWiseWalletMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addBlockMccCfgForm";
	}

	@RequestMapping(value = { "/addBlockMccConfigFormData" }, method = { RequestMethod.POST })
	public String addBlockMccConfigFormData(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		try {
			System.out.println("model::" + model);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_BLOCK_MCC_CONFIG_FORM);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();

			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseBlockedMccMaster.setStrCreatedBy(sessionDTO.getLoginID());
			accountTypeWiseBlockedMccMaster.setStrAccounType(participantWiseWalletMaster.getStrAccountType());
			accountTypeWiseBlockedMccMaster.setStrBlockedMCCCode(participantWiseWalletMaster.getStrMccCode());

			accountTypeWiseBlockedMccMaster = this.configurationService
					.addAccountTypeWiseBlockedMccMaster(accountTypeWiseBlockedMccMaster);
			if (accountTypeWiseBlockedMccMaster.getStrID() != null) {
				model.addAttribute("success", "MCC Blocked List Added Successfully!");
			} else {
				model.addAttribute("error", "error");
			}

			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());

			List<ParticipantWiseWalletMaster> participantWiseWalletMasters = this.configurationService
					.getMCCListParticipantWise(participantWiseWalletMaster);
			model.addAttribute("mccList", participantWiseWalletMasters);
			model.addAttribute("mccListJson", this.mapper.writeValueAsString(participantWiseWalletMasters));

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			e.printStackTrace();
		}
		return "addBlockMccCfgForm";
	}

	@RequestMapping(value = { "/addAccountTypeWalletConfigForm" }, method = { RequestMethod.POST })
	public String addAccountTypeWalletConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		String formName = "accTypeWalletConfigForm";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_ACCOUNT_TYPE_WISE_WALLET);
			// model.addAttribute("accountType",
			// this.configurationService.getAccountType());
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());
			List<AccountTypeWiseBlockedMccMaster> accountTypeWiseBlockedMccMasters = this.configurationService
					.getAccountTypeWiseUnBlockedMccMaster(accountTypeWiseBlockedMccMaster);
			model.addAttribute("mccList", accountTypeWiseBlockedMccMasters);
			model.addAttribute("mccListJson", this.mapper.writeValueAsString(accountTypeWiseBlockedMccMasters));

			String strMccCode = participantWiseWalletMaster.getStrMccCode();
			if (strMccCode == null) {
				model.addAttribute("error", "NO MCC Code Selected.Please Select.");
				return formName;
			}

			AccountTypeWiseWalletMaster accountTypeWiseWalletMaster = new AccountTypeWiseWalletMaster();
			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			accountTypeWiseWalletMaster.setStrAccounType(participantWiseWalletMaster.getStrAccountType());
			accountTypeWiseWalletMaster.setStrMccCode(participantWiseWalletMaster.getStrMccCode());
			accountTypeWiseWalletMaster.setStrPercentage(participantWiseWalletMaster.getStrPercentage());

			accountTypeWiseWalletMaster = this.configurationService
					.addAccountTypeWiseWalletMaster(accountTypeWiseWalletMaster);
			if (accountTypeWiseWalletMaster.getStrID() != null) {
				model.addAttribute("success", "Wallet Added Successfully for Participant !");
			} else {
				model.addAttribute("error", "error");
			}

			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());

			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			e.printStackTrace();
		}
		return formName;
	}

	@RequestMapping(value = { "/addAccTypeWalletConfigForm" }, method = { RequestMethod.POST })
	public String addingAccTypeWalletConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		String methodName = "addAccTypeWalletConfigForm";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_ACCOUNT_TYPE_WISE_WALLET);
			model.addAttribute("accountType", this.configurationService.getAccountType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accTypeWalletConfigForm";
	}
	// Added by sunny Soni for showing add wallet screen End

	// Added by Pankaj P for Customer Id creation Start
	@RequestMapping(value = { "/customerIdCreationConfig" }, method = { RequestMethod.GET })
	public String customerIdCreationConfig(Model model, CustomerIdCreation customerIDCreation) {
		String methodName = "customerIdCreationConfig";
		try {
			model.addAttribute("customerIdCreationConfig", customerIDCreation);
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CREATE_CUSTOMER_ID_TYPE);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.CREATE_CUSTOMER_ID_TYPE);

			String participantId = sessionDTO.getParticipantid();

			customerIDCreation.setStrParticipantID(participantId);
			customerIDCreation.setStrCreatedBy(sessionDTO.getLoginID());

			/*
			 * List<AccountTypeMaster> accountTypeMasters =
			 * accountManagementService.getAccountTypeMasterDataListByParticipantWise(
			 * participantId); model.addAttribute("accountType", accountTypeMasters);
			 * model.addAttribute("accountTypelist",
			 * this.mapper.writeValueAsString(accountTypeMasters));
			 */

			/*
			 * List<AccountCreditLimitCategory> limitCategories =
			 * accountManagementService.getAccountCreditLimitCategoryListByParticipantWise(
			 * participantId);
			 * model.addAttribute("creditLimitCategorylist",limitCategories);
			 * model.addAttribute("limitCategoryStr",this.mapper.writeValueAsString(
			 * limitCategories));
			 */

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);

			List<AddressProofDocumentTypeMaster> addressProofDocumentTypeMasters = accountManagementService
					.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster);

			model.addAttribute("POAList", addressProofDocumentTypeMasters);

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			TaxConfigModel taxConfigModel = new TaxConfigModel();
			taxConfigModel.getStrParticipantId();
			model.addAttribute("taxType", accountManagementService.getTaxTypeConfigList(taxConfigModel));

			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			/*
			 * CountryCodeMaster countryCodeMaster = new CountryCodeMaster();
			 * model.addAttribute("phoneCodeList",
			 * accountManagementService.getCountryCode(countryCodeMaster));
			 */

			model.addAttribute("countryList", configurationService.getCountry());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "customerIdCreationConfig";
	}

	@RequestMapping(value = { "/customerIdCreationConfig" }, method = { RequestMethod.POST })
	public String addcustomerIdCreationConfig(Model model, CustomerIdCreation customerIDCreation) {
		String methodName = "customerIdCreationConfigForm";
		try {
			String participantId = sessionDTO.getParticipantid();

			model.addAttribute("display", "block");
			model.addAttribute("customerIdCreationConfig", customerIDCreation);
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.CREATE_CUSTOMER_ID_TYPE);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.CREATE_CUSTOMER_ID_TYPE);

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			TaxConfigModel taxConfigModel = new TaxConfigModel();
			taxConfigModel.getStrParticipantId();
			model.addAttribute("taxType", accountManagementService.getTaxTypeConfigList(taxConfigModel));

			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			/*
			 * CountryCodeMaster countryCodeMaster = new CountryCodeMaster();
			 * model.addAttribute("phoneCodeList",
			 * accountManagementService.getCountryCode(countryCodeMaster));
			 */

			model.addAttribute("countryList", configurationService.getCountry());

			// Added by Sagar for validate cust id and make entry in pre account master with
			// password blank Start
			CustomerIdCreation custListData = accountManagementService
					.getCustDetailsByMobileNumber(customerIDCreation.getStrMobileNo());
			if (custListData != null && custListData.getStrCustId() != null) {
				model.addAttribute("error", "CustID already Created!!");
				return "customerIdCreationConfig";
			}

			String phoneCode = customerIDCreation.getStrPhoneCode().split("-")[0].trim();
			String countryCodeShortName = customerIDCreation.getStrPhoneCode().split("-")[1].trim();
			phoneCode = "+" + phoneCode;
			customerIDCreation.setStrPhoneCode(phoneCode);

			int preCount = 0;
			PreAccountMaster preAccountMaster = new PreAccountMaster();
			preAccountMaster.setStrMobileNo(customerIDCreation.getStrMobileNo());
			PreAccountMaster entryExistOfPreAccountMaster = accountManagementService
					.getPreAccountMaster(preAccountMaster);

			if (entryExistOfPreAccountMaster != null && entryExistOfPreAccountMaster.getStrID() != null) {
				preAccountMaster.setIsCustIdCreated("Y");
				preAccountMaster.setStrIsKycVerified("Y");
				preCount = accountManagementService.updatePreAccountMasterAccount(preAccountMaster);
			} else {
				preAccountMaster.setStrFirstName(customerIDCreation.getStrFirstName());
				preAccountMaster.setStrMiddleName(customerIDCreation.getStrMiddleName());
				preAccountMaster.setStrLastName(customerIDCreation.getStrLastName());
				preAccountMaster.setStrGender(customerIDCreation.getStrGender());
				preAccountMaster.setStrCountryCode(phoneCode);
				preAccountMaster.setStrCountryCodeShortName(countryCodeShortName);
				preAccountMaster.setStrMobileNo(customerIDCreation.getStrMobileNo());
				preAccountMaster.setStrEmailID(customerIDCreation.getStrEmailID());
				preAccountMaster.setStrDOB(customerIDCreation.getStrDOB());
				preAccountMaster.setStrAccountType(customerIDCreation.getStrAccountType());
				preAccountMaster.setStrCreatedBy(customerIDCreation.getStrCreatedBy());

				preAccountMaster.setStrIsKycVerified("Y");
				preAccountMaster.setIsCustIdCreated("Y");

				preCount = accountManagementService.insertPrecaccountMaster(preAccountMaster);
			}
			// Added by Sagar for validate cust id and make entry in pre account master with
			// password blank End
			System.out.println("Inside addcustomerIdCreationConfig preCount::[" + preCount + "]");
			// Added by Sagar to handler duplicate Entry Start
			if (preCount > 0) {
				customerIDCreation.setStrCreatedBy(sessionDTO.getLoginID());
				customerIDCreation.setStrParticipantID(participantId);
				customerIDCreation.setStrKycUpdateRequired("NO");

				CustomerIdMap customerIdMap = accountManagementService.getCustomerId();

				String action = customerIdMap.getStrAction();
				String julianYear = customerIdMap.getStrYear();
				String customerID = customerIdMap.getStrCustID();
				String julianDate = customerIdMap.getStrJulianDate();

				customerIDCreation.setStrAction(action);
				customerIDCreation.setStrCustId(customerID);
				customerIDCreation.setStrJulianYear(julianYear);
				customerIDCreation.setStrJulianDate(julianDate);

				int count = accountManagementService.insertCustIdDetails(customerIDCreation);
				if (count > 0) {
					CustomerIdTable customerIdTable = new CustomerIdTable();
					customerIdTable.setStrAction(customerIDCreation.getStrAction());
					customerIdTable.setStrLastTxnSerialNo(customerIDCreation.getStrCustId());
					customerIdTable.setStrYear(customerIDCreation.getStrJulianYear());
					customerIdTable.setStrJulianDate(customerIDCreation.getStrJulianDate());
					customerIdTable.setStrCreatedBy(sessionDTO.getLoginID());
					customerIdTable.setStrCreatedDate(customerIDCreation.getStrAccountCreationDate());

					int cnt = accountManagementService.updateCustId(customerIdTable);

					if (cnt > 0) {
						model.addAttribute("success",
								"Your Created Customer ID is::" + customerIDCreation.getStrCustId());
						CustomerIdCreation customerIDCreationObj = new CustomerIdCreation();
						model.addAttribute("customerIdCreationConfig", customerIDCreationObj);

						String bodyMessage = "Dear User,<br/><br/> "
								+ "Please find the below created Customer Id : <br/> " + "Customer Id is : <b>"
								+ customerIDCreation.getStrCustId() + "</br> <br/> "
								+ "Powered by Sequro Technologies Pvt Ltd.";
						
						//Commented on 11 Oct 2023
						
						//EmailTemplate emailTemplate = Utils.getEmailtemplate(customerIDCreation.getStrEmailID(),"Regarding to Customer Id", bodyMessage);
						//emailTemplate.setStrBcc("ssoni@sequrotechnologies.com");

						//accountManagementService.sendMail(emailTemplate);
					} else {
						model.addAttribute("error", "Customer Creation Failed !!");
					}
				} else {
					model.addAttribute("error", "Customer Creation Failed !");
				}
			} else {
				model.addAttribute("error", "Customer Creation Failed..");
			}
			// Added by Sagar to handler duplicate Entry End
		} catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
			e.printStackTrace();
		}
		return "customerIdCreationConfig";
	}

	@RequestMapping(value = { "/viewCustomerAccounts" }, method = { RequestMethod.GET })
	public String viewCustomerAccounts(Model model, CustomerIdCreation customerIDCreation) {
		String methodName = "viewCustomerAccounts";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_CUSTOMER_ACCOUNTS);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.CustomerAccounts.VIEW_CUSTOMER_ACCOUNTS);
			// model.addAttribute("customerIDCreation", customerIDCreation);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewCustomerAccounts";
	}

	@RequestMapping(value = "/getCustomerDetailsbyId/{strCustId}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getCustomerDetailsbyId(@PathVariable String strCustId) {
		try {
			CustomerIdCreation customerIdCreation = new CustomerIdCreation();
			customerIdCreation.setStrCustId(strCustId);
			customerIdCreation.setStrCreatedBy(sessionDTO.getLoginID());

			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrCustId(strCustId);

			accountCreation.setStrCreatedBy(sessionDTO.getLoginID());

			List<CustomerIdCreation> custListData = accountManagementService
					.getCustomerDetailsbyCustId(customerIdCreation);

			System.out.println("custListData::::" + custListData);

			return ResponseEntity.ok(accountCreation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	// Added by Pankaj P for Customer Id creation End

	@RequestMapping(value = "/customerSearchView", method = RequestMethod.GET)
	public String customerSearchView(Model model, CustomerIdCreation customerIDCreation, HttpSession session) {
		String methodName = "customerSearchView";
		try {
			session.setAttribute("account_cust_id", "");
			model.addAttribute("customerIdCreationConfig", customerIDCreation);
			model.addAttribute("leftCustomerInfoMenuId", TranecoStatusCode.CustomerInfoService.CUSTOMER_SEARCH);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "customerSearchView";
	}

	@RequestMapping(value = "/viewCustomerInfoDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewCustomerInfoDetails(Model model, CustomerIdCreation customerIDCreation, HttpSession session) {
		String methodName = "viewCustomerInfoDetails";
		String sessionBasedCustId = "";
		try {

			String custId = customerIDCreation.getStrCustId();
			if (session.getAttribute("account_cust_id") != null) {
				sessionBasedCustId = (String) session.getAttribute("account_cust_id");
				if (custId == null) {
					customerIDCreation = (CustomerIdCreation) session.getAttribute(sessionBasedCustId);
				} else if (custId.equalsIgnoreCase(sessionBasedCustId)) {
					customerIDCreation = (CustomerIdCreation) session.getAttribute(sessionBasedCustId);
				}
			}

			if (custId != null && customerIDCreation.getStrID() == null) {
				customerIDCreation = accountManagementService.getCustMstrDetails(customerIDCreation);
				session.setAttribute("account_cust_id", custId);
				session.setAttribute(custId, customerIDCreation);
			}
			if (customerIDCreation != null && customerIDCreation.getStrID() != null) {
				StringBuilder customerName = new StringBuilder();
				if (customerIDCreation.getStrFirstName() != null) {
					customerName.append(customerIDCreation.getStrFirstName());
				}
				if (customerIDCreation.getStrMiddleName() != null) {
					customerName.append(" ").append(customerIDCreation.getStrMiddleName());
				}
				if (customerIDCreation.getStrLastName() != null) {
					customerName.append(" ").append(customerIDCreation.getStrLastName());
				}
				customerIDCreation.setStrAccountHolderName(customerName + "");
			}

			if (customerIDCreation == null) {
				customerIDCreation = new CustomerIdCreation();
			}
			model.addAttribute("customerIdCreationConfig", customerIDCreation);

			System.out.println(session);
			model.addAttribute("leftCustomerInfoMenuId", TranecoStatusCode.CustomerInfoService.CUSTOMER_DETAILS);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewCustomerInfoDetails";
	}

	@RequestMapping(value = "/viewCustomerAccountDetails", method = RequestMethod.GET)
	public String viewCustomerAccountDetails(Model model, CustomerIdCreation customerIDCreation, HttpSession session) {
		String methodName = "viewCustomerAccountDetails";
		try {
			String sessionBasedCustId = (String) session.getAttribute("account_cust_id");
			System.out.println("sessionBasedCustId::" + sessionBasedCustId);

			List<AccountCreation> accountDetailsList = new ArrayList<AccountCreation>();

			if (sessionBasedCustId != null && sessionBasedCustId.trim().length() > 0) {
				customerIDCreation = (CustomerIdCreation) session.getAttribute(sessionBasedCustId);

				AccountCreation accountCreation = new AccountCreation();
				accountCreation.setStrCustId(sessionBasedCustId);

				accountDetailsList = accountManagementService.getAccountInfoListBasedOnTypes(accountCreation);
			}
			if (accountDetailsList == null) {
				accountDetailsList = new ArrayList<AccountCreation>();
			}
			model.addAttribute("accountDetailsList", accountDetailsList);

			model.addAttribute("customerIdCreationConfig", customerIDCreation);
			model.addAttribute("leftCustomerInfoMenuId", TranecoStatusCode.CustomerInfoService.ACCOUNT_DETAILS);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewCustomerAccountDetails";
	}

	@RequestMapping(value = "/viewCustomerCardDetails", method = RequestMethod.GET)
	public String viewCustomerCardDetails(Model model, CustomerIdCreation customerIDCreation, HttpSession session) {
		String methodName = "viewCustomerCardDetails";
		try {
			String sessionBasedCustId = (String) session.getAttribute("account_cust_id");
			System.out.println("sessionBasedCustId::" + sessionBasedCustId);

			List<CardAccountLinkage> cardAccountLinkages = new ArrayList<CardAccountLinkage>();
			if (sessionBasedCustId != null && sessionBasedCustId.trim().length() > 0) {
				customerIDCreation = (CustomerIdCreation) session.getAttribute(sessionBasedCustId);

				CardAccountLinkage cardAccountLinkage = new CardAccountLinkage();
				cardAccountLinkage.setStrCustId(sessionBasedCustId);

				cardAccountLinkages = accountManagementService.getLinkageCardDetailsBasedOnCustId(cardAccountLinkage);
			}
			model.addAttribute("accountCardDetailsList", cardAccountLinkages);
			model.addAttribute("customerCardDetailsConfig", customerIDCreation);
			model.addAttribute("leftCustomerInfoMenuId", TranecoStatusCode.CustomerInfoService.CARD_DETAILS);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewCustomerCardDetails";
	}

	@RequestMapping(value = "/viewCustomerAccountStatement", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewCustomerAccountStatement(Model model, CustomerIdCreation customerIDCreation,
			HttpSession session) {
		String methodName = "viewCustomerAccountStatement";
		try {
			List<AccountStatement> accountStatements = new ArrayList<AccountStatement>();
			if (customerIDCreation != null && customerIDCreation.getStrAccountType() != null) {
				AccountStatement accountStatementObj = new AccountStatement();
				accountStatementObj.setStrAccountNumber(customerIDCreation.getStrAccountNumber());
				accountStatementObj.setStrAccountType(customerIDCreation.getStrAccountType());
				accountStatementObj.setFromDate(customerIDCreation.getStrfromDate());
				accountStatementObj.setToDate(customerIDCreation.getStrtoDate());

				accountStatements = accountManagementService.getAccountStatements(accountStatementObj);
			}

			if (customerIDCreation == null) {
				customerIDCreation = new CustomerIdCreation();
			}

			model.addAttribute("customerIdCreationConfig", customerIDCreation);
			model.addAttribute("accountStatementList", accountStatements);
			model.addAttribute("leftCustomerInfoMenuId", TranecoStatusCode.CustomerInfoService.ACCOUNT_STATEMENT);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewCustomerAccountStatement";
	}

	// Added for linked account no list Start
	@RequestMapping(value = { "/nonLinkedCustomerForAccountNo" }, method = { RequestMethod.GET })
	public String getNonLinkedCustomerForAccountNo(Model model, PreAccountMaster preAccountMaster) {
		String methodName = "linkedPreAccountForm";
		try {
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.APPROVE_ACCOUNT);
			preAccountMaster.setScreenType("account");
			// model.addAttribute("screenType","account");
			model.addAttribute("title", "Customer List to Link Account Number");
			model.addAttribute("linkName", "Link Account");
			model.addAttribute("preAccountMaster", preAccountMaster);
			List<PreAccountMaster> preAccountMasters = accountManagementService
					.getNonLinkedCustomersListForAccountNo(preAccountMaster);
			model.addAttribute("preAccountList", preAccountMasters);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display Connection Configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "linkedPreAccountForm";
	}
	// Added for linked account no list End

	// added by prashant Tayde for adding jsp for add charge type and getting charge
	// List

	@RequestMapping(value = { "/addChargeType" }, method = { RequestMethod.GET })
	public String addChargeType(Model model, ChargeMaster chargeMaster) {
		String methodName = "addChargeType";
		try {
			model.addAttribute("leftChargeMenuID", TranecoStatusCode.ADD_CHARGE_TYPE);
			model.addAttribute("chargeMaster", chargeMaster);

			// model.addAttribute("chargeRelated", configurationService.getChargeRelated());
			ChargeRelatedMaster chargeRelatedMaster = new ChargeRelatedMaster();

			List<ChargeRelatedMaster> chargeRelatedMasterList = accountManagementService
					.getChargeRelatedList(chargeRelatedMaster);
			model.addAttribute("chargeRelatedMaster", chargeRelatedMasterList);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addChargeType";

	}

	// changes added by prashant Tayde for saving charge type data

	@RequestMapping(value = { "/addChargeTypeConfig" }, method = { RequestMethod.POST })
	public String addChargeTypeForm(Model model, ChargeMaster chargeMaster) {
		String methodName = "addChargeTypeConfig";
		try {
			model.addAttribute("leftChargeMenuID", TranecoStatusCode.ADD_CHARGE_TYPE);
			model.addAttribute("chargeMaster", chargeMaster);

			chargeMaster.setStrParticipantID(this.sessionDTO.getParticipantid());
			System.out.println("AmsServiceController.addChargeTypeForm()" + chargeMaster);
			chargeMaster.setStrCreatedBy(this.sessionDTO.getLoginID());

			ChargeRelatedMaster chargeRelatedMaster = new ChargeRelatedMaster();
			chargeRelatedMaster.setStrChargeRelated(chargeMaster.getStrChargeRelated());

			List<ChargeRelatedMaster> chargeRelatedMasterList = accountManagementService
					.getChargeRelatedList(chargeRelatedMaster);
			model.addAttribute("chargeRelatedMaster", chargeRelatedMasterList);

			String description = accountManagementService.getChargeRelatedDescription(chargeRelatedMaster);
			chargeMaster.setStrChargeRelatedDescription(description);

			String strErrMsg = "Charge Type Already Configured:::";

			Boolean checkChargeType = accountManagementService.validateChargeType(chargeMaster);
			System.out.println("CheckChargeType:::" + checkChargeType);
			if (checkChargeType) {
				model.addAttribute("error", strErrMsg);
				return "addChargeType";
			}

			int count = accountManagementService.addChargeType(chargeMaster);
			if (count > 0) {
				model.addAttribute("success", "Charge Type added Successfully");
				ChargeMaster chargeMaster2 = new ChargeMaster();
				model.addAttribute("chargeMaster", chargeMaster2);
			} else {
				model.addAttribute("error", "Charge Type added Failed");
			}
			model.addAttribute("display", "block");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addChargeType";

	}

	@RequestMapping(value = { "/chargingConfigForm" }, method = { RequestMethod.GET })
	public String chargingConfigForm(Model model, AccountTypeCharges accountTypeCharges) {
		String methodName = "chargingConfigForm";
		try {
			ChargeMaster chargeMaster = new ChargeMaster();
			model.addAttribute("leftChargeMenuID", TranecoStatusCode.CHARGING_MODULE_CONFIG);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			List<ChargeMaster> cardChargeMstrList = this.configurationService.getChargeMasterList(chargeMaster);
			model.addAttribute("cardChargeList", cardChargeMstrList);
			model.addAttribute("cardChargeListJson", this.mapper.writeValueAsString(cardChargeMstrList));

			List<ChargeMaster> txnChargeMstrList = this.configurationService.getTransactionChargeList(chargeMaster);
			model.addAttribute("transactionChargeList", txnChargeMstrList);
			model.addAttribute("transactionChargeListJson", this.mapper.writeValueAsString(txnChargeMstrList));

			List<ChargeMaster> fuelChargeMstr = this.configurationService.getFuelChargeList(chargeMaster);
			model.addAttribute("fuelChargeList", fuelChargeMstr);
			model.addAttribute("fuelChargeListJson", this.mapper.writeValueAsString(fuelChargeMstr));

			/*
			 * AccountTypeCharges accountTypeCharges = new AccountTypeCharges();
			 * List<AccountTypeCharges> acListData =
			 * configurationService.getSelectedChargesAccountTypeWise(accountTypeCharges);
			 * 
			 * model.addAttribute("chargeList", acListData);
			 * model.addAttribute("chargeListJson",
			 * this.mapper.writeValueAsString(acListData));
			 */

		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "chargingConfigForm";
	}

	// Added by prashant tayde for charging module configuration
	@RequestMapping(value = { "/chargingConfig" }, method = { RequestMethod.POST })
	public String chargingConfig(Model model, AccountTypeCharges accountTypeCharges) {
		String methodName = "chargingConfigForm";
		try {
			ChargeMaster chargeMaster = new ChargeMaster();
			String strChargeType = "";
			model.addAttribute("leftChargeMenuID", TranecoStatusCode.CHARGING_MODULE_CONFIG);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			List<ChargeMaster> cardChargeMstrList = this.configurationService.getChargeMasterList(chargeMaster);
			model.addAttribute("cardChargeList", cardChargeMstrList);
			model.addAttribute("cardChargeListJson", this.mapper.writeValueAsString(cardChargeMstrList));

			List<ChargeMaster> txnChargeMstrList = this.configurationService.getTransactionChargeList(chargeMaster);
			model.addAttribute("transactionChargeList", txnChargeMstrList);
			model.addAttribute("transactionChargeListJson", this.mapper.writeValueAsString(txnChargeMstrList));

			List<ChargeMaster> fuelChargeMstr = this.configurationService.getFuelChargeList(chargeMaster);
			model.addAttribute("fuelChargeList", fuelChargeMstr);
			model.addAttribute("fuelChargeListJson", this.mapper.writeValueAsString(fuelChargeMstr));
			/*
			 * if(accountTypeCharges.getStrSelectedCardChargeType()!=null &&
			 * accountTypeCharges.getStrSelectedTxnChargeType()!= null &&
			 * accountTypeCharges.getStrSelectedFuelChargeType()!= null) { strChargeType =
			 * accountTypeCharges.getStrSelectedCardChargeType() +
			 * ","+accountTypeCharges.getStrSelectedTxnChargeType()
			 * +","+accountTypeCharges.getStrSelectedFuelChargeType(); } else if
			 * (accountTypeCharges.getStrSelectedCardChargeType()!=null &&
			 * accountTypeCharges.getStrSelectedTxnChargeType()!= null) { strChargeType =
			 * accountTypeCharges.getStrSelectedCardChargeType() +
			 * ","+accountTypeCharges.getStrSelectedTxnChargeType(); } else if
			 * (accountTypeCharges.getStrSelectedCardChargeType()!=null &&
			 * accountTypeCharges.getStrSelectedFuelChargeType()!= null) { strChargeType =
			 * accountTypeCharges.getStrSelectedCardChargeType() +
			 * ","+accountTypeCharges.getStrSelectedFuelChargeType(); } else if
			 * (accountTypeCharges.getStrSelectedTxnChargeType()!= null &&
			 * accountTypeCharges.getStrSelectedFuelChargeType()!= null) { strChargeType =
			 * accountTypeCharges.getStrSelectedTxnChargeType()
			 * +","+accountTypeCharges.getStrSelectedFuelChargeType(); } else {
			 * if(accountTypeCharges.getStrSelectedCardChargeType()!=null) { strChargeType =
			 * accountTypeCharges.getStrSelectedCardChargeType(); } else if
			 * (accountTypeCharges.getStrSelectedTxnChargeType()!= null) { strChargeType =
			 * accountTypeCharges.getStrSelectedTxnChargeType(); } else { strChargeType =
			 * accountTypeCharges.getStrSelectedFuelChargeType(); } }
			 * 
			 * int lengthofChargeType = 0; int lengthOfAmount = 0;
			 * 
			 * if (strChargeType != null && strChargeType.indexOf(",") != -1) { String[]
			 * chargeTypeArr = strChargeType.split(","); lengthofChargeType =
			 * chargeTypeArr.length; } else { lengthofChargeType = 1; }
			 * 
			 * String amtStr = accountTypeCharges.getStrAmount(); if(amtStr!= null &&
			 * amtStr.lastIndexOf(",")!=-1) { String[] amountArr = amtStr.split(",");
			 * lengthOfAmount = amountArr.length; }
			 */

			// if (lengthofChargeType == lengthOfAmount) {

			accountTypeCharges.setStrChargeType(strChargeType);

			accountTypeCharges.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeCharges.setStrCreatedBy(sessionDTO.getLoginID());

			accountTypeCharges = this.configurationService.addChargingConfigData(accountTypeCharges);

			if (accountTypeCharges.getStrId() != null) 
			{
				model.addAttribute("success", "Charges Added Successfully");
				AccountTypeCharges accountTypeCharges2 = new AccountTypeCharges();
				model.addAttribute("accountTypeCharges", accountTypeCharges2);
			} else {
				model.addAttribute("error", "Failed to Save:::");
			}
			/*
			 * } else { model.addAttribute("error",
			 * "AMOUNT NOT SELECTED. PLEASE SELECT CHARGES AMOUNT::"); }
			 */
			/*
			 * model.addAttribute("accountType",
			 * this.configurationService.getAccountTypeMasterDataList());
			 * model.addAttribute("cardChargeList",
			 * this.configurationService.getChargeMasterList(chargeMaster));
			 * model.addAttribute("transactionChargeList",
			 * this.configurationService.getTransactionChargeList(chargeMaster));
			 * model.addAttribute("fuelChargeList",
			 * this.configurationService.getFuelChargeList(chargeMaster));
			 * List<AccountTypeCharges> acListData =
			 * configurationService.getSelectedChargesAccountTypeWise(accountTypeCharges);
			 * model.addAttribute("chargeList", acListData);
			 * model.addAttribute("chargeListJson",
			 * this.mapper.writeValueAsString(acListData));
			 */
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "chargingConfigForm";
	}

	@RequestMapping(value = { "/viewRevolvingCreditCardForm" }, method = { RequestMethod.GET })
	public String revolvingCreditCardForm(Model model, RevolvingCreditCardMaster revolvingCreditCardMaster) {
		String methodName = "revolvingCreditCardForm";
		try {
			model.addAttribute("leftcreditCardMenuID", TranecoStatusCode.REVOLVING_CREDIT_CARD);
			model.addAttribute("revolvingCreditCardMaster", revolvingCreditCardMaster);

			AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
			accountTypeMaster.setStrIsCreditType("Y");

			List<AccountTypeMaster> accountTypeMasterList = accountManagementService
					.getAccountTypeForIsCredit(accountTypeMaster);
			model.addAttribute("creditAccountType", accountTypeMasterList);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewRevolvingCreditCardForm";

	}

	@RequestMapping(value = { "/addRevolvingCreditCardForm" }, method = { RequestMethod.POST })
	public String addRevolvingCreditCardForm(Model model, RevolvingCreditCardMaster revolvingCreditCardMaster) {
		String methodName = "addRevolvingCreditCardForm";
		try {
			model.addAttribute("leftcreditCardMenuID", TranecoStatusCode.REVOLVING_CREDIT_CARD);
			model.addAttribute("revolvingCreditCardMaster", revolvingCreditCardMaster);

			revolvingCreditCardMaster.setStrParticipantID(this.sessionDTO.getParticipantid());
			revolvingCreditCardMaster.setStrCreatedBy(this.sessionDTO.getLoginID());

			boolean isRevolvingExist = accountManagementService.validateRevolvingData(revolvingCreditCardMaster);
			if (isRevolvingExist) {
				model.addAttribute("error", "Already Configured - Failed.");
			} else {
				if (revolvingCreditCardMaster.getStrMadRate() != null
						&& revolvingCreditCardMaster.getStrMadRate().trim().length() > 0) {
					String madRate = revolvingCreditCardMaster.getStrMadRate().substring(0,
							revolvingCreditCardMaster.getStrMadRate().lastIndexOf("%"));
					revolvingCreditCardMaster.setStrMadRate(madRate);
				}
				if (revolvingCreditCardMaster.getStrInterestRate() != null
						&& revolvingCreditCardMaster.getStrInterestRate().trim().length() > 0) {
					String intrestRate = revolvingCreditCardMaster.getStrInterestRate().substring(0,
							revolvingCreditCardMaster.getStrInterestRate().lastIndexOf("%"));
					revolvingCreditCardMaster.setStrInterestRate(intrestRate);
				}
				if (revolvingCreditCardMaster.getStrPenultyInterestRate() != null
						&& revolvingCreditCardMaster.getStrPenultyInterestRate().trim().length() > 0) {
					String penultyRate = revolvingCreditCardMaster.getStrPenultyInterestRate().substring(0,
							revolvingCreditCardMaster.getStrPenultyInterestRate().lastIndexOf("%"));
					revolvingCreditCardMaster.setStrPenultyInterestRate(penultyRate);
				}

				int count = accountManagementService.addRevolvingCreditCardData(revolvingCreditCardMaster);
				if (count > 0) {
					AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
					accountTypeMaster.setStrAccountType(revolvingCreditCardMaster.getStrAccountType());
					accountManagementService.updateIsRevolvingCredit(accountTypeMaster);

					model.addAttribute("success", "Data added Successfully");

					RevolvingCreditCardMaster revolvingCreditCardMasterObj = new RevolvingCreditCardMaster();
					model.addAttribute("revolvingCreditCardMaster", revolvingCreditCardMasterObj);
				} else {
					model.addAttribute("error", "Data added Failed");
				}
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewRevolvingCreditCardForm";
	}

	@RequestMapping(value = { "/addChargeRelatedConfig" }, method = { RequestMethod.GET })
	public String addChargeRelatedConfig(Model model, ChargeRelatedMaster chargeRelatedMaster) {
		String methodName = "addChargeRelatedConfig";
		try {
			model.addAttribute("leftChargeMenuID", TranecoStatusCode.ADD_CHARGE_RELATED);
			model.addAttribute("chargeRelatedMaster", chargeRelatedMaster);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addChargeRelatedConfig";

	}

	@RequestMapping(value = { "/chargeRelatedConfig" }, method = { RequestMethod.POST })
	public String chargeRelatedConfig(Model model, ChargeRelatedMaster chargeRelatedMaster) {
		String methodName = "chargeRelatedConfig";
		try {
			model.addAttribute("leftChargeMenuID", TranecoStatusCode.ADD_CHARGE_RELATED);
			model.addAttribute("chargeRelatedMaster", chargeRelatedMaster);

			String strErrMsg = "Duplicate Charge Related!!";

			Boolean isChargeRelatedExist = accountManagementService.validateChargeRelated(chargeRelatedMaster);
			if (isChargeRelatedExist) {
				model.addAttribute("error", strErrMsg);
				return "addChargeRelatedConfig";
			}

			int count = accountManagementService.addChargeRelatedData(chargeRelatedMaster);
			if (count > 0) {
				ChargeRelatedMaster chargeRelatedMasterObj = new ChargeRelatedMaster();
				model.addAttribute("chargeRelatedMaster", chargeRelatedMasterObj);
				model.addAttribute("success", "Charge Related added Successfully");
			} else {
				model.addAttribute("error", "Charge Related added Failed");
			}

		} catch (Exception e) {
			model.addAttribute("display", "block");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "addChargeRelatedConfig";
	}

	// Add by Abhishek T to view mcc_wise Table-start
	@RequestMapping(value = "/MccWiseInterestForm", method = RequestMethod.GET)
	public String MccWiseInterestForm(Model model, MccWiseInterestModel mccWiseInterestModel) {
		model.addAttribute("leftcreditCardMenuID", TranecoStatusCode.MCC_WISE_INTERESTVIEW);
		String methodName = "MccWiseInterestForm";
		try {
			List<MccWiseInterestModel> mccWiseInterestModels = accountManagementService
					.getmccwisedata(mccWiseInterestModel);
			model.addAttribute("MccWiseInterestview", mccWiseInterestModels);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "MccWiseInterestForm";

	}

	@RequestMapping(value = "/glCreationTable", method = RequestMethod.GET)
	public String glCreationTable(Model model, GLAccountTypeMaster glAccountviewModel) {
		// model.addAttribute("leftGLAccountMenuID",
		// TranecoStatusCode.VIEW_GLACCOUNT_CREATION );
		model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.VIEW_GLACCOUNT_CREATION);
		String methodName = "glCreationTable";
		try {
			List<GLAccountTypeMaster> glAccountviewModels = accountManagementService
					.getGLAccountcreationdata(glAccountviewModel);
			model.addAttribute("glAccountview", glAccountviewModels);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "glCreationTable";

	}
	// Abhishek Tamrakare end

	@RequestMapping(value = { "/AccountTypeCategory" }, method = { RequestMethod.GET })
	public String AccountTypeCategory(Model model, CategoryListModel categoryListModel) {
		String methodName = "AccountTypeCategory";
		try {
			model.addAttribute("AccountTypeCategory", categoryListModel);
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.CATEGORY_LIST);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.CATEGORY_LIST);

			List<CategoryListModel> categoryListModels = accountManagementService
					.getCategoryTypeListData(categoryListModel);
			model.addAttribute("categoryListModels", categoryListModels);

			Categorytype categorytype = new Categorytype();
			List<Categorytype> categorytype1 = accountManagementService.getCategoryTypeList(categorytype);
			model.addAttribute("categorytype", categorytype1);
			model.addAttribute("categorytypeList",
					this.mapper.writeValueAsString(this.accountManagementService.getCategoryTypeList(categorytype)));

			// model.addAttribute("categoryDataModelList",
			// this.mapper.writeValueAsString(this.accountManagementService.getCategoryTypeListData(categoryListModel)));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "AccountTypeCategory";
	}

	@RequestMapping(value = { "/addAccountTypeCategory" }, method = { RequestMethod.POST })
	public String addAccountTypeCategory(Model model, CategoryListModel categoryListModel) {
		try {
			model.addAttribute("AccountTypeCategory", categoryListModel);
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.CATEGORY_LIST);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.CATEGORY_LIST);

			boolean isAlreadyExist = accountManagementService.isTypeAlreadyExist(categoryListModel);

			if (!isAlreadyExist) {
				categoryListModel.setStrParticipantID(sessionDTO.getParticipantid());
				// categoryListModel.setStrCategoryType(categoryListModel.getStrType());//Added
				// to handle Issue while creating account Type
				categoryListModel = accountManagementService.saveCategoryListModel(categoryListModel);
				if (categoryListModel != null && categoryListModel.getId() > 0) {
					model.addAttribute("success", "Successfully created.");
					CategoryListModel categoryListModelObj = new CategoryListModel();
					model.addAttribute("categoryListModel", categoryListModelObj);
				}
			} else {
				model.addAttribute("error", "Duplicate Category Type. Account Already Exist!");
				model.addAttribute("categoryListModel", categoryListModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AccountTypeCategory";
	}

	// Added by Abhishek T for AccountTypeCategory on 17-03-2023 End
	// create Transaction Type Creation By SagarK Start
	@RequestMapping(value = "/transactionType", method = RequestMethod.GET)
	public String txnTypeCreatation(Model model, TransactionTypeModel transactionTypeModel) {
		String methodName = "txnTypeCreation";
		try {
			model.addAttribute("transactionTypeModel", transactionTypeModel);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.TRANSACTION_TYPE);
			transactionTypeModel.setStrParticipantId(sessionDTO.getParticipantid());

			// Added By Prashant T 29-AUG-2023
			GLAccountCreation glAccountCreation = new GLAccountCreation();
			List<GLAccountCreation> glAccountCreations = accountManagementService
					.getGlAccountTypeAccountNoListForThirdPartyY(glAccountCreation);
			model.addAttribute("glAccountTypeListY", glAccountCreations);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "txnTypeCreation";
	}

	@RequestMapping(value = "/addTransactionType", method = RequestMethod.POST)
	public String addTxnTypeData(Model model, TransactionTypeModel transactionTypeModel) {
		String methodName = "txnTypeCreation";
		try {
			model.addAttribute("transactionTypeModel", transactionTypeModel);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.TRANSACTION_TYPE);
			transactionTypeModel.setStrParticipantId(sessionDTO.getParticipantid());
			
			if (transactionTypeModel.getStrGLAccountType() != null
					&& transactionTypeModel.getStrGLAccountType().indexOf("-") != -1) 
			{
				String glAccType[] = transactionTypeModel.getStrGLAccountType().split("-");
				transactionTypeModel.setStrGLAccountType(glAccType[0].trim());
				transactionTypeModel.setStrGLAccountNumber(glAccType[1].trim());
			}
			
				Boolean isTransactionTypeExist = accountManagementService.isTransactionTypeAlreadyExist(transactionTypeModel);
				if(isTransactionTypeExist)
				{
					model.addAttribute("error", "Transaction type already configured!");
					GLAccountCreation glAccountCreation = new GLAccountCreation();
					List<GLAccountCreation> glAccountCreations = accountManagementService.getGlAccountTypeAccountNoListForThirdPartyY(glAccountCreation);
					model.addAttribute("glAccountTypeListY", glAccountCreations);
					return "txnTypeCreation";
				}
				Boolean isGLAccountTypeExist = accountManagementService.isGLAccountTypeExist(transactionTypeModel);
				if(isGLAccountTypeExist)
				{
					model.addAttribute("error", "GL Account Type already Map to Another Transaction Type, Please Choose Another!");
					GLAccountCreation glAccountCreation = new GLAccountCreation();
					List<GLAccountCreation> glAccountCreations = accountManagementService.getGlAccountTypeAccountNoListForThirdPartyY(glAccountCreation);
					model.addAttribute("glAccountTypeListY", glAccountCreations);
					return "txnTypeCreation";
				}
				int count = configurationService.addTranscationTypeData(transactionTypeModel);
				if (count > 0) 
				{
					TransactionTypeModel transactionTypeData = accountManagementService.addTranscationTypeData(transactionTypeModel);
					if (transactionTypeData != null && transactionTypeData.getStrID() != null) 
					{
						model.addAttribute("success", "Successfully Data Added.");
						TransactionTypeModel transactionTypeModelObj = new TransactionTypeModel();
						model.addAttribute("transactionTypeModel", transactionTypeModelObj);
					} 
					else 
					{
						model.addAttribute("error", "Error during adding data!!.");
					}
				} 
				else 
				{
					model.addAttribute("error", "Error during adding data!");
				}
			GLAccountCreation glAccountCreation = new GLAccountCreation();
			List<GLAccountCreation> glAccountCreations = accountManagementService.getGlAccountTypeAccountNoListForThirdPartyY(glAccountCreation);
			model.addAttribute("glAccountTypeListY", glAccountCreations);
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "txnTypeCreation";
	}
	// create Transaction Type Creation By SagarK End

	@RequestMapping(value = "/viewtransactionTypes", method = RequestMethod.GET)
	public String viewtransactionTypes(Model model, TransactionTypeModel transactionTypeModel) {
		String methodName = "viewtransactionTypes";
		try 
		{
			model.addAttribute("transactionTypeModel", transactionTypeModel);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.VIEW_TRANSACTION_TYPE);
			List<TransactionTypeModel> transactionTypeModels = accountManagementService
					.getTransactionTypeModelData(transactionTypeModel);
			model.addAttribute("viewTransactionTypeModel", transactionTypeModels);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewtransactionTypes";
	}

	// Added by prashant Tayde view customer account details
	@RequestMapping(value = { "/viewCustomerAccountsDetails" }, method = { RequestMethod.GET })
	public String viewCustomerAccountsDetails(Model model, CustomerIdCreation customerIdCreation) {
		String methodName = "viewCustomerAccountsDetails";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_CUSTOMER_ACCOUNTS);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.VIEW_CUSTOMER_ACCOUNTS);
			model.addAttribute("customerIDCreation", customerIdCreation);
			model.addAttribute("customerAccDetailsList",
					accountManagementService.getCustomerAccountDetails(customerIdCreation));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewCustomerAccountsDetails";
	}

	// Added by prashant Tayde view edit account details
	@RequestMapping(value = { "/editCustomerAccountsDetails" }, method = { RequestMethod.GET })
	public String editCustomerAccountsDetails(Model model, CustomerIdCreation customerIdCreation) {
		String methodName = "editCustomerAccountsDetails";
		try {
			log.doLog(4, className, methodName, sessionDTO.getParticipantid());
			String participantId = sessionDTO.getParticipantid();
			System.out.println("participantId::" + participantId);

			CustomerIdCreation customerIdCreation2 = accountManagementService
					.getCustomerAccountDetailsBasedOnCustId(customerIdCreation).get(0);
			customerIdCreation.setStrFirstName(customerIdCreation2.getStrFirstName());

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			customerIdCreation.setStrAddressProofDocumentId(addressProofDocumentTypeMaster.getStrDocumentType());
			customerIdCreation.setStrAddressProofDocumentValue(addressProofDocumentTypeMaster.getStrDocumentDescr());

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);
			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			customerIdCreation.setStrIdentityProofDocumentId(identityProofDocumentTypeMaster.getStrDocumentType());
			customerIdCreation.setStrIdentityProofDocumentValue(identityProofDocumentTypeMaster.getStrDocumentDescr());
			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("stateList", configurationService.getStateData(customerIdCreation2.getStrCountry()));
			model.addAttribute("cityList", configurationService.getCityData(customerIdCreation2.getStrState()));
			// customerIdCreation2.setStrCountryCode("16");
			String strCountryCode = (customerIdCreation2.getStrCountry() != null) ? customerIdCreation2.getStrCountry()
					: "NA";
			String strState = (customerIdCreation2.getStrState() != null) ? customerIdCreation2.getStrState() : "NA";
			String strCity = (customerIdCreation2.getStrCity() != null) ? customerIdCreation2.getStrCity() : "NA";

			model.addAttribute("display", "block");
			model.addAttribute("strCountryCode", strCountryCode);
			model.addAttribute("strState", strState);
			model.addAttribute("strCity", strCity);

			model.addAttribute("customerIdCreation", customerIdCreation2);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "editCustomerAccountsDetails";
	}

	@RequestMapping(value = "/updateEditedCustomerAccountDetails", method = RequestMethod.POST)
	public String updateEditedCusAccDetails(Model model, CustomerIdCreation customerIdCreation) {
		String methodName = "UpdatecustomerAccountDetails";
		try {
			log.doLog(4, className, methodName, sessionDTO.getParticipantid());

			String participantId = sessionDTO.getParticipantid();
			customerIdCreation.setStrParticipantID(participantId);

			int count = accountManagementService.updateCustomerAccountDetails(customerIdCreation);
			if (count > 0) {
				model.addAttribute("success", "Account Details Updated Successfully");
			} else {
				model.addAttribute("error", "failed to save");
			}
			CustomerIdCreation customerIdCreation2 = new CustomerIdCreation();
			model.addAttribute("customerIdCreation", customerIdCreation2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "redirect:viewCustomerAccountsDetails";
	}

	// Added by pankaj pawar for charts of accounts menu [Start]
	@RequestMapping(value = { "/chartsAccounts" }, method = { RequestMethod.GET })
	public String ChartsoFAccounts(Model model, GLAccountTypeMaster gLAccountTypeMaster) {
		String methodName = "chartsAccounts";
		try {
			// model.addAttribute("leftAccountMenuID",
			// TranecoStatusCode.VIEW_CUSTOMER_ACCOUNTS);
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.ReportStatement.CHART_ACCOUNTS);
			model.addAttribute("gLAccountTypeMaster", gLAccountTypeMaster);
			System.out.println("gLAccountTypeMaster:::" + gLAccountTypeMaster);

			List<GLAccountTypeMaster> glAccountTypeMasters = accountManagementService
					.getGLAccountDetailsList(gLAccountTypeMaster);
			model.addAttribute("glAccountDetails", glAccountTypeMasters);

			model.addAttribute("glAccountDetailsJson", this.mapper.writeValueAsString(glAccountTypeMasters));

			GLAccountCreation glAccountCreation = new GLAccountCreation();
			glAccountCreation = accountManagementService.getAllGLAccountBalanceTogether(glAccountCreation);

			if (glAccountCreation != null && glAccountCreation.getStrAllGLBalance() != null) {
				gLAccountTypeMaster.setStrAllGLBalance(glAccountCreation.getStrAllGLBalance());
			}

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "chartsAccounts";
	}
	// Added by pankaj pawar for charts of accounts menu [End]

	// Added by ankit on 05-04-2023-- start
	// GLTOGL -start-
	@RequestMapping(value = { "/journalTransfer" }, method = { RequestMethod.GET })
	private String journalTransfer(Model model, JournalTransfer journalTransfer) {
		try {
			model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.GT_TO_GL);
			model.addAttribute("journalTransfer", journalTransfer);

			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("accountType", glAccountTypeList);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransfer";
	}

	@RequestMapping(value = { "/journalTransfer" }, method = { RequestMethod.POST })
	private String journalTransferSubmitGLToGL(Model model, JournalTransfer journalTransfer) {
		try {
			model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.GT_TO_GL);
			model.addAttribute("journalTransfer", journalTransfer);

			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("accountType", glAccountTypeList);

			journalTransfer.setStrMakerId(sessionDTO.getLoginID());
			journalTransfer.setTxnJournalTransferType("G2G");
			String journalTransferDetails = accountManagementService.journalTransfer(journalTransfer);
			if (journalTransferDetails != null) {
				model.addAttribute("success", "Success: Journal Transfer Added");
				JournalTransfer journalTransferNew = new JournalTransfer();
				model.addAttribute("journalTransfer", journalTransferNew);
			} else {
				model.addAttribute("error", "Error: Error Occured During Add!!");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Exception occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransfer";
	}
	// GLTOGL -end-

	// GL To Acc -start-
	@RequestMapping(value = { "/journalTransferGLToAcc" }, method = { RequestMethod.GET })
	private String journalTransferGLToAcc(Model model, JournalTransfer journalTransfer) {
		try {
			model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.GT_TO_ACCOUNT);
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			List<AccountTypeMaster> accountTypeMasterList = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountType", glAccountTypeList);
			model.addAttribute("accAccountType", accountTypeMasterList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferGLToAcc";
	}

	@RequestMapping(value = { "/journalTransferGLToAcc" }, method = { RequestMethod.POST })
	private String journalTransferSubmitGLToAcc(Model model, JournalTransfer journalTransfer) {
		model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.GT_TO_ACCOUNT);
		try {
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("accountType", glAccountTypeList);

			List<AccountTypeMaster> accountTypeMasterList = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accAccountType", accountTypeMasterList);

			journalTransfer.setStrMakerId(sessionDTO.getLoginID());
			journalTransfer.setTxnJournalTransferType("G2A");
			String journalTransferDetails = accountManagementService.journalTransfer(journalTransfer);
			if (journalTransferDetails != null) {
				model.addAttribute("success", "Success: Journal Transfer Added");
				JournalTransfer journalTransferNew = new JournalTransfer();
				model.addAttribute("journalTransfer", journalTransferNew);
			} else {
				model.addAttribute("error", "Error: Error Occured During Add!!");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferGLToAcc";
	}
	// GL To Acc -end-

	// Acc To GL-start-
	@RequestMapping(value = { "/journalTransferAccToGL" }, method = { RequestMethod.GET })
	private String journalTransferAccToGL(Model model, JournalTransfer journalTransfer) {
		model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.ACCOUNT_TO_GL);
		try {
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			List<AccountTypeMaster> accountTypeMasterList = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountType", glAccountTypeList);
			model.addAttribute("accAccountType", accountTypeMasterList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferAccToGL";
	}

	@RequestMapping(value = { "/journalTransferAccToGL" }, method = { RequestMethod.POST })
	private String journalTransferSubmitAccToGL(Model model, JournalTransfer journalTransfer) {
		model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.ACCOUNT_TO_GL);
		try {
			journalTransfer.setStrMakerId(sessionDTO.getLoginID());
			journalTransfer.setTxnJournalTransferType("A2G");
			String journalTransferDetails = accountManagementService.journalTransfer(journalTransfer);
			if (journalTransferDetails != null) {
				model.addAttribute("success", "Success: Journal Transfer Added");
				JournalTransfer journalTransferNew = new JournalTransfer();
				model.addAttribute("journalTransfer", journalTransferNew);
			} else {
				model.addAttribute("error", "Error: Error Occured During Add!!");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferAccToGL";
	}
	// Acc To GL -end-

	// Acc To Acc -start-
	@RequestMapping(value = { "/journalTransferAccToAcc" }, method = { RequestMethod.GET })
	private String journalTransferAccToAcc(Model model, JournalTransfer journalTransfer) {
		model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.ACCOUNT_TO_ACCOUNT);
		try {
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			List<AccountTypeMaster> accountTypeMasterList = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountType", glAccountTypeList);
			model.addAttribute("accAccountType", accountTypeMasterList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferAccToAcc";
	}

	@RequestMapping(value = { "/journalTransferAccToAcc" }, method = { RequestMethod.POST })
	private String journalTransferSubmitAccToAcc(Model model, JournalTransfer journalTransfer) {
		model.addAttribute("leftJournalTransferMenuID", TranecoStatusCode.JournalTransfer.ACCOUNT_TO_ACCOUNT);
		try {
			journalTransfer.setStrMakerId(sessionDTO.getLoginID());
			journalTransfer.setTxnJournalTransferType("A2A");
			String journalTransferDetails = accountManagementService.journalTransfer(journalTransfer);
			if (journalTransferDetails != null) {
				model.addAttribute("success", "Success: Journal Transfer Added");
				JournalTransfer journalTransferNew = new JournalTransfer();
				model.addAttribute("journalTransfer", journalTransferNew);
			} else {
				model.addAttribute("error", "Error: Error Occured During Add!!");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Journal Transfer!");
			log.doLog(3, className, "xyz", LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferAccToAcc";
	}
	// Acc To Acc -end-

	// to get the available balance of GL Account
	@RequestMapping(value = { "/getGLAvailableBalance" }, method = { RequestMethod.POST })
	private ResponseEntity<?> getGLAvailableBalance(@RequestBody GLAccountTypeMaster glAccountTypeMaster) {
		try {
			List<GLAccountTypeMaster> glAvailableBalnce = accountManagementService
					.getGLAvailableBalnce(glAccountTypeMaster);
			if (glAvailableBalnce.size() > 0) {
				GLAccountTypeMaster glAccountTypeMaster2 = glAvailableBalnce.get(0);
				String strStatus = glAccountTypeMaster2.getStrStatus();
				return ResponseEntity.ok(glAccountTypeMaster2);
			} else {
				return ResponseEntity.ok(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/getAvailableBalanceOfAccount" }, method = { RequestMethod.POST })
	private ResponseEntity<?> getAvailableBalanceOfAccount(@RequestBody AccountCreation accountCreation) {
		try {
			List<AccountCreation> availableBalnceOfAccount = accountManagementService
					.getAvailableBalnceOfAccount(accountCreation);
			if (availableBalnceOfAccount.size() > 0) {
				AccountCreation accountCreation2 = availableBalnceOfAccount.get(0);
				return ResponseEntity.ok(accountCreation2);
			} else {
				return ResponseEntity.ok(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/getAccDescription" }, method = { RequestMethod.POST })
	private ResponseEntity<?> getDescription(@RequestBody AccountTypeMaster accountTypeMaster) {
		try {
			List<AccountTypeMaster> strDescription = accountManagementService.getStrAccDescription(accountTypeMaster);
			if (strDescription.size() > 0) {
				AccountTypeMaster accountTypeMaster2 = strDescription.get(0);
				return ResponseEntity.ok(accountTypeMaster2);
			} else {
				return ResponseEntity.ok(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/getGLDescription" }, method = { RequestMethod.POST })
	private ResponseEntity<?> getGLDescription(@RequestBody GLAccountTypeMaster glAccuntTypeMaster) {
		try {
			List<GLAccountTypeMaster> strDescription = accountManagementService.getStrGLDescription(glAccuntTypeMaster);
			if (strDescription.size() > 0) {
				GLAccountTypeMaster glAccountTypeMaster = strDescription.get(0);
				return ResponseEntity.ok(glAccountTypeMaster);
			} else {
				return ResponseEntity.ok(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Added by ankit on 05-04-2023-- end

	@RequestMapping(value = { "/authoriseAccounts" }, method = { RequestMethod.GET })
	public String getPendingListForAuthorization(Model model, JournalTransfer journalTransfer) {
		String methodName = "authoriseAccounts";
		try {
			model.addAttribute("journalTransfer", journalTransfer);

			journalTransfer.setLoginUserCode(sessionDTO.getLoginID());
			List<JournalTransfer> accountCreationAuthors = accountManagementService
					.getAccountAuthoriseDetailsList(journalTransfer);
			model.addAttribute("journalTransferView", accountCreationAuthors);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "authoriseAccounts";
	}

	@RequestMapping(value = { "/authoriseAccounts" }, method = { RequestMethod.POST })
	public String authoriseAccounts(Model model, JournalTransfer journalTransfer) {
		String methodName = "authoriseAccounts";
		try {
			model.addAttribute("journalTransfer", journalTransfer);

			journalTransfer.setStrCheckerId(sessionDTO.getLoginID());
			if ("A".equalsIgnoreCase(journalTransfer.getStrTxnStatus())) {
				journalTransfer.setStrTxnStatus("approved");
				accountManagementService.processToApproveJournalTransfer(journalTransfer);
			} else {
				journalTransfer.setStrTxnStatus("rejected");
				accountManagementService.processToRejectJournalTransfer(journalTransfer);
			}
			journalTransfer.setLoginUserCode(sessionDTO.getLoginID());
			List<JournalTransfer> accountCreationAuthors = accountManagementService
					.getAccountAuthoriseDetailsList(journalTransfer);
			model.addAttribute("journalTransferView", accountCreationAuthors);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "authoriseAccounts";
	}
	// Added for Control Account Related changes Start
	/*
	 * @RequestMapping(value = {"/createControlAccount"}, method= {
	 * RequestMethod.GET}) public String createControlAccount(Model model,
	 * GLAccountTypeMaster glAccountTypeMaster) { String
	 * methodName="createControlAccount"; try {
	 * model.addAttribute("GLAccountTypeMaster",glAccountTypeMaster);
	 * model.addAttribute("leftControlAccountMenuID",TranecoStatusCode.
	 * ControlAccount.CREATE_VIEW_ACCOUNT); } catch (Exception e) {
	 * model.addAttribute("display", "block"); model.addAttribute("exception",
	 * "Error occurs While add Service !"); log.doLog(3, className, methodName,
	 * LoggerUtil.getExStackTrace(e)); } return "createControlAccount"; }
	 */
	// Added for Control Account Related changes End

	// Added for Control Account Related changes Start
	@RequestMapping(value = { "/controlAccount" }, method = { RequestMethod.GET })
	public String controlAccount(Model model, GLAccountCreation glAccountCreation) {
		String methodName = "controlAccount";
		try {
			// model.addAttribute("leftGLAccountMenuID",
			// TranecoStatusCode.CONTROL_VIEW_ACCOUNT);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ControlAccount.CREATE_VIEW_ACCOUNT);
			glAccountCreation.setStrParticipantId(sessionDTO.getParticipantid());
			glAccountCreation.setStrCreatedBy(sessionDTO.getLoginID());
			glAccountCreation.setStrGLAccountType("CTR");
			GLAccountCreation gLAccountCreationResp = accountManagementService
					.isGLAccountTypeAlreadyExistDetails(glAccountCreation);
			if (gLAccountCreationResp == null) {
				String julianYear = Utils.getJulianYear();
				julianYear = "000" + julianYear.substring(2);
				String julianDay = Utils.getJulianDay();
				String strAcountNumber1 = julianYear + julianDay;
				model.addAttribute("strAcountNumber1", strAcountNumber1);
				glAccountCreation.setStrGLAcountNumber1(strAcountNumber1);

				glAccountCreation.setStrParticipantName(sessionDTO.getParticipantDesc());

				glAccountCreation.setStrGLDescription(sessionDTO.getParticipantDesc() + " Control Account");

				model.addAttribute("glAccountCreation", glAccountCreation);
				model.addAttribute("isHide", true);
			} else {
				model.addAttribute("glAccountCreation", gLAccountCreationResp);
				model.addAttribute("isHide", false);
			}

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "controlAccount";
	}

	@RequestMapping(value = { "/controlAccount" }, method = { RequestMethod.POST })
	public String addControlAccount(Model model, GLAccountCreation glAccountCreation) {
		String methodName = "controlAccount";
		String message = "";
		String glUsrEnterAccountNumber = "";
		try {
			// model.addAttribute("leftGLAccountMenuID",
			// TranecoStatusCode.CONTROL_VIEW_ACCOUNT);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ControlAccount.CREATE_VIEW_ACCOUNT);
			model.addAttribute("glAccountCreation", glAccountCreation);

			glUsrEnterAccountNumber = glAccountCreation.getStrGLAccountNumber();
			if (glUsrEnterAccountNumber != null && glUsrEnterAccountNumber.indexOf(",") != -1) 
			{
				glUsrEnterAccountNumber = glUsrEnterAccountNumber.substring(0, glUsrEnterAccountNumber.indexOf(","));
			}

			String glAccountNumber = glAccountCreation.getStrGLAcountNumber1() + glUsrEnterAccountNumber;

			System.out.println("glAccountNumber::" + glAccountNumber);

			if (glAccountCreation.getStrGLDescription() != null && glAccountCreation.getStrGLDescription().indexOf(",") != -1) 
			{
				glAccountCreation.setStrGLDescription(glAccountCreation.getStrGLDescription().substring(0,glAccountCreation.getStrGLDescription().indexOf(",")));
			}

			glAccountCreation.setStrGLAccountNumber(glAccountNumber);
			glAccountCreation.setStrParticipantId(sessionDTO.getParticipantid());
			glAccountCreation.setStrCreatedBy(sessionDTO.getLoginID());
			glAccountCreation.setStrGLAccountType("CTR");
			GLAccountCreation gLAccountCreationResp = accountManagementService
					.isGLAccountTypeAlreadyExistDetails(glAccountCreation);
			if (gLAccountCreationResp == null) 
			{
				glAccountCreation.setStrStatus("A");
				glAccountCreation.setStrOpeningBalance("0");
				glAccountCreation.setStrClosingBalance("0");
				glAccountCreation.setStrThirdPartyAllow("Y");
				GLAccountCreation glacAccountCreation = accountManagementService.addGLAccountType(glAccountCreation);
				if (glacAccountCreation != null && glacAccountCreation.getStrId() != null) 
				{
					message = "Account Created Successfully.";
					model.addAttribute("success", message);

					glacAccountCreation.setStrClosingBalance(Utils.decimalFormat.format(0));
					model.addAttribute("glAccountCreation", glacAccountCreation);
					model.addAttribute("isHide", false);
				} 
				else 
				{
					message = "Record not Saved.";
					model.addAttribute("error", message);
					glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
					model.addAttribute("isHide", true);
				}
			} else {
				model.addAttribute("glAccountCreation", gLAccountCreationResp);
				return "viewAccount";
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "controlAccount";
	}

	@RequestMapping(value = { "/transferInOut" }, method = { RequestMethod.GET })
	public String transferInOut(Model model, GLAccountCreation glAccountCreation) {
		String methodName = "transferInOut";
		try {
			// model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.TRANSFER_IN_OUT);
			model.addAttribute("leftControlAccountMenuID", TranecoStatusCode.ControlAccount.TRANSFER_IN_OUT);
			model.addAttribute("glAccountCreation", glAccountCreation);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "transferInOut";
	}

	@RequestMapping(value = { "/transferInOut" }, method = { RequestMethod.POST })
	public String transaferUpdateClosingBalance(Model model, GLAccountCreation glAccountCreation) {
		String methodName = "transferInOut";

		String message = "";
		String glUsrEnterAccountNumber = "";
		String userEnteredAmount;
		double newClosingBalance;
		try {
			// model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.TRANSFER_IN_OUT);
			model.addAttribute("leftControlAccountMenuID", TranecoStatusCode.ControlAccount.TRANSFER_IN_OUT);
			model.addAttribute("glAccountCreation", glAccountCreation);
			userEnteredAmount = glAccountCreation.getStrClosingBalance();
			glAccountCreation.setStrGLAccountType("CTR");
			glAccountCreation.setStrStatus("A");
			glAccountCreation.setStrOpeningBalance("0");
			GLAccountCreation gLAccountCreationResp = accountManagementService
					.getControlAccountTypeObj(glAccountCreation);
			if (gLAccountCreationResp != null) {
				GLAccountCreation gLAccountCreationupdated = new GLAccountCreation();
				String existingClosingBalance = gLAccountCreationResp.getStrClosingBalance();
				if ("In".equalsIgnoreCase(glAccountCreation.getStrThirdPartyAllow())) {
					gLAccountCreationupdated.setStrTransferTranType("TIN");
					newClosingBalance = Utils.stringToDouble(existingClosingBalance)
							+ Utils.stringToDouble(userEnteredAmount);
					message = "Amount Credited Successfully.";
				} else {
					gLAccountCreationupdated.setStrTransferTranType("TOT");
					newClosingBalance = Utils.stringToDouble(existingClosingBalance)
							- Utils.stringToDouble(userEnteredAmount);
					message = "Amount Debited Successfully.";
				}
				gLAccountCreationupdated.setStrClosingBalance(String.valueOf(newClosingBalance));
				gLAccountCreationupdated.setStrGLAccountType(glAccountCreation.getStrGLAccountType());
				gLAccountCreationupdated.setStrGLAccountNumber(gLAccountCreationResp.getStrGLAccountNumber());
				gLAccountCreationupdated.setStrIsThirdPartyTransfer("Y");
				gLAccountCreationupdated.setStrTransferAmount(userEnteredAmount);
				gLAccountCreationupdated.setStrParticipantId(sessionDTO.getParticipantid());

				int updateCount = accountManagementService.updateGLClosingBalance(gLAccountCreationupdated);
				if (updateCount > 0) {
					model.addAttribute("success", message);
					GLAccountCreation glAccountCreationObj = new GLAccountCreation();
					model.addAttribute("glAccountCreation", glAccountCreationObj);
				} else {
					message = "Issue while transfer Amount.";
					model.addAttribute("error", message);
					glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
				}
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			glAccountCreation.setStrGLAccountNumber(glUsrEnterAccountNumber);
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "transferInOut";
	}
	// Added for Control Account Related changes End

	/* Changes added regarding to Reports related Start */
	// Added by SagarK For showing Transaction Report [Start]

	@RequestMapping(value = { "/txnReportFrom" }, method = { RequestMethod.GET })
	public String viewTxnReportForm(Model model, TransactionTypeModel transactionTypeModel) {
		String methodName = "txnReportFrom";
		try {
			// model.addAttribute("leftAMSMenuID",
			// TranecoStatusCode.ReportStatement.VIEW_TXN_REPORT);
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.ReportStatement.VIEW_TXN_REPORT);
			List<TransactionTypeModel> txnTypelist = accountManagementService.getTxnTypeList(transactionTypeModel);
			model.addAttribute("transactionType", txnTypelist);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "txnReportFrom";
	}

	// Added for Journal Transfer Report
	@RequestMapping(value = { "/journalTransferReport" }, method = { RequestMethod.GET })
	public String JournalTransferReportlist(Model model, JournalTransfer journalTransfer) {
		String methodName = "journalTransferReport";
		try {
			// model.addAttribute("leftAMSMenuID",
			// TranecoStatusCode.ReportStatement.VIEW_JOURNAL_REPORT);
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.ReportStatement.VIEW_JOURNAL_REPORT);
			model.addAttribute("journalTransferReport", journalTransfer);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "journalTransferReport";
	}
	// Added by SagarK For showing Transaction Report [END]

	// Added by prashant Tayde
	@RequestMapping(value = { "/registerCustLinkedAccountForm" }, method = { RequestMethod.GET })
	public String registeredCustWithLinkedAcc(Model model,
			RegisteredCustWithLinkAccounts registeredCustWithLinkAccounts) {
		String methodName = "registeredCustWithLinkedAcc";
		try {
			// model.addAttribute("leftAMSMenuID",
			// TranecoStatusCode.ReportStatement.REG_CUST_WITH_LINKED_ACCOUNT);
			model.addAttribute("leftAccountStatementMenuID",
					TranecoStatusCode.ReportStatement.REG_CUST_WITH_LINKED_ACCOUNT);
			model.addAttribute("registeredCustWithLinkedAcc", registeredCustWithLinkAccounts);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "registerCustLinkedAccountForm";
	}

	@RequestMapping(value = { "/CashWithdrawalAgent" }, method = { RequestMethod.GET })
	public String CashWithdrawalAgent(Model model, DenominationMaster denominationMaster) {
		String methodName = "CashWithdrawalAgent";
		try {
			model.addAttribute("leftAccountStatementMenuID",
					TranecoStatusCode.ReportStatement.CASH_WITHDRAWAL_STATEMENT);
			// model.addAttribute("leftAMSMenuID",
			// TranecoStatusCode.ReportStatement.CASH_WITHDRAWAL_STATEMENT);
			model.addAttribute("denominationMaster", denominationMaster);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "CashWithdrawalAgent";
	}

	// CashDeposit statement
	@RequestMapping(value = { "/CashDepositAgent" }, method = { RequestMethod.GET })
	public String CashDepositAgent(Model model, DenominationMaster denominationMaster) {
		String methodName = "CashDepositAgent";
		try {
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.ReportStatement.CASH_DEPOSIT_STATEMENT);
			model.addAttribute("denominationMaster", denominationMaster);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "CashDepositAgent";
	}

	// Add by Abhishek -Start
	@RequestMapping(value = { "/viewRegisteredCustomers" }, method = { RequestMethod.GET })
	public String registeredCustomers(Model model, PreAccountMaster preAccountMaster) {
		String methodName = "viewRegisteredCustomers";
		try {
			// model.addAttribute("leftAMSMenuID",
			// TranecoStatusCode.ReportStatement.REG_CUSTOMER_REPORT);
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.ReportStatement.REG_CUSTOMER_REPORT);
			model.addAttribute("viewRegisteredCustomers", preAccountMaster);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewRegisteredCustomers";
	}

	// Add by Abhishek -End
	/* Changes added regarding to Reports related End */

	// added by prashant tayde for search transaction view Start
	@RequestMapping(value = { "/searchTransaction" }, method = { RequestMethod.GET })
	public String searchTransaction(Model model, AccountTranMaster accountTranMaster) {
		String methodName = "searchTransaction";
		try {
			model.addAttribute("AccountTranMaster", accountTranMaster);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "searchTransaction";
	}
	// added by prashant tayde for search transaction view End

	@RequestMapping(value = { "/approveUpgradeTier" }, method = { RequestMethod.GET })
	public String searchTransaction(Model model, ApproveUpgradeTier approveUpgradeTier, HttpSession session) {
		String methodName = "approveUpgradeTier";
		try {
			if (session.getAttribute("showAllRec") != null) {
				String showAllVal = (String) session.getAttribute("showAllRec");
				if (showAllVal != null && showAllVal.trim().length() > 0) {
					model.addAttribute("showAllRecords", showAllVal);
				}
			}
			model.addAttribute("ApproveUpgradeTier", approveUpgradeTier);
			model.addAttribute("leftCustomerAccountMenuID", TranecoStatusCode.CustomerAccounts.APPROVE_UPGRADE_TIER);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "approveUpgradeTier";
	}

	// Added by Sagar for Customer Id creation Start
	@RequestMapping(value = { "/upgradeCustForm" }, method = { RequestMethod.GET })
	public String upgradeCustomerForm(Model model, CustomerIdCreation customerIDCreation) {
		try {
			model.addAttribute("customerIDCreation", customerIDCreation);

			String participantId = sessionDTO.getParticipantid();

			customerIDCreation.setStrParticipantID(participantId);
			customerIDCreation.setStrCreatedBy(sessionDTO.getLoginID());

			List<AccountCreditLimitCategory> limitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(participantId);

			model.addAttribute("creditLimitCategorylist", limitCategories);
			model.addAttribute("limitCategoryStr", this.mapper.writeValueAsString(limitCategories));

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);

			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);

			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			model.addAttribute("countryList", configurationService.getCountry());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			e.printStackTrace();
		}
		return "upgradeCustomerForm";
	}

	@RequestMapping(value = { "/upgradeCustForm" }, method = { RequestMethod.POST })
	public String upgradeCustForm(Model model, CustomerIdCreation customerIDCreation, HttpSession session) {
		try {
			model.addAttribute("customerIDCreation", customerIDCreation);

			String participantId = sessionDTO.getParticipantid();

			customerIDCreation.setStrParticipantID(participantId);
			customerIDCreation.setStrCreatedBy(sessionDTO.getLoginID());

			List<AccountCreditLimitCategory> limitCategories = accountManagementService
					.getAccountCreditLimitCategoryListByParticipantWise(participantId);

			model.addAttribute("creditLimitCategorylist", limitCategories);
			model.addAttribute("limitCategoryStr", this.mapper.writeValueAsString(limitCategories));

			AddressProofDocumentTypeMaster addressProofDocumentTypeMaster = new AddressProofDocumentTypeMaster();
			addressProofDocumentTypeMaster.setStrParticipantId(participantId);

			model.addAttribute("POAList",
					accountManagementService.getAddressProofDocumentTypeMasterList(addressProofDocumentTypeMaster));

			IdentityProofDocumentTypeMaster identityProofDocumentTypeMaster = new IdentityProofDocumentTypeMaster();
			identityProofDocumentTypeMaster.setStrParticipantId(participantId);

			model.addAttribute("POIList",
					accountManagementService.getIdentityProofDocumentTypeMasters(identityProofDocumentTypeMaster));

			model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
			model.addAttribute("countryList", configurationService.getCountry());

			UpgradeTierReqRes upgradeTierReqRes = new UpgradeTierReqRes();
			upgradeTierReqRes.setStrCustId(customerIDCreation.getStrCustId());
			upgradeTierReqRes.setStrRejectedReason(customerIDCreation.getStrRejectedReason());
			upgradeTierReqRes.setStrTierType(customerIDCreation.getStrTierType());
			upgradeTierReqRes.setAction(customerIDCreation.getUpgradeAction());

			String result = accountManagementService.updateUpgradeRequest(upgradeTierReqRes);

			if (!"failed".equalsIgnoreCase(result)) {
				session.setAttribute("showAllRec", "all");
				return "redirect:approveUpgradeTier";
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			e.printStackTrace();
		}
		return "upgradeCustomerForm";
	}

	// added by ankit on 23-05-2023 -start-
	@RequestMapping(value = { "/nubanTypeConfiguration" }, method = { RequestMethod.GET })
	public String nubanAccountTypeConfiguration(Model model, NubanCodeConfig nubanCodeConfig) {
		String methodName = "nubanTypeConfiguration";
		try {
			model.addAttribute("nubanTypeConfiguration", nubanCodeConfig);
			model.addAttribute("DMB_HIDE", true);
			model.addAttribute("OFI_HIDE", true);

			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ControlAccount.CREATE_NUBAN_TYPE_CONFIG);
			List<NubanTypeConfig> nubanTypeAndDescription = accountManagementService.getNubanTypeAndDescription();
			System.out.println(nubanTypeAndDescription);
			model.addAttribute("nubanTypes", nubanTypeAndDescription);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "nubanTypeConfiguration";
	}

	@RequestMapping(value = { "/nubanTypeConfiguration" }, method = { RequestMethod.POST })
	public String nubanAccountTypeConfigurationPost(Model model, NubanCodeConfig nubanCodeConfig) {
		String methodName = "nubanTypeConfiguration";
		try {
			model.addAttribute("nubanTypeConfiguration", nubanCodeConfig);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ControlAccount.CREATE_NUBAN_TYPE_CONFIG);
			List<NubanTypeConfig> nubanTypeAndDescription = accountManagementService.getNubanTypeAndDescription();
			System.out.println(nubanTypeAndDescription);

			model.addAttribute("nubanTypes", nubanTypeAndDescription);
			String participantid = sessionDTO.getParticipantid();
			nubanCodeConfig.setStrParticipantId(participantid);
			int addNubanCodeConfig = accountManagementService.addNubanCodeConfig(nubanCodeConfig);

			// added by ankit
			model.addAttribute("display", "block");
			if (addNubanCodeConfig > 0) {
				model.addAttribute("success", "Nuban Config Added Successfully");
				NubanCodeConfig ubCodeConfig = new NubanCodeConfig();
				model.addAttribute("nubanTypeConfiguration", ubCodeConfig);
				model.addAttribute("DMB_HIDE", true);
				model.addAttribute("OFI_HIDE", true);
			} else {
				model.addAttribute("error", "Nuban Config Already Configured.");
				if (nubanCodeConfig.getStrNubanType() != null && nubanCodeConfig.getStrNubanType().equals("DMB")) {
					model.addAttribute("OFI_HIDE", true);
					model.addAttribute("DMB_HIDE", false);
				} else {
					model.addAttribute("DMB_HIDE", true);
					model.addAttribute("OFI_HIDE", false);
				}
			}
			// added by ankit

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "nubanTypeConfiguration";
	}

	@RequestMapping(value = { "/getNubanTypeDescription" }, method = { RequestMethod.POST })
	public ResponseEntity<?> getNubanTypeAndDescription(@RequestBody NubanTypeConfig nubanTypeConfig) {
		try {
			NubanTypeConfig nubanTypeDescription = accountManagementService.getNubanTypeDescription(nubanTypeConfig);
			return ResponseEntity.ok(nubanTypeDescription);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = { "/CloseAccount" }, method = { RequestMethod.GET })
	public String closeAccount(Model model, CloseAccountMaster closeAccountMaster) {
		String methodName = "closeAccount";
		try {
			model.addAttribute("closeAccount", closeAccountMaster);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "closeAccount";
	}

	@RequestMapping(value = { "/CloseAccount" }, method = { RequestMethod.POST })
	public String closeAccountRequestSubmitForm(Model model, CloseAccountMaster closeAccountMaster) {
		String methodName = "closeAccountRequestSubmitForm";
		try {
			String loginUser = sessionDTO.getLoginID();

			closeAccountMaster.setStrAccountNumber(closeAccountMaster.getSelectedAccountNoForClose());
			closeAccountMaster.setStrAccountType(closeAccountMaster.getSelectedAccountTypeForClose());

			closeAccountMaster.setClosureInitiatedBy(loginUser);
			closeAccountMaster.setMakerUserId(loginUser);

			CloseAccountResponse closeAccountResponse = accountManagementService
					.saveCloseAccountRequest(closeAccountMaster);
			if ("S0000".equalsIgnoreCase(closeAccountResponse.getCode())) {
				CloseAccountMaster closeAccountMaster2 = new CloseAccountMaster();
				model.addAttribute("closeAccount", closeAccountMaster2);
				model.addAttribute("success", "successfully request added.");
			} else {
				model.addAttribute("error", closeAccountResponse.getMsg());
				model.addAttribute("closeAccount", closeAccountMaster);
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "closeAccount";
	}

	// added by ankit on 02-06-2023
	@RequestMapping(value = { "/bulkTransfer" }, method = { RequestMethod.GET })
	public String bulkTransfer(Model model, BulkTransfer bulkTransfer) {
		String methodName = "bulkTransfer";
		try {
			// model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.TRANSFER_IN_OUT);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.BULK_TRANSFER);
			model.addAttribute("bulkTransfer", bulkTransfer);

			List<AccountTypeMaster> accountTypeMasterList = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accAccountType", accountTypeMasterList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "bulkTransfer";
	}

	@RequestMapping(value = { "/bulkTransfer" }, method = { RequestMethod.POST })
	public String bulkTransferPost(Model model, @ModelAttribute BulkTransfer bulkTransfer) {
		String methodName = "bulkTransfer";
		try {
			List<AccountCreation> accountCreationList = bulkTransfer.getAccountCreationList();
			int size = accountCreationList.size();
			System.out.println(size);
			// model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.TRANSFER_IN_OUT);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.BULK_TRANSFER);
			model.addAttribute("glAccountCreation", bulkTransfer);
			commaSeprateValues();
			List<AccountTypeMaster> accountTypeMasterList = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accAccountType", accountTypeMasterList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "bulkTransfer";
	}

	// added by ankit on 02-06-2023 - end -
	@RequestMapping(value = { "/uploadAccountExcelFile" }, method = { RequestMethod.POST })
	public ResponseEntity<BulkTransfer> bulkTransfer(@RequestParam("file") MultipartFile file) {
		String contentType = file.getContentType();
		System.out.println(contentType);
		BulkTransfer bulkTransfer = new BulkTransfer();
		if (!file.isEmpty() && file.getContentType().equals("application/vnd.ms-excel")) {
			System.out.println(file);
			return ResponseEntity.ok(bulkTransfer);
		}

		bulkTransfer.setStrMessage("Please Select Properly Formatted Excel File ");

		return ResponseEntity.ok(bulkTransfer);

	}

	public void commaSeprateValues() {
		String str = "apple,banana,orange";

		// splittin the comma seprared values
		String[] arr = str.split(",");

		for (String s : arr) {
			System.out.println(s);
		}

		// splitting the equals to values
		String string = "value1.1=value1.2,value2.1=value2.2;value3.1=value3.2";
		String[] accountNoAmount = str.split(";");

		for (String pair : accountNoAmount) {
			String[] entry = pair.split("=");
			String accountNo = entry[0];
			String amount = entry[1];
			System.out.println("Account No: " + accountNo + ", Amount: " + amount);
		}
	}

	// Added by prashant tayde for one to many accounts

	@RequestMapping(value = { "/oneToManyAccounts" }, method = { RequestMethod.GET })
	public String oneToManyAccounts(Model model, BulkTransfer bulkTransfer) {
		String methodName = "oneToManyAccount";
		try {
			model.addAttribute("bulkTransfer", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.ONE_TO_MANY_ACCOUNTS);

			List<AccountTypeMaster> accountTypeMasters = this.configurationService.getAccountTypeMasterDataList();

			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "oneToManyAccounts";
	}

	@RequestMapping(value = { "/getOneToManyAccounts" }, method = { RequestMethod.POST })
	public String getOneToManyAccounts(Model model, BulkTransfer bulkTransfer) {
		String methodName = "getOneToManyAccounts";
		try {
			model.addAttribute("oneToManyAccount", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.ONE_TO_MANY_ACCOUNTS);

			BulkTransfer[] bulkTransfers = this.mapper.readValue(bulkTransfer.getStrBulkData(), BulkTransfer[].class);

			List<BulkTransfer> bulkTransferList = Arrays.asList(bulkTransfers);

			bulkTransfer.setBulkTransferToAccounts(bulkTransferList);
			bulkTransfer.setStrBulkMode("OTM");

			List<ProcessResponse> oneToManyBulkTransfer = accountManagementService
					.addOneToManyAccountsData(bulkTransfer);

			if (oneToManyBulkTransfer.size() > 0 && oneToManyBulkTransfer != null) 
			{
				model.addAttribute("success", "Bulk Transfer One To Many Entries Added Successfully!");
			}
			else 
			{
				model.addAttribute("error", "Failed to Save!");
			}
			List<AccountTypeMaster> accountTypeMasters = this.configurationService.getAccountTypeMasterDataList();
			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));
			model.addAttribute("bulkTransfer", bulkTransfer);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "oneToManyAccounts";
	}

	@RequestMapping(value = "/getAccountLimitInfoToCompareTransferAmount", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getAccountLimitInfo(@RequestBody BulkTransfer bulkTransfer) {
		try {
			ResponseEntity<?> accountLimitInfoToCompareTransferAmount = accountManagementService
					.getAccountLimitInfoToCompareTransferAmount(bulkTransfer);

			return accountLimitInfoToCompareTransferAmount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getCumlativeBalanceCompareTransAmt", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getCumlativeBalanceCompareTransAmt(@RequestBody BulkTransfer bulkTransfer) {
		try {
			ResponseEntity<?> cumulativeBalCompareToTransAmt = accountManagementService
					.getCumlativeBalanceCompareTransAmt(bulkTransfer);
			return cumulativeBalCompareToTransAmt;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Added by prashant tayde for many to accounts

	@RequestMapping(value = { "/manyToOneAccount" }, method = { RequestMethod.GET })
	public String manyToOneAccount(Model model, BulkTransfer bulkTransfer) {
		String methodName = "manyToOneAccount";
		try {
			model.addAttribute("manyToOneAccount", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.MANY_TO_ONE_ACCOUNT);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());
			List<AccountTypeMaster> accountTypeMasters = this.configurationService.getAccountTypeMasterDataList();
			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "manyToOneAccount";
	}

	@RequestMapping(value = { "/getManyToOneAccount" }, method = { RequestMethod.POST })
	public String getManyToOneAccount(Model model, BulkTransfer bulkTransfer) {
		String methodName = "getManyToOneAccount";
		try {
			model.addAttribute("manyToOneAccount", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.MANY_TO_ONE_ACCOUNT);

			BulkTransfer[] bulkTransfers = this.mapper.readValue(bulkTransfer.getStrBulkData(), BulkTransfer[].class);

			List<BulkTransfer> bulkTransferList = Arrays.asList(bulkTransfers);

			bulkTransfer.setBulkTransferFromAccounts(bulkTransferList);
			bulkTransfer.setStrBulkMode("MTO");

			List<ProcessResponse> manyToOneBulkTransfer = accountManagementService
					.addManyToOneAccountsData(bulkTransfer);

			if (manyToOneBulkTransfer.size() > 0 && manyToOneBulkTransfer != null) {
				model.addAttribute("success", "Bulk Transfer Many To One Entries Added Successfully!");
			} else {
				model.addAttribute("error", "Failed to Save");
			}

			List<AccountTypeMaster> accountTypeMasters = this.configurationService.getAccountTypeMasterDataList();
			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));
			model.addAttribute("bulkTransfer", bulkTransfer);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "manyToOneAccount";
	}

	@RequestMapping(value = { "/pendingForVerification" }, method = { RequestMethod.GET })
	public String pendingForVerification(Model model, BulkTransfer bulkTransfer) {
		String methodName = "bulkTransfer";
		try {
			model.addAttribute("bulkTransfer", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.AUTHORIZE_ACCOUNTS);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "pendingForVerification";
	}

	@RequestMapping(value = { "/accountClosureApproval" }, method = { RequestMethod.GET })
	public String getAccountClouserList(Model model, CloseAccountMaster closeAccountMaster) {
		String methodName = "accountClosureApproval";
		try {
			model.addAttribute("accountclouserObj", closeAccountMaster);
			List<CloseAccountMaster> accountClouserList = accountManagementService.getListOfAccountClouser();
			model.addAttribute("accountClouseList", accountClouserList);
			model.addAttribute("preAccountListJson", this.mapper.writeValueAsString(accountClouserList));

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountClosureApproval";
	}

	@RequestMapping(value = { "/accountClouserViewPage" }, method = { RequestMethod.GET })
	public String clouserAccountMasterView(Model model, CustomerIdCreation customerIDCreation) {
		try 
		{
			CloseAccountMaster closeAccountMaster = new CloseAccountMaster();

			closeAccountMaster.setStrAccountNumber(customerIDCreation.getStrAccountNumber());
			closeAccountMaster.setStrCustId(customerIDCreation.getStrCustId());

			CloseAccountMaster customerIDCreation1 = accountManagementService
					.getCLoseAccountMasterDataByCustId(closeAccountMaster);
			model.addAttribute("customerIDCreation", customerIDCreation1);
			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAccountNumber(customerIDCreation.getStrAccountNumber());
			accountCreation.setStrCustId(customerIDCreation.getStrCustId());
			List<AccountCreation> accountDetailsList = accountManagementService
					.getAccountInfoListBasedOnTypes(accountCreation);
			model.addAttribute("accountDetailsList", accountDetailsList);
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			e.printStackTrace();
		}
		return "accountClouserViewPage";
	}

	@RequestMapping(value = { "/accountClouserApproveReject" }, method = { RequestMethod.POST })
	public String accountClouserApproveReject(Model model, CustomerIdCreation customerIDCreation) {
		try {
			model.addAttribute("customerIDCreation", customerIDCreation);

			CloseAccountMaster closeAccountMaster = new CloseAccountMaster();
			closeAccountMaster.setStrCustId(customerIDCreation.getStrCustId());
			closeAccountMaster = accountManagementService.getCloseAccountMasterDetails(closeAccountMaster);
			closeAccountMaster.setStrAccountNumber(closeAccountMaster.getStrAccountNumber());
			closeAccountMaster.setUpgradeAction(customerIDCreation.getUpgradeAction());
			closeAccountMaster.setStrRejectedReason(customerIDCreation.getStrRejectedReason());
			closeAccountMaster = accountManagementService.requestStatusApproveReject(closeAccountMaster);

		} catch (Exception e) {
			model.addAttribute("display", "block");
			e.printStackTrace();
		}
		return "redirect:accountClosureApproval";
	}

	@RequestMapping(value = { "/glAccToManyAccounts" }, method = { RequestMethod.GET })
	public String getGLToManyAccounts(Model model, BulkTransfer bulkTransfer) {
		String methodName = "glAccToManyAccounts";
		try {
			model.addAttribute("glAccToManyAccounts", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.GL_TO_MANY_ACCOUNTS);
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("glAccountTypeList", glAccountTypeList);

			List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountType", accountTypeMasters);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccToManyAccounts";
	}

	@RequestMapping(value = { "/getGlAccToManyAccounts" }, method = { RequestMethod.POST })
	public String getGlAccToManyAccounts(Model model, BulkTransfer bulkTransfer) {
		String methodName = "getGlAccToManyAccounts";
		try {
			model.addAttribute("glAccToManyAccounts", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.GL_TO_MANY_ACCOUNTS);

			BulkTransfer[] bulkTransfers = this.mapper.readValue(bulkTransfer.getStrBulkData(), BulkTransfer[].class);

			List<BulkTransfer> bulkTransferList = Arrays.asList(bulkTransfers);

			bulkTransfer.setBulkTransferToAccounts(bulkTransferList);
			bulkTransfer.setStrIsVerified("Y");
			bulkTransfer.setStrMakerId(sessionDTO.getLoginID());

			ProcessResponse processResponse = accountManagementService.addGLToManyAccountsData(bulkTransfer);

			if (processResponse != null && "S0000".equalsIgnoreCase(processResponse.getCode())) {
				model.addAttribute("success", processResponse.getMessage());
				BulkTransfer bulkTransfer2 = new BulkTransfer();
				model.addAttribute("bulkTransfer", bulkTransfer2);
			} else {
				model.addAttribute("error", processResponse.getMessage());
				model.addAttribute("bulkTransfer", bulkTransfer);
			}
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("glAccountTypeList", glAccountTypeList);
			model.addAttribute("glAccountTypeJson", this.mapper.writeValueAsString(glAccountTypeList));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccToManyAccounts";
	}

	@RequestMapping(value = { "/manyToGLAccount" }, method = { RequestMethod.GET })
	public String getManyToGLAccount(Model model, BulkTransfer bulkTransfer) {
		String methodName = "manyToGLAccount";
		try {

			model.addAttribute("manyToGLAccount", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.MANY_TO_GL_ACCOUNT);
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("glAccountTypeList", glAccountTypeList);

			List<AccountTypeMaster> accountTypeMasters = accountManagementService.getAccountTypeMasterList();
			model.addAttribute("accountType", accountTypeMasters);
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(accountTypeMasters));

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "manyToGLAccount";
	}

	@RequestMapping(value = { "/getManyToGLAccount" }, method = { RequestMethod.POST })
	public String getManyToGLAccountList(Model model, BulkTransfer bulkTransfer) {
		String methodName = "manyToGLAccount";
		try {

			model.addAttribute("manyToGLAccount", bulkTransfer);
			model.addAttribute("leftbulkMenuID", TranecoStatusCode.BulkTransfer.MANY_TO_GL_ACCOUNT);

			BulkTransfer[] bulkTransfers = this.mapper.readValue(bulkTransfer.getStrBulkData(), BulkTransfer[].class);

			List<BulkTransfer> bulkTransferList = Arrays.asList(bulkTransfers);

			bulkTransfer.setBulkTransferFromAccounts(bulkTransferList);
			bulkTransfer.setStrMakerId(sessionDTO.getLoginID());
			bulkTransfer.setStrIsVerified("Y");

			ProcessResponse processResponse = accountManagementService.addManyToGLAccountsData(bulkTransfer);

			if (processResponse != null && "S0000".equalsIgnoreCase(processResponse.getCode())) {
				model.addAttribute("success", processResponse.getMessage());
				BulkTransfer bulkTransfer2 = new BulkTransfer();
				model.addAttribute("bulkTransfer", bulkTransfer2);
			} else {
				model.addAttribute("error", processResponse.getMessage());
				model.addAttribute("bulkTransfer", bulkTransfer);
			}
			List<GLAccountTypeMaster> glAccountTypeList = accountManagementService.getGLAccountTypeList();
			model.addAttribute("glAccountTypeList", glAccountTypeList);
			model.addAttribute("accountTypeJson", this.mapper.writeValueAsString(glAccountTypeList));

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "manyToGLAccount";
	}

	// Reward Management starts
	@RequestMapping(value = { "/cashBackPointSetup" }, method = { RequestMethod.GET })
	public String binConfigForm(Model model, CashBackCalculationModel cashBackCalculationModel) {
		String methodName = "binConfigForm";
		try {
			model.addAttribute("cashBackCalculationModel", cashBackCalculationModel);
			// model.addAttribute("leftbinCardMenuID", TranecoStatusCode.account);

		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cashBackPointSetup";
	}

	// Added by prashant T 30 Aug 2023

	@RequestMapping(value = { "/vatTypeMapped" }, method = { RequestMethod.GET })
	public String vatTypeMasterForm(Model model, VatTypeMaster vatTypeMaster) {
		String methodName = "vatTypeMasterForm";
		try {
			model.addAttribute("vatTypeMaster", vatTypeMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.VAT_TYPE_MAPPED);

		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "vatTypeConfiguration";
	}

	// Added by prashant T 30 Aug 2023
	@RequestMapping(value = { "/addVatTypeMapped" }, method = { RequestMethod.POST })
	public String addVatTypeMappedForm(Model model, VatTypeMaster vatTypeMaster) {
		String methodName = "addVatTypeMappedForm";
		try {
			model.addAttribute("vatTypeMaster", vatTypeMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.VAT_TYPE_MAPPED);
			vatTypeMaster.setCreatedBy(this.sessionDTO.getLoginID());
			 String strErrorMsg = "Duplicate Vat Type!!";
			 Boolean isVatTypeExist = accountManagementService.validateVatType(vatTypeMaster);
			 if (isVatTypeExist) 
			 {
					model.addAttribute("error", strErrorMsg);
					return "vatTypeConfiguration";
			}
			int count = accountManagementService.addVatTypeMappedData(vatTypeMaster);
			if (count > 0) 
			{
				model.addAttribute("success", "Vat Type Mapped Successfully");
			}
			else 
			{
				model.addAttribute("failed", "Failed To save");
			}
			VatTypeMaster vatTypeMaster2 = new VatTypeMaster();
			model.addAttribute("vatTypeMaster", vatTypeMaster2);
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "vatTypeConfiguration";
	}

	// Added by prashant T 30 Aug 2023
	@RequestMapping(value = { "/feeTypeMapped" }, method = { RequestMethod.GET })
	public String feeTypeMasterFrom(Model model, FeeTypeMaster feeTypeMaster) {
		String methodName = "feeTypeMasterForm";
		try {
			model.addAttribute("feeTypeMaster", feeTypeMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.FEE_TYPE_MAPPED);
			VatTypeMaster vatTypeMaster = new VatTypeMaster();
			List<VatTypeMaster> vatTypeMasterList = accountManagementService.getVatTypeList(vatTypeMaster);
			model.addAttribute("vatTypeMasterList", vatTypeMasterList);

		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "feeTypeConfiguration";
	}
	
	// Added by prashant T 30 Aug 2023
	@RequestMapping(value = { "/addFeeTypeMapped" }, method = { RequestMethod.POST })
	public String addFeeTypeMappedFrom(Model model, FeeTypeMaster feeTypeMaster) 
	{
		String methodName = "addFeeTypeMappedFrom";
		try 
		{
			model.addAttribute("feeTypeMaster", feeTypeMaster);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.FEE_TYPE_MAPPED);
			feeTypeMaster.setCreatedBy(this.sessionDTO.getLoginID());
			String ErrorMsg = "Duplicate Fee Type!!";
			boolean isFeeTypeExist = accountManagementService.validateFeeType(feeTypeMaster);
			if(isFeeTypeExist)
			{
				model.addAttribute("error", ErrorMsg);
				VatTypeMaster vatTypeMaster = new VatTypeMaster();
				List<VatTypeMaster> vatTypeMasterList = accountManagementService.getVatTypeList(vatTypeMaster);
				model.addAttribute("vatTypeMasterList", vatTypeMasterList);
				return "feeTypeConfiguration";
			}
			String ErrorMessage = "Already Linked With Fee Type. Please Choose Anothor Vat Type!!";
			
			boolean isVatTypeMapped = accountManagementService.validateVatTypeList(feeTypeMaster);
			if(isVatTypeMapped)
			{
				model.addAttribute("error", ErrorMessage);
				VatTypeMaster vatTypeMaster = new VatTypeMaster();
				List<VatTypeMaster> vatTypeMasterList = accountManagementService.getVatTypeList(vatTypeMaster);
				model.addAttribute("vatTypeMasterList", vatTypeMasterList);
				return "feeTypeConfiguration";
			}
			int count = accountManagementService.addFeeTypeMappedData(feeTypeMaster);
			if(count > 0)
			{
				model.addAttribute("success", "Fee Type Mapped Successfully");
			}
			else 
			{
				model.addAttribute("error", "Failed to Map");
			}
			FeeTypeMaster feeTypeMaster2 = new FeeTypeMaster();
			model.addAttribute("feeTypeMaster", feeTypeMaster2);
			VatTypeMaster vatTypeMaster = new VatTypeMaster();
			List<VatTypeMaster> vatTypeMasterList = accountManagementService.getVatTypeList(vatTypeMaster);
			model.addAttribute("vatTypeMasterList", vatTypeMasterList);
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "feeTypeConfiguration";
	}
	// Ended by prashant T 31 Aug 2023
	
	
	//added by prashant Tayde 01 Sept 2023
	
	@RequestMapping(value = { "/viewTransactionLogs" }, method = { RequestMethod.GET })
	public String viewTransactionLogForm(Model model, TxnReqRespLogMaster txnReqRespLogMaster) {
		String methodName = "viewTransactionLogForm";
		try 
		{
			model.addAttribute("txnReqRespLogMaster", txnReqRespLogMaster);
			//model.addAttribute("leftAccountMenuID", TranecoStatusCode.Configuration.FEE_TYPE_MAPPED);
		} 
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewTransactionLogs";
	}
	
	
	@RequestMapping(value = { "/addViewTransactionLogs" }, method = { RequestMethod.POST })
	public String addViewTransactionLogs(Model model, TxnReqRespLogMaster txnReqRespLogMaster) {
		String methodName = "addViewTransactionLogs";
		try 
		{
			model.addAttribute("txnReqRespLogMaster", txnReqRespLogMaster);
			
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewTransactionLogs";
	}
	
	
	
	
	
	
	
	
	
}
