package com.traneco.keyEncrypt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.util.Utils;
import com.traneco.keyEncrypt.model.EncryptKeyModel;
import com.traneco.keyEncrypt.service.EncryptKeyDataService;

@Repository
public class EncryptKeyDataDaoImpl implements EncryptKeyDataDao
{
	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	EncryptKeyDataService encryptKeyDataService;
	
	@Override
	public EncryptKeyModel getEncDecValue() {
		 try 
		   {
		    EncryptKeyModel encryptKeyModels = jdbcCMSTemplate.queryForObject(QueryConstant.GET_ENC_DEC_VALUE, new Object[] {},
		   	new BeanPropertyRowMapper<EncryptKeyModel>(EncryptKeyModel.class));
		    return encryptKeyModels; 
		   } catch (Exception e) 
		   {
		      e.printStackTrace();
		   }
		return null;
	}
	
	@Override
	public EncryptKeyModel getEncDecTempValueFromDB() 
	{
		 try 
		   {
		    EncryptKeyModel encryptKeyModels = jdbcCMSTemplate.queryForObject(QueryConstant.CARD_ENCDEC_TEMP, new Object[] {},
		   	new BeanPropertyRowMapper<EncryptKeyModel>(EncryptKeyModel.class));
		    return encryptKeyModels; 
		   } catch (Exception e) 
		   {
		      e.printStackTrace();
		   }
		return null;
	}
	
	@Override
	public int updateEncDecValueInDB(EncryptKeyModel encryptKeyModel) 
	{
		try {
			String sql = "UPDATE card_encrypt_key SET value = '"+encryptKeyModel.getValue()+"' , created_date = '"+Utils.generateDate()+"'  where id = '"+encryptKeyModel.getId()+"' ";
			int query = jdbcCMSTemplate.update(sql,
					new Object[] {}
					);
			
			return query;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertEncDecValueInTempDB(String keyPartForDBFile) 
	{
		try {
			int count = this.jdbcCMSTemplate.update(
					"INSERT INTO card_encrypt_key_temp (id,value,created_date,created_by) VALUES (?,?,?,?)",
					new Object[] {1,keyPartForDBFile, Utils.generateDate(), this.sessionDTO.getLoginID()});
			return count;
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public EncryptKeyModel getMaskedValue() 
	{
		try 
		   {
		    EncryptKeyModel encryptKeyModels = jdbcCMSTemplate.queryForObject(QueryConstant.GET_MASKED_VALUE, new Object[] {},
		   	new BeanPropertyRowMapper<EncryptKeyModel>(EncryptKeyModel.class));
		    if (encryptKeyModels != null && encryptKeyModels.getValue() != null)
		    {
		    	String maskValue = Utils.maskValue(encryptKeyModels.getValue().trim());
		    	encryptKeyModels.setValue(maskValue.trim());
		    	return encryptKeyModels;
		    }
		   } catch (Exception e) 
		   {
		      e.printStackTrace();
		   }
		return null;
	}

	@Override
	public int insertEncDecValueInDB(EncryptKeyModel encryptKeyModel) 
	{
		try 
		{
			int count = this.jdbcCMSTemplate.update(
					"INSERT INTO card_encrypt_key (id,value,created_date,created_by) VALUES (?,?,?,?)",
					new Object[] {1,encryptKeyModel.getValue(), Utils.generateDate(), this.sessionDTO.getLoginID()});
			return count;
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEncDecTempValueFromDB() 
	{
		 try 
		   {
			 int count = jdbcCMSTemplate.update(QueryConstant.DELETE_ENDEC_TEMP);
		    return count; 
		   } catch (Exception e) 
		   {
		      e.printStackTrace();
		      return 0;
		   }
		
	}

}
