package com.traneco.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;
import com.traneco.report.model.TransactionRequest;
import com.traneco.report.services.ReportService;
import com.traneco.service.services.ClientService;

@Controller
public class ReportController {
	private String className = this.getClass().getSimpleName();
	
	@Autowired
	LoggerUtil log;
	@Autowired
	ClientService clientService;
	@Autowired
	ConfigurationService configurationService;
	@Autowired
	ObjectMapper mapper;

	@Autowired
	ReportService reportService;

	
	
	@RequestMapping(value = "/viewTxnForm", method = RequestMethod.GET)
	public String viewTxnForm(TransactionRequest transactionRequest) {
		return "viewTxnForm";
	}

	//Added by prashant Tayde for Reports
	
	@RequestMapping(value = "/transactionData", method = RequestMethod.GET)
	public String getTransactionReport(Model model, TranMaster tranMaster) {
		String methodName = "transactionData";
		try {
			model.addAttribute("cardTypeList", configurationService.getCardType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While view transaction !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "transactionData";
	}
	
	@RequestMapping(value = "/cardInventoryIssued", method = RequestMethod.GET)
	public String cardInventoryIssued(Model model, CardInventoryIssued cardInventoryIssued) {
		String methodName = "cardInventoryIssued";
		try {
			model.addAttribute("cardTypeList", configurationService.getCardType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While view cardInventoryIssued Report !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardInventoryIssued";
	}

	@RequestMapping(value = "/cardInventoryPending", method = RequestMethod.GET)
	public String cardInventoryPending(Model model, CardInventoryPending cardInventoryPending) {
		String methodName = "cardInventoryPending";
		try {
			model.addAttribute("cardTypeList", configurationService.getCardType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While view cardInventoryPending Report !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardInventoryPending";
	}

	@RequestMapping(value = "/cardHotListing", method = RequestMethod.GET)
	public String cardHotListing(Model model, CardHotListing cardHotListing) {
		String methodName = "cardHotListing";
		try {
			model.addAttribute("cardTypeList", configurationService.getCardType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While view cardHotListing Report !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "cardHotListing";
	}
	
	
    //added by prashant
	
	@RequestMapping(value = "/cardHotListingMIS", method = RequestMethod.GET)
	public String cardHotListingMIS(Model model, CardHotListing cardHotListing) {
		String methodName = "cardHotListingMIS";
		try {
			model.addAttribute("cardTypeList", configurationService.getCardType());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While view cardHotListingMIS Report !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "hotListingMIS";
	}
	//Ended by Prashant Tayde
	
}
