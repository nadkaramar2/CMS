package com.traneco.service.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DetailedClientCardRequest;
import com.traneco.clientSearch.model.DetailedClientCardResponse;
import com.traneco.clientSearch.model.SearchClientCardRequest;
import com.traneco.clientSearch.services.ClientSearchService;
import com.traneco.common.CardStatusCode;
import com.traneco.common.ResponseBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.logger.model.UserLoggingModel;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.AccountDetails;
import com.traneco.configuration.model.AccountLinkedWalletMaster;
import com.traneco.configuration.model.AccountTypeCharges;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.AccountTypeWiseBlockedMccMaster;
import com.traneco.configuration.model.AccountTypeWiseWalletMaster;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.keyEncrypt.model.EncryptKeyModel;
import com.traneco.keyEncrypt.service.EncryptKeyDataService;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountRequest;
import com.traneco.service.model.BulkUpload;
import com.traneco.service.model.Client;
import com.traneco.service.model.CreditCardCustomerAccountCreationModel;
import com.traneco.service.model.CustomerAdditionResponse;
import com.traneco.service.model.EmbossRequest;
import com.traneco.service.model.PinPrintingModel;
import com.traneco.service.model.ServiceBean;
import com.traneco.service.services.ClientService;

@Controller
public class ServiceController {

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
	EncryptKeyDataService encryptKeyDataService;

	private String className = this.getClass().getSimpleName();

	@RequestMapping(value = "/addClientForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String addClientForm(Model model, Client client) {
		String methodName = "addClientForm";
		try {
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("countryList", configurationService.getCountry());
			sessionDTO.setCustomerID("");
			/* model.addAttribute("stateList",configurationService.getState()); */
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add client !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addClientForm";
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public String addClient(Model model, Client client) 
	{
		String methodName = "addClient";
		String action = "addClientForm";
		try 
		{
			client.setStrParticipantID(sessionDTO.getParticipantid());
			client.setStrParticipantID("6");
			log.doLog(4, className, methodName, "Start add Client Method");
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("countryList", configurationService.getCountry());
			/* model.addAttribute("stateList",configurationService.getState()); */
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
			CustomerAdditionResponse response = clientService.addClient(client);

			if (response != null) 
			{
			   switch (response.getOutResponseCode()) 
			  {
			   
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Client Added Successfully !");
					sessionDTO.setCustomerID(response.getOutcustomerid());
					DetailedClientCardRequest request = new DetailedClientCardRequest();
					request.setStrParticipantID(sessionDTO.getParticipantid());
					request.setStrCustomerID(response.getOutcustomerid());
					searchClientCardRequest.setStrCustomerID(response.getOutcustomerid());
					DetailedClientCardResponse detailedClientCardResponse = clientSearchService.clientDetails(request);
					model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CUSTOMER_DETAILS);
					model.addAttribute("customerDetails", detailedClientCardResponse.getCustomerDetails());
					action = "clientDetailsForm";
					/*
					 * if(client.getStrSelectID() !=null && client.getStrSelectID().length() > 0) 
					 * {
					 * int count = clientService.updateCustomerID(response.getOutcustomerid(),
					 * client.getStrSelectID()); log.doLog(4, className, methodName,
					 * "Update customer id "+response.getOutcustomerid()+" for card "+client.
					 * getStrSelectID()+" result is "+count); client.setStrSelectID(null); }
					 */
					
					
					 //Client accountCreation = accountManagementService.accountCreation(client);
					
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} 
			else 
			{
				model.addAttribute("error", "Error occurs While adding client !");
			}
			log.doLog(4, className, methodName, "End add Client Method");
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add client !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return action;
	}

	@RequestMapping(value = "/updateClientForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateClientForm(Model model, Client client) {
		String methodName = "updateClientForm";
		try {
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("countryList", configurationService.getCountry());
			sessionDTO.setCustomerID("");
			/* model.addAttribute("stateList",configurationService.getState()); */
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add client !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "updateClientForm";
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public String updateClient(Model model, Client client) {
		String methodName = "updateClient";
		String action = "addClientForm";
		try {
			client.setStrParticipantID(sessionDTO.getParticipantid());
			log.doLog(4, className, methodName, "Start add Client Method");
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("countryList", configurationService.getCountry());
			/* model.addAttribute("stateList",configurationService.getState()); */
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
			CustomerAdditionResponse response = clientService.addClient(client);

			if (response != null) {
				switch (response.getOutResponseCode()) 
				{
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Client Updated Successfully !");
					sessionDTO.setCustomerID(response.getOutcustomerid());
					DetailedClientCardRequest request = new DetailedClientCardRequest();
					request.setStrParticipantID(sessionDTO.getParticipantid());
					request.setStrCustomerID(response.getOutcustomerid());
					searchClientCardRequest.setStrCustomerID(response.getOutcustomerid());
					sessionDTO.setCustomerID(response.getOutcustomerid());
					DetailedClientCardResponse detailedClientCardResponse = clientSearchService.clientDetails(request);
					model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CUSTOMER_DETAILS);
					model.addAttribute("customerDetails", detailedClientCardResponse.getCustomerDetails());
					action = "clientDetailsForm";
					/*
					 * if(client.getStrSelectID() !=null && client.getStrSelectID().length() > 0) {
					 * int count = clientService.updateCustomerID(response.getOutcustomerid(),
					 * client.getStrSelectID()); log.doLog(4, className, methodName,
					 * "Update customer id "+response.getOutcustomerid()+" for card "+client.
					 * getStrSelectID()+" result is "+count); client.setStrSelectID(null); }
					 */
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While adding client !");
			}
			log.doLog(4, className, methodName, "End add Client Method");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add client !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return action;
	}

	@RequestMapping(value = "/addInstantCardsForm", method = RequestMethod.GET)
	public String instantCardsForm(Model model, ServiceBean serviceBean) {
		String methodName = "instantCardsForm";
		try {
			serviceBean.setInEmbossLine1(TranecoStatusCode.EMBOSS_LINE1);
			serviceBean.setInEncodeFirstName(TranecoStatusCode.ENCODE_FIRST);
			serviceBean.setInEncodeLastName(TranecoStatusCode.ENCODE_LAST);
			serviceBean.setInEmbossLine1(TranecoStatusCode.EMBOSS_LINE1);
			model.addAttribute("leftCardMenuID", TranecoStatusCode.INSTANT_CARD_MANAGEMENT);
			model.addAttribute("cardTypeList", configurationService.getCardType());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "instantCardsForm";
	}

	@RequestMapping(value = "/addInstantCards", method = RequestMethod.POST)
	public String addInstantCards(Model model, ServiceBean serviceBean) {
		String methodName = "addInstantCards";
		String action = "instantCardsForm";

		try {
			log.doLog(4, className, methodName, "Start add Service Method");
			serviceBean.setInPartID(sessionDTO.getParticipantid());
			model.addAttribute("cardTypeList", configurationService.getCardType());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
			serviceBean.setInCustomerID(sessionDTO.getCustomerID());
			serviceBean.setInUserID("" + sessionDTO.getLoginID());
			model.addAttribute("leftCardMenuID", TranecoStatusCode.INSTANT_CARD_MANAGEMENT);
			serviceBean.setInFunction(TranecoStatusCode.INSTANT_CARD_FUNCTION);
			serviceBean.setInInstantFlag("Y");
			serviceBean.setInCard(null);
			serviceBean.setInCardType(serviceBean.getInBulkCardType());

			model.addAttribute("display", "block");
			ResponseBean response = null;
			int instantData = clientSearchService.addInstantInfo(serviceBean);
			if (serviceBean.getInQty() != null && serviceBean.getInQty().length() > 0) {
				int count = Integer.parseInt(serviceBean.getInQty());
				for (int i = 0; i < count; i++) {
					response = clientService.addService(serviceBean);
					if (!TranecoStatusCode.STATUS_SUCCESS.equals(response.getOutResponseCode())) {
						break;
					}
					log.doLog(4, className, methodName, "Instant Card Response :" + response.toString());
				}
			}
			if (!TranecoStatusCode.STATUS_SUCCESS.equals(response.getOutResponseCode())) {
				model.addAttribute("error", response.getMessage());
			} 
			else 
			{
				model.addAttribute("success", "Instant Card Generate Successfully");
			}
			log.doLog(4, className, methodName, "End add Service Method");
		} 
		catch (Exception e) 
		{
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return action;
	}

	@RequestMapping(value = "/updateInstantCardsForm", method = RequestMethod.GET)
	public String updateInstantCardsForm(Model model, ServiceBean serviceBean) {
		String methodName = "updateInstantCardsForm";
		try {
			model.addAttribute("leftCardMenuID", TranecoStatusCode.UPDATE_INSTANT_CARD);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "updateInstantCardsForm";
	}

	@RequestMapping(value = "/updateInstantCards", method = RequestMethod.POST)
	public String updateInstantCards(Model model, ServiceBean serviceBean) {
		String methodName = "updateInstantCards";
		try {
			model.addAttribute("leftCardMenuID", TranecoStatusCode.UPDATE_INSTANT_CARD);
			model.addAttribute("display", "block");
			List<ServiceBean> cardList = clientService.getCard(serviceBean.getInCard());
			if (cardList.isEmpty()) {
				model.addAttribute("error", "Instant Card Not Present !");
			} else {
				if (cardList.get(0).getInCustomerID() != null && cardList.get(0).getInCustomerID().length() > 0) {
					model.addAttribute("error",
							"Instant Card Already Personalized Or Updated ! Please Go To Search Menu And Search Details !");
					cardList = null;
				}
			}
			model.addAttribute("cardNo", serviceBean.getInCard());
			model.addAttribute("cardList", cardList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "updateInstantCardsForm";
	}

	@RequestMapping(value = "/addServiceForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String addServiceForm(Model model, ServiceBean serviceBean) 
	{
		String methodName = "addServiceForm";
		try
		{
			serviceBean.setInCustomerID(sessionDTO.getCustomerID());
			model.addAttribute("cardList", clientService.getCardList(sessionDTO.getCustomerID()));
			model.addAttribute("cardTypeList", configurationService.getCardType());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrCustomerID(sessionDTO.getCustomerID());
			DetailedClientCardResponse detailedClientCardResponse = clientSearchService.clientDetails(request);
			CustomerDetails customerDetails = detailedClientCardResponse.getCustomerDetails();
			serviceBean.setCitizenID(customerDetails.getStrCitizenID());
			serviceBean.setCifKey(customerDetails.getStrCIFKey());
			serviceBean.setInEmbossLine1(customerDetails.getStrFirstName() == null ? ""
					: customerDetails.getStrFirstName() + " " + customerDetails.getStrLastName() == null ? ""
							: customerDetails.getStrLastName());
			serviceBean.setInEmbossLine2("");
			serviceBean.setInEncodeFirstName(customerDetails.getStrFirstName());
			serviceBean.setInEncodeMiddleName(customerDetails.getStrMiddleName());
			serviceBean.setInEncodeLastName(customerDetails.getStrLastName());
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addServiceForm";
	}

	@RequestMapping(value = "/addService", method = RequestMethod.POST)
	public String addService(Model model, ServiceBean serviceBean) 
	{
		String methodName = "addService";
		String action = "clientDetailsForm";
		
		try 
		{
			log.doLog(4, className, methodName, "Start add Service Method");
			serviceBean.setInPartID(sessionDTO.getParticipantid());
			model.addAttribute("cardTypeList", configurationService.getCardType());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
			serviceBean.setInCustomerID(sessionDTO.getCustomerID());
			serviceBean.setInUserID("" + sessionDTO.getLoginID());
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrCustomerID(sessionDTO.getCustomerID());
			DetailedClientCardResponse detailedClientCardResponse = clientSearchService.clientDetails(request);
			CustomerDetails customerDetails = detailedClientCardResponse.getCustomerDetails();
			model.addAttribute("customerDetails", customerDetails);
			// action = "addServiceForm";
			model.addAttribute("cardList", clientService.getCardList(sessionDTO.getCustomerID()));

			switch (serviceBean.getInCardIssurance()) {
			case "1":
				serviceBean.setInFunction(TranecoStatusCode.NEW_CARD_FUNCTION);
				action = "addAccountForm";
				serviceBean.setInCard(null);
				model.addAttribute("serviceBean", serviceBean);
				break;
			case "2":
				serviceBean.setInFunction(TranecoStatusCode.REPLACEMENT_CARD_FUNCTION);
				String[] data = serviceBean.getInCard().split("\\|");
				serviceBean.setInCard(data[0]);
				serviceBean.setInMbr(data[1]);
				model.addAttribute("serviceBean", serviceBean);
				break;
			case "3":
				serviceBean.setInFunction(TranecoStatusCode.RENEW_CARD_FUNCTION);
				String[] data2 = serviceBean.getInCard().split("\\|");
				serviceBean.setInCard(data2[0]);
				serviceBean.setInMbr(data2[1]);
				serviceBean.setInCardType(data2[2]);
				model.addAttribute("serviceBean", serviceBean);
				break;
			case "4":
				// serviceBean.setInFunction(TranecoStatusCode.REPLACEMENT_CARD_FUNCTION);
				serviceBean.setInFunction(TranecoStatusCode.RENEW_WITH_NEW_CARD_FUNCTION);
				String[] data1 = serviceBean.getInCard().split("\\|");
				serviceBean.setInCard(data1[0]);
				serviceBean.setInMbr(data1[1]);
				serviceBean.setInCardType(data1[2]);
				model.addAttribute("serviceBean", serviceBean);
				break;
			case "5":
				serviceBean.setInFunction(TranecoStatusCode.INSTANT_CARD_FUNCTION);
				serviceBean.setInInstantFlag("Y");
				serviceBean.setInCard(null);
				serviceBean.setInCardType(serviceBean.getInBulkCardType());
				break;
			default:
				break;
			}

			model.addAttribute("display", "block");
			if (serviceBean.getInQty() != null && serviceBean.getInQty().length() > 0) {
				int count = Integer.parseInt(serviceBean.getInQty());
				for (int i = 0; i < count; i++) {
					ResponseBean response = clientService.addService(serviceBean);
				}
				model.addAttribute("success", "Instant Card Generate Successfully");
			} else {
				ResponseBean response = clientService.addService(serviceBean);
				if (response != null) {
					switch (response.getOutResponseCode()) {
					case CardStatusCode.STATUS_SUCCESS:

						switch (serviceBean.getInCardIssurance()) {
						case "1":
							model.addAttribute("success", new StringBuffer("New Card Generated Successfully ! ")
									.append(response.getOutCard() == null ? "" : response.getOutCard()).toString());
							break;
						case "2":
							model.addAttribute("success", new StringBuffer("Card Replaced Successfully ! ")
									.append(response.getOutCard() == null ? "" : response.getOutCard()).toString());
							break;
						case "3":
							model.addAttribute("success",
									new StringBuffer("Renewal With Same Card Generated Successfully ! ")
											.append(response.getOutCard() == null ? "" : response.getOutCard())
											.toString());
							break;
						case "4":
							model.addAttribute("success",
									new StringBuffer("Renewal With New Card Generated Successfully ! ")
											.append(response.getOutCard() == null ? "" : response.getOutCard())
											.toString());
							break;
						case "5":
							model.addAttribute("success", new StringBuffer("Card Added Successfully ! ")
									.append(response.getOutCard() == null ? "" : response.getOutCard()).toString());
							break;
						default:
							break;
						}

						AccountRequest accountRequest = new AccountRequest();
						accountRequest.setStrCardNumber(response.getOutTokenCard());
						accountRequest.setStrCardMask(response.getOutCard());
						accountRequest.setCitizenID(serviceBean.getCitizenID());
						accountRequest.setCifKey(serviceBean.getCifKey());
						model.addAttribute("accountBean", accountRequest);
						model.addAttribute("accountList", configurationService.getAccount());
						model.addAttribute("currencyList", clientService.getCurrencyList());
						model.addAttribute("branchCodeList", configurationService.getBranchCode());
						break;
					default:
						model.addAttribute("error", response.getMessage());
						action = "addServiceForm";
						break;
					}
				} else {
					model.addAttribute("error", "Error occurs While adding Service !");
					action = "addServiceForm";
				}
			}

			log.doLog(4, className, methodName, "End add Service Method");
		} catch (Exception e) {
			action = "addServiceForm";
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return action;
	}

	@RequestMapping(value = "/addAccountForm", method = RequestMethod.POST)
	public String addAccountForm(Model model, AccountRequest accountBean) {
		String methodName = "addAccountForm";
		String action = "addAccountForm";

		try {
			log.doLog(4, className, methodName, "Start add Service Method");
			model.addAttribute("accountList", configurationService.getAccount());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
			model.addAttribute("inCustomerID", sessionDTO.getCustomerID());
			model.addAttribute("accountBean", accountBean);
			model.addAttribute("currencyList", clientService.getCurrencyList());
			//model.addAttribute("accountTypeList", accountManagementService.getAccountTypeList());
			DetailedClientCardRequest request = new DetailedClientCardRequest();
			request.setStrParticipantID(sessionDTO.getParticipantid());
			request.setStrCustomerID(sessionDTO.getCustomerID());
			DetailedClientCardResponse detailedClientCardResponse = clientSearchService.clientDetails(request);
			CustomerDetails customerDetails = detailedClientCardResponse.getCustomerDetails();
			accountBean.setCitizenID(customerDetails.getStrCitizenID());
			accountBean.setCifKey(customerDetails.getStrCIFKey());
			ServiceBean serviceBean = new ServiceBean();
			serviceBean.setInCustomerID(sessionDTO.getCustomerID());
			model.addAttribute("serviceBean", serviceBean);
			log.doLog(4, className, methodName, "End add Service Method");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Account !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return action;
	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String addAccount(Model model, AccountRequest accountBean) 
	{
		String methodName = "addAccount";
		String action = "addAccountForm";
		try 
		{
			log.doLog(4, className, methodName, "Start add Service Method");
			model.addAttribute("accountList", configurationService.getAccount());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
			model.addAttribute("currencyList", clientService.getCurrencyList());
			accountBean.setStrParticipantID(sessionDTO.getParticipantid());
			accountBean.setStrFunctionType("LINK");
			accountBean.setStrCardSeqNumber("1");
			if (accountBean.getStrPrimaryFlag() == null) {
				accountBean.setStrPrimaryFlag("N");
			}
			model.addAttribute("display", "block");
			ResponseBean response = clientService.addAccount(accountBean);
			if (response != null) {
				switch (response.getOutResponseCode()) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", "Account Added Successfully !");
					AccountRequest accountRequest = new AccountRequest();
					accountRequest.setStrCardNumber(response.getOutCard());
					model.addAttribute("accountBean", accountRequest);
					DetailedClientCardRequest request = new DetailedClientCardRequest();
					request.setStrParticipantID(sessionDTO.getParticipantid());
					request.setStrCustomerID(sessionDTO.getCustomerID());
					DetailedClientCardResponse detailedClientCardResponse = clientSearchService.clientDetails(request);
					model.addAttribute("leftCustomerMenuID", TranecoStatusCode.CustomerService.CUSTOMER_DETAILS);
					model.addAttribute("customerDetails", detailedClientCardResponse.getCustomerDetails());
					action = "clientDetailsForm";
					if (clientService.getEndpointURL(accountBean.getStrCardNumber()) != null) 
					{
						clientService.addAccountToFundomo(detailedClientCardResponse, accountBean.getStrAccountNumber(),
								accountBean.getStrCardNumber());
					}
					break;
				default:
					model.addAttribute("error", response.getMessage());
					break;
				}
			} else {
				model.addAttribute("error", "Error occurs While adding Account !");
			}
			log.doLog(4, className, methodName, "End add Service Method");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Account !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return action;
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public String deleteAccount() {
		String methodName = "deleteAccount";
		String responseMsg = null;
		try {
			log.doLog(4, className, methodName, "Start add Service Method");

			// ResponseBean response = clientService.addAccount(accountBean);
			/*
			 * if (response != null) { responseMsg = response.getMessage(); } else {
			 * responseMsg = "Error occurs While deleting Account !"; }
			 */
			log.doLog(4, className, methodName, "End add Service Method");
		} catch (Exception e) {
			responseMsg = "Error occurs While deleting Account !";
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return responseMsg;
	}

	@RequestMapping(value = "/addEmbossingForm", method = RequestMethod.GET)
	public String addEmbossingForm(Model model, EmbossRequest embossRequest) {
		model.addAttribute("embossRequest", embossRequest);
		model.addAttribute("embossList", clientService.getEmbossFileList());
		model.addAttribute("embossCardList", clientService.getEmbossCardList());
		return "addEmbossingForm";
	}

	@RequestMapping(value = "/addEmbossing", method = RequestMethod.POST)
	public String addEmbossing(Model model, EmbossRequest embossRequest, HttpServletRequest req,
			HttpServletResponse resp) 
		{
		String methodName = "addEmbossing";
		try 
		{
			if (embossRequest.getStrSelectID() == null) 
			{
				String[] data = embossRequest.getCardType().split("\\|");
				clientService.requestEmboss(embossRequest);
				model.addAttribute("success",
						"Cards For Selected Card Type - " + data[2] + " Submitted For Embossing.");
			} 
			else 
			{
				String name[] = embossRequest.getStrSelectID().replace("/", "$").split("\\$");
				// Path file =
				// Paths.get(env.getProperty("emboss.file.drive")+File.separator+env.getProperty("emboss.file.path")+File.separator,
				// name[2]);
				resp.setContentType("text/plain");
				resp.setHeader("Content-disposition", "attachment; filename=" + name[2] + ".txt");

				try (InputStream in = new FileInputStream(new File(embossRequest.getStrSelectID()));
						OutputStream out = resp.getOutputStream()) 
				{
					byte[] buffer = new byte[1048];

					int numBytesRead;
					while ((numBytesRead = in.read(buffer)) > 0) 
					{
						out.write(buffer, 0, numBytesRead);
					}
				}
			}
			model.addAttribute("embossRequest", embossRequest);
			model.addAttribute("embossList", clientService.getEmbossFileList());
			model.addAttribute("embossCardList", clientService.getEmbossCardList());
			model.addAttribute("display", "block");
		}
		catch (Exception e) 
		{
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addEmbossingForm";
	}

	@RequestMapping(value = "/addPinPrintingForm", method = RequestMethod.GET)
	public String addPinPrintingForm(Model model, PinPrintingModel pinPrintingModel) 
	{
		String methodName = "addPinPrintingForm";
		try 
		{
			model.addAttribute("pinList", clientService.getPendingPinPrinting());
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Pin Printing !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addPinPrintingForm";
	}

	@RequestMapping(value = "/addPinPrinting", method = { RequestMethod.POST, RequestMethod.GET })
	public String addPinPrinting(Model model, PinPrintingModel pinPrintingModel) 
	{
		String methodName = "addPinPrinting";
		try 
		{
			String[] data = pinPrintingModel.getCardType().split("\\|");
			int count = clientService.updatePinMailer(data[0]);
			if (count > 0)
			{
				model.addAttribute("success", "Cards For Selected Card Type - " + data[1] + " Submitted For Pin Mailer Printing.");
			} 
			else 
			{
				model.addAttribute("error", "Error Occurs While Genrate Pin Mailer Printing !");
			}
			model.addAttribute("pinList", clientService.getPendingPinPrinting());
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Pin Printing !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addPinPrintingForm";
	}

	@RequestMapping(value = "/addBulkUploadForm", method = RequestMethod.GET)
	public String addBulkUploadForm(Model model, BulkUpload bulkUpload) {
		String methodName = "addBulkUploadForm";
		try {
			model.addAttribute("bulkUpload", bulkUpload);
			model.addAttribute("bulkList", clientService.getUploadDataList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Bulk Upload !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addBulkUploadForm";
	}

	@RequestMapping(value = "/addBulkUpload", method = RequestMethod.POST)
	public String addBulkUpload(Model model, BulkUpload bulkUpload, @RequestParam("file") MultipartFile file) {
		String methodName = "addBulkUploadForm";
		try {
			String path = env.getProperty("temp_file_path") + file.getOriginalFilename();

			String fileName = file.getOriginalFilename();
			if (!"".equalsIgnoreCase(fileName)) 
			{
				file.transferTo(new File(path));
			}

			// file.transferTo(new File(path));
			int count = clientService.readTextFile(path);
			model.addAttribute("display", "block");
			model.addAttribute("success", "Bulk Uploaded Successfully !");
			model.addAttribute("bulkUpload", bulkUpload);
			model.addAttribute("bulkList", clientService.getUploadDataList());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Bulk Upload !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addBulkUploadForm";
	}

	@RequestMapping(value = "/addTxnViewForm", method = RequestMethod.GET)
	public String addTxnViewForm(Model model, BulkUpload bulkUpload) 
	{
		String methodName = "addTxnViewForm";
		try 
		{
			model.addAttribute("txnList", clientService.getTxnDetails());
		} catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While view transaction !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addTxnViewForm";
	}

	@RequestMapping(value = "/inventoryForm", method = RequestMethod.GET)
	public String inventoryForm(Model model) 
	{
		String methodName = "inventoryForm";
		try 
		{
			model.addAttribute("inventroy", clientService.getInventroy());
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Inventory !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "inventoryForm";
	}

	@RequestMapping(value = "/mobileTokenViewForm", method = RequestMethod.GET)
	public String mobileTokenViewForm(Model model, MobileTokenModel mobileTokenModel) 
	{
		String methodName = "mobileTokenViewForm";
		try 
		{
			model.addAttribute("mobileTokenModel", mobileTokenModel);
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Inventory !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "mobileTokenViewForm";
	}

	@RequestMapping(value = "/mobileTokenView", method = RequestMethod.POST)
	public String mobileTokenView(Model model, MobileTokenModel mobileTokenModel) 
	{
		String methodName = "mobileTokenViewForm";
		try 
		{
			model.addAttribute("mobileTokenModel", mobileTokenModel);
			model.addAttribute("mobileTokenList", clientService.getMobileToken("" + mobileTokenModel.getMobile()));
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While Inventory !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "mobileTokenViewForm";
	}

	
	@RequestMapping(value = "/updateInstantAccountForm", method = RequestMethod.GET)
	public String updateInstantAccountForm(Model model, ServiceBean serviceBean) {
		String methodName = "updateInstantAccountForm";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.VIEW_AVAILABLE_INSTANT_ACCOUNT);
			model.addAttribute("InstantAccountCreateList", configurationService.getInstantAccount());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "updateInstantAccountForm";
	}

	@RequestMapping(value = "/updateInstantAccounts", method = RequestMethod.POST)
	public String updateInstantAccounts(Model model, ServiceBean serviceBean) {
		String methodName = "updateInstantAccounts";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.VIEW_AVAILABLE_INSTANT_ACCOUNT);
			model.addAttribute("display", "block");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "updateInstantAccountForm";
	}

	/*
	 * @RequestMapping(value = { "/accountCreation" }, method = { RequestMethod.GET
	 * }) public String accountCreation(Model model, AccountCreation
	 * accountCreation) { String methodName = "accountCreation"; try {
	 * model.addAttribute("accountCreation", accountCreation);
	 * model.addAttribute("leftAccountMenuID", TranecoStatusCode.CREATE_ACCOUNT);
	 * 
	 * String participantId = sessionDTO.getParticipantid();
	 * 
	 * accountCreation.setStrParticipantID(participantId);
	 * accountCreation.setStrCreatedBy(sessionDTO.getLoginID());
	 * 
	 * List<AccountTypeMaster> accountTypeMasters =
	 * configurationService.getAccountTypeMasterDataListByParticipantWise(
	 * participantId); model.addAttribute("accountType", accountTypeMasters);
	 * model.addAttribute("accountTypelist",
	 * this.mapper.writeValueAsString(accountTypeMasters));
	 * 
	 * List<AccountCreditLimitCategory> limitCategories =
	 * configurationService.getAccountCreditLimitCategoryListByParticipantWise(
	 * participantId);
	 * model.addAttribute("creditLimitCategorylist",limitCategories);
	 * model.addAttribute("limitCategoryStr",this.mapper.writeValueAsString(
	 * limitCategories));
	 * 
	 * model.addAttribute("POAList",configurationService.
	 * getAddressProofDocumentTypeMasters(participantId));
	 * model.addAttribute("POIList",configurationService.
	 * getIdentityProofDocumentTypeMasters(participantId));
	 * 
	 * model.addAttribute("taxType", configurationService.getTaxType());
	 * model.addAttribute("phoneCodeList", configurationService.getPhoneCode());
	 * model.addAttribute("countryList", configurationService.getCountry());
	 * //model.addAttribute("stateList", configurationService.getState());
	 * 
	 * //model.addAttribute("success","NA"); } catch (Exception e) {
	 * this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
	 * } return "accountCreation"; }
	 * 
	 * @RequestMapping(value = { "/accountCreationForm" }, method = {
	 * RequestMethod.POST }) public String addAccountCreation(Model model,
	 * AccountCreation accountCreation) { String methodName = "accountCreationForm";
	 * try { model.addAttribute("leftAccountMenuID",
	 * TranecoStatusCode.CREATE_ACCOUNT);
	 * 
	 * String strAccountWiseTxnLimit = accountCreation.getStrAccountWiseTxnLimit();
	 * if (strAccountWiseTxnLimit!=null) { AccountTransactionLimitation
	 * accountTransactionLimitation = this.mapper.readValue(strAccountWiseTxnLimit,
	 * AccountTransactionLimitation.class);
	 * 
	 * if (accountTransactionLimitation!=null) {
	 * accountCreation.setStrAvailableDailyLimit(accountTransactionLimitation.
	 * getStrSingleTxnLimit());
	 * accountCreation.setStrAvailableMonthlyLimit(accountTransactionLimitation.
	 * getStrDailyTxnLimit());
	 * accountCreation.setStrAvailableYearlyLimit(accountTransactionLimitation.
	 * getStrMonthlyTxnLimit()); } }
	 * 
	 * accountCreation.setStrParticipantID(sessionDTO.getParticipantid());
	 * accountCreation.setStrCreatedBy(sessionDTO.getLoginID());
	 * 
	 * 
	 * int count = this.configurationService.addAccountCreation(accountCreation); if
	 * (count > 0) { model.addAttribute("success",
	 * "Account Creation added Successfully"); } else { model.addAttribute("error",
	 * "Account Creation Failed"); }
	 * 
	 * 
	 * model.addAttribute("display", "block"); } catch (Exception e) {
	 * this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
	 * } return "accountCreation"; }
	 */

	@RequestMapping(value = "/editAccountCreation", method = RequestMethod.GET)
	public String editAccountCreation(Model model, AccountCreation accountCreation) {
		String methodName = "editAccountCreation";
		try {
			log.doLog(4, className, methodName, "Inside Method :");
			model.addAttribute("accountCreation", accountCreation);
			model.addAttribute("accountCreation", this.configurationService.getAccountCreation());
			model.addAttribute("editAccountCreation", this.mapper.writeValueAsString(
					this.configurationService.getAccountCreationData(accountCreation.getStrID()).get(0)));
			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("stateList", configurationService.getState());
			model.addAttribute("display", "block");
		} catch (Exception e) {
			model.addAttribute("Failed", "Error While Loading the page");
			e.printStackTrace();
		}
		return "editAccountCreation";
	}

	/*
	 * @RequestMapping(value = "/editAccountCreationForm", method =
	 * RequestMethod.POST) public ModelAndView editAccountCreationForm(Model model,
	 * AccountCreation accountCreation) { String methodName =
	 * "editAccountCreationForm"; try { log.doLog(4, className, methodName,
	 * sessionDTO.getParticipantid());
	 * 
	 * String participantId = sessionDTO.getParticipantid();
	 * System.out.println("participantId::"+participantId);
	 * 
	 * accountCreation =
	 * this.configurationService.getAccountInfoListByAccountNo(accountCreation).get(
	 * 0);
	 * 
	 * model.addAttribute("countryList", configurationService.getCountry());
	 * model.addAttribute("stateList",
	 * configurationService.getStateData(accountCreation.getStrCountry()));
	 * 
	 * model.addAttribute("cityList",
	 * configurationService.getCityData(accountCreation.getStrState()));
	 * 
	 * String strCountryCode = (accountCreation.getStrCountry()!=null) ?
	 * accountCreation.getStrCountry() : "NA"; String strState =
	 * (accountCreation.getStrState()!=null) ? accountCreation.getStrState() : "NA";
	 * String strCity = (accountCreation.getStrCity()!=null) ?
	 * accountCreation.getStrCity() : "NA";
	 * 
	 * model.addAttribute("display", "block"); model.addAttribute("is_issued_acc",
	 * false); model.addAttribute("strCountryCode", strCountryCode);
	 * model.addAttribute("strState", strState); model.addAttribute("strCity",
	 * strCity);
	 * 
	 * model.addAttribute("POAList",configurationService.
	 * getAddressProofDocumentTypeMasters(sessionDTO.getParticipantid()));
	 * model.addAttribute("POIList",configurationService.
	 * getIdentityProofDocumentTypeMasters(sessionDTO.getParticipantid()));
	 * 
	 * List<AccountCreditLimitCategory> limitCategories =
	 * configurationService.getAccountCreditLimitCategoryListByParticipantWise(
	 * participantId);
	 * model.addAttribute("creditLimitCategorylist",limitCategories);
	 * model.addAttribute("limitCategoryStr",this.mapper.writeValueAsString(
	 * limitCategories));
	 * 
	 * boolean isCreditTypeAccount =
	 * Boolean.parseBoolean(accountCreation.getStrIsCreditType());
	 * model.addAttribute("is_credit_acc", isCreditTypeAccount);
	 * 
	 * if (accountCreation.getStrIsInstantAccount().equalsIgnoreCase("Y")) {
	 * model.addAttribute("aestrik_display", "\"none\"");
	 * model.addAttribute("is_instance_acc", true); } else {
	 * model.addAttribute("aestrik_display", "\"contents\"");
	 * model.addAttribute("is_instance_acc", false); } } catch (Exception e) {
	 * System.out.println(e.getMessage()); e.printStackTrace(); log.doLog(3,
	 * className, methodName, LoggerUtil.getExStackTrace(e)); } return new
	 * ModelAndView("editAccountCreationForm", "accountCreation", accountCreation);
	 * }
	 */
	// @RequestMapping(value = "/updateEditedAccountForm", method =
	// RequestMethod.POST)
	/*
	 * @RequestMapping(value = "/updateEditedAccountForm", method =
	 * RequestMethod.GET) public String updateAccountMaster(Model model,
	 * AccountCreation accountCreation) { String methodName = "updateAccountMaster";
	 * String redirectionPageName = "viewAccountCreationForm"; int count = 0; try {
	 * log.doLog(4, className, methodName, sessionDTO.getParticipantid()); String
	 * participantId = sessionDTO.getParticipantid();
	 * accountCreation.setStrParticipantID(participantId);
	 * 
	 * if (accountCreation.getStrIsInstantAccount() !=null &&
	 * accountCreation.getStrIsInstantAccount().trim().equalsIgnoreCase("Y")) {
	 * count =
	 * configurationService.updateAccountCreationFromInstanceAccount(accountCreation
	 * ); redirectionPageName = "viewInstantAccountForm"; } else { count =
	 * configurationService.updateAccountCreation(accountCreation); }
	 * System.out.println("count::"+count); //model.addAttribute("display",
	 * "block"); } catch (Exception e) { System.out.println(e.getMessage());
	 * e.printStackTrace(); log.doLog(3, className, methodName,
	 * LoggerUtil.getExStackTrace(e)); } return "redirect:"+redirectionPageName; }
	 */
	/*
	 * @RequestMapping(value = "/viewAccountCreationForm", method =
	 * RequestMethod.GET) public String viewAccountCreationForm(Model model,
	 * AccountCreation accountCreation) { String methodName =
	 * "viewAccountCreationForm"; try {
	 * accountCreation.setStrParticipantID(this.sessionDTO.getParticipantid());
	 * accountCreation.setStrIsInstantAccount("N");
	 * 
	 * model.addAttribute("leftAccountMenuID",
	 * TranecoStatusCode.VIEW_ISSUED_ACCOUNT);
	 * //model.addAttribute("accountCreationList",
	 * configurationService.getAccountCreationListDataBasedOnTypes("N"));
	 * model.addAttribute("accountCreationList",
	 * configurationService.getAccountInfoListBasedOnTypes(accountCreation));
	 * model.addAttribute("accountTypeList",this.mapper.writeValueAsString(this.
	 * configurationService.getAccountCreation()));
	 * 
	 * //List<AccountCreation> list =
	 * configurationService.getAccountInfoListBasedOnTypes(accountCreation);
	 * //System.out.println("list::"+list); } catch (Exception e) {
	 * model.addAttribute("display", "block"); model.addAttribute("exception",
	 * "Error occurs While add Service !"); log.doLog(3, className, methodName,
	 * LoggerUtil.getExStackTrace(e)); } return "viewAccountCreationForm"; }
	 */

	/*
	 * @RequestMapping(value = "/viewInstantAccountForm", method =
	 * RequestMethod.GET) public String viewInstantAccountForm(Model model,
	 * AccountCreation accountCreation) { String methodName =
	 * "viewInstantAccountForm"; try { model.addAttribute("leftAccountMenuID",
	 * TranecoStatusCode.VIEW_AVAILABLE_INSTANT_ACCOUNT);
	 * 
	 * accountCreation.setStrParticipantID(this.sessionDTO.getParticipantid());
	 * accountCreation.setStrIsInstantAccount("Y");
	 * 
	 * model.addAttribute("accountCreationList",
	 * configurationService.getAccountInfoListBasedOnTypes(accountCreation));
	 * //model.addAttribute("accountCreationList",
	 * configurationService.getAccountCreationListDataBasedOnTypes("Y"));
	 * //model.addAttribute("accountCreationList",
	 * configurationService.getAccountCreationListDataBasedOnTypes(accountCreation.
	 * getStrIsInstantAccount().toUpperCase()));
	 * model.addAttribute("accountTypeList",this.mapper.writeValueAsString(this.
	 * configurationService.getAccountCreation())); } catch (Exception e) {
	 * model.addAttribute("display", "block"); model.addAttribute("exception",
	 * "Error occurs While add Service !"); log.doLog(3, className, methodName,
	 * LoggerUtil.getExStackTrace(e)); } return "viewInstantAccountForm"; }
	 */

	@RequestMapping(value = "/updateAccountCreation", method = RequestMethod.POST)
	public String updateAccountCreation(Model model, ServiceBean serviceBean) {
		String methodName = "updateAccountCreation";
		try {
			model.addAttribute("leftAccountMenuID", TranecoStatusCode.CREATE_ACCOUNT);
			model.addAttribute("display", "block");
			List<ServiceBean> cardList = clientService.getCard(serviceBean.getInCard());
			if (cardList.isEmpty()) {
				model.addAttribute("error", "Instant Card Not Present !");
			} else {
				if (cardList.get(0).getInCustomerID() != null && cardList.get(0).getInCustomerID().length() > 0) {
					model.addAttribute("error",
							"Instant Account Already Personalized Or Updated ! Please Go To Search Menu And Search Details !");
					cardList = null;
				}
			}
			model.addAttribute("cardNo", serviceBean.getInCard());
			model.addAttribute("cardList", cardList);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "updateAccountCreationForm";
	}

	@RequestMapping(value = { "/cardAccountLinkageViewForm" }, method = { RequestMethod.GET })
	public String cardAccountLinkageViewForm(Model model, SearchClientCardRequest clientBean, BindingResult result) {
		String methodName = "cardAccountLinkageViewForm";
		try {
			model.addAttribute("cardAccountLinkageView", clientBean);
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardAccountLinkageViewForm";
	}

	/*
	 * @RequestMapping(value = { "/cardAccountLinkageView" }, method = {
	 * RequestMethod.GET, RequestMethod.POST }) public String
	 * cardAccountLinkageView(Model model, SearchClientCardRequest clientBean,
	 * BindingResult result) { String methodName = "cardAccountLinkageView";
	 * SearchClientCardResponse response = null; try { log.doLog(4, className,
	 * methodName, "Inside Method :"); List<AccountTypeMaster> accountTypeMasters =
	 * configurationService.getAccountTypeMasterDataListByParticipantWise(sessionDTO
	 * .getParticipantid()); model.addAttribute("accountType", accountTypeMasters);
	 * model.addAttribute("cardTypeList", configurationService.getCardType());
	 * clientBean.setStrParticipantID(sessionDTO.getParticipantid()); response =
	 * clientSearchService.searchClient(clientBean); model.addAttribute("display",
	 * "block"); log.doLog(4, className, methodName, "End Inside Method :"); } catch
	 * (Exception e) { model.addAttribute("display", "block");
	 * model.addAttribute("exception",
	 * env.getProperty("imps.instAddForm.exception")); log.doLog(3, className,
	 * methodName, LoggerUtil.getExStackTrace(e)); }
	 * model.addAttribute("clientBean", clientBean); return
	 * "cardAccountLinkageView"; }
	 */
	// Added by Sunny Soni for setting Account Number Start
	@GetMapping(path = "/getUpdatedAccNo/{accountType}")
	private ResponseEntity<?> getUpdatedAccountNumber(@PathVariable String accountType) {
		String result = "";
		try {
			// String lastAccountNumberStr =
			// configurationService.getAccounNumberBasedOnAccountType(accountType);
			String lastAccountNumberStr = configurationService.getLastAccounNumberBasedOnAccountType(accountType);
			result = Utils.getUpdatedAccNumber(lastAccountNumberStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/getAccountTypeInfoBasedOnAccountType/{accountType}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getAccountTypeInfoBasedOnAccountType(@PathVariable String accountType) {
		try {
			AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
			accountTypeMaster.setStrParticipantId(sessionDTO.getParticipantid());
			accountTypeMaster.setStrAccountType(accountType);

			accountTypeMaster = configurationService.getSingleAccountTypeObject(accountTypeMaster);

			String updatedlastAccountNoStr = Utils.getUpdatedAccNumber(accountTypeMaster.getStrLastAccNumber());
			accountTypeMaster.setStrLastAccNumber(updatedlastAccountNoStr);

			return ResponseEntity.ok(accountTypeMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/getAccountTypeBasedMccWallet/{accountType}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getAccountTypeBasedUnblockMcc(@PathVariable String accountType) {
		Map<String, Object> responseData = new HashMap<String, Object>();
		try {
			AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
			accountTypeMaster.setStrParticipantId(sessionDTO.getParticipantid());
			accountTypeMaster.setStrAccountType(accountType);

			accountTypeMaster = configurationService.getSingleAccountTypeObject(accountTypeMaster);

			AccountTypeWiseWalletMaster accountTypeWiseWalletMaster = new AccountTypeWiseWalletMaster();
			accountTypeWiseWalletMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseWalletMaster.setStrAccounType(accountType);

			List<AccountTypeWiseWalletMaster> listData = configurationService
					.getAccountTypeWiseWalletMaster(accountTypeWiseWalletMaster);
			System.out.println("listData::" + listData);

			/*
			 * if (listData!=null && listData.size() > 0) { for (AccountTypeWiseWalletMaster
			 * accountTypeWiseWalletMst : listData) {
			 * accountTypeWiseWalletMst.setStrIsCreditType(accountTypeMaster.
			 * getStrIsCreditType()); } //return ResponseEntity.ok(listData); } else {
			 * listData = new ArrayList<AccountTypeWiseWalletMaster>(); if
			 * (accountTypeMaster != null && accountTypeMaster.getStrIsCreditType() != null)
			 * { accountTypeWiseWalletMaster.setStrIsCreditType(accountTypeMaster.
			 * getStrIsCreditType()); listData.add(accountTypeWiseWalletMaster); //return
			 * ResponseEntity.ok(listData); } }
			 */

			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			accountTypeWiseBlockedMccMaster.setStrAccounType(accountType);
			List<AccountTypeWiseBlockedMccMaster> accountTypeWiseBlockedMccMasters = configurationService
					.getBlockMccListAccountTypeWise(accountTypeWiseBlockedMccMaster);

			responseData.put("strIsCreditType", accountTypeMaster.getStrIsCreditType());
			responseData.put("MCC_CODES", listData);
			responseData.put("BLOCKED_MCC", accountTypeWiseBlockedMccMasters);

			return ResponseEntity.ok(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	// Added by Prashant T for account credit limit
	/*
	 * @RequestMapping(value = "/viewAccountCreditLimitForm", method =
	 * RequestMethod.GET) public String viewAccountCreditLimit(Model model,
	 * AccountCreditLimitCategory accountCreditLimitCategory) { String methodName =
	 * "viewAccountCreditLimit"; try { model.addAttribute("leftAccountMenuID",
	 * TranecoStatusCode.ACCOUNT_CREDIT_LIMIT);
	 * model.addAttribute("AccountCreditLimit", accountCreditLimitCategory);
	 * model.addAttribute("accountLimitCategoryList",
	 * configurationService.getAccountCreditLimiCategory());
	 * model.addAttribute("accountCategory",
	 * this.mapper.writeValueAsString(this.configurationService.
	 * getAccountCreditLimiCategory())); } catch (Exception e) {
	 * model.addAttribute("display", "block"); model.addAttribute("exception",
	 * "Error occurs While add Service !"); log.doLog(3, className, methodName,
	 * LoggerUtil.getExStackTrace(e)); } return "viewAccountCreditLimitForm"; }
	 * 
	 * // Added by Prashant T for account credit limit
	 * 
	 * @RequestMapping(value = "/viewAccountCreditLimit", method =
	 * RequestMethod.POST) public String viewAccountCreditLimitForm(Model model,
	 * AccountCreditLimitCategory accountCreditLimitCategory) { String methodName =
	 * "viewAccountCreditLimit"; try { model.addAttribute("leftAccountMenuID",
	 * TranecoStatusCode.ACCOUNT_CREDIT_LIMIT);
	 * model.addAttribute("accountLimitCategory",
	 * configurationService.getAccountCreditLimiCategory()); int count =
	 * this.configurationService.addCreditLimitCategory(accountCreditLimitCategory);
	 * if (count > 0) { model.addAttribute("Success",
	 * "Account Credit Limit added Successfully"); } else {
	 * model.addAttribute("Error", "Account Credit Limit Failed"); }
	 * model.addAttribute("accountCreditLimitCategory", accountCreditLimitCategory);
	 * model.addAttribute("accountLimitCategoryList",
	 * this.configurationService.getAccountCreditLimiCategory());
	 * model.addAttribute("accountCategory",
	 * this.mapper.writeValueAsString(this.configurationService.
	 * getAccountCreditLimiCategory())); } catch (Exception e) {
	 * model.addAttribute("exception", "Error occurs While add Service !");
	 * log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e)); } return
	 * "viewAccountCreditLimitForm"; }
	 */
	// Added by Prashant T for MCC wise interest form
	/*
	 * @RequestMapping(value = "/viewMccWiseInterestForm", method =
	 * RequestMethod.GET) public String viewMccWiseInterestForm(Model model,
	 * MccWiseInterestModel mccWiseInterestModel) { String methodName =
	 * "viewMccWiseInterestForm"; try { model.addAttribute("leftAccountMenuID",
	 * TranecoStatusCode.MCC_WISE_INTEREST); model.addAttribute("MccWiseInterest",
	 * mccWiseInterestModel); model.addAttribute("accountType",
	 * configurationService.getAccountType()); model.addAttribute("MccCodeList",
	 * configurationService.getMccCodeList());
	 * model.addAttribute("MccWiseInterestList",
	 * configurationService.getMccWiseInterest());
	 * model.addAttribute("mccWiseInterestData",
	 * this.mapper.writeValueAsString(this.configurationService.getMccWiseInterest()
	 * )); } catch (Exception e) { model.addAttribute("display", "block");
	 * model.addAttribute("exception", "Error occurs While add Service !");
	 * log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e)); } return
	 * "viewMccWiseInterestForm"; }
	 * 
	 * @RequestMapping(value = "/viewMccWiseInterest", method = RequestMethod.POST)
	 * public String viewMccWiseInterest(Model model, MccWiseInterestModel
	 * mccWiseInterestModel) { String methodName = "viewMccWiseInterestForm"; try {
	 * model.addAttribute("leftAccountMenuID", TranecoStatusCode.MCC_WISE_INTEREST);
	 * model.addAttribute("MccWiseInterestList",
	 * this.configurationService.getMccWiseInterest()); int count =
	 * this.configurationService.saveMccInterest(mccWiseInterestModel); if (count >
	 * 0) { model.addAttribute("Success", "MCC Interest Data Saved Successfully"); }
	 * else { model.addAttribute("Failed", "MCC Interest Data Failed"); }
	 * model.addAttribute("MccWiseInterest", mccWiseInterestModel);
	 * model.addAttribute("MccWiseInterestList",
	 * this.configurationService.getMccWiseInterest());
	 * model.addAttribute("mccWiseInterestData",
	 * this.mapper.writeValueAsString(this.configurationService.getMccWiseInterest()
	 * )); } catch (Exception e) { model.addAttribute("display", "block");
	 * model.addAttribute("exception", "Error occurs While add Service !");
	 * log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e)); } return
	 * "viewMccWiseInterestForm"; }
	 */
	// Added by Prashant Tayde for account details view form
	// @RequestMapping(value = "/viewAccountDetailsForm", method =
	// RequestMethod.GET)
	// public String accountDetailsForm(Model model, AccountDetails accountDetails,
	// AccountTransactionLimitModel accountTransactionLimitModel)
	/*
	 * @RequestMapping(value = "/viewAccountDetailsForm", method =
	 * RequestMethod.GET) public String accountDetailsForm(Model model,
	 * AccountDetails accountDetails, HttpSession session) { String methodName =
	 * "accountDetailsForm"; try { model.addAttribute("leftCustomerMenuID",
	 * TranecoStatusCode.ACCOUNT_DETAILS); System.out.println(session); if
	 * (session.getAttribute("accountDetails") == null) {
	 * session.setAttribute("accountDetails", accountDetails); } else {
	 * accountDetails = (AccountDetails) session.getAttribute("accountDetails");
	 * System.out.println(accountDetails);
	 * accountDetails.setStrAccountType(accountDetails.getStrAccountType());
	 * model.addAttribute("accountDetails", accountDetails); return
	 * "viewAccountDetailsForm"; }
	 * 
	 * String participantId = sessionDTO.getParticipantid();
	 * 
	 * CardAccountLinkage cardAccountLinkage = new CardAccountLinkage();
	 * cardAccountLinkage.setStrParticipantID(participantId);
	 * cardAccountLinkage.setStrAccountNumber(accountDetails.getStrAccountNumber());
	 * cardAccountLinkage.setStrCardNumber(accountDetails.getStrCardNumber());
	 * 
	 * cardAccountLinkage =
	 * this.configurationService.getCardAccountLinkages(cardAccountLinkage);
	 * 
	 * AccountTransactionLimitModel accountTransactionLimitModel = new
	 * AccountTransactionLimitModel();
	 * accountTransactionLimitModel.setStrParticipantId(participantId);
	 * accountTransactionLimitModel.setStrAccountType(cardAccountLinkage.
	 * getStrAccountType());
	 * 
	 * accountTransactionLimitModel =
	 * this.configurationService.getAccountTransactionLimitModel(
	 * accountTransactionLimitModel);
	 * 
	 * AccountCreation accountCreation = new AccountCreation();
	 * accountCreation.setStrParticipantID(participantId);
	 * accountCreation.setStrAccountType(cardAccountLinkage.getStrAccountType());
	 * accountCreation.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber())
	 * ;
	 * 
	 * accountCreation =
	 * this.configurationService.getAccountInformation(accountCreation);
	 * 
	 * accountDetails.setStrAccountType(accountCreation.getStrAccountType());
	 * accountDetails.setStrAccountCatogory(accountCreation.
	 * getStrCreditLimitCategory());
	 * accountDetails.setStrAccountNumber(accountCreation.getStrAccountNumber());
	 * accountDetails.setStrAvailableBalance(accountCreation.getStrClosingBalance())
	 * ;
	 * 
	 * accountDetails.setStrIsInstanceAccount(
	 * (accountCreation.getStrIsInstantAccount().equalsIgnoreCase("Y")) ? "YES" :
	 * "NO");
	 * 
	 * accountDetails.setStrFirstName(accountCreation.getStrFirstName());
	 * accountDetails.setStrMiddleName(accountCreation.getStrMiddleName());
	 * accountDetails.setStrLastName(accountCreation.getStrLastName());
	 * accountDetails.setStrGender(accountCreation.getStrGender());
	 * accountDetails.setStrDOB(accountCreation.getStrDOB());
	 * accountDetails.setStrEmailId(accountCreation.getStrEmailID());
	 * accountDetails.setStrAddress1(accountCreation.getStrAddress1());
	 * accountDetails.setStrAddress2(accountCreation.getStrAddress2());
	 * accountDetails.setStrAddress3(accountCreation.getStrAddress3());
	 * 
	 * accountDetails.setStrCountry(configurationService.getCountryName(
	 * accountCreation.getStrCountry()));
	 * accountDetails.setStrState(configurationService.getStateName(accountCreation.
	 * getStrState()));
	 * accountDetails.setStrCity(configurationService.getCityName(accountCreation.
	 * getStrCity()));
	 * 
	 * accountDetails.setStrPincode(accountCreation.getStrPinCode());
	 * accountDetails.setStrMobileNo(accountCreation.getStrMobileNo());
	 * accountDetails.setStrAccountCreationDate(accountCreation.getStrDateOfCreation
	 * ());
	 * 
	 * accountDetails.setStrAccountIssuedDate(cardAccountLinkage.getStrCreationDate(
	 * )); accountDetails.setStrAssignedSingleLimit(accountTransactionLimitModel.
	 * getStrSingleTxnLimit());
	 * accountDetails.setStrAssignedDailyLimit(accountTransactionLimitModel.
	 * getStrDailyTxnLimit());
	 * accountDetails.setStrAssignedMonthlyLimit(accountTransactionLimitModel.
	 * getStrMonthlyTxnLimit());
	 * accountDetails.setStrAssignedYearlyLimit(accountTransactionLimitModel.
	 * getStrYearlyTxnLimit());
	 * 
	 * accountDetails.setStrDailyAvailableLimit(accountCreation.
	 * getStrAvailableDailyLimit());
	 * accountDetails.setStrMonthlyAvailableLimit(accountCreation.
	 * getStrAvailableMonthlyLimit());
	 * accountDetails.setStrYearlyAvailableLimit(accountCreation.
	 * getStrAvailableYearlyLimit());
	 * 
	 * AccountCreditLimitCategory accountCreditLimitCategory = new
	 * AccountCreditLimitCategory();
	 * accountCreditLimitCategory.setStrParticipantId(participantId);
	 * accountCreditLimitCategory.setStrCreditType(accountCreation.
	 * getStrCreditLimitCategory());
	 * 
	 * accountCreditLimitCategory =
	 * this.configurationService.getAccountCreditLimitCategory(
	 * accountCreditLimitCategory);
	 * 
	 * accountDetails.setStrAssignedCreditLimit(accountCreditLimitCategory.
	 * getStrCreditLimit());
	 * 
	 * accountDetails.setStrAvailableCreditLimit(accountCreation.
	 * getStrAvailableCreditLimit());
	 * 
	 * accountDetails.setStrNoOfBalanceLoading(accountCreation.getStrLoadCount());//
	 * Need To CHECK
	 * accountDetails.setStrTotalBalanceLoaded(accountCreation.getStrClosingBalance(
	 * ));// Need To CHECK
	 * 
	 * accountDetails.setStrTotalPrincipalOutStanding("");// Need To CHECK
	 * accountDetails.setStrTotalInterestOuStanding("");// Need To CHECK
	 * 
	 * } catch (Exception e) { model.addAttribute("display", "block"); //
	 * model.addAttribute("exception", "Error occurs While add Service !");
	 * log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e)); } return
	 * "viewAccountDetailsForm"; }
	 */
	// Added by Prashant Tayde for account details view form
	@RequestMapping(value = "/viewAccountDetails", method = RequestMethod.POST)
	public String viewAccountDetailsForm(Model model, AccountDetails accountDetails) {
		String methodName = "accountDetailsForm";
		try {
			model.addAttribute("AccountDetails", accountDetails);
			model.addAttribute("leftCustomerMenuID", TranecoStatusCode.ACCOUNT_DETAILS);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountDetailsForm";
	}

	@RequestMapping(value = "/getAvailableBalance", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getAvailableBalance(@RequestBody AccountCreation accountCreation) {
		String result = "error";
		try {

			boolean isExistAccountTypeNumber = this.accountManagementService.isAccountAlreadyExist(accountCreation);
			if (isExistAccountTypeNumber) {
				String availableBalance = this.configurationService
						.getAvailableBalanceBasedOnAccountTypeAndNumber(accountCreation);
				System.out.println(availableBalance);
				if (availableBalance == null) {
					availableBalance = "";
				}
				return ResponseEntity.ok(availableBalance);
			} else {
				return ResponseEntity.ok(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// Added by JYOTI For showing account Statement Start
	@RequestMapping(value = { "/viewAccountStatementForm" }, method = { RequestMethod.GET })
	public String viewAccountStatementForm(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "viewAccountStatementForm";
		try {
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.VIEW_ACCOUNT_STATEMENT);
			// model.addAttribute("accountTypeMasterList",
			// this.configurationService.getAccounTypeMaster());
			// model.addAttribute("accountType", configurationService.getAccountType());

			List<AccountTypeMaster> accountTypeMasters = configurationService
					.getAccountTypeMasterDataListByParticipantWise(sessionDTO.getParticipantid());
			model.addAttribute("accountType", accountTypeMasters);
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewAccountStatementForm";
	}
	// Added by JYOTI For showing account Statement End

	// Added by Sagark EXRTA AJAX CALLING METHOD display view sub wallet account
	// linked list date:23-12-2022
	@RequestMapping(value = "/getlinkedAccountWallet/{walletAccountNumber}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getlinkedAccountWallet(@PathVariable String walletAccountNumber) {
		List<AccountLinkedWalletMaster> accountLinkedWalletMaster = null;
		String methodName = "getlinkedAccountWallet";
		try {
			accountLinkedWalletMaster = this.configurationService.getlinkedAccountWallet(walletAccountNumber);
			return ResponseEntity.ok(accountLinkedWalletMaster);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return ResponseEntity.ok(accountLinkedWalletMaster);
	}

	// prashant
	@RequestMapping(value = "/getBlockMccListAccountTypeWise/{accountType}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getAccountTypeWiseBlockList(@PathVariable String accountType) {
		try {
			AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
			accountTypeMaster.setStrParticipantId(sessionDTO.getParticipantid());
			accountTypeMaster.setStrAccountType(accountType);

			accountTypeMaster = configurationService.getSingleAccountTypeObject(accountTypeMaster);

			AccountTypeWiseBlockedMccMaster accountTypeWiseBlockedMccMaster = new AccountTypeWiseBlockedMccMaster();
			accountTypeWiseBlockedMccMaster.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeWiseBlockedMccMaster.setStrAccounType(accountType);

			List<AccountTypeWiseBlockedMccMaster> listData = configurationService
					.getBlockMccListAccountTypeWise(accountTypeWiseBlockedMccMaster);

			System.out.println("listData::" + listData);
			return ResponseEntity.ok(listData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/viewMccWiseInterestFormData", method = RequestMethod.GET)
	public String viewMccWiseInterestFormData(Model model, MccWiseInterestModel mccWiseInterestModel) {
		String methodName = "viewMccWiseInterestForm";
		try {
			// model.addAttribute("leftAccountMenuID", TranecoStatusCode.MCC_WISE_INTEREST);
			model.addAttribute("MccWiseInterest", mccWiseInterestModel);
			model.addAttribute("MccWiseInterestList", configurationService.getMccWiseInterest());
			model.addAttribute("mccWiseInterest",
					this.mapper.writeValueAsString(this.configurationService.getMccWiseInterest()));
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewMccWiseInterestFormData";
	}

	@RequestMapping(value = { "/accountStatementView" }, method = { RequestMethod.GET })
	public String accountStatementViewMasterConfig(Model model, AccountTypeMaster accountTypeMaster) {
		String methodName = "accountStatementView";
		try {
			// model.addAttribute("accountType", configurationService.getAccountType());
			model.addAttribute("leftAccountStatementMenuID", TranecoStatusCode.VIEW_ACCOUNT_STATEMENT);
			model.addAttribute("accountTypeMasterList", this.configurationService.getAccounTypeMaster());
			model.addAttribute("accountType", configurationService.getAccountType());
		} catch (Exception e) {
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "accountStatementView";
	}

	// code added by Prashant Tayde for getting account type for blockMccList
	@RequestMapping(value = "/getSelectedChargesAccountTypeWise/{accountType}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getSelectedChargesAccountTypeWise(@PathVariable String accountType) {
		try {
			AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
			accountTypeMaster.setStrParticipantId(sessionDTO.getParticipantid());
			accountTypeMaster.setStrAccountType(accountType);

			accountTypeMaster = configurationService.getSingleAccountTypeObject(accountTypeMaster);

			AccountTypeCharges accountTypeCharges = new AccountTypeCharges();
			accountTypeCharges.setStrParticipantID(sessionDTO.getParticipantid());
			accountTypeCharges.setStrAccountType(accountType);

			List<AccountTypeCharges> acListData = configurationService
					.getSelectedChargesAccountTypeWise(accountTypeCharges);

			System.out.println("acListData::" + acListData);
			return ResponseEntity.ok(acListData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = { "/generateKey" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String generateKey(Model model, EncryptKeyModel encryptKeyModel)
	{
		String methodName = "generateKey";
		try 
		{
			model.addAttribute("encryptKeyModel", encryptKeyModel);
		}
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
			e.printStackTrace();
		}
		return "generateKey";
	}
	

	
	@RequestMapping(value = { "/reEnterKeyForm" }, method = { RequestMethod.GET })
	public String reEnteredKey(Model model, EncryptKeyModel encryptKeyModel)
	{
		String methodName = "reEnterKey";
		try 
		{
			model.addAttribute("encryptKeyModel", encryptKeyModel);
			encryptKeyModel = encryptKeyDataService.getMaskedFileKey();
			encryptKeyModel.setKey(encryptKeyModel.getKey().trim());
			encryptKeyModel.setValue(encryptKeyModel.getValue().trim());
			model.addAttribute("encryptKeyModel", encryptKeyModel);
		}
		catch (Exception e) 
		{
			this.log.doLog(3, this.className, methodName, LoggerUtil.getExStackTrace(e));
			e.printStackTrace();
		}
		return "reEnterKeyForm";
	}
	
	
	@RequestMapping(value = "/startStopLogging", method = RequestMethod.GET)
	public String startStopLogging(Model model, UserLoggingModel userLoggingModel) 
	{
		try
		{
			model.addAttribute("userLoggingModel", userLoggingModel);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "startStopLogging";
	}
	
	
	
	@RequestMapping(value = "/createCreditCardCustomerAccount", method = RequestMethod.GET)
	public String createCreditCardCustomerAccount(Model model, CreditCardCustomerAccountCreationModel creditCardCustomerAccountCreation)
	{
		try 
		{
			model.addAttribute("creditCardCustomerAccountCreation", creditCardCustomerAccountCreation);
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("binList", this.configurationService.getBin());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "createCreditCardCustomerAccount";
		
	}
	
	
	@RequestMapping(value = "/generateCardAccount", method = RequestMethod.POST)
	public String generateCardAccount(Model model, CreditCardCustomerAccountCreationModel creditCardCustomerAccountCreation)
	{
		try 
		{
			model.addAttribute("creditCardCustomerAccountCreation", creditCardCustomerAccountCreation);
			CreditCardCustomerAccountCreationModel generateCardAccount = clientService.generateCardAccount(creditCardCustomerAccountCreation);
			if (generateCardAccount != null)
			{
				model.addAttribute("success", "Card Account Generated Successfully !");
			}
			else 
			{
				model.addAttribute("error", "Failed To Geneate Card Account !");
			}
			model.addAttribute("emailList", configurationService.getEmail());
			model.addAttribute("countryList", configurationService.getCountry());
			model.addAttribute("addressList", configurationService.getAddress());
			model.addAttribute("documentType", clientSearchService.getDocumentType());
			model.addAttribute("cardTypeList", this.configurationService.getCardType());
			model.addAttribute("binList", this.configurationService.getBin());
			model.addAttribute("branchCodeList", configurationService.getBranchCode());
			model.addAttribute("creditCardCustomerAccountCreation", creditCardCustomerAccountCreation);
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "createCreditCardCustomerAccount";
	}
	
	
}
