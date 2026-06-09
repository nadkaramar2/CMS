package com.traneco.report.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traneco.config.ApiPropertyConfig;
import com.traneco.configuration.model.TranMaster;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;

@Service
public class CmsReportServiceImpl implements CmsReportService
{
	
	@Autowired
	Environment env;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ApiPropertyConfig apiPropertyConfig;
	
	@Override
	public String getDownloadedTransactionReportFilePath(List<TranMaster> tranMaster) 
	{
		try 
		{
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		  
		    Map<String, Object> data = new HashMap<>();
		    

	        data.put("bodyInfoInList", tranMaster);
	        data.put("templateName", "transaction-report");
	        data.put("fileName", "transaction-report-");
	        
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");
		    
		    String responseEntity = restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("personResultAsJsonStr::"+responseEntity);
		
			return responseEntity;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public String getDownloadTransactionReportFilePathForExcel(List<TranMaster> tranMaster) {

		try {
			String serverUrl = this.env.getProperty("ams.file_download_url") + this.env.getProperty("ams.export_excel")
					+ "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", tranMaster);

			// key contains mapping data for getting value and value contains header name

			//Map<String, String> headerDataMap = new HashMap<>();
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();
			headerDataMap.put("strLocal_tran_date", "Transaction Date");
			headerDataMap.put("strLocal_tran_time", "Transaction Time");
			headerDataMap.put("strtxnId", "Txn Id");
			headerDataMap.put("strMaskCardNo", "Card Number");
			headerDataMap.put("strTxnAmount", "Amount");
			headerDataMap.put("strProcessingCode", "Transaction Type");
			headerDataMap.put("strMid", "MID");
			headerDataMap.put("strTID", "TID");
			headerDataMap.put("strRRN", "RRN");
			headerDataMap.put("strMccCode", "MCC Code");
			headerDataMap.put("strResponseCode", "Response Code");
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "transaction-report-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getDownloadedCardInventoryIssuedReportFilePath(List<CardInventoryIssued> cardInventoryIssued) {
		try 
		{
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		  
		    Map<String, Object> data = new HashMap<>();
		    

	        data.put("bodyInfoInList", cardInventoryIssued);	        
	        //data.put("headerInfoMap", customer);
	        data.put("templateName", "card-inventory-issued-report");
	        data.put("fileName", "card-inventory-issued-report-");
	        
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");
		    
		    String responseEntity = restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("personResultAsJsonStr::"+responseEntity);
		
			return responseEntity;
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public String getDownloadedCardInventoryIssuedReportFilePathForExcel(List<CardInventoryIssued> tranMaster) {
		try {
			String serverUrl = this.env.getProperty("ams.file_download_url") + this.env.getProperty("ams.export_excel")
					+ "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", tranMaster);

			// key contains mapping data for getting value and value contains header name

			//Map<String, String> headerDataMap = new HashMap<>();
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();
			headerDataMap.put("card_issue_date", "Card Issue Date");
			headerDataMap.put("cardType", "Card Type");
			headerDataMap.put("card_bin", "Card Bin");
			headerDataMap.put("sold", "Issued");
			headerDataMap.put("expiry_date", "Card Expiry Date");
		
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "cardInventoryIssued-report-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCardInventoryPendingReportFilePath(List<CardInventoryPending> cardInventoryPending) {
		try 
		{
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		  
		    Map<String, Object> data = new HashMap<>();
		    

	        data.put("bodyInfoInList", cardInventoryPending);	        
	        //data.put("headerInfoMap", customer);
	        data.put("templateName", "card-inventory-pending-report");
	        data.put("fileName", "card-inventory-pending-report-");
	        
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");
		    
		    String responseEntity = restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("personResultAsJsonStr::"+responseEntity);
		
			return responseEntity;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCardInventoryPendingReportFilePathForExcel(
			List<CardInventoryPending> cardInventoryPending) {
		try {
			String serverUrl = this.env.getProperty("ams.file_download_url") + this.env.getProperty("ams.export_excel")
					+ "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", cardInventoryPending);

			// key contains mapping data for getting value and value contains header name

			//Map<String, String> headerDataMap = new HashMap<>();
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();
			headerDataMap.put("card_issue_date", "Card Issue Date");
			headerDataMap.put("cardtype", "Card Type");
			headerDataMap.put("card_bin", "Card Bin");
			headerDataMap.put("unsold", "Pending");
			headerDataMap.put("expiry_date", "Card Expiry Date");
			
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "cardInventoryPending-report-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCardHotListingReportFilePath(List<CardHotListing> cardHotListing) {
		try 
		{
			String serverUrl = this.env.getProperty("ams.file_download_url")+ this.env.getProperty("ams.export_pdf") +"/getDownloadFile";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		  
		    Map<String, Object> data = new HashMap<>();
		    

	        data.put("bodyInfoInList", cardHotListing);	        
	        //data.put("headerInfoMap", customer);
	        data.put("templateName", "card-hotlisting-report");
	        data.put("fileName", "card-hotlisting-report-");
	        
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);
		    
		    System.out.println("Calling .....processToGETDownloadedFile Request");
		    
		    String responseEntity = restTemplate.postForObject(serverUrl, request, String.class);
		    System.out.println("personResultAsJsonStr::"+responseEntity);
		
			return responseEntity;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDownloadedCardHotListingReportFilePathForExcel(List<CardHotListing> cardHotListing) {
		try {
			String serverUrl = this.env.getProperty("ams.file_download_url") + this.env.getProperty("ams.export_excel")
					+ "/getDownloadFile";
			HttpHeaders headers = new HttpHeaders();

			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, Object> data = new HashMap<>();
			data.put("bodyInfoInList", cardHotListing);

			// key contains mapping data for getting value and value contains header name

			//Map<String, String> headerDataMap = new HashMap<>();
			LinkedHashMap<String, String> headerDataMap = new LinkedHashMap<>();
			
			headerDataMap.put("card_issue_date", "Card Issue Date");
			headerDataMap.put("cardType", "Card Type");
			headerDataMap.put("card_number", "Card Number");
			headerDataMap.put("card_status_changed_date", "Card Hot Listed Date");
			headerDataMap.put("card_status_changed_time", "Card Hot Listed Time");
			headerDataMap.put("card_status_description", "Card Hot Listed Reason");
			headerDataMap.put("status_change_user", "Card Hot Listed By");
			
			data.put("headerInfoMap", headerDataMap);

			data.put("fileName", "hotListing-report-");

			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(data, headers);

			System.out.println("Calling .....processToGETDownloadedFile Request");
			String responseEntity = this.restTemplate.postForObject(serverUrl, request, String.class);
			System.out.println("responseEntity::" + responseEntity);

			return responseEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
