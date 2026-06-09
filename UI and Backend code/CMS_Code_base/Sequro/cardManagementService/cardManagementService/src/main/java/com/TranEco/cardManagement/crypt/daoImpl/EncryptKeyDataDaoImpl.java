package com.TranEco.cardManagement.crypt.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.crypt.dao.EncryptKeyDataDao;
import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;

@Repository
public class EncryptKeyDataDaoImpl implements EncryptKeyDataDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	@Override
	public EncryptKeyModel getKeySaltPhrase() 
	{
	   try 
	   {
	    EncryptKeyModel queryForObject = jdbcTemplate.queryForObject(QueryConstant.GET_KEY_PHRASE, new Object[] {},
	   	new BeanPropertyRowMapper<EncryptKeyModel>(EncryptKeyModel.class));
	    return queryForObject; 
	   } catch (Exception e) 
	   {
	      e.printStackTrace();
	   }
	   return null;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public EncryptKeyModel getEncDecValue() 
	{
		 try 
		 {
		    EncryptKeyModel encryptKeyModels = jdbcTemplate.queryForObject(QueryConstant.GET_ENC_DEC_VALUE, new Object[] {},
		   	new BeanPropertyRowMapper<EncryptKeyModel>(EncryptKeyModel.class));
		    return encryptKeyModels; 
		 } catch (Exception e) 
		   {
		      e.printStackTrace();
		   }
		return null;
	}
}
