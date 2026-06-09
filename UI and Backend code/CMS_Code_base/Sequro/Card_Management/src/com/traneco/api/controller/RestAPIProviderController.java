package com.traneco.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.api.model.CardDetailsRequest;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardTypeList;
import com.traneco.clientSearch.services.ClientSearchService;
import com.traneco.common.SessionDTO;
import com.traneco.common.util.AESEncDec;
import com.traneco.common.util.Utils;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.CardAccountLinkage;
//created by ankit on 12-04-2023

@RestController
@RequestMapping("/api")
public class RestAPIProviderController 
{
	@Autowired
	private ClientSearchService clientSearchService;

	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	AESEncDec aesEncDec;
	
	@Autowired
	ConfigurationService configurationService;

	@Autowired
	AccountManagementService accountManagementService;
	

	SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// test method to check the Rest API Call -can be removed
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Rest Call Working...!";
	}

	@RequestMapping(value = "/getCardNo", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<?> getCardNo(@RequestBody CardDetailsRequest cardDetailsRequest) 
	{
		try 
		{
			String strTokenCard = cardDetailsRequest.getStrTokenCard();

			CardDetails cardDetails = new CardDetails();
			cardDetails.setStrTokenCard(strTokenCard);
			List<CardDetails> clearCardNo = clientSearchService.getClearCardNo(cardDetails);
			CardDetailsRequest cardDetailsResponse = new CardDetailsRequest();
			if (clearCardNo.size() > 0) 
			{
				String strClearCardNo = clearCardNo.get(0).getStrClearCardNo();
				cardDetailsResponse.setStrCardNo(strClearCardNo);
				return new ResponseEntity<>(cardDetailsResponse, HttpStatus.OK);
			} 
			else 
			{
				return new ResponseEntity<>(cardDetailsResponse, HttpStatus.OK);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCardDetails", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<?> getCardDetails(@RequestBody CardAccountLinkage cardAccountLinkage) {
		try {
			// String strTokenCard = cardAccountLinkage.getStrTokenCard();
			CardAccountLinkage cardAccountLinkageResp = clientSearchService.getCardDetails(cardAccountLinkage);
			if (cardAccountLinkageResp != null) {
				return new ResponseEntity<>(cardAccountLinkageResp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(cardAccountLinkageResp, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateCardStatusBlock", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<?> updateCardStatusBlock(@RequestBody CardAccountLinkage cardAccountLinkage) {
		try {
			// String strTokenCard = cardAccountLinkage.getStrTokenCard();
			int cardAccountLinkageResp = clientSearchService.updateCardStatusBlock(cardAccountLinkage);
			if (cardAccountLinkageResp > 0) {
				return new ResponseEntity<>(cardAccountLinkageResp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(cardAccountLinkageResp, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateCardStatusActive", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<?> updateCardStatusActive(@RequestBody CardAccountLinkage cardAccountLinkage) {
		try {
			// String strTokenCard = cardAccountLinkage.getStrTokenCard();
			int cardAccountLinkageResp = clientSearchService.updateCardStatusActive(cardAccountLinkage);
			if (cardAccountLinkageResp > 0) {
				return new ResponseEntity<>(cardAccountLinkageResp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(cardAccountLinkageResp, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCMSCardDetails", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<?> getCMSCardDetails(@RequestBody CardDetails cardDetails) {
		try {
			// String strTokenCard = cardAccountLinkage.getStrTokenCard();
			List<CardDetails> cardDetailsData = clientSearchService.getCardDetailsData(cardDetails);
			if (cardDetailsData != null && cardDetailsData.size() > 0) 
			{
				return new ResponseEntity<>(cardDetailsData, HttpStatus.OK);
			} 
			else 
			{
				return new ResponseEntity<>(cardDetailsData, HttpStatus.NOT_FOUND);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	/**
	 * Card Account Linkage Changes Date - 04-09-2023
	 * 
	 * @author Jyoti S
	 */
	@RequestMapping(value = "/getCardType", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getCardType() 
	{
		try 
		{
			List<CardTypeList> cardTypeList = clientSearchService.getCardTypeList();
			if (cardTypeList != null && cardTypeList.size() > 0) 
			{
				return new ResponseEntity<>(cardTypeList, HttpStatus.OK);
			} 
			else 
			{
				return new ResponseEntity<>(cardTypeList, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


	 // Card Account Linkage Changes Date - 04-09-2023
	@RequestMapping(value = "/assignUnsoldCard", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> assignUnsoldCard(@RequestBody CardAccountLinkage cardAccountLinkage) {
		try {
			CardAccountLinkage newCardLinkage = new CardAccountLinkage();
			String Response1 = null;

			ObjectMapper objectMapper = new ObjectMapper();

			newCardLinkage.setStrAccountType(cardAccountLinkage.getStrAccountType());
			newCardLinkage.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
			newCardLinkage.setStrCardType(cardAccountLinkage.getStrCardType());

			List<CardDetailsList> getUnsoldCardList = clientSearchService.getUnsoldCardList(cardAccountLinkage.getStrCardType());

			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAccountNumber(cardAccountLinkage.getStrAccountNumber());
			accountCreation.setStrAccountType(cardAccountLinkage.getStrAccountType());

			AccountCreation accCreationObj = accountManagementService.getAccountInformation(accountCreation);
			if (getUnsoldCardList != null && getUnsoldCardList.size() > 0) 
			{
				newCardLinkage.setStrCardNumber(getUnsoldCardList.get(0).getStrCardNumber());

				newCardLinkage.setStrTokenCard(getUnsoldCardList.get(0).getStrTokenCard());
				newCardLinkage.setStrCreatedBy(sessionDTO.getLoginID());
				newCardLinkage.setStrParticipantID(sessionDTO.getParticipantid());
				newCardLinkage.setStrCardStatus("Active");
				newCardLinkage.setStrAccountStatus("Active");

				CardTypeModel cardTypeModel = new CardTypeModel();
				cardTypeModel.setCardNumber(getUnsoldCardList.get(0).getStrCardNumber());
				cardTypeModel.setStrCardType(getUnsoldCardList.get(0).getStrCardType());

				List<CardTypeModel> cardTypeModels = configurationService.getCardTypeInfo(cardTypeModel);
				newCardLinkage.setStrCardId(cardTypeModels.get(0).getStrCardId());
				newCardLinkage.setStrCardType(cardTypeModels.get(0).getStrCardTypeData());
				newCardLinkage.setStrCardEncCard(cardTypeModels.get(0).getStrCardEncCard());
				newCardLinkage.setStrCardDescription(cardTypeModels.get(0).getStrCardDescr());

				newCardLinkage.setStrCardExpDate(cardTypeModels.get(0).getStrCardExpDate());
				newCardLinkage.setStrBin(cardTypeModels.get(0).getStrBin());

				String networkType = configurationService.getNetworkType(newCardLinkage);
				newCardLinkage.setStrNetworkType(networkType);

				String cardHolderName = configurationService.getCardHolderName(newCardLinkage);
				newCardLinkage.setStrCardHolderName(cardHolderName);

				String inputDateString = newCardLinkage.getStrCardExpDate();
				Date date = inputFormat.parse(inputDateString);

				String expriryDate = outputFormat.format(date);
				System.out.println(expriryDate);

				newCardLinkage.setStrCardExpDate(expriryDate);

				String maskCardNumber = newCardLinkage.getStrCardNumber();
				String s1 = maskCardNumber.substring(maskCardNumber.length() - 4);
				String s2 = maskCardNumber.substring(0, maskCardNumber.length() - 4);
				String replaceMaskNumber = s2.replace(s2, "************");
				String cardNumber = replaceMaskNumber.concat(s1).trim();

				newCardLinkage.setStrCardNumber(cardNumber);

				int count = this.configurationService.addCardAccountLinkageCMS(newCardLinkage);
				// Making Entry in CMS cardaccount linkage Table End
				if (count > 0) 
				{
					// Added by prashant Tayde for updated Issued date
					CardDetails cardDetails = new CardDetails();
					cardDetails.setStrTokenCard(newCardLinkage.getStrTokenCard());
					cardDetails.setStrIssuedToCustomer(new Date());
					int updateIssueCardToCustomer = this.configurationService.updateIssueCardToCustomer(cardDetails);
					System.out.println("updateIssueCardToCustomer::::"+updateIssueCardToCustomer);
					// ended by prashant Tayde 20 Aug 2023
					// Added by prashant Tayde 23 Aug - For entry in AMS for MaskCardNumbercard

					CardAccountLinkage cardAccountLinkageDta = this.accountManagementService.addCardAccountLinkageData(newCardLinkage);
					int result = Integer.parseInt(cardAccountLinkageDta.getStrID());
					if (result > 0) 
					{
						accCreationObj.setStrIsLinkedwithCard("Y");
						this.accountManagementService.updateAccountAfterLinkedCard(accCreationObj);

						accountManagementService.applyCardIssuanceCharges(newCardLinkage);

						/*
						 * String Response = "{ strCardNumber '" + newCardLinkage.getStrCardNumber() +
						 * "' Message : Card Linked Sucessfully }";
						 */

						Response1 = "{\"strCardNumber\" : \"" + newCardLinkage.getStrCardNumber() + "\",\n"
								+ "        \"Message\" : \"Card Linked Sucessfully\"\n" + "    }";

						JsonNode jsonNode = objectMapper.readTree(Response1);

						System.out.println("JSON DATA" + jsonNode);

						return new ResponseEntity<>(jsonNode, HttpStatus.OK);

					}
				}

			} 
			else 
			{
				return new ResponseEntity<>("Card Not Linked", HttpStatus.NOT_FOUND);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	
		//Addded  By Prashant T 15Dec2023
	/*	@RequestMapping(value = "/getCardTypeInfo", method = RequestMethod.POST, produces = "application/json")
		public List<CardTypeModel> getCardTypeInfo(@RequestBody CardTypeModel cardTypeModel)
		{
			List<CardTypeModel> cardTypeModelss = null;
			try 
			{
				String cardNumber = cardTypeModel.getStrCardNumber();
				String encryptedCardNubmer = aesEncDec.encrypt(cardNumber);
				System.out.println(encryptedCardNubmer);
				cardTypeModel.setCardNumber(encryptedCardNubmer);
				List<CardTypeModel> cardTypeModels = configurationService.getCardTypeInfo(cardTypeModel);
				if (cardTypeModels !=null && cardTypeModels.size() > 0) 
				{
					return cardTypeModels;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			cardTypeModelss = new ArrayList<>();
			return cardTypeModelss;
		} */
	
		
		//Added By Prashant Tayde 21Mar2024 For Credit Card - Card Account Linkage
		
	@RequestMapping(value = "/getCardTypeInfo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<CardTypeModel>> getCardTypeInfo(@RequestBody CardTypeModel cardTypeModel)
	{
		try 
		{
			String maskValue = Utils.maskValue(cardTypeModel.getStrCardNumber().trim());
			System.out.println(maskValue);
			cardTypeModel.setCardNumber(maskValue);
			List<CardTypeModel> cardTypeModels = configurationService.getCardTypeInfo(cardTypeModel);
			if (cardTypeModels != null && !cardTypeModels.isEmpty()) 
			{
				return new ResponseEntity<>(cardTypeModels, HttpStatus.OK);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		
	 }
		
		@RequestMapping(value = "/isCardAlreadyLinked", method = RequestMethod.POST, produces = "application/json")
		public boolean isCardAlreadyLinked(@RequestBody String tokenCard)
		{
			try 
			{
				boolean isCardAlreadyLinked = configurationService.isCardAlreadyLinked(tokenCard);
				System.out.println("AmsServiceRestAPI.isCardAlreadyLinked()"+isCardAlreadyLinked);
				return isCardAlreadyLinked;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
			
		}
		
		@RequestMapping(value = "/getNetworkType", method = RequestMethod.POST, produces = "application/json")
		public CardAccountLinkage isCardAlreadyLinked(@RequestBody CardAccountLinkage cardAccountLinkageObject)
		{
			try 
			{
				String networkType = configurationService.getNetworkType(cardAccountLinkageObject);
				cardAccountLinkageObject.setStrNetworkType(networkType);
				System.out.println("AmsServiceRestAPI.isCardAlreadyLinked()"+networkType);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return cardAccountLinkageObject;
		}
		
		@RequestMapping(value = "/getCardHolderName", method = RequestMethod.POST, produces = "application/json")
		public CardAccountLinkage getCardHolderName(@RequestBody CardAccountLinkage cardAccountLinkageObject)
		{
			try 
			{
				String cardHolderName = configurationService.getCardHolderName(cardAccountLinkageObject);
				cardAccountLinkageObject.setStrCardHolderName(cardHolderName);
				System.out.println("AmsServiceRestAPI.isCardAlreadyLinked()"+cardHolderName);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return cardAccountLinkageObject;
		}
		
		
		@RequestMapping(value = "/addCardAccountLinkageCMS", method = RequestMethod.POST, produces = "application/json")
		private ResponseEntity<?> addCardAccountLinkageCMS(@RequestBody CardAccountLinkage cardAccountLinkageObject) 
		{
			try 
			{
				int count = this.configurationService.addCardAccountLinkageCMS(cardAccountLinkageObject);
				if (count > 0) 
				{
					return ResponseEntity.ok(count);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return ResponseEntity.ok(null);
		}
		
		
		@RequestMapping(value = "/updateIssueCardToCustomer", method = RequestMethod.POST, produces = "application/json")
		private ResponseEntity<?> updateIssueCardToCustomer(@RequestBody CardDetails cardDetails) 
		{
			try 
			{
				int update = this.configurationService.updateIssueCardToCustomer(cardDetails);
				if (update > 0) 
				{
					return ResponseEntity.ok(update);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return ResponseEntity.ok(null);
		}
	
	
		@RequestMapping(value = "/getCardTypedataForLinkage", method = RequestMethod.POST)
		public ResponseEntity<?> getCardTypeBasedOnParticipntId(@RequestBody CardTypeModel cardTypeModel)
		{
			ProcessResponse processWebResponse = new ProcessResponse();
			try 
			{
				List<CardTypeModel> cardtypelistData = configurationService.getCardType(cardTypeModel);
				return ResponseEntity.ok(cardtypelistData);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return ResponseEntity.ok(processWebResponse);
		}
		
		
		
		@RequestMapping(value = "/cardReplacementWithCRCCharges", method = RequestMethod.POST)
		public ResponseEntity<?> cardReplacementWithCRCCharges(@RequestBody CardTypeModel cardTypeModel)
		{
			ProcessResponse processWebResponse = new ProcessResponse();
			try 
			{
				List<CardTypeModel> cardtypelistData = configurationService.getCardType(cardTypeModel);
				return ResponseEntity.ok(cardtypelistData);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return ResponseEntity.ok(processWebResponse);
		}
		
}
