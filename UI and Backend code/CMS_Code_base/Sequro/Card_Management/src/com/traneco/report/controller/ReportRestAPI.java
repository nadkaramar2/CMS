package com.traneco.report.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;
import com.traneco.report.model.HotListingMIS;
import com.traneco.report.services.ReportService;
import com.traneco.service.services.ClientService;

//created by prashant Tayde for-CMS Reports

@RestController
public class ReportRestAPI {

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

	@Autowired
	Environment env;

	@ResponseBody
	@RequestMapping(value = "/getSearchList/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<TranMaster> getSearchList(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) {
		List<TranMaster> transbean = new ArrayList<TranMaster>();
		String methodName = "searchList";
		{
			try {
				transbean = reportService.getSearchDetails(fromDate, toDate);
				model.addAttribute("cardTypeList", configurationService.getCardType());

			} catch (Exception e) {
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view transaction !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			}
		}
		return transbean;
	}

	@ResponseBody
	@RequestMapping(value = "/getCardInventoryIssued/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<CardInventoryIssued> getCardInventoryIssued(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) {
		List<CardInventoryIssued> inventoryIssued = new ArrayList<CardInventoryIssued>();
		String methodName = "cardInventoryIssued";
		{
			try {
				System.out.println("ReportRestAPI.getSearchList()::fromDate" + fromDate);
				System.out.println("ReportRestAPI.getSearchList()::toDate" + toDate);
				inventoryIssued = reportService.getCardInventoryIssued(fromDate, toDate);
				model.addAttribute("cardTypeList", configurationService.getCardType());

			} catch (Exception e) {
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view transaction !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));

			}
		}
		return inventoryIssued;
	}

	@ResponseBody
	@RequestMapping(value = "/getCardInventoryPending/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<CardInventoryPending> getCardInventoryPending(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) {
		List<CardInventoryPending> inventoryPending = new ArrayList<CardInventoryPending>();
		;
		String methodName = "cardInventoryPending";
		{
			try {
				inventoryPending = reportService.getCardInventoryPending(fromDate, toDate);
				model.addAttribute("cardTypeList", configurationService.getCardType());

			} 
			catch (Exception e) 
			{
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view transaction !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			}
		}

		return inventoryPending;
	}

	@ResponseBody
	@RequestMapping(value = "/getCardHotListing/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<CardHotListing> getCardHotListing(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) 
		{
		List<CardHotListing> hotListing = new ArrayList<CardHotListing>();
		String methodName = "cardHotListing";
		{
			try 
			{
				hotListing = reportService.getCardHotListing(fromDate, toDate);
				model.addAttribute("cardTypeList", configurationService.getCardType());

			} 
			catch (Exception e) 
			{
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view transaction !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			}
		}
		return hotListing;
	}

	@ResponseBody
	@RequestMapping(value = "/getHotListingMIS/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<HotListingMIS> getHotListingMIS(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) {
		List<HotListingMIS> hotListingMIS = new ArrayList<HotListingMIS>();
		String methodName = "cardHotListingMIS";
		{
			try 
			{
				hotListingMIS = reportService.getHotListingMIS(fromDate, toDate);
				// hotListingMIS = reportService.getHotListingMISNetwork(fromDate, toDate);
			} catch (Exception e) 
			{
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view transaction !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			}
		}
		return hotListingMIS;
	}

	@ResponseBody
	@RequestMapping(value = "/hotListingMISNetwork/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<HotListingMIS> hotListingMISNetwork(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) 
		{
		List<HotListingMIS> hotListingMISnetwork = new ArrayList<HotListingMIS>();
		String methodName = "hotListingMISNetwork";
		{
			try 
			{
				System.out.println("ReportRestAPI.hotListingMISNetwork()" + fromDate);
				System.out.println("ReportRestAPI.hotListingMISNetwork()" + toDate);
				hotListingMISnetwork = reportService.getHotListingMISNetwork(fromDate, toDate);

			} 
			catch (Exception e) 
			{
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view hotListingMISNetwork !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			}
		}
		return hotListingMISnetwork;
	}

	@ResponseBody
	@RequestMapping(value = "/totalHotListingMIS/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public int getTotalHotListingMIS(@PathVariable(value = "fromDate") String fromDate,

			@PathVariable(value = "toDate") String toDate, Model model) { //
		// List<HotListingMIS> totalHotListingMIS = new ArrayList<HotListingMIS>();
		int count = 0;
		String methodName = "totalHotListingMIS";
		{
			try {
				System.out.println("ReportRestAPI.totalHotListingMIS()" + fromDate);
				System.out.println("ReportRestAPI.totalHotListingMIS()" + toDate);
				count = reportService.getTotalHotListingMIS(fromDate, toDate);

			} catch (Exception e) {
				model.addAttribute("display", "block");
				model.addAttribute("exception", "Error occurs While view totalHotListingMIS !");
				log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
			}
		}
		return count;
	}
	// Ended by prashant Tayde for getting report data
}
