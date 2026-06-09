package com.traneco.service.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.traneco.common.ResponseBean;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.config.AppConfig;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.GlAccountTypeWiseStatementMaster;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MerchantCategoryCodeMaster;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.services.ClientService;

@RestController
public class ServiceRestAPI {

	private String className = this.getClass().getSimpleName();
	
	@Autowired
	LoggerUtil log;
	
	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	Environment env;
	
	//added by prashant T for deletion of account
	
	@ResponseBody
	@RequestMapping(value = "/deleteAccountCreation", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteAccountCreation(@RequestParam(value = "id") String id) 
	{
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteAccountCreation(id);
			if (count > 0) 
			{
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} 
			else 
			{
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	

	//added by prashant T for update account creation 
	
	@ResponseBody
	@RequestMapping(value = "/updateAccountCreation", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean updateAccountCreation(@RequestBody AccountCreation accountCreation) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateAccountCreation(accountCreation);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	
	
	//Added by prashant tayde for delete account type
	
	@ResponseBody
	@RequestMapping(value = "/deleteAccountType", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteAccountType(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteAccountType(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	//added by prashant T for update account type

	@ResponseBody
	@RequestMapping(value = "/updateAccountType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateAccountType(@RequestBody AccountTypeMaster accountTypeMaster) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateAccountType(accountTypeMaster);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	//added by prashant t for delete card account linkage
	
	@ResponseBody
	@RequestMapping(value = "/deleteCardAccountLinkage", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean deleteCardAccountLinkage(@RequestParam(value = "id") String id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.deleteCardAccountLinkage(id);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	//added by prashant tayde for updated card account linkage
	
	@ResponseBody
	@RequestMapping(value = "/updateCardAccountLinkage", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean updateCardAccountLinkage(CardAccountLinkage cardAccountLinkage) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateCardAccountLinkage(cardAccountLinkage);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	//added by prashant tayde for update account limit creadit
	
	@ResponseBody
	@RequestMapping(value = "/updateAccountLimitCredit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateAccountLimitCredit(@RequestBody AccountCreditLimitCategory accountCreditLimitCategory) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateAccountLimitCredit(accountCreditLimitCategory);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	
	//Added by prashant Tayde for update mcc interest form ==09Dec22
	
	@ResponseBody
	@RequestMapping(value = "/updateMCCWiseInterest", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateMCCWiseInterest(@RequestBody MccWiseInterestModel mccWiseInterestModel) {
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = configurationService.updateMCCWiseInterest(mccWiseInterestModel);
			if (count > 0) {
				responseBean.setOutResponseCode("00");
				responseBean.setMessage("Success");
			} else {
				responseBean.setOutResponseCode("11");
				responseBean.setMessage("Error");
			}
		} catch (Exception e) {
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	/**
	 * Search Account Linkage View for account type,account number
	 * @param accountType
	 * @param accountNumber
	 * @param model
	 * @return
	 * @author Jyoti Date- 06-12-2022
	 */
	/*
	@RequestMapping(value = "/getAccountLinkageData/{accountType}/{accountNumber}", method = RequestMethod.POST, produces = "application/json")
	public List<CardAccountLinkage> getAccountLinkageData(@PathVariable(value = "accountType") String accountType,
			@PathVariable(value = "accountNumber") String accountNumber, Model model) {
		List<CardAccountLinkage> cardAccountLinkageData = new ArrayList<CardAccountLinkage>();
		String methodName = "searchList";
		try {
			System.out.println("cardManagement.getCardAccountLinkageList()::Account Type " + accountType);
			System.out.println("cardManagement.getCardAccountLinkageList()::Account Number " + accountNumber);
			cardAccountLinkageData = configurationService.getCardAccountLinkage(accountType, accountNumber);
			//model.addAttribute("MccWiseInterestList", configurationService.getMccWiseInterest());
			//model.addAttribute("mccWiseInterest",this.mapper.writeValueAsString(this.configurationService.getMccWiseInterest()));
			//model.addAttribute("cardAccountLinkList", configurationService.getCardAccountLinkage());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			 model.addAttribute("exception", "Error occurs While add Service !"); 
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return cardAccountLinkageData;
	}
	
	@RequestMapping(value = "/getAccountLinkageDataCard/{cardType}/{cardNumber}", method = RequestMethod.POST, produces = "application/json")
	public List<CardAccountLinkage> getAccountLinkageDataCard(@PathVariable(value = "cardType") String cardType,
			@PathVariable(value = "cardNumber") String cardNumber, Model model) {
		List<CardAccountLinkage> cardAccountLinkageData = new ArrayList<CardAccountLinkage>();
		String methodName = "searchList";
		try {
			System.out.println("cardManagement.getCardAccountLinkageList()::Card Type " + cardType);
			System.out.println("cardManagement.getCardAccountLinkageList()::Card Number " + cardNumber);
			cardAccountLinkageData = configurationService.getCardAccountLinkageCard(cardType, cardNumber);
			//model.addAttribute("MccWiseInterestList", configurationService.getMccWiseInterest());
			//model.addAttribute("mccWiseInterest",this.mapper.writeValueAsString(this.configurationService.getMccWiseInterest()));
			//model.addAttribute("cardAccountLinkList", configurationService.getCardAccountLinkage());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			 model.addAttribute("exception", "Error occurs While add Service !"); 
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return cardAccountLinkageData;
	}
	*/
	//Added DOWNLOADED RELATED CHANGES START
	
	@RequestMapping(value = "/getDownloadFilePath", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToGETDownloadedFilePath(@RequestBody List<MerchantCategoryCodeMaster> merchantCategoryCodeMasters) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = configurationService.getDownloadedPDFPath(merchantCategoryCodeMasters);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/downloadPdf?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/downloadAccountStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedAccountStatementFilePath(@RequestBody List<AccountStatement> accountStatements) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = configurationService.getDownloadedAccountStatementFilePath(accountStatements);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/downloadPdf?"+filepath);
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
			
			String filepath = configurationService.getDownloadedAccountStatementFilePathForExcel(accountStatements);
			System.out.println("[processToDownloadExcelAccountStatementFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(path="/downloadPdf", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadDocument(@RequestParam("fileName") String fileName)  
    {
        InputStreamResource resource = null;
        File file2Upload = null;
        HttpHeaders headers = null;
        
        try
        {
            System.out.println("c"+fileName);
            String pdfDirectory = env.getProperty("DOWNLOAD_FILE_FOLDER_PATH");
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
	        String parentFolderPath = env.getProperty("DOWNLOAD_FILE_FOLDER_PATH");
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
	        String parentFolderPath = env.getProperty("DOWNLOAD_FILE_FOLDER_PATH");
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
	//Added DOWNLOADED RELATED CHANGES END
	
	/**
	 * Account Statement Changes by Jyoti Shirahatti
	 * @param fromDate
	 * @param toDate
	 * @param model
	 * @return
	 */
	/*
	@RequestMapping(value = "/getAccountStatementByDate/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<AccountStatement> getAccountStatementByDate(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate, Model model) {
		List<AccountStatement> accountStatementByDate = new ArrayList<AccountStatement>();
		String methodName = "searchList";
		try {
			System.out.println("cardManagement.getAccountStatementList()::fromDate " + fromDate);
			System.out.println("cardManagement.getAccountStatementList()::toDate " + toDate);
			accountStatementByDate = clientService.getAccountStatementByDate(fromDate, toDate);
			
			for (AccountStatement accountStatement : accountStatementByDate) 
			{
				if ("CREDIT".equalsIgnoreCase(accountStatement.getStrTranMode()) || "REVERSAL".equalsIgnoreCase(accountStatement.getStrTranMode())) 
				{
					accountStatement.setStrCreditAmount(accountStatement.getStrTransactionAmount());
					accountStatement.setStrDebitAmount("0");
				}
				else
				{
					accountStatement.setStrCreditAmount("0");
					accountStatement.setStrDebitAmount(accountStatement.getStrTransactionAmount());
				}
			}
		} 
		catch (Exception e) {
			model.addAttribute("display", "block");
			 model.addAttribute("exception", "Error occurs While add Service !"); 
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return accountStatementByDate;
	}
	*/
	/*
	@RequestMapping(value = "/getAccountStatement/{fromDate}/{toDate}/{accountType}/{accountNumber}", method = RequestMethod.POST, produces = "application/json")
	public List<AccountStatement> getAccountStatement(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate,@PathVariable(value = "accountType") String accountType,@PathVariable(value = "accountNumber") String accountNumber, Model model) 
	{
		List<AccountStatement> accountStatementByDate = new ArrayList<AccountStatement>();
		String methodName = "searchList";
		try {
			System.out.println("cardManagement.getAccountStatementList()::fromDate " + fromDate);
			System.out.println("cardManagement.getAccountStatementList()::toDate " + toDate);
			System.out.println("cardManagement.getAccountStatementList()::Account Type " + accountType);
			System.out.println("cardManagement.getAccountStatementList()::Account Number " + accountNumber);
			accountStatementByDate = clientService.getAccountStatement(accountType,accountNumber,fromDate, toDate);
			
			for (AccountStatement accountStatement : accountStatementByDate) 
			{
				if ("CREDIT".equalsIgnoreCase(accountStatement.getStrTranMode()) || "REVERSAL".equalsIgnoreCase(accountStatement.getStrTranMode())) 
				{
					accountStatement.setStrCreditAmount(accountStatement.getStrTransactionAmount());
					accountStatement.setStrDebitAmount("0");
				}
				else
				{
					accountStatement.setStrCreditAmount("0");
					accountStatement.setStrDebitAmount(accountStatement.getStrTransactionAmount());
				}
			}
			
			//model.addAttribute("MccWiseInterestList", configurationService.getMccWiseInterest());
			//model.addAttribute("mccWiseInterest",this.mapper.writeValueAsString(this.configurationService.getMccWiseInterest()));
			//model.addAttribute("cardAccountLinkList", configurationService.getCardAccountLinkage());
		} catch (Exception e) {
			model.addAttribute("display", "block");
			 model.addAttribute("exception", "Error occurs While add Service !"); 
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return accountStatementByDate;
	}
	*/
	
	@RequestMapping(value = "/getAccountIssueData/{accountLinkedFlag}", method = RequestMethod.POST, produces = "application/json")
	public List<AccountCreation> getAccountIssueData(@PathVariable(value = "accountLinkedFlag") String accountLinkedFlag, Model model)
	{
		List<AccountCreation> accountCreationList = new ArrayList<AccountCreation>();
		String methodName = "searchList";
		try 
		{
			accountCreationList = clientService.getAccountIssueData(accountLinkedFlag);
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !"); 
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return accountCreationList;
	}
	
	
	/**
	 * GL ACCOUNT CREATION CODE
	 * @param glAccountType
	 * @param glAccountDescription
	 * @param glAccountNumber
	 * @param model
	 * @return
	 */
	
	// GL Account Creation Save Logic
	/*
	@RequestMapping(value = "/saveGLAccountData/{glAccountType}/{glAccountDescription}/{glAccountNumber}", method = RequestMethod.POST, produces = "application/json")
	public List<GLAccountCreation> saveGLAccountData(@PathVariable(value = "glAccountType") String glAccountType, @PathVariable(value = "glAccountDescription") String glAccountDescription, @PathVariable(value = "glAccountNumber") String glAccountNumber, Model model)
	{
		List<GLAccountCreation> getGLAccountList = new ArrayList<GLAccountCreation>();
		String methodName = "saveList";
		int count = 0;
		try
		{
			getGLAccountList = clientService.getGLAccountTypeList();
			System.out.println("GLAccountNumber :: Account Number" + glAccountNumber);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String Date = dateFormat.format(cal.getTime());
			
			if (!getGLAccountList.isEmpty()) 
			{
				String glType = glAccountNumber.substring(3, 5);
				String glAccountNo = glAccountNumber.substring(5);
				
				for (int i = 0; i < getGLAccountList.size(); i++)
				{
					System.out.println("GL TYPE ::" + glType);
					
					if (!getGLAccountList.get(i).getStrGLAccountType().equalsIgnoreCase(glAccountType)) 
					{
						String glType1 = getGLAccountList.get(i).getStrGLAccountNumber().substring(3, 5);
						String glAccountNo1 = getGLAccountList.get(i).getStrGLAccountNumber().substring(5);
						System.out.println("GL TYPE ::" + glType1);
						if (!glType.equalsIgnoreCase(glType1)) 
						{
							if (!glAccountNo.equalsIgnoreCase(glAccountNo1)) 
							{
								count = 1;
							} 
							else
							{
								String message = "Duplicate Account Number";
								model.addAttribute("error", message);
							}
						} 
						else 
						{
							String message = "Duplicate Account Number";
							model.addAttribute("error", message);
						}
					} 
					else 
					{
						String message = "Duplicate Account Type";
						model.addAttribute("error", message);
					}
				}
				if(count == 1) 
				{
					this.clientService.saveGLAccountList(glAccountType, glAccountDescription,glAccountNumber, Date);
				}
			} 
			else
			{
				count = clientService.saveGLAccountList(glAccountType, glAccountDescription, glAccountNumber, Date);
			}

		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		if (count != 0) {
			return clientService.getGLAccountTypeList();
		} else {
			return null;
		}
	}
	*/

	@RequestMapping(value = "/getGLAccountData", method = RequestMethod.POST, produces = "application/json")
	public List<GLAccountCreation> getGLAccountData(Model model) {
		List<GLAccountCreation> getGLAccountList = new ArrayList<GLAccountCreation>();
		String methodName = "getList";
		try {
			getGLAccountList = clientService.getGLAccountTypeList();
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", "Error occurs While add Service !");
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return getGLAccountList;
	}
	
	//GL ACCOUNT CREATION CODE ENDS

//	GL Account from date and todate for pdfdownload Related changes - Change Path by sagark 12-01-2023 ---Start
	
	@RequestMapping(value = "/getGlAccountStatementByDate/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
    public List<GlAccountTypeWiseStatementMaster> getGlAccountStatementByDate(@PathVariable(value = "fromDate") String fromDate,
            @PathVariable(value = "toDate") String toDate, Model model) {
		 List<GlAccountTypeWiseStatementMaster> glaccountStatementByDate = new ArrayList<GlAccountTypeWiseStatementMaster>();
		 String methodName = "searchList";
		 try {
			 
			System.out.println("cardManagement.getGlAccountStatementList()::fromDate " + fromDate);
			System.out.println("cardManagement.getGlAccountStatementList()::toDate " + toDate);
			glaccountStatementByDate = clientService.getGlAccountStatementByDate(fromDate, toDate);
		} 
		 catch (Exception e) {
			// TODO: handle exception
			 model.addAttribute("display", "block");
			 model.addAttribute("exception", "Error occurs While add Service !");
			 log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return glaccountStatementByDate;
		
	  }
	
	@RequestMapping(value = "/getGlAccountStatement/{fromDate}/{toDate}/{accountType}/{accountNumber}", method = RequestMethod.POST, produces = "application/json")
	public List<GlAccountTypeWiseStatementMaster> getGlAccountStatement(@PathVariable(value = "fromDate") String fromDate,
	@PathVariable(value = "toDate") String toDate,@PathVariable(value = "accountType") String accountType,@PathVariable(value = "accountNumber") String accountNumber, Model model)
	{
		List<GlAccountTypeWiseStatementMaster> accountStatementByDate = new ArrayList<GlAccountTypeWiseStatementMaster>();
	String methodName = "searchList";
	try {
		System.out.println("cardManagement.getGlAccountStatementList()::fromDate " + fromDate);
		System.out.println("cardManagement.getGlAccountStatementList()::toDate " + toDate);
		System.out.println("cardManagement.getGlAccountStatementList()::Account Type " + accountType);
		System.out.println("cardManagement.getGlAccountStatementList()::Account Number " + accountNumber);
		accountStatementByDate = clientService.getGlAccountStatement(accountType,accountNumber,fromDate, toDate);
		
	} catch (Exception e) {
		// TODO: handle exception
		model.addAttribute("display", "block");
		model.addAttribute("exception", "Error occurs While add Service !");
		 log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
	}
		return accountStatementByDate;
		
	}
//	GL Account from date and todate for pdfdownload Related changes - Change Path by sagark 12-01-2023 ---End
	
	//GL Account Downloaded Related changes - Change Path by Pankaj Pawar 11-01-2023 ---Start
	@RequestMapping(value = "/downloadGLAccountStatement", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> processToDownloadedGLAccountStatementFilePath(@RequestBody List<GlAccountTypeWiseStatementMaster> glAccountTypeWiseStatementMaster) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("_url::"+_url);
			
			String filepath = configurationService.getDownloadedGLAccountStatmntFilePath(glAccountTypeWiseStatementMaster);
			System.out.println("filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/downloadPdf?"+filepath);
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
	private ResponseEntity<?> processToDownloadExcelGLAccountStatementFilePath(@RequestBody List<GlAccountTypeWiseStatementMaster> glAccountTypeWiseStatementMaster) 
	{
		try 
		{
			String _url = AppConfig.getCurrentBaseUrl();
			System.out.println("[processToDownloadExcelAccountStatementFilePath] _url::"+_url);
			
			String filepath = configurationService.getDownloadedGLAccountStatmntFilePathForExcel(glAccountTypeWiseStatementMaster);
			System.out.println("[processToDownloadExcelAccountStatementFilePath] filepath::"+filepath);
			
			if(filepath!=null) 
			{
				ResponseBean responseBean = new ResponseBean();
				responseBean.setOutResponseCode("S000");
				responseBean.setMessage(_url +"/downloadExcel?"+filepath);
				return ResponseEntity.ok(responseBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
//	GL Account Downloaded Related changes - Change Path by Pankaj Pawar 11-01-2023 ---End
	
	
	//Added by prashant T
	
	
	
}
