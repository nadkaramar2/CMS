package com.traneco.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traneco.common.ResponseBean;
import com.traneco.config.ApiPropertyConfig;
import com.traneco.config.AppConfig;
import com.traneco.configuration.model.TranMaster;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;

//Author By Prashant T -29Aug2023


@RequestMapping("/cms-reports")
@RestController
public class CmsReportsController 
{
	@Autowired
	CmsReportService cmsReportService;
	
	@Autowired
	ApiPropertyConfig apiPropertyConfig;
	
	@RequestMapping(value = "/downloadTransactionReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadTransactionReportFilePath(@RequestBody List<TranMaster> tranMaster) {
		try {
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = cmsReportService.getDownloadedTransactionReportFilePath(tranMaster);
			System.out.println("filepath::" + filepath);

			if (filepath != null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadExcelTransactionReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExceltransactionReportFilePath(@RequestBody List<TranMaster> tranMaster) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExceltransactionReportFilePath] _url::" + _url);

			String filepath = cmsReportService.getDownloadTransactionReportFilePathForExcel(tranMaster);
			System.out.println("[processToDownloadExceltransactionReportFilePath] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadCardInventoryReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadCardInventoryReportFilePath(@RequestBody List<CardInventoryIssued> cardInventoryIssued) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = cmsReportService.getDownloadedCardInventoryIssuedReportFilePath(cardInventoryIssued);
			System.out.println("filepath::" + filepath);

			if (filepath != null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadExcelCardInventoryReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelCardInventoryReportFilePath(@RequestBody List<CardInventoryIssued> cardInventoryIssued) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelCardInventoryReportFilePath] _url::" + _url);

			String filepath = cmsReportService.getDownloadedCardInventoryIssuedReportFilePathForExcel(cardInventoryIssued);
			System.out.println("[processToDownloadExcelCardInventoryReportFilePath] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadCardInventoryPendingReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadCardInventoryPendingReportFilePath(@RequestBody List<CardInventoryPending> cardInventoryPending) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = cmsReportService.getDownloadedCardInventoryPendingReportFilePath(cardInventoryPending);
			System.out.println("filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadExcelCardInventoryPendingReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelCardInventoryPendingReportFilePath(@RequestBody List<CardInventoryPending> cardInventoryPending) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelCardInventoryPendingReportFilePath] _url::" + _url);

			String filepath = cmsReportService.getDownloadedCardInventoryPendingReportFilePathForExcel(cardInventoryPending);
			System.out.println("[processToDownloadExcelCardInventoryPendingReportFilePath] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadHotListingReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadHotListingReportFilePath(@RequestBody List<CardHotListing> cardHotListing) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = cmsReportService.getDownloadedCardHotListingReportFilePath(cardHotListing);
			System.out.println("filepath::" + filepath);

			if (filepath != null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadExcelHotListingReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelhotListingReportFilePath(
			@RequestBody List<CardHotListing> cardHotListing) {
		try {
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelhotListingReportFilePath] _url::" + _url);

			String filepath = cmsReportService.getDownloadedCardHotListingReportFilePathForExcel(cardHotListing);
			System.out.println("[processToDownloadExcelhotListingReportFilePath] filepath::" + filepath);

			if (filepath != null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S0000");
				responseBean.setMessage(_url +"/cms-reports/downloadExcel?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(path="/downloadExcel", method=RequestMethod.GET)
	public ResponseEntity<Resource> downloadExcelFile(@RequestParam("fileName") String fileName)  
	{
	    InputStreamResource resource = null;
	    File file2Upload = null;
	    HttpHeaders headers = null;        
	    try
	    {
	        String parentFolderPath = apiPropertyConfig.getDownloadFilePath();
	        System.out.println("FileName is::"+fileName);
	        
	        file2Upload = new File( parentFolderPath + "xlsx" + File.separator + fileName);
	        
	        headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");
	        
	        resource = new InputStreamResource(new FileInputStream(file2Upload));
	        System.out.println("The length of the file is : "+file2Upload.length());
	      }
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	       } 
	       return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(file2Upload.length())
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(resource);
	  }
	
	
	@RequestMapping(path="/downloadPdf", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadDocument(@RequestParam("fileName") String fileName)  
    {
        InputStreamResource resource = null;
        File file2Upload = null;
        HttpHeaders headers = null;
        
        try
        {
            String pdfDirectory = apiPropertyConfig.getDownloadFilePath();
            String filePath = pdfDirectory + "pdf/" + fileName;
            
            file2Upload = new File(filePath);
            
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            
            resource = new InputStreamResource(new FileInputStream(file2Upload));
            System.out.println("The length of the file is : "+file2Upload.length());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file2Upload.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
   }
	
}
