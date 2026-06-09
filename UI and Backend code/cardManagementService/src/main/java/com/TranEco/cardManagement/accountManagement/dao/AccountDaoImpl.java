package com.TranEco.cardManagement.accountManagement.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.accountManagement.model.AccountRequest;
import com.TranEco.cardManagement.accountManagement.model.AccountResponse;


@Repository
public class AccountDaoImpl implements AccountDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public AccountResponse accountManagement(AccountRequest request) 
	{
		AccountResponse outResponseObj = new  AccountResponse();
		List<SqlParameter> parameters = Arrays.asList(
	            new SqlParameter(Types.VARCHAR), 
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlParameter(Types.VARCHAR),
	            new SqlOutParameter("outResponse", Types.VARCHAR),
	            new SqlOutParameter("outDescription", Types.VARCHAR));
		
		Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() 
		{
	        @Override
	        public CallableStatement createCallableStatement(Connection con) throws SQLException 
	        {
	            CallableStatement callableStatement = con.prepareCall("{call accountManagement (?,?,?,?,?,?,?,?,?,?,?)}");
	            callableStatement.setString(1, request.getStrParticipantID());
	            callableStatement.setString(2, request.getStrFunctionType());
	            callableStatement.setString(3, request.getStrCardNumber());
	            callableStatement.setString(4, request.getStrCardSeqNumber() == null ? "1" : request.getStrCardSeqNumber());
	            callableStatement.setString(5, request.getStrAccountNumber());
	            callableStatement.setString(6, request.getStrAccountType());
	            callableStatement.setString(7, request.getStrCurrencyCode());
	            callableStatement.setString(8, request.getStrAccountBranch());
	            callableStatement.setString(9, request.getStrPrimaryFlag());
	            callableStatement.registerOutParameter(10, Types.VARCHAR);
	            callableStatement.registerOutParameter(11, Types.VARCHAR);
	            return callableStatement;
	        }
	    }, parameters);
		outResponseObj.setOutResponseCode(t.get("outResponse").toString());
		outResponseObj.setMessage(t.get("outDescription").toString());
		return outResponseObj;
	}

}
