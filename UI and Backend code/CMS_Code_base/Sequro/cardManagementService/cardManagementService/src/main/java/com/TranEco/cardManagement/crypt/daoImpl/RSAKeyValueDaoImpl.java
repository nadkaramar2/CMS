package com.TranEco.cardManagement.crypt.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.crypt.dao.RSAKeyValueDao;
import com.TranEco.cardManagement.crypt.model.RsaKeyValue;

@Repository
public class RSAKeyValueDaoImpl implements RSAKeyValueDao

{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public RsaKeyValue getRSAKeyValue(int id) 
	{
		try 
		   {
			RsaKeyValue rsaKeyValues = jdbcTemplate.queryForObject(QueryConstant.GET_RSA_KEY, new Object[] { id },
		   	new BeanPropertyRowMapper<RsaKeyValue>(RsaKeyValue.class));
		    return rsaKeyValues; 
		   } catch (Exception e) 
		   {
		      e.printStackTrace();
		   }
		return null;
	}

}
