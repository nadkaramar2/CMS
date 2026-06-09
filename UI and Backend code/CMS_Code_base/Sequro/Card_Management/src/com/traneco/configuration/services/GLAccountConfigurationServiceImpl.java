package com.traneco.configuration.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.traneco.common.util.Utils;
import com.traneco.configuration.dao.ConfigurationDao;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.BalanceUpdateInAccountMaster;
import com.traneco.configuration.model.GLAccountLoadingMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.UpdateGLAccount;

//created by ankit on 09-01-2023 for GLAccounts
@Service
public class GLAccountConfigurationServiceImpl implements GLAccountConfigurationService {

	@Autowired
	ConfigurationDao configurationDao;

	@Override
	public List<GLAccountLoadingMaster> getGLAccountTypes() 
	{
		List<GLAccountLoadingMaster> glAccountTypes = this.configurationDao.getGLAccountTypes();
		return glAccountTypes;
	}

	@Override
	@Transactional()
	public int addLoadBalanceInGlAccount(GLAccountLoadingMaster glAccountLoadingMaster) {

		try {
			// adding the Transaction of Gl Loading
			AccountTranMaster accountTranMaster = mapGLLoadToAccountTranMaster(glAccountLoadingMaster);
			int addTransaction = addTransaction(accountTranMaster);

			// adding entry in glAccountLoadingMaster
			GLAccountLoadingMaster newGlAccountLoadingMaster = mapAccountTranMasterToGlAccountLoadingMaster(accountTranMaster);
			this.configurationDao.addLoadInGLAccount(newGlAccountLoadingMaster);

			UpdateGLAccount updateGLAccount = updateClosingBalance(glAccountLoadingMaster);
			int updateGLAccountDetails = this.configurationDao.updateGLAccountDetails(updateGLAccount);
			
			// String strLoadCountString = updateGLAccount.getStrLoadCountString();
			String balance = updateGLAccount.getStrClosingBalanceString();

			GLAccountStatement accountStatementDetails = mapToAccountStatement(accountTranMaster, balance);
			accountStatementDetails.setStrIsGLType("Y");
			int addAccountStatement = addAccountStatement(accountStatementDetails);

			if (addTransaction == 0 || updateGLAccountDetails == 0 || addAccountStatement == 0) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public UpdateGLAccount updateClosingBalance(GLAccountLoadingMaster glAccountLoadingMaster) 
	{
		try 
		{
			String strGLAccountType = glAccountLoadingMaster.getStrGLAccountType();
			String strAccountNumber = glAccountLoadingMaster.getStrAccountNumber();
			String strLoadedBalance = glAccountLoadingMaster.getStrLoadedBalance();
			int loadedBalanceInteger = Integer.parseInt(strLoadedBalance);

			// getting the loadCount and Closing balance
			String closingBalance = this.configurationDao.fetchClosingBalanceOfGlAccount(strGLAccountType,strAccountNumber);

			int closingBalanceInteger = Integer.parseInt(closingBalance);
			int newClosingBalance = closingBalanceInteger + loadedBalanceInteger;

			String newClosingBalanceString = String.valueOf(newClosingBalance);

			UpdateGLAccount updateGLAccount = new UpdateGLAccount();
			updateGLAccount.setStrClosingBalanceString(newClosingBalanceString);
			updateGLAccount.setStrAccountNumber(strAccountNumber);

			return updateGLAccount;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	private AccountTranMaster mapGLLoadToAccountTranMaster(GLAccountLoadingMaster glAccountLoadingMaster) {
		try {
			String strAccountNumber = glAccountLoadingMaster.getStrAccountNumber();
			String strGLAccountType = glAccountLoadingMaster.getStrGLAccountType();
			String strGLAccountDescription = glAccountLoadingMaster.getStrGLAccountDescription();
			String strChannel = glAccountLoadingMaster.getStrChannel();
			String strLoadedBalance = glAccountLoadingMaster.getStrLoadedBalance();
			String julidanDate = Utils.getJulidanDate();
			String localDate = Utils.getLocalDate();
			String localTime = Utils.getLocalTime();

			AccountTranMaster accountTranMaster = new AccountTranMaster();
			accountTranMaster.setStrAccountNumber(strAccountNumber);
			accountTranMaster.setStrSys_id("001");
			accountTranMaster.setStrTran_type("CR");
			accountTranMaster.setStrAccountType(strGLAccountType);
			accountTranMaster.setStrTransaction_amount(strLoadedBalance);
			accountTranMaster.setStrLocal_tran_time(localTime);
			accountTranMaster.setStrLocal_tran_date(localDate);
			accountTranMaster.getStrTo_account_number();
			return accountTranMaster;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BalanceUpdateInAccountMaster gLAccountMasterMapToBalanceUpdateInAccountMaster(
			GLAccountLoadingMaster glAccountLoadingMaster) {
		String strLoadedBalance = glAccountLoadingMaster.getStrLoadedBalance();
		String strAccountNumber = glAccountLoadingMaster.getStrAccountNumber();
		String strAccountType = glAccountLoadingMaster.getStrGLAccountType();
		BalanceUpdateInAccountMaster balance = new BalanceUpdateInAccountMaster();
		balance.setStrAccountNumber(strAccountNumber);
		balance.setStrAccountType(strAccountType);
		balance.setStrClosingBalance(strLoadedBalance);
		return balance;
	}

	public int addTransaction(AccountTranMaster accountTranMaster) {
		String julianDate = Utils.getJulidanDate();
		String strLocalTranDate = accountTranMaster.getStrLocal_tran_date();
		List<AccountTranMaster> lastTransaction = this.configurationDao.getLastTransaction();
		if (lastTransaction.size() == 0) {
			// add a new Transaction
			String transactionNumnerStringOneString = "0000001";
			String transactionIdString = julianDate + transactionNumnerStringOneString;
			long strTxn_id = Long.parseLong(transactionIdString);
			accountTranMaster.setStrTxn_id(String.valueOf(strTxn_id));
			int addTransaction = this.configurationDao.addTransaction(accountTranMaster);

			return addTransaction;
		} 
		else if (strLocalTranDate.compareTo(lastTransaction.get(0).getStrLocal_tran_date()) > 0) 
		{
			// add a new Transaction
			String transactionNumnerStringOneString = "0000001";
			String transactionIdString = julianDate + transactionNumnerStringOneString;
			long transactionIdInteger = Long.parseLong(transactionIdString);
			accountTranMaster.setStrTxn_id(String.valueOf(transactionIdInteger));
			int addTransaction = this.configurationDao.addTransaction(accountTranMaster);
			return addTransaction;
		}
		else 
		{
			long strTxn_id = Long.parseLong(lastTransaction.get(0).getStrTxn_id());
			System.out.println(strTxn_id);
			long newTxnIdLong = strTxn_id + 1;
			accountTranMaster.setStrTxn_id(String.valueOf(newTxnIdLong));
			int addTransaction = this.configurationDao.addTransaction(accountTranMaster);
			return addTransaction;
		}
	}

	public GLAccountStatement mapToAccountStatement(AccountTranMaster accountTranMaster, String balance) {
		String strAccountNumber = accountTranMaster.getStrAccountNumber();
		String strAccountType = accountTranMaster.getStrAccountType();
		String strLocalTranDate = accountTranMaster.getStrLocal_tran_date();
		Date date = null;
		try 
		{
			date = new SimpleDateFormat("dd-MM-yyyy").parse(strLocalTranDate);
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strTransaction_amount = accountTranMaster.getStrTransaction_amount();
		long strTxn_id = Long.parseLong(accountTranMaster.getStrTxn_id());
		String transactionId = String.valueOf(strTxn_id);
		accountTranMaster.getStrLocal_tran_time();
		String strClosingBalance = balance;
		GLAccountStatement accountStatement = new GLAccountStatement();
		accountStatement.setStrAccountNumber(strAccountNumber);
		accountStatement.setStrAccountType(strAccountType);
		accountStatement.setStrClosingBalance(strClosingBalance);
		accountStatement.setStrIsGLType("N");
		accountStatement.setStrTransactionAmount(strTransaction_amount);
		accountStatement.setStrTransactionID(transactionId);
		accountStatement.setStrTransactionMode("CREDIT");
		accountStatement.setStrTransactionType("DEPOSIT");
		return accountStatement;
	}

	// same method is or both Gl loading and loadingffor non Credit but the models
	public int addAccountStatement(GLAccountStatement accountStatement) {
		int addEntryInStatement = this.configurationDao.addGLEntryInStatement(accountStatement);
		return addEntryInStatement;
	}

//		public GLAccountLoadingMaster fetchAccountTypeAndDesctiption(AccountTranMaster accountTranMaster) {
//			String accountNumber = accountTranMaster.getStrAccountNumber();
//			
//			List<GLAccountLoadingMaster> fetchGLAccountDetails = this.configurationDao.fetchGLAccountDetails(accountNumber);
//			GLAccountLoadingMaster glAccountLoadingMaster = new GLAccountLoadingMaster();
//			String strGLAccountDescription = fetchGLAccountDetails.get(0).getStrGLAccountDescription();
//			String strGLAccountType = fetchGLAccountDetails.get(0).getStrGLAccountType();
//			
//			return glAccountLoadingMaster;
//			
//		}

	public GLAccountLoadingMaster mapAccountTranMasterToGlAccountLoadingMaster(AccountTranMaster accountTranMaster) {
		String accountNumber = accountTranMaster.getStrAccountNumber();
		long strTxn_id = Long.parseLong(accountTranMaster.getStrTxn_id());
		String transactionId = String.valueOf(strTxn_id);
		String strLocal_tran_date = accountTranMaster.getStrLocal_tran_date();
		String strLocal_tran_time = accountTranMaster.getStrLocal_tran_time();
		GLAccountLoadingMaster glAccountLoadingMaster = new GLAccountLoadingMaster();
		try {
			List<GLAccountLoadingMaster> fetchGLAccountDetails = this.configurationDao
					.fetchGLAccountDetails(accountNumber);
			String strGLAccountDescription = fetchGLAccountDetails.get(0).getStrGLAccountDescription();
			String strGLAccountType = fetchGLAccountDetails.get(0).getStrGLAccountType();
			glAccountLoadingMaster.setStrGLAccountDescription(strGLAccountDescription);
			glAccountLoadingMaster.setStrGLAccountType(strGLAccountType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		glAccountLoadingMaster.setStrDateOfLoading(strLocal_tran_time);
		glAccountLoadingMaster.setStrTimeOfLoading(strLocal_tran_date);
		glAccountLoadingMaster.setStrTransactionId(transactionId);
		return glAccountLoadingMaster;

	}

	// created by ankit updated on 12-01-2023
	@Override
	public String getAvailabelBalance(String accountType, String accountNumber) {
		try {
			String closingBalance = this.configurationDao.fetchClosingBalanceOfGlAccount(accountType, accountNumber);
			return closingBalance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// created by ankit updated on 12-01-2023
}
