package com.traneco.encDecKey.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.common.constants.QueryConstant;
import com.traneco.keyEncrypt.model.EncryptKeyModel;

@Repository
public class EncDecKeyDaoImpl implements EncDecKeyDao
{
	
	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int validateCustdian1Password(EncryptKeyModel encryptKeyModel) 
	{
		try 
		{
			int count = jdbcTemplate.queryForObject(QueryConstant.Validate_CUSTODIAN1_PASSWORD, new Object[] { encryptKeyModel.getPassword() },
					Integer.class);
			System.out.println("EncDecKeyDaoImpl.validateCustodian1Password()"+count);
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int validateCustdian2Password(EncryptKeyModel encryptKeyModel) 
	{
		try 
		{
			int count = jdbcTemplate.queryForObject(QueryConstant.Validate_CUSTODIAN2_PASSWORD, new Object[] { encryptKeyModel.getPassword() },
					Integer.class);
			System.out.println("EncDecKeyDaoImpl.validateCustodian1Password()"+count);
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

}
