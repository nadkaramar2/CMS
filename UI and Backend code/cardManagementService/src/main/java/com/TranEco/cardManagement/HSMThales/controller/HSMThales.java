package com.TranEco.cardManagement.HSMThales.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.HSMThales.services.HSMThalesService;
import com.TranEco.cardManagement.utils.StringUtil;

@RestController
public class HSMThales {

	@Autowired
	@Lazy
	HSMThalesService hSMThalesService;

	private static Logger logger = Logger.getLogger(HSMThales.class.getName());

	@RequestMapping(method = RequestMethod.POST, value = "generateCVV", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public String GenerateCVV(@RequestParam String strCard, @RequestParam String strExpDate,
			@RequestParam String strServiceCode, @RequestParam String strCVK) {
		String response = null;
		try {
			logger.info("Inside GenerateCVV: "+strCard+" "+strExpDate+" "+strServiceCode+" "+strCVK);
			response = hSMThalesService.GenerateCVV(strCard, strExpDate, strServiceCode, strCVK);
			logger.info("response GenerateCVV: " + response);
		} catch (Exception e) {
			logger.info("Error GenerateCVV: " + StringUtil.getExStackTrace(e));
		} finally {
			logger.info("End GenerateCVV");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "validatePinBlock", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String ValidatePinBlock(@RequestParam String strCard, @RequestParam String strZPK,
			@RequestParam String strPVK, @RequestParam String strPinBlock, @RequestParam String strDecimalizationTable,
			@RequestParam String strPINOffset) {
		String response = null;
		try {
			logger.info("Inside Validate Pin Block");
			response = hSMThalesService.ValidatePinBlock(strCard, strZPK, strPVK, strPinBlock, strDecimalizationTable,
					strPINOffset);
			logger.info("Validate Pin Block Response: " + response);

		} catch (Exception e) {
			logger.info("Error Validate Pin Block: " + StringUtil.getExStackTrace(e));
			response = e.getMessage();
		} finally {
			logger.info("End Validate Pin Block");
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "generateRandomPin", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String GenerateRandomPin(@RequestParam String strCard, @RequestParam String strPinLength) {
		String response = null;
		try {
			logger.info("Inside Generate Random Pin");
			response = hSMThalesService.GenerateRandomPin(strCard, strPinLength);
			logger.info("Generate Random Pin Response");

		} catch (Exception e) {
			logger.info("Error Generate Random Pin: " + StringUtil.getExStackTrace(e));
			response = e.getMessage();
			return e.getMessage();
		} finally {
			logger.info("End Generate Random Pin");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "generatePinOffset", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String GeneratePinOffset(@RequestParam String strCard, @RequestParam String strPVK,
			@RequestParam String strDecimalizationTable, @RequestParam String strPinLength) {
		String response = null;
		try {
			logger.info("Inside Generate Pin Offset");
			response = hSMThalesService.GeneratePinOffset(strCard, strPVK, strDecimalizationTable, strPinLength);
			logger.info("Response Generate Pin Offset: ");

		} catch (Exception e) {
			logger.info("Error Generate Pin Offset: " + StringUtil.getExStackTrace(e));
			response = e.getMessage();
		} finally {
			logger.info("End Generate Pin Offset");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "initiatePinPrintingProcess", consumes = {
			MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String InitiatePinPrintingProcess(@RequestParam String strParticipant, @RequestParam String strCardType,
			@RequestParam String strCard, @RequestParam String strPVK, @RequestParam String strDecimalizationTable,
			@RequestParam String strPinLength, @RequestParam String strPinOffset,
			@RequestParam String strFormattingdata, @RequestParam String strPrintData) {
		String response = null;
		try {
			logger.info("Inside Initiate Pin Printing Process");
			response = hSMThalesService.InitiatePinPrintingProcess(strParticipant, strCardType, strCard, strPVK,
					strDecimalizationTable, strPinLength, strPinOffset, strFormattingdata, strPrintData);
			logger.info("Response Initiate Pin Printing Process: " + response);
		} catch (Exception e) {
			logger.info("Error Initiate Pin Printing Process: " + StringUtil.getExStackTrace(e));
			response = e.getMessage();
		} finally {
			logger.info("End Initiate Pin Printing Process");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "derivePin", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String DerivePin(@RequestParam String strCard, @RequestParam String strPVK,
			@RequestParam String strDecimalizationTable, @RequestParam String strPinLength,
			@RequestParam String strPinOffset) {
		String response = null;
		try {
			logger.info("Inside Derive Pin");
			response = hSMThalesService.DerivePin(strCard, strPVK, strDecimalizationTable, strPinLength, strPinOffset);
			logger.info("Response Derive Pin: " + response);
		} catch (Exception e) {
			logger.info("Error Derive Pin");
			e.printStackTrace();
			return e.getMessage();
		} finally {
			logger.info("End Derive Pin");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "loadFormattingData", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String loadFormattingData(@RequestParam String strData) {
		String response = null;
		try {
			logger.info("Inside load Formatting Data");
			response = hSMThalesService.LoadFormattingData(strData);
			logger.info("Response load Formatting Data: " + response);
		} catch (Exception e) {
			logger.info("Error load Formatting Data");
			logger.info(StringUtil.getExStackTrace(e));
		} finally {
			logger.info("Inside load Formatting Datan");
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "printPinMailerData", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public String PrintPinMailerData(@RequestParam String strCard, @RequestParam String strPIN, @RequestParam String strData) {
		String response = null;
		try {
			logger.info("Inside load Formatting Data");
			response = hSMThalesService.PrintPinMailerData(strCard, strPIN, strData);
			logger.info("Response load Formatting Data"+response);
		}
		catch (Exception e) 
		{
				logger.info("Inside Derive Pin");
				logger.info(StringUtil.getExStackTrace(e));
				response = e.getMessage();
			}
		finally 
		{
				logger.info("Inside Derive Pin");
		}
			return response;
		}

	@RequestMapping(method = RequestMethod.POST, value = "validateCVV", consumes = { MediaType.TEXT_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String ValidateCVV(@RequestParam String strCard, @RequestParam String strExpDate,
			@RequestParam String strServiceCode, @RequestParam String strCVK, @RequestParam String strCVV) 
	{
		try 
		{
			return hSMThalesService.ValidateCVV(strCard, strExpDate, strServiceCode, strCVK, strCVV);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
