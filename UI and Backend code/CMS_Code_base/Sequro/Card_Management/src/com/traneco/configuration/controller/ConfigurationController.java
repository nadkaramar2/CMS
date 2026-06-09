package com.traneco.configuration.controller;

import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.logger.Constants;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.AccountTypeModel;
import com.traneco.configuration.model.AddressTypeModel;
import com.traneco.configuration.model.BinModel;
import com.traneco.configuration.model.BranchCodeModel;
import com.traneco.configuration.model.BranchTypeModel;
import com.traneco.configuration.model.CardPlasticModel;
import com.traneco.configuration.model.CardTemplateModel;
import com.traneco.configuration.model.CardTokenModel;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.ChargingModule;
import com.traneco.configuration.model.ConnectionConfigModel;
import com.traneco.configuration.model.CountryModel;
import com.traneco.configuration.model.EmailTypeModel;
import com.traneco.configuration.model.EmbossConfigModel;
import com.traneco.configuration.model.IsoConfigModel;
import com.traneco.configuration.model.KeyConfigModel;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.model.NcmcServiceModel;
import com.traneco.configuration.model.ParticipantConfigModel;
import com.traneco.configuration.model.PhoneTypeModel;
import com.traneco.configuration.model.StateModel;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.configuration.services.GLAccountConfigurationService;

@Controller
public class ConfigurationController {
	@Autowired
	Environment env;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	LoggerUtil log;

	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	GLAccountConfigurationService glAccountConfigurationService;
	
	@Autowired
	AccountManagementService accountManagementService;
	
	@Autowired
	AuditService auditService;

	@Autowired
	ObjectMapper mapper;

	private String className = getClass().getSimpleName();

	@RequestMapping(value = { "/binConfigForm" }, method = { RequestMethod.GET })
	public String binConfigForm(Model model, BinModel binModel) {
		String methodName = "binConfigForm";
		try 
		{
			model.addAttribute("binModel", binModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.BIN_CONFIGURATION);
			model.addAttribute("binList", this.configurationService.getBin());
			model.addAttribute("bin", this.mapper.writeValueAsString(this.configurationService.getBin()));
		}
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "binConfigForm";
	}

	@RequestMapping(value = { "/binConfig" }, method = { RequestMethod.POST })
	public String binConfig(Model model, BinModel binModel) {
		String methodName = "binConfig";
		
		try {
			model.addAttribute("binModel", binModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.BIN_CONFIGURATION);
			int count = this.configurationService.addBin(binModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Bin Added Successfully !");
			} else {
				model.addAttribute("error", "Bin Adding Failed ! !!");
			}
			model.addAttribute("binList", this.configurationService.getBin());
			model.addAttribute("bin", this.mapper.writeValueAsString(this.configurationService.getBin()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "binConfigForm";
	}

	@RequestMapping(value = { "/countryConfigForm" }, method = { RequestMethod.GET })
	public String countryConfigForm(Model model, CountryModel countryModel) {
		String methodName = "countryConfigForm";
		try {
			model.addAttribute("countryModel", countryModel);
			model.addAttribute("countryList", this.configurationService.getCountry());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "countryConfigForm";
	}

	@RequestMapping(value = { "/countryConfig" }, method = { RequestMethod.POST })
	public String countryConfig(Model model, CountryModel countryModel) {
		String methodName = "countryConfig";
		try {
			model.addAttribute("countryModel", countryModel);
			int count = this.configurationService.addCountry(countryModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Country Added Successfully !");
			} else {
				model.addAttribute("error", "Country Adding Failed ! !!");
			}
			model.addAttribute("countryList", this.configurationService.getCountry());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "countryConfigForm";
	}

	@RequestMapping(value = { "/stateConfigForm" }, method = { RequestMethod.GET })
	public String stateConfigForm(Model model, StateModel stateModel) {
		String methodName = "stateConfigForm";
		try {
			model.addAttribute("stateModel", stateModel);
			model.addAttribute("countryList", this.configurationService.getCountry());
			model.addAttribute("stateList", this.configurationService.getState());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "stateConfigForm";
	}

	@RequestMapping(value = { "/stateConfig" }, method = { RequestMethod.POST })
	public String stateConfig(Model model, StateModel stateModel) {
		String methodName = "stateConfig";
		try {
			model.addAttribute("stateModel", stateModel);
			model.addAttribute("countryList", this.configurationService.getCountry());
			int count = this.configurationService.addState(stateModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "State Added Successfully !");
			} else {
				model.addAttribute("error", "State Adding Failed ! !!");
			}
			model.addAttribute("stateList", this.configurationService.getState());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "stateConfigForm";
	}

	@RequestMapping(value = { "/branchConfigForm" }, method = { RequestMethod.GET })
	public String branchTypeForm(Model model, BranchTypeModel branchTypeModel) {
		String methodName = "branchConfigForm";
		try {
			model.addAttribute("branchTypeModel", branchTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.BRANCH_CONFIGURATION);
			model.addAttribute("branchList", this.configurationService.getBranch());
			model.addAttribute("branch", this.mapper.writeValueAsString(this.configurationService.getBranch()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "branchConfigForm";
	}

	@RequestMapping(value = { "/branchConfig" }, method = { RequestMethod.POST })
	public String branchConfig(Model model, BranchTypeModel branchTypeModel) {
		String methodName = "branchConfig";
		try {
			model.addAttribute("branchTypeModel", branchTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.BRANCH_CONFIGURATION);
			int count = this.configurationService.addBranch(branchTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Branch Added Successfully !");
			} else {
				model.addAttribute("error", "Branch Adding Failed ! !!");
			}
			model.addAttribute("branchList", this.configurationService.getBranch());
			model.addAttribute("branch", this.mapper.writeValueAsString(this.configurationService.getBranch()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "branchConfigForm";
	}

	@RequestMapping(value = { "/branchCodeConfigForm" }, method = { RequestMethod.GET })
	public String branchCodeConfigForm(Model model, BranchCodeModel branchCodeModel) {
		String methodName = "branchCodeConfigForm";
		try {
			model.addAttribute("branchCodeModel", branchCodeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.BRANCH_CODE_CONFIGURATION);
			model.addAttribute("branchList", this.configurationService.getBranch());
			model.addAttribute("branchCodeList", this.configurationService.getBranchCode());
			model.addAttribute("branchCode", this.mapper.writeValueAsString(this.configurationService.getBranchCode()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "branchCodeConfigForm";
	}

	@RequestMapping(value = { "/branchCodeConfig" }, method = { RequestMethod.POST })
	public String branchCodeConfig(Model model, BranchCodeModel branchCodeModel) {
		String methodName = "branchCodeConfigForm";
		try {
			model.addAttribute("branchCodeModel", branchCodeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.BRANCH_CODE_CONFIGURATION);
			model.addAttribute("branchList", this.configurationService.getBranch());
			int count = this.configurationService.addBranchCode(branchCodeModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Branch Code Added Successfully !");
			} else {
				model.addAttribute("error", "Branch Code Adding Failed ! !!");
			}
			model.addAttribute("branchCodeList", this.configurationService.getBranchCode());
			model.addAttribute("branchCode", this.mapper.writeValueAsString(this.configurationService.getBranchCode()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "branchCodeConfigForm";
	}

	@RequestMapping(value = { "/accountConfigForm" }, method = { RequestMethod.GET })
	public String accountConfigForm(Model model, AccountTypeModel accountTypeModel) {
		String methodName = "branchCodeConfigForm";
		try {
			model.addAttribute("accountTypeModel", accountTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.ACCOUNT_TYPE_CONFIGURATION);
			model.addAttribute("accountList", this.configurationService.getAccount());
			model.addAttribute("account", this.mapper.writeValueAsString(this.configurationService.getAccount()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountConfigForm";
	}

	@RequestMapping(value = { "/accountConfig" }, method = { RequestMethod.POST })
	public String accountConfig(Model model, AccountTypeModel accountTypeModel) {
		String methodName = "accountConfigForm";
		try {
			model.addAttribute("accountTypeModel", accountTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.ACCOUNT_TYPE_CONFIGURATION);
			int count = this.configurationService.addAccount(accountTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Account Type Added Successfully !");
			} else {
				model.addAttribute("error", "Account Type Adding Failed ! !!");
			}
			model.addAttribute("accountList", this.configurationService.getAccount());
			model.addAttribute("account", this.mapper.writeValueAsString(this.configurationService.getAccount()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountConfigForm";
	}

	@RequestMapping(value = { "/addressConfigForm" }, method = { RequestMethod.GET })
	public String addressConfigForm(Model model, AddressTypeModel addressTypeModel) {
		String methodName = "addressConfigForm";
		try {
			model.addAttribute("addressTypeModel", addressTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.ADDRESS_TYPE_CONFIGURATION);
			model.addAttribute("addressList", this.configurationService.getAddress());
			model.addAttribute("address", this.mapper.writeValueAsString(this.configurationService.getAddress()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addressConfigForm";
	}

	@RequestMapping(value = { "/addressConfig" }, method = { RequestMethod.POST })
	public String addressConfig(Model model, AddressTypeModel addressTypeModel) {
		String methodName = "addressConfig";
		try {
			model.addAttribute("addressTypeModel", addressTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.ADDRESS_TYPE_CONFIGURATION);
			int count = this.configurationService.addAddress(addressTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Address Type Added Successfully !");
			} else {
				model.addAttribute("error", "Address Type Adding Failed ! !!");
			}
			model.addAttribute("addressList", this.configurationService.getAddress());
			model.addAttribute("address", this.mapper.writeValueAsString(this.configurationService.getAddress()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addressConfigForm";
	}

	@RequestMapping(value = { "/cardTypeConfigForm" }, method = { RequestMethod.GET })
	public String cardTypeConfigForm(Model model, CardTypeModel cardTypeModel) {
		String methodName = "cardTypeConfigForm";
		try {
			model.addAttribute("cardTypeModel", cardTypeModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.CARD_TYPE_CONFIGURATION);
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("binList", this.configurationService.getBin());
			model.addAttribute("cardType", this.mapper.writeValueAsString(this.configurationService.getCardType()));
			model.addAttribute("endpoint", this.configurationService.getEndPoint());
			model.addAttribute("accountTypeList", this.configurationService.getAccountTypeList());
			//model.addAttribute("endpoint", this.configurationService.getEndPoints());
			
		} catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardTypeConfigForm";
	}

	@RequestMapping(value = { "/cardTypeConfig" }, method = { RequestMethod.POST })
	public String cardTypeConfig(Model model, CardTypeModel cardTypeModel) {
		String methodName = "cardTypeConfig";
		try {
			model.addAttribute("cardTypeModel", cardTypeModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.CARD_TYPE_CONFIGURATION);
			model.addAttribute("binList", this.configurationService.getBin());
			model.addAttribute("endpoint", this.configurationService.getEndPoint());
			//model.addAttribute("endpoint", this.configurationService.getEndPoints());\
			model.addAttribute("accountTypeList", this.configurationService.getAccountTypeList());
			int count = this.configurationService.addCardType(cardTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) 
			{
				model.addAttribute("success", "Card Type Added Successfully !");
				CardTypeModel cardTypeModel2 = new CardTypeModel();
				model.addAttribute("cardTypeModel", cardTypeModel2);
			}
			else
			{
				model.addAttribute("error", "Card Type Adding Failed ! !!");
			}
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("cardType", this.mapper.writeValueAsString(this.configurationService.getCardType()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardTypeConfigForm";
	}

	@RequestMapping(value = { "/cardTemplateConfigForm" }, method = { RequestMethod.GET })
	public String cardTemplateConfigForm(Model model, CardTemplateModel cardTemplateModel) {
		String methodName = "cardTemplateConfigForm";
		try {
			model.addAttribute("cardTemplateModel", cardTemplateModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.CARD_TEMPLATE_CONFIGURATION);
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("cardTempList", this.configurationService.getCardTemplate());
			model.addAttribute("cardTemplate",
					this.mapper.writeValueAsString(this.configurationService.getCardTemplate()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardTemplateConfigForm";
	}

	@RequestMapping(value = { "/cardTemplateConfig" }, method = { RequestMethod.POST })
	public String cardTemplateConfig(Model model, CardTemplateModel cardTemplateModel) {
		String methodName = "cardTemplateConfig";
		try {
			model.addAttribute("cardTemplateModel", cardTemplateModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.CARD_TEMPLATE_CONFIGURATION);
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			int count = this.configurationService.addCardTemplate(cardTemplateModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Card Type Added Successfully !");
				CardTemplateModel cardTemplateModel2 = new CardTemplateModel();
				model.addAttribute("cardTemplateModel", cardTemplateModel2);
			} else {
				model.addAttribute("error", "Card Type Adding Failed ! !!");
			}
			model.addAttribute("cardTempList", this.configurationService.getCardTemplate());
			model.addAttribute("cardTemplate",
					this.mapper.writeValueAsString(this.configurationService.getCardTemplate()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardTemplateConfigForm";
	}

	@RequestMapping(value = { "/cardPlasticConfigForm" }, method = { RequestMethod.GET })
	public String cardPlasticConfigForm(Model model, CardPlasticModel cardPlastic) {
		String methodName = "cardPlasticConfigForm";
		try {
			model.addAttribute("cardPlastic", cardPlastic);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.CARD_PLASTIC_CONFIGURATION);
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("cardPlasticList", this.configurationService.getCardPlastic());
			model.addAttribute("embossName", this.configurationService.getEmbossNameConfig());
			model.addAttribute("crdPlastic",
					this.mapper.writeValueAsString(this.configurationService.getCardPlastic()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardPlasticConfigForm";
	}

	@RequestMapping(value = { "/cardPlasticConfig" }, method = { RequestMethod.POST })
	public String cardPlasticConfig(Model model, CardPlasticModel cardPlastic) {
		String methodName = "cardPlasticConfig";
		try {
			model.addAttribute("cardPlastic", cardPlastic);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.CARD_PLASTIC_CONFIGURATION);
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			int count = this.configurationService.addCardPlastic(cardPlastic);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Card Plastic Added Successfully !");
				CardPlasticModel cardPlastic2 = new CardPlasticModel();
				model.addAttribute("cardPlastic", cardPlastic2);
			} else {
				model.addAttribute("error", "Card Plastic Adding Failed ! !!");
			}
			model.addAttribute("embossName", this.configurationService.getEmbossNameConfig());
			model.addAttribute("cardPlasticList", this.configurationService.getCardPlastic());
			model.addAttribute("crdPlastic",
					this.mapper.writeValueAsString(this.configurationService.getCardPlastic()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardPlasticConfigForm";
	}

	@RequestMapping(value = { "/emailConfigForm" }, method = { RequestMethod.GET })
	public String emailConfigForm(Model model, EmailTypeModel emailTypeModel) {
		String methodName = "emailConfigForm";
		try {
			model.addAttribute("emailTypeModel", emailTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.EMAIL_TYPE_CONFIGURATION);
			model.addAttribute("emailList", this.configurationService.getEmail());
			model.addAttribute("email", this.mapper.writeValueAsString(this.configurationService.getEmail()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "emailConfigForm";
	}

	@RequestMapping(value = { "/emailConfig" }, method = { RequestMethod.POST })
	public String emailConfig(Model model, EmailTypeModel emailTypeModel) {
		String methodName = "emailConfig";
		try 
		{
			model.addAttribute("emailTypeModel", emailTypeModel);
			model.addAttribute("leftbranchMenuID", TranecoStatusCode.EMAIL_TYPE_CONFIGURATION);
			int count = this.configurationService.addEmail(emailTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) 
			{
				model.addAttribute("success", "Email Type Added Successfully !");
			} 
			else 
			{
				model.addAttribute("error", "Email Type Adding Failed ! !!");
			}
			model.addAttribute("emailList", this.configurationService.getEmail());
			model.addAttribute("email", this.mapper.writeValueAsString(this.configurationService.getEmail()));
		} 
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "emailConfigForm";
	}

	@RequestMapping(value = { "/phoneConfigForm" }, method = { RequestMethod.GET })
	public String phoneConfigForm(Model model, PhoneTypeModel phoneTypeModel) 
	{
		String methodName = "phoneConfigForm";
		try {
			model.addAttribute("phoneTypeModel", phoneTypeModel);
			model.addAttribute("phoneList", this.configurationService.getPhone());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "phoneConfigForm";
	}

	@RequestMapping(value = { "/phoneConfig" }, method = { RequestMethod.POST })
	public String phoneConfig(Model model, PhoneTypeModel phoneTypeModel) {
		String methodName = "phoneConfig";
		try {
			model.addAttribute("phoneTypeModel", phoneTypeModel);
			int count = this.configurationService.addPhone(phoneTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Phone Type Added Successfully !");
			} else {
				model.addAttribute("error", "Phone Type Adding Failed ! !!");
			}
			model.addAttribute("phoneList", this.configurationService.getPhone());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "phoneConfigForm";
	}

	@RequestMapping(value = { "/addNcmcConfigForm" }, method = { RequestMethod.GET })
	public String addNcmcConfigForm(Model model, NcmcServiceModel ncmcServiceModel) 
	{
		String methodName = "addNcmcConfigForm";
		try 
		{
			model.addAttribute("ncmcServiceModel", ncmcServiceModel);
			model.addAttribute("cardTypeList", this.configurationService.getCardTypeNCMC());
			List<KeyValueBean> list = this.configurationService.getncmcList((ncmcServiceModel.getStrCardType() == null) ? "" : ncmcServiceModel.getStrCardType());
			List<NcmcServiceModel> data = this.configurationService.getNcmcCode();
			for (KeyValueBean list1 : list) 
			{
				NcmcServiceModel keyValueBean = data.stream().filter(x -> list1.getLkpkey().equals(x.getStrNcmcID()))
						.findAny().orElse(null);
				if (keyValueBean != null)
					keyValueBean.setStrSelectID("selected");
			}
			model.addAttribute("ncmcList", data);
		} 
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "ncmcServiceConfigForm";
	}

	@RequestMapping(value = { "/addNcmcConfig" }, method = { RequestMethod.POST })
	public String addNcmcConfig(Model model, NcmcServiceModel ncmcServiceModel) {
		String methodName = "addNcmcConfig";
		try 
		{
			model.addAttribute("ncmcServiceModel", ncmcServiceModel);
			int count = this.configurationService.addNCMCService(ncmcServiceModel);
			model.addAttribute("display", "block");
			if (count > 0) 
			{
				model.addAttribute("success", "NCMC Service Added Successfully !");
			}
			else 
			{
				model.addAttribute("error", "NCMC Service Adding Failed ! !!");
			}
			List<KeyValueBean> list = this.configurationService
					.getncmcList((ncmcServiceModel.getStrCardType() == null) ? "" : ncmcServiceModel.getStrCardType());
			List<NcmcServiceModel> data = this.configurationService.getNcmcCode();
			for (KeyValueBean list1 : list) 
			{
				NcmcServiceModel keyValueBean = data.stream().filter(x -> list1.getLkpkey().equals(x.getStrNcmcID()))
						.findAny().orElse(null);
				if (keyValueBean != null)
					keyValueBean.setStrSelectID("selected");
			}
			model.addAttribute("ncmcList", data);
			model.addAttribute("cardTypeList", this.configurationService.getCardTypeNCMC());
		} 
		catch (Exception e) 
		{
			model.addAttribute("error", "NCMC Service Adding Failed ! !!");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "ncmcServiceConfigForm";
	}

	@RequestMapping(value = { "/ncmcConfigForm" }, method = { RequestMethod.GET })
	public String ncmcConfigForm(Model model, NcmcServiceModel ncmcServiceModel) 
	{
		String methodName = "ncmcConfigForm";
		try 
		{
			model.addAttribute("ncmcServiceModel", ncmcServiceModel);
			model.addAttribute("ncmcList", this.configurationService.getNcmc());
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("ncmc", this.mapper.writeValueAsString(this.configurationService.getNcmc()));
		} 
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "ncmcConfigForm";
	}

	@RequestMapping(value = { "/ncmcConfig" }, method = { RequestMethod.POST })
	public String ncmcConfig(Model model, NcmcServiceModel ncmcServiceModel) 
	{
		String methodName = "ncmcConfig";
		try 
		{
			model.addAttribute("ncmcServiceModel", ncmcServiceModel);
			int count = this.configurationService.addNcmcService(ncmcServiceModel);
			model.addAttribute("display", "block");
			if (count > 0) 
			{
				model.addAttribute("success", "NCMC Service Added Successfully !");
			} 
			else 
			{
				model.addAttribute("error", "NCMC Service Adding Failed ! !!");
			}
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("ncmcList", this.configurationService.getNcmc());
			model.addAttribute("ncmc", this.mapper.writeValueAsString(this.configurationService.getNcmc()));
		} 
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "ncmcConfigForm";
	}

	@RequestMapping(value = { "/keyConfigForm" }, method = { RequestMethod.GET })
	public String keyConfigForm(Model model, KeyConfigModel keyConfigModel) {
		String methodName = "keyConfigForm";
		try {
			model.addAttribute("keyConfigModel", keyConfigModel);
			System.out.println("-----------" + configurationService.getParticipantConfigList());
			model.addAttribute("participantList", configurationService.getParticipantConfigList());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "keyConfigForm";
	}

	@RequestMapping(value = { "/keyConfig" }, method = { RequestMethod.POST })
	public String keyConfig(Model model, KeyConfigModel keyConfigModel) {
		String methodName = "keyConfig";
		try {
			model.addAttribute("keyConfigModel", keyConfigModel);
			JsonObject keyObject = null;

			if (keyConfigModel.getStrSelectID().equals("CMS")) 
			{
				keyObject = Json.createObjectBuilder().add(Constants.CVK, keyConfigModel.getStrCVK())
						.add(Constants.PVK, keyConfigModel.getStrPVK()).add(Constants.MDK, keyConfigModel.getStrMDK())
						.add(Constants.SEC_KEY, keyConfigModel.getStrSecKeyID()).build();
			}
			else 
			{
				keyObject = Json.createObjectBuilder().add(Constants.ZPK, keyConfigModel.getStrZPK())
						.add(Constants.ZPK_CV, keyConfigModel.getStrZPK_CV())
						.add(Constants.ZMK, keyConfigModel.getStrZMK())
						.add(Constants.ZMK_CV, keyConfigModel.getStrZMK_CV())
						.add(Constants.MAC_KEY, keyConfigModel.getStrMAC_KEY())
						.add(Constants.MAC_CV, keyConfigModel.getStrMAC_CV())
						.add(Constants.MAC_FIELDS, keyConfigModel.getStrMAC_FIELDS()).build();
			}
			Iterator<String> keyItr = keyObject.keySet().iterator();
			int count = 0;
			while (keyItr.hasNext()) {
				String key = keyItr.next();
				count += this.configurationService.addKeyConfig(key, keyObject.getString(key),
						keyConfigModel.getParticipantId());
			}

			// int count = this.configurationService.addKeyConfig(keyConfigModel);
			model.addAttribute("display", "block");
			model.addAttribute("participantList", configurationService.getParticipantConfigList());
			if (count > 0) {
				model.addAttribute("success", "Key Config Added Successfully !");
			} else {
				model.addAttribute("error", "Key Config Adding Failed ! !!");
			}
			// model.addAttribute("keyList", this.configurationService.getKeyConfig());
			model.addAttribute("key", this.mapper.writeValueAsString(this.configurationService.getKeyConfig()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "keyConfigForm";
	}

	@RequestMapping(value = { "/addEmbossForm" }, method = { RequestMethod.GET })
	public String addEmbossForm(Model model, EmbossConfigModel embossConfigModel) {
		String methodName = "addEmbossForm";
		try {
			model.addAttribute("embossConfigModel", embossConfigModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.EMBOSS_CONFIGURATION);
			model.addAttribute("embossList", this.configurationService.getEmbossConfig());
			model.addAttribute("emboss",
					this.mapper.writeValueAsString(this.configurationService.getEmbossNameConfig()));
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "embossConfigForm";
	}

	@RequestMapping(value = { "/addEmboss" }, method = { RequestMethod.POST })
	public String addEmboss(Model model, EmbossConfigModel embossConfigModel) {
		String methodName = "addEmboss";
		try {
			model.addAttribute("embossConfigModel", embossConfigModel);
			model.addAttribute("leftbinCardMenuID", TranecoStatusCode.EMBOSS_CONFIGURATION);
			int count = this.configurationService.addEmboss(embossConfigModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "Emboss Config Added Successfully !");
			} else {
				model.addAttribute("error", "Emboss Config Adding Failed ! !!");
			}
			model.addAttribute("embossList", this.configurationService.getEmbossConfig());
			model.addAttribute("emboss",
					this.mapper.writeValueAsString(this.configurationService.getEmbossNameConfig()));
		} catch (Exception e) {
			model.addAttribute("error", "Emboss Config Adding Failed ! !!");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "embossConfigForm";
	}

	@RequestMapping(value = { "/addMccConfigForm" }, method = { RequestMethod.GET })
	public String addMccConfigForm(Model model, CardTypeModel cardTypeModel) {
		String methodName = "addMccConfigForm";
		try {
			model.addAttribute("mccList", this.configurationService.getCfg_MCC());
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("cardTypeModel", cardTypeModel);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addMccConfigForm";
	}

	@RequestMapping(value = { "/addMccConfig" }, method = { RequestMethod.GET })
	public String addMccConfig(Model model, CardTypeModel cardTypeModel) {
		String methodName = "addMccConfigForm";
		try {
			model.addAttribute("mccList", this.configurationService.getCfg_MCC());
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("cardTypeModel", cardTypeModel);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addMccConfigForm";
	}

	@RequestMapping(value = { "/addMCCConfig" }, method = { RequestMethod.POST })
	public String addMCCConfig(Model model, CardTypeModel cardTypeModel) {
		String methodName = "addMCCConfig";
		try {
			model.addAttribute("mccList", this.configurationService.getCfg_MCC());
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("cardTypeModel", cardTypeModel);
			int count = this.configurationService.updateMCC(cardTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) 
			{
				model.addAttribute("success", "MCC Added Successfully !");
			} else {
				model.addAttribute("error", "No Update For MCC ! !!");
			}
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While added MCC !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addMccConfigForm";
	}

	@RequestMapping(value = { "/addCardTokenConfigForm" }, method = { RequestMethod.GET })
	public String addCardTempConfigForm(Model model, CardTokenModel cardTokenModel) {
		String methodName = "addCardTempConfigForm";
		try {
			model.addAttribute("cardTypeList", this.configurationService.getCfgCardType());
			model.addAttribute("cfgCardToken", this.configurationService.getCardTokenList());
			model.addAttribute("cardTokenModel", cardTokenModel);
			model.addAttribute("lefttokenMenuID", TranecoStatusCode.CARD_TOKEN_CONFIG);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add card token !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addCardTokenConfigForm";
	}

	@RequestMapping(value = { "/addMobileTokenConfigForm" }, method = { RequestMethod.GET })
	public String addMobileTokenConfigForm(Model model, MobileTokenModel mobileTokenModel) {
		String methodName = "addCardTempConfigForm";
		try {
			model.addAttribute("mobileTokenModel", mobileTokenModel);
			model.addAttribute("lefttokenMenuID", TranecoStatusCode.MOBILE_TOKEN);
			model.addAttribute("mobileTokenList", this.configurationService.getMobileTokenList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add card token !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addMobileTokenConfigForm";
	}

	@RequestMapping(value = { "/isoConfigForm" }, method = { RequestMethod.GET })
	public String isoConfigForm(Model model, IsoConfigModel isoConfig) {
		String methodName = "addCardTempConfigForm";
		try {
			model.addAttribute("isoConfig", isoConfig);
			model.addAttribute("leftpartipantMenuID", TranecoStatusCode.ISO_CONFIG);
			model.addAttribute("isoConfigList", this.configurationService.getISOConfigList());
			model.addAttribute("connectionList", this.configurationService.getConnectionList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add card token !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "isoConfigForm";
	}

	@RequestMapping(value = { "/addISOConfigForm" }, method = { RequestMethod.POST })
	public String addISOConfigForm(Model model, IsoConfigModel isoConfig) {
		String methodName = "addIsoConfigForm";
		try 
		{
			model.addAttribute("isoConfig", isoConfig);
			model.addAttribute("leftpartipantMenuID", TranecoStatusCode.ISO_CONFIG);
			int count = this.configurationService.addIsoData(isoConfig);
			model.addAttribute("isoConfigList", this.configurationService.getISOConfigList());
			model.addAttribute("connectionList", this.configurationService.getConnectionList());
			model.addAttribute("display", "block");
			if (count > 0) 
			{
				model.addAttribute("success", "ISO Config Added Successfully !");
			}
			else 
			{
				model.addAttribute("error", "No Update For ISO Config !!");
			}
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add card token !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "isoConfigForm";
	}

	@RequestMapping(value = { "/connConfigForm" }, method = { RequestMethod.GET })
	public String connConfigForm(Model model, ConnectionConfigModel connConfig) {
		String methodName = "connConfigForm";
		try {
			model.addAttribute("connConfigModel", connConfig);
			model.addAttribute("leftpartipantMenuID", TranecoStatusCode.CONNECTION_CONFIG);
			model.addAttribute("participantList", this.configurationService.getParticipantList());
			model.addAttribute("connectionList", this.configurationService.getConnectionList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display Connection Configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "connConfigForm";
	}

	@RequestMapping(value = { "/addConnConfig" }, method = { RequestMethod.POST })
	public String addConnConfig(Model model, ConnectionConfigModel connConfig) {
		String methodName = "addConnConfig";
		try {
			model.addAttribute("participantList", this.configurationService.getParticipantList());
			int count = configurationService.addConnection(connConfig);
			if("client".equalsIgnoreCase(connConfig.getServiceType()))
			{
			String ip = connConfig.getIp();
			String port = connConfig.getPort();
			
			String url = ip.concat(":").concat(port).trim();
			connConfig.setIp(url);
			int endpointEntry = configurationService.addEndPointEntry(connConfig);
			System.out.println(endpointEntry);
			}
			if (count > 0) {
				connConfig = ConnectionConfigModel.builder().build();
				model.addAttribute("success", "Connection Config Added Successfully !");
			} else {
				model.addAttribute("error", "No Update For Connection Config !!");
			}
			model.addAttribute("connectionList", this.configurationService.getConnectionList());
			model.addAttribute("connConfigModel", connConfig);
			
			model.addAttribute("leftpartipantMenuID", TranecoStatusCode.CONNECTION_CONFIG);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Connection Configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "connConfigForm";
	}

	@RequestMapping(value = { "/partConfigForm" }, method = { RequestMethod.GET })
	public String partConfigForm(Model model, ParticipantConfigModel partConfig) {
		String methodName = "partConfigForm";
		try {
			model.addAttribute("partConfigModel", partConfig);
			model.addAttribute("leftpartipantMenuID", TranecoStatusCode.PARTICIPANT_CONFIG);
			model.addAttribute("participantList", this.configurationService.getParticipantConfigList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display participant configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "partConfigForm";
	}

	@RequestMapping(value = { "/addPartConfig" }, method = { RequestMethod.POST })
	public String addPartConfig(Model model, ParticipantConfigModel partConfig) {
		String methodName = "partConfigForm";
		try {
			int count = configurationService.addParticipant(partConfig);
			if (count > 0) {
				partConfig = ParticipantConfigModel.builder().build();
				model.addAttribute("success", "Participant Config Added Successfully !");
			} else {
				model.addAttribute("error", "No Update For Participant Config !!");
			}
			model.addAttribute("participantList", this.configurationService.getParticipantConfigList());
			model.addAttribute("partConfigModel", partConfig);
			model.addAttribute("leftpartipantMenuID", TranecoStatusCode.PARTICIPANT_CONFIG);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Participant Configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "partConfigForm";
	}

	@RequestMapping(value = { "/chargeModuleConfigForm" }, method = { RequestMethod.GET })
	public String chargeModuleConfigForm(Model model, ChargingModule chargeModule) {
		String methodName = "chargeModuleConfigForm";
		try {
			model.addAttribute("chargeConfigModel", chargeModule);
			model.addAttribute("ChargeTypeList", configurationService.getChargesTypeList());
			// model.addAttribute("accountTypeList",
			// configurationService.getAccountTypeList());
			model.addAttribute("cardTypeList", configurationService.getCardTypeList());
			// model.addAttribute("AccountTypeList",
			// configurationService.getAccountTypeList());
			// model.addAttribute("cardTypeList", configurationService.getCardTypeList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display charge module configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "chargeModuleConfigForm";
	}

	@RequestMapping(value = { "/addChargeModuleConfigForm" }, method = { RequestMethod.POST })
	public String addChargeModuleConfigForm(Model model, ChargingModule chargeModule) {
		String methodName = "chargeModuleConfigForm";
		try {
			model.addAttribute("chargeConfigModel", chargeModule);
			model.addAttribute("ChargeTypeList", configurationService.getChargesTypeList());
			// model.addAttribute("accountTypeList",
			// configurationService.getAccountTypeList());
			model.addAttribute("cardTypeList", configurationService.getCardTypeList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display charge module configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "chargeModuleConfigForm";
	}

	// Added by sunny Soni for showing add wallet screen Start'
	/*
	@RequestMapping(value = { "/addWalletConfigForm" }, method = { RequestMethod.GET })
	public String addWalletConfigForm(Model model, MerchantCategoryCodeMaster merchantCategoryCodeMaster) 
	{
		String methodName = "addWalletConfigForm";
		try 
		{
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_PARTICIPANT_WISE_WALLET);

			merchantCategoryCodeMaster.setStrParticipantID(sessionDTO.getParticipantid());
			
			List<MerchantCategoryCodeMaster> unSelectedMccList = this.configurationService.getUnSelectedAllMCC(merchantCategoryCodeMaster);
			if (unSelectedMccList!=null && unSelectedMccList.size() > 0)
			{
				model.addAttribute("unselectedMccList", unSelectedMccList);
				model.addAttribute("unselectedMccListJson",this.mapper.writeValueAsString(unSelectedMccList));
			}
			
			List<MerchantCategoryCodeMaster> selectedMccList = this.configurationService.getSelectedMCCList(merchantCategoryCodeMaster);			
			if (selectedMccList!=null && selectedMccList.size() > 0)
			{
				model.addAttribute("selectedMccList", selectedMccList);
			}
			
		} 
		catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addWalletConfigForm";
	}
	
	@RequestMapping(value = { "/addParticipantWiseWallet" }, method = { RequestMethod.POST })
	public String addParticipantWiseWallet(Model model, MerchantCategoryCodeMaster merchantCategoryCodeMaster) {
		String methodName = "addParticipantWiseWallet";
		try 
		{
			ParticipantWiseWalletMaster participantWiseWalletMaster = new ParticipantWiseWalletMaster();
			participantWiseWalletMaster.setStrMccCode(merchantCategoryCodeMaster.getStrMccCode());
			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			
			participantWiseWalletMaster = this.configurationService.addParticipantWiseWalletMaster(participantWiseWalletMaster);
			if (participantWiseWalletMaster.getStrID()!=null)
			{
				model.addAttribute("success", "Wallet Added Successfully for Participant !");
			}
			else
			{
				model.addAttribute("error", "error");
			}
			
			merchantCategoryCodeMaster.setStrParticipantID(sessionDTO.getParticipantid());
			
			List<MerchantCategoryCodeMaster> selectedMccList = this.configurationService.getSelectedMCCList(merchantCategoryCodeMaster);			
			if (selectedMccList!=null && selectedMccList.size() > 0)
			{
				model.addAttribute("selectedMccList", selectedMccList);
			}
						
			List<MerchantCategoryCodeMaster> unSelectedMccList = this.configurationService.getUnSelectedAllMCC(merchantCategoryCodeMaster);
			if (unSelectedMccList!=null && unSelectedMccList.size() > 0)
			{
				model.addAttribute("unselectedMccList", unSelectedMccList);
			}
		} 
		catch (Exception e)
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While display charge module configuration !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addWalletConfigForm";
	}
	
	@RequestMapping(value = { "/showAccTypeWalletConfigForm" }, method = { RequestMethod.GET })
	public String addAccTypeWalletConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		String methodName = "addAccTypeWalletConfigForm";
		try
		{
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_ACCOUNT_TYPE_WISE_WALLET);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());
			
			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());
			
			List<AccountTypeWiseBlockedMccMaster> accountTypeWiseBlockedMccMasters = this.configurationService.getAccountTypeWiseUnBlockedMccMaster(accountTypeWiseBlockedMccMaster);
			model.addAttribute("mccList", accountTypeWiseBlockedMccMasters);
			model.addAttribute("mccListJson",this.mapper.writeValueAsString(accountTypeWiseBlockedMccMasters));
		}
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accTypeWalletConfigForm";
	}
	
	@RequestMapping(value = { "/addBlockMccConfigForm" }, method = { RequestMethod.GET })
	public String addBlockMccConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) 
	{
		String methodName = "addBlockMccConfigForm";
		try
		{
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_BLOCK_MCC_CONFIG_FORM);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());
			
			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			
			List<ParticipantWiseWalletMaster> participantWiseWalletMasters = this.configurationService.getMCCListParticipantWise(participantWiseWalletMaster);
			model.addAttribute("mccList",participantWiseWalletMasters);
			model.addAttribute("mccListJson", this.mapper.writeValueAsString(participantWiseWalletMasters));
		}
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addBlockMccCfgForm";
	}
	
	@RequestMapping(value = { "/addBlockMccConfigFormData" }, method = { RequestMethod.POST })
	public String addBlockMccConfigFormData(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) 
	{
		try 
		{
			System.out.println("model::"+model);
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_BLOCK_MCC_CONFIG_FORM);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());

			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			
			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseBlockedMccMaster.setStrCreatedBy(sessionDTO.getLoginID());
			accountTypeWiseBlockedMccMaster.setStrAccounType(participantWiseWalletMaster.getStrAccountType());
			accountTypeWiseBlockedMccMaster.setStrBlockedMCCCode(participantWiseWalletMaster.getStrMccCode());
			
			accountTypeWiseBlockedMccMaster = this.configurationService.addAccountTypeWiseBlockedMccMaster(accountTypeWiseBlockedMccMaster);
			if (accountTypeWiseBlockedMccMaster.getStrID()!=null)
			{
				model.addAttribute("success", "MCC Blocked List Added Successfully!");
			}
			else
			{
				model.addAttribute("error", "error");
			}
			
			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			
			List<ParticipantWiseWalletMaster> participantWiseWalletMasters = this.configurationService.getMCCListParticipantWise(participantWiseWalletMaster);
			model.addAttribute("mccList",participantWiseWalletMasters);
			model.addAttribute("mccListJson", this.mapper.writeValueAsString(participantWiseWalletMasters));
			
		}
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			e.printStackTrace();
		}
		return "addBlockMccCfgForm";
	}
	
	@RequestMapping(value = { "/addAccountTypeWalletConfigForm" }, method = { RequestMethod.POST })
	public String addAccountTypeWalletConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) 
	{
		String formName = "accTypeWalletConfigForm";
		try 
		{
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_ACCOUNT_TYPE_WISE_WALLET);
			model.addAttribute("accountType", this.configurationService.getAccountTypeMasterDataList());
			
			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());
			List<AccountTypeWiseBlockedMccMaster> accountTypeWiseBlockedMccMasters = this.configurationService.getAccountTypeWiseUnBlockedMccMaster(accountTypeWiseBlockedMccMaster);
			model.addAttribute("mccList", accountTypeWiseBlockedMccMasters);
			model.addAttribute("mccListJson",this.mapper.writeValueAsString(accountTypeWiseBlockedMccMasters));
			
			String strMccCode = participantWiseWalletMaster.getStrMccCode();
			if (strMccCode == null) 
			{
				model.addAttribute("error", "NO MCC Code Selected.Please Select.");
				return formName;
			}
			
			AccountTypeWiseWalletMaster accountTypeWiseWalletMaster = new AccountTypeWiseWalletMaster();
			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			accountTypeWiseWalletMaster.setStrAccounType(participantWiseWalletMaster.getStrAccountType());
			accountTypeWiseWalletMaster.setStrMccCode(participantWiseWalletMaster.getStrMccCode());
			accountTypeWiseWalletMaster.setStrPercentage(participantWiseWalletMaster.getStrPercentage());
			
			accountTypeWiseWalletMaster = this.configurationService.addAccountTypeWiseWalletMaster(accountTypeWiseWalletMaster);
			if (accountTypeWiseWalletMaster.getStrID()!=null)
			{
				model.addAttribute("success", "Wallet Added Successfully for Participant !");
			}
			else
			{
				model.addAttribute("error", "error");
			}
			
			
			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			
			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
		}
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			e.printStackTrace();
		}
		return formName;
	}
	
	
	@RequestMapping(value = { "/addAccTypeWalletConfigForm" }, method = { RequestMethod.POST })
	public String addingAccTypeWalletConfigForm(Model model, ParticipantWiseWalletMaster participantWiseWalletMaster) {
		String methodName = "addAccTypeWalletConfigForm";
		try
		{
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.ADD_ACCOUNT_TYPE_WISE_WALLET);
			model.addAttribute("accountType", this.configurationService.getAccountType());
			
			AccountTypeWiseWalletMaster accountTypeWiseWalletMaster = new AccountTypeWiseWalletMaster();
			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			accountTypeWiseWalletMaster.setStrAccounType(participantWiseWalletMaster.getStrAccountType());
			
			accountTypeWiseWalletMaster = this.configurationService.addAccountTypeWiseWalletMaster(accountTypeWiseWalletMaster);
			if (accountTypeWiseWalletMaster.getStrID()!=null)
			{
				model.addAttribute("success", "Wallet Added Successfully for Participant !");
			}
			else
			{
				model.addAttribute("error", "error");
			}
			
			participantWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			participantWiseWalletMaster.setStrCreatedBy(sessionDTO.getLoginID());
			
			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			
			model.addAttribute("mccList", this.configurationService.getMCCListParticipantWise(participantWiseWalletMaster));
			
		}
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While adding MCC Config !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accTypeWalletConfigForm";
	}
	*/
	//Added by sunny Soni for showing add wallet screen End

	//Added by Prashant Tayde for Gl account Creation

	/*
	@RequestMapping(value = { "/glAccountCreationForm" }, method = { RequestMethod.GET })
	public String glAccountCreationForm(Model model, GLAccountCreation glAccountCreation)
	{
		String methodName = "glAccountCreationForm";
		try
		{
			model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.GL_ACCOUNT_CREATION);
			model.addAttribute("glAccountCreation", glAccountCreation);
		} 
		catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error !");
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "glAccountCreationForm";
	}
	
	// END
	
	 @RequestMapping(value = "/glAccountLoadingForm", method = RequestMethod.GET)
	 public String glLoadBalance(Model model, GLAccountLoadingMaster glAccountLoadingMaster)
	 {
	        String methodName = "glAccountLoading";
	        try 
	        {
	            model.addAttribute("glAccountLoadingMaster", glAccountLoadingMaster);
	            model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.GLACCOUNT_BALANCELOADING);
	            model.addAttribute("channel", this.configurationService.getChannels());
	            
	            GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
	            List<GLAccountTypeMaster> glAccountTypeMastersList = accountManagementService.getListOfGLAccountTypelist(glAccountTypeMaster);
	            
	            model.addAttribute("glAccountType", glAccountTypeMastersList);
	            model.addAttribute("glAccountTypeJson", this.mapper.writeValueAsString(glAccountTypeMastersList));
	        } 
	        catch (Exception e) {
	            model.addAttribute("display", "block");
	            model.addAttribute("exception", "Error occurs While add Service! or Account Not Found");
	            log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
	        }
	        return "glAccountLoadingForm";
	    }

	    @RequestMapping(value = "/glAccountLoading", method = RequestMethod.POST)
	    public String addGlLoadBalance(Model model, GLAccountLoadingMaster glAccountLoadingMaster) 
	    {
	        String methodName = "glAccountLoading";
	        try 
	        {
	        	model.addAttribute("glAccountLoadingMaster", glAccountLoadingMaster);
	            model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.GLACCOUNT_BALANCELOADING);
	            model.addAttribute("channel", this.configurationService.getChannels());
	            
	            GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
	            List<GLAccountTypeMaster> glAccountTypeMastersList = accountManagementService.getListOfGLAccountTypelist(glAccountTypeMaster);
	            
	            model.addAttribute("glAccountType", glAccountTypeMastersList);
	            model.addAttribute("glAccountTypeJson", this.mapper.writeValueAsString(glAccountTypeMastersList));
	            
	            String txnId = Utils.getGeneratedTransactionId();
	            
	            glAccountLoadingMaster.setStrTransactionId(txnId);
	            glAccountLoadingMaster.setStrParticipantId(sessionDTO.getParticipantid());
	            glAccountLoadingMaster.setStrAccountCreatedBy(sessionDTO.getLoginID());
	            
	            glAccountLoadingMaster.setStrTimeOfLoading(Utils.getLocalTime());
	            
	        	AccountTranMaster accountTranMaster = accountManagementService.addAccountTransactionMasterForGLAccount(glAccountLoadingMaster);
	        	if (accountTranMaster!=null)
	        	{
	        		accountManagementService.loadGLAccountInformationData(glAccountLoadingMaster);
	        		
	        		glAccountTypeMaster.setStrGLAccountType(glAccountLoadingMaster.getStrGLAccountType());
	        		glAccountTypeMaster.setStrAccountNumber(glAccountLoadingMaster.getStrAccountNumber());
	        		
	        		String closingBalance = accountManagementService.getClosingBalanceOfGlAccount(glAccountTypeMaster);
	        		Double closingBal = (closingBalance!=null) ? Double.parseDouble(closingBalance) : 0;
	        		Double updatedClosingBalance = closingBal + Double.parseDouble(glAccountLoadingMaster.getStrLoadedBalance());
	        		
	        		if(closingBalance == null) 
	        		{
	        			glAccountTypeMaster.setStrOpeningBalance(glAccountLoadingMaster.getStrLoadedBalance());
	        		}
	        		
	        		glAccountTypeMaster.setStrClosingBalance(String.valueOf(updatedClosingBalance));
	        		
	        		accountManagementService.updateGlaccountTypeDetails(glAccountTypeMaster);
	        		
	        		accountTranMaster.setStrAccountNumber(glAccountLoadingMaster.getStrAccountNumber());
	        		accountTranMaster.setStrAccountType(glAccountLoadingMaster.getStrGLAccountType());
	        		accountTranMaster.setStrCreatedBy(sessionDTO.getLoginID());
	        		accountManagementService.addAccountStatementForGLAccount(accountTranMaster, updatedClosingBalance + "");
	        		
	        		model.addAttribute("success", "Success: Balance Loaded Succesfully");
	        	}
	        } 
	        catch (Exception e)
	        {
	            model.addAttribute("display", "block");
	            model.addAttribute("exception", "Error occurs While add Service! or Account Not Found");
	            log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
	        }
	        return "glAccountLoadingForm";
	    }

	    @RequestMapping(value="/getAvailableBalanceGl",method=RequestMethod.POST, produces = "application/json")
		public ResponseEntity<?> getAvailableBalanceOfGL(@RequestBody GLAccountLoadingMaster glAccountLoadingMaster){
			try 
			{
				GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
				glAccountTypeMaster.setStrGLAccountType(glAccountLoadingMaster.getStrGLAccountType());
				glAccountTypeMaster.setStrAccountNumber(glAccountLoadingMaster.getStrAccountNumber());
				
				String availableBalance = accountManagementService.getClosingBalanceOfGlAccount(glAccountTypeMaster);
				System.out.println(availableBalance);
				return ResponseEntity.ok(availableBalance);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
    @RequestMapping(value = { "/glAccountStatementForm" },method= {RequestMethod.GET})
    public String glAccountStatementForm(Model model, GlAccountTypeWiseStatementMaster glAccountTypeWiseStatementMaster) 
    {
        String methodName = "glAccountStatementForm";
        try
        {
            model.addAttribute("leftGLAccountMenuID", TranecoStatusCode.GLACCOUNT_STATEMENT);
            model.addAttribute("glAccountStatementForm", glAccountTypeWiseStatementMaster);
            
            GLAccountTypeMaster glAccountTypeMaster = new GLAccountTypeMaster();
            List<GLAccountTypeMaster> glAccountTypeMastersList = accountManagementService.getListOfGLAccountTypelist(glAccountTypeMaster);
            
            model.addAttribute("glaccountTypelist", glAccountTypeMastersList);
        }
        catch (Exception e) 
        {
            model.addAttribute("display", "block");
            model.addAttribute("exception", "Error occurs While adding MCC Config !");
            this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
        }
        return "glAccountStatementForm";
    }
    */
	
}
