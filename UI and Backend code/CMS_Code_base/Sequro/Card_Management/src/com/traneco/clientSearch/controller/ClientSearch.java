package com.traneco.clientSearch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.traneco.clientSearch.model.AddressDetailsList;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardList;
import com.traneco.clientSearch.model.CardNCMCServiceList;
import com.traneco.clientSearch.model.CardStatusRequest;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DetailedClientCardRequest;
import com.traneco.clientSearch.model.DetailedClientCardResponse;
import com.traneco.clientSearch.model.EmailMaintainanceRequest;
import com.traneco.clientSearch.model.EnquireCardDetailsRequest;
import com.traneco.clientSearch.model.EnquireCardDetailsResponse;
import com.traneco.clientSearch.model.NCMCServiceList;
import com.traneco.clientSearch.model.NCMCServiceUpdationRequest;
import com.traneco.clientSearch.model.PhoneMaintainanceRequest;
import com.traneco.clientSearch.model.SearchClientCardRequest;
import com.traneco.clientSearch.model.SearchClientCardResponse;
import com.traneco.clientSearch.services.ClientSearchService;
import com.traneco.common.CardStatusCode;
import com.traneco.common.KeyValueBean;
import com.traneco.common.ResponseBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.logging.services.LoggingService;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.services.ConfigurationService;

@Controller
public class ClientSearch {

	@Autowired
	Environment env;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	LoggerUtil log;

	@Autowired
	ConfigurationService configurationService;

	@Autowired
	SearchClientCardRequest searchClientCardRequest;

	@Autowired
	DetailedClientCardResponse detailedClientCardResponse;

	@Autowired
	ClientSearchService clientSearchService;
	
	@Autowired
	LoggingService userLoggingService;

	private String className = this.getClass().getSimpleName();

	@RequestMapping(value = "/searchClient", method = RequestMethod.POST)
	public String searchClient(Model model, SearchClientCardRequest clientBean, BindingResult result) 
	{
		String methodName = "searchClient";
		SearchClientCardResponse response = null;
		
		try 
		{
			log.doLog(4, className, methodName, "Inside Method :");
			model.addAttribute("cardTypeList", configurationService.getCardType());
			clientBean.setStrParticipantID(sessionDTO.getParticipantid());
			response = clientSearchService.searchClient(clientBean);
			model.addAttribute("display", "block");
			sessionDTO.setCustomerID("");
			if (response != null) 
			{
				switch (response.getOutResponseCode()) 
				{
				case CardStatusCode.STATUS_SUCCESS:
					searchClientCardRequest = clientBean;
					model.addAttribute("success", response.getMessage());
					model.addAttribute("clientList", response.getSearchClientCardResponseList());
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} 
			else 
			{
				model.addAttribute("error", "No Data Found !!");
			}
			log.doLog(4, className, methodName, "End Inside Method :");
			
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.instAddForm.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		model.addAttribute("clientBean", clientBean);
		return "home";
	}

	@RequestMapping(value = "/clientDetailsForm", method = RequestMethod.GET)
	public String clientDetailsForm(Model model, CustomerDetails customerDetails, HttpSession session) {
		String methodName = "clientDetailsForm";
		try {
			// session.setAttribute("cid", customerDetails.getStrCustomerID());
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			request.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			if (customerDetails.getStrCustomerID() != null) 
			{
				request.setStrCustomerID(customerDetails.getStrCustomerID());
				// searchClientCardRequest.setStrCustomerID(customerDetails.getStrCustomerID());
				sessionDTO.setCustomerID(customerDetails.getStrCustomerID());
			}
			else if (searchClientCardRequest.getStrCustomerID() != null) 
			{
				request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
				sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID());
			}
			else if(sessionDTO.getCustomerID() != null) 
			{
				request.setStrCustomerID(sessionDTO.getCustomerID());
			}
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			customerDetails = detailedClientCardResponse.getCustomerDetails();
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CUSTOMER_DETAILS);
			model.addAttribute("clientDetails", detailedClientCardResponse.getCustomerDetails());
		} 
		catch (Exception e) 
		{
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "clientDetailsForm";
	}

	@RequestMapping(value = "/clientDetails", method = RequestMethod.POST)
	public String clientDetails(Model model, CustomerDetails customerDetails, HttpSession session) 
	{
		String methodName = "clientDetails";
		try 
		{
			model.addAttribute("display", "block");
			if (clientSearchService.updateClient(customerDetails) > 0) 
			{
				model.addAttribute("success", "Client Details Updated Successfully !");
			} else {
				model.addAttribute("error", "Client Details Update Failed !");
			}
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CUSTOMER_DETAILS);
			model.addAttribute("clientDetails", customerDetails);
		} 
		catch (Exception e) 
		{
			model.addAttribute("error", "Client Details Update Failed !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "clientDetailsForm";
	}

	@RequestMapping(value = "/cardDetails", method = { RequestMethod.POST, RequestMethod.GET })
	public String cardDetails(Model model, CardDetailsList cardList, HttpSession session) 
	{
		String methodName = "cardDetails";
		try 
		{
			if (cardList.getStrCardNumber() != null) 
			{
				session.setAttribute("cardNo", cardList.getStrCardNumber());
			}
			else 
			{
				cardList.setStrCardNumber(String.valueOf(session.getAttribute("cardNo")));
			}
			model.addAttribute("leftCardMenuID", TranecoStatusCode.LIMIT_MANAGEMENT);
			model.addAttribute("cardDetails", clientSearchService.getCard(cardList.getStrCardNumber(), null));
		} 
		catch (Exception e) 
		{
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardDetails";
	}

	@RequestMapping(value = "/cardStatusForm", method = RequestMethod.GET)
	public String cardStatusForm(Model model, CardList cardStatus, HttpSession session) 
	{
		String methodName = "cardStatusForm";
		try 
		{
			model.addAttribute("leftCardMenuID", TranecoStatusCode.CARD_STATUS_MANAGEMENT);
			cardStatus.setStrCardNumber(String.valueOf(session.getAttribute("cardNo")));
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrCustomerID(detailedClientCardResponse.getCustomerDetails().getStrCustomerID());
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			cardStatus = detailedClientCardResponse.getCardList().stream()
					.filter(data -> String.valueOf(session.getAttribute("cardNo")).equals(data.getStrCardNumber()))
					.findAny().orElse(null);
			model.addAttribute("cardStatusList", clientSearchService.getCardStatus(sessionDTO.getParticipantid()));
			if (cardStatus != null) 
			{
				if (!cardStatus.getStrExpiryDate().contains("/"))
					cardStatus.setStrExpiryDate(Utils.convertExpDate(cardStatus.getStrExpiryDate()));
			}
			EnquireCardDetailsRequest request1 = new EnquireCardDetailsRequest();
			request1.setStrCardNumber(cardStatus.getStrCardNumber());
			request1.setStrParticipantID(sessionDTO.getParticipantid());
			request1.setStrMemberNo(cardStatus.getStrCardSeqNumber());
			EnquireCardDetailsResponse enCardDetailsResponse = clientSearchService.getCardDetails(request1);
			model.addAttribute("cardStatusDataList", enCardDetailsResponse.getCardStatusDetailsList());
			model.addAttribute("cardStatus", cardStatus);
		} 
		catch (Exception e) 
		{
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardStatus";
	}

	@RequestMapping(value = "/cardStatus", method = RequestMethod.POST)
	public String cardStatus(Model model, CardList cardStatus, HttpSession session) 
	{
		String methodName = "cardStatus";
		ResponseBean response = null;
		try 
		{
			model.addAttribute("leftCardMenuID", TranecoStatusCode.CARD_STATUS_MANAGEMENT);
			CardStatusRequest cardStatusRequest = new CardStatusRequest();
			cardStatusRequest.setStrParticipant_ID(sessionDTO.getParticipantid());
			cardStatusRequest.setStrCardNo(cardStatus.getStrCardNumber());
			cardStatusRequest.setStrDescription(cardStatus.getStrDescription());
			cardStatusRequest.setStrMemberNo(cardStatus.getStrCardSeqNumber());
			cardStatusRequest.setStrStatusCode(cardStatus.getStrCardStatus());
			cardStatusRequest.setStrStatusChangeUser("" + sessionDTO.getLoginID());
			EnquireCardDetailsRequest request1 = new EnquireCardDetailsRequest();
			request1.setStrCardNumber(cardStatus.getStrCardNumber());
			request1.setStrParticipantID(sessionDTO.getParticipantid());
			request1.setStrMemberNo(cardStatus.getStrCardSeqNumber());

			model.addAttribute("cardStatusList", clientSearchService.getCardStatus(sessionDTO.getParticipantid()));
			model.addAttribute("cardStatus", cardStatus);
			response = clientSearchService.updateCardStatus(cardStatusRequest);
			EnquireCardDetailsResponse enCardDetailsResponse = clientSearchService.getCardDetails(request1);
			model.addAttribute("cardStatusDataList", enCardDetailsResponse.getCardStatusDetailsList());
			model.addAttribute("display", "block");
			if (response != null) 
			{
				switch (response.getOutResponseCode()) 
				{
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", response.getMessage());
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} 
			else 
			{
				model.addAttribute("error", "No Data Found !!");
			}

		} 
		catch (Exception e) 
		{
			model.addAttribute("error", "Error occurs While updating Card Status");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardStatus";
	}

	@RequestMapping(value = "/cardDetailUpdate", method = RequestMethod.POST)
	public String cardDetailUpdate(Model model, CardDetailsList cardDetails) {
		String methodName = "cardDetailUpdate";
		ResponseBean response = null;
		try {
			model.addAttribute("leftCardMenuID", TranecoStatusCode.LIMIT_MANAGEMENT);
			cardDetails.setStrPartID(sessionDTO.getParticipantid());
			model.addAttribute("cardDetails", cardDetails);
			response = clientSearchService.updateCard(cardDetails);
			model.addAttribute("display", "block");
			if (response != null) {
				switch (response.getOutResponseCode()) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Card Details Updated Successfully !");
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While updating Card !");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error occurs While updating Card !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardDetails";
	}

	@RequestMapping(value = "/cardDetailsForm", method = RequestMethod.GET)
	public String cardDetailsForm(Model model, CardList cardList) {
		String methodName = "cardDetailsForm";
		try {
			model.addAttribute("cardStatus", searchClientCardRequest.getStrCardNumber());
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			/*
			 * if(searchClientCardRequest.getStrCustomerID() != null){
			 * request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			 * sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID()); }else
			 */if (sessionDTO.getCustomerID() != null) 
			 {
				request.setStrCustomerID(sessionDTO.getCustomerID());
				searchClientCardRequest.setStrCustomerID(sessionDTO.getCustomerID());
			}
			request.setStrParticipantID(searchClientCardRequest.getStrParticipantID());
			request.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			request.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			// request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CARD_DETAILS);
			model.addAttribute("cardDetails", detailedClientCardResponse.getCardList());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardDetailsForm";
	}

	@RequestMapping(value = "/accountDetailsForm", method = RequestMethod.GET)
	public String accountDetailsForm(Model model) 
	{
		String methodName = "accountDetailsForm";
		try 
		{
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			/*
			 * if(searchClientCardRequest.getStrCustomerID() != null){
			 * request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			 * sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID()); }else
			 */ if (sessionDTO.getCustomerID() != null) 
			 {
				request.setStrCustomerID(sessionDTO.getCustomerID());
				searchClientCardRequest.setStrCustomerID(sessionDTO.getCustomerID());
			}

			request.setStrParticipantID(searchClientCardRequest.getStrParticipantID());
			request.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			request.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			// request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.ACCOUNT_DETAILS);
			model.addAttribute("accountDetails", detailedClientCardResponse.getAccountDetailsList());
			model.addAttribute("inPart", searchClientCardRequest.getStrParticipantID());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountDetailsForm";
	}

	@RequestMapping(value = "/addressDetailsForm", method = RequestMethod.GET)
	public String addressDetailsForm(Model model, AddressDetailsList addressDetailsBean) {
		String methodName = "addressDetailsForm";
		try {
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			/*
			 * if(searchClientCardRequest.getStrCustomerID() != null){
			 * request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			 * sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID()); }else
			 */if (sessionDTO.getCustomerID() != null) {
				request.setStrCustomerID(sessionDTO.getCustomerID());
				searchClientCardRequest.setStrCustomerID(sessionDTO.getCustomerID());
			}
			request.setStrParticipantID(searchClientCardRequest.getStrParticipantID());
			request.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			request.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			// request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.ADDRESS_DETAILS);
			model.addAttribute("addressDetails", detailedClientCardResponse.getAddressDetailsList());
			model.addAttribute("addressDetailsBean", addressDetailsBean);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addressDetailsForm";
	}

	@RequestMapping(value = "/addressDetailsUpdateForm", method = RequestMethod.POST)
	public String addressDetailsUpdateForm(Model model, AddressDetailsList addressDetailsBean) {
		String methodName = "addressDetailsUpdateForm";
		try {
			if (addressDetailsBean.getStrRequestData() != null) {
				String[] data = addressDetailsBean.getStrRequestData().split("\\|");
				addressDetailsBean.setStrAddress1(String.valueOf(data[0]));
				addressDetailsBean.setStrAddress2(String.valueOf(data[1]));
				addressDetailsBean.setStrAddress3(String.valueOf(data[2]));
				addressDetailsBean.setStrAddressPrimaryFlag(String.valueOf(data[3]));
				addressDetailsBean.setStrAddressType(String.valueOf(data[4]));
				addressDetailsBean.setStrCity(String.valueOf(data[5]));
				addressDetailsBean.setStrCountryCode(String.valueOf(data[6]));
				addressDetailsBean.setStrPinCode(String.valueOf(data[7]));
				addressDetailsBean.setStrState(String.valueOf(data[8]));
			} else {
				model.addAttribute("addFlag", "Y");
				addressDetailsBean.setStrAddFlag("Y");
			}
			model.addAttribute("addressDetailsBean", addressDetailsBean);
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("stateList", configurationService.getStateList(addressDetailsBean.getStrCountryCode()));
			model.addAttribute("cityList", configurationService.getCityList(addressDetailsBean.getStrState()));
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addressDetails";
	}

	@RequestMapping(value = "/addressDetailsUpdate", method = RequestMethod.POST)
	public String addressDetailsUpdate(Model model, AddressDetailsList addressDetailsBean) {
		String methodName = "addressDetailsUpdate";
		ResponseBean response = null;
		try {
			addressDetailsBean.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			addressDetailsBean.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			addressDetailsBean.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			if (addressDetailsBean.getStrAddFlag() != null && "Y".equals(addressDetailsBean.getStrAddFlag())) {
				addressDetailsBean.setStrFunctionType(TranecoStatusCode.REQTYPE_ADD);
			} else {
				addressDetailsBean.setStrFunctionType(TranecoStatusCode.REQTYPE_UPDATE);
			}

			addressDetailsBean.setStrParticipantID(sessionDTO.getParticipantid());
			model.addAttribute("addressDetailsBean", addressDetailsBean);
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("stateList", configurationService.getStateList(addressDetailsBean.getStrCountryCode()));
			model.addAttribute("cityList", configurationService.getCityList(addressDetailsBean.getStrState()));
			response = clientSearchService.addressUpdate(addressDetailsBean);
			model.addAttribute("display", "block");
			if (response != null) {
				switch (response.getOutResponseCode()) 
				{
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Address Updated Successfully !");
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While updating address !");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error occurs While updating address !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addressDetails";
	}

	@RequestMapping(value = "/contactDetailsForm", method = RequestMethod.GET)
	public String contactDetailsForm(Model model) {
		String methodName = "contactDetailsForm";
		try {
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			/*
			 * if(searchClientCardRequest.getStrCustomerID() != null){
			 * request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			 * sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID()); }else
			 */if (sessionDTO.getCustomerID() != null) {
				request.setStrCustomerID(sessionDTO.getCustomerID());
				searchClientCardRequest.setStrCustomerID(sessionDTO.getCustomerID());
			}
			request.setStrParticipantID(searchClientCardRequest.getStrParticipantID());
			request.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			request.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			// request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CONTACT_DETAILS);
			model.addAttribute("emailDetails", detailedClientCardResponse.getEmailDetailsList());
			model.addAttribute("phoneDetails", detailedClientCardResponse.getPhoneDetailsList());
			model.addAttribute("emailBean", new EmailMaintainanceRequest());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "contactDetailsForm";
	}

	@RequestMapping(value = "/emailDetailsForm", method = RequestMethod.POST)
	public String emailDetailsForm(Model model, EmailMaintainanceRequest emailBean) {
		String methodName = "searchClient";
		try {
			/*
			 * if(searchClientCardRequest.getStrCustomerID() != null){
			 * sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID()); }else
			 */if (sessionDTO.getCustomerID() != null) {
				searchClientCardRequest.setStrCustomerID(sessionDTO.getCustomerID());
			}
			if (emailBean.getStrRequestData() != null) {
				String[] data = emailBean.getStrRequestData().split("\\|");
				emailBean.setStrEmailID(String.valueOf(data[0]));
				emailBean.setStrEmailPrimaryFlag(String.valueOf(data[1]));
				emailBean.setStrEmailType(String.valueOf(data[2]));
				emailBean.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
				emailBean.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
				emailBean.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
				emailBean.setStrParticipantID(sessionDTO.getParticipantid());
			} else {
				emailBean.setStrEmailFlag("Y");
			}
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("emailBean", emailBean);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "emailDetailsForm";
	}

	@RequestMapping(value = "/emailUpdate", method = RequestMethod.POST)
	public String emailUpdate(Model model, EmailMaintainanceRequest emailBean) {
		String methodName = "emailUpdate";
		ResponseBean response = null;
		try {
			if ("Y".equals(emailBean.getStrEmailFlag())) {
				emailBean.setStrFunction(TranecoStatusCode.REQTYPE_ADD);
			} else {
				emailBean.setStrFunction(TranecoStatusCode.REQTYPE_UPDATE);
			}
			emailBean.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			emailBean.setStrParticipantID(sessionDTO.getParticipantid());
			model.addAttribute("emailBean", emailBean);
			model.addAttribute("emailList", configurationService.getEmail());
			response = clientSearchService.emailUpdate(emailBean);
			model.addAttribute("display", "block");
			if (response != null) {
				switch (response.getOutResponseCode()) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Email Updated Successfully !");
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While updating Email !");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error occurs While updating Email !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "emailDetailsForm";
	}

	@RequestMapping(value = "/phoneDetailsForm", method = RequestMethod.POST)
	public String phoneDetailsForm(Model model, PhoneMaintainanceRequest phoneBean) {
		String methodName = "phoneDetailsForm";
		try {
			if (phoneBean.getStrRequestData() != null) {
				String[] data = phoneBean.getStrRequestData().split("\\|");
				phoneBean.setStrPhoneNo(String.valueOf(data[0]));
				phoneBean.setStrPhonePrimaryFlag(String.valueOf(data[1]));
				phoneBean.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
				phoneBean.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
				phoneBean.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
				phoneBean.setStrParticipantID(sessionDTO.getParticipantid());
			} else {
				phoneBean.setStrPhnFlag("Y");
			}
			model.addAttribute("phoneBean", phoneBean);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "phoneDetailsForm";
	}

	@RequestMapping(value = "/phoneUpdate", method = RequestMethod.POST)
	public String phoneUpdate(Model model, PhoneMaintainanceRequest phoneBean) {
		String methodName = "phoneUpdate";
		ResponseBean response = null;
		try {
			if ("Y".equals(phoneBean.getStrPhnFlag())) {
				phoneBean.setStrFunction(TranecoStatusCode.REQTYPE_ADD);
			} else {
				phoneBean.setStrFunction(TranecoStatusCode.REQTYPE_UPDATE);
			}
			phoneBean.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			phoneBean.setStrParticipantID(sessionDTO.getParticipantid());
			model.addAttribute("phoneBean", phoneBean);
			response = clientSearchService.phoneUpdate(phoneBean);
			model.addAttribute("display", "block");
			if (response != null) {
				switch (response.getOutResponseCode()) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Phone Updated Successfully !");
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While updating Phone !");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error occurs While updating Phone !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "phoneDetailsForm";
	}

	@RequestMapping(value = "/pinManagementForm", method = { RequestMethod.POST, RequestMethod.GET })
	public String pinManagementForm(Model model, HttpSession session, CardDetails cardBean) {
		String methodName = "pinManagementForm";
		try {
			model.addAttribute("leftCardMenuID", TranecoStatusCode.PIN_MANAGEMENT);
			CardList card = detailedClientCardResponse.getCardList().stream()
					.filter(data -> String.valueOf(session.getAttribute("cardNo")).equals(data.getStrCardNumber()))
					.findAny().orElse(null);
			EnquireCardDetailsRequest request = new EnquireCardDetailsRequest();
			request.setStrCardNumber(card.getStrCardNumber());
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrMemberNo(card.getStrCardSeqNumber());
			EnquireCardDetailsResponse enCardDetailsResponse = clientSearchService.getCardDetails(request);
			CardDetails cardDetails = enCardDetailsResponse.getCardDetails();
			if (cardDetails == null) {
				model.addAttribute("cardBean", new CardDetails());
			} else {
				cardDetails.setStrTokenCard(card.getStrCardNumber());
				model.addAttribute("cardBean", cardDetails);
			}
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "pinManagementForm";
	}

	@RequestMapping(value = "/pinManagement", method = { RequestMethod.POST, RequestMethod.POST })
	public String pinManagement(Model model, HttpSession session, CardDetails cardBean) {
		String methodName = "pinManagement";
		ResponseBean response = null;
		try {
			model.addAttribute("leftCardMenuID", TranecoStatusCode.PIN_MANAGEMENT);
			cardBean.setStrPartID(sessionDTO.getParticipantid());
			cardBean.setStrUserID("" + sessionDTO.getUserID());
			model.addAttribute("cardBean", cardBean);
			cardBean.setStrCardNumber(cardBean.getStrTokenCard());
			response = clientSearchService.updatePinMailer(cardBean);
			model.addAttribute("display", "block");
			if (response != null) {
				switch (response.getOutResponseCode()) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Pin Details Updated Successfully !");
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While updating Pin Details !");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Error occurs While updating Pin Details !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "pinManagementForm";
	}

	@RequestMapping(value = "/plasticManagementForm", method = { RequestMethod.POST, RequestMethod.GET })
	public String plasticManagementForm(Model model, HttpSession session, CardDetails cardBean) {
		String methodName = "plasticManagementForm";
		try {
			model.addAttribute("leftCardMenuID", TranecoStatusCode.PLASTIC_MANAGEMENT);
			CardList card = detailedClientCardResponse.getCardList().stream()
					.filter(data -> String.valueOf(session.getAttribute("cardNo")).equals(data.getStrCardNumber()))
					.findAny().orElse(null);
			EnquireCardDetailsRequest request = new EnquireCardDetailsRequest();
			request.setStrCardNumber(card.getStrCardNumber());
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrMemberNo(card.getStrCardSeqNumber());
			EnquireCardDetailsResponse enCardDetailsResponse = clientSearchService.getCardDetails(request);
			CardDetails cardDetails = enCardDetailsResponse.getCardDetails();
			if (cardDetails == null)
				model.addAttribute("cardBean", new CardDetails());
			else
				model.addAttribute("cardBean", cardDetails);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "plasticManagementForm";
	}

	@RequestMapping(value = "/serviceManagementForm", method = { RequestMethod.GET })
	public String serviceManagementForm(Model model, HttpSession session, NCMCServiceUpdationRequest ncmcRequest) {
		String methodName = "serviceManagementForm";
		try {
			CardList card = detailedClientCardResponse.getCardList().stream()
					.filter(data -> String.valueOf(session.getAttribute("cardNo")).equals(data.getStrCardNumber()))
					.findAny().orElse(null);
			EnquireCardDetailsRequest request = new EnquireCardDetailsRequest();
			request.setStrCardNumber(card.getStrCardNumber());
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrMemberNo(card.getStrCardSeqNumber());
			EnquireCardDetailsResponse enCardDetailsResponse = clientSearchService.getCardDetails(request);

			List<KeyValueBean> ncmcList = clientSearchService
					.getNCMCService(String.valueOf(sessionDTO.getParticipantid()), request.getStrCardNumber());
			for (CardNCMCServiceList list : enCardDetailsResponse.getCardNCMCServiceList()) {
				KeyValueBean keyValueBean = ncmcList.stream()
						.filter(x -> list.getStrNCMCServiceId().equals(x.getLkpkey())).findAny().orElse(null);
				if (keyValueBean != null) 
				{
					keyValueBean.setLkpvalue("selected");
				}
			}
			model.addAttribute("cardDetails", enCardDetailsResponse.getCardDetails());
			model.addAttribute("leftCardMenuID", TranecoStatusCode.SERVICE_MANAGEMENT);
			model.addAttribute("serviceList", ncmcList);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "serviceManagementForm";
	}

	@RequestMapping(value = "/mccManagementForm", method = { RequestMethod.GET })
	public String mccManagementForm(Model model, HttpSession session, CardTypeModel cardTypeModel) {
		String methodName = "mccManagementForm";
		try {
			CardList card = detailedClientCardResponse.getCardList().stream()
					.filter(data -> String.valueOf(session.getAttribute("cardNo")).equals(data.getStrCardNumber()))
					.findAny().orElse(null);
			cardTypeModel.setStrSelectID(card.getStrCardNumber());
			model.addAttribute("cardTypeModel", cardTypeModel);
			model.addAttribute("mccList", configurationService.getCard_Type_MCC(card.getStrCardType()));
			model.addAttribute("leftCardMenuID", TranecoStatusCode.MCC_MANAGEMENT);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "mccManagementForm";
	}

	@RequestMapping(value = "/mccManagement", method = { RequestMethod.POST })
	public String mccManagement(Model model, HttpSession session, CardTypeModel cardTypeModel) {
		String methodName = "mccManagement";
		try {
			CardList card = detailedClientCardResponse.getCardList().stream()
					.filter(data -> String.valueOf(session.getAttribute("cardNo")).equals(data.getStrCardNumber()))
					.findAny().orElse(null);
			cardTypeModel.setStrSelectID(card.getStrCardNumber());
			model.addAttribute("cardTypeModel", cardTypeModel);
			model.addAttribute("mccList", configurationService.getCard_Type_MCC(card.getStrCardType()));
			model.addAttribute("leftCardMenuID", TranecoStatusCode.MCC_MANAGEMENT);
			int count = clientSearchService.addCardMCC(cardTypeModel);
			model.addAttribute("display", "block");
			if (count > 0) {
				model.addAttribute("success", "MCC Added Successfully !");
			} else {
				model.addAttribute("error", "No Update For MCC ! !!");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("error", "Error occurs While added MCC !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "mccManagementForm";
	}

	@RequestMapping(value = "/serviceManagement", method = { RequestMethod.POST })
	public String serviceManagement(Model model, HttpSession session, NCMCServiceUpdationRequest ncmcRequest) {
		String methodName = "serviceManagement";
		ResponseBean response = null;
		try {

			List<NCMCServiceList> ncmcList = new ArrayList<NCMCServiceList>();
			for (String s : ncmcRequest.getNcmcList()) {
				NCMCServiceList ncm = new NCMCServiceList();
				ncm.setStrNCMCServiceID(s);
				ncmcList.add(ncm);
			}
			ncmcRequest.setNCMCServiceList(ncmcList);

			response = clientSearchService.updateNCMCService(ncmcRequest);
			model.addAttribute("display", "block");

			if (response != null) {
				switch (response.getOutResponseCode()) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "NCMC Service Updated Successfully !");
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While NCMC Service !");
			}

			EnquireCardDetailsRequest request = new EnquireCardDetailsRequest();
			request.setStrCardNumber(ncmcRequest.getStrCardNo());
			request.setStrParticipantID(ncmcRequest.getStrParticipantID());
			request.setStrMemberNo(ncmcRequest.getStrMemberNo());
			EnquireCardDetailsResponse enCardDetailsResponse = clientSearchService.getCardDetails(request);

			List<KeyValueBean> ncmList = clientSearchService
					.getNCMCService(String.valueOf(sessionDTO.getParticipantid()), request.getStrCardNumber());
			for (CardNCMCServiceList list : enCardDetailsResponse.getCardNCMCServiceList()) {
				KeyValueBean keyValueBean = ncmList.stream()
						.filter(x -> list.getStrNCMCServiceId().equals(x.getLkpkey())).findAny().orElse(null);
				if (keyValueBean != null) {
					keyValueBean.setLkpvalue("selected");
				}
			}
			model.addAttribute("cardDetails", enCardDetailsResponse.getCardDetails());
			model.addAttribute("serviceList", ncmList);
			model.addAttribute("leftCardMenuID", TranecoStatusCode.SERVICE_MANAGEMENT);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "serviceManagementForm";
	}

	@RequestMapping(value = "/documentDetailsForm", method = RequestMethod.GET)
	public String documentDetailsForm(Model model) {
		String methodName = "documentDetailsForm";
		try {
			model.addAttribute("cardStatus", searchClientCardRequest.getStrCardNumber());
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			/*
			 * if(searchClientCardRequest.getStrCustomerID() != null){
			 * request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			 * sessionDTO.setCustomerID(searchClientCardRequest.getStrCustomerID()); }else
			 */if (sessionDTO.getCustomerID() != null) {
				request.setStrCustomerID(sessionDTO.getCustomerID());
				searchClientCardRequest.setStrCustomerID(sessionDTO.getCustomerID());
			}
			request.setStrParticipantID(searchClientCardRequest.getStrParticipantID());
			request.setStrCIFKey(searchClientCardRequest.getStrCIFKey());
			request.setStrCitizenID(searchClientCardRequest.getStrCitizenID());
			request.setStrCustomerID(searchClientCardRequest.getStrCustomerID());
			detailedClientCardResponse = clientSearchService.clientDetails(request);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.DOCUMENT_DETAILS);
			model.addAttribute("documentDetails", detailedClientCardResponse.getDocumentDetailsList());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
			model.addAttribute("customerID", sessionDTO.getCustomerID());
			model.addAttribute("partID", sessionDTO.getParticipantid());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "documentDetailsForm";
	}

}
