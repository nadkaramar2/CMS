package com.traneco.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.accountmanagement.model.AccountMasterResponse;
import com.traneco.accountmanagement.model.ApproveUpgradeTier;
import com.traneco.accountmanagement.model.BankListResponse;
import com.traneco.accountmanagement.model.ExternalServerRequestResponseModel;
import com.traneco.accountmanagement.model.TxnReqRespLogMaster;
import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.common.ResponseBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.util.Utils;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountInterestMaster;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.CategoryListModel;
import com.traneco.configuration.model.CustomerIdCreation;
import com.traneco.configuration.model.CustomerIdMap;
import com.traneco.configuration.model.CustomerIdTable;
import com.traneco.configuration.model.DenominationMaster;
import com.traneco.configuration.model.GLAccountCreation;
import com.traneco.configuration.model.PreSubAccountMaster;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountCreditCardTransactionModel;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.JournalTransfer;
import com.traneco.service.model.PreAccountMaster;

@RestController
public class AmsServiceRestAPI 
{
	@Autowired
	AccountManagementService accountManagementService;
	
	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping(value = "/getIssuedAccount", method = RequestMethod.POST, produces = "application/json")
	public List<AccountCreation> getIssuedAccountBasedOnCardLinked(@RequestBody AccountCreation accountCreation)
	{
		List<AccountCreation> accountCreationList = new ArrayList<AccountCreation>();
		try 
		{
			accountCreationList = accountManagementService.getIssuedAccountBasedOnCardLinked(accountCreation);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return accountCreationList;
	}
	
	@RequestMapping(value = "/getAccountLinkageData", method = RequestMethod.POST, produces = "application/json")
	public List<CardAccountLinkage> getAccountLinkageData(@RequestBody CardAccountLinkage cardAccountLinkage)
	{
		try 
		{
			List<CardAccountLinkage> cardAccountLinkageData =  accountManagementService.getCardAccountLinkageBasedOnAccount(cardAccountLinkage);
			return cardAccountLinkageData;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getAccountLinkageDataCard", method = RequestMethod.POST, produces = "application/json")
	public List<CardAccountLinkage> getAccountLinkageDataCard(@RequestBody CardAccountLinkage cardAccountLinkage)
	{
		try 
		{
			List<CardAccountLinkage> cardAccountLinkageData = accountManagementService.getCardAccountLinkageDataBasedOnCard(cardAccountLinkage);
			return cardAccountLinkageData;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getOutStandingData", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getOutStandingList(@RequestBody AccountCreditLimitCategory accountCreditLimitCategory)
	{
		try
		{
			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrCreditLimitCategory(accountCreditLimitCategory.getStrCreditTypeCategory());
			accountCreation.setStrAccountNumber(accountCreditLimitCategory.getStrAccountNumber());
			AccountCreation accountCreationData = accountManagementService.getOutstandingAccount(accountCreation);
			
			List<AccountCreation> accountInterestlist = new ArrayList<AccountCreation>();
			if (accountCreationData.getStrID()!=null)
			{
				accountInterestlist.add(accountCreationData);
			}
			
			return ResponseEntity.ok(accountInterestlist);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/getOutStandingInterestList", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getOutStandingList(@RequestBody AccountInterestMaster accountInterestMaster)
	{
		try
		{
			List<AccountInterestMaster> accountCreationlist = accountManagementService.getOutStandingInterestList(accountInterestMaster);
			return ResponseEntity.ok(accountCreationlist);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/getOutStandingBalanceList", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getOutStandingBalanceList(@RequestBody AccountCreditCardTransactionModel accountCreditCardTransactionModel)
	{
		try 
		{
			List<AccountCreditCardTransactionModel> accountCreditCardTransactionModels = accountManagementService.getAccountCreditCardTxn(accountCreditCardTransactionModel);
			return ResponseEntity.ok(accountCreditCardTransactionModels);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/getPreAccountData", method = RequestMethod.POST, produces = "application/json")
	public List<PreAccountMaster> getPreAccountDataList(@RequestBody PreAccountMaster preAccountMaster)
	{
		try 
		{
			return accountManagementService.getPreAccountDataList(preAccountMaster);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getIntantAccNumber", method = RequestMethod.POST, produces = "application/json")
	public List<PreAccountMaster> getIntantAccNumber(@RequestBody PreAccountMaster preAccountMaster)
	{
		try 
		{
			AccountCreation accountCreation = new AccountCreation();
			accountCreation.setStrAccountType(preAccountMaster.getStrAccountType());
			accountCreation.setStrIsInstantAccount("Y");
			
			List<AccountCreation> accountCreations = accountManagementService.getAccountInfoListBasedOnTypes(accountCreation);
			
			List<PreAccountMaster> preAccountMasters = new ArrayList<>();
			for(AccountCreation accoCreation : accountCreations)
			{
				PreAccountMaster preAccountMasterObj = new PreAccountMaster();
				preAccountMasterObj.setStrAccountNumber(accoCreation.getStrAccountNumber());
				preAccountMasters.add(preAccountMasterObj);
			}
			return preAccountMasters;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//Logic to generate cust id and display on screen by Jyoti S
	//Date - 10-03-2023
	@RequestMapping(value = { "/customerIdCreationConfigPreAccount" }, method = { RequestMethod.POST },produces = "application/json")
	//public CustomerIdCreation addcustomerIdCreationConfigPreAccount(@RequestBody PreAccountMaster preAccountMaster,Model model, CustomerIdCreation customerIDCreation) 
	public CustomerIdMap addcustomerIdCreationConfigPreAccount(@RequestBody PreAccountMaster preAccountMaster,Model model, CustomerIdCreation customerIDCreation)
	{
		try 
		{
			CustomerIdMap customerIdMap = accountManagementService.getCustomerId();	
			return customerIdMap;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/verifiedKYC", method = RequestMethod.POST)
	public PreAccountMaster verifiedKYC(@RequestBody PreAccountMaster preAccountMaster)
	{
		try 
		{
			System.out.println("During Verifying KYC.......");
			System.out.println("Inside verifiedKYC Mobile no::["+preAccountMaster.getStrMobileNo()+"]");
			
			preAccountMaster.setStrMobileNo(preAccountMaster.getStrMobileNo());
			preAccountMaster.setStrIsKycVerified("Y");
			int result = accountManagementService.updatePreAccountAccountBasedOnParam(preAccountMaster);
			
			System.out.println("Inside verifiedKYC result::["+result+"]");
			if(result > 0) 
			{
				System.out.println("Verified data stored successfully.");
			}
			
			preAccountMaster.setVerifiedResponse("verified");
			return preAccountMaster;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception occured during KYC Verified::"+e);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getKycImage", method = RequestMethod.POST)
	public PreAccountMaster getBase64EncodedImage(@RequestBody PreAccountMaster preAccountMaster)
	{
		try
		{
			
			String imageName = preAccountMaster.getStrImageName();
			String imageLocation = "";
			
			if (imageName.indexOf("tier")!=-1)
			{
				imageLocation = "D:\\Sequro\\winservice\\ams-service\\TierPhotos\\";
			}
			else 
			{
				imageLocation = "D:\\Sequro\\winservice\\ams-service\\imageUploads\\";
			}
			
			//String imagePath = "E:\\KYC_IMAGE\\" + preAccountMaster.getStrImageName(); \\Local Image Path			
			//String imageLocation = "D:\\Sequro\\winservice\\ams-service\\imageUploads\\"; //Server Image Path
			
			System.out.println("Inside getBase64EncodedImage imageLocation::["+imageLocation+"]");
			
			String imagePath = imageLocation + preAccountMaster.getStrImageName();
			System.out.println("Inside getBase64EncodedImage imagePath::["+imagePath+"]");
			
			preAccountMaster.setStrBase64Image(Utils.getBase64Image(imagePath));
			return preAccountMaster;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/saveGLAccountDataObj", method = RequestMethod.POST)
	public GLAccountCreation saveGLAccountData(@RequestBody GLAccountCreation glAccountCreation)
	{
		String message = "";
		try
		{
			
			boolean isExistAcType = accountManagementService.isGLAccountTypeAlreadyExist(glAccountCreation);
			if (!isExistAcType) 
			{
				boolean isExistAcNo = accountManagementService.isGLAccountTypeAlreadyExist(glAccountCreation);
				if (!isExistAcNo) 
				{
					glAccountCreation = accountManagementService.addGLAccountType(glAccountCreation);
					if(glAccountCreation!=null && glAccountCreation.getStrId()!=null)
					{
						message = "Account Created Successfully.";
					}
					else
					{
						message = "Error: Record not Saved.";
					}
				}
				else
				{
					message = "Error: Duplicate Account Number";
				}
			}
			else
			{
				message = "Error: Duplicate Account Type";
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			message = "exception: exception occurs while adding.";
		}
		glAccountCreation.setResponseMsg(message);
		return glAccountCreation;
	}
	
	/**
	 * Account Statement Changes by Jyoti Shirahatti
	 * @param fromDate
	 * @param toDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAccountStatementByDate/{fromDate}/{toDate}", method = RequestMethod.POST, produces = "application/json")
	public List<AccountStatement> getAccountStatementByDate(@PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate)
	{
		List<AccountStatement> accountStatementByDate = new ArrayList<AccountStatement>();
		try 
		{
			AccountStatement accountStatementObj = new AccountStatement();
			accountStatementObj.setFromDate(fromDate);
			accountStatementObj.setToDate(toDate);
			
			accountStatementByDate = accountManagementService.getAccountStatementByDate(accountStatementObj);
			
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
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return accountStatementByDate;
	}
	
	@RequestMapping(value = "/getAccountStatement/{fromDate}/{toDate}/{accountType}/{accountNumber}", method = RequestMethod.POST, produces = "application/json")
	public List<AccountStatement> getAccountStatement(@PathVariable(value = "fromDate") String fromDate,
			@PathVariable(value = "toDate") String toDate,@PathVariable(value = "accountType") String accountType,@PathVariable(value = "accountNumber") String accountNumber) 
	{
		List<AccountStatement> accountStatementByDate = new ArrayList<AccountStatement>();
		try
		{
			AccountStatement accountStatementObj = new AccountStatement();
			
			accountStatementObj.setStrAccountNumber(accountNumber);
			accountStatementObj.setStrAccountType(accountType);			
			accountStatementObj.setFromDate(fromDate);
			accountStatementObj.setToDate(toDate);
			
			accountStatementByDate = accountManagementService.getAccountStatements(accountStatementObj);
			
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
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return accountStatementByDate;
	}
	
	@RequestMapping(value = "/getAccountInfoBasedOnCustomer/{strCustId}", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<?> getAccountTypeInfoBasedOnAccountType(@PathVariable String strCustId) 
	{
		try 
		{
			CustomerIdCreation customerIdCreation = new CustomerIdCreation();
			customerIdCreation.setStrCustId(strCustId);
			
			CustomerIdCreation customerIdCreation1 = accountManagementService.getCustMstrDetails(customerIdCreation);
			
			if (customerIdCreation1!=null) {
				return ResponseEntity.ok(customerIdCreation1);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = { "/sendCustId" }, method = { RequestMethod.POST })
	//public ResponseEntity<?> sendCustIdConfig(@RequestBody PreAccountMaster preAccountMaster, Model model, CustomerIdCreation customerIDCreation) {
	public ResponseEntity<?> sendCustIdConfig(@RequestBody PreAccountMaster preAccountMaster) {	
	try 
	{
			//CustomerIdCreation custIdCreation = accountManagementService.getCustDetailsByMobileNumber(preAccountMaster.getStrMobileNo());
			
			CustomerIdMap customerIdMap = mapper.readValue(preAccountMaster.getCustomerIdMap(), CustomerIdMap.class);
			CustomerIdCreation customerIDCreation = new CustomerIdCreation();
			
			System.out.println(customerIdMap);
			
			String action = customerIdMap.getStrAction();
 			String julianYear = customerIdMap.getStrYear();
 			String customerID = customerIdMap.getStrCustID();
 			String julianDate = customerIdMap.getStrJulianDate();
 			
 			customerIDCreation.setStrAction(action);
			customerIDCreation.setStrCustId(customerID);
			customerIDCreation.setStrJulianYear(julianYear);
			customerIDCreation.setStrJulianDate(julianDate);
 			
 			
 			String participantId = sessionDTO.getParticipantid();
 			
			PreAccountMaster preAccountMasterData = accountManagementService.getPreAccountMaster(preAccountMaster);
			
			customerIDCreation.setStrCreatedBy(sessionDTO.getLoginID());
			customerIDCreation.setStrParticipantID(participantId);			
			customerIDCreation.setStrKycUpdateRequired("NO");
			
			customerIDCreation.setStrFirstName(preAccountMasterData.getStrFirstName());
			customerIDCreation.setStrMiddleName(preAccountMasterData.getStrMiddleName());
			customerIDCreation.setStrLastName(preAccountMasterData.getStrLastName());
			customerIDCreation.setStrGender(preAccountMasterData.getStrGender());
			customerIDCreation.setStrDOB(preAccountMasterData.getStrDOB());
			customerIDCreation.setStrEmailID(preAccountMasterData.getStrEmailID());
			customerIDCreation.setStrMobileNo(preAccountMasterData.getStrMobileNo());
			
			int count = accountManagementService.insertCustIdDetails(customerIDCreation);			
			if (count > 0)
			{
				CustomerIdTable customerIdTable = new CustomerIdTable();			
				customerIdTable.setStrAction(customerIDCreation.getStrAction());
				customerIdTable.setStrLastTxnSerialNo(customerIDCreation.getStrCustId());
				customerIdTable.setStrYear(customerIDCreation.getStrJulianYear());
				customerIdTable.setStrJulianDate(customerIDCreation.getStrJulianDate());
				customerIdTable.setStrCreatedBy(sessionDTO.getLoginID());
				customerIdTable.setStrCreatedDate(customerIDCreation.getStrAccountCreationDate());
				
				int cnt = accountManagementService.updateCustId(customerIdTable);
				if(cnt > 0)
				{
					String bodyMessage = "Dear User,<br/><br/> " + "Please find the below created Customer Id : <br/> "
							+ "Customer Id is : <b>" + customerIDCreation.getStrCustId() + "</br> <br/> "
							+ "Powered by Sequro Technologies Pvt Ltd.";

					//Commented by Prashant 11 Oct 2023
					//EmailTemplate emailTemplate = Utils.getEmailtemplate(preAccountMaster.getStrEmailID(), "Regarding to Customer Id", bodyMessage);
					//accountManagementService.sendMail(emailTemplate);
					
					return ResponseEntity.ok(customerIDCreation);
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
			
			
			/*
			if (custIdCreation != null) {

				String bodyMessage = "Dear User,<br/><br/> " + "Please find the below created Customer Id : <br/> "
						+ "Customer Id is : <b>" + custIdCreation.getStrCustId() + "</br> <br/> "
						+ "Powered by Sequro Technologies Pvt Ltd.";

				EmailTemplate emailTemplate = Utils.getEmailtemplate(custIdCreation.getStrEmailID(), "Regarding to Customer Id", bodyMessage);
				accountManagementService.sendAccountNoByEmail(emailTemplate);
			}
			*/
			
			//return ResponseEntity.ok(custIdCreation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null); 
	}
	//Added by sagar k for TxnId Wise List fetch[START]
	@RequestMapping(value = "/getTransactionIDList", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getTxnIdWiseJournalTransfer(@RequestBody JournalTransfer journalTransfer) 
	{
		try 
		{
			List<JournalTransfer> journalTransferData = accountManagementService.getTxnIdApprovallist(journalTransfer);
			System.out.println(journalTransferData);
			
			if (journalTransferData!=null) 
			{
				return ResponseEntity.ok(journalTransferData);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/getUpdateReasonlist", method = RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getUpdateReasonInfo(@RequestBody JournalTransfer journalTransfer) 
	{
		try 
		{
			int  count = accountManagementService.UpdateReasonDetails(journalTransfer);
			if (count>0) 
			{
				return ResponseEntity.ok(count);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}
	//Added by sagar k for TxnId Wise List fetch[END]
	
	//Added changes on 05-May-2023 Start
	@RequestMapping(value = "/getTxnReportFrom", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getTxnAccountStatement(@RequestBody AccountTranMaster accountTranMaster) {
		String methodName = "getTxnReportFrom";
		try {
			List<AccountTranMaster> txnAccountReportByDate = accountManagementService.getTxnReportlist(accountTranMaster);
			System.out.println(txnAccountReportByDate);

			if (txnAccountReportByDate != null) {
				return ResponseEntity.ok(txnAccountReportByDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/journalTransferStatement", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getTxnAccountStatement(@RequestBody JournalTransfer journalTransfer) {
		String methodName = "JournalTransferFrom";
		try {
			List<JournalTransfer> journaltransferlist = accountManagementService.getJournalStatementlist(journalTransfer);
			System.out.println(journaltransferlist);

			if (journaltransferlist != null) {
				return ResponseEntity.ok(journaltransferlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/getRegCustWithLinkAccount", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getRegCustWithLinkAccount(@RequestBody AccountCreation accountCreation) 
	{
		String methodName = "getRegCustWithLinkAccount";
		try {
			List<AccountCreation> accountCreationList = accountManagementService.getRegCustWithLinkAccount(accountCreation);
			System.out.println(accountCreationList);

			if (accountCreationList != null) {
				return ResponseEntity.ok(accountCreationList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/getPendingRegCustWithLinkAccount", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getPendingRegCustWithLinkAccount(@RequestBody PreSubAccountMaster preSubAccountMaster) {
		String methodName = "getRegCustWithLinkAccount";
		try {
			List<PreSubAccountMaster> accountCreationList = accountManagementService.getPendingRegCustWithLinkAccount(preSubAccountMaster);
			System.out.println(accountCreationList);

			if (accountCreationList != null) {
				return ResponseEntity.ok(accountCreationList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/CashWithdrawalAgentStatements", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> CashWithdrawalAgentStatement(@RequestBody DenominationMaster denominationMaster) {
		String methodName = "CashWithdrawalAgent";
		try {
			List<DenominationMaster> denominationMasterList = accountManagementService.getdenominationMasterDetails(denominationMaster);
			System.out.println(denominationMasterList);

			if (denominationMasterList != null) {
				return ResponseEntity.ok(denominationMasterList);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		List<DenominationMaster> denominationMasterEmptyList = new ArrayList<>();
		return ResponseEntity.ok(denominationMasterEmptyList);
	}
	// Cashwithdrawal statement

	@RequestMapping(value = { "/CashDepositAgentStatement" }, method = { RequestMethod.POST })
	public ResponseEntity<?> CashDepositAgentStatement(@RequestBody DenominationMaster denominationMaster)
	{
		String methodName = "CashDepositAgentStatement";
		try
		{ 
			List<DenominationMaster> denominationDepositeData = accountManagementService.getdenominationMasterDetailsbyAgent(denominationMaster); 
			if(denominationDepositeData!= null)
			{
				return ResponseEntity.ok(denominationDepositeData);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		List<DenominationMaster> denominationDepositeData = new ArrayList<>();
		return ResponseEntity.ok(denominationDepositeData);
	}
	// CashDeposit statement
	
	//Add by Abhishek -Start
	@RequestMapping(value="/getRegisterCustomersDetails", method=RequestMethod.POST, produces="application/Json")
	public ResponseEntity<?> registerCustomer(@RequestBody PreAccountMaster preAccountMaster) 
	{
		try 
		{
			List<PreAccountMaster> customersData = accountManagementService.getRegisterCustomers(preAccountMaster);
			if (customersData != null) 
			{
				return ResponseEntity.ok(customersData);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return ResponseEntity.ok(null);
	}
	//Added changes on 05-May-2023 End
	
	@RequestMapping(value = "/searchTransactionByTxnId/{txnid}", method = RequestMethod.POST, produces = "application/json")
	public List<AccountTranMaster> searchTransactionByTxnId(@PathVariable(value = "txnid") String strTxn_id) 
	{
		List<AccountTranMaster> searchTransactionbyTxnId = new ArrayList<AccountTranMaster>();
		try
		{
			AccountTranMaster searchTxnObj = new AccountTranMaster();
			searchTxnObj.setStrTxn_id(strTxn_id);
			
			searchTransactionbyTxnId = accountManagementService.searchTransactionByTxnId(searchTxnObj);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return searchTransactionbyTxnId;
	}
	
	@RequestMapping(value = "/getUpgradeTierType/{tierType}", method = RequestMethod.POST, produces = "application/json")
	public List<ApproveUpgradeTier> getUpgradeTierType(@PathVariable(value = "tierType") String strTierType)
	{
		List<ApproveUpgradeTier> list = new ArrayList<ApproveUpgradeTier>();
		try 
		{
			ApproveUpgradeTier approveTierType = new ApproveUpgradeTier();
			approveTierType.setStrTierType(strTierType);
			list = accountManagementService.getUpgradeTierType(approveTierType);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return list;

	}	
	
	@ResponseBody
	@RequestMapping(value = "/updateAccountTypeCategory", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateAccountTypeCategory(@RequestBody CategoryListModel categoryListModel) 
	{
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = accountManagementService.updateAccountTypeCategory(categoryListModel);
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
	
	
	
	@ResponseBody
	@RequestMapping(value = "/updateAccountLimitCreditType", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseBean updateAccountLimitCredit(@RequestBody AccountCreditLimitCategory accountCreditLimitCategory) 
	{
		ResponseBean responseBean = new ResponseBean();
		try {
			int count = accountManagementService.updateAccountLimitCredit(accountCreditLimitCategory);
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
		} 
		catch (Exception e) 
		{
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	
	@RequestMapping(value = "/getAccountHolderName",  method =  RequestMethod.POST, produces = "application/json")
	private ResponseEntity<?> getAccountHolderName(@RequestBody AccountCreation accountCreation) {
		try {
			
			List<AccountCreation> strAccountHolderName = accountManagementService.getStrAccountHolderName(accountCreation);
			
			if (strAccountHolderName != null && strAccountHolderName.size() > 0) 
			{
				AccountCreation accountCreationResponse = strAccountHolderName.get(0);
				return ResponseEntity.ok(accountCreationResponse);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Added for getting accountinfo list for Close Account Start
		@RequestMapping(value = "/getAccountInfoForClosingAccount", method = RequestMethod.POST, produces = "application/json")
		public List<AccountMasterResponse> getAccountInformationForClosingAccount(@RequestBody AccountCreation accountCreation)
		{
			List<AccountMasterResponse> list = null;
			try 
			{
				list = accountManagementService.getAccountInformationForClosingAccount(accountCreation);
				if (list !=null && list.size() > 0) 
				{
					return list;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			list = new ArrayList<>();
			return list;

		}
		//Added for getting accountinfo list for Close Account End
		
		
		
		//Added by Sunny Soni for getting non credit account type list Start
		@RequestMapping(value = "/nonCreditAccounType", method = RequestMethod.GET, produces = "application/json")
		public List<AccountTypeMaster> getNonCreditAccounType()
		{
			List<AccountTypeMaster> list = null;
			try 
			{
				AccountTypeMaster accountTypeMaster = new AccountTypeMaster();
				list = accountManagementService.getNonCreditAccounTypeObject(accountTypeMaster);
				if (list !=null && list.size() > 0) 
				{
					return list;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			list = new ArrayList<>();
			return list;

		}
	
	@RequestMapping(value = "/getThirdPartyAccountName", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getThirdPartyAccountName(@RequestBody ExternalServerRequestResponseModel externalServerRequestResponseModel)
	{
		ProcessResponse processResponse = null;
		try 
		{
			processResponse = accountManagementService.getThirdPartyAccountName(externalServerRequestResponseModel);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.ok(processResponse);
	}
	@RequestMapping(value = "/getThirdPartyBankList", method = RequestMethod.GET, produces = "application/json")
	public List<BankListResponse> getThirdPartyBankList()
	{
		List<BankListResponse> list = null;
		try 
		{
			ExternalServerRequestResponseModel externalServerRequestResponseModel = new ExternalServerRequestResponseModel();
			ProcessResponse processResponse = accountManagementService.getThirdPartyBankList(externalServerRequestResponseModel);
			if (processResponse != null) 
			{
				if ("S0000".equalsIgnoreCase(processResponse.getCode())) 
				{
					list = processResponse.getBanksList();
					if (list !=null && list.size() > 0) 
					{
						return list;
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		list = new ArrayList<>();
		return list;

	}
	
	
	//Added by Prashant Tayde 01Sep2023
	@RequestMapping(value = "/getTxnReqResLogsList", method = RequestMethod.POST, produces = "application/json")
	public List<TxnReqRespLogMaster> getTxnReqRespLogMaster(@RequestBody TxnReqRespLogMaster txnReqRespLogMaster)
	{
		List<TxnReqRespLogMaster> list = null;
		try 
		{
			list = accountManagementService.getTxnReqRespLogMaster(txnReqRespLogMaster);
			if (list !=null && list.size() > 0) 
			{
				return list;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		list = new ArrayList<>();
		return list;

	}
	
}
