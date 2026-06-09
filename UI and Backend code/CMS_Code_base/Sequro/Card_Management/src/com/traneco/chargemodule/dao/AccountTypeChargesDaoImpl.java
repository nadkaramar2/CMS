package com.traneco.chargemodule.dao;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.common.util.logger.SequroLogger;
import com.traneco.configuration.model.AccountTypeCharges;


@Repository
public class AccountTypeChargesDaoImpl implements AccountTypeChargesDao 
{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private SequroLogger sequroLogger = SequroLogger.getInstance(AccountTypeChargesDaoImpl.class);


	//Addded by prashant Tayde for getting charges data based on account Type
		/* @Override
		public AccountTypeCharges getAccountTypeChargesBasedOnAccountType(AccountTypeCharges accountWiseCharges) {
			try 
			{
				StringBuilder queryBuilder = new StringBuilder("SELECT calm.account_type AS strAccountType, calm.account_number AS strAccountNumber, atc.charge_type AS strChargeType, atc.charge_description AS strChargeDescription,  ");
				queryBuilder.append("atc.amount AS strAmount, atc.percentage AS strPercentage ");
				queryBuilder.append("from account_type_charges atc ");
				queryBuilder.append("INNER JOIN card_account_linkage_master calm ");
				queryBuilder.append("ON atc.account_type = calm.account_type ");
				queryBuilder.append("WHERE calm.account_type = ? AND atc.charge_type = ? AND calm.account_number = ? ");
				
				List<AccountTypeCharges> accountTypecharges  = jdbcTemplate.query(queryBuilder.toString(),
				new BeanPropertyRowMapper<AccountTypeCharges>(AccountTypeCharges.class),
				     new Object[]  { accountWiseCharges.getStrAccountType(),
				    		 accountWiseCharges.getStrChargeType(),accountWiseCharges.getStrAccountNumber()});
				System.out.println("AccountTypeChargesDaoImpl.getAccountTypeChargesBasedOnAccountType()"+accountTypecharges);
				
				return accountTypecharges.get(0);
			} 
			catch (Exception e) {
				sequroLogger.writeExceptionLog("Exception occured::"+ExceptionUtils.getStackTrace(e));
			}
			return null;
		} */
	
	
	@Override
	public AccountTypeCharges getAccountTypeChargesBasedOnAccountType(AccountTypeCharges accountWiseCharges) 
	{
		try 
		{
			StringBuilder queryBuilder = new StringBuilder("SELECT Distinct calm.account_type AS strAccountType, calm.account_number AS strAccountNumber, atc.charge_type AS strChargeType, atc.charge_description AS strChargeDescription,  ");
			queryBuilder.append("atc.amount AS strAmount, atc.percentage AS strPercentage, am.closing_balance AS strClosingBalance ");
			queryBuilder.append("from account_type_charges atc ");
			queryBuilder.append("INNER JOIN card_account_linkage_master calm ");
			queryBuilder.append("ON atc.account_type = calm.account_type ");
			queryBuilder.append("INNER JOIN account_master am ");
			queryBuilder.append("ON am.account_number = calm.account_number ");
			queryBuilder.append("WHERE calm.account_type = ? AND atc.charge_type = ? ");
			
			List<AccountTypeCharges> accountTypecharges  = jdbcTemplate.query(queryBuilder.toString(),
			new BeanPropertyRowMapper<AccountTypeCharges>(AccountTypeCharges.class),
			     new Object[]  { accountWiseCharges.getStrAccountType(),
			    		 accountWiseCharges.getStrChargeType()});
			System.out.println("AccountTypeChargesDaoImpl.getAccountTypeChargesBasedOnAccountType()"+accountTypecharges);
			
			return accountTypecharges.get(0);
		} 
		catch (Exception e) 
		{
			sequroLogger.writeExceptionLog("Exception occured::"+ExceptionUtils.getStackTrace(e));
		}
		return null;
	}


		@Override
		public AccountTypeCharges getAccountTypeChargesBasedOnAccType(AccountTypeCharges accountTypeCharges) {
			try {
				StringBuilder queryBuilder = new StringBuilder("SELECT am.account_type AS strAccountType,am.account_number AS  strAccountNumber,am.closing_balance AS strClosingBalance,am.creation_date AS strAccountIssueDate,  ");
				queryBuilder.append("atc.charge_type AS strChargeType,atc.charge_description AS strChargeDescription,atc.amount AS strAmount,atc.percentage AS strPercentage ");
				queryBuilder.append("FROM account_master am ");
				queryBuilder.append("INNER JOIN account_type_charges atc ");
				queryBuilder.append("ON am.account_type = atc.account_type ");
				queryBuilder.append("WHERE am.account_type = ? AND am.account_number = ? AND atc.charge_type = ? ");
				
				List<AccountTypeCharges> accountTypecharges  = jdbcTemplate.query(queryBuilder.toString(),
				new BeanPropertyRowMapper<AccountTypeCharges>(AccountTypeCharges.class),
				     new Object[]  { accountTypeCharges.getStrAccountType(),
				    		accountTypeCharges.getStrAccountNumber(),accountTypeCharges.getStrChargeType()});
				System.out.println("AccountTypeChargesDaoImpl.getAccountTypeChargesBasedOnAccType()"+accountTypecharges);
				
				return accountTypecharges.get(0);
			} 
			catch (Exception e) {
				sequroLogger.writeExceptionLog("Exception occured::"+ExceptionUtils.getStackTrace(e));
			}
			return null;
		}
}
