package com.traneco.accountmanagement.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Comparator;
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
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.DenominationMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.PreSubAccountMaster;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.GLAccountTypeMaster;
import com.traneco.service.model.JournalTransfer;
import com.traneco.service.model.PreAccountMaster;

@RestController
@RequestMapping("/ams-ac-statement")
public class AmsStatementController 
{
	@Autowired
	AmsStatementService amsStatementService;
	
	@Autowired
	ApiPropertyConfig apiPropertyConfig;
	
	@Autowired
	AccountManagementServiceImpl  accountManagementServiceImpl;

	@RequestMapping(value = "/getGlAccountStatement", method = RequestMethod.POST, produces = "application/json")
	public List<GLAccountStatement> getGlAccountStatement(@RequestBody GLAccountStatement glAccountStatement)
	{
		try
		{
			List<GLAccountStatement> accountStatementList = amsStatementService.getGLAccountStatement(glAccountStatement);
			Collections.reverse(accountStatementList);//New Added
			return accountStatementList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping(value = "/downloadAccountStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedAccountStatementFilePath(@RequestBody List<AccountStatement> accountStatements) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = amsStatementService.getDownloadedAccountStatementFilePath(accountStatements);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadExcelAccountStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelAccountStatementFilePath(@RequestBody List<AccountStatement> accountStatements) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelAccountStatementFilePath] _url::"+_url);
			
			String filepath = amsStatementService.getDownloadedAccountStatementFilePathForExcel(accountStatements);
			System.out.println("[processToDownloadExcelAccountStatementFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	//GL Account Downloaded Related changes - Change Path by Pankaj Pawar 11-01-2023 ---Start
	@RequestMapping(value = "/downloadGLAccountStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedGLAccountStatementFilePath(@RequestBody List<GLAccountStatement> glAccountStatementList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = amsStatementService.getDownloadedGLAccountStatmntFilePath(glAccountStatementList);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadExcelGLAccountStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelGLAccountStatementFilePath(@RequestBody List<GLAccountStatement> glAccountStatementList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelAccountStatementFilePath] _url::"+_url);
			
			String filepath = amsStatementService.getDownloadedGLAccountStatmntFilePathForExcel(glAccountStatementList);
			System.out.println("[processToDownloadExcelAccountStatementFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//GL Account Downloaded Related changes - Change Path by Pankaj Pawar 11-01-2023 ---End
	
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
        catch (Exception e) {
            e.printStackTrace();
        } 
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file2Upload.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
   }
	
	@RequestMapping(path="/downloadCSV", method=RequestMethod.GET)
	public ResponseEntity<Resource> downloadCSVFile(@RequestParam("fileName") String fileName)  
	{
	    InputStreamResource resource = null;
	    File file2Upload = null;
	    HttpHeaders headers = null;        
	    try
	    {
	        String parentFolderPath = apiPropertyConfig.getDownloadFilePath();
	        System.out.println("FileName is::"+fileName);
	        
	        file2Upload = new File( parentFolderPath + "csv" + File.separator + fileName);
	        
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
	
	//Transaction Account Download Related changes by Sagark - Change Path by Sagark[Start]
	@RequestMapping(value = "/downloadPdfTransactionStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedTransactionStatementFilePath(@RequestBody List<AccountTranMaster> accountTranMasterList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = amsStatementService.getDownloadedTxnStatmentPdfFilePath(accountTranMasterList);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadExcelTransactionStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelTransactionStatementFilePath(@RequestBody List<AccountTranMaster> accountTranMasterList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelTsxnReportFilePath] _url::"+_url);
			
			String filepath = amsStatementService.getDownloadedTxnStatmentFilePathForExcel(accountTranMasterList);
			System.out.println("[processToDownloadExcelTxnRpeortFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	@RequestMapping(value = "/downloadExcelJournalReport", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelJournalTransferFilePath(@RequestBody List<JournalTransfer> journalTransferList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelTsxnReportFilePath] _url::"+_url);
			
			String filepath = amsStatementService.getDownloadedJournalTransferFilePathForExcel(journalTransferList);
			System.out.println("[processToDownloadExcelTxnRpeortFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadPdfGlChartStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedChartReportFilePath(@RequestBody List<GLAccountTypeMaster> glAccountTypeMasterList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = amsStatementService.getDownloadedChartReportPdfFilePath(glAccountTypeMasterList);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadExcelGlChartStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelChartReportFilePath(@RequestBody List<GLAccountTypeMaster> glAccountTypeMasterList) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelTsxnReportFilePath] _url::"+_url);
			
			String filepath = amsStatementService.getDownloadedChartReportFilePathForExcel(glAccountTypeMasterList);
			System.out.println("[processToDownloadExcelTxnRpeortFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}		
	
	/*
	@RequestMapping(value = "/downloadExcelRegisterCustLinked", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadRegisteredCustLinkedReportFilePath(@RequestBody List<AccountCreation> accountCreation) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelTsxnReportFilePath] _url::"+_url);
			
			String filepath = amsStatementService.getDownloadedRegisterCustLinkedFilePathForExcel(accountCreation);
			System.out.println("[processToDownloadRegisteredCustLinkedReportFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/ams-ac-statement/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	*/
	//Transaction Account Download Related changes - Change Path by Sagark[End]
	@RequestMapping(value = "/downloadExcelRegisteredCustomersStatment", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelRegisteredCustomersStatment(@RequestBody List<PreAccountMaster> preAccountMasterlist) 
	{
		try {
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelRegisteredCustomersStatment] _url::" + _url);

			String filepath = amsStatementService.getDownloadedregisteredCustomersFilePathForExcel(preAccountMasterlist);
			System.out.println("[processToDownloadExcelRegisteredCustomersStatment] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadExcel?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/downloadExcelRegisteredCustLinkedReportStatment", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadRegisteredCustLinkedReportFilePath(@RequestBody List<PreSubAccountMaster> preSubAccountMaster) 
	{
		try
		{
			String _url = AppConfig.getCurrentBaseUrl();
			String filepath = amsStatementService.getDownloadedRegisterCustLinkedFilePathForExcel(preSubAccountMaster);
			System.out.println("[processToDownloadExcelRegisteredCustLinkedReportStatment] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadExcel?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	//Added by sunil Y , download  added button 17-May-23 START 
	@RequestMapping(value = "/downloadPdfRegisterCustLinkedAccount", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedRegisterCustLinkedAccountFilePath(@RequestBody List<PreSubAccountMaster> preSubAccountMaster) 
	{
		try
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = amsStatementService.getDownloadedRegisterCustLinkedFilePathFor(preSubAccountMaster);
			System.out.println("filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadPdf?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//Added by sunil Y , download  added button 17-May-23 END
	
	//Added by sunil Y , download  added button 17-May-23 START 
	@RequestMapping(value = "/downloadPdfCashDeptStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedCashDeptStatementFilePath(@RequestBody List<DenominationMaster> denominationMaster) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = amsStatementService.getDownloadedCashDeptStatementPdfFilePath(denominationMaster);
			System.out.println("filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadPdf?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//Added by sunil Y , download  added button 17-May-23 END

  //Added by sunil Y , download  added button 17-May-23 START
	@RequestMapping(value = "/downloadExcelCashDeptStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelCashDeptStatementFilePath(@RequestBody List<DenominationMaster> denominationMaster) 
	{
		try
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadCashDeptStatementFilePath] _url::" + _url);

			String filepath = amsStatementService.getDownloadedCashDeptStatementPathForExcel(denominationMaster);
			System.out.println("[processToDownloadExcelCashDeptStatementFilePath] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadExcel?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//Added by sunil Y , download  added button 17-May-23 END
	
	@RequestMapping(value = "/getGLAccountLists", method = RequestMethod.GET)
	public ResponseEntity<?> getGLAcccountListData()
	{
		try 
		{
			List<GLAccountTypeMaster> glAccountMastersList = accountManagementServiceImpl.getGLAccountTypeLists();
			if (glAccountMastersList!=null && glAccountMastersList.size() > 0)
			{
				//added by sunil Y , sorting by type 
				glAccountMastersList.stream().sorted(Comparator.comparing(GLAccountTypeMaster::getStrAccountType));
				return ResponseEntity.ok(glAccountMastersList);			
			}		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	//added by sunil Y , cash withdrawal statement pdf and exel button methods , 22-May-23 START
	@RequestMapping(value = "/downloadCashWithdrawStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedCashWithdrawStatementFilePath(@RequestBody List<DenominationMaster> denominationMaster) 
	{
		try
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::" + _url);

			String filepath = amsStatementService.getDownloadedCashWithdrawalStatmntFilePath(denominationMaster);
			System.out.println("filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadPdf?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	//added by sunil Y , 26-05-23 START,
	@RequestMapping(value = "/downloadExcelCashWithdrawStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadExcelCashWithdrawStatementFilePath(@RequestBody List<DenominationMaster> denominationMasters) 
	{
		try {
			String _url = AppConfig.getCurrentBaseUrl();
			String filepath = amsStatementService.getDownloadedCashWithdrawalStatmntFilePathForExcel(denominationMasters);
			System.out.println("[processToDownloadExcelCashWithdrawStatementFilePath] filepath::" + filepath);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadExcel?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//added by sunil Y , 26-05-23 START,

	
	//Add By Abhishek T 25-05-2023-Start
	@RequestMapping(value="/downloadPDFJournalReport",method=RequestMethod.POST,produces="application/json")
	private ResponseEntity<?> processDownloadPDFJournalReport(@RequestBody List<JournalTransfer> journalTransferList)
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			String filepath = amsStatementService.getDownloadedJournalTransferFilePathForPDF(journalTransferList);

			if (filepath != null) {
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url + "/ams-ac-statement/downloadPdf?" + filepath);
				return ResponseEntity.ok(responseBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//Add By Abhishek T 25-05-2023-End
}
