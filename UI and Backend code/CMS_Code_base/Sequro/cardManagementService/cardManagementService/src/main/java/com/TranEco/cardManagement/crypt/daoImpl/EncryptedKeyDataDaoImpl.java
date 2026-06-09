package com.TranEco.cardManagement.crypt.daoImpl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.crypt.dao.EncryptedKeyDataDao;
import com.TranEco.cardManagement.crypt.model.EncryptKeyData;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EncryptedKeyDataDaoImpl implements EncryptedKeyDataDao
{
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public int saveKeyData(EncryptKeyData encryptKeyData)
	{
		String sql = "INSERT INTO encryption_key_data(aes_key, aes_iv, file_name) VALUES (?, ?, ?)";
		
		int update = jdbcTemplate.update(sql, 
				new Object[] {
						encryptKeyData.getAesEncodedKey(),
						encryptKeyData.getAesEncodedIv(),
						encryptKeyData.getFileName(),
					
					}
				);
		return update; 
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<EncryptKeyData> getKeyDataByFileName(String fileName)
	{
	   String sql = "SELECT aes_key AS aesEncodedKey, aes_iv AS aesEncodedIv, file_name as fileName "
	   	+ "FROM encryption_key_data WHERE file_name = ? ";
	   List<EncryptKeyData> data = jdbcTemplate.query(sql,
		   new Object[] { fileName },
		   new BeanPropertyRowMapper<EncryptKeyData>(EncryptKeyData.class));
	    return data;
	}


}
