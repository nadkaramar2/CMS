package com.traneco.accountmanagement.services;

import java.util.List;

import com.traneco.api.model.AccountStatementHeader;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.DenominationMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.PreSubAccountMaster;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.GLAccountTypeMaster;
import com.traneco.service.model.JournalTransfer;
import com.traneco.service.model.PreAccountMaster;

public interface AmsStatementService 
{
	String getDownloadedAccountStatementFilePath(List<AccountStatement> accountStatements);
	
	List<AccountStatement> getAccountStatementBasedonParameter(AccountStatement accountStatement) throws Exception;
	
	List<GLAccountStatement> getGLAccountStatement(GLAccountStatement glAccountStatement) throws Exception;
	
	String getDownloadedGLAccountStatmntFilePath(List<GLAccountStatement> glAccountStatementList) throws Exception;
	
	String getDownloadedGLAccountStatmntFilePathForExcel(List<GLAccountStatement> glAccountStatementList) throws Exception;
	
	//String getDownloadedGLAccountStatmntFilePathForExcel(List<AccountStatement> accountStatementList) throws Exception;
	
	String getDownloadedAccountStatementFilePathForExcel(List<AccountStatement> accountStatements) throws Exception;
	
	//Added by Sagar Start
	String getDownloadedJournalTransferFilePathForExcel(List<JournalTransfer> journalTransferList);

	String getDownloadedTxnStatmentPdfFilePath(List<AccountTranMaster> accountTranMasterList);
	//added by sunil DEMO
	String getDownloadedGlReportStatement(List<GLAccountTypeMaster> glAccountTypeMasters);

	String getDownloadedTxnStatmentFilePathForExcel(List<AccountTranMaster> accountTranMasterList);

	String getDownloadedChartReportPdfFilePath(List<GLAccountTypeMaster> glAccountTypeMasterList);

	String getDownloadedChartReportFilePathForExcel(List<GLAccountTypeMaster> gLAccountTypeMaster);

	//String getDownloadedRegisterCustLinkedFilePathForExcel(List<AccountCreation> accountCreation);
	//Added by Sagar End
	String getDownloadedregisteredCustomersFilePathForExcel(List<PreAccountMaster> preAccountMasterlist);
	
	//Added by sunil Y , download  added button 17-May-23 START 
	String getDownloadedRegisterCustLinkedFilePathForExcel(List<PreSubAccountMaster> preSubAccountMaster);
	
	//Added by sunil Y , download  added button 17-May-23 START
	String getDownloadedRegisterCustLinkedFilePathFor(List<PreSubAccountMaster> preSubAccountMaster);

	//Added by sunil Y , download  added button 17-May-23 START
	String getDownloadedCashDeptStatementPdfFilePath(List<DenominationMaster> denominationMaster);

	//Added by sunil Y , download  added button 17-May-23 START
	String getDownloadedCashDeptStatementPathForExcel(List<DenominationMaster> denominationMaster);

     String getDownloadedCashWithdrawalStatmntFilePath(List<DenominationMaster> denominationMasters) throws Exception;
	
	String getDownloadedCashWithdrawalStatmntFilePathForExcel(List<DenominationMaster> denominationMasters) throws Exception;
	
	//addded By Sunil Y Started
	List<AccountStatementHeader> getAccountStatementHeader(GLAccountStatement glAccountStatement) throws Exception;

	String getDownloadedJournalTransferFilePathForPDF(List<JournalTransfer> journalTransferList);

	//added by Sunil Y Started
	List<GLAccountTypeMaster> getGLAccountStatementHeader(GLAccountStatement glAccountStatement) throws Exception;

	List<AccountStatementHeader> getAccountStatementAndGLAccountStatementHeader(GLAccountStatement glAccountStatement)
			throws Exception;
	
}
